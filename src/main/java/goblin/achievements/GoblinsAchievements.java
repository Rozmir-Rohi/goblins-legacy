package goblin.achievements;

import cpw.mods.fml.common.FMLCommonHandler;
import goblin.Goblins;
import net.minecraft.stats.Achievement;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.AchievementPage;

public class GoblinsAchievements {
	/*
	 * ============================= NOTICE ====================================
	 * 
	 * Do NOT convert the fields in this class to camel case.
	 * 
	 * Snake case is used ON PURPOSE FOR FIELDS IN THIS CLASS to differentiate
	 * from the multiple very similar fields that are called from other classes.
	 * 
	 * 
	 * =========================================================================
	 * 
	 */
		
		
	public static Achievement red_totem;
	public static Achievement explosive_powder;
	public static Achievement explosive_orb;
	public static Achievement enchanted_tnt;
	public static Achievement overcharged_tnt;
	public static Achievement fiery_crystal;
	public static Achievement flame_blade;
	
	public static Achievement green_totem;
	public static Achievement nature_powder;
	public static Achievement nature_orb;
	public static Achievement goblin_drum;
	public static Achievement nature_crystal;
	public static Achievement scepter_of_life;
	
	public static Achievement blue_totem;
	public static Achievement arcane_powder;
	public static Achievement force_orb;
	public static Achievement arcane_crystal;
	public static Achievement arcane_staff;
	
	public static Achievement teleportation_staff;
	
	public static Achievement yellow_totem;
	public static Achievement lightning_powder;
	public static Achievement lightning_orb;
	public static Achievement lightning_crystal;
	public static Achievement lightning_staff;
	
	public static Achievement bomb;
	public static Achievement shuriken;
	
	public static Achievement goblin_flesh;
	
	public static Achievement kill_goblin;
	public static Achievement kill_dire_wolf;
	public static Achievement kill_goblin_ranger;
	public static Achievement kill_goblin_bomber;
	public static Achievement kill_goblin_soldier;
	public static Achievement kill_goblin_rider;
	public static Achievement kill_goblin_miner;
	public static Achievement kill_goblin_lord;
	public static Achievement kill_goblin_shaman;
	
	public static Achievement kill_goblin_ninja;
	
	public static Achievement katana;
	
	
	private static final int TOTEM_ACHIEVEMENTS_X_BASE = 3;
	
	private static final int TOTEM_ACHIEVEMENTS_Y_BASE = 8;
	
	private static final int TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING = 5;
	
