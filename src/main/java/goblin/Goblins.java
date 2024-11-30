package goblin;

import java.util.HashMap;
import java.util.LinkedHashMap;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import goblin.achievements.GoblinsAchievements;
import goblin.block.BlockEnchantedTNT;
import goblin.block.BlockGoblinDrum;
import goblin.block.BlockGoo;
import goblin.block.BlockMobGMSpawner;
import goblin.block.BlockMobGRSpawner;
import goblin.block.BlockMobGSpawner;
import goblin.block.BlockOverchargedTNT;
import goblin.block.BlockTotem;
import goblin.block.BlockVillageSpawner;
import goblin.entity.EntityDirewolf;
import goblin.entity.EntityGoblin;
import goblin.entity.EntityGoblinBomber;
import goblin.entity.EntityGoblinLord;
import goblin.entity.EntityGoblinMage;
import goblin.entity.EntityGoblinMiner;
import goblin.entity.EntityGoblinMiner2;
import goblin.entity.EntityGoblinNinja;
import goblin.entity.EntityGoblinRanger;
import goblin.entity.EntityGoblinRangerGuard;
import goblin.entity.EntityGoblinRider;
import goblin.entity.EntityGoblinSoldier;
import goblin.entity.item.EntityEnchantedTNTPrimed;
import goblin.entity.item.EntityOverchargedTNTPrimed;
import goblin.entity.projectile.EntityArcaneball;
import goblin.entity.projectile.EntityBomb;
import goblin.entity.projectile.EntityGArcaneball;
import goblin.entity.projectile.EntityLightball;
import goblin.entity.projectile.EntityOrbExplosive;
import goblin.entity.projectile.EntityOrbForce;
import goblin.entity.projectile.EntityOrbLightning;
import goblin.entity.projectile.EntityOrbNature;
import goblin.entity.projectile.EntityShuriken;
import goblin.item.GoblinsItem;
import goblin.item.GoblinsItemFood;
import goblin.item.GoblinsItemSword;
import goblin.item.GoblinsSpawnEgg;
import goblin.item.ItemBomb;
import goblin.item.ItemOrbExplosive;
import goblin.item.ItemOrbForce;
import goblin.item.ItemOrbLightning;
import goblin.item.ItemOrbNature;
import goblin.item.ItemShuriken;
import goblin.item.ItemStaffArcane;
import goblin.item.ItemStaffLightning;
import goblin.item.ItemStaffNature;
import goblin.item.ItemStaffTeleport;
import goblin.proxy.CommonProxy;
import goblin.tileEntity.TileEntityGoblinDrum;
import goblin.tileEntity.TileEntityMobGMSpawner;
import goblin.tileEntity.TileEntityMobGRSpawner;
import goblin.tileEntity.TileEntityMobGSpawner;
import goblin.world.gen.Generate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = "goblin", name = "Goblins Legacy", version = "1.0")
public class Goblins {
	
	static int startEntityId = 0;
	static int MobGSpawnerBlockID;
	static int MobGRSpawnerBlockID;
	static int MobGMSpawnerBlockID;
	static int VillageSpawnBlockID;
	static int ETNTBlockID;
	static int MTNTBlockID;
	static int totemRBlockID;
	static int totemBBlockID;
	static int totemGBlockID;
	static int totemYBlockID;
	static int gobDrumBlockID;
	static int gooID;
	static int swordFireShiftedIndex;
	static int GoblinFleshShiftedIndex;
	static int bombShiftedIndex;
	static int staffBlueShiftedIndex;
	static int staffNatureShiftedIndex;
	static int ShurikenShiftedIndex;
	static int orbBShiftedIndex;
	static int orbGShiftedIndex;
	static int orbRShiftedIndex;
	static int orbYShiftedIndex;
	static int crystalBShiftedIndex;
	static int crystalRShiftedIndex;
	static int crystalGShiftedIndex;
	static int crystalYShiftedIndex;
	static int powderRShiftedIndex;
	static int powderBShiftedIndex;
	static int powderYShiftedIndex;
	static int powderGShiftedIndex;
	static int katanaSwordShiftedIndex;
	static int staffTeleportShiftedIndex;
	static int staffLightningShiftedIndex;
	static int ectoplasmShiftedIndex;
	static int GoblinBowShiftedIndex;
	static int GoblinSpawnrate1;
	static int GoblinSpawnrate2;
	static int GoblinSpawnrate3;
	static int spawnerDelay1;
	static int spawnerDelay2;
	static int spawnerDelay3;
	
