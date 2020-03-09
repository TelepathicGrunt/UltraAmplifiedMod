package net.telepathicgrunt.ultraamplified.world.biomes;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.telepathicgrunt.ultraamplified.world.biome.UABiome;
import net.telepathicgrunt.ultraamplified.world.biome.surfacebuilder.UASurfaceBuilders;
import net.telepathicgrunt.ultraamplified.world.feature.UAFeatures;
import net.telepathicgrunt.ultraamplified.world.feature.carver.UACarvers;
import net.telepathicgrunt.ultraamplified.world.feature.config.ChanceAndTypeConfig;
import net.telepathicgrunt.ultraamplified.world.feature.config.ColumnBlocksConfig;
import net.telepathicgrunt.ultraamplified.world.feature.config.PercentageAndFrequencyConfig;
import net.telepathicgrunt.ultraamplified.world.feature.placement.UAPlacements;
import net.telepathicgrunt.ultraamplified.world.feature.structure.MineshaftConfigUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.MineshaftStructureUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.VillageConfigUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.VillagePastStyledPiecesUA;


public class EndBarrenFieldBiomeUA extends UABiome
{
	public EndBarrenFieldBiomeUA()
	{
		super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder<>(UASurfaceBuilders.END_SURFACE_BUILDER_UA, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)).precipitation(Biome.RainType.NONE).category(Biome.Category.THEEND).depth(0.1F).scale(0.2F).temperature(0.7F).downfall(0.5F).waterColor(7299198).waterFogColor(8542866).parent((String) null));

		this.addStructureFeature(UAFeatures.MINESHAFT.configure(new MineshaftConfigUA(MineshaftStructureUA.Type.END)));
		this.addStructureFeature(UAFeatures.VILLAGE.configure(new VillageConfigUA(0, VillagePastStyledPiecesUA.Type.END)));
		this.addStructureFeature(UAFeatures.END_CITY.configure(IFeatureConfig.NO_FEATURE_CONFIG));

		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(UACarvers.CAVE_CAVITY_CARVER, new ProbabilityConfig(0f)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(UACarvers.RAVINE_CARVER, new ProbabilityConfig(0f)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(UACarvers.LONG_RAVINE_CARVER, new ProbabilityConfig(0f)));
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, UAFeatures.HANGING_RUINS.configure(IFeatureConfig.NO_FEATURE_CONFIG).createDecoratedFeature(UAPlacements.LEDGE_UNDERSIDE_MINI_FEATURE.configure(new ChanceAndTypeConfig(0.6f, ChanceAndTypeConfig.Type.HANGING_RUINS))));

		this.addFeature(GenerationStage.Decoration.RAW_GENERATION, UAFeatures.NETHER_UNDERWATER_MAGMA.configure(IFeatureConfig.NO_FEATURE_CONFIG).createDecoratedFeature(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, UAFeatures.CONTAIN_UNDERGROUND_LIQUID.configure(IFeatureConfig.NO_FEATURE_CONFIG).createDecoratedFeature(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		this.addStructureFeaturesUA();
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, UAFeatures.COLUMN.configure(new ColumnBlocksConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.STONE.getDefaultState())).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 70, 0, 220))));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, UAFeatures.RAMP.configure(new ColumnBlocksConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.STONE.getDefaultState())).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 70, 0, 220))));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, UAFeatures.GLOWPATCH.configure(new CountConfig(160)).createDecoratedFeature(UAPlacements.GLOWSTONE_VARIANT_PATCH.configure(new ChanceConfig(2))));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, UAFeatures.CONTAIN_OCEAN_LIQUID.configure(IFeatureConfig.NO_FEATURE_CONFIG).createDecoratedFeature(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

		this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, UAFeatures.END_DUNGEONS.configure(IFeatureConfig.NO_FEATURE_CONFIG).createDecoratedFeature(UAPlacements.DUNGEON_PLACEMENT.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, UAFeatures.DISK_DRY.configure(new SphereReplaceConfig(Blocks.END_STONE.getDefaultState(), 5, 3, Lists.newArrayList(Blocks.STONE.getDefaultState(), Blocks.DIRT.getDefaultState()))).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(4, 70, 10, 250))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, UAFeatures.DISK_DRY.configure(new SphereReplaceConfig(Blocks.OBSIDIAN.getDefaultState(), 5, 2, Lists.newArrayList(Blocks.GRASS_BLOCK.getDefaultState()))).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 10, 10, 250))));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UAFeatures.END_TREE.configure(UAFeatures.getConfiguredForUndergroundTreeConfig(DefaultBiomeFeatures.PINE_TREE_CONFIG)).createDecoratedFeature(UAPlacements.AT_SURFACE_WITH_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UAFeatures.END_TREE.configure(UAFeatures.getConfiguredForUndergroundTreeConfig(DefaultBiomeFeatures.PINE_TREE_CONFIG)).createDecoratedFeature(UAPlacements.AT_SURFACE_BELOW_TOP_LAYER_WITH_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(UAFeatures.getConfiguredClusterConfig(DefaultBiomeFeatures.TAIGA_GRASS_CONFIG)).createDecoratedFeature(UAPlacements.CHANCE_ON_ALL_SURFACES.configure(new PercentageAndFrequencyConfig(0.4F, 1))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(UAFeatures.getConfiguredClusterConfig(DefaultBiomeFeatures.SWEET_BERRY_BUSH_CONFIG)).createDecoratedFeature(UAPlacements.CHANCE_ON_ALL_SURFACES.configure(new PercentageAndFrequencyConfig(0.1F, 1))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.SPRING_FEATURE.configure(new LiquidsConfig(Fluids.WATER.getDefaultState(), true, 4, 1, ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.END_STONE, Blocks.END_STONE_BRICKS))).createDecoratedFeature(UAPlacements.WATERFALL_RANGE.configure(new CountRangeConfig(2, 70, 8, 256))));
		this.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, UAFeatures.SNOW_AND_ICE_LAYERER.configure(IFeatureConfig.NO_FEATURE_CONFIG).createDecoratedFeature(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMITE, 10, 4, 12));
	}


	/*
	 * Set sky color
	 */
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getSkyColor()
	{
		return 2369577;
	}


	/*
	 * set grass color
	 */
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getGrassColorAt(double p_225528_1_, double p_225528_3_)
	{
		return 7045758;
	}


	/*
	 * set foliage/plant color
	 */
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getFoliageColor()
	{
		return 6060912;
	}

}
