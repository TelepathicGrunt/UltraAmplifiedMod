package net.telepathicgrunt.ultraamplified.world.feature.carver;

import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.telepathicgrunt.ultraamplified.config.ConfigUA;
import net.telepathicgrunt.ultraamplified.world.biome.BiomeInit;

public class CaveCavityCarver extends WorldCarver<ProbabilityConfig> {

	private final float[] ledgeWidthArrayYIndex = new float[1024];
	protected static OctavesNoiseGenerator noiseGen;
	protected static final BlockState STONE = Blocks.STONE.getDefaultState();
	protected static final BlockState LAVA = Blocks.LAVA.getDefaultState();
	protected static final BlockState WATER = Blocks.WATER.getDefaultState();
	protected static final BlockState MAGMA = Blocks.MAGMA_BLOCK.getDefaultState();
	protected static final BlockState OBSIDIAN = Blocks.OBSIDIAN.getDefaultState();

	// Blocks that we can carve out.
	private static final Map<BlockState, BlockState> canReplaceMap = createMap();
	private static Map<BlockState, BlockState> createMap() {
		Map<BlockState, BlockState> result = new HashMap<BlockState, BlockState>();
		result.put(Blocks.NETHERRACK.getDefaultState(), Blocks.NETHERRACK.getDefaultState());
		result.put(Blocks.ICE.getDefaultState(), Blocks.ICE.getDefaultState());
		result.put(Blocks.SNOW_BLOCK.getDefaultState(), Blocks.ICE.getDefaultState());
		result.put(Blocks.END_STONE.getDefaultState(), Blocks.END_STONE.getDefaultState());
		result.put(Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState());
		return Collections.unmodifiableMap(result);
	}
	
	// Used to keep track of what block to use to fill in certain air/liquids
	protected BlockState replacementBlock = Blocks.STONE.getDefaultState();
	