	public static int structureSpawnrate;
	public static int villageSpawnrate;
	public static int hutsSpawnrate;
	public static int fireplaceSpawnrate;
	static boolean spawnerDeath;
	
	public static Block MobGSpawner;
	public static Block MobGRSpawner;
	public static Block MobGMSpawner;
	public static Block VillageSpawn;
	public static Block ETNT;
	public static Block MTNT;
	public static Block totemR;
	public static Block totemB;
	public static Block totemG;
	public static Block totemY;
	public static Block gobDrum;
	public static Block goo;
	
	public static Item swordFire;
	public static Item goblinFlesh;
	public static Item bomb;
	public static Item staffArcane;
	public static Item staffNature;
	public static Item shuriken;
	public static Item orbForce;
	public static Item orbNature;
	public static Item orbExplosive;
	public static Item orbLightning;
	public static Item crystalB;
	public static Item crystalR;
	public static Item crystalG;
	public static Item crystalY;
	public static Item powderR;
	public static Item powderB;
	public static Item powderY;
	public static Item powderG;
	public static Item swordKatana;
	public static Item staffTeleport;
	public static Item staffLightning;
	public static Item ectoplasm;
	public static Item goblinBow;
	
	
	public static Item icon_goblin;
	public static Item achievement_icon_kill_goblin_lord;
	public static Item achievement_icon_kill_goblin_shaman;
	public static Item achievement_icon_goblin_ninja_attack;
	
	public static Item spawnEgg;
	
	public static HashMap entityEggs = new LinkedHashMap();
	
	public static final ToolMaterial EXPLOSIVE_MATERIAL = EnumHelper.addToolMaterial("explosiveMaterial", 0, 250, 6.0F, 2.0F, 14).setRepairItem(new ItemStack(powderR));
//	public static final ToolMaterial NATURE_MATERIAL = EnumHelper.addToolMaterial("natureMaterial", 0, 250, 6.0F, 2.0F, 14).setRepairItem(new ItemStack(powderG));
//	public static final ToolMaterial ARCANE_MATERIAL = EnumHelper.addToolMaterial("arcaneMaterial", 0, 250, 6.0F, 2.0F, 14).setRepairItem(new ItemStack(powderB));
//	public static final ToolMaterial LIGHTNING_MATERIAL = EnumHelper.addToolMaterial("lightningMaterial", 0, 250, 6.0F, 2.0F, 14).setRepairItem(new ItemStack(powderY));
//	public static final ToolMaterial TELEPORTATION_STAFF_MATERIAL = EnumHelper.addToolMaterial("teleportationStaffMaterial", 0, 250, 6.0F, 2.0F, 14).setRepairItem(new ItemStack(powderB));
	
	
	@SidedProxy(clientSide = "goblin.proxy.ClientProxy", serverSide = "goblin.proxy.CommonProxy")
	
	public static CommonProxy proxy;
	
	public static Configuration configFile;
	
	private static boolean isThaumcraftLoaded;
	
	public static final CreativeTabs GOBLINS_CREATIVE_TAB = new GoblinsCreativeTab(CreativeTabs.creativeTabArray.length, "GoblinsTab");

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		/**
		 * Register Config
		 */
		
		configFile = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();
		
		
		/**
		 * Register Items
		 */
		
		swordKatana =  new GoblinsItemSword("swordKatana", Item.ToolMaterial.IRON);
		shuriken = new ItemShuriken("shuriken");
		swordFire = new GoblinsItemSword("swordFire", EXPLOSIVE_MATERIAL);
		goblinFlesh = new GoblinsItemFood("goblinFlesh", 4, 0.1f, true).setPotionEffect(Potion.blindness.id, 20, 0, 0.6f);
		bomb = new ItemBomb("bomb");
		orbForce = new ItemOrbForce("orbB");
		orbNature = new ItemOrbNature("orbG");
		orbExplosive = new ItemOrbExplosive("orbR");
		orbLightning = new ItemOrbLightning("orbY");
		crystalB = new GoblinsItem("crystalB");
		crystalR = new GoblinsItem("crystalR");
		crystalG = new GoblinsItem("crystalG");
		crystalY = new GoblinsItem("crystalY");
		powderR = new GoblinsItem("powderR");
		powderB = new GoblinsItem("powderB");
		powderY = new GoblinsItem("powderY");
		powderG = new GoblinsItem("powderG");
		staffArcane = new ItemStaffArcane("staffBlue");
		staffNature = new ItemStaffNature("staffNature");
		staffTeleport = new ItemStaffTeleport("staffTeleport");
		staffLightning = new ItemStaffLightning("staffLightning");
		
