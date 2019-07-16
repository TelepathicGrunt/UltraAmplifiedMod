package net.TelepathicGrunt.UltraAmplified.World.gen.feature;

import java.util.Random;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.SlabBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.storage.loot.LootTables;

public class DungeonsDesert extends Feature<NoFeatureConfig> 
{
    public DungeonsDesert(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	private static final Logger LOGGER = LogManager.getLogger();
    private static final BlockState CaveAir = Blocks.CAVE_AIR.getDefaultState();
    private static final BlockState SandStone = Blocks.SMOOTH_SANDSTONE.getDefaultState();
    private static final BlockState CreeperSandStone = Blocks.CHISELED_SANDSTONE.getDefaultState();
    private static final BlockState SlabBottom = Blocks.SANDSTONE_SLAB.getDefaultState();
    private static final BlockState SlabTop = Blocks.SANDSTONE_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.TOP);
    
    //only the mob spawner chance and what blocks the wall cannot replace was changed. Everything else is just the normal dungeon code.
    
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> changedBlock, Random rand, BlockPos position, NoFeatureConfig p_212245_5_)
    {
        int j = rand.nextInt(2) + 2;
        int xmin = -j - 1;
        int xmax = j + 1;
        int k1 = rand.nextInt(2) + 2;
        int zmin = -k1 - 1;
        int zmax = k1 + 1;
        int j2 = 0;
        int ceilingOpenings = 0;

        for (int k2 = xmin; k2 <= xmax; ++k2)
        {
            for (int l2 = -1; l2 <= 4; ++l2)
            {
                for (int i3 = zmin; i3 <= zmax; ++i3)
                {
                    BlockPos blockpos = position.add(k2, l2, i3);
                    Material material = worldIn.getBlockState(blockpos).getMaterial();
                    boolean flag = material.isSolid();

                    if (l2 == -1 && !flag)
                    {
                        return false;
                    }

                    if (l2 == 4 && !flag)
                    {
                    	ceilingOpenings++;
                    }

                    if ((k2 == xmin || k2 == xmax || i3 == zmin || i3 == zmax) && l2 == 0 && worldIn.isAirBlock(blockpos) && worldIn.isAirBlock(blockpos.up()))
                    {
                        ++j2;
                    }
                }
            }
        }

        if (j2 >= 1 && j2 <= 14 && ceilingOpenings < 14)
        {
            for (int x = xmin; x <= xmax; ++x)
            {
                for (int y = 4; y >= -1; --y)
                {
                    for (int z = zmin; z <= zmax; ++z)
                    {
                        BlockPos blockpos1 = position.add(x, y, z);

                        if (x != xmin && z != zmin && x != xmax && y != 5 && z != zmax && y != -1 )
                        {
                        	if(y == 4) {
                        		//ceiling
                            	if (rand.nextInt(4) == 0)
                            	{
                                	worldIn.setBlockState(blockpos1, SandStone, 2);
                            	}else 
                            	{
                                	worldIn.setBlockState(blockpos1, SlabTop, 2);
                            	}
                        	}
                        	else if (worldIn.getBlockState(blockpos1).getBlock() != Blocks.CHEST && worldIn.getBlockState(blockpos1).getBlock() != Blocks.SPAWNER)
                            {
                                worldIn.setBlockState(blockpos1, CaveAir, 2);
                            }
                        }
                        else if (blockpos1.getY() >= 0 && !worldIn.getBlockState(blockpos1.down()).getMaterial().isSolid())
                        {
                            worldIn.setBlockState(blockpos1, CaveAir, 2);
                        }
                        
                        //made sure the dungeon wall cannot replace other dungeon's mob spawner now.
                        else if (worldIn.getBlockState(blockpos1).getMaterial().isSolid() && worldIn.getBlockState(blockpos1).getBlock() != Blocks.CHEST && worldIn.getBlockState(blockpos1).getBlock() != Blocks.SPAWNER)
                        {
                            if (x != xmin && z != zmin && x != xmax && z != zmax && y == -1 && rand.nextInt(4) != 0)
                            {
                                worldIn.setBlockState(blockpos1, SlabBottom, 2);
                            }
                            else if(rand.nextInt(10) == 0)
                            {
                                worldIn.setBlockState(blockpos1, CreeperSandStone, 2);
                            }
                            else {
                            	 worldIn.setBlockState(blockpos1, SandStone, 2);
                            }
                        }
                    }
                }
            }

            for (int l3 = 0; l3 < 2; ++l3)
            {
                for (int j4 = 0; j4 < 3; ++j4)
                {
                    int l4 = position.getX() + rand.nextInt(j * 2 + 1) - j;
                    int i5 = position.getY();
                    int j5 = position.getZ() + rand.nextInt(k1 * 2 + 1) - k1;
                    BlockPos blockpos2 = new BlockPos(l4, i5, j5);

                    if (worldIn.isAirBlock(blockpos2))
                    {
                        int j3 = 0;

                        for (Direction Direction : Direction.Plane.HORIZONTAL)
                        {
                            if (worldIn.getBlockState(blockpos2.offset(Direction)).getMaterial().isSolid())
                            {
                                ++j3;
                            }
                        }

                        if (j3 == 1)
                        {
                        	worldIn.setBlockState(blockpos2, StructurePiece.func_197528_a(worldIn, blockpos2, Blocks.CHEST.getDefaultState()), 2); 
                        	LockableLootTileEntity.setLootTable(worldIn, rand, blockpos2, LootTables.CHESTS_SIMPLE_DUNGEON);
                            worldIn.setBlockState(blockpos2.down(), SandStone, 2);

                            break;
                        }
                    }
                }
            }

            worldIn.setBlockState(position, Blocks.SPAWNER.getDefaultState(), 2);
            TileEntity tileentity = worldIn.getTileEntity(position);
            worldIn.setBlockState(position.down(), SandStone, 2);

            if (tileentity instanceof MobSpawnerTileEntity)
            {
             ((MobSpawnerTileEntity)tileentity).getSpawnerBaseLogic().setEntityType(this.pickMobSpawner(worldIn, rand, position));
            }
            else
            {
                LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", new Object[] {Integer.valueOf(position.getX()), Integer.valueOf(position.getY()), Integer.valueOf(position.getZ())});
            }

            return true;
        }
        else
        {
            return false;
        }
    }
    

    /**
     * Randomly decides which spawner to use in a dungeon
     */
    private EntityType<?> pickMobSpawner(IWorld worldIn, Random rand, BlockPos position)
    {
    	
    	int roll = rand.nextInt(100);
    	
    	if(roll < 48) {
    		//48% chance
    		return EntityType.HUSK;
    	}
    	else if(roll < 73) {
    		//25% chance
        	return pickRandomDungeonMob(rand);
    	}
    	else if(roll < 98) {
    		//25% chance
    		return pickRandomDungeonMob(rand);
    	}
    	else if(roll == 98) {
    		//1% chance
        	return EntityType.LLAMA;
        }
    	else {
    		//1% chance
      		return EntityType.ILLUSIONER;
    	}
    }
    
    private EntityType<?> pickRandomDungeonMob(Random p_201043_1_) {
        return net.minecraftforge.common.DungeonHooks.getRandomDungeonMob(p_201043_1_);
     }
}
