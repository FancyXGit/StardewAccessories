# AGENTS.md

本文件给在此仓库工作的 AI 代理/开发者提供项目概览与实现约定。所有内容以当前代码为准。

## 项目概览

**Stardew Accessories**：以《星露谷物语》饰品为原型的 NeoForge 模组。核心玩法是各种**戒指**，
佩戴后提供属性加成或特殊效果（发光、吸物品、元素免疫等），另有宝石、戒圈等合成材料。
物品栏采用 Curios 的 `ring` 槽（默认 2 格）。

- 版本：`0.2.0-alpha.1`，Minecraft `1.21.1` / NeoForge `21.1.252` / Java 21
- 必需前置：**Curios** `9.5.1+1.21.1`（物品装备与槽位）
- 软依赖：**LambDynamicLights** `4.8.11`（仅辉光戒指的动态照明用到；未安装不崩溃、不发光）
- 开发环境：**JEI**（仅用于开发时查看配方，不打包）
- 构建：ModDevGradle `2.0.147`

## 常用命令

```bat
.\gradlew.bat runData     :: 数据生成，产物写入 src/generated/resources
.\gradlew.bat build       :: 编译 + 打包
.\gradlew.bat runClient   :: 启动开发客户端
.\gradlew.bat runServer   :: 启动开发服务端（--nogui）
```

## 目录结构

```
src/main/java/top/fancyflow/stardewaccessories/
  StardewAccessories.java          模组入口：注册各注册表 + 注册配置文件
  registry/
    ModItems.java                  所有物品（材料 + 戒指）注册
    ModBlocks.java                 方块注册（目前为空）
    ModAttributes.java             自定义属性注册（crit_chance / crit_damage）
    ModCreativeTabs.java           创造页签 stardewaccessories
  item/                            物品/戒指类（DescribedItem / MaterialItem 及各种戒指）
  event/                           服务端游戏事件监听（磁铁、暴击、史莱姆克星、材料掉落）
  client/dynamiclights/            辉光戒指的 LambDynamicLights 软依赖实现
  config/Config.java               所有数值集中在此，生成 config/stardewaccessories-common.toml
  datagen/                         数据生成器（见下）
src/main/resources/
  assets/stardewaccessories/textures/item/<id>.png   手绘纹理
  data/stardewaccessories/curios/{slots,entities}/*.json  Curios 槽位与实体定义
src/generated/resources/           由 runData 生成，勿手改
src/main/templates/                mods.toml 模板（构建时展开属性）
```

`src/generated/resources` 已通过 `build.gradle` 的 `sourceSets.main.resources.srcDir` 打进资源。

## 数据生成（datagen）

入口：`datagen/DataGenerators.java`（监听 `GatherDataEvent`）。新增生成器要在这里 `addProvider`。
运行 `runData` 会重写 `src/generated/resources` 下的文件，**不要手改生成物**，改对应的 Provider 源码再跑。

| Provider | 生成内容 |
|---|---|
| `ModItemModels` | `models/item/<id>.json`（普通物品用 `basicItem`） |
| `ModBlockStates` | 方块 blockstate/model（目前无方块） |
| `ModLanguageChinese` / `ModLanguageEnglish` | `lang/zh_cn.json` / `en_us.json` |
| `ModRecipes` | `recipe/*.json` + 配方解锁进度 `advancement/recipes/**` |
| `ModLootTables` | 方块掉落表 |
| `ModItemTags` / `ModBlockTags` | Curios 戒指标签等 |
| `ModWorldGen` | 矿石世界生成（configured/placed/biome_modifier） |

## 核心约定

- **注释一律用中文**，风格保持现有文件。
- **物品 id 必须与纹理文件名一致**：`textures/item/<id>.png`。新物品先放纹理，id 对齐才能复用。
- **Tooltip 文案 key**：
  - 作用行：`tooltip.stardewaccessories.<id>`（放进 Curios“佩戴戒指时”作用区，蓝色）
  - 风味描述：`tooltip.stardewaccessories.<id>.desc`（物品备注，灰色）
  - 获取方式：`tooltip.stardewaccessories.<id>.source`（材料备注，灰色；用 `item/MaterialItem`）
