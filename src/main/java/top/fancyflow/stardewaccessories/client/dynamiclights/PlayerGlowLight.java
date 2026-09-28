package top.fancyflow.stardewaccessories.client.dynamiclights;

import dev.lambdaurora.lambdynlights.api.behavior.DynamicLightBehavior;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

// 以玩家为中心的一个"点光源"。亮度由管理器传入，随距离按原版方式衰减。
public class PlayerGlowLight implements DynamicLightBehavior {

    private final Player player;
    private int luminance;
    private double x;
    private double y;
    private double z;
    private boolean luminanceChanged;

    public PlayerGlowLight(Player player, int luminance) {
        this.player = player;
        this.luminance = luminance;
        updatePosition();
    }

    // 运行时更改亮度（例如换上更亮的戒指）
    public void setLuminance(int luminance) {
        if (luminance != this.luminance) {
            this.luminance = luminance;
            this.luminanceChanged = true;
        }
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
        // falloffRatio 让衰减范围符合 LambDynamicLights 的尺度
        return Math.max(this.luminance - Math.sqrt(distanceSquared) * falloffRatio, 0.0);
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
        // 玩家移动超过约 0.1 格、或亮度变化时通知 LambDynamicLights 刷新
        double dx = player.getX() - this.x;
        double dy = lightY() - this.y;
        double dz = player.getZ() - this.z;
        boolean moved = dx * dx + dy * dy + dz * dz > 0.01;
        if (moved) {
            updatePosition();
        }
        boolean changed = moved || this.luminanceChanged;
        this.luminanceChanged = false;
        return changed;
    }
}
