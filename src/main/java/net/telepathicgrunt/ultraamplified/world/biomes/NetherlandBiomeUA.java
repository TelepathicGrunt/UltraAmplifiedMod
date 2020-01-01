package net.telepathicgrunt.ultraamplified.world.biomes;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.telepathicgrunt.ultraamplified.world.biome.BiomeUA;
import net.telepathicgrunt.ultraamplified.world.feature.FeatureUA;
import net.telepathicgrunt.ultraamplified.world.feature.config.CountRangeAndTypeConfig;
import net.telepathicgrunt.ultraamplified.world.feature.config.PercentageAndFrequencyConfig;
import net.telepathicgrunt.ultraamplified.world.feature.structure.FortressConfigUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.MineshaftConfigUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.MineshaftUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.VillageConfigUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.VillagePiecesUA;


public class NetherlandBiomeUA extends BiomeUA
{
	public NetherlandBiomeUA()
	{
		super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder<>(NETHER_SURFACE_BUILDER_UA, SurfaceBuilder.NETHERRACK_CONFIG)).precipitation(Biome.RainType.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).waterColor(12411469).waterFogColor(12076846).parent((String) null));

		this.func_226711_a_(FeatureUA.MINESHAFT_UA.func_225566_b_(new MineshaftConfigUA(MineshaftUA.Type.HELL)));
		this.func_226711_a_(FeatureUA.VILLAGE_UA.func_225566_b_(new VillageConfigUA(0, VillagePiecesUA.Type.HELL)));
		this.func_226711_a_(FeatureUA.STRONGHOLD_UA.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG));
		this.func_226711_a_(FeatureUA.FORTRESS_UA.func_225566_b_(new FortressConfigUA(true)));
		this.addStructureFeaturesUA();
		
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(CAVE_CAVITY_CARVER, new ProbabilityConfig(0f)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(RAVINE_CARVER, new ProbabilityConfig(0f)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(LONG_RAVINE_CARVER, new ProbabilityConfig(0f)));

		this.addFeature(GenerationStage.Decoration.RAW_GENERATION, FeatureUA.UNDERWATER_MAGMA.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		this.addFeature(GenerationStage.Decoration.RAW_GENERATION, FeatureUA.CONTAIN_UNDERGROUND_LIQUID.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		this.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, FeatureUA.CONTAIN_LIQUID.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, FeatureUA.NETHER_DUNGEONS.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(DUNGEON_PLACEMENT.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureUA.RARE_LAVA.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(CHANCE_ON_ALL_SURFACES_UA.func_227446_a_(new PercentageAndFrequencyConfig(1F, 5))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.SPRING_FEATURE.func_225566_b_(new LiquidsConfig(Fluids.LAVA.getDefaultState(), true, 4, 1, ImmutableSet.of(Blocks.NETHERRACK))).func_227228_a_(Placement.COUNT_VERY_BIASED_RANGE.func_227446_a_(new CountRangeConfig(38, 8, 16, 256))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(DefaultBiomeFeatures.field_226722_J_).func_227228_a_(Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(0.9F, 10, 0, 255))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(DefaultBiomeFeatures.field_226721_I_).func_227228_a_(Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(0.9F, 10, 0, 255))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.SPRING_FEATURE.func_225566_b_(DefaultBiomeFeatures.field_226738_Z_).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(23, 4, 8, 256))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.field_227248_z_.func_225566_b_(FeatureUA.getConfiguredClusterConfig(DefaultBiomeFeatures.field_226719_G_)).func_227228_a_(CHANCE_ON_ALL_SURFACES_UA.func_227446_a_(new PercentageAndFrequencyConfig(0.18F, 1))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.field_227248_z_.func_225566_b_(DefaultBiomeFeatures.field_226719_G_).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(2, 0, 0, 100))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.GLOWSTONE_BLOB.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(GLOWSTONE_PLACEMENT.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.GLOWSTONE_BLOB.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(1f, 0, 0, 256, false, CountRangeAndTypeConfig.Type.GLOWSTONE))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.field_227248_z_.func_225566_b_((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.NETHER_WART.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_()).func_227228_a_(NETHERWART_SOUL_SAND_SURFACES_UA.func_227446_a_(new PercentageAndFrequencyConfig(0.7F, 2))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.field_227248_z_.func_225566_b_(DefaultBiomeFeatures.field_226722_J_).func_227228_a_(Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(1F, 0, 0, 75))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.field_227248_z_.func_225566_b_(DefaultBiomeFeatures.field_226721_I_).func_227228_a_(Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(1F, 0, 0, 75))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.field_227248_z_.func_225566_b_(FeatureUA.getConfiguredClusterConfig(DefaultBiomeFeatures.field_226722_J_)).func_227228_a_(CHANCE_ON_ALL_SURFACES_UA.func_227446_a_(new PercentageAndFrequencyConfig(0.05F, 1))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.field_227248_z_.func_225566_b_(FeatureUA.getConfiguredClusterConfig(DefaultBiomeFeatures.field_226721_I_)).func_227228_a_(CHANCE_ON_ALL_SURFACES_UA.func_227446_a_(new PercentageAndFrequencyConfig(0.05F, 1))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, Blocks.NETHER_QUARTZ_ORE.getDefaultState(), 25)).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(1f, 18, 0, 256, true, CountRangeAndTypeConfig.Type.QUARTZ))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, Blocks.NETHER_QUARTZ_ORE.getDefaultState(), 25)).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(0.5f, 10, 20, 120, false, CountRangeAndTypeConfig.Type.QUARTZ))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, Blocks.NETHER_QUARTZ_ORE.getDefaultState(), 25)).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(0.1f, 10, 120, 240, false, CountRangeAndTypeConfig.Type.QUARTZ))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, Blocks.MAGMA_BLOCK.getDefaultState(), 55)).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(1, 10, 20, 100, false, CountRangeAndTypeConfig.Type.MAGMA))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.SPRING_FEATURE.func_225566_b_(DefaultBiomeFeatures.field_226766_aa_).func_227228_a_(LAVAFALL_RANGE.func_227446_a_(new CountRangeConfig(1, 10, 20, 240))));
		this.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, FeatureUA.SNOW_AND_ICE_LAYERER.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));

		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.GHAST, 50, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_PIGMAN, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.MAGMA_CUBE, 2, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 1, 4, 4));
	}


	/*
	 * Set sky color
	 */
	@OnlyIn(Dist.CLIENT)
	public int func_225529_c_()
	{
		return 2621440;
	}


	/*
	 * set grass color
	 */
	@OnlyIn(Dist.CLIENT)
	public int func_225528_a_(double p_225528_1_, double p_225528_3_)
	{
		return 9259264;
	}


	/*
	 * set foliage/plant color
	 */
	@OnlyIn(Dist.CLIENT)
	public int func_225527_a_()
	{
		return 8075008;
	}

}