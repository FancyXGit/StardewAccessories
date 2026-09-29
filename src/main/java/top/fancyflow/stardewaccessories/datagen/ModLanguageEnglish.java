package top.fancyflow.stardewaccessories.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModItems;

// 生成 assets/stardewaccessories/lang/en_us.json
public class ModLanguageEnglish extends LanguageProvider {
    public ModLanguageEnglish(PackOutput output) {
        super(output, StardewAccessories.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.stardewaccessories", "Stardew Accessories");
        add("attribute.name.stardewaccessories.crit_chance", "Critical Chance");
        add("attribute.name.stardewaccessories.crit_damage", "Critical Damage");
        add("effect.stardewaccessories.warrior_energy", "Warrior Energy");
        add(ModItems.CHICKEN.get(), "Golden Chicken");

        add(ModItems.RING_BLANK.get(), "Ring Blank");
        add(ModItems.RUBY.get(), "Ruby");
        add(ModItems.EMERALD.get(), "Emerald");
        add(ModItems.JADE.get(), "Jade");
        add(ModItems.AQUAMARINE.get(), "Aquamarine");
        add(ModItems.TOPAZ.get(), "Topaz");
        add(ModItems.AMETHYST.get(), "Amethyst");
        add(ModItems.LUMEN_DUST.get(), "Lumen Dust");
        add(ModItems.LUMENITE.get(), "Lumenite");
        add(ModItems.LUMENITE_RING_BLANK.get(), "Lumen Ring Blank");
        add(ModItems.METAL_RING_BLANK.get(), "Metal Ring Blank");
        add(ModItems.MAGNET_FRAGMENTS.get(), "Magnet Fragments");
        add(ModItems.MAGNET.get(), "Magnet");
        add(ModItems.SLIME_CRYSTAL.get(), "Slime Crystal");

        add(ModItems.RUBY_RING.get(), "Ruby Ring");
        add(ModItems.EMERALD_RING.get(), "Emerald Ring");
        add(ModItems.JADE_RING.get(), "Jade Ring");
        add(ModItems.AQUAMARINE_RING.get(), "Aquamarine Ring");
        add(ModItems.TOPAZ_RING.get(), "Topaz Ring");
        add(ModItems.AMETHYST_RING.get(), "Amethyst Ring");
        add(ModItems.SMALL_GLOW_RING.get(), "Small Glow Ring");
        add(ModItems.GLOW_RING.get(), "Glow Ring");
        add(ModItems.SMALL_MAGNET_RING.get(), "Small Magnet Ring");
        add(ModItems.MAGNET_RING.get(), "Magnet Ring");
        add(ModItems.SLIME_CHARMER_RING.get(), "Slime Charmer Ring");
        add(ModItems.WARRIOR_RING.get(), "Warrior Ring");

        add("tooltip.stardewaccessories.small_glow_ring", "Slightly lights up the area around you");
        add("tooltip.stardewaccessories.glow_ring", "Brightly lights up the area around you");
        add("tooltip.stardewaccessories.small_magnet_ring", "Attracts nearby items");
        add("tooltip.stardewaccessories.magnet_ring", "Strongly attracts nearby items");
        add("tooltip.stardewaccessories.slime_charmer_ring", "Slimes and magma cubes cannot hurt you");
        add("tooltip.stardewaccessories.warrior_ring", "Chance to gain Warrior Energy when slaying monsters, boosting your attack");
        add("tooltip.stardewaccessories.requires_lambdynlights", "Requires LambDynamicLights");
        add("tooltip.stardewaccessories.not_installed", "No effect when not installed");

        // Flavor descriptions
        add("tooltip.stardewaccessories.ruby_ring.desc", "An enchanted ruby, putting more weight behind every swing");
        add("tooltip.stardewaccessories.emerald_ring.desc", "An enchanted emerald, quickening the rhythm of your strikes");
        add("tooltip.stardewaccessories.jade_ring.desc", "A sharp edge sleeps within the smooth jade");
        add("tooltip.stardewaccessories.aquamarine_ring.desc", "A drop of enchanted sea, leaving no weak point hidden");
        add("tooltip.stardewaccessories.topaz_ring.desc", "You feel a little bit safer wearing this");
        add("tooltip.stardewaccessories.amethyst_ring.desc", "Enchanted amethyst, giving every blow a stubborn shove");
        add("tooltip.stardewaccessories.small_glow_ring.desc", "A tiny light, glowing softly in the dark");
        add("tooltip.stardewaccessories.glow_ring.desc", "A bright, steady light to see you through the night");
        add("tooltip.stardewaccessories.ring_blank.desc", "A plain band, waiting for a gem");
        add("tooltip.stardewaccessories.lumenite_ring_blank.desc", "A lumenite band, still holding a trace of light");
        add("tooltip.stardewaccessories.metal_ring_blank.desc", "A weighty metal band, faintly humming with magnetism");
        add("tooltip.stardewaccessories.small_magnet_ring.desc", "Shards of lodestone set in the band; loose things find their way to you");
        add("tooltip.stardewaccessories.magnet_ring.desc", "A full lodestone face; loose things hurry into your grasp");
        add("tooltip.stardewaccessories.slime_charmer_ring.desc", "Slimes only want to be your gooey friends now");
        add("tooltip.stardewaccessories.warrior_ring.desc", "A ring forged from battle spirit, its fury lingering after the kill");
    }
}