- **装备进 Curios 戒指槽**必须在 `ModItemTags` 里 `tag(CuriosTags.RING).add(...)`。
- **数值**（倍率、半径、亮度等）统一放 `Config.java`，用 `ModConfigSpec.DoubleValue/IntValue` 定义，运行时通过 `Config.XXX.get()` 读取。
- **物品注册**集中在 `ModItems`，按“材料 / 戒指”分组用中文注释。戒指默认 `new Item.Properties().stacksTo(1)`。
- 创造栏在 `ModCreativeTabs` 按“戒指 / 标志 / 材料”顺序摆放。
- 中文标识符风格：ModItems 常量用英文，注释与 lang 用中文。

## 新增一枚戒指的完整清单

1. `registry/ModItems.java`：注册（材料类用 `registerSimpleItem`，戒指用 `registerItem(..., stacksTo(1))`）。
2. `item/`：写物品类（视效果决定继承哪个基类，见下）。
3. 需要服务端效果 → `event/` 新增监听类；需要客户端效果 → `client/`。
4. `config/Config.java`：加数值配置项。
5. `datagen/ModRecipes.java`：加合成配方（多为 `ShapelessRecipeBuilder`）。
6. `datagen/ModItemModels.java`：`basicItem(...)`。
7. `datagen/ModItemTags.java`：`tag(CuriosTags.RING).add(...)`。
8. `registry/ModCreativeTabs.java`：`output.accept(...)`。
9. `datagen/ModLanguageChinese.java` + `ModLanguageEnglish.java`：物品名、作用行、风味描述。
10. 放好 `textures/item/<id>.png`。
11. `.\gradlew.bat runData` 再 `.\gradlew.bat build`。

## 几种典型戒指的实现方式

所有戒指都继承 `item/DescribedItem`（在物品备注里加一行灰色风味描述），
并实现 Curios 的 `ICurioItem` 以放进戒指槽。

### 1. 纯属性戒指 —— 红宝石戒指（`RubyRing`）

在 Curios 的属性回调里返回修饰符，Curios 负责应用与移除：

```java
public class RubyRing extends DescribedItem implements ICurioItem {
    public RubyRing(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.ruby_ring.desc");
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(
            SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers =
                ICurioItem.super.getAttributeModifiers(slotContext, id, stack);
        // 用槽位索引生成唯一 id，多个同类槽位的加成才不会互相覆盖
        ResourceLocation modifierId = ResourceLocation.fromNamespaceAndPath(
                StardewAccessories.MODID,
                "ruby_ring_damage_" + slotContext.identifier() + "_" + slotContext.index());
        modifiers.put(Attributes.ATTACK_DAMAGE,
                new AttributeModifier(modifierId, Config.RUBY_RING_ATTACK_DAMAGE.get(),
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifiers;
    }
}
```

同类：黄水晶戒指（`Attributes.ARMOR`，`ADD_VALUE`）、绿宝石戒指（攻速）、紫水晶戒指（击退）。

### 2. 自定义属性 + 事件 —— 暴击（海蓝宝石 / 翡翠）

- 自定义属性在 `registry/ModAttributes.java` 用 `PercentageAttribute` 注册，
  并**必须**在 `EntityAttributeModificationEvent` 里 `event.add(EntityType.PLAYER, ...)`，
  否则玩家身上没有该属性，修饰符无效。
- 戒指类照第 1 种写法把属性挂上去（`AQUAMARINE_RING` 加 `CRIT_CHANCE`，`JADE_RING` 加 `CRIT_DAMAGE`）。
- 原版事件里读取属性值并干预判定：`event/CriticalHitEvents.java` 监听 `CriticalHitEvent`，
  非原版暴击时按 `CRIT_CHANCE` roll 一次，命中后再叠加 `CRIT_DAMAGE` 到暴击倍率。

### 3. Curios 佩戴检测 + 服务端事件 —— 磁铁戒指 / 史莱姆克星戒指

模式：事件触发 → 判断目标是否玩家 → 用 Curios 查是否佩戴某戒指 → 执行效果。
`CuriosApi.getCuriosInventory(player).map(inv -> inv.findFirstCurio(item).isPresent())`。

