package net.telepathicgrunt.ultraamplified.world.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.Feature;
import net.telepathicgrunt.ultraamplified.UltraAmplified;


public class BoulderTiny extends Feature<BlockBlobConfig>
{
	public BoulderTiny(Function<Dynamic<?>, ? extends BlockBlobConfig> configFactory)
	{
		super(configFactory);
	}

	private final static BlockState mossyCobblestone = Blocks.MOSSY_COBBLESTONE.getDefaultState();
	private final static BlockState cobblestone = Blocks.COBBLESTONE.getDefaultState();
	private final static BlockState andesite = Blocks.ANDESITE.getDefaultState();
	private final static BlockState coalOre = Blocks.COAL_ORE.getDefaultState();
	private final static BlockState ironOre = Blocks.IRON_ORE.getDefaultState();
	private final static int startRadius = 0;


	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random rand, BlockPos position, BlockBlobConfig p_212245_5_)
	{

		BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable(position);
		Block block = world.getBlockState(blockpos$Mutable).getBlock();
		Block block2 = world.getBlockState(blockpos$Mutable.add(-1, 0, -1)).getBlock();

		//boulder can only generate on grass/dirt
		if ((block != Blocks.GRASS_BLOCK && block != Blocks.PODZOL && !isDirt(block)) || (block2 != Blocks.GRASS_BLOCK && block2 != Blocks.PODZOL && !isDirt(block2)))
		{
			return false;
		}

		for (int currentCount = 0; startRadius >= 0 && currentCount < 3; ++currentCount)
		{
			int x = startRadius + rand.nextInt(2);
			int y = startRadius + rand.nextInt(2);
			int z = startRadius + rand.nextInt(2);
			float calculatedDistance = (x + y + z) * 0.333F + 0.5F;

			for (BlockPos blockpos : BlockPos.getAllInBoxMutable(blockpos$Mutable.add(-x, -y, -z), blockpos$Mutable.add(x, y, z)))
			{
				if (blockpos.distanceSq(blockpos$Mutable) <= calculatedDistance * calculatedDistance)
				{
					//adds the blocks for generation in this boulder
					//note, if user turns off an ore, that ore's chance is dumped into the below ore for generation
					int randomChance = rand.nextInt(1400);

					// 40/1400th chance for iron ore
					if (UltraAmplified.UAConfig.ironOreSpawnrate.get() != 0 && randomChance <= 40)
					{
						world.setBlockState(blockpos.up(), ironOre, 4);
					}

					// 60/1400th chance for coal ore
					else if (UltraAmplified.UAConfig.coalOreSpawnrate.get() != 0 && randomChance <= 100)
					{
						world.setBlockState(blockpos.up(), coalOre, 4);
					}

					// 300/1400th chance for andesite
					else if (randomChance <= 400)
					{
						world.setBlockState(blockpos.up(), andesite, 4);
					}

					// 300/1400th chance for cobblestone
					else if (randomChance <= 700)
					{
						world.setBlockState(blockpos.up(), cobblestone, 4);
					}

					// 700/1400th chance for mossyCobblestone
					else
					{
						world.setBlockState(blockpos.up(), mossyCobblestone, 4);
					}
				}
			}
			blockpos$Mutable.move(-(startRadius + 1) + rand.nextInt(2 + startRadius * 2), -rand.nextInt(2), -(startRadius + 1) + rand.nextInt(2 + startRadius * 2));
		}

		//finished generating the boulder
		return true;
	}
}
