package goblin;
import goblin.item.ItemStaffTeleport;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class GoblinsThaumcraftAspects {
	public static void addThaumcraftAspects()
	{
		// =====================================================================================
		                //ITEMS
		// =====================================================================================
	
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.goblinFlesh), new AspectList().add(Aspect.FLESH, 2).add(Aspect.MAN, 1));
		
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.bomb), new AspectList().add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3).add(Aspect.EARTH, 1));
		
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.shuriken), new AspectList().add(Aspect.WEAPON, 1).add(Aspect.EARTH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.swordKatana), new AspectList().add(Aspect.METAL, 6).add(Aspect.WEAPON, 3));

		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.totemB), new AspectList().add(Aspect.ENERGY, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.powderB), new AspectList().add(Aspect.ENERGY, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.orbForce), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.ENERGY, 3).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.crystalB), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.ENERGY, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.staffArcane), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.ENERGY, 5).add(Aspect.MAGIC, 3));
		
		
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.totemG), new AspectList().add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.powderG), new AspectList().add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.orbNature), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.PLANT, 3).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.crystalG), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.staffNature), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.PLANT, 5).add(Aspect.MAGIC, 3));
		
		
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.totemR), new AspectList().add(Aspect.FIRE, 4).add(Aspect.ENTROPY, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.powderR), new AspectList().add(Aspect.FIRE, 1).add(Aspect.ENTROPY, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.orbExplosive), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.crystalR), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.swordFire), new AspectList().add(Aspect.CRYSTAL, 6).add(Aspect.FIRE, 3).add(Aspect.WEAPON, 2));
		
		
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.totemY), new AspectList().add(Aspect.WEATHER, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.powderY), new AspectList().add(Aspect.WEATHER, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.orbLightning), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.WEATHER, 3).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.crystalY), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.WEATHER, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.staffLightning), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.WEATHER, 5).add(Aspect.MAGIC, 3));
		
		
		

		// =====================================================================================
		        //BLOCKS
		// =====================================================================================
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.MobGSpawner), new AspectList().add(Aspect.BEAST, 2).add(Aspect.MAN, 2).add(Aspect.TRAVEL, 4).add(Aspect.MAGIC, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.MobGRSpawner), new AspectList().add(Aspect.BEAST, 2).add(Aspect.MAN, 2).add(Aspect.TRAVEL, 4).add(Aspect.MAGIC, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.MobGMSpawner), new AspectList().add(Aspect.BEAST, 2).add(Aspect.MAN, 2).add(Aspect.TRAVEL, 4).add(Aspect.MAGIC, 4));
		
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.VillageSpawn), new AspectList().add(Aspect.BEAST, 4).add(Aspect.MAN, 4).add(Aspect.CRAFT, 6).add(Aspect.MAGIC, 8));
		
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.ETNT), new AspectList().add(Aspect.FIRE, 18).add(Aspect.ENTROPY, 21).add(Aspect.EARTH, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(Goblins.MTNT), new AspectList().add(Aspect.FIRE, 25).add(Aspect.ENTROPY, 31).add(Aspect.EARTH, 3));
		
				
		
		// =====================================================================================
				//MOBS
		// =====================================================================================
		
		ThaumcraftApi.registerEntityTag("goblin.Goblin", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 1)); 
		
		ThaumcraftApi.registerEntityTag("goblin.GoblinMiner", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 1).add(Aspect.MINE, 1));
		ThaumcraftApi.registerEntityTag("goblin.GoblinMiner2", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 1).add(Aspect.MINE, 1));
		
		ThaumcraftApi.registerEntityTag("goblin.Direwolf", new AspectList().add(Aspect.BEAST, 3).add(Aspect.EARTH, 3).add(Aspect.WEAPON, 1));
		ThaumcraftApi.registerEntityTag("goblin.GoblinRider", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 1).add(Aspect.BEAST, 1).add(Aspect.WEAPON, 2));
		
		ThaumcraftApi.registerEntityTag("goblin.GoblinBomber", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 1).add(Aspect.ENTROPY, 2));
		
		ThaumcraftApi.registerEntityTag("goblin.GoblinSoldier", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 1).add(Aspect.ARMOR, 1).add(Aspect.WEAPON, 2));	
		ThaumcraftApi.registerEntityTag("goblin.GoblinLord", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 3).add(Aspect.ARMOR, 3).add(Aspect.WEAPON, 4));

		ThaumcraftApi.registerEntityTag("goblin.GOBLINEntityGoblinRanger", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 1).add(Aspect.WEAPON, 2));
		ThaumcraftApi.registerEntityTag("goblin.GOBLINEntityGoblinRangerGuard", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 1).add(Aspect.WEAPON, 2));

		ThaumcraftApi.registerEntityTag("goblin.GoblinNinja", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 1).add(Aspect.WEAPON, 3));	
		
		ThaumcraftApi.registerEntityTag("goblin.GoblinMage", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 4).add(Aspect.MAGIC, 3));
		
	}

}
