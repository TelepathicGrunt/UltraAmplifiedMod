package net.TelepathicGrunt.UltraAmplified.World.gen.feature;

import java.util.Random;

import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class WorldGenVinesShortUA extends Feature<NoFeatureConfig> 
{
	   
	public boolean func_212245_a(IWorld worldIn, IChunkGenerator<? extends IChunkGenSettings> changedBlocks, Random rand, BlockPos position, NoFeatureConfig config) {
	
		//generates vines from given position down 6 blocks if path is clear and the given position is valid
		//Also won't generate vines below Y = 15.
		int length = 0;
		
		for (; position.getY() > 15 && length < 6; position = position.down()) 
		{
			if (worldIn.isAirBlock(position)) 
			{
				for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) 
				{
					IBlockState iblockstate = Blocks.VINE.getDefaultState().with(BlockVine.getPropertyFor(enumfacing), Boolean.valueOf(true));
					if (iblockstate.isValidPosition(worldIn, position)) 
					{
						worldIn.setBlockState(position, iblockstate, 2);
						break;
					} 
					else if (worldIn.getBlockState(position.up()).getBlock() == Blocks.VINE) 
					{
						worldIn.setBlockState(position, worldIn.getBlockState(position.up()), 2);
						length++;
						break;
					}
				}
			}
		}

		return true;
	}
}