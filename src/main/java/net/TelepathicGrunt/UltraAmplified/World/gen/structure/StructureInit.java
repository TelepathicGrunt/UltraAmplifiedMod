package net.TelepathicGrunt.UltraAmplified.World.gen.structure;

import java.lang.reflect.Method;

import net.minecraft.world.gen.feature.structure.StructureIO;

public class StructureInit {

	static Method registerStructure;
	
	public static void initializeStructures() {
			StructureIO.registerStructure(MineshaftUA.Start.class, "Mineshaft UA");
			StructureIO.registerStructure(EndCityUA.Start.class, "End City UA");
			StructureIO.registerStructure(NetherBridgeUA.Start.class, "Nether Fortress UA");
			StructureIO.registerStructure(WoodlandMansionUA.Start.class, "Woodland Mansion UA"); // Calls vanilla pieces so we don't need to register pieces here
			StructureIO.registerStructure(StrongholdUA.Start.class, "Stronghold UA");
			MineshaftPiecesUA.registerStructurePieces();
			EndCityPiecesUA.registerPieces();
			NetherBridgePiecesUA.registerStructurePieces();
			StrongholdPiecesUA.registerStrongholdPieces();
	}
}