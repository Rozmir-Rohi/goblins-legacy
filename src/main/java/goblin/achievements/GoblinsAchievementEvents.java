package goblin.achievements;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import goblin.Goblins;
import net.minecraft.item.Item;

public class GoblinsAchievementEvents {

	@SubscribeEvent
	public void GoblinsItemPickupEvent(PlayerEvent.ItemPickupEvent event)
	{
		if (event.pickedUp.getEntityItem().getItem().equals(Item.getItemFromBlock(Goblins.totemR))) {event.player.addStat(GoblinsAchievements.red_totem, 1);}
		
		if (event.pickedUp.getEntityItem().getItem().equals(Item.getItemFromBlock(Goblins.totemG))) {event.player.addStat(GoblinsAchievements.green_totem, 1);}
		
		if (event.pickedUp.getEntityItem().getItem().equals(Item.getItemFromBlock(Goblins.totemB))) {event.player.addStat(GoblinsAchievements.blue_totem, 1);}
		
		if (event.pickedUp.getEntityItem().getItem().equals(Item.getItemFromBlock(Goblins.totemY))) {event.player.addStat(GoblinsAchievements.yellow_totem, 1);}
		
		if (event.pickedUp.getEntityItem().getItem().equals(Goblins.goblinFlesh)) {event.player.addStat(GoblinsAchievements.goblin_flesh, 1);}
	}
	
	@SubscribeEvent
	public void MoCItemCraftedEvent(PlayerEvent.ItemCraftedEvent event)
	{
		if (event.crafting.getItem().equals(Goblins.powderR)) {event.player.addStat(GoblinsAchievements.explosive_powder, 1);}
		if (event.crafting.getItem().equals(Goblins.orbExplosive)) {event.player.addStat(GoblinsAchievements.explosive_orb, 1);}
		if (event.crafting.getItem().equals(Item.getItemFromBlock(Goblins.ETNT))) {event.player.addStat(GoblinsAchievements.enchanted_tnt, 1);}
		if (event.crafting.getItem().equals(Item.getItemFromBlock(Goblins.MTNT))) {event.player.addStat(GoblinsAchievements.overcharged_tnt, 1);}
		if (event.crafting.getItem().equals(Goblins.crystalR)) {event.player.addStat(GoblinsAchievements.fiery_crystal, 1);}
		if (event.crafting.getItem().equals(Goblins.swordFire)) {event.player.addStat(GoblinsAchievements.flame_blade, 1);}



		if (event.crafting.getItem().equals(Goblins.powderG)) {event.player.addStat(GoblinsAchievements.nature_powder, 1);}
		if (event.crafting.getItem().equals(Goblins.orbNature)) {event.player.addStat(GoblinsAchievements.nature_orb, 1);}
		if (event.crafting.getItem().equals(Goblins.crystalG)) {event.player.addStat(GoblinsAchievements.nature_crystal, 1);}
		if (event.crafting.getItem().equals(Goblins.staffNature)) {event.player.addStat(GoblinsAchievements.scepter_of_life, 1);}



		if (event.crafting.getItem().equals(Goblins.powderB)) {event.player.addStat(GoblinsAchievements.arcane_powder, 1);}
		if (event.crafting.getItem().equals(Goblins.orbForce)) {event.player.addStat(GoblinsAchievements.force_orb, 1);}
		if (event.crafting.getItem().equals(Goblins.crystalB)) {event.player.addStat(GoblinsAchievements.arcane_crystal, 1);}
		if (event.crafting.getItem().equals(Goblins.staffArcane)) {event.player.addStat(GoblinsAchievements.arcane_staff, 1);}

		if (event.crafting.getItem().equals(Goblins.staffTeleport)) {event.player.addStat(GoblinsAchievements.teleportation_staff, 1);}


		if (event.crafting.getItem().equals(Goblins.powderY)) {event.player.addStat(GoblinsAchievements.lightning_powder, 1);}
		if (event.crafting.getItem().equals(Goblins.orbLightning)) {event.player.addStat(GoblinsAchievements.lightning_orb, 1);}
		if (event.crafting.getItem().equals(Goblins.crystalY)) {event.player.addStat(GoblinsAchievements.lightning_crystal, 1);}
		if (event.crafting.getItem().equals(Goblins.staffLightning)) {event.player.addStat(GoblinsAchievements.lightning_staff, 1);}


		if (event.crafting.getItem().equals(Goblins.bomb)) {event.player.addStat(GoblinsAchievements.bomb, 1);}
	}
}
