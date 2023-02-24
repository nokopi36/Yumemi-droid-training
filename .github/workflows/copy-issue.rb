require 'octokit'

def fetch_token(key)
  token = ENV.fetch(key, "")
  # 空文字だと401 Bad Credentials
  token.empty? ? nil : token
end

src_client = Octokit::Client.new(access_token: fetch_token("src_repo_token"))
src_client.auto_paginate = true
src_repo = ENV.fetch("src_repo")

dst_client = Octokit::Client.new(access_token: fetch_token("dst_repo_token"))
src_client.auto_paginate = true
dst_repo = ENV.fetch("dst_repo")

def with_retry
  delay = 1
  begin
    yield
  rescue Octokit::TooManyRequests => e
    # seconday rate limitsで403の対応
    # https://docs.github.com/en/rest/guides/best-practices-for-integrators?apiVersion=2022-11-28#dealing-with-secondary-rate-limits
    # delay = e.response_headers["Retry-After"].to_i 見つからない？
    puts ("exceeded seconday rate limits, wait #{delay} sec")
    sleep(delay)
    delay *= 2
    retry
  end
end

# label一覧の取得
labels = src_client.labels(src_repo)

# labelを追加
labels.each do |label|
  with_retry do
    dst_client.add_label(dst_repo, label.name, label.color, {description: label.description})
  end
  puts "label added: #{label.name}"
end

# milestone取得
milestones = src_client.list_milestones(src_repo)

# milestone追加 & number記録
milestone_numbers = milestones.map do |m|
  r = with_retry do
    dst_client.create_milestone(dst_repo, m.title, {description: m.description})
  end
  puts "milestone added: ##{r.number} #{m.title}"
  [m.number, r.number]
end.to_h

# issue一覧の取得
# このAPIではissueとPRは同一視されるがopenなPRは無い前提で実行
issues = src_client.list_issues(src_repo, {sort: "created", direction: "asc"})

# 既存のissue,PRの数を考慮(closed含む)
issue_number_offset = dst_client.list_issues(dst_repo, {state: "all"}).length + 1

# issue追加
issues.each do |i|
  # bodyテキスト中の他issueへのリンクテキストを置換
  body = i.body.gsub(/(?<=#)[0-9]+(?=\s)/) do |m|
    idx = issues.index{|e| e.number == m.to_i }
    (issue_number_offset + idx).to_s
  end
  milestone_number = milestone_numbers[i.milestone.number]
  labels = i.labels.map(&:name)
  r = with_retry do
    dst_client.create_issue(dst_repo, i.title, body, {milestone: milestone_number, labels: labels})
  end
  puts "issue added: ##{r.number} #{i.title}"
end
