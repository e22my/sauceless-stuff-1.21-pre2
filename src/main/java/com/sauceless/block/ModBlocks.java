package com.sauceless.block;

import com.sauceless.SaucelessStuff;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlocks {
    public static final Block YGH_BLOCK = registerBlock("ygh_block",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, SaucelessStuff.id(name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, SaucelessStuff.id(name),
                new BlockItem(block, new Item.Settings()));
    }
    public static void registerModBlocks(){
        SaucelessStuff.LOGGER.info("Registering Mod Items for: " + SaucelessStuff.MOD_ID);
    }
}
