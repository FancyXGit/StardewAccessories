package top.fancyflow.stardewaccessories.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;

// 战士能量：由战士戒指击杀敌对生物时触发，持续期间提高攻击力。
// 攻击加成挂在效果自身上，效果到期/被移除时由 removeAttributeModifiers 清理。
public class WarriorEnergyEffect extends MobEffect {

    // 修饰符 id 固定，保证同一玩家只有一份加成、重复触发不会叠加
    private static final ResourceLocation ATTACK_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath(StardewAccessories.MODID, "warrior_energy_attack");

    public WarriorEnergyEffect() {
        // 有益效果 + 红色（与交叉双剑图标呼应）
        super(MobEffectCategory.BENEFICIAL, 0xB03A30);
    }

    // 效果生效时挂上攻击修饰符；这里才读配置，避开注册阶段配置尚未加载的问题
    @Override
    public void addAttributeModifiers(AttributeMap attributes, int amplifier) {
        super.addAttributeModifiers(attributes, amplifier);

        AttributeInstance attackDamage = attributes.getInstance(Attributes.ATTACK_DAMAGE);
        if (attackDamage != null) {
            // 先移除旧的再加，重复触发时不会残留/叠加
            attackDamage.removeModifier(ATTACK_MODIFIER_ID);
            attackDamage.addPermanentModifier(new AttributeModifier(
                    ATTACK_MODIFIER_ID,
                    Config.WARRIOR_ENERGY_ATTACK_DAMAGE.get(),
                    AttributeModifier.Operation.ADD_VALUE));
        }
    }

    // 效果结束或被移除时摘掉攻击修饰符
    @Override
    public void removeAttributeModifiers(AttributeMap attributes) {
        super.removeAttributeModifiers(attributes);

        AttributeInstance attackDamage = attributes.getInstance(Attributes.ATTACK_DAMAGE);
        if (attackDamage != null) {
            attackDamage.removeModifier(ATTACK_MODIFIER_ID);
        }
    }
}
