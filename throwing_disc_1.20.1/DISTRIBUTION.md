# Ruby's Throwing Disc — 配布ページ用テキスト

CurseForge / Modrinth の説明文としてそのまま使える文面です。

---

## English

### Ruby's Throwing Disc

**The Throwing Disc is coming to Minecraft in the first 2027 game drop. This mod brings it to 1.20.1 (Forge) and 1.21.1 (NeoForge) today.**

Throw the disc and it flies straight with no gravity, **pierces** every enemy lined up in its path, **ricochets** off solid blocks — each bounce sends it farther and faster — and then **returns to the hand that threw it**, hitting enemies again on the way back. It can be thrown from either hand, needs no charging, no cooldown and no ammo, and after 3 seconds it always comes back. Decorated pots shatter and chorus flowers drop when hit, just like with arrows.

**32 discs (1.20.1) / 35 discs (1.21.1):**

- Vanilla: Copper, Iron, Golden, Diamond, Netherite
- Twilight Forest: Ironwood, Steeleaf, Knightmetal, Fiery
- The Aether: Ambrosium, Zanite, Gravitite
- Create: Zinc Alloy, Zinc, Brass
- Mekanism: Osmium, Steel, Refined Glowstone, Refined Obsidian
- Deeper and Darker: Reinforced Echo
- L_Ender's Cataclysm: Black Steel, Ancient Metal, Enderite, Ignitium, Cursium, Witherite
- Ice and Fire: Silver, Dragonbone, Phantasmal, Fire / Ice / Lightning Dragonsteel
- Thaumaturge (1.21.1 only): Alchemical Brass, Thaumium, Void Metal

Every integration is a **soft dependency**: a disc is only registered while its home mod is installed — without the mod, that disc simply does not exist in the game, and the rest runs standalone.

**Each disc keeps its home material's weapon traits:**

- **Ironwood** — extra knockback, like the sword's built-in Knockback I
- **Steeleaf** — kills pay bonus experience, after its sword's built-in Looting II
- **Knightmetal** — +2 damage against armored targets
- **Fiery / Ignitium / Fire Dragonsteel** — sets targets on fire
- **Zanite** — grows stronger the more the disc itself is worn (up to double damage)
- **Gravitite** — launches targets skyward
- **Silver** — 1.5× damage against the undead
- **Dragonbone / Dragonsteel** — 1.5× damage against dragons
- **Reinforced Echo** — +5 damage against sculk creatures, in the manner of Sculk Smite
- **Witherite** — withers what it hits; **Ice Dragonsteel** — slows what it hits
- **Void Metal** — slowly repairs itself while carried, as in Thaumcraft
- **Thaumium** — very high enchantability

**Three enchantments of its own** (plus Unbreaking and Mending):

- **Delicate** — the disc bounces off creatures instead of piercing them, ricocheting from mob to mob
- **Lingering I–III** — stays in the air longer before returning: +2 seconds and +13 blocks of range per level
- **Splitting I–IV** — one extra disc per level, thrown in a fan; the copies vanish when they return

**Credits:** Mod development and code created with the assistance of Muse AI. All textures created by Ruby.
**License:** MIT

---

## 日本語

### Ruby's Throwing Disc

**2027年第1ドロップで実装予定の新武器「投擲ディスク（Throwing Disc）」を、1.20.1（Forge）と 1.21.1（NeoForge）で先行して遊べるようにする Mod です。**

投げたディスクは重力を受けずに直進し、並んだ敵を**貫通**、固いブロックに当たると**跳ね返って**（跳ねるたびに射程と速度が伸びます）、最後は**投げた手に戻ってきます**（帰り道でも敵に当たります）。どちらの手からでも投げられ、溜め・クールダウン・弾は不要で、3秒経つと必ず戻ります。飾り壺は砕け、コーラスフラワーはアイテム化して落ちるなど、飛び道具としての反応もバニラ準拠です。

**ディスクは 1.20.1版で32種／1.21.1版で35種：**

- バニラ：銅、鉄、金、ダイヤモンド、ネザライト
- Twilight Forest：アイアンウッド、スティールリーフ、ナイトメタル、ファイアリー
- The Aether：アンブロシウム、ザナイト、グラヴィタイト
- Create：亜鉛合金、亜鉛、真鍮
- Mekanism：オスミウム、鋼鉄、精製グロウストーン、精製オブシディアン
- Deeper and Darker：強化残響
- L_Ender's Cataclysm：ブラック鋼、古代金属、エンダライト、イグニティウム、カーシウム、ウィザライト
- Ice and Fire：銀、龍骨、幻影、火龍鋼、氷龍鋼、雷龍鋼
- Thaumaturge（1.21.1のみ）：錬金真鍮、ソーミウム、虚無金属

連携はすべて**ソフト依存**です。ディスクは相手の Mod が入っている時だけ登録され、入っていない環境ではそのディスク自体が存在しません。本体は単独で動きます。

**各ディスクには、本家素材の武器特性も再現されています：**

- **アイアンウッド**：ノックバック強化（剣のノックバックI内蔵の再現）
- **スティールリーフ**：キル時に経験値ボーナス（剣のドロップ増加IIの翻案）
- **ナイトメタル**：防具を着た相手に+2ダメージ（装甲特効）
- **ファイアリー／イグニティウム／火龍鋼**：命中時に着火
- **ザナイト**：ディスク自体の耐久が減るほど与ダメージ上昇（最大2倍）
- **グラヴィタイト**：命中した相手を真上に打ち上げる
- **銀**：アンデッドに1.5倍
- **龍骨／龍鋼**：ドラゴンに1.5倍
- **強化残響**：スカルク系の Mob に+5（Sculk Smite の翻案）
- **ウィザライト**：命中時にウィザー付与／**氷龍鋼**：鈍化付与
- **虚無金属**：所持している間に少しずつ自己修復（Thaumcraft 由来）
- **ソーミウム**：エンチャント適性が非常に高い

**専用エンチャント3種**（耐久・修繕も付けられます）：

- **繊細**：クリーチャーを貫通せずに跳ね返り、MobからMobへ反射していく
- **滞空 I〜III**：戻ってくるまでの滞空時間が延びる。レベルごとに+2秒・射程+13ブロック
- **分裂 I〜IV**：レベルごとにディスクが1枚増えて扇状に飛ぶ。増えた分は戻ると消える

**クレジット：** 開発・コードは Muse AI の支援による。テクスチャはすべて Ruby 制作。
**ライセンス：** MIT
