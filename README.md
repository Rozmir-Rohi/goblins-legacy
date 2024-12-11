# Goblins Legacy
Port of the [Goblins!](https://www.curseforge.com/minecraft/mc-mods/goblins-mod) mod for 1.7.10 with a few improvements.

<strong>Project Status: </strong> Final stages, accepting minor feature suggestions until February 2025 

<details>
<summary><h2>Changes to Original Mod:</h2></summary>
 
Ported the original mod from 1.7.2 to 1.7.10.

Fixed format of sounds.json.

Added Thaumcraft aspects to all content.

Added Goblins Creative Tab. Also added spawn eggs to Goblins creative tab.

Added Goblins achievements page.

Fixed format of code for configuration generation.

Fixed texture bug of primed enchanted TNT and primed overcharged TNT.

Readded Goblin Ninja, Shuriken, and Katana.

All goblins now swing their arm like players in melee attacks.

Goblin Ninjas now attack with either Shuriken or Katana depending on their distance to the player/enemy.

Increased attack damage of Flame Blade to 6.

Goblin Shaman now hold the Arcane Staff and Teleportation Staff for their attacks/abilities.

Fixed display of all staves.

Teleportation Staff now makes the normal teleportation sound.

Teleportation staff now teleports the player to the block they look at in a small range. If used while the player is inside a block, this staff can now also has a chance of teleporting the player to a safe location nearby.

Teleportation Staff and Goblin Shaman now make particle when they teleport.

Goblins no longer try to fight each other.

Goblin Lord and Goblin Shaman are now resistant to explosions that deal up to 50 HP of damage.

Goblin Shaman now has a chance to drop a few totem powders on death.

Fixed format of drop items code for all entities.

Players now swing their arm when using staves.

Fixed durability of the Scepter of Life.

Changed the name of the yellow totem, from "Holy Totem" to "Sky Totem".

Changed the name of the green totem, from "Nature Totem" to "Earth Totem".

Renamed village spawner to Goblin Village Spawner.

Readjusted texture of Katana so that the blade is on the correct side.
 
Fixed staves being stackable.

Scepter of Life now heals users when used to grow flora. Also added sound effect for this action.

Fixed Goblin Rangers and Goblin Miners not spawnin from their spawners.

Fixed Goblin Spawner death config not working.

Readded Goblin Drum

</details>

<details>
<summary><h2>Credits & Assets Used:</h2></summary>
 
<strong>Jan Orlowski (Sartharis)</strong> - Creator of the Goblins! mod. This project is a modified version of the Goblins! mod and is licensed under the "MIT" license as instructed from the written permission from the original creator. Proof of permission can be found inside the assets folder of the mod jar archive as an image file.

<strong>Azanor</strong> - Thaumcraft 4.2.2 API was used as a library under the MIT License to add Thaumcraft aspects to the mod content.

<strong>user17512883</strong> - Their forum post helped me fix the primed TNT render bug (https://stackoverflow.com/questions/70119718/minecraft-forge-1-7-10-custom-entity-not-spawning-on-the-client).

<strong>Kara (Freesound)</strong> - The sound effect for the Goblin Drum was sourced from https://pixabay.com/sound-effects/bongo3-107664/ . This was used under the Pixabay License (see "Negligable Licenses" folder inside the assets folder of the mod jar archive).

<strong>New Teleportation Staff Mechanism:</strong>
<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong>coolAlias</strong> - Their answer in Minecraft Forums helped me to work out the base code for the new mechanism (https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/modification-development/2132650-solved-1-7-2-forge-block-player-is-looking-at).

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong>AlchemyMouse</strong> - Their answer in StackOverflow me helped resolve vectors needed for the new mechanism (https://gamedev.stackexchange.com/questions/59858/how-to-find-the-entity-im-looking-at).

<br><br>
---
<h3> Original Goblins! Contributer Credits </h3>
<br>
<strong>Hengabone</strong> - Made the goblin village templates and awesome Beta-Tester Posted Image.
<br><br>
<strong>Garrura and Srymon</strong> - For being great friends of Sartharis and giving ideas!
<br><br>
<strong>mister_person</strong> - For giving Sartharis the materials to craft the nature arrow!
<br><br>
<strong>cowmonkey (AKA Madgoblin)</strong> - For showing Sartharis the bomb blueprints.
<br><br>
<strong>Geethebluesky</strong> - For solving the configuration file mystery.
<br><br>
<strong>Cowguy666</strong> - For making the drum texture.
<br>
<hr>
<br><br><br>

<strong>Special Thanks:</strong>
<br>
<strong>nanaqui </strong>and <strong>sunconure11</strong> - For suggesting me to take on this project. This project wouldn't have existed if it wasn't for their request.
<br><br><br><br><br><br><br><br>

## Use of anatawa12's Fork of ForgeGradle 1.2 within Project:
The source code of this project uses anatawa12's fork of ForgeGradle 1.2 as a library under the GNU Lesser General Public License v2.1 (https://choosealicense.com/licenses/lgpl-2.1/).
 

Compiled versions of this mod are permitted under section 5 of the original license, "A program that contains no derivative of any portion of the Library, but is designed to work with the Library by being compiled or linked with it, is called a "work that uses the Library". Such a work...is not a derivative work of the Library"; consequently the conditions of the original license do not apply to the work.

The source code of this mod is permitted under section 6 of the original license, "you may also combine or link a "work that uses the Library" with the Library to produce a work containing portions of the Library, and distribute that work under terms of your choice" provided that:
* Private modifications are allowed.
* Notice is given that the Library is used and a copy of it's original license is provided.
* Access is provided to the source code of the Library.


The source code for anatawa12's fork of ForgeGradle 1.2 can be found here: https://github.com/anatawa12/ForgeGradle-1.2

</details>
