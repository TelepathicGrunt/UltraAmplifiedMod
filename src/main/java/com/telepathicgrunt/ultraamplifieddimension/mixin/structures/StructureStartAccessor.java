package com.telepathicgrunt.ultraamplifieddimension.mixin.structures;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.world.gen.feature.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Random;

@Mixin(StructureStart.class)
public interface StructureStartAccessor {
    @Invoker("func_214626_a")
    void uad_callFunc_214626_a(Random p_214626_1_, int p_214626_2_, int p_214626_3_);

    @Accessor("rand")
    SharedSeedRandom uad_getRand();
}