	private static final int Y_CENTER = ((TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 1)) + (TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 0)))/2;
	
	
	public static void initilization()
	{
		FMLCommonHandler.instance().bus().register(new GoblinsAchievementEvents());
		
		red_totem = new Achievement("achievement.red_totem", "red_totem", TOTEM_ACHIEVEMENTS_X_BASE, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 2), Goblins.totemR, (Achievement)null).initIndependentStat().registerStat();
		explosive_powder = new Achievement("achievement.explosive_powder", "explosive_powder", TOTEM_ACHIEVEMENTS_X_BASE + 2, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 2), Goblins.powderR, red_totem).registerStat();
		explosive_orb = new Achievement("achievement.explosive_orb", "explosive_orb", TOTEM_ACHIEVEMENTS_X_BASE + 1, (TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 2)) - 2, Goblins.orbExplosive, explosive_powder).registerStat();
		enchanted_tnt = new Achievement("achievement.enchanted_tnt", "enchanted_tnt", TOTEM_ACHIEVEMENTS_X_BASE + 3, (TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 2)) -2, Goblins.ETNT, explosive_powder).registerStat();
		overcharged_tnt = new Achievement("achievement.overcharged_tnt", "overcharged_tnt", TOTEM_ACHIEVEMENTS_X_BASE + 3, (TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 2)) -4, Goblins.MTNT, enchanted_tnt).registerStat();
		fiery_crystal = new Achievement("achievement.fiery_crystal", "fiery_crystal", TOTEM_ACHIEVEMENTS_X_BASE + 4, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 2), Goblins.crystalR, explosive_powder).registerStat();
		flame_blade = new Achievement("achievement.flame_blade", "flame_blade", TOTEM_ACHIEVEMENTS_X_BASE + 6, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 2), Goblins.swordFire, fiery_crystal).registerStat();
		
		
		green_totem = new Achievement("achievement.green_totem", "green_totem", TOTEM_ACHIEVEMENTS_X_BASE, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 1), Goblins.totemG, (Achievement)null).initIndependentStat().registerStat();
		nature_powder = new Achievement("achievement.nature_powder", "nature_powder", TOTEM_ACHIEVEMENTS_X_BASE + 2, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 1), Goblins.powderG, green_totem).registerStat();
		nature_orb = new Achievement("achievement.nature_orb", "nature_orb", TOTEM_ACHIEVEMENTS_X_BASE + 1, (TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 1)) - 2, Goblins.orbNature, nature_powder).registerStat();
		goblin_drum = new Achievement("achievement.goblin_drum", "goblin_drum", TOTEM_ACHIEVEMENTS_X_BASE + 3, (TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 1)) - 2, Goblins.gobDrum, nature_powder).registerStat();
		nature_crystal = new Achievement("achievement.nature_crystal", "nature_crystal", TOTEM_ACHIEVEMENTS_X_BASE + 4, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 1), Goblins.crystalG, nature_powder).registerStat();
		scepter_of_life = new Achievement("achievement.scepter_of_life", "scepter_of_life", TOTEM_ACHIEVEMENTS_X_BASE + 6, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 1), Goblins.staffNature, nature_crystal).registerStat();
		
		
		blue_totem = new Achievement("achievement.blue_totem", "blue_totem", TOTEM_ACHIEVEMENTS_X_BASE, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 0), Goblins.totemB, (Achievement)null).initIndependentStat().registerStat();
		arcane_powder = new Achievement("achievement.arcane_powder", "arcane_powder", TOTEM_ACHIEVEMENTS_X_BASE + 2, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 0), Goblins.powderB, blue_totem).registerStat();
		force_orb = new Achievement("achievement.force_orb", "force_orb", TOTEM_ACHIEVEMENTS_X_BASE + 2, (TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 0)) - 2, Goblins.orbForce, arcane_powder).registerStat();
		arcane_crystal = new Achievement("achievement.arcane_crystal", "arcane_crystal", TOTEM_ACHIEVEMENTS_X_BASE + 4, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 0), Goblins.crystalB, arcane_powder).registerStat();
		arcane_staff = new Achievement("achievement.arcane_staff", "arcane_staff", TOTEM_ACHIEVEMENTS_X_BASE + 6, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * 0), Goblins.staffArcane, arcane_crystal).registerStat();
		
		teleportation_staff = new Achievement("achievement.teleportation_staff", "teleportation_staff", TOTEM_ACHIEVEMENTS_X_BASE + 8, Y_CENTER, Goblins.staffTeleport, (Achievement)null).initIndependentStat().registerStat();
		
		yellow_totem = new Achievement("achievement.lightning_totem", "lightning_totem", TOTEM_ACHIEVEMENTS_X_BASE, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * -1), Goblins.totemY, (Achievement)null).initIndependentStat().registerStat();
		lightning_powder = new Achievement("achievement.lightning_powder", "lightning_powder", TOTEM_ACHIEVEMENTS_X_BASE + 2, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * -1), Goblins.powderY, yellow_totem).registerStat();
		lightning_orb = new Achievement("achievement.lightning_orb", "lightning_orb", TOTEM_ACHIEVEMENTS_X_BASE + 2, (TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * -1)) - 2, Goblins.orbLightning, lightning_powder).registerStat();
		lightning_crystal = new Achievement("achievement.lightning_crystal", "lightning_crystal", TOTEM_ACHIEVEMENTS_X_BASE + 4, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * -1), Goblins.crystalY, lightning_powder).registerStat();
		lightning_staff = new Achievement("achievement.lightning_staff", "lightning_staff", TOTEM_ACHIEVEMENTS_X_BASE + 6, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * -1), Goblins.staffLightning, lightning_crystal).registerStat();
		
		
		goblin_flesh = new Achievement("achievement.goblin_flesh", "goblin_flesh", -TOTEM_ACHIEVEMENTS_X_BASE, TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * -1), Goblins.goblinFlesh, (Achievement)null).initIndependentStat().registerStat();
		bomb = new Achievement("achievement.bomb", "bomb", -TOTEM_ACHIEVEMENTS_X_BASE,  TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * -1), Goblins.bomb, (Achievement)null).initIndependentStat().registerStat();
		shuriken = new Achievement("achievement.shuriken", "shuriken", -TOTEM_ACHIEVEMENTS_X_BASE - 2,  TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * -1), Goblins.shuriken, (Achievement)null).initIndependentStat().registerStat();
		katana = new Achievement("achievement.katana", "katana", -TOTEM_ACHIEVEMENTS_X_BASE - 4,  TOTEM_ACHIEVEMENTS_Y_BASE - (TOTEM_ACHIEVEMENTS_Y_OVERALL_SPACING * -1), Goblins.swordKatana, kill_goblin_ninja).registerStat();
		
		
		kill_goblin = new Achievement("achievement.kill_goblin", "kill_goblin", -TOTEM_ACHIEVEMENTS_X_BASE, TOTEM_ACHIEVEMENTS_Y_BASE - 4, Goblins.achievement_icon_kill_goblin, (Achievement)null).initIndependentStat().registerStat();
		
		kill_dire_wolf = new Achievement("achievement.kill_dire_wolf", "kill_dire_wolf", -TOTEM_ACHIEVEMENTS_X_BASE - 2, TOTEM_ACHIEVEMENTS_Y_BASE - 1, Goblins.achievement_icon_kill_dire_wolf, (Achievement)null).initIndependentStat().registerStat();
		kill_goblin_rider  = new Achievement("achievement.kill_goblin_rider", "kill_goblin_rider", -TOTEM_ACHIEVEMENTS_X_BASE - 4, TOTEM_ACHIEVEMENTS_Y_BASE - 1, Goblins.achievement_icon_kill_goblin_rider, (Achievement)null).initIndependentStat().registerStat();
		
		kill_goblin_ranger = new Achievement("achievement.kill_goblin_ranger", "kill_goblin_ranger", -TOTEM_ACHIEVEMENTS_X_BASE - 2, TOTEM_ACHIEVEMENTS_Y_BASE - 7, Goblins.achievement_icon_kill_goblin_ranger, (Achievement)null).initIndependentStat().registerStat();
		kill_goblin_ninja = new Achievement("achievement.kill_goblin_ninja", "kill_goblin_ninja", -TOTEM_ACHIEVEMENTS_X_BASE - 4, TOTEM_ACHIEVEMENTS_Y_BASE - 7, Goblins.achievement_icon_kill_goblin_ninja, (Achievement)null).initIndependentStat().registerStat();
		
		kill_goblin_soldier = new Achievement("achievement.kill_goblin_soldier", "kill_goblin_soldier", -TOTEM_ACHIEVEMENTS_X_BASE - 2, TOTEM_ACHIEVEMENTS_Y_BASE - 5, Goblins.achievement_icon_kill_goblin_soldier, (Achievement)null).initIndependentStat().registerStat();
		
		kill_goblin_miner = new Achievement("achievement.kill_goblin_miner", "kill_goblin_miner", -TOTEM_ACHIEVEMENTS_X_BASE - 2, TOTEM_ACHIEVEMENTS_Y_BASE - 3, Goblins.achievement_icon_kill_goblin_miner, (Achievement)null).initIndependentStat().registerStat();
		kill_goblin_bomber = new Achievement("achievement.kill_goblin_bomber", "kill_goblin_bomber", -TOTEM_ACHIEVEMENTS_X_BASE - 4, TOTEM_ACHIEVEMENTS_Y_BASE - 3, Goblins.achievement_icon_kill_goblin_bomber, (Achievement)null).initIndependentStat().registerStat();
		
		kill_goblin_lord = new Achievement("achievement.kill_goblin_lord", "kill_goblin_lord", -TOTEM_ACHIEVEMENTS_X_BASE - 6, TOTEM_ACHIEVEMENTS_Y_BASE - 5, Goblins.achievement_icon_kill_goblin_lord, (Achievement)null).initIndependentStat().setSpecial().registerStat();
		kill_goblin_shaman = new Achievement("achievement.kill_goblin_shaman", "kill_goblin_shaman", -TOTEM_ACHIEVEMENTS_X_BASE - 6, TOTEM_ACHIEVEMENTS_Y_BASE - 2, Goblins.achievement_icon_kill_goblin_shaman, (Achievement)null).initIndependentStat().setSpecial().registerStat();
		
		
		
		
		
		AchievementPage.registerAchievementPage(new AchievementPage(StatCollector.translateToLocal("achievements.GoblinsTab"), new Achievement[]{
				red_totem,
				explosive_powder,
				explosive_orb,
				enchanted_tnt,
				overcharged_tnt,
				fiery_crystal,
				flame_blade,
				
				green_totem,
				nature_powder,
				nature_orb,
				goblin_drum,
				nature_crystal,
				scepter_of_life,
				
				blue_totem,
				arcane_powder,
				force_orb,
				arcane_crystal,
				arcane_staff,
				
				teleportation_staff,
				
				yellow_totem,
				lightning_powder,
				lightning_orb,
				lightning_crystal,
				lightning_staff,
				
				bomb,
				shuriken,
				
				goblin_flesh,
				
				
				kill_goblin,
				kill_dire_wolf,
				kill_goblin_ranger,
				kill_goblin_bomber,
				kill_goblin_soldier,
				kill_goblin_rider,
				kill_goblin_miner,
				kill_goblin_lord,
				kill_goblin_shaman,
				kill_goblin_ninja,
				
				katana
		}));
	}

}
