# Ruby's Throwing Disc — Changelog / チェンジログ

## Changelog — changes since the first release (2026-11-17)

- **Many more integrations**: discs for Caverns and Chasms, Oreganized, Deep Aether, Just Dire Things, The Undergarden, Unusual End, Eternal Starlight, Malum and Occultism on both versions — plus Behemoths, Aether Redux, Aether Lost Content, Mana and Artifice, Blaze Gear and Blue Skies on 1.20.1, and Astral Sorcery on 1.21.1 — **67 discs (1.20.1) / 62 discs (1.21.1)** in total, each keeping its home material's traits. The Phoenix disc cannot be crafted: like Lost Content's own Phoenix tools, it is found in Platinum Dungeon treasure chests. On 1.20.1, the Reinforced Echo disc was removed (Deeper and Darker's 1.20.1 release has no Reinforced Echo Shard); it remains on 1.21.1

- **Enderite Mod support**: an Enderite disc (damage 11, durability 2400, fire-resistant) — made by smithing-upgrading the Netherite disc with an Enderite ingot and the Enderite Upgrade Template, exactly like the mod's own gear
- **Flight speed is configurable**: a config option (discSpeed, default 1.0) now sets the discs' flight speed — it was fixed at 1.6 in the first release
- **Spin fixed on centre**: the flying disc now rotates exactly around its own centre (the item model's ground display offset had been shifting the spin axis)

- **Three enchantments of its own**: Delicate (the disc bounces off creatures instead of piercing them), Lingering I–III (+40 ticks of flight time and +13 blocks of range per level), Splitting I–IV (one phantom copy per level in a ±4° fan; copies deal damage but never wear, drop or duplicate the item)
- **Conditional registration**: compat discs are only registered while their home mod is installed — without the mod they do not appear in the creative tab or JEI, and removing a mod removes its discs from existing worlds
- **Texture colours re-matched**: every disc's palette was regenerated from its source material's real texture, matched shade by shade — Gravitite now wears the magenta of enchanted gravitite itself
- **Smoother flight**: the client simulates the disc's flight like vanilla projectiles, so motion and ricochet tilts render continuously instead of stepping between server updates
- **Stuck-in-wall rescue**: a disc that wedges into geometry slips out to the nearest open space (air or liquid) within 3 blocks and returns
- **Mod icon added**: the disc-cluster illustration now shows in the mod list
- **Japanese wording aligned**: the Andesite Alloy disc's Japanese name now matches Create's own （安山岩合金）

---

## チェンジログ — 最初のリリース（2026-11-17）からの変更点

- **連携Modを大量追加**：両版に Caverns and Chasms、Oreganized、Deep Aether、Just Dire Things、The Undergarden、Unusual End、Eternal Starlight、Malum、Occultism。1.20.1 のみに Behemoths、Aether Redux、Aether Lost Content、Mana and Artifice、Blaze Gear、Blue Skies。1.21.1 のみに Astral Sorcery。各ディスクに本家素材の特性も再現。合計 **1.20.1版 67種／1.21.1版 62種**。フェニックスのディスクはクラフト不可で、本家のフェニックスツールと同じくプラチナダンジョンのお宝チェストから入手する。1.20.1 では強化残響のディスクを削除した（Deeper and Darker の 1.20.1 版に強化残響の破片が存在しないため。1.21.1 版には残っている）

- **Enderite Mod に対応**：エンダライトディスク（ダメージ11、耐久2400、耐火）を追加。本家の装備と同じく、ネザライトのディスクをエンダライトインゴットとエンダライト強化テンプレートで鍛冶強化して作る
- **飛翔速度をコンフィグで変更可能にした**：設定項目 discSpeed（デフォルト 1.0）でディスクの飛ぶ速度を変えられるようにした。最初のリリースでは 1.6 固定だった
- **回転の中心を修正**：飛んでいるディスクがちょうど中心で回るようにした（アイテムモデルの地面表示オフセットで回転軸がずれていた）

- **専用エンチャント3種を追加**：繊細（貫通せずクリーチャーに反射）、滞空 I〜III（レベルごとに帰還まで+40 tick・射程+13ブロック）、分裂 I〜IV（レベルごとにファントムコピーが1枚増えて±4°の扇状に飛ぶ。コピーはダメージを与えるが、アイテムを削らず・落とさず・増殖もしない）
- **条件付き登録**：連携ディスクは相手の Mod が入っている時だけ登録されるようにした。Mod が無い環境ではクリエイティブタブや JEI に出ず、Mod を抜くと既存ワールドからもそのディスクは消える
- **テクスチャの色彩を全面見直し**：全ディスクのパレットを元素材の原画から階調ごとに写し取る方式で再生成した。グラビタイトはエンチャントされたグラビタイト本体のマゼンタ色になった
- **飛翔の描写を連続化**：クライアント側でもバニラの飛び道具と同様に飛行を再現し、サーバー更新ごとのカクつきと反射時の傾きの遅れを解消
- **壁埋まり対策**：埋まって動けなくなったディスクが、半径3以内の一番近い空白（液体も可）へ抜け出して帰還するようにした
- **Modアイコンを追加**：ディスク集合イラストが Mod 一覧に表示されるようにした
- **日本語表記の統一**：安山岩合金ディスクの日本語名を Create 本体の表記に合わせた