		icon_goblin = new GoblinsItem("icon_goblin");
		achievement_icon_kill_goblin_lord = new GoblinsItem("achievement_icon_kill_goblin_lord");
		achievement_icon_kill_goblin_shaman = new GoblinsItem("achievement_icon_kill_goblin_shaman");
		achievement_icon_goblin_ninja_attack = new GoblinsItem("achievement_icon_goblin_ninja_attack");
		
		registerItem(bomb);
		registerItem(crystalB);
		registerItem(crystalG);
		registerItem(crystalR);
		registerItem(crystalY);
		registerItem(goblinFlesh);
		registerItem(orbExplosive);
		registerItem(orbForce);
		registerItem(orbLightning);
		registerItem(orbNature);
		registerItem(powderB);
		registerItem(powderG);
		registerItem(powderR);
		registerItem(powderY);
		registerItem(shuriken);
		registerItem(staffArcane);
		registerItem(staffLightning);
		registerItem(staffNature);
		registerItem(staffTeleport);
		registerItem(swordFire);
		registerItem(swordKatana);
		
		registerItem(icon_goblin);
		registerItem(achievement_icon_kill_goblin_lord);
		registerItem(achievement_icon_kill_goblin_shaman);
		registerItem(achievement_icon_goblin_ninja_attack);
		
		
		/**
		 * Register Disposable Items
		 */
		BlockDispenser.dispenseBehaviorRegistry.putObject(orbExplosive, new BehaviorProjectileDispense()
        {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            protected IProjectile getProjectileEntity(World world, IPosition iPosition)
            {
                return new EntityOrbExplosive(world, iPosition.getX(), iPosition.getY(), iPosition.getZ());
            }
        });
		
		BlockDispenser.dispenseBehaviorRegistry.putObject(orbNature, new BehaviorProjectileDispense()
        {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            protected IProjectile getProjectileEntity(World world, IPosition iPosition)
            {
                return new EntityOrbNature(world, iPosition.getX(), iPosition.getY(), iPosition.getZ());
            }
        });
		
		BlockDispenser.dispenseBehaviorRegistry.putObject(orbForce, new BehaviorProjectileDispense()
        {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            protected IProjectile getProjectileEntity(World world, IPosition iPosition)
            {
                return new EntityOrbForce(world, iPosition.getX(), iPosition.getY(), iPosition.getZ());
            }
        });
		
		BlockDispenser.dispenseBehaviorRegistry.putObject(orbLightning, new BehaviorProjectileDispense()
        {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            protected IProjectile getProjectileEntity(World world, IPosition iPosition)
            {
                return new EntityOrbLightning(world, iPosition.getX(), iPosition.getY(), iPosition.getZ());
            }
        });
		
		BlockDispenser.dispenseBehaviorRegistry.putObject(bomb, new BehaviorProjectileDispense()
        {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            protected IProjectile getProjectileEntity(World world, IPosition iPosition)
            {
                return new EntityBomb(world, iPosition.getX(), iPosition.getY(), iPosition.getZ());
            }
        });
		
		BlockDispenser.dispenseBehaviorRegistry.putObject(shuriken, new BehaviorProjectileDispense()
        {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            protected IProjectile getProjectileEntity(World world, IPosition iPosition)
            {
                return new EntityShuriken(world, iPosition.getX(), iPosition.getY(), iPosition.getZ());
            }
        });
		
		
		/**
		 * Register Blocks
		 */
		
		MobGSpawner = new BlockMobGSpawner(spawnerDelay1).setHardness(4.0f).setResistance(1.0f).setCreativeTab(GOBLINS_CREATIVE_TAB).setBlockName("MobGSpawner");
		MobGRSpawner = new BlockMobGRSpawner(spawnerDelay2).setHardness(4.0f).setResistance(1.0f).setCreativeTab(GOBLINS_CREATIVE_TAB).setBlockName("MobGRSpawner");
		MobGMSpawner = new BlockMobGMSpawner(spawnerDelay3).setHardness(4.0f).setResistance(1.0f).setCreativeTab(GOBLINS_CREATIVE_TAB).setBlockName("MobGMSpawner");
		VillageSpawn = new BlockVillageSpawner().setHardness(0.1f).setResistance(1.0f).setLightLevel(-0.65f).setCreativeTab(GOBLINS_CREATIVE_TAB).setBlockName("VillageSpawn");
		
		
		ETNT = new BlockEnchantedTNT().setHardness(0.0f).setLightLevel(0.65f).setCreativeTab(GOBLINS_CREATIVE_TAB).setBlockName("ETNT");
		MTNT = new BlockOverchargedTNT().setHardness(0.0f).setLightLevel(0.8f).setCreativeTab(GOBLINS_CREATIVE_TAB).setBlockName("MTNT");
		
