package net.telepathicgrunt.ultraamplified.world.feature.structure;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.telepathicgrunt.ultraamplified.UltraAmplified;


public class IceSpikeTemplePiecesUA
{
    /**
     * --------------------------------------------------------------------------
     * |									|
     * |	HELLO READERS! IF YOU'RE HERE, YOU'RE PROBABLY			|
     * |	LOOKING FOR A TUTORIAL ON HOW TO DO STRUCTURES			|
     * |									|
     * -------------------------------------------------------------------------
     * 
     * Don't worry, I actually have a structure tutorial
     * mod already setup for you to check out! It's full
     * of comments on what does what and how to make structures.
     * 
     * Here's the link! https://github.com/TelepathicGrunt/StructureTutorialMod
     * 
     * Good luck and have fun modding!
     */
	private static final ResourceLocation CENTER = new ResourceLocation(UltraAmplified.MODID + ":ice_spike_temple_center");
	private static final ResourceLocation CENTER_TOP = new ResourceLocation(UltraAmplified.MODID + ":ice_spike_temple_center_top");
	private static final ResourceLocation LEFT_BODY = new ResourceLocation(UltraAmplified.MODID + ":ice_spike_temple_left_body");
	private static final ResourceLocation RIGHT_BODY = new ResourceLocation(UltraAmplified.MODID + ":ice_spike_temple_right_body");
	private static final ResourceLocation CHESTS_1_ICE_SPIKE_TEMPLE_UA = new ResourceLocation(UltraAmplified.MODID + ":chests/ice_spike_temple_1_ua");
	private static final ResourceLocation CHESTS_2_ICE_SPIKE_TEMPLE_UA = new ResourceLocation(UltraAmplified.MODID + ":chests/ice_spike_temple_2_ua");
	private static final Map<ResourceLocation, BlockPos> OFFSET = ImmutableMap.of(CENTER, new BlockPos(0, -7, 0), CENTER_TOP, new BlockPos(0, 10, 0), LEFT_BODY, new BlockPos(0, -7, 0), RIGHT_BODY, new BlockPos(0, -7, 0));


	public static void start(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList, Random random)
	{

		addPartToList(templateManager, pos, rotation, pieceList, random, CENTER);
		addPartToList(templateManager, pos, rotation, pieceList, random, CENTER_TOP);
		addSideSpikes(templateManager, random, rotation, pos, pieceList);

	}


	private static void addSideSpikes(TemplateManager templateManager, Random random, Rotation rotationIn, BlockPos pos, List<StructurePiece> pieceList)
	{
		int x = pos.getX();
		int z = pos.getZ();

		BlockPos offSet = new BlockPos(-9, 4, 5).rotate(rotationIn);
		BlockPos blockpos2 = offSet.add(x, pos.getY(), z);
		addPartToList(templateManager, blockpos2, rotationIn, pieceList, random, LEFT_BODY);

		offSet = new BlockPos(19, 4, 0).rotate(rotationIn);
		BlockPos blockpos3 = offSet.add(x, pos.getY(), z);
		addPartToList(templateManager, blockpos3, rotationIn, pieceList, random, RIGHT_BODY);

	}


	private static void addPartToList(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList, Random random, ResourceLocation rl)
	{
		pieceList.add(new IceSpikeTemplePiecesUA.Piece(templateManager, rl, pos, rotation));
	}

	public static class Piece extends TemplateStructurePiece
	{
		private ResourceLocation resourceLocation;
		private Rotation rotation;


		public Piece(TemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotationIn)
		{
			super(StructureInitUA.ISTUA, 0);
			this.resourceLocation = resourceLocationIn;
			BlockPos blockpos = IceSpikeTemplePiecesUA.OFFSET.get(resourceLocation);
			this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
			this.rotation = rotationIn;
			this.setupPiece(templateManagerIn);
		}


		public Piece(TemplateManager templateManagerIn, CompoundNBT tagCompound)
		{
			super(StructureInitUA.ISTUA, tagCompound);
			this.resourceLocation = new ResourceLocation(tagCompound.getString("Template"));
			this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
			this.setupPiece(templateManagerIn);
		}


		private void setupPiece(TemplateManager templateManager)
		{
			Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
			this.setup(template, this.templatePosition, placementsettings);
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putString("Template", this.resourceLocation.toString());
			tagCompound.putString("Rot", this.rotation.name());
		}


		@Override
		protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb)
		{
			if ("chest".equals(function))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
				if (UltraAmplified.UAStructuresConfig.chestGeneration.get())
				{
					TileEntity tileentity = world.getTileEntity(pos.down());
					if (tileentity instanceof ChestTileEntity)
					{
						if (UltraAmplified.UAStructuresConfig.chestGeneration.get())
						{
							((ChestTileEntity) tileentity).setLootTable(CHESTS_1_ICE_SPIKE_TEMPLE_UA, rand.nextLong());
						}
						else
						{
							world.setBlockState(pos.down(), Blocks.AIR.getDefaultState(), 2);
						}
					}
				}
			}
			else if ("chest2".equals(function))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
				if (UltraAmplified.UAStructuresConfig.chestGeneration.get())
				{
					TileEntity tileentity = world.getTileEntity(pos.down());
					if (tileentity instanceof ChestTileEntity)
					{
						if (UltraAmplified.UAStructuresConfig.chestGeneration.get())
						{
							((ChestTileEntity) tileentity).setLootTable(CHESTS_2_ICE_SPIKE_TEMPLE_UA, rand.nextLong());
						}
						else
						{
							world.setBlockState(pos.down(), Blocks.AIR.getDefaultState(), 2);
						}
					}
				}
			}

		}


		@Override
		public boolean create(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos)
		{
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
			BlockPos blockpos = IceSpikeTemplePiecesUA.OFFSET.get(this.resourceLocation);
			this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3 - blockpos.getX(), 0, 0 - blockpos.getZ())));
			return super.create(world, p_225577_2_, random, structureBoundingBoxIn, chunkPos);
		}
	}

}