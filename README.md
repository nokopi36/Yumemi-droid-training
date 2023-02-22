# Android研修（改）

業務に近いかたちでアプリ開発を行いながら、  
Androidアプリ開発の基礎復習、実務スキルを身に付けるための研修です。

## 概要

天気予報アプリを開発します  
<img src="https://user-images.githubusercontent.com/25225028/218980194-d745e7bf-d470-4235-a2d4-6a7d8bd850bd.gif" width="300">


## 環境

Android Studio Electric Eel | 2022.1.1 以上のバージョン

[最新の Android Studioをダウンロードする](https://developer.android.com/studio)

## 研修を始める前に

#### 1. GitHubリポジトリの用意
本プロジェクトを [**Duplicate** してください](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/duplicating-a-repository)（Fork しないようにしてください。必要ならプライベートリポジトリにしても大丈夫です）。今後のコミットは全てご自身のリポジトリで行ってください。

#### 2. Issueのコピー
ご自身のリポジトリにIssueをコピーするGitHub Actionsを用意しています。
[こちらのWorkflow](./.github/workflows/setup.yml)を[手動でトリガーする](https://docs.github.com/ja/actions/managing-workflow-runs/manually-running-a-workflow)ことでコピーできますのでご活用下さい。

<img width="600" src="https://user-images.githubusercontent.com/25225028/220605964-1e6eda91-cdc7-4540-b3c5-e2da13c88101.png">



## 課題
研修の課題はすべてIssueで管理しています。課題の種類ごとにラベルが設定されています

### 課題の選択
すべての課題を完了する必要はありません。ご自身の進め方や時間的余裕に応じて選択してください。

| label | 説明 |  
|:-:|:--|  
|必須| 必ず取り組みましょう |  
|選択必須| 取り組む必要がありますが、作成済みコードを利用してもよいです |  
|任意| スキップも可能です |  

### UI作成の方法
研修を始めるときメンターの指示に従って選択してください

| label | 説明 |  
|:-:|:--|  
|View| XML形式のレイアウトファイルを利用します |  
|Compose| Jetpack Composeを利用します |  


💡 Issueの一覧表示

Issueページでlabelのフィルターを利用し、取り組むべき課題の一覧を見てみましょう

<img width="600" src="https://user-images.githubusercontent.com/25225028/220609765-d6c8356d-3074-4f26-a1f6-a1f25c89b36b.png">



## ブランチ運用

1. main ブランチから課題用のブランチを切って作業： `feature/{#}`
2. 完了したら Pull Requestを作成してレビューを依頼
3. PRがapproveされたらmasterブランチにマージ
4. 次の課題へ進む

全ての改題をクリアしたら修了です！

💡 レビュー待ちのとき

レビュー待ちの時は次の課題に先行着手しましょう。  
`git rebase` コマンドを使ってみましょう。  
課題#1 がレビュー待ちの場合...

1. `feature/1`ブランチから`feature/2`を切る
2. 課題#2を進める
3. `feature/1`のマージ後、`feature/2`を`main`でrebaseする