	// Associates what block to use when in which biome when setting the replacementBlock.
    private static Map<Biome, BlockState> fillerBiomeMap;
	
    
	public CaveCavityCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> probabilityConfig, int maximumHeight) {
		super(probabilityConfig, maximumHeight);
		
	}

	
	/**
	 * Sets the internal seed for this carver after we get the world seed. 
	 * (Based on Nether's surface builder code)
	 */
	public static void setSeed(long seed) {
		if (noiseGen == null) {
			noiseGen = new OctavesNoiseGenerator(new SharedSeedRandom(seed), 3, 0);
		}
	}
	
	/**
	 * Have to make this map much later instead of in constructor since 
	 * the biomes needs to be initialized first and that's delayed a bit
	 */
	public static void setFillerMap() {
		if (fillerBiomeMap == null) {
			fillerBiomeMap = new HashMap<Biome, BlockState>();

			fillerBiomeMap.put(BiomeInit.NETHER, Blocks.NETHERRACK.getDefaultState()); 
			fillerBiomeMap.put(BiomeInit.ICE_MOUNTAIN, Blocks.ICE.getDefaultState()); 
			fillerBiomeMap.put(BiomeInit.ICE_SPIKES, Blocks.ICE.getDefaultState()); 
			fillerBiomeMap.put(BiomeInit.DEEP_FROZEN_OCEAN, Blocks.ICE.getDefaultState()); 
			fillerBiomeMap.put(BiomeInit.FROZEN_OCEAN, Blocks.ICE.getDefaultState()); 
	        fillerBiomeMap.put(BiomeInit.BARREN_END_FIELD, Blocks.END_STONE.getDefaultState()); 
	        fillerBiomeMap.put(BiomeInit.END, Blocks.END_STONE.getDefaultState()); 
		}
	}

	/**
	 * Checks whether the entire cave can spawn or not. (Not the individual parts)
	 */
	public boolean shouldCarve(Random randomIn, int chunkX, int chunkZ, ProbabilityConfig config) {
		setSeed(randomIn.nextLong());
		return randomIn.nextFloat() <= (float) (ConfigUA.caveCavitySpawnrate) / 1000f;
	}

	
	public boolean func_225555_a_(IChunk region, Function<BlockPos, Biome> biomeBlockPos, Random random, int seaLevel, int chunkX, int chunkZ, int originalX,
			int originalZ, BitSet mask, ProbabilityConfig config) {

		int i = (this.func_222704_c() * 2 - 1) * 16;
		double xpos = (double) (chunkX * 16 + random.nextInt(16));
		double height = (double) (random.nextInt(random.nextInt(2) + 1) + 34);
		double zpos = (double) (chunkZ * 16 + random.nextInt(16));
		float xzNoise2 = random.nextFloat() * ((float) Math.PI * 1F);
		float xzCosNoise = (random.nextFloat() - 0.5F) / 16.0F;
		float widthHeightBase = (random.nextFloat() + random.nextFloat()) / 16; // width And Height Modifier
		this.func_222729_a(region, biomeBlockPos, random.nextLong(), seaLevel, originalX, originalZ, xpos, height, zpos,
				widthHeightBase, xzNoise2, xzCosNoise, 0, i, random.nextDouble() + 20D, mask);
		return true;
	}

	private void func_222729_a(IChunk worldIn, Function<BlockPos, Biome> biomeBlockPos, long randomSeed, int seaLevel, int mainChunkX, int mainChunkZ,
			double randomBlockX, double randomBlockY, double randomBlockZ, float widthHeightBase, float xzNoise2,
			float xzCosNoise, int startIteration, int maxIteration, double heightMultiplier, BitSet mask) {
		
		Random random = new Random(randomSeed);
		float ledgeWidth = 1.0F;

		// CONTROLS THE LEDGES' WIDTH! FINALLY FOUND WHAT THIS JUNK DOES
		for (int currentHeight = 0; currentHeight <= 70; ++currentHeight) {
			
			//attempt at creating dome ceilings
			if(currentHeight > 44 && currentHeight < 60) {
				ledgeWidth = 1.0F + random.nextFloat()*0.3f; 
				ledgeWidth = (float) (ledgeWidth + Math.max(0, Math.pow((currentHeight-44) * 0.15F, 2)));
			}
			
			//normal ledges on walls
			else {
				if (currentHeight == 0 || random.nextInt(3) == 0) {
					ledgeWidth = 1.0F + random.nextFloat() * 0.5F; 
				}
			}

			this.ledgeWidthArrayYIndex[currentHeight] = ledgeWidth;
		}

		
		float f4 = 0.0F;
		float f1 = 0.0F;

		//creates "rooms" which are giant sections of the cave. (cave is made up of many rooms)
		for (int currentRoom = startIteration; currentRoom < maxIteration; ++currentRoom) 
		{
			double placementXZBound = 2D
					+ (double) (MathHelper.sin((float) currentRoom * (float) Math.PI / (float) maxIteration)
							* widthHeightBase);
			double placementYBound = placementXZBound * heightMultiplier;
			placementXZBound = placementXZBound * 32D; // thickness of the "room" itself
			placementYBound = placementYBound * 2.2D;
			xzCosNoise = xzCosNoise * 0.5F + f1 * 0.04F;
			xzNoise2 += f4 * 0.05F;
			f1 = f1 * 0.8F;
			f4 = f4 * 0.5F;
			f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.5F;
			f4 = f4 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
			
			//No idea what this is for yet
			if (!this.func_222702_a(mainChunkX, mainChunkZ, randomBlockX, randomBlockZ, currentRoom, maxIteration,
					widthHeightBase)) {
				return;
			}

			this.carveAtTarget(worldIn, biomeBlockPos, random, randomSeed, mainChunkX, mainChunkZ, randomBlockX, randomBlockY,
					randomBlockZ, placementXZBound, placementYBound, mask);

		}

	}

	protected boolean carveAtTarget(IChunk worldIn, Function<BlockPos, Biome> biomeBlockPos, Random random, long seed, int mainChunkX, int mainChunkZ,
			double xRange, double yRange, double zRange, double placementXZBound, double placementYBound, BitSet mask) {
		
		
		double xPos = (double) (mainChunkX * 16 + 8);
		double zPos = (double) (mainChunkZ * 16 + 8);
		double multipliedXZBound = placementXZBound *  2.0D;

		if (!(xRange < xPos - 16.0D - multipliedXZBound) 
			&& !(zRange < zPos - 16.0D - multipliedXZBound)
			&& !(xRange > xPos + 16.0D + multipliedXZBound)
			&& !(zRange > zPos + 16.0D + multipliedXZBound)) {
			
			int xMin = Math.max(MathHelper.floor(xRange - placementXZBound) - mainChunkX * 16 - 1, 0);
			int xMax = Math.min(MathHelper.floor(xRange + placementXZBound) - mainChunkX * 16 + 1, 16);
			int yMin = Math.max(MathHelper.floor(yRange - placementYBound) - 1, 5);
			int yMax = Math.min(MathHelper.floor(yRange + placementYBound) + 1, this.maxHeight);
			int zMin = Math.max(MathHelper.floor(zRange - placementXZBound) - mainChunkZ * 16 - 1, 0);
			int zMax = Math.min(MathHelper.floor(zRange + placementXZBound) - mainChunkZ * 16 + 1, 16);
			if (xMin <= xMax && yMin <= yMax && zMin <= zMax) {
				boolean flag = false;
				BlockState currentBlockstate;
				BlockState aboveBlockstate;
				BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable();
				BlockPos.Mutable blockpos$Mutableup = new BlockPos.Mutable();
				BlockPos.Mutable blockpos$Mutabledown = new BlockPos.Mutable();

				for (int smallX = xMin; smallX < xMax; ++smallX) {
					int x = smallX + mainChunkX * 16;
					double xSquaringModified = ((double) x + 0.5D - xRange) / placementXZBound;

					for (int smallZ = zMin; smallZ < zMax; ++smallZ) {
						int z = smallZ + mainChunkZ * 16;
						double zSquaringModified = ((double) z + 0.5D - zRange) / placementXZBound;
						double xzSquaredModified = xSquaringModified * xSquaringModified + zSquaringModified * zSquaringModified;
						
						if (xzSquaredModified < 1.0D) {
							int yMaxSum = (int) (yMax - ((1 + random.nextFloat()) * random.nextFloat() * 20));

							if (yMaxSum < yMin) {
								continue;
							}

							blockpos$Mutable.setPos(x, 60, z);
							replacementBlock = fillerBiomeMap.get(biomeBlockPos.apply(blockpos$Mutable));
							if (replacementBlock == null) {
								replacementBlock = STONE;
							}

							for (int y = yMaxSum; y > yMin; y--) {

								
								// sets a trial and error value that widens base of pillar and makes paths
								// through lava that look good
								double yPillarModifier = y;
								
								//makes pillar widen at top
								if(y >= 40) {
									yPillarModifier = 40 - (yPillarModifier-40)*2D;
									
									//prevents it from widening too fast and end up lowering the ceiling
									if(yPillarModifier < 10) {
										yPillarModifier += (10-yPillarModifier)*0.75D;
									}
								}
								
								//Use this value to control how much lava is shown at bottom
								//Increase constant for less lava
								yPillarModifier -= 4.0D;

								if (y < 10) {
									// creates a deep lava pool that starts 2 blocks deep automatically at edges.
									yPillarModifier++;
								} else if (yPillarModifier <= 0) {
									// prevents divide by 0 or by negative numbers (decreasing negative would make
									// more terrain be carved instead of not carve)
									yPillarModifier = 0.00001D;
								}
								
								
								//limits calling pillar and stalagmite perlin generators to reduce gen time
								if(y < 60) 
								{
									// Creates pillars that are widen at bottom.
									//
									// Perlin field creates the main body for pillar by stepping slowly through x
									// and z and extremely slowly through y.
									// Then subtracted modified target height to flatten bottom of pillar to
									// make a path through lava.
									// Next, adds a random value to add some noise to the pillar.
									// And lastly, sets the greater than value to be very low so most of the cave gets carved
									// out.
									//
									//Increase step in X and Z to make pillars less frequent but thicker
									boolean flagPillars = noiseGen.func_205563_a((double) x * 0.12D, (double) z * 0.12D,
											y * 0.035D) * 10.0D - (15 / yPillarModifier) + random.nextDouble() * 0.1D > -2.0D;
	
									if(!flagPillars) {
										//skip position if we are in pillar space
										continue;
									}
									

									//limits calling stalagmite perlin generators to reduce gen time
									if(y > 32) 
									{
										// Creates large stalagmites that cannot reach floor of cavern.
										//
										// Perlin field creates the main stalagmite shape and placement by stepping
										// though x and z pretty fast and through y very slowly.
										// Then adds 400/y so that as the y value gets lower, the more area gets carved
										// which sets the limit on how far down the stalagmites can go.
										// Next, add a random value to add some noise to the pillar.
										// And lastly, sets the greater than value to be high so more stalagmites can be made
										// while the 400/y has already carved out the rest of the cave.
										//
										//Increase step in X and Z to decrease number of stalagmites and make them slightly thicker
										double stalagmiteDouble = noiseGen.func_205563_a((double) x * 0.33125D,
												(double) z * 0.33125D, y * 0.06D) * 10.0D + (360D / (y));
										
										//adds more tiny stalagmites to ceiling
										if(y>54) {
											stalagmiteDouble -= (y-53D)/3D;
										}
										
										//increase constant to make stalagmites smaller and thinner
										boolean flagStalagmites = stalagmiteDouble > 6.0D;

										if(!flagStalagmites) {
											//skip position if we are in stalagmite space
											continue;
										}
									}
								}
								
								
								double ySquaringModified = ((double) (y - 1) + 0.5D - yRange) / placementYBound;
								
								// Where the pillar flag and stalagmite flag both flagged this block to be
								// carved, begin carving.
								// Thus the pillar and stalagmite is what is left after carving.
								if (xzSquaredModified * this.ledgeWidthArrayYIndex[y - 1] 
										+ ySquaringModified * ySquaringModified / 6.0D + random.nextFloat() * 0.1f < 1.0D) {
									
									blockpos$Mutable.setPos(x, y, z);
									currentBlockstate = worldIn.getBlockState(blockpos$Mutable);
									blockpos$Mutableup.setPos(blockpos$Mutable).move(Direction.UP);
									blockpos$Mutabledown.setPos(blockpos$Mutable).move(Direction.DOWN);
									aboveBlockstate = worldIn.getBlockState(blockpos$Mutableup);

									
									if(y >= 60) {
										//Creates the messy but cool plateau of stone on the ocean floor 
										//above this cave to help players locate caves when exploring
										//ocean biomes. Also helps to break up the blandness of ocean
										//floors.
										
										if (!currentBlockstate.getFluidState().isEmpty()) {
											worldIn.setBlockState(blockpos$Mutable, replacementBlock, false);
										} else if (!aboveBlockstate.getFluidState().isEmpty()) {
											worldIn.setBlockState(blockpos$Mutable, replacementBlock, false);
											worldIn.setBlockState(blockpos$Mutableup, replacementBlock, false);
											worldIn.setBlockState(blockpos$Mutabledown, replacementBlock, false);
											flag = true;
										}
									} else if (this.canCarveBlock(currentBlockstate, aboveBlockstate)
											|| canReplaceMap.containsKey(currentBlockstate)) {
										
										if (y < 11) {
											worldIn.setBlockState(blockpos$Mutable, LAVA, false);
										} else {
											//carves the cave
											worldIn.setBlockState(blockpos$Mutable, AIR.getBlockState(), false);
										}

										flag = true;
									}
									
								}
							}
						}
					}
				}

				return flag;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * MC doesn't seem to do anything with the returned value in the end. Strange.
	 * I wonder why.
	 */
	protected boolean func_222708_a(double p_222708_1_, double p_222708_3_, double p_222708_5_, int p_222708_7_) {
		return true;
		//return (p_222708_1_ * p_222708_1_ + p_222708_5_ * p_222708_5_) * (double) this.ledgeWidthArrayYIndex[p_222708_7_ - 1] + p_222708_3_ * p_222708_3_ / 6.0D >= 1.0D;
	}


}
