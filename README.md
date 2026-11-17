# Ruby's Throwing Disc (1.21.1 NeoForge)

![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-brightgreen)
![NeoForge](https://img.shields.io/badge/NeoForge-21.1.66+-blue)
![Version](https://img.shields.io/badge/Version-0.1.13-blue)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

2027年第1ドロップで実装予定の新武器**「投擲ディスク（Throwing Disc）」**の Minecraft 1.21.1（NeoForge）移植版Modです。

---

## 📖 特徴 / Features

- **直進＆貫通**: 重力を受けずに一直線に飛び、経路上に並んだ複数の敵を貫通します。
- **跳ね返り＆加速**: 固いブロックに当たると跳ね返り（リコシェ）、跳ね返るたびに射程と速度が上昇します。
- **自動帰還**: 投げた手元へ自動で戻ってきます（帰り道でも敵に命中します）。3秒経過すると強制的に戻ります。
- **両手対応＆ノーコスト**: メインハンド・オフハンドどちらからでも投げられ、チャージ・クールダウン・弾薬は不要です。
- **ギミック連動**: 飾り壺の破壊やコーラスフラワーのアイテム化など、バニラの飛び道具挙動を再現しています。
- **多言語対応**: 日本語、英語をはじめとする全14言語に対応。
- **全35種のディスク**: バニラ5種＋各種連携Mod素材30種（1.21.1専用のThaumaturge連携を含む）。
- **ソフト依存（Soft Dependency）**: 連携先Modが導入されていない環境でも問題なく単独で動作します（未導入Modのレシピのみ無効化）。

---

## 📦 収録ディスク（全35種）

### バニラ素材（5種）
- 銅、鉄、金、ダイヤモンド、ネザライト（鍛冶台で強化）

### 外部Mod連携素材（30種）
- **Twilight Forest (黄昏の森)**: アイアンウッド、スティールリーフ、ナイトメタル、ファイアリー
- **The Aether**: アンブロシウム、ザナイト、グラヴィタイト
- **Create**: 安山合金、亜鉛、真鍮
- **Mekanism**: オスミウム、鋼鉄、精製グロウストーン、精製オブシディアン
- **Deeper and Darker**: 強化残響
- **L_Ender's Cataclysm**: ブラック鋼、古代金属、エンダライト、イグニティウム、カーシウム、ウィザライト
- **Ice and Fire**: 銀、龍骨、幻影、火龍鋼、氷龍鋼、雷龍鋼
- **Thaumaturge** *(1.21.1 限定)*: 錬金真鍮、ソーミウム、虚無金属

### 特殊武器特性
- **アイアンウッド**: ノックバック強化（ノックバックI内蔵の再現）
- **スティールリーフ**: キル時に経験値ボーナス（ドロップ増加IIの翻案）
- **ナイトメタル**: 防具を着た敵に対してダメージ+2（装甲特効）
- **ファイアリー / イグニティウム / 火龍鋼**: 命中時に相手を着火
- **ザナイト**: ディスク自体の耐久値が減るほど与ダメージ上昇（最大2倍）
- **グラヴィタイト**: 命中した敵を真上へ打ち上げる
- **銀**: アンデッド系に対して1.5倍のダメージ
- **龍骨 / 龍鋼**: ドラゴンに対して1.5倍のダメージ
- **強化残響**: スカルク系の敵に対してダメージ+5（Sculk Smite の翻案）
- **ウィザライト**: 命中時にウィザー効果を付与
- **氷龍鋼**: 命中時に移動速度低下（鈍化）を付与
- **虚無金属**: インベントリに所持している間に少しずつ耐久が自己修復
- **ソーミウム**: エンチャント適性が非常に高い

---

## 🛠️ 導入方法 / Installation

1. Minecraft 1.21.1 および **NeoForge 21.1.66 以降** をインストールします。
2. リリースページから `throwing_disc-1.21.1-0.1.13.jar` をダウンロードします。
3. ダウンロードした jar ファイルを `.minecraft/mods` フォルダに配置します。

---

## 💻 ビルド方法 / Building from Source

### 前提環境
- **JDK 21**（Java 21）

### ビルド手順
```bash
# プロジェクトルートで実行
./gradlew build
```

ビルドが成功すると、以下の場所に jar ファイルが生成されます：
```
build/libs/throwing_disc-1.21.1-0.1.13.jar
```

---

## 🤖 AI 利用に関する開示 / AI Disclosure

本 Mod は、プログラムコードの実装・テクスチャ画像の作成・ドキュメント作成において **生成AI（AI支援）** を使用して制作されています。

- **企画・管理・テクスチャ監修**: Ruby
- **AI 支援 (AI-Assisted / AI-Generated)**: プログラムコードの実装、テクスチャ作成、ドキュメント生成

This project was developed with the assistance of Generative AI for code implementation, texture assets, and documentation.
- **Project Lead & Supervision**: Ruby
- **AI-Assisted / AI-Generated**: Code implementation, texture creation, and documentation.

---

## 📜 ライセンス / License

本 Mod は **[MIT License](LICENSE)** の下で公開されています。  
- 制作: **Ruby**（AI-Assisted）
