package top.fancyflow.stardewaccessories.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

// 由巴的祝福：由巴的戒指在玩家受伤后按概率触发，持续期间免疫伤害。
// 免疫本身不在这里实现，而是由 RingOfYobaEvents 取消伤害事件达成，效果只负责状态与图标。
public class YobasBlessingEffect extends MobEffect {

    public YobasBlessingEffect() {
        // 有益效果 + 金色（与祝福的圣洁感呼应）
        super(MobEffectCategory.BENEFICIAL, 0xF6D65C);
    }
}
