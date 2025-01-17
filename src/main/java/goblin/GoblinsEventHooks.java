package goblin;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import goblin.entity.EntityGoblinNinja;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
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
	 		
	 		Random rand = new Random();
	 		
	 		if (
	 				rand.nextInt(100) < 50
	 				&& !entityPlayer.worldObj.isRemote
	 				&& doesPlayerHaveGoblinTotemsInTheirInventoryIfSoRemoveIt(entityPlayer)
	 			)
	 		{
	 			EntityPotion entityPotion = new EntityPotion(entityPlayer.worldObj);
	            entityPotion.setPotionDamage(Potion.blindness.getId());
	            entityPotion.setPosition(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
	            entityPlayer.worldObj.spawnEntityInWorld(entityPotion);
	 			
	            int potionEffectDurationInSeconds = 8;
	            entityPlayer.addPotionEffect(new PotionEffect(Potion.blindness.getId(), potionEffectDurationInSeconds * 20, 0));
	            
	 			
	            int amountOfGoblinsToSpawn = entityPlayer.experienceLevel / 5;
	            
	            if (amountOfGoblinsToSpawn < 1) {amountOfGoblinsToSpawn = 1;} //min 1 goblin
	            
	            if (amountOfGoblinsToSpawn > 8) {amountOfGoblinsToSpawn = 8;} //max 8 goblins
	            
	            if(
	            		entityPlayer.getTotalArmorValue() >= 10
	            		&& amountOfGoblinsToSpawn < 4
	            	)
	            {
	            	amountOfGoblinsToSpawn += rand.nextInt(4);
	            }
	            
	            for (int index = 0; index < amountOfGoblinsToSpawn; index++)
	            {
	            	EntityGoblinNinja goblinNinja = new EntityGoblinNinja(entityPlayer.worldObj);
		            goblinNinja.setPosition(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
		            
		            goblinNinja.setTarget(entityPlayer);
		            
		            entityPlayer.worldObj.spawnEntityInWorld(goblinNinja);
	            }
	 		}
	    }
	 	
	 	
	 	public static boolean doesPlayerHaveGoblinTotemsInTheirInventoryIfSoRemoveIt(EntityPlayer player)
		{
			InventoryPlayer inventoryOfPlayer = player.inventory;

			return
				(
					inventoryOfPlayer.consumeInventoryItem(Item.getItemFromBlock(Goblins.totemR))
					|| inventoryOfPlayer.consumeInventoryItem(Item.getItemFromBlock(Goblins.totemG))
					|| inventoryOfPlayer.consumeInventoryItem(Item.getItemFromBlock(Goblins.totemB))
					|| inventoryOfPlayer.consumeInventoryItem(Item.getItemFromBlock(Goblins.totemY))
				);

		}
}
