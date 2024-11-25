package goblin;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import goblin.achievements.GoblinsAchievements;
import goblin.entity.EntityGoblinNinja;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;

public class GoblinsEventHooks {

	 	@SubscribeEvent
	    public void playerWakeUpEvent(PlayerWakeUpEvent event)
	    {
	 		EntityPlayer entityPlayer = event.entityPlayer;
	 		
	 		EntityPlayerMP entityPlayerMP = MinecraftServer.getServer().getConfigurationManager().func_152612_a(entityPlayer.getCommandSenderName());
	 		
	 		Random rand = new Random();
	 		
	 		if (
	 				(
	 						(
	 								
	 								!(entityPlayerMP.func_147099_x().hasAchievementUnlocked(GoblinsAchievements.goblin_ninja_attack))
	 								&& rand.nextInt(100) < 50
	 						)
	 						|| (	//lower chance for Goblin Ninja attack if the player already has the achievement
	 								entityPlayerMP.func_147099_x().hasAchievementUnlocked(GoblinsAchievements.goblin_ninja_attack)
	 								&& rand.nextInt(100) < 10
	 						)
	 				)
	 				&& !(entityPlayer.worldObj.isDaytime())
	 				&& doesPlayerHaveGoblinTotemsInTheirInventory(entityPlayer)
	 			)
	 		{
	 			EntityPotion entityPotion = new EntityPotion(entityPlayer.worldObj);
	            entityPotion.setPotionDamage(Potion.blindness.getId());
	            entityPotion.setPosition(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
	            entityPlayer.worldObj.spawnEntityInWorld(entityPotion);
	 			
	            int potionEffectDurationInSeconds = 4;
	            entityPlayer.addPotionEffect(new PotionEffect(Potion.blindness.getId(), potionEffectDurationInSeconds * 20, 0));
	            
	 			
	            int amountOfGoblinsToSpawn = entityPlayer.experienceLevel / 5;
	            
	            if (amountOfGoblinsToSpawn < 1) {amountOfGoblinsToSpawn = 1;} //min 1 goblin
	            
	            if (amountOfGoblinsToSpawn > 6) {amountOfGoblinsToSpawn = 8;} //max 8 goblins
	            
	            for (int index = 0; index < amountOfGoblinsToSpawn; index++)
	            {
	            	EntityGoblinNinja goblinNinja = new EntityGoblinNinja(entityPlayer.worldObj);
		            goblinNinja.setPosition(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
		            
		            goblinNinja.setTarget(entityPlayer);
		            
		            entityPlayer.worldObj.spawnEntityInWorld(goblinNinja); 
		            
		            entityPlayer.addStat(GoblinsAchievements.goblin_ninja_attack, 1);
	            }
	 		}
	    }
	 	
	 	
	 	public static boolean doesPlayerHaveGoblinTotemsInTheirInventory(EntityPlayer player)
		{
			ItemStack[] inventoryOfPlayer = player.inventory.mainInventory;
			
			int iterationLength = inventoryOfPlayer.length;
			
			if (iterationLength > 0)
			{
				for (int index = 0; index < iterationLength; index++) //iterates through all slots of the player's inventory
				{
					ItemStack itemStackInInventorySlot = inventoryOfPlayer[index];
					
					if (itemStackInInventorySlot != null)
					{
						Item itemInInventorySlot = itemStackInInventorySlot.getItem();
						if (
								itemInInventorySlot == Item.getItemFromBlock(Goblins.totemR)
								|| itemInInventorySlot == Item.getItemFromBlock(Goblins.totemG)
								|| itemInInventorySlot == Item.getItemFromBlock(Goblins.totemB)
								|| itemInInventorySlot == Item.getItemFromBlock(Goblins.totemY)
							)
						{	
							return true;
						}
					}
				}
			}	
			return false;
		}
}
