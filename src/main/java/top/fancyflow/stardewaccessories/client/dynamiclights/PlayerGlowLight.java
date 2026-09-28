package top.fancyflow.stardewaccessories.client.dynamiclights;

import dev.lambdaurora.lambdynlights.api.behavior.DynamicLightBehavior;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

import top.fancyflow.stardewaccessories.config.Config;

// 以玩家为中心的一个"点光源"。亮度从配置读取，随距离按原版方式衰减。
public class PlayerGlowLight implements DynamicLightBehavior {

    private final Player player;
    private double x;
    private double y;
    private double z;

    public PlayerGlowLight(Player player) {
        this.player = player;
        updatePosition();
    }

    // 光从玩家胸口高度发出
    private double lightY() {
        return player.getY() + player.getEyeHeight() * 0.5;
    }

    private void updatePosition() {
        this.x = player.getX();
        this.y = lightY();
        this.z = player.getZ();
    }

    @Override
    public double lightAtPos(BlockPos pos, double falloffRatio) {
        double dx = pos.getX() + 0.5 - this.x;
        double dy = pos.getY() + 0.5 - this.y;
        double dz = pos.getZ() + 0.5 - this.z;
        double distanceSquared = dx * dx + dy * dy + dz * dz;
        // 光等级从配置读取（0~15）；falloffRatio 让衰减范围符合 LambDynamicLights 的尺度
        return Math.max(Config.SMALL_GLOW_RING_LIGHT.get() - Math.sqrt(distanceSquared) * falloffRatio, 0.0);
    }

    @Override
    public DynamicLightBehavior.BoundingBox getBoundingBox() {
        // 点光源：包围盒就是当前所在的那一格
        int bx = Mth.floor(this.x);
        int by = Mth.floor(this.y);
        int bz = Mth.floor(this.z);
        return new DynamicLightBehavior.BoundingBox(bx, by, bz, bx, by, bz);
    }

    @Override
    public boolean hasChanged() {
        // 玩家移动超过约 0.1 格就通知 LambDynamicLights 更新光照
        double dx = player.getX() - this.x;
        double dy = lightY() - this.y;
        double dz = player.getZ() - this.z;
        if (dx * dx + dy * dy + dz * dz > 0.01) {
            updatePosition();
            return true;
        }
        return false;
    }
}
