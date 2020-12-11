package com.telepathicgrunt.ultraamplifieddimension.world.features;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.telepathicgrunt.ultraamplified.world.generation.BiomeGenHelper;

import java.util.Random;
import java.util.function.Function;


public class SnowIceLayerHandlerFeature extends Feature<NoFeatureConfig>
{

	public SnowIceLayerHandlerFeature(Codec<NoFeatureConfig> p_i51435_1_)
	{
		super(p_i51435_1_);
	}


	@Override
	public boolean generate(ISeedReader world, ChunkGenerator generator, Random random, BlockPos position, NoFeatureConfig config)
	{
		BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable();

		for (int xOffset = 0; xOffset < 16; xOffset++)
		{
			for (int zOffset = 0; zOffset < 16; zOffset++)
			{
				blockpos$Mutable.setPos(position).move(xOffset, 0, zOffset);

				Biome biome = world.getBiome(blockpos$Mutable);

				if (BiomeGenHelper.frozenBiomes.contains(biome))
				{
					net.telepathicgrunt.ultraamplified.world.feature.SnowIceAllLayer.place(world, generator, random, blockpos$Mutable, config, biome);
				}
				else if (BiomeGenHelper.coldOceanBiomes.contains(biome))
				{
					net.telepathicgrunt.ultraamplified.world.feature.SnowLayerColdOceanFeature.place(world, generator, random, blockpos$Mutable, config, biome);
				}
				else
				{
					SnowIceTopLayer.place(world, generator, random, blockpos$Mutable, config, biome);
				}
			}
		}

		return true;
	}
}