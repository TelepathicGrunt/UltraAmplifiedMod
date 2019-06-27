package net.TelepathicGrunt.UltraAmplified.World.gen.structure;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.EndCityPieces;
import net.minecraft.world.gen.feature.structure.StructureIO;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;

public class StructureEndCityPiecesUA
{
	  private static final PlacementSettings OVERWRITE = (new PlacementSettings()).setIgnoreEntities(true);
	   private static final PlacementSettings INSERT = (new PlacementSettings()).setIgnoreEntities(true).setReplacedBlock(Blocks.AIR);
	   private static final StructureEndCityPiecesUA.IGenerator HOUSE_TOWER_GENERATOR = new StructureEndCityPiecesUA.IGenerator() {
		   
	      public void init() {
	      }

	      public boolean generate(TemplateManager p_191086_1_, int p_191086_2_, StructureEndCityPiecesUA.CityTemplate p_191086_3_, BlockPos p_191086_4_, List<StructurePiece> p_191086_5_, Random p_191086_6_) {
	         if (p_191086_2_ > 8) {
	            return false;
	         } else {
	            Rotation rotation = p_191086_3_.placeSettings.getRotation();
	            StructureEndCityPiecesUA.CityTemplate StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, p_191086_3_, p_191086_4_, "base_floor", rotation, true));
	            int i = 2;
	            if (i == 0) {
	               StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(-1, 4, -1), "base_roof", rotation, true));
	            } else if (i == 1) {
	               StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(-1, 0, -1), "second_floor_2", rotation, false));
	               StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(-1, 8, -1), "second_roof", rotation, false));
	               StructureEndCityPiecesUA.recursiveChildren(p_191086_1_, StructureEndCityPiecesUA.TOWER_GENERATOR, p_191086_2_ + 1, StructureEndCityPiecesUA$citytemplate, (BlockPos)null, p_191086_5_, p_191086_6_);
	            } else if (i == 2) {
	               StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(-1, 0, -1), "second_floor_2", rotation, false));
	               StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(-1, 4, -1), "third_floor_2", rotation, false));
	               StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(-1, 8, -1), "third_roof", rotation, true));
	               StructureEndCityPiecesUA.recursiveChildren(p_191086_1_, StructureEndCityPiecesUA.TOWER_GENERATOR, p_191086_2_ + 1, StructureEndCityPiecesUA$citytemplate, (BlockPos)null, p_191086_5_, p_191086_6_);
	            }

	            return true;
	         }
	      }
	   };
	   private static final List<Tuple<Rotation, BlockPos>> TOWER_BRIDGES = Lists.newArrayList(new Tuple<>(Rotation.NONE, new BlockPos(1, -1, 0)), new Tuple<>(Rotation.CLOCKWISE_90, new BlockPos(6, -1, 1)), new Tuple<>(Rotation.COUNTERCLOCKWISE_90, new BlockPos(0, -1, 5)), new Tuple<>(Rotation.CLOCKWISE_180, new BlockPos(5, -1, 6)));
	   private static final StructureEndCityPiecesUA.IGenerator TOWER_GENERATOR = new StructureEndCityPiecesUA.IGenerator() {
	      public void init() {
	      }

	      public boolean generate(TemplateManager p_191086_1_, int p_191086_2_, StructureEndCityPiecesUA.CityTemplate p_191086_3_, BlockPos p_191086_4_, List<StructurePiece> p_191086_5_, Random p_191086_6_) {
	         Rotation rotation = p_191086_3_.placeSettings.getRotation();
	         StructureEndCityPiecesUA.CityTemplate lvt_8_1_ = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, p_191086_3_, new BlockPos(3 + p_191086_6_.nextInt(2), -3, 3 + p_191086_6_.nextInt(2)), "tower_base", rotation, true));
	         lvt_8_1_ = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, lvt_8_1_, new BlockPos(0, 7, 0), "tower_piece", rotation, true));
	         StructureEndCityPiecesUA.CityTemplate StructureEndCityPiecesUA$citytemplate1 = p_191086_6_.nextInt(3) == 0 ? lvt_8_1_ : null;
	         int i = 1 + p_191086_6_.nextInt(3);

	         for(int j = 0; j < i; ++j) {
	            lvt_8_1_ = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, lvt_8_1_, new BlockPos(0, 4, 0), "tower_piece", rotation, true));
	            if (j < i - 1 && p_191086_6_.nextBoolean()) {
	               StructureEndCityPiecesUA$citytemplate1 = lvt_8_1_;
	            }
	         }

	         if (StructureEndCityPiecesUA$citytemplate1 != null) {
	            for(Tuple<Rotation, BlockPos> tuple : StructureEndCityPiecesUA.TOWER_BRIDGES) {
	               if (p_191086_6_.nextBoolean()) {
	                  StructureEndCityPiecesUA.CityTemplate StructureEndCityPiecesUA$citytemplate2 = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate1, tuple.getB(), "bridge_end", rotation.add(tuple.getA()), true));
	                  StructureEndCityPiecesUA.recursiveChildren(p_191086_1_, StructureEndCityPiecesUA.TOWER_BRIDGE_GENERATOR, p_191086_2_ + 1, StructureEndCityPiecesUA$citytemplate2, (BlockPos)null, p_191086_5_, p_191086_6_);
	               }
	            }

	            StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, lvt_8_1_, new BlockPos(-1, 4, -1), "tower_top", rotation, true));
	         } else {
	            if (p_191086_2_ != 7) {
	               return StructureEndCityPiecesUA.recursiveChildren(p_191086_1_, StructureEndCityPiecesUA.FAT_TOWER_GENERATOR, p_191086_2_ + 1, lvt_8_1_, (BlockPos)null, p_191086_5_, p_191086_6_);
	            }

	            StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, lvt_8_1_, new BlockPos(-1, 4, -1), "tower_top", rotation, true));
	         }

	         return true;
	      }
	   };
	   private static final StructureEndCityPiecesUA.IGenerator TOWER_BRIDGE_GENERATOR = new StructureEndCityPiecesUA.IGenerator() {
	      public boolean shipCreated;

	      public void init() {
	         this.shipCreated = false;
	      }

	      public boolean generate(TemplateManager p_191086_1_, int p_191086_2_, StructureEndCityPiecesUA.CityTemplate p_191086_3_, BlockPos p_191086_4_, List<StructurePiece> p_191086_5_, Random p_191086_6_) {
	         Rotation rotation = p_191086_3_.placeSettings.getRotation();
	         int i = p_191086_6_.nextInt(4) + 1;
	         StructureEndCityPiecesUA.CityTemplate StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, p_191086_3_, new BlockPos(0, 0, -4), "bridge_piece", rotation, true));
	         StructureEndCityPiecesUA$citytemplate.componentType = -1;
	         int j = 0;

	         for(int k = 0; k < i; ++k) {
	            if (p_191086_6_.nextBoolean()) {
	               StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(0, j, -4), "bridge_piece", rotation, true));
	               j = 0;
	            } else {
	               if (p_191086_6_.nextBoolean()) {
	                  StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(0, j, -4), "bridge_steep_stairs", rotation, true));
	               } else {
	                  StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(0, j, -8), "bridge_gentle_stairs", rotation, true));
	               }

	               j = 4;
	            }
	         }

	         if (!this.shipCreated) {
	            StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(-8 + p_191086_6_.nextInt(8), j, -70 + p_191086_6_.nextInt(10)), "ship", rotation, true));
	            this.shipCreated = true;
	         } else if (!StructureEndCityPiecesUA.recursiveChildren(p_191086_1_, StructureEndCityPiecesUA.HOUSE_TOWER_GENERATOR, p_191086_2_ + 1, StructureEndCityPiecesUA$citytemplate, new BlockPos(-3, j + 1, -11), p_191086_5_, p_191086_6_)) {
	            return false;
	         }

	         StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(4, j, 0), "bridge_end", rotation.add(Rotation.CLOCKWISE_180), true));
	         StructureEndCityPiecesUA$citytemplate.componentType = -1;
	         return true;
	      }
	   };
	   private static final List<Tuple<Rotation, BlockPos>> FAT_TOWER_BRIDGES = Lists.newArrayList(new Tuple<>(Rotation.NONE, new BlockPos(4, -1, 0)), new Tuple<>(Rotation.CLOCKWISE_90, new BlockPos(12, -1, 4)), new Tuple<>(Rotation.COUNTERCLOCKWISE_90, new BlockPos(0, -1, 8)), new Tuple<>(Rotation.CLOCKWISE_180, new BlockPos(8, -1, 12)));
	   private static final StructureEndCityPiecesUA.IGenerator FAT_TOWER_GENERATOR = new StructureEndCityPiecesUA.IGenerator() {
	      public void init() {
	      }

	      public boolean generate(TemplateManager p_191086_1_, int p_191086_2_, StructureEndCityPiecesUA.CityTemplate p_191086_3_, BlockPos p_191086_4_, List<StructurePiece> p_191086_5_, Random p_191086_6_) {
	         Rotation rotation = p_191086_3_.placeSettings.getRotation();
	         StructureEndCityPiecesUA.CityTemplate StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, p_191086_3_, new BlockPos(-3, 4, -3), "fat_tower_base", rotation, true));
	         StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(0, 4, 0), "fat_tower_middle", rotation, true));

	         for(int i = 0; i < 2 && p_191086_6_.nextInt(3) != 0; ++i) {
	            StructureEndCityPiecesUA$citytemplate = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(0, 8, 0), "fat_tower_middle", rotation, true));

	            for(Tuple<Rotation, BlockPos> tuple : StructureEndCityPiecesUA.FAT_TOWER_BRIDGES) {
	               if (p_191086_6_.nextBoolean()) {
	                  StructureEndCityPiecesUA.CityTemplate StructureEndCityPiecesUA$citytemplate1 = StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, tuple.getB(), "bridge_end", rotation.add(tuple.getA()), true));
	                  StructureEndCityPiecesUA.recursiveChildren(p_191086_1_, StructureEndCityPiecesUA.TOWER_BRIDGE_GENERATOR, p_191086_2_ + 1, StructureEndCityPiecesUA$citytemplate1, (BlockPos)null, p_191086_5_, p_191086_6_);
	               }
	            }
	         }

	         StructureEndCityPiecesUA.addHelper(p_191086_5_, StructureEndCityPiecesUA.addPiece(p_191086_1_, StructureEndCityPiecesUA$citytemplate, new BlockPos(-2, 8, -2), "fat_tower_top", rotation, true));
	         return true;
	      }
	   };

	   public static void registerPieces() {
	      StructureIO.registerStructureComponent(StructureEndCityPiecesUA.CityTemplate.class, "ECP");
	   }

	   private static StructureEndCityPiecesUA.CityTemplate addPiece(TemplateManager p_191090_0_, StructureEndCityPiecesUA.CityTemplate p_191090_1_, BlockPos p_191090_2_, String p_191090_3_, Rotation p_191090_4_, boolean owerwrite) {
	      StructureEndCityPiecesUA.CityTemplate StructureEndCityPiecesUA$citytemplate = new StructureEndCityPiecesUA.CityTemplate(p_191090_0_, p_191090_3_, p_191090_1_.templatePosition, p_191090_4_, owerwrite);
	      BlockPos blockpos = p_191090_1_.template.calculateConnectedPos(p_191090_1_.placeSettings, p_191090_2_, StructureEndCityPiecesUA$citytemplate.placeSettings, BlockPos.ORIGIN);
	      StructureEndCityPiecesUA$citytemplate.offset(blockpos.getX(), blockpos.getY(), blockpos.getZ());
	      return StructureEndCityPiecesUA$citytemplate;
	   }

	   public static void startHouseTower(TemplateManager p_191087_0_, BlockPos p_191087_1_, Rotation p_191087_2_, List<StructurePiece> p_191087_3_, Random p_191087_4_) {
	      FAT_TOWER_GENERATOR.init();
	      HOUSE_TOWER_GENERATOR.init();
	      TOWER_BRIDGE_GENERATOR.init();
	      TOWER_GENERATOR.init();
	      StructureEndCityPiecesUA.CityTemplate StructureEndCityPiecesUA$citytemplate = addHelper(p_191087_3_, new StructureEndCityPiecesUA.CityTemplate(p_191087_0_, "base_floor", p_191087_1_, p_191087_2_, true));
	      StructureEndCityPiecesUA$citytemplate = addHelper(p_191087_3_, addPiece(p_191087_0_, StructureEndCityPiecesUA$citytemplate, new BlockPos(-1, 0, -1), "second_floor_1", p_191087_2_, false));
	      StructureEndCityPiecesUA$citytemplate = addHelper(p_191087_3_, addPiece(p_191087_0_, StructureEndCityPiecesUA$citytemplate, new BlockPos(-1, 4, -1), "third_floor_1", p_191087_2_, false));
	      StructureEndCityPiecesUA$citytemplate = addHelper(p_191087_3_, addPiece(p_191087_0_, StructureEndCityPiecesUA$citytemplate, new BlockPos(-1, 8, -1), "third_roof", p_191087_2_, true));
	      recursiveChildren(p_191087_0_, TOWER_GENERATOR, 1, StructureEndCityPiecesUA$citytemplate, (BlockPos)null, p_191087_3_, p_191087_4_);
	   }

	   private static StructureEndCityPiecesUA.CityTemplate addHelper(List<StructurePiece> p_189935_0_, StructureEndCityPiecesUA.CityTemplate p_189935_1_) {
	      p_189935_0_.add(p_189935_1_);
	      return p_189935_1_;
	   }

	   private static boolean recursiveChildren(TemplateManager p_191088_0_, StructureEndCityPiecesUA.IGenerator p_191088_1_, int p_191088_2_, StructureEndCityPiecesUA.CityTemplate p_191088_3_, BlockPos p_191088_4_, List<StructurePiece> p_191088_5_, Random p_191088_6_) {
	      if (p_191088_2_ > 8) {
	         return false;
	      } else {
	         List<StructurePiece> list = Lists.newArrayList();
	         if (p_191088_1_.generate(p_191088_0_, p_191088_2_, p_191088_3_, p_191088_4_, list, p_191088_6_)) {
	            boolean flag = false;
	            int i = p_191088_6_.nextInt();

	            for(StructurePiece structurepiece : list) {
	               structurepiece.componentType = i;
	               StructurePiece structurepiece1 = StructurePiece.findIntersecting(p_191088_5_, structurepiece.getBoundingBox());
	               if (structurepiece1 != null && structurepiece1.componentType != p_191088_3_.componentType) {
	                  flag = true;
	                  break;
	               }
	            }

	            if (!flag) {
	               p_191088_5_.addAll(list);
	               return true;
	            }
	         }

	         return false;
	      }
	   }

	   public static class CityTemplate extends TemplateStructurePiece {
	      private String pieceName;
	      private Rotation rotation;
	      /** Whether this template should overwrite existing blocks. Replaces only air if false. */
	      private boolean overwrite;

	      public CityTemplate() {
	      }

	      public CityTemplate(TemplateManager p_i47214_1_, String p_i47214_2_, BlockPos p_i47214_3_, Rotation p_i47214_4_, boolean overwriteIn) {
	         super(0);
	         this.pieceName = p_i47214_2_;
	         this.templatePosition = p_i47214_3_;
	         this.rotation = p_i47214_4_;
	         this.overwrite = overwriteIn;
	         this.loadTemplate(p_i47214_1_);
	      }

	      private void loadTemplate(TemplateManager p_191085_1_) {
	         Template template = p_191085_1_.getTemplateDefaulted(new ResourceLocation("end_city/" + this.pieceName));
	         PlacementSettings placementsettings = (this.overwrite ? StructureEndCityPiecesUA.OVERWRITE : StructureEndCityPiecesUA.INSERT).copy().setRotation(this.rotation);
	         this.setup(template, this.templatePosition, placementsettings);
	      }

	      /**
	       * (abstract) Helper method to write subclass data to NBT
	       */
	      protected void writeStructureToNBT(NBTTagCompound tagCompound) {
	         super.writeStructureToNBT(tagCompound);
	         tagCompound.setString("Template", this.pieceName);
	         tagCompound.setString("Rot", this.rotation.name());
	         tagCompound.setBoolean("OW", this.overwrite);
	      }

	      /**
	       * (abstract) Helper method to read subclass data from NBT
	       */
	      protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
	         super.readStructureFromNBT(tagCompound, p_143011_2_);
	         this.pieceName = tagCompound.getString("Template");
	         this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
	         this.overwrite = tagCompound.getBoolean("OW");
	         this.loadTemplate(p_143011_2_);
	      }

	      protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
	         if (function.startsWith("Chest")) {
	            BlockPos blockpos = pos.down();
	            if (sbb.isVecInside(blockpos)) {
	               TileEntityLockableLoot.setLootTable(worldIn, rand, blockpos, LootTableList.CHESTS_END_CITY_TREASURE);
	            }
	         } else if (function.startsWith("Sentry")) {
	            EntityShulker entityshulker = new EntityShulker(worldIn.getWorld());
	            entityshulker.setPosition((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D);
	            entityshulker.setAttachmentPos(pos);
	            worldIn.spawnEntity(entityshulker);
	         } else if (function.startsWith("Elytra")) {
	            EntityItemFrame entityitemframe = new EntityItemFrame(worldIn.getWorld(), pos, this.rotation.rotate(EnumFacing.SOUTH));
	            entityitemframe.setDisplayedItem(new ItemStack(Items.ELYTRA));
	            worldIn.spawnEntity(entityitemframe);
	         }

	      }
	   }

	   interface IGenerator {
	      void init();

	      boolean generate(TemplateManager p_191086_1_, int p_191086_2_, StructureEndCityPiecesUA.CityTemplate p_191086_3_, BlockPos p_191086_4_, List<StructurePiece> p_191086_5_, Random p_191086_6_);
	   }
 }