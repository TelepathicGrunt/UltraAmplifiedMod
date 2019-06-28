package net.TelepathicGrunt.UltraAmplified.World.gen.feature;

import net.TelepathicGrunt.UltraAmplified.World.gen.feature.placement.BlockConfig;
import net.TelepathicGrunt.UltraAmplified.World.gen.structure.EndCityUA;
import net.TelepathicGrunt.UltraAmplified.World.gen.structure.StrongholdUA;
import net.TelepathicGrunt.UltraAmplified.World.gen.structure.MineshaftConfigUA;
import net.TelepathicGrunt.UltraAmplified.World.gen.structure.MineshaftUA;
import net.TelepathicGrunt.UltraAmplified.World.gen.structure.NetherBridgeConfigUA;
import net.TelepathicGrunt.UltraAmplified.World.gen.structure.NetherBridgeUA;
import net.TelepathicGrunt.UltraAmplified.World.gen.structure.WoodlandMansionUA;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeTreesFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.EndCityConfig;
import net.minecraft.world.gen.feature.structure.StrongholdConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.WoodlandMansionConfig;

public abstract class FeatureUA<C extends IFeatureConfig> extends Feature<C>{

	   public static final Feature<NoFeatureConfig> BETTER_CACTUS = new WorldGenBetterCactusUA();
	   public static final Feature<NoFeatureConfig> GENERIC_DUNGEONS = new WorldGenDungeonsUA();
	   public static final Feature<NoFeatureConfig> MESA_DUNGEONS = new WorldGenMesaDungeonsUA();
	   public static final Feature<NoFeatureConfig> DARK_FOREST_DUNGEONS = new WorldGenDarkForestDungeonsUA();
	   public static final Feature<NoFeatureConfig> DESERT_DUNGEONS = new WorldGenDesertDungeonsUA();
	   public static final Feature<NoFeatureConfig> END_DUNGEONS = new WorldGenEndDungeonsUA();
	   public static final Feature<NoFeatureConfig> NETHER_DUNGEONS = new WorldGenNetherDungeonsUA();
	   public static final Feature<NoFeatureConfig> SNOW_DUNGEONS = new WorldGenSnowDungeonsUA();
	   public static final Feature<NoFeatureConfig> SWAMP_DUNGEONS = new WorldGenSwampDungeonsUA();
	   public static final Feature<NoFeatureConfig> MUSHROOM_DUNGEONS = new WorldGenMushroomDungeonsUA();
	   
	   public static final Feature<NoFeatureConfig> CROSS = new WorldGenCross();
	   public static final Feature<NoFeatureConfig> HAY_BALE = new WorldGenHayBalePileUA();
	   public static final Feature<NoFeatureConfig> TINY_HAY_BALE = new WorldGenHayBaleTinyPileUA();
	   public static final Feature<NoFeatureConfig> STONEHENGE = new WorldGenStonehenge();
	   public static final Feature<NoFeatureConfig> SUN_SHRINE = new WorldGenSunShrine();
	   public static final Feature<NoFeatureConfig> FOSSILS_UA = new WorldGenFossilUA();
	   public static final Feature<BlockConfig> SINGLE_BLOCK = new SingleBlock();

	   public static final Feature<BlockBlobConfig> LARGE_STACKABLE_BOULDER = new WorldGenGiantStackableBoulderUA();
	   public static final Feature<BlockBlobConfig> LARGE_BOULDER = new WorldGenGiantBoulderUA();
	   public static final Feature<BlockBlobConfig> MEDIUM_BOULDER = new WorldGenNormalBoulderUA();
	   public static final Feature<BlockBlobConfig> SMALL_BOULDER = new WorldGenTinyBoulderUA();

	   public static final Feature<LakesConfig> SLIME_LAKE = new WorldGenSlimeLakes();
	   public static final Feature<LakesConfig> SHALLOW_LAKE = new WorldGenShallowLakes();
	   public static final Feature<NoFeatureConfig> ICE_PATCH_SANDY = new WorldGenIcePatchUA();
	   public static final Feature<NoFeatureConfig> GIANT_ICE_SPIKE = new WorldGenIceSpikeUA();
	   public static final Feature<NoFeatureConfig> GREEN_CONCRETE_POWDER_PATCH = new WorldGenGreenPowConcretePatchUA();
	   public static final Feature<NoFeatureConfig> BLUE_ICE_WATERFALL = new WorldGenBlueIceWaterfallUA();
	   public static final Feature<LiquidsConfig> CEILING_FLUID = new CeilingFluid();
	   public static final Feature<NoFeatureConfig> ICE_AND_SNOW_UNDER_LEDGES = new IceAndSnowAtAllLayer();
	   
	   public static final Feature<NoFeatureConfig> LONG_VINES = new WorldGenVinesLongUA();
	   public static final Feature<NoFeatureConfig> SHORT_VINES = new WorldGenVinesShortUA();
	   
	   public static final AbstractTreeFeature<NoFeatureConfig> HORNED_SWAMP_TREE = new WorldGenSwampTreeMutatedUA();
	   public static final HugeTreesFeature<NoFeatureConfig> MEGA_BIRCH_TREE = new WorldGenBirchMTree(false);
	   public static final HugeTreesFeature<NoFeatureConfig> MEGA_PINE_TREE_1_UA = new WorldGenMegaPineTreeUA(false, false);
	   public static final HugeTreesFeature<NoFeatureConfig> MEGA_PINE_TREE_2_UA = new WorldGenMegaPineTreeUA(false, true);
	   public static final AbstractTreeFeature<NoFeatureConfig> DARK_FOREST_M_TREE = new WorldGenDarkOakMTree(false);
	   public static final AbstractTreeFeature<NoFeatureConfig> TAIGA_M_TREE = new WorldGenTaigaTreeMUA(false);
	   
	   public static final Structure<MineshaftConfigUA> MINESHAFT_UA = new MineshaftUA();
	   public static final Structure<EndCityConfig> END_CITY_UA = new EndCityUA();
	   public static final Structure<NetherBridgeConfigUA> FORTRESS_UA = new NetherBridgeUA();
	   public static final Structure<WoodlandMansionConfig> WOODLAND_MANSION_UA = new WoodlandMansionUA();
	   public static final Structure<StrongholdConfig> STRONGHOLD_UA = new StrongholdUA();
}
