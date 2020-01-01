package net.telepathicgrunt.ultraamplified.world.generation;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.telepathicgrunt.ultraamplified.world.biome.BiomeInit;

public enum BiomeGenHelper {
   instance;
   public static ForgeRegistry<Biome> BiomeRegistry = ((ForgeRegistry<Biome>)ForgeRegistries.BIOMES);
	
   public static final int PLAINS = BiomeRegistry.getID(BiomeInit.PLAINS);
   public static final int SWAMP = BiomeRegistry.getID(BiomeInit.SWAMP);
   public static final int FOREST = BiomeRegistry.getID(BiomeInit.FOREST);
   public static final int DESERT = BiomeRegistry.getID(BiomeInit.DESERT);
   public static final int WOODED_ROCKY_FIELD = BiomeRegistry.getID(BiomeInit.WOODED_ROCKY_FIELD);
   public static final int RELIC_FOREST = BiomeRegistry.getID(BiomeInit.RELIC_FOREST);
   public static final int SNOWY_TUNDRA = BiomeRegistry.getID(BiomeInit.SNOWY_TUNDRA);
   public static final int ICED_TERRAIN = BiomeRegistry.getID(BiomeInit.ICED_TERRAIN);
   public static final int BAMBOO_JUNGLE = BiomeRegistry.getID(BiomeInit.BAMBOO_JUNGLE);
   public static final int JUNGLE = BiomeRegistry.getID(BiomeInit.JUNGLE);
   public static final int JUNGLE_EDGE = BiomeRegistry.getID(BiomeInit.JUNGLE_EDGE);
   public static final int BADLANDS = BiomeRegistry.getID(BiomeInit.BADLANDS);
   public static final int WOODED_BADLANDS = BiomeRegistry.getID(BiomeInit.WOODED_BADLANDS);
   public static final int SANDLESS_BADLANDS = BiomeRegistry.getID(BiomeInit.SANDLESS_BADLANDS);
   public static final int MUSHROOM_FIELDS = BiomeRegistry.getID(BiomeInit.MUSHROOM_FIELDS);
   public static final int GIANT_TREE_TAIGA = BiomeRegistry.getID(BiomeInit.GIANT_TREE_TAIGA);
   public static final int DARK_FOREST = BiomeRegistry.getID(BiomeInit.DARK_FOREST);
   public static final int SAVANNA = BiomeRegistry.getID(BiomeInit.SAVANNA);
   public static final int TAIGA = BiomeRegistry.getID(BiomeInit.TAIGA);
   public static final int SNOWY_TAIGA = BiomeRegistry.getID(BiomeInit.SNOWY_TAIGA);
   public static final int WARM_OCEAN = BiomeRegistry.getID(BiomeInit.WARM_OCEAN);
   public static final int LUKEWARM_OCEAN = BiomeRegistry.getID(BiomeInit.LUKEWARM_OCEAN);
   public static final int OCEAN = BiomeRegistry.getID(BiomeInit.OCEAN);
   public static final int COLD_OCEAN = BiomeRegistry.getID(BiomeInit.COLD_OCEAN);
   public static final int FROZEN_OCEAN = BiomeRegistry.getID(BiomeInit.FROZEN_OCEAN);
   public static final int DEEP_WARM_OCEAN = BiomeRegistry.getID(BiomeInit.DEEP_WARM_OCEAN);
   public static final int DEEP_LUKEWARM_OCEAN = BiomeRegistry.getID(BiomeInit.DEEP_LUKEWARM_OCEAN);
   public static final int DEEP_OCEAN = BiomeRegistry.getID(BiomeInit.DEEP_OCEAN);
   public static final int DEEP_COLD_OCEAN = BiomeRegistry.getID(BiomeInit.DEEP_COLD_OCEAN);
   public static final int DEEP_FROZEN_OCEAN = BiomeRegistry.getID(BiomeInit.DEEP_FROZEN_OCEAN);
   public static final int SUNFLOWER_PLAINS = BiomeRegistry.getID(BiomeInit.SUNFLOWER_PLAINS);
   public static final int NETHERLAND = BiomeRegistry.getID(BiomeInit.NETHERLAND);
   public static final int END_FIELD = BiomeRegistry.getID(BiomeInit.END_FIELD);
   public static final int BARREN_END_FIELD = BiomeRegistry.getID(BiomeInit.BARREN_END_FIELD);
   public static final int FROZEN_DESERT = BiomeRegistry.getID(BiomeInit.FROZEN_DESERT);
   public static final int STONE_PLAINS = BiomeRegistry.getID(BiomeInit.STONE_PLAINS);

