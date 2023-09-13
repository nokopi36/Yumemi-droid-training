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

このリポジトリはテンプレートリポジトリに設定されています。右上の「Use this template」をタップして、ご自身のGitHubアカウントで研修用リポジトリを作成してください。

<img src="https://user-images.githubusercontent.com/25225028/248723114-3db906be-ac27-4072-bab6-b8fa15663b7f.png">

> **Warning**
>
> 「Include all branches」オプションは**チェックしない**でください
> <img src="https://user-images.githubusercontent.com/25225028/248724454-ee7e2890-6742-4e3a-bb0a-2f80a6ccb4ef.png">


リポジトリが作成されると、テンプレートのSetupワークフローが自動実行されます。

- 各課題 Issue の登録
- 不要なファイルを削除して Commit & Push
- `template/*`ブランチの作成

## 課題

研修の課題はすべてIssueで管理しています。課題の種類ごとにラベルが設定されています

### 課題の選択

| label | 説明                             |  
|-------|--------------------------------|  
| 必須    | 必ず取り組みましょう                     |  
| 選択必須  | 取り組む必要がありますが、作成済みコードを利用してもよいです |  
| 任意    | スキップも可能です                      |  

> **Note**
>
> ### すべての課題を完了する必要はありません
>
> ２週間の研修期間で必須課題が完了すれば十分です！ もし時間が余るようであれば任意課題も取り組んでみましょう 💪

### UI作成の方法

研修を始めるときメンターの指示に従って選択してください

| label   | 説明                    |  
|---------|-----------------------|  
| View    | XML形式のレイアウトファイルを利用します |  
| Compose | Jetpack Composeを利用します |  

#### 💡 Issueの一覧表示

Issueページでlabelのフィルターを利用し、取り組むべき課題の一覧を見てみましょう。原則としてIssue番号の小さい順に取り組んでください

<img width="600" src="https://user-images.githubusercontent.com/25225028/220609765-d6c8356d-3074-4f26-a1f6-a1f25c89b36b.png">

## 課題の進め方

### ブランチ運用

1. main ブランチから課題用のブランチを切って作業： `feature/{#}`
2. 完了したら Pull Requestを作成してレビューを依頼
3. PRがapproveされたらmasterブランチにマージ
4. 次の課題へ進む

全ての必須課題をクリアしたら修了です！

#### 💡 レビュー待ちのとき

レビュー待ちの時は次の課題に先行着手しましょう。  
`git rebase` コマンドを使ってみましょう。  
課題#1 がレビュー待ちの場合...

1. `feature/1`ブランチから`feature/2`を切る
2. 課題#2を進める
3. `feature/1`のマージ後、`feature/2`を`main`でrebaseする

### Pull Requestの作成

新たにPR (Pull Request)を作成するときはテンプレート文に従って必要事項を記入してください。

#### 💡 PRと課題Issueのリンク

PRの説明文に「Resovel #{Issue番号}」と書くと自動でIssueとリンクされます.
リンクされたIssueはPR画面の右側サイドパネルからも確認できます

<img width="400" alt="image" src="https://user-images.githubusercontent.com/25225028/221106346-737fd21a-9a2a-414a-bf62-94789c8ee410.png">

リンクされたPRがマージされると自動で対応するIssueもCloseされるので進捗の管理に便利です

<img width="400" alt="image" src="https://user-images.githubusercontent.com/25225028/221106250-0057ea33-eac3-400d-a228-fd2a81204d98.png">

### 作成済みコードの利用

一部の実装負担が大きい課題では、あらかじめ作成済みのコードを利用できます.

- API呼び出しの実装：`api`モジュールのファイル
- CIの実装：`./github`以下のファイル

作成済みコードはブランチ`template/*`に用意されており、必要に応じてマージしてください.
