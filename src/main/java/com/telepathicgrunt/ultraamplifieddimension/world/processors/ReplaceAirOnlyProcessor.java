package com.telepathicgrunt.ultraamplifieddimension.world.processors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.ultraamplifieddimension.modInit.UADProcessors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import java.util.HashSet;

/**
 * For mimicking the dungeon look where they cannot replace air.
 */
public class ReplaceAirOnlyProcessor extends StructureProcessor {

    public static final Codec<ReplaceAirOnlyProcessor> CODEC  = RecordCodecBuilder.create((instance) -> instance.group(
            BlockState.CODEC.listOf()
                    .xmap(Sets::newHashSet, Lists::newArrayList)
                    .optionalFieldOf("blocks_to_always_place", new HashSet<>())
                    .forGetter((config) -> config.blocksToAlwaysPlace))
            .apply(instance, instance.stable(ReplaceAirOnlyProcessor::new)));

    public final HashSet<BlockState> blocksToAlwaysPlace;
    private ReplaceAirOnlyProcessor(HashSet<BlockState> blocksToAlwaysPlace) {
        this.blocksToAlwaysPlace = blocksToAlwaysPlace;
    }

    @Override
    public Template.BlockInfo func_230386_a_(IWorldReader worldView, BlockPos pos, BlockPos blockPos, Template.BlockInfo structureBlockInfoLocal, Template.BlockInfo structureBlockInfoWorld, PlacementSettings structurePlacementData) {

        if(!blocksToAlwaysPlace.contains(structureBlockInfoWorld.state)){
            BlockPos position = structureBlockInfoWorld.pos;
            BlockState worldState = worldView.getBlockState(position);
            BlockState aboveWorldState = worldView.getBlockState(position.up());

            if (worldState.isAir()&&
                !structureBlockInfoWorld.state.getBlock().isTileEntityProvider() &&
                !aboveWorldState.getBlock().isTileEntityProvider() &&
                !worldState.matchesBlock(Blocks.END_PORTAL))
            {
                structureBlockInfoWorld = new Template.BlockInfo(structureBlockInfoWorld.pos, worldState, null);
            }
            // Do not replace other dungeon's chests/spawners
            else if(worldState.getBlock().isTileEntityProvider()){
                structureBlockInfoWorld = new Template.BlockInfo(structureBlockInfoWorld.pos, worldState, null);
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected IStructureProcessorType<?> getType() {
        return UADProcessors.REPLACE_AIR_ONLY_PROCESSOR;
    }
}
