package com.telepathicgrunt.ultraamplifieddimension.modInit;


import com.telepathicgrunt.ultraamplifieddimension.UltraAmplifiedDimension;
import com.telepathicgrunt.ultraamplifieddimension.world.decorators.*;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UADPlacements {
    public static final DeferredRegister<Placement<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.DECORATORS, UltraAmplifiedDimension.MODID);

    public static final RegistryObject<Placement<LedgeSurfacePlacerConfig>> LEDGE_SURFACE_PLACER = DECORATORS.register("ledge_surface_placer", () -> new LedgeSurfacePlacer(LedgeSurfacePlacerConfig.CODEC));
    public static final RegistryObject<Placement<YOffsetPlacerConfig>> Y_OFFSET_PLACER = DECORATORS.register("y_offset_placer", () -> new OffsetPlacer(YOffsetPlacerConfig.CODEC));
    public static final RegistryObject<Placement<RangeValidationPlacerConfig>> RANGE_VALIDATION_PLACER = DECORATORS.register("range_validation_placer", () -> new RangeValidationPlacer(RangeValidationPlacerConfig.CODEC));
    public static final RegistryObject<Placement<WaterIceSurfaceConfig>> WATER_ICE_SURFACE_PLACER = DECORATORS.register("water_ice_surface_placer", () -> new WaterIceSurfacePlacer(WaterIceSurfaceConfig.CODEC));
    public static final RegistryObject<Placement<NonAirSurfaceLedgePlacerConfig>> NON_AIR_SURFACE_LEDGE_PLACER = DECORATORS.register("non_air_surface_ledge_placer", () -> new NonAirSurfaceLedgePlacer(NonAirSurfaceLedgePlacerConfig.CODEC));
}
