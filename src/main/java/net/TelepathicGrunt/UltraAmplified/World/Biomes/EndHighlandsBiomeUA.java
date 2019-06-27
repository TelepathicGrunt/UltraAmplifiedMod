package net.TelepathicGrunt.UltraAmplified.World.Biomes;

import net.TelepathicGrunt.UltraAmplified.Config.Config;
import net.TelepathicGrunt.UltraAmplified.World.Biome.BiomeUA;
import net.TelepathicGrunt.UltraAmplified.World.gen.feature.FeatureUA;
import net.TelepathicGrunt.UltraAmplified.World.gen.feature.placement.BlockConfig;
import net.TelepathicGrunt.UltraAmplified.World.gen.feature.placement.PercentageAndFrequencyConfig;
import net.TelepathicGrunt.UltraAmplified.World.gen.structure.MapGenMineshaftUA;
import net.TelepathicGrunt.UltraAmplified.World.gen.structure.MineshaftConfigUA;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.EndCityConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DungeonRoomConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EndHighlandsBiomeUA extends BiomeUA {
   public EndHighlandsBiomeUA() {
	      super((new Biome.BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder<>(END_SURFACE_BUILDER_UA, END_STONE_SURFACE)).precipitation(Biome.RainType.NONE).category(Biome.Category.THEEND).depth(0.1F).scale(0.2F).temperature(0.8F).downfall(0.5F).waterColor(16711840).waterFogColor(16711840).parent((String)null));
	    
	      //this.addCarver(GenerationStage.Carving.AIR, createWorldCarverWrapper(CAVE_CAVITY_CARVER, new ProbabilityConfig((float)(Config.caveCavitySpawnrate)/100)));
	      this.addCarver(GenerationStage.Carving.AIR, createWorldCarverWrapper(RAVINE_CARVER, new ProbabilityConfig((float)(Config.ravineSpawnrate)/100)));

	      if(Config.mineshaftAbovegroundAllowed || Config.mineshaftUndergroundAllowed)
		      this.addStructure(FeatureUA.MINESHAFT_UA, new MineshaftConfigUA((double)Config.mineshaftSpawnrate, MapGenMineshaftUA.Type.END));
	      this.addStructure(Feature.END_CITY, new EndCityConfig()); 
	      this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, createCompositeFeature(FeatureUA.END_DUNGEONS, IFeatureConfig.NO_FEATURE_CONFIG, DUNGEON_PLACEMENT, new DungeonRoomConfig(Config.dungeonSpawnrate)));
	      
	      this.addFeature(GenerationStage.Decoration.RAW_GENERATION, createCompositeFeature(Feature.END_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, HEIGHT_BIASED_RANGE_UA, new CountRangeConfig(Config.endIslandSpawnrate, 75, 0, 254)));
	      this.addFeature(GenerationStage.Decoration.RAW_GENERATION, createCompositeFeature(Feature.END_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, HEIGHT_BIASED_RANGE_UA, new CountRangeConfig(Config.endIslandSpawnrate/10, 10, 0, 50)));
	      this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, createCompositeFeature(Feature.END_CITY, new EndCityConfig(), PASSTHROUGH, IPlacementConfig.NO_PLACEMENT_CONFIG));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.CHORUS_PLANT, IFeatureConfig.NO_FEATURE_CONFIG, CHANCE_ON_ALL_SURFACES_UA, new PercentageAndFrequencyConfig(0.7f, 2)));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.CHORUS_PLANT, IFeatureConfig.NO_FEATURE_CONFIG, HEIGHT_BIASED_RANGE_UA, new CountRangeConfig(50, 10, 10, 75)));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(FeatureUA.SINGLE_BLOCK, new BlockConfig(Blocks.DRAGON_HEAD), HEIGHT_BIASED_RANGE_UA, new CountRangeConfig(2, 4, 1, 50)));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(FeatureUA.SINGLE_BLOCK, new BlockConfig(Blocks.SHULKER_BOX), HEIGHT_BIASED_RANGE_UA, new CountRangeConfig(1, 25, 1, 70)));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(FeatureUA.SINGLE_BLOCK, new BlockConfig(Blocks.DRAGON_EGG), HEIGHT_BIASED_RANGE_UA, new CountRangeConfig(1, 4, 1, 255)));
	      
	      this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 4, 4));
	   }

	/**
    * takes temperature, returns color
    */
   @OnlyIn(Dist.CLIENT)
   public int getSkyColorByTemp(float currentTemperature) {
      return 0;
   }
   
   @OnlyIn(Dist.CLIENT)
   public int getGrassColor(BlockPos pos) {
      return 7625106;
   }

   @OnlyIn(Dist.CLIENT)
   public int getFoliageColor(BlockPos pos) {
      return 5329028;
   }
   
}