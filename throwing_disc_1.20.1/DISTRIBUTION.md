# Ruby's Throwing Disc — 配布ページ用テキスト

CurseForge / Modrinth の説明文としてそのまま使える文面です。

---

## English

### Ruby's Throwing Disc

**The Throwing Disc is coming to Minecraft in the first 2027 game drop. This mod brings it to 1.20.1 (Forge) and 1.21.1 (NeoForge) today.**

Throw the disc and it flies straight with no gravity, **pierces** every enemy lined up in its path, **ricochets** off solid blocks — each bounce sends it farther and faster — and then **returns to the hand that threw it**, hitting enemies again on the way back. It can be thrown from either hand, needs no charging, no cooldown and no ammo, and after 3 seconds it always comes back. Decorated pots shatter and chorus flowers drop when hit, just like with arrows. Flight is simulated on the client too, so the disc glides smoothly, and a disc that wedges itself into a wall slips out to the nearest open space — air or liquid — and comes home.

**33 discs (1.20.1) / 36 discs (1.21.1):**

- Vanilla: Copper, Iron, Golden, Diamond, Netherite
- Enderite Mod: Enderite (smithing upgrade of the Netherite disc, with the Enderite Upgrade Template — just like the mod's own gear)
- Twilight Forest: Ironwood, Steeleaf, Knightmetal, Fiery
- The Aether: Ambrosium, Zanite, Gravitite
- Create: Andesite Alloy, Zinc, Brass
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

Every disc's colours are sampled from its source material's own texture, matched shade by shade, so each disc reads as the metal, gem or alloy it is made from. The flight speed can be adjusted in the config (discSpeed, default 1.0).

**Credits:** Mod development and code created with the assistance of Muse AI. All textures created by Ruby.
**License:** MIT

---

## 日本語

### Ruby's Throwing Disc

**2027年第1ドロップで実装予定の新武器「投擲ディスク（Throwing Disc）」を、1.20.1（Forge）と 1.21.1（NeoForge）で先行して遊べるようにする Mod です。**

投げたディスクは重力を受けずに直進し、並んだ敵を**貫通**、固いブロックに当たると**跳ね返って**（跳ねるたびに射程と速度が伸びます）、最後は**投げた手に戻ってきます**（帰り道でも敵に当たります）。どちらの手からでも投げられ、溜め・クールダウン・弾は不要で、3秒経つと必ず戻ります。飾り壺は砕け、コーラスフラワーはアイテム化して落ちるなど、飛び道具としての反応もバニラ準拠です。飛翔はクライアント側でも再現されるので滑らかに飛び、壁に埋まってしまったディスクは近くの空白や液体へ抜け出して戻ってきます。

**ディスクは 1.20.1版で33種／1.21.1版で36種：**

- バニラ：銅、鉄、金、ダイヤモンド、ネザライト
- Enderite Mod：エンダライト（本家の装備と同じく、ネザライトのディスクをエンダライト強化テンプレートで鍛冶強化）
- Twilight Forest：アイアンウッド、スティールリーフ、ナイトメタル、ファイアリー
- The Aether：アンブロシウム、ザナイト、グラビタイト
- Create：安山岩合金、亜鉛、真鍮
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
- **グラビタイト**：命中した相手を真上に打ち上げる
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

各ディスクの色は、元素材そのもののテクスチャから階調ごとに写し取ってあるので、金属・宝石・合金それぞれの色味そのままに見えます。飛ぶ速度はコンフィグ（discSpeed、デフォルト1.0）で調整できます。

**クレジット：** 開発・コードは Muse AI の支援による。テクスチャはすべて Ruby 制作。
**ライセンス：** MIT

---

## Changelog — changes since the first release (2026-11-17)

- **Three enchantments of its own**: Delicate (the disc bounces off creatures instead of piercing them), Lingering I–III (+40 ticks of flight time and +13 blocks of range per level), Splitting I–IV (one phantom copy per level in a ±4° fan; copies deal damage but never wear, drop or duplicate the item)
- **Conditional registration**: compat discs are only registered while their home mod is installed — without the mod they do not appear in the creative tab or JEI, and removing a mod removes its discs from existing worlds
- **Texture colours re-matched**: every disc's palette was regenerated from its source material's real texture, matched shade by shade — Gravitite now wears the magenta of enchanted gravitite itself
- **Smoother flight**: the client simulates the disc's flight like vanilla projectiles, so motion and ricochet tilts render continuously instead of stepping between server updates
- **Stuck-in-wall rescue**: a disc that wedges into geometry slips out to the nearest open space (air or liquid) within 3 blocks and returns
- **Mod icon added**: the disc-cluster illustration now shows in the mod list
- **Japanese wording aligned**: the Andesite Alloy disc's Japanese name now matches Create's own （安山岩合金）

---

## チェンジログ — 最初のリリース（2026-11-17）からの変更点

- **専用エンチャント3種を追加**：繊細（貫通せずクリーチャーに反射）、滞空 I〜III（レベルごとに帰還まで+40 tick・射程+13ブロック）、分裂 I〜IV（レベルごとにファントムコピーが1枚増えて±4°の扇状に飛ぶ。コピーはダメージを与えるが、アイテムを削らず・落とさず・増殖もしない）
- **条件付き登録**：連携ディスクは相手の Mod が入っている時だけ登録されるようにした。Mod が無い環境ではクリエイティブタブや JEI に出ず、Mod を抜くと既存ワールドからもそのディスクは消える
- **テクスチャの色彩を全面見直し**：全ディスクのパレットを元素材の原画から階調ごとに写し取る方式で再生成した。グラビタイトはエンチャントされたグラビタイト本体のマゼンタ色になった
- **飛翔の描写を連続化**：クライアント側でもバニラの飛び道具と同様に飛行を再現し、サーバー更新ごとのカクつきと反射時の傾きの遅れを解消
- **壁埋まり対策**：埋まって動けなくなったディスクが、半径3以内の一番近い空白（液体も可）へ抜け出して帰還するようにした
- **Modアイコンを追加**：ディスク集合イラストが Mod 一覧に表示されるようにした
- **日本語表記の統一**：安山岩合金ディスクの日本語名を Create 本体の表記に合わせた
