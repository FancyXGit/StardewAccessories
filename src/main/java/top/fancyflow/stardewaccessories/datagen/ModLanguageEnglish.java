package top.fancyflow.stardewaccessories.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModBlocks;
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
        add("effect.stardewaccessories.yobas_blessing", "Yoba's Blessing");
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
        add(ModItems.DARK_ALLOY_RING_BLANK.get(), "Dark Alloy Ring Blank");
        add(ModItems.MAGNET_FRAGMENTS.get(), "Magnet Fragments");
        add(ModItems.MAGNET.get(), "Magnet");
        add(ModItems.SLIME_CRYSTAL.get(), "Slime Crystal");
        add(ModItems.BLOOD_ESSENCE.get(), "Blood Essence");

        // Ores
        add(ModBlocks.STARSHARD_ORE.get(), "Starshard Ore");
        add(ModBlocks.DEEPSLATE_STARSHARD_ORE.get(), "Deepslate Starshard Ore");
        add(ModBlocks.PRISM_ORE.get(), "Prism Ore");
        add(ModBlocks.DEEPSLATE_PRISM_ORE.get(), "Deepslate Prism Ore");
        add(ModItems.STARSHARD.get(), "Starshard");
        add(ModItems.STARSHARD_INGOT.get(), "Starshard Ingot");

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
        add(ModItems.VAMPIRE_RING.get(), "Vampire Ring");
        add(ModItems.SAVAGE_RING.get(), "Savage Ring");
        add(ModItems.RING_OF_YOBA.get(), "Ring of Yoba");
        add(ModItems.BURGLARS_RING.get(), "Burglar's Ring");
        add(ModItems.IRIDIUM_BAND.get(), "Iridium Band");

        add("tooltip.stardewaccessories.small_glow_ring", "Slightly lights up the area around you");
        add("tooltip.stardewaccessories.glow_ring", "Brightly lights up the area around you");
        add("tooltip.stardewaccessories.small_magnet_ring", "Attracts nearby items");
        add("tooltip.stardewaccessories.magnet_ring", "Strongly attracts nearby items");
        add("tooltip.stardewaccessories.slime_charmer_ring", "Slimes and magma cubes cannot hurt you");
        add("tooltip.stardewaccessories.warrior_ring", "Chance to gain Warrior Energy when slaying monsters, boosting your attack");
        add("tooltip.stardewaccessories.vampire_ring", "Restores a little health whenever you slay a monster");
        add("tooltip.stardewaccessories.savage_ring", "Gain a short speed boost after slaying a monster");
        add("tooltip.stardewaccessories.ring_of_yoba", "Chance to gain Yoba's Blessing when hurt, granting brief immunity to damage");
        add("tooltip.stardewaccessories.burglars_ring", "Monsters drop extra loot when slain");
        add("tooltip.stardewaccessories.iridium_band.glow", "Brightly lights up the area around you");
        add("tooltip.stardewaccessories.iridium_band.magnet", "Strongly attracts nearby items");
        add("tooltip.stardewaccessories.requires_lambdynlights", "Requires LambDynamicLights");
        add("tooltip.stardewaccessories.not_installed", "No effect when not installed");

        // Material acquisition
        add("tooltip.stardewaccessories.lumen_dust.source", "Occasional drop when mining glowstone");
        add("tooltip.stardewaccessories.magnet_fragments.source", "Occasionally found in mineshaft and dungeon chests");
        add("tooltip.stardewaccessories.slime_crystal.source", "Occasional drop from slimes");
        add("tooltip.stardewaccessories.blood_essence.source", "Occasional drop from bats or phantoms");

        // Flavor descriptions
        add("tooltip.stardewaccessories.ruby_ring.desc", "Makes your attacks hit harder");
        add("tooltip.stardewaccessories.emerald_ring.desc", "Speeds up your striking rhythm");
        add("tooltip.stardewaccessories.jade_ring.desc", "Skilled at exploiting weak points");
        add("tooltip.stardewaccessories.aquamarine_ring.desc", "Leaves no weak point hidden");
        add("tooltip.stardewaccessories.topaz_ring.desc", "You feel a little bit safer wearing this");
        add("tooltip.stardewaccessories.amethyst_ring.desc", "Adds a hidden force to every swing");
        add("tooltip.stardewaccessories.small_glow_ring.desc", "A tiny glimmer");
        add("tooltip.stardewaccessories.glow_ring.desc", "A bright sphere of light");
        add("tooltip.stardewaccessories.ring_blank.desc", "A plain band");
        add("tooltip.stardewaccessories.lumenite_ring_blank.desc", "A band polished from lumenite");
        add("tooltip.stardewaccessories.metal_ring_blank.desc", "A heavy plain metal band");
        add("tooltip.stardewaccessories.dark_alloy_ring_blank.desc", "A dark, dim alloy band");
        add("tooltip.stardewaccessories.small_magnet_ring.desc", "Shattered lodestone set in the band");
        add("tooltip.stardewaccessories.magnet_ring.desc", "A whole lodestone ring face");
        add("tooltip.stardewaccessories.slime_charmer_ring.desc", "Makes slimes fond of you");
        add("tooltip.stardewaccessories.warrior_ring.desc", "A ring formed from battle spirit");
        add("tooltip.stardewaccessories.vampire_ring.desc", "A blood-drinking ring");
        add("tooltip.stardewaccessories.savage_ring.desc", "Your steps suddenly feel lighter");
        add("tooltip.stardewaccessories.ring_of_yoba.desc", "Grants an unbreakable body in times of peril");
        add("tooltip.stardewaccessories.burglars_ring.desc", "I want it all");
        add("tooltip.stardewaccessories.iridium_band.desc", "A gleaming iridium band");
    }
}
