package com.sauceless.item;

import com.sauceless.SaucelessStuff;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {
    public static final Item COIN = registerItem("coin", new Item(new Item.Settings()));

    private static void addToINGREDIENTSItemGroup(FabricItemGroupEntries entries){
        entries.add(COIN);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, SaucelessStuff.id(name), item);
    }
    public static void registerModItems(){
        SaucelessStuff.LOGGER.info("Registering Mod Items for: " + SaucelessStuff.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addToINGREDIENTSItemGroup);
    }
}
