package com.sauceless.item;

import com.sauceless.SaucelessStuff;
import com.sauceless.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;


public class ModItemGroups {
    public static final ItemGroup SAUCELESS = Registry.register(Registries.ITEM_GROUP,
            SaucelessStuff.id("sauceless"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.sauceless"))
                    .icon(() -> new ItemStack(ModItems.COIN)).entries((displayContext, entries) -> {
                        entries.add(ModItems.COIN);
                        entries.add(ModBlocks.YGH_BLOCK);
                        entries.add(ModItems.SAUCE);
                        entries.add(ModItems.RARE_SAUCE);
                        entries.add(ModItems.EPIC_SAUCE);

                    }).build());
    public static void registerItemGroup(){
        SaucelessStuff.LOGGER.info("Registering Item Groups for " + SaucelessStuff.MOD_ID);
    }
}
