package net.telepathicgrunt.ultraamplified.world.feature;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.HugeTreesFeature;

public class BirchMTree extends HugeTreesFeature<HugeTreeFeatureConfig> {
    
    public BirchMTree(Function<Dynamic<?>, ? extends HugeTreeFeatureConfig> p_i225808_1_) {
        super(p_i225808_1_);
     }

    public boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> p_225557_4_, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBox, HugeTreeFeatureConfig p_225557_7_) 
    {
        int height = this.func_227256_a_(rand, p_225557_7_);
        IWorld world = (IWorld) worldIn;
        
        //checks to see if there is room to generate tree
        if (!this.hasRoom(worldIn, position, height+8, p_225557_7_)) {
            return false;
         } 
        else {
    		//adds the leaves on crown
            this.createCrown(worldIn, position.getX(), position.getZ(), position.getY() + height - 4, rand, boundingBox, p_225557_4_, p_225557_7_);

            //adds the 2 by 2 wood trunk
            for (int currentHeight = 0; currentHeight < height; ++currentHeight)
            {
                BlockState iblockstate = world.getBlockState(position.up(currentHeight));

                if (iblockstate.getMaterial() == Material.AIR || iblockstate.getMaterial() == Material.LEAVES)
                {
                    this.func_227216_a_(worldIn, rand, position.up(currentHeight), p_225557_4_, boundingBox, p_225557_7_);
                }

                if (currentHeight < height - 1)
                {
                    iblockstate = world.getBlockState(position.add(1, currentHeight, 0));

                    if (iblockstate.getMaterial() == Material.AIR || iblockstate.getMaterial() == Material.LEAVES)
                    {
                        this.func_227216_a_(worldIn, rand, position.add(1, currentHeight, 0), p_225557_4_, boundingBox, p_225557_7_);
                    }

                    iblockstate = world.getBlockState(position.add(1, currentHeight, 1));

                    if (iblockstate.getMaterial() == Material.AIR || iblockstate.getMaterial() == Material.LEAVES)
                    {
                        this.func_227216_a_(worldIn, rand, position.add(1, currentHeight, 1), p_225557_4_, boundingBox, p_225557_7_);
                    }

                    iblockstate = world.getBlockState(position.add(0, currentHeight, 1));

                    if (iblockstate.getMaterial() == Material.AIR || iblockstate.getMaterial() == Material.LEAVES)
                    {
                        this.func_227216_a_(worldIn, rand, position.add(0, currentHeight, 1), p_225557_4_, boundingBox, p_225557_7_);
                    }
                }
            }
        	
        	
	        return true;
	     }
    }

    //this is set so that the crown is leaves in a cone shape 
    private void createCrown(IWorldGenerationReader worldIn, int x, int z, int y, Random rand, MutableBoundingBox p_214596_7_, Set<BlockPos> p_214596_8_, HugeTreeFeatureConfig p_227252_7_)
    {
        int depthOfLeaves = p_227252_7_.field_227276_b_ - (rand.nextInt(5) + 10);
        int currentRadius = 0;

        for (int currentHeight = y - depthOfLeaves; currentHeight <= y+5; ++currentHeight)
        {
            int heightDiff = y - currentHeight;
            int radius = MathHelper.floor((float)heightDiff / (float)depthOfLeaves * 2F);
            this.func_227255_a_(worldIn, rand, new BlockPos(x, currentHeight, z), radius + (int)((heightDiff > 0 && radius == currentRadius && (currentHeight & 1) == 0 ? 0.5 : 1)*3.7), p_214596_8_, p_214596_7_, p_227252_7_);
            currentRadius = radius;
        }
    }
}