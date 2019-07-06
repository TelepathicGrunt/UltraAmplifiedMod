package net.TelepathicGrunt.UltraAmplified.World.gen.structure;

import java.util.Random;

import org.apache.logging.log4j.Level;

import com.TelepathicGrunt.UltraAmplified.UltraAmplified;

import net.TelepathicGrunt.UltraAmplified.Config.Config;
import net.TelepathicGrunt.UltraAmplified.World.gen.feature.FeatureUA;
import net.minecraft.init.Biomes;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.structure.IglooConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class IglooUA extends Structure<IglooConfig> {
	
	protected ChunkPos getStartPositionForPosition(IChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
	      int i = Config.scatteredSpawnrate;
	      int j = 8;
	      if(Config.scatteredSpawnrate < 9 ) {
	    	  j = Config.scatteredSpawnrate - 1;
	      }
	      int k = x + i * spacingOffsetsX;
	      int l = z + i * spacingOffsetsZ;
	      int i1 = k < 0 ? k - i + 1 : k;
	      int j1 = l < 0 ? l - i + 1 : l;
	      int k1 = i1 / i;
	      int l1 = j1 / i;
	      ((SharedSeedRandom)random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), k1, l1, this.getSeedModifier());
	      k1 = k1 * i;
	      l1 = l1 * i;
	      k1 = k1 + random.nextInt(i - j);
	      l1 = l1 + random.nextInt(i - j);
	      return new ChunkPos(k1, l1);
	   }

	   protected boolean isEnabledIn(IWorld worldIn) {
	      return worldIn.getWorldInfo().isMapFeaturesEnabled();
	   }
	
	   protected String getStructureName() {
	      return "Igloo UA";
	   }

	   public int getSize() {
	      return 3;
	   }

	   protected StructureStart makeStart(IWorld worldIn, IChunkGenerator<?> generator, SharedSeedRandom random, int x, int z) {
	      Biome biome = generator.getBiomeProvider().getBiome(new BlockPos((x << 4) + 9, 0, (z << 4) + 9), Biomes.PLAINS);
	      return new IglooUA.Start(worldIn, generator, random, x, z, biome);
	   }

	   protected int getSeedModifier() {
	      return 14357618;
	   }

	   protected boolean hasStartAt(IChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ) {
	      ChunkPos chunkpos = this.getStartPositionForPosition(chunkGen, rand, chunkPosX, chunkPosZ, 0, 0);
	      if (chunkPosX == chunkpos.x && chunkPosZ == chunkpos.z) {
	         Biome biome = chunkGen.getBiomeProvider().getBiome(new BlockPos(chunkPosX * 16 + 9, 0, chunkPosZ * 16 + 9),  Biomes.PLAINS);
	         if (chunkGen.hasStructure(biome, this)) {
	            return true;
	         }
	      }

	      return false;
	   }
	   
	   public static class Start extends StructureStart {
	      public Start() {
	      }

	      public Start(IWorld worldIn, IChunkGenerator<?> chunkGenerator, SharedSeedRandom sharedRandom, int chunkX, int chunkZ, Biome biome) {
	         super(chunkX, chunkZ, biome, sharedRandom, worldIn.getSeed());
	         IglooConfig iglooconfig = (IglooConfig)chunkGenerator.getStructureConfig(biome, FeatureUA.IGLOO_UA);
	         int x = chunkX * 16;
	         int z = chunkZ * 16;
	         BlockPos blockpos = new BlockPos(x, 90, z);
	         Rotation rotation = Rotation.values()[sharedRandom.nextInt(Rotation.values().length)];
	         TemplateManager templatemanager = worldIn.getSaveHandler().getStructureTemplateManager();
	         IglooPiecesUA.start(templatemanager, blockpos, rotation, this.components, sharedRandom, iglooconfig);
	         this.recalculateStructureSize(worldIn);
	         
	           UltraAmplified.Logger.log(Level.DEBUG, "Igloo | "+(chunkX*16)+" "+(chunkZ*16));
	      }
	   }

	}