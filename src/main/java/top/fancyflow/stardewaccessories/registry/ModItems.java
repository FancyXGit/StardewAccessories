package top.fancyflow.stardewaccessories.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.item.AmethystRing;
import top.fancyflow.stardewaccessories.item.AquamarineRing;
import top.fancyflow.stardewaccessories.item.DescribedItem;
import top.fancyflow.stardewaccessories.item.EmeraldRing;
import top.fancyflow.stardewaccessories.item.GlowRing;
import top.fancyflow.stardewaccessories.item.JadeRing;
import top.fancyflow.stardewaccessories.item.MagnetRing;
import top.fancyflow.stardewaccessories.item.RubyRing;
import top.fancyflow.stardewaccessories.item.SavageRing;
import top.fancyflow.stardewaccessories.item.SlimeCharmerRing;
import top.fancyflow.stardewaccessories.item.SmallGlowRing;
import top.fancyflow.stardewaccessories.item.SmallMagnetRing;
import top.fancyflow.stardewaccessories.item.TopazRing;
import top.fancyflow.stardewaccessories.item.VampireRing;
import top.fancyflow.stardewaccessories.item.WarriorRing;

// 所有物品的注册集中在这里；新增物品时在对应分组下加一行
public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(StardewAccessories.MODID);

    // 鸡图标
    public static final DeferredItem<Item> CHICKEN = ITEMS.registerSimpleItem("chicken", new Item.Properties());

    // 材料
    // 空白戒指
    public static final DeferredItem<DescribedItem> RING_BLANK = ITEMS.registerItem("ring_blank",
            properties -> new DescribedItem(properties, "tooltip.stardewaccessories.ring_blank.desc"),
            new Item.Properties());
    // 红宝石
    public static final DeferredItem<Item> RUBY = ITEMS.registerSimpleItem("ruby", new Item.Properties());
    // 绿宝石
    public static final DeferredItem<Item> EMERALD = ITEMS.registerSimpleItem("emerald", new Item.Properties());
    // 翡翠
    public static final DeferredItem<Item> JADE = ITEMS.registerSimpleItem("jade", new Item.Properties());
    // 海蓝宝石
    public static final DeferredItem<Item> AQUAMARINE = ITEMS.registerSimpleItem("aquamarine", new Item.Properties());
    // 黄水晶
    public static final DeferredItem<Item> TOPAZ = ITEMS.registerSimpleItem("topaz", new Item.Properties());
    // 紫水晶
    public static final DeferredItem<Item> AMETHYST = ITEMS.registerSimpleItem("amethyst", new Item.Properties());
    // 流明尘（暂时只能从创造栏获取，来源以后再定）
    public static final DeferredItem<Item> LUMEN_DUST = ITEMS.registerSimpleItem("lumen_dust", new Item.Properties());
    // 流明晶（由 4 个流明尘合成）
    public static final DeferredItem<Item> LUMENITE = ITEMS.registerSimpleItem("lumenite", new Item.Properties());
    // 流明空白戒指（材料）
    public static final DeferredItem<DescribedItem> LUMENITE_RING_BLANK = ITEMS.registerItem("lumenite_ring_blank",
            properties -> new DescribedItem(properties, "tooltip.stardewaccessories.lumenite_ring_blank.desc"),
            new Item.Properties());
    // 金属空白戒指（材料）
    public static final DeferredItem<DescribedItem> METAL_RING_BLANK = ITEMS.registerItem("metal_ring_blank",
            properties -> new DescribedItem(properties, "tooltip.stardewaccessories.metal_ring_blank.desc"),
            new Item.Properties());
    // 暗合金空白戒指（材料，由空白戒指 + 下界合金锭合成）
    public static final DeferredItem<DescribedItem> DARK_ALLOY_RING_BLANK = ITEMS.registerItem("dark_alloy_ring_blank",
            properties -> new DescribedItem(properties, "tooltip.stardewaccessories.dark_alloy_ring_blank.desc"),
            new Item.Properties());
    // 磁石碎块（暂时只能从创造栏获取，来源以后再定）
    public static final DeferredItem<Item> MAGNET_FRAGMENTS = ITEMS.registerSimpleItem("magnet_fragments", new Item.Properties());
    // 磁石（由 4 个磁石碎块合成）
    public static final DeferredItem<Item> MAGNET = ITEMS.registerSimpleItem("magnet", new Item.Properties());
    // 史莱姆结晶（暂时只能从创造栏获取，来源以后再定）
    public static final DeferredItem<Item> SLIME_CRYSTAL = ITEMS.registerSimpleItem("slime_crystal", new Item.Properties());
    // 血之精华（暂时只能从创造栏获取，来源以后再定）
    public static final DeferredItem<Item> BLOOD_ESSENCE = ITEMS.registerSimpleItem("blood_essence", new Item.Properties());

    // 矿石方块（物品形式，实际方块在 ModBlocks）
    public static final DeferredItem<BlockItem> STARSHARD_ORE = ITEMS.registerSimpleBlockItem(ModBlocks.STARSHARD_ORE);
    public static final DeferredItem<BlockItem> DEEPSLATE_STARSHARD_ORE = ITEMS.registerSimpleBlockItem(ModBlocks.DEEPSLATE_STARSHARD_ORE);
    public static final DeferredItem<BlockItem> PRISM_ORE = ITEMS.registerSimpleBlockItem(ModBlocks.PRISM_ORE);
    public static final DeferredItem<BlockItem> DEEPSLATE_PRISM_ORE = ITEMS.registerSimpleBlockItem(ModBlocks.DEEPSLATE_PRISM_ORE);
    // 星之碎片（星陨矿石掉落）
    public static final DeferredItem<Item> STARSHARD = ITEMS.registerSimpleItem("starshard", new Item.Properties());
    // 星陨锭（由星之碎片熔炼）
    public static final DeferredItem<Item> STARSHARD_INGOT = ITEMS.registerSimpleItem("starshard_ingot", new Item.Properties());

    // 戒指
    // 红宝石戒指：加10%伤害；不可堆叠
    public static final DeferredItem<RubyRing> RUBY_RING = ITEMS.registerItem("ruby_ring", RubyRing::new, new Item.Properties().stacksTo(1));
    // 绿宝石戒指：加10%攻速；不可堆叠
    public static final DeferredItem<EmeraldRing> EMERALD_RING = ITEMS.registerItem("emerald_ring", EmeraldRing::new, new Item.Properties().stacksTo(1));
    // 翡翠戒指：加10%暴击伤害；不可堆叠
    public static final DeferredItem<JadeRing> JADE_RING = ITEMS.registerItem("jade_ring", JadeRing::new, new Item.Properties().stacksTo(1));
    // 海蓝宝石戒指：加5%暴击率；不可堆叠
    public static final DeferredItem<AquamarineRing> AQUAMARINE_RING = ITEMS.registerItem("aquamarine_ring", AquamarineRing::new, new Item.Properties().stacksTo(1));
    // 黄水晶戒指：加6点盔甲；不可堆叠
    public static final DeferredItem<TopazRing> TOPAZ_RING = ITEMS.registerItem("topaz_ring", TopazRing::new, new Item.Properties().stacksTo(1));
    // 紫水晶戒指：加1点攻击击退；不可堆叠
    public static final DeferredItem<AmethystRing> AMETHYST_RING = ITEMS.registerItem("amethyst_ring", AmethystRing::new, new Item.Properties().stacksTo(1));
    // 小型光辉戒指：佩戴后客户端动态照明；不可堆叠
    public static final DeferredItem<SmallGlowRing> SMALL_GLOW_RING = ITEMS.registerItem("small_glow_ring", SmallGlowRing::new, new Item.Properties().stacksTo(1));
    // 光辉戒指：佩戴后客户端动态照明（更亮）；不可堆叠
    public static final DeferredItem<GlowRing> GLOW_RING = ITEMS.registerItem("glow_ring", GlowRing::new, new Item.Properties().stacksTo(1));
    // 小型磁铁戒指：佩戴后把附近的掉落物吸向玩家；不可堆叠
    public static final DeferredItem<SmallMagnetRing> SMALL_MAGNET_RING = ITEMS.registerItem("small_magnet_ring", SmallMagnetRing::new, new Item.Properties().stacksTo(1));
    // 磁铁戒指：佩戴后把更大范围内的掉落物吸向玩家；不可堆叠
    public static final DeferredItem<MagnetRing> MAGNET_RING = ITEMS.registerItem("magnet_ring", MagnetRing::new, new Item.Properties().stacksTo(1));
    // 史莱姆克星戒指：佩戴后史莱姆和岩浆怪不再造成伤害；不可堆叠
    public static final DeferredItem<SlimeCharmerRing> SLIME_CHARMER_RING = ITEMS.registerItem("slime_charmer_ring", SlimeCharmerRing::new, new Item.Properties().stacksTo(1));
    // 战士戒指：击杀敌对生物时有概率获得战士能量（提高攻击力）；不可堆叠
    public static final DeferredItem<WarriorRing> WARRIOR_RING = ITEMS.registerItem("warrior_ring", WarriorRing::new, new Item.Properties().stacksTo(1));
    // 吸血戒指：每击杀一个敌对生物恢复少量生命；不可堆叠
    public static final DeferredItem<VampireRing> VAMPIRE_RING = ITEMS.registerItem("vampire_ring", VampireRing::new, new Item.Properties().stacksTo(1));
    // 野蛮人戒指：每击杀一个敌对生物获得短时间速度提升；不可堆叠
    public static final DeferredItem<SavageRing> SAVAGE_RING = ITEMS.registerItem("savage_ring", SavageRing::new, new Item.Properties().stacksTo(1));

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
