package top.fancyflow.stardewaccessories.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.fancyflow.stardewaccessories.registry.ModItems;

// 材料的世界获取：
//   流明尘     —— 挖萤石小概率掉落
//   史莱姆结晶 —— 玩家击杀史莱姆/岩浆怪小概率掉落
//   血之精华   —— 玩家击杀蝙蝠/幻翼小概率掉落
// 概率统一走 Config；只在服务端执行，且要求玩家击杀，避免刷怪塔的自动击杀也产出。
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class MaterialDropEvents {

    // 挖萤石：小概率掉流明尘（精准采集/时运都不影响这个额外掉落）
    @SubscribeEvent
    private static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) {
            return;
        }
        if (!event.getState().is(Blocks.GLOWSTONE)) {
            return;
        }
        if (serverLevel.random.nextDouble() >= Config.LUMEN_DUST_GLOWSTONE_CHANCE.get()) {
            return;
        }
        Block.popResource(serverLevel, event.getPos(), new ItemStack(ModItems.LUMEN_DUST.get()));
    }

    // 击杀生物：史莱姆 -> 史莱姆结晶；蝙蝠 / 幻翼 -> 血之精华
    @SubscribeEvent
    private static void onLivingDeath(LivingDeathEvent event) {
        // 击杀者必须是玩家（弹射物击杀时 getEntity() 返回发射者）
        if (!(event.getSource().getEntity() instanceof ServerPlayer)) {
            return;
        }

        var entity = event.getEntity();
        ItemStack drop = null;
        if (entity instanceof Slime) {
            // 岩浆怪继承自 Slime，一个判断覆盖两者
            if (entity.getRandom().nextDouble() < Config.SLIME_CRYSTAL_SLIME_CHANCE.get()) {
                drop = new ItemStack(ModItems.SLIME_CRYSTAL.get());
            }
        } else if (entity instanceof Bat) {
            if (entity.getRandom().nextDouble() < Config.BLOOD_ESSENCE_BAT_CHANCE.get()) {
                drop = new ItemStack(ModItems.BLOOD_ESSENCE.get());
            }
        } else if (entity instanceof Phantom) {
            if (entity.getRandom().nextDouble() < Config.BLOOD_ESSENCE_PHANTOM_CHANCE.get()) {
                drop = new ItemStack(ModItems.BLOOD_ESSENCE.get());
            }
        }

        if (drop != null) {
            entity.spawnAtLocation(drop);
        }
    }
}
