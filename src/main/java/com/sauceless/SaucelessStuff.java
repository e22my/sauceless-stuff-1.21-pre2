package com.sauceless;

import com.sauceless.block.ModBlocks;
import com.sauceless.item.ModItemGroups;
import com.sauceless.item.ModItems;
import com.sauceless.item.advancement.advancementHandler;
import com.sauceless.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaucelessStuff implements ModInitializer {
	public static final String MOD_ID = "sauceless_stuff";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.of(SaucelessStuff.MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("INITIALIZED!");
		//Call Method to register new item group
		ModItemGroups.registerItemGroup();

		//Call Methods to register new content
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModLootTableModifiers.modifyLootTables();

		/*
		* Every GAME_MESSAGE, get player from GAME_MESSAGE
		* THEN update the advancement progress via AdvHandler
		* */

		ServerMessageEvents.GAME_MESSAGE.register((server, message, overlay) -> {
			advancementHandler handler = new advancementHandler();
			String[] playerNames = server.getPlayerNames();
			ServerPlayerEntity player = null;
			for(String playerName : playerNames) {
				if (message.toString().contains(playerName)) {
					player = server.getPlayerManager().getPlayer(playerName);
					handler.advancementProgress(player);
					break;
				}
			}
			if (player!=null){
				PlayerData playerState = StateSaverAndLoader.getPlayerState(player);
				playerState.progress = handler.progress;
			}
			//Here, I would figure out how to use the new Fabric Networking API to save progress
			//rather than constantly check advancements....TOO BAD!
		});
	}
}