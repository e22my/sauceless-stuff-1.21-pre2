package com.sauceless;

import com.sauceless.block.ModBlocks;
import com.sauceless.item.ModItemGroups;
import com.sauceless.item.ModItems;
import com.sauceless.item.advancement.advancementHandler;
import com.sauceless.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaucelessStuff implements ModInitializer {
	public static final String MOD_ID = "sauceless_stuff";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static Identifier id(String path) {
		return Identifier.of(SaucelessStuff.MOD_ID, path);
	}

	public static SoundEvent COMMON_SOUND = SoundEvent.of(id("common"));
	public static SoundEvent RARE_SOUND = SoundEvent.of(id("rare"));
	public static SoundEvent EPIC_SOUND = SoundEvent.of(id("epic"));

	@Override
	public void onInitialize() {
		LOGGER.info("INITIALIZED!");
		//Call Method to register new item group
		ModItemGroups.registerItemGroup();

		//Call Methods to register new content
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModLootTableModifiers.modifyLootTables();
		Registry.register(Registries.SOUND_EVENT, id("common"), COMMON_SOUND);
		Registry.register(Registries.SOUND_EVENT, id("rare"), RARE_SOUND);
		Registry.register(Registries.SOUND_EVENT, id("epic"), EPIC_SOUND);

		/*
		* Every GAME_MESSAGE, get player from GAME_MESSAGE
		* THEN update the advancement progress via AdvHandler
		* */

		ServerMessageEvents.GAME_MESSAGE.register((server, message, overlay) -> {
			advancementHandler handler = new advancementHandler();
			String[] playerNames = server.getPlayerNames();
			ServerPlayerEntity player;
			for(String playerName : playerNames) {
				if (message.toString().contains(playerName)) {
					player = server.getPlayerManager().getPlayer(playerName);
					handler.advancementProgress(player);
					break;
				}
			}
			//Here, I would figure out how to use the new Fabric Networking API to save progress
			//rather than constantly check advancements....TOO BAD!
		});
	}
}