package net.telepathicgrunt.ultraamplified.world.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.telepathicgrunt.ultraamplified.config.ConfigUA;
import net.telepathicgrunt.ultraamplified.world.biome.BiomeInit;


public class UnderwaterMagmaForNether extends Feature<NoFeatureConfig>
{
	public UnderwaterMagmaForNether(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn)
	{
		super(configFactoryIn);
	}

	private final static BlockState MAGMA = Blocks.MAGMA_BLOCK.getDefaultState();


	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> chunkSettings, Random random, BlockPos pos, NoFeatureConfig configBlock)
	{

		//set y to 0
		pos.down(pos.getY());
		BlockState currentblock;
		Biome netherBiome = null;
		boolean hasNetherBiome = false;
		BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable(pos);

		//checks to see if there is an nether biome in this chunk
		//breaks out of nested loop if nether is found
		for (int x = 0; x < 16; ++x)
		{
			for (int z = 0; z < 16; ++z)
			{
				//only check along chunk edges for better performance
				if(!(z == 0 || z == 16 || x == 0 || x == 16)) {
					continue;
				}
				
				netherBiome = worldIn.func_226691_t_(blockpos$Mutable.add(x, 0, z));
				if (netherBiome == BiomeInit.NETHERLAND)
				{
					hasNetherBiome = true;
					x = 16;
					break;
				}
			}
		}

		//does not do anything if there is no nether biome
		if (!hasNetherBiome)
		{
			return false;
		}

		//ocean nether was found and thus, is not null. Can safely now add the magma layer
		for (int x = 0; x < 16; ++x)
		{
			for (int z = 0; z < 16; ++z)
			{

				netherBiome = worldIn.func_226691_t_(blockpos$Mutable.add(x, 0, z));
				if (netherBiome != BiomeInit.NETHERLAND)
				{
					continue;
				}
				
				currentblock = worldIn.getBlockState(blockpos$Mutable.add(x, ConfigUA.seaLevel - 7, z));

				//if water, place magma block
				if (currentblock.getMaterial() == Material.WATER)
				{
					worldIn.setBlockState(blockpos$Mutable.add(x, ConfigUA.seaLevel - 7, z), MAGMA, 3);
				}
			}
		}
		
		return true;
	}
}