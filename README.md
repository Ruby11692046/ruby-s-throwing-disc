# Ruby's Throwing Disc

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1%20%7C%201.21.1-brightgreen)
![Forge](https://img.shields.io/badge/Forge-1.20.1-orange)
![NeoForge](https://img.shields.io/badge/NeoForge-1.21.1-blue)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

2027年第1ドロップで実装予定の新武器**「投擲ディスク（Throwing Disc）」**を、Minecraft 1.20.1（Forge）および 1.21.1（NeoForge）で先行して遊べるようにする Mod です。

---

## 📁 プロジェクト構成 / Directory Structure

本リポジトリでは、Minecraft のバージョンごとに独立したプロジェクトフォルダを用意しています。

```
ruby-s-throwing-disc/
├── throwing_disc_1.20.1/  # 1.20.1 (Forge) 向けプロジェクト（最新: v0.1.25）
└── throwing_disc_1.21.1/  # 1.21.1 (NeoForge) 向けプロジェクト（最新: v0.1.28）
```

| ディレクトリ | Minecraft バージョン | Mod ローダー | 最新Modバージョン |
| :--- | :--- | :--- | :--- |
| **`throwing_disc_1.20.1`** | 1.20.1 | Minecraft Forge (47.4.20+) | **v0.1.25** |
| **`throwing_disc_1.21.1`** | 1.21.1 | NeoForge (21.1.66+) | **v0.1.28** |

---

## 📖 特徴 / Features

- **直進＆貫通**: 重力を受けずに一直線に飛び、経路上に並んだ複数の敵を貫通します。
- **跳ね返り＆加速**: 固いブロックに当たると跳ね返り（リコシェ）、跳ね返るたびに射程と速度が上昇します。
- **自動帰還**: 投げた手元へ自動で戻ってきます（帰り道でも敵に命中します）。3秒経過すると強制的に戻ります。
- **両手対応＆ノーコスト**: メインハンド・オフハンドどちらからでも投げられ、チャージ・クールダウン・弾薬は不要です。
- **ギミック連動**: 飾り壺の破壊やコーラスフラワーのアイテム化など、バニラの飛び道具挙動を再現しています。
- **独自エンチャント**:
- **繊細 (Delicate)**: クリーチャーを貫通せず跳ね返ります
  - **滞空 (Lingering I〜III)**: 戻ってくるまでの滞空時間と射程が延長
  - **分裂 (Splitting I〜IV)**: 扇状に複数枚のディスクを投擲
- **速度調整**: コンフィグ（discSpeed）でディスクの飛ぶ速度を変更できます
- **多数の他Mod連携（ソフト依存）**: 各種有名Modの素材を使ったディスクを追加。相手Modが未導入の環境でも問題なく単独動作します。

---

## 📦 追加ディスク一覧と対応素材Mod

ディスクは **1.20.1版で全33種**、**1.21.1版で全36種** 登場します。

### バニラ素材（5種）
- 銅、鉄、金、ダイヤモンド、ネザライト

### 外部Mod連携素材
- **Twilight Forest**: アイアンウッド、スティールリーフ、ナイトメタル、ファイアリー
- **The Aether**: アンブロシウム、ザナイト、グラビタイト
- **Create**: 安山岩合金、亜鉛、真鍮
- **Mekanism**: オスミウム、鋼鉄、精製グロウストーン、精製オブシディアン
- **Deeper and Darker**: 強化残響
- **L_Ender's Cataclysm**: ブラック鋼、古代金属、エンダライト、イグニティウム、カーシウム、ウィザライト
- **Ice and Fire**: 銀、龍骨、幻影、火龍鋼、氷龍鋼、雷龍鋼
- **Enderite Mod**: エンダライト
- **Thaumaturge** *(1.21.1 のみ)*: 錬金真鍮、ソーミウム、虚無金属

---

## 💻 ビルド方法 / Building from Source

各フォルダに移動して Gradle ビルドを実行します。

### 1.20.1 (Forge)
- **動作要件**: JDK 17
```bash
cd throwing_disc_1.20.1
./gradlew build
# 成果物: throwing_disc_1.20.1/build/libs/throwing_disc-1.20.1-0.1.15.jar
```

### 1.21.1 (NeoForge)
- **動作要件**: JDK 21
```bash
cd throwing_disc_1.21.1
./gradlew build
# 成果物: throwing_disc_1.21.1/build/libs/throwing_disc-1.21.1-0.1.23.jar
```

---

## 🏷️ jar ファイル命名規則

配布用 jar ファイルは、Mod ID・Minecraft バージョン・Mod バージョンが判別できる以下の形式で統一されています。

```
throwing_disc-[Minecraftバージョン]-[Modバージョン].jar
```
- 1.20.1: `throwing_disc-1.20.1-0.1.15.jar`
- 1.21.1: `throwing_disc-1.21.1-0.1.23.jar`

---

## 🤖 AI 利用に関する開示 / AI Disclosure

本 Mod は、プログラムコードの実装・テクスチャ画像の作成・ドキュメント作成において **生成AI（AI支援）** を使用して制作されています。

- **企画・ディレクション・テクスチャ監修**: Ruby
- **AI 支援 (AI-Assisted / AI-Generated)**: プログラムコードの実装、テクスチャ作成、ドキュメント生成

---

## 📜 ライセンス / License

本 Mod は **[MIT License](LICENSE)** の下で公開されています。  
- 制作: **Ruby**（AI-Assisted）
