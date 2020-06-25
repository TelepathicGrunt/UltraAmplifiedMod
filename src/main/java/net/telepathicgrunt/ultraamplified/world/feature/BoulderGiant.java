package net.telepathicgrunt.ultraamplified.world.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.Feature;
import net.telepathicgrunt.ultraamplified.UltraAmplified;


public class BoulderGiant extends Feature<BlockBlobConfig>
{
    public BoulderGiant(Function<Dynamic<?>, ? extends BlockBlobConfig> configFactory) {
	super(configFactory);
    }

    private final static Block mossyCobblestone = Blocks.MOSSY_COBBLESTONE;
    private final static Block cobblestone = Blocks.COBBLESTONE;
    private final static Block andesite = Blocks.ANDESITE;
    private final static Block coalOre = Blocks.COAL_ORE;
    private final static Block ironOre = Blocks.IRON_ORE;
    private final static Block diamondOre = Blocks.DIAMOND_ORE;
    private final static int startRadius = 4;


    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkgen, Random rand, BlockPos position, BlockBlobConfig config) {

	BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable(position);
	BlockState blockState = world.getBlockState(blockpos$Mutable);

	// Will keeps moving down position until it finds valid ground to generate on while ignoring other boulders
	while (blockpos$Mutable.getY() >= 6) {
	    if (blockState.getMaterial() == Material.AIR || (blockState.getBlock() != Blocks.GRASS_BLOCK && !isDirt(blockState.getBlock()))) {
		// block was air or a non-dirt/grass block. Thus move down one.
		blockpos$Mutable.move(Direction.DOWN);
		blockState = world.getBlockState(blockpos$Mutable);
	    }
	    else {
		break; // We hit a valid spot to generate a boulder, time to exit loop
	    }
	}
	

	// if the height is too low or high, just quit.
	if (blockpos$Mutable.getY() <= 6 || blockpos$Mutable.getY() >= 250) {
	    return false;
	}
	

	// we are at a valid spot to generate a boulder now. Begin generation.
	for (int currentCount = 0; startRadius >= 0 && currentCount < 3; ++currentCount) {
	    int x = startRadius + rand.nextInt(2);
	    int y = startRadius + rand.nextInt(2);
	    int z = startRadius + rand.nextInt(2);
	    float calculatedDistance = (x + y + z) * 0.333F + 0.5F;

	    for (BlockPos blockpos : BlockPos.getAllInBoxMutable(blockpos$Mutable.add(-x, -y, -z), blockpos$Mutable.add(x, y, z))) {
		if (blockpos.distanceSq(blockpos$Mutable) <= calculatedDistance * calculatedDistance) {
		    // adds the blocks for generation in this boulder
		    // note, if user turns off an ore, that ore's chance is dumped into the below ore for generation
		    int randomChance = rand.nextInt(1400);

		    // 2/1400th chance for diamond ore
		    if (UltraAmplified.UAFeaturesConfig.diamondOreSpawnrate.get() != 0 && randomChance <= 1) {
			world.setBlockState(blockpos, diamondOre.getDefaultState(), 4);
		    }

		    // 48/1400th chance for iron ore
		    else if (UltraAmplified.UAFeaturesConfig.ironOreSpawnrate.get() != 0 && randomChance <= 50) {
			world.setBlockState(blockpos, ironOre.getDefaultState(), 4);
		    }

		    // 82/1400th chance for coal ore
		    else if (UltraAmplified.UAFeaturesConfig.coalOreSpawnrate.get() != 0 && randomChance <= 130) {
			world.setBlockState(blockpos, coalOre.getDefaultState(), 4);
		    }

		    // 398/1400th chance for andesite
		    else if (randomChance <= 480) {
			world.setBlockState(blockpos, andesite.getDefaultState(), 4);
		    }

		    // 352/1400th chance for cobblestone
		    else if (randomChance <= 750) {
			world.setBlockState(blockpos, cobblestone.getDefaultState(), 4);
		    }

		    // 650/1400th chance for mossyCobblestone
		    else {
			world.setBlockState(blockpos, mossyCobblestone.getDefaultState(), 4);
		    }
		}
	    }
	    blockpos$Mutable.move(-(startRadius + 1) + rand.nextInt(2 + startRadius * 2), 0 - rand.nextInt(2), -(startRadius + 1) + rand.nextInt(2 + startRadius * 2));

	}
	// finished generating the boulder
	return true;
    }
}