		totemR = new BlockTotem("totemR").setHardness(3.5f).setResistance(20.0f).setLightLevel(0.65f);
		totemB = new BlockTotem("totemB").setHardness(3.5f).setResistance(20.0f).setLightLevel(0.5f);
		totemG = new BlockTotem("totemG").setHardness(3.5f).setResistance(20.0f).setLightLevel(0.5f);
		totemY = new BlockTotem("totemY").setHardness(3.5f).setResistance(20.0f).setLightLevel(0.65f);
		gobDrum = new BlockGoblinDrum().setHardness(0.8f).setBlockName("gobDrum").setCreativeTab(GOBLINS_CREATIVE_TAB);
		
		goo = new BlockGoo().setHardness(0.0f).setResistance(0.0f).setBlockName("goo");
		
		
		registerBlock(ETNT);
		registerBlock(MTNT);
		registerBlock(MobGMSpawner);
		registerBlock(MobGRSpawner);
		registerBlock(MobGSpawner);
		registerBlock(VillageSpawn);
		registerBlock(gobDrum);
		registerBlock(goo);
		registerBlock(totemB);
		registerBlock(totemG);
		registerBlock(totemR);
		registerBlock(totemY);
		
		
		/**
		 * Register World Generators
		 */
		
		GameRegistry.registerWorldGenerator((IWorldGenerator) new Generate(), 0);

		
		
		/**
		 * Register Entities and Spawn Eggs
		 */
		
		int modEntityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity((Class) EntityOrbNature.class, "orbG", modEntityID++, (Object) this, 250, 1, true);

		EntityRegistry.registerModEntity((Class) EntityOrbLightning.class, "orbY", modEntityID++, (Object) this, 250, 1, true);

		EntityRegistry.registerModEntity((Class) EntityOrbExplosive.class, "orbR", modEntityID++, (Object) this, 250, 1, true);

		EntityRegistry.registerModEntity((Class) EntityOrbForce.class, "orbB", modEntityID++, (Object) this, 250, 1, true);

		EntityRegistry.registerModEntity((Class) EntityGArcaneball.class, "GArcaneball", modEntityID++, (Object) this, 250, 1, true);
		EntityRegistry.registerModEntity((Class) EntityArcaneball.class, "Arcaneball", modEntityID++, (Object) this, 250, 1, true);

		EntityRegistry.registerModEntity((Class) EntityLightball.class, "Lightball", modEntityID++, (Object) this, 250, 1, true);

		EntityRegistry.registerModEntity((Class) EntityShuriken.class, "Shuriken", modEntityID++, (Object) this, 250, 1, true);
		
		EntityRegistry.registerModEntity(EntityEnchantedTNTPrimed.class, "ETNTPrimed", modEntityID++, (Object) this, 250, 5, false);
		
		EntityRegistry.registerModEntity((Class) EntityOverchargedTNTPrimed.class, "MTNTPrimed", modEntityID++, (Object) this, 250, 5, false);

		EntityRegistry.registerModEntity((Class) EntityBomb.class, "Bomb", modEntityID++, (Object) this, 250, 1, true);
		
		
		
		GameRegistry.registerTileEntity((Class) TileEntityMobGSpawner.class, "MobGSpawner");

		GameRegistry.registerTileEntity((Class) TileEntityMobGRSpawner.class, "MobGRSpawner");

		GameRegistry.registerTileEntity((Class) TileEntityMobGMSpawner.class, "MobGMSpawner");

		GameRegistry.registerTileEntity((Class) TileEntityGoblinDrum.class, "TGobDrum");
		
		
	

		EntityRegistry.registerModEntity((Class) EntityDirewolf.class, "Direwolf", modEntityID++, (Object) this, 250, 1, false);
		registerEntitySpawnEgg((Class<? extends Entity>) EntityDirewolf.class, 4801343, 1118481);
		EntityRegistry.addSpawn((Class) EntityDirewolf.class, 4, 1, 5, EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.swampland });
		
		EntityRegistry.registerModEntity((Class) EntityGoblin.class, "Goblin", modEntityID++, (Object) this, 250, 1, false);
		registerEntitySpawnEgg((Class<? extends Entity>) EntityGoblin.class, 3178279, 6044188);
		
		EntityRegistry.registerModEntity((Class) EntityGoblinBomber.class, "GoblinBomber", modEntityID++, (Object) this, 250, 1, false);
		registerEntitySpawnEgg((Class<? extends Entity>) EntityGoblinBomber.class, 5856839, 2631459);

		EntityRegistry.registerModEntity((Class) EntityGoblinLord.class, "GoblinLord", modEntityID++, (Object) this, 250, 1, false);
		registerEntitySpawnEgg((Class<? extends Entity>) EntityGoblinLord.class, 4027205, 2175518);

		EntityRegistry.registerModEntity((Class) EntityGoblinMage.class, "GoblinMage", modEntityID++, (Object) this, 250, 1, false);
		registerEntitySpawnEgg((Class<? extends Entity>) EntityGoblinMage.class, 4027205, 10950147);
		
		EntityRegistry.registerModEntity((Class) EntityGoblinMiner.class, "GoblinMiner", modEntityID++, (Object) this, 250, 1, false);
		registerEntitySpawnEgg((Class<? extends Entity>) EntityGoblinMiner.class, 7306346, 6710886);

		EntityRegistry.registerModEntity((Class) EntityGoblinMiner2.class, "GoblinMiner2", modEntityID++, (Object) this, 250, 1, false);
		
		EntityRegistry.registerModEntity((Class) EntityGoblinNinja.class, "GoblinNinja", modEntityID++, (Object) this, 250, 1, false);
		registerEntitySpawnEgg((Class<? extends Entity>) EntityGoblinNinja.class, 5856839, 6498581);
		
		EntityRegistry.registerModEntity((Class) EntityGoblinRanger.class, "GoblinRanger", modEntityID++, (Object) this, 250, 1, false);
		registerEntitySpawnEgg((Class<? extends Entity>) EntityGoblinRanger.class, 35926, 8083721);

		EntityRegistry.registerModEntity((Class) EntityGoblinRangerGuard.class, "GoblinRangerGuard", modEntityID++, (Object) this, 250, 1, false);
		registerEntitySpawnEgg((Class<? extends Entity>) EntityGoblinRangerGuard.class, 35926, 8083721);

		EntityRegistry.registerModEntity((Class) EntityGoblinRider.class, "GoblinRider", modEntityID++, (Object) this, 250, 1, false);
		registerEntitySpawnEgg((Class<? extends Entity>) EntityGoblinRider.class, 4872741, 11168283);
		
		EntityRegistry.registerModEntity((Class) EntityGoblinSoldier.class, "GoblinSoldier", modEntityID++, (Object) this, 250, 1, false);
		registerEntitySpawnEgg((Class<? extends Entity>) EntityGoblinSoldier.class, 7761199, 6498581);
		
		

		
		/**
		 * Register Spawn Egg Item Base
		 */
		spawnEgg = new GoblinsSpawnEgg().setUnlocalizedName("spawnEgg").setTextureName("spawn_egg");
		
		registerItem(spawnEgg);
		
		
		/**
		 * Register Crafting Recipes
		 */
		GameRegistry.addRecipe(new ItemStack(powderG, 4), new Object[]
		{ "X", 'X', totemG });

		GameRegistry.addRecipe(new ItemStack(powderY, 4), new Object[]
		{ "X", 'X', totemY });

		GameRegistry.addRecipe(new ItemStack(powderB, 4), new Object[]
		{ "X", 'X', totemB });

		GameRegistry.addRecipe(new ItemStack(powderR, 4), new Object[]
		{ "X", 'X', totemR });

		GameRegistry.addRecipe(new ItemStack(orbExplosive, 2), new Object[]
		{ " X ", "XYX", " X ", 'X', Blocks.glass, 'Y', powderR });

		GameRegistry.addRecipe(new ItemStack(orbForce, 4), new Object[]
		{ " X ", "XYX", " X ", 'X', Blocks.glass, 'Y', powderB });

		GameRegistry.addRecipe(new ItemStack(orbLightning, 4), new Object[]
		{ " X ", "XYX", " X ", 'X', Blocks.glass, 'Y', powderY });

		GameRegistry.addRecipe(new ItemStack(orbNature, 8), new Object[]
		{ " X ", "XYX", " X ", 'X', Blocks.glass, 'Y', powderG });

		GameRegistry.addRecipe(new ItemStack(swordFire, 1), new Object[]
		{ "X", "Y", "Z", 'X', crystalR, 'Y', crystalR, 'Z', Items.stick });

		GameRegistry.addRecipe(new ItemStack(staffNature, 1), new Object[]
		{ "X", "Y", "Z", 'X', crystalG, 'Y', Items.stick, 'Z', Items.stick });

		GameRegistry.addRecipe(new ItemStack(staffArcane, 1), new Object[]
		{ "X", "Y", "Z", 'X', crystalB, 'Y', Items.stick, 'Z', Items.stick });

		GameRegistry.addRecipe(new ItemStack(staffLightning, 1), new Object[]
		{ "X", "Y", "Z", 'X', crystalY, 'Y', Items.stick, 'Z', Items.stick });

		GameRegistry.addRecipe(new ItemStack(bomb, 2), new Object[]
		{ "#", "Y", "#", '#', Blocks.stone, 'Y', Items.gunpowder });
		
		GameRegistry.addRecipe(new ItemStack(shuriken, 1), new Object[]
		{ "##", "##", '#', Blocks.cobblestone});

		GameRegistry.addShapelessRecipe(new ItemStack(staffTeleport, 1), new Object[]
		{ staffArcane, crystalG });

		GameRegistry.addShapelessRecipe(new ItemStack(ETNT, 3), new Object[]
		{ powderR, Blocks.tnt });

		GameRegistry.addShapelessRecipe(new ItemStack(MTNT, 1), new Object[]
		{ powderB, ETNT });

		GameRegistry.addShapelessRecipe(new ItemStack(crystalR), new Object[]
		{ powderR, powderR });

		GameRegistry.addShapelessRecipe(new ItemStack(crystalG), new Object[]
		{ powderG, powderG });

		GameRegistry.addShapelessRecipe(new ItemStack(crystalB), new Object[]
		{ powderB, powderB });

		GameRegistry.addShapelessRecipe(new ItemStack(crystalY), new Object[]
		{ powderY, powderY });
		
		
		/**
		 *  Events
		 */
		
		 MinecraftForge.EVENT_BUS.register(new GoblinsEventHooks());
	}


	private void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}


	private void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}
	
	
	@EventHandler
    public void init(FMLInitializationEvent event)
	{
		
	
		proxy.registerEntityRenderers(); // register Renderers
		
		GoblinsAchievements.initilization();
		
		isThaumcraftLoaded = Loader.isModLoaded("Thaumcraft");
		
		if (isThaumcraftLoaded) {GoblinsThaumcraftAspects.addThaumcraftAspects();};
	}

	public static int getItemSubId()
	{
		++startEntityId;
		return startEntityId;
	}

	public static void registerEntitySpawnEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor)
	{
		int subId = getItemSubId();
		EntityList.IDtoClassMapping.put(subId, entity);
		entityEggs.put(subId, new EntityList.EntityEggInfo(subId, primaryColor, secondaryColor));
	}
	
	public static void syncConfig()
	{
		spawnerDelay1 = configFile.getInt("Goblin Spawner Delay", "general", 280, 0, Integer.MAX_VALUE, " ");
		
		spawnerDelay2 = configFile.getInt("Goblin Rider and Direwolf Spawner Delay", "general", 230, 0, Integer.MAX_VALUE, " ");
		
		spawnerDelay3 = configFile.getInt("Goblin Miner Spawner Delay", "general", 180, 0, Integer.MAX_VALUE, " ");
		
		structureSpawnrate = configFile.getInt("General Spawnrate of all Goblin Structures", "general", 4, 0, Integer.MAX_VALUE, "(Larger number means more often)");
		
		villageSpawnrate = configFile.getInt("Goblin Village Spawnrate", "general", 2, 0, Integer.MAX_VALUE, "(Larger number means less often)");
		
		hutsSpawnrate = configFile.getInt("Huts Spawnrate", "general", 5, 0, Integer.MAX_VALUE, "(Larger number means less often)");
		
		fireplaceSpawnrate = configFile.getInt("Fireplace Spawnrate", "general", 5, 0, Integer.MAX_VALUE, "(Larger number means less often)");
		
		spawnerDeath = configFile.getBoolean("Should Goblin spawners run out of Goblins after a certain amount of goblins?", "general", true, "(If you are building a map, set this to false, or else all your spawners will die)");
		
		if (configFile.hasChanged()) {configFile.save();}
	}
}
