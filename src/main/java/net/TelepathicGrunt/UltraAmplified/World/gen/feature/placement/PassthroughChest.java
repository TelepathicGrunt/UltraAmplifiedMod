package net.TelepathicGrunt.UltraAmplified.World.gen.feature.placement;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

import com.mojang.datafixers.Dynamic;

import net.TelepathicGrunt.UltraAmplified.Config.ConfigUA;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class PassthroughChest extends Placement<NoPlacementConfig> {
   public PassthroughChest(Function<Dynamic<?>, ? extends NoPlacementConfig> configFactoryIn) {
		super(configFactoryIn);
	}

public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, NoPlacementConfig placementConfig, BlockPos pos) {
      
	   //needed so we can prevent vanilla treasure chest from spawning if config is off
	   if(!ConfigUA.chestGeneration) {
		   return Stream.empty();
	   }
	   
	   return Stream.of(pos);
   }
}