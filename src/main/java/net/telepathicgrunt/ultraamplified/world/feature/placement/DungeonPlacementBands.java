package net.telepathicgrunt.ultraamplified.world.feature.placement;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.telepathicgrunt.ultraamplified.UltraAmplified;


public class DungeonPlacementBands extends Placement<NoPlacementConfig>
{
	public DungeonPlacementBands(Function<Dynamic<?>, ? extends NoPlacementConfig> configFactory)
	{
		super(configFactory);
	}


	@Override
	public Stream<BlockPos> getPositions(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, NoPlacementConfig placementConfig, BlockPos pos)
	{
		int count = UltraAmplified.UAFeaturesConfig.dungeonSpawnrate.get();

		return IntStream.range(0, count).mapToObj((function) ->
		{
			int x = random.nextInt(16);
			int z = random.nextInt(16);
			int y;
			int rand = random.nextInt(100);

			//30% chance
			if (rand < 30)
			{
				y = random.nextInt(26) + 75;
				//range: 75 - 100
				//gap: 25
				//chance per pos: 1.200%
			}
			//35% chance
			else if (rand < 65)
			{
				y = random.nextInt(160) + 1;
				//range 1 - 160
				//gap: 160
				//chance per pos: 0.219%
			}
			//35%
			else
			{
				y = random.nextInt(86) + 160;
				//range: 160 - 245
				//gap: 85
				//chance per pos: 0.412%
			}

			return pos.add(x, y, z);
		});
	}
}