package top.fancyflow.stardewaccessories.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.Enemy;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.fancyflow.stardewaccessories.registry.ModEffects;
import top.fancyflow.stardewaccessories.registry.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

// 「击杀怪物」类戒指的统一入口：战士 / 吸血 / 野蛮人共用同一套
// 「击杀者是玩家 + 目标是敌对生物 + 查 Curios 佩戴」判定，各自效果分在下面的私有方法里。
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class MonsterKillEvents {

    @SubscribeEvent
    private static void onLivingDeath(LivingDeathEvent event) {
        // 只对敌对生物生效（史莱姆/岩浆怪等实现了 Enemy，非敌对生物不触发）
        if (!(event.getEntity() instanceof Enemy)) {
            return;
        }

        // 击杀者必须是玩家；用弹射物击杀时 getEntity() 返回发射者
        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) {
            return;
        }

        // Curios 背包只取一次，三个戒指共用
        CuriosApi.getCuriosInventory(player).ifPresent(inventory -> {
            // 战士戒指：戴几枚就 roll 几次概率
            int warriorRings = inventory.findCurios(ModItems.WARRIOR_RING.get()).size();
            if (warriorRings > 0) {
                applyWarriorEnergy(player, warriorRings);
            }

            // 吸血戒指：每枚各回一次，多枚叠加
            int vampireRings = inventory.findCurios(ModItems.VAMPIRE_RING.get()).size();
            if (vampireRings > 0) {
                healFromVampireRing(player, vampireRings);
            }

            // 野蛮人戒指：必定触发，第二枚无额外收益，只判是否佩戴
            if (inventory.findFirstCurio(ModItems.SAVAGE_RING.get()).isPresent()) {
                applyAdrenalineRush(player);
            }
        });
    }

    // 战士戒指：按概率获得"战士能量"（提高攻击力），逐枚 roll、命中即停
    private static void applyWarriorEnergy(ServerPlayer player, int ringCount) {
        double chance = Config.WARRIOR_RING_PROC_CHANCE.get();
        boolean triggered = false;
        for (int i = 0; i < ringCount; i++) {
            if (player.getRandom().nextDouble() < chance) {
                triggered = true;
                break;
            }
        }
        if (!triggered) {
            return;
        }

        // amplifier 固定 0，效果不自叠加
        player.addEffect(new MobEffectInstance(
                ModEffects.WARRIOR_ENERGY, Config.WARRIOR_ENERGY_DURATION.get(), 0, false, true, true));
    }

    // 吸血戒指：恢复生命值，多枚叠加；满血时 heal 会自动封顶，不会溢出
    private static void healFromVampireRing(ServerPlayer player, int ringCount) {
        player.heal((float) (Config.VAMPIRE_RING_HEAL_AMOUNT.get() * ringCount));
    }

    // 野蛮人戒指：必定获得短时间速度提升；amplifier 走配置，连续击杀只刷新时长
    private static void applyAdrenalineRush(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SPEED,
                Config.SAVAGE_RING_SPEED_DURATION.get(),
                Config.SAVAGE_RING_SPEED_AMPLIFIER.get(),
                false, true, true));
    }
}
