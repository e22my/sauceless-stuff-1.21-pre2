package com.sauceless.item;

import com.sauceless.SaucelessStuff;
import com.sauceless.item.lootbox.LootType;
import com.sauceless.item.lootbox.LootboxItem;
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
            new LootboxItem(new Item.Settings().maxCount(16).rarity(Rarity.UNCOMMON),
                    LootType.COMMON));
    public static final Item RARE_SAUCE = registerItem("rare_sauce",
            new LootboxItem(new Item.Settings().maxCount(16).rarity(Rarity.RARE),
                    LootType.RARE));
    public static final Item EPIC_SAUCE = registerItem("epic_sauce",
            new LootboxItem(new Item.Settings().maxCount(16).rarity(Rarity.EPIC),
                    LootType.EPIC));

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
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addToFOODItemGroup);
    }
}
