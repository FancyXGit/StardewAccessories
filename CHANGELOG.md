# Changelog

All notable changes to Stardew Accessories are documented in this file.
The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

本文件记录 Stardew Accessories 的重要变更，格式参考
[Keep a Changelog](https://keepachangelog.com/zh-CN/1.1.0/) 与
[语义化版本](https://semver.org/lang/zh-CN/)。

---

## [1.0.0-beta.1] - 2026-09-30

First formal release (Beta). Ports the accessory system of *Stardew Valley* to Minecraft.

首个正式发布版本（Beta）。将《星露谷物语》的饰品系统带入 Minecraft。

### Added / 新增

**Rings / 戒指（17 枚）**

- Stat rings: Ruby Ring (+10% attack damage), Emerald Ring (+10% attack speed),
  Jade Ring (+10% critical damage), Aquamarine Ring (+5% critical chance),
  Topaz Ring (+6 armor), Amethyst Ring (+1 attack knockback).
- Utility rings: Small Glow Ring and Glow Ring (dynamic lighting),
  Small Magnet Ring and Magnet Ring (pull nearby dropped items).
- Combat rings: Slime Charmer Ring (immunity to slime and magma cube damage),
  Warrior Ring (chance of Warrior Energy on kill), Vampire Ring (heal on kill),
  Savage Ring (speed boost after a kill), Ring of Yoba (chance of Yoba's Blessing when hurt),
  Burglar's Ring (extra monster loot), Iridium Band (Glow + Magnet + Ruby Ring combined).
- 属性类：红宝石戒指（+10% 攻击伤害）、绿宝石戒指（+10% 攻速）、翡翠戒指（+10% 暴击伤害）、
  海蓝宝石戒指（+5% 暴击率）、黄水晶戒指（+6 护甲）、紫水晶戒指（+1 击退）。
- 功能类：小型光辉戒指与光辉戒指（动态照明）、小型磁铁戒指与磁铁戒指（吸取附近掉落物）。
- 战斗类：史莱姆克星戒指（免疫史莱姆与岩浆怪伤害）、战士戒指（击杀概率获得战士能量）、
  吸血戒指（击杀回血）、野蛮人戒指（击杀后加速）、由巴的戒指（受伤概率获得由巴的祝福）、
  窃贼戒指（额外战利品）、铱环（光辉 + 磁铁 + 红宝石三合一）。

**Systems / 系统**

- Two custom attributes: Critical Chance and Critical Damage.
- Two custom effects: Warrior Energy and Yoba's Blessing.
- Curios `ring` slot support (2 slots by default); all rings are unstackable.
- 25 options in `config/stardewaccessories-common.toml` to tune every effect and drop chance.
- English and Simplified Chinese localization.
- Dedicated creative tab containing all rings, materials and ores.
- 两个自定义属性：暴击率与暴击伤害。
- 两个自定义效果：战士能量与由巴的祝福。
- 支持 Curios 的 `ring` 槽位（默认 2 格），所有戒指不可堆叠。
- `config/stardewaccessories-common.toml` 提供 25 项数值配置，可调整全部效果与掉落概率。
- 内置英文与简体中文两种语言。
- 独立的创造模式页签，包含全部戒指、材料与矿石。

**Materials & Ores / 材料与矿石**

- Gems: Ruby, Emerald, Jade, Aquamarine, Topaz, Amethyst.
- Ring blanks: Ring Blank, Lumen Ring Blank, Metal Ring Blank, Dark Alloy Ring Blank.
- Crafted materials: Lumen Dust → Lumenite; Magnet Fragments → Magnet;
  Slime Crystal; Blood Essence; Starshard → Starshard Ingot.
- New ores with world generation: Starshard Ore (stone and deepslate),
  Prism Ore (stone and deepslate).
- Material sources: Lumen Dust from glowstone, Magnet Fragments from mineshaft and dungeon chests,
  Slime Crystal from slimes and magma cubes, Blood Essence from bats and phantoms.
- 宝石：红宝石、绿宝石、翡翠、海蓝宝石、黄水晶、紫水晶。
- 戒圈：空白戒指、流明空白戒指、金属空白戒指、暗合金空白戒指。
- 合成材料：流明尘 → 流明晶；磁石碎块 → 磁石；史莱姆结晶；血之精华；星之碎片 → 星陨锭。
- 新增带世界生成的矿石：星陨矿石（石质与深板岩）、彩晶矿石（石质与深板岩）。
- 材料获取途径：流明尘来自萤石、磁石碎块来自废弃矿井/地牢箱子、
  史莱姆结晶来自史莱姆与岩浆怪、血之精华来自蝙蝠与幻翼。

### Dependencies / 依赖

- Required / 必需：Curios
- Optional / 可选：LambDynamicLights (dynamic lighting for the glow rings / 光辉戒指的动态照明)

[1.0.0-beta.1]: https://github.com/FancyXGit/StardewAccessories/releases/tag/v1.0.0-beta.1
