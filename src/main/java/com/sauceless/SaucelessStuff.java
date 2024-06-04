package com.sauceless;

import com.sauceless.item.ModItemGroups;
import com.sauceless.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaucelessStuff implements ModInitializer {
	public static final String MOD_ID = "sauceless_stuff";
	public static Identifier id(String path) {
		return Identifier.of(SaucelessStuff.MOD_ID, path);
	}
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("INITIALIZED!");
		ModItemGroups.registerItemGroup();
		ModItems.registerModItems();
	}
}