   public static final int VANILLA_OCEAN = BiomeRegistry.getID(Biomes.OCEAN);
   public static final int VANILLA_DEEP_OCEAN = BiomeRegistry.getID(Biomes.DEEP_OCEAN);
	

	public static Set<Biome> frozenBiomes = ImmutableSet.of(
			BiomeInit.DEEP_FROZEN_OCEAN,
			BiomeInit.FROZEN_OCEAN,
			BiomeInit.ICE_SPIKES,
			BiomeInit.FROZEN_DESERT,
			BiomeInit.SNOWY_TAIGA,
			BiomeInit.RELIC_SNOWY_TAIGA,
			BiomeInit.SNOWY_ROCKY_TAIGA,
			BiomeInit.SNOWY_TUNDRA
			);
	
	public static Set<Biome> coldOceanBiomes = ImmutableSet.of(
			BiomeInit.DEEP_COLD_OCEAN,
			BiomeInit.COLD_OCEAN
			);
   
   private static final Set<Biome> OCEAN_LIST = ImmutableSet.of(
		   BiomeInit.WARM_OCEAN,
		   BiomeInit.LUKEWARM_OCEAN,
		   BiomeInit.OCEAN,
		   BiomeInit.COLD_OCEAN,
		   BiomeInit.FROZEN_OCEAN,
		   BiomeInit.DEEP_WARM_OCEAN,
		   BiomeInit.DEEP_LUKEWARM_OCEAN,
		   BiomeInit.DEEP_OCEAN,
		   BiomeInit.DEEP_COLD_OCEAN,
		   BiomeInit.DEEP_FROZEN_OCEAN
		  );
   
   public static boolean isOcean(Biome biomeIn) {
	   return OCEAN_LIST.contains(biomeIn);
   }
   
   public static boolean isOcean(int biomeIn) {
      return biomeIn == VANILLA_OCEAN || biomeIn == VANILLA_DEEP_OCEAN || biomeIn == WARM_OCEAN || biomeIn == LUKEWARM_OCEAN || biomeIn == OCEAN || biomeIn == COLD_OCEAN || biomeIn == FROZEN_OCEAN || biomeIn == DEEP_WARM_OCEAN || biomeIn == DEEP_LUKEWARM_OCEAN || biomeIn == DEEP_OCEAN || biomeIn == DEEP_COLD_OCEAN || biomeIn == DEEP_FROZEN_OCEAN;
   }

   public static boolean isShallowOcean(int biomeIn) {
      return biomeIn == VANILLA_OCEAN || biomeIn == WARM_OCEAN || biomeIn == LUKEWARM_OCEAN || biomeIn == OCEAN || biomeIn == COLD_OCEAN || biomeIn == FROZEN_OCEAN;
   }
	

   public static boolean areUABiomesSimilar(int biome1ID, int biome2ID) {
      if (biome1ID == biome2ID) {
         return true;
      } else {
         Biome biome1 = BiomeRegistry.getValue(biome1ID);
         Biome biome2 = BiomeRegistry.getValue(biome2ID);
         if (biome1 != null && biome2 != null) {
            if (biome1 != BiomeInit.WOODED_BADLANDS && biome1 != BiomeInit.SANDLESS_BADLANDS) {
               if (biome1.getCategory() != Biome.Category.NONE && biome2.getCategory() != Biome.Category.NONE && biome1.getCategory() == biome2.getCategory()) {
                  return true;
               } else {
                  return biome1 == biome2;
               }
            } else {
               return biome2 == BiomeInit.WOODED_BADLANDS || biome2 == BiomeInit.SANDLESS_BADLANDS;
            }
         } else {
            return false;
         }
      }
   }
}