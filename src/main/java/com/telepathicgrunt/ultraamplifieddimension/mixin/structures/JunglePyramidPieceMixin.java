package com.telepathicgrunt.ultraamplifieddimension.mixin.structures;

import com.telepathicgrunt.ultraamplifieddimension.dimension.UADChunkGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.JunglePyramidPiece;
import net.minecraft.world.gen.feature.structure.StructureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(JunglePyramidPiece.class)
public abstract class JunglePyramidPieceMixin {
    
    /**
     * @author TelepathicGrunt
     * @reason Place Pyramids on top of land properly
     */
    @Inject(
            method = "func_230383_a_(Lnet/minecraft/world/ISeedReader;Lnet/minecraft/world/gen/feature/structure/StructureManager;Lnet/minecraft/world/gen/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/MutableBoundingBox;Lnet/minecraft/util/math/ChunkPos;Lnet/minecraft/util/math/BlockPos;)Z",
            at = @At(target = "Lnet/minecraft/world/gen/feature/structure/JunglePyramidPiece;fillWithRandomizedBlocks(Lnet/minecraft/world/ISeedReader;Lnet/minecraft/util/math/MutableBoundingBox;IIIIIIZLjava/util/Random;Lnet/minecraft/world/gen/feature/structure/StructurePiece$BlockSelector;)V",
                    value = "INVOKE",
                    ordinal = 0)
    )
    private void uad_fixedYHeightForUAD(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator, Random random, MutableBoundingBox mutableBoundingBox, ChunkPos chunkPos, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if(chunkGenerator instanceof UADChunkGenerator){
            MutableBoundingBox box = ((JunglePyramidPiece)(Object)this).getBoundingBox();
            box.offset(0, chunkGenerator.getHeight(box.minX + (box.getXSize() / 2), box.minZ + (box.getZSize() / 2), Heightmap.Type.WORLD_SURFACE_WG) - box.minY, 0);
        }
    }
}