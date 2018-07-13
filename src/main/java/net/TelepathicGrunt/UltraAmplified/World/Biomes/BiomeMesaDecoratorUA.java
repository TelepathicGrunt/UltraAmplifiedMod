package net.TelepathicGrunt.UltraAmplified.World.Biomes;

import java.util.Random;

import net.TelepathicGrunt.UltraAmplified.UltraAmplified;
import net.TelepathicGrunt.UltraAmplified.Config.UAConfig;
import net.TelepathicGrunt.UltraAmplified.World.Biome.BiomeDecoratorUA;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BiomeMesaDecoratorUA extends BiomeDecoratorUA{

	private final WorldGenerator goldGen = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), 9);
    
	public void decorate(World worldIn, Random rand, Biome biome, BlockPos pos)
    {
		super.decorate(worldIn, rand, biome, pos);
        
		 for (int numPerChunk = 0; numPerChunk < this.chunkProviderSettingsUA.mesaGoldCount; ++numPerChunk)
         {
             int x = rand.nextInt(16);
             int y = rand.nextInt(246)+5;
             int z = rand.nextInt(16);
             
	         if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, goldGen,  pos.add(x, y, z), net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD))
	         this.goldGen.generate(worldIn, rand, pos.add(x,y,z));
         }
		 
		this.chunkPos = pos;
        this.genDecorations(biome, worldIn, rand);
        this.decorating = false;
    }
}