**磁铁戒指**（`event/MagnetRingEvents.java`，`PlayerTickEvent.Post`，服务端）：
每 tick 把范围内 `ItemEntity` 的速度指向玩家（跳过刚丢出/自己丢出的），由原版拾取判定收走；
范围取所戴戒指配置值的较大者。

**史莱姆克星戒指**（`event/SlimeCharmerRingEvents.java`，`LivingIncomingDamageEvent`）：
取消来自史莱姆/岩浆怪的伤害，等于同时免疫伤害与击退。

```java
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class SlimeCharmerRingEvents {
    @SubscribeEvent
    private static void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        // MagmaCube 继承自 Slime，一个判断覆盖两者
        if (!(event.getSource().getDirectEntity() instanceof Slime)) return;
        boolean wearing = CuriosApi.getCuriosInventory(player)
                .map(inv -> inv.findFirstCurio(ModItems.SLIME_CHARMER_RING.get()).isPresent())
                .orElse(false);
        if (wearing) event.setCanceled(true);
    }
}
```

对应物品类 `item/SlimeCharmerRing.java` 只做 tooltip：重写 `getAttributesTooltip`，
往 Curios“佩戴戒指时”作用区加一行蓝字（参考 `AbstractMagnetRing` / `AbstractGlowRing`）。

> 效果行放作用区的通用写法见 `item/AbstractMagnetRing.java`、`item/AbstractGlowRing.java`。

### 4. 客户端软依赖 —— 辉光戒指（`GlowRing` / `SmallGlowRing`）

- 物品类继承 `AbstractGlowRing`，只负责 tooltip（含“需要安装 LambDynamicLights”提示）。
- 实际发光在 `client/dynamiclights/`：
  - `StardewDynamicLightsInitializer`：LambDynamicLights 的入口点，**只有装了才会被加载**，把光源管理器交给 `GlowRingLightManager`。
  - `GlowRingLightManager`：`ClientTickEvent.Post` 每 tick 检查玩家戴的发光戒指，登记/更新/移除光源；**刻意不直接引用 LambDynamicLights 类型**（用 `Object` 保存），避免未安装时类加载崩溃。
  - `GlowRingLightHelper`：真正调用 LambDynamicLights API 的薄封装，仅在 `manager != null` 时执行。
  - `PlayerGlowLight`：以玩家胸口为中心的点光源，随距离衰减，玩家移动/亮度变化时通知刷新。
- 亮度数值来自 `Config.SMALL_GLOW_RING_LIGHT` / `GLOW_RING_LIGHT`。

**软依赖的关键**：入口点由一个“只有依赖存在时才会加载”的类触发，把对依赖 API 的直接引用隔离在最内层几个类里，
外层逻辑用 `Object` 传值。新增其他软依赖时可照此结构。

## 物品/材料与配方

- 材料用 `ModItems.registerSimpleItem`；需要风味描述的用 `DescribedItem`；
  需要“获取方式”备注的用 `MaterialItem`（构造时传 `.source` 的 lang key，可选再传风味 `.desc`）。
- 配方几乎都是无序合成：`ShapelessRecipeBuilder.shapeless(category, result).requires(...).unlockedBy(...).save(output)`。
- **同产物多条配方**（升级路线）时，给后一条指定独立 id 以免覆盖，例如：
  `save(recipeOutput, ResourceLocation.fromNamespaceAndPath(MODID, "magnet_ring_from_small_magnet_ring"))`。
- 材料获取方式（实现见 `event/MaterialDropEvents.java`、`event/MaterialChestLootEvents.java`，概率在 `Config`）：
  - 流明尘：挖萤石小概率掉落；
  - 磁石碎块：废弃矿井 / 地牢箱子概率出现（`LootTableLoadEvent` 运行时注入，非 datagen）；
  - 史莱姆结晶：玩家击杀史莱姆 / 岩浆怪小概率掉落；
  - 血之精华：玩家击杀蝙蝠 / 幻翼小概率掉落。

## 世界生成

`datagen/ModWorldGen.java` 已搭好矿石 datagen 框架，但 `ORE_VEINS` 列表目前为空。
新增矿石只需往 `ORE_VEINS` 加一条记录，会自动生成 configured feature / placed feature / biome modifier 三个 JSON。
