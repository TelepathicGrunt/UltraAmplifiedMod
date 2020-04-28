package net.telepathicgrunt.ultraamplified.world.feature.placement;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.placement.SimplePlacement;
import net.telepathicgrunt.ultraamplified.UltraAmplified;
import net.telepathicgrunt.ultraamplified.world.feature.config.CountRangeAndTypeConfig;


public class GeneralConfigHookupPlacement extends SimplePlacement<CountRangeAndTypeConfig>
{
	public GeneralConfigHookupPlacement(Function<Dynamic<?>, ? extends CountRangeAndTypeConfig> config)
	{
		super(config);
	}


	@Override
	public Stream<BlockPos> getPositions(Random rand, CountRangeAndTypeConfig config, BlockPos pos)
	{
		int count;

		//hacky workaround as biome/feature registration happens at MC startup before the config file is loaded in a world. 
		//We have to read the config file here as placement is being found to have the config file be read in real time.
		switch (config.type)
		{
			case GLOWSTONE_VARIANT_PATCH:
				float result = UltraAmplified.UAConfig.glowstoneVariantsSpawnrate.get() * config.countModifier;
				//if the resulting count is less than one, then we switch to probability
				if (result < 1 && rand.nextFloat() < UltraAmplified.UAConfig.glowstoneVariantsSpawnrate.get() * config.countModifier)
				{
					count = 1;
				}
				else
				{
					count = (int) result;
				}

				break;

			case GLOWSTONE:
				count = (int) (UltraAmplified.UAConfig.glowstoneSpawnrate.get() * config.countModifier);
				break;

			case MAGMA:
				count = (int) (UltraAmplified.UAConfig.magmaSpawnrate.get() * config.countModifier);
				break;

			case QUARTZ:
				count = (int) (UltraAmplified.UAConfig.quartzOreSpawnrate.get() * config.countModifier);
				break;

			case EMERALD:
				count = (int) ((20 + rand.nextInt(35)) * ((double) (UltraAmplified.UAConfig.emeraldOreSpawnrate.get() * config.countModifier) / 100));
				break;

			case SILVERFISH:
				count = (int) (UltraAmplified.UAConfig.silverfishSpawnrate.get() * config.countModifier);
				break;

			case COAL:
				count = (int) (UltraAmplified.UAConfig.coalOreSpawnrate.get() * config.countModifier);
				break;

			case IRON:
				count = (int) (UltraAmplified.UAConfig.ironOreSpawnrate.get() * config.countModifier);
				break;

			case GOLD:
				count = (int) (UltraAmplified.UAConfig.goldOreSpawnrate.get() * config.countModifier);
				break;

			case REDSTONE:
				count = (int) (UltraAmplified.UAConfig.redstoneOreSpawnrate.get() * config.countModifier);
				break;

			case DIAMOND:
				count = (int) (UltraAmplified.UAConfig.diamondOreSpawnrate.get() * config.countModifier);
				break;

			default:
				count = (int) config.countModifier;
				break;
		}

		return IntStream.range(0, count).mapToObj((p_215061_3_) ->
		{
			int x = rand.nextInt(16);

			int y = rand.nextInt(config.maximum - config.topOffset) + (config.sealevelBased ? UltraAmplified.UAConfig.seaLevel.get() - config.bottomOffset : config.bottomOffset);

			int z = rand.nextInt(16);
			return pos.add(x, y, z);
		});
	}
}