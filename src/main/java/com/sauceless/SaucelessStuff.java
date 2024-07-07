package com.sauceless;

import com.sauceless.block.ModBlocks;
import com.sauceless.item.ModItemGroups;
import com.sauceless.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaucelessStuff implements ModInitializer {
	//Commonly Used Variables by other classes
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

		/*
		* Every GAME_MESSAGE, get player from GAME_MESSAGE
		* THEN update the advancement progress via AdvHandler
		* */

		//Here, I would figure out how to use the new Fabric Networking API to save progress
		//rather than constantly check advancements....TOO BAD!
		ServerMessageEvents.GAME_MESSAGE.register((server, message, overlay) -> {
			String[] playerNames = server.getPlayerNames();
			LivingEntity player = null;
			for(String playerName : playerNames) {
				if (message.toString().contains(playerName)) {
					player = server.getPlayerManager().getPlayer(playerName);
					break;
				}
			}
			if (player!=null){
				PlayerData playerState = StateSaverAndLoader.getPlayerState(player);
				player.sendMessage(Text.literal( "PLAYER GOTTEN! : " + player.getName()));
			}
		});
	}
}