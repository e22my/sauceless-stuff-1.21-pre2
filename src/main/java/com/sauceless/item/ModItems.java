package com.sauceless.item;

import com.sauceless.SaucelessStuff;
import com.sauceless.item.custom.lootType;
import com.sauceless.item.custom.lootboxItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item COIN = registerItem("coin", new Item(new Item.Settings()));
    public static final Item SAUCE = registerItem("sauce",
            new lootboxItem(new Item.Settings().maxCount(16).rarity(Rarity.UNCOMMON),
                    lootType.COMMON));
    public static final Item RARE_SAUCE = registerItem("rare_sauce",
            new lootboxItem(new Item.Settings().maxCount(16).rarity(Rarity.RARE),
                    lootType.RARE));
    public static final Item EPIC_SAUCE = registerItem("epic_sauce",
            new lootboxItem(new Item.Settings().maxCount(16).rarity(Rarity.EPIC),
                    lootType.EPIC));

    private static void addToINGREDIENTSItemGroup(FabricItemGroupEntries entries){
        entries.add(COIN);
    }
    private static void addToFOODItemGroup(FabricItemGroupEntries entries){

        entries.add(SAUCE);
        entries.add(RARE_SAUCE);
        entries.add(EPIC_SAUCE);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, SaucelessStuff.id(name), item);
    }
    public static void registerModItems(){
        SaucelessStuff.LOGGER.info("Registering Mod Items for: " + SaucelessStuff.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addToINGREDIENTSItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addToFOODItemGroup);
    }
}
