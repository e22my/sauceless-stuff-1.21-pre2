package com.sauceless.util;

import com.sauceless.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class ModLootTableModifiers {
    public static Block[] blocks = {
            Blocks.STONE, //Natural Stone
                Blocks.DIORITE, Blocks.ANDESITE, Blocks.GRANITE,
                Blocks.DEEPSLATE, Blocks.TUFF,
            Blocks.TERRACOTTA, //Badlands Terracotta
                Blocks.WHITE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.ORANGE_TERRACOTTA,
                Blocks.RED_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA,
            Blocks.NETHERRACK, //Nether Stone
                Blocks.BASALT, Blocks.BLACKSTONE, Blocks.SOUL_SAND, Blocks.SOUL_SOIL,
            Blocks.OAK_LOG, //Wood Logs
                Blocks.ACACIA_LOG, Blocks.BIRCH_LOG, Blocks.DARK_OAK_LOG,
                Blocks.CHERRY_LOG, Blocks.MANGROVE_LOG, Blocks.SPRUCE_LOG,
                Blocks.JUNGLE_LOG, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_BIRCH_LOG,
                Blocks.STRIPPED_CHERRY_LOG, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_JUNGLE_LOG,
                Blocks.STRIPPED_MANGROVE_LOG, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_SPRUCE_LOG,
            Blocks.END_STONE, Blocks.OBSIDIAN
    };

    public static Collection<RegistryKey<LootTable>> lootTable =
            List.of(
                    LootTables.DESERT_PYRAMID_ARCHAEOLOGY,
                    LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY,
                    LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_RARE_CHEST
            );

    public static void modifyLootTables(){
        for (Block block : blocks){
            LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
                if (block.getLootTableKey() == key && source.isBuiltin()) {
                    LootPool.Builder pool = LootPool.builder()
                            .with(ItemEntry.builder(ModItems.SAUCE).weight(15))
                            .with(ItemEntry.builder(ModItems.RARE_SAUCE).weight(6))
                            .with(ItemEntry.builder(ModItems.EPIC_SAUCE).weight(1))
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(0.001f))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                    tableBuilder.pool(pool.build());
                }
            });
        }
        for (RegistryKey<LootTable> lootTableKey : lootTable) {
            LootTableEvents.REPLACE.register(((key, original, source, registries) -> {
                if (lootTableKey == key){
                    LootTable.Builder tableBuilder = FabricLootTableBuilder.copyOf(original);
                    Consumer<LootPool.Builder> pool = x -> x.with(ItemEntry.builder(ModItems.RARE_SAUCE).weight(3));
                    tableBuilder.modifyPools(pool);
                    return tableBuilder.build();
                }
                return null;
            }));
        }
        //OMINOUS TRIAL
        LootTableEvents.REPLACE.register(((key, original, source, registries) -> {
            if (LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE_CHEST == key){
                LootTable.Builder tableBuilder = FabricLootTableBuilder.copyOf(original);
                Consumer<LootPool.Builder> pool = x -> x.with(ItemEntry.builder(ModItems.EPIC_SAUCE).weight(1));
                tableBuilder.modifyPools(pool);
                return tableBuilder.build();
            }
            return null;
        }));
        //WOODLAND MANSION LOOT TABLE
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (LootTables.WOODLAND_MANSION_CHEST == key && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.SAUCE).weight(1))
                        .with(ItemEntry.builder(ModItems.RARE_SAUCE).weight(12))
                        .with(ItemEntry.builder(ModItems.EPIC_SAUCE).weight(7))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.8f))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(pool.build());
            }
        });
        //SNIFFER DIGGING LOOT TABLE
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (LootTables.SNIFFER_DIGGING_GAMEPLAY == key && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.SAUCE).weight(75))
                        .with(ItemEntry.builder(ModItems.RARE_SAUCE).weight(24))
                        .with(ItemEntry.builder(ModItems.EPIC_SAUCE).weight(1))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(pool.build());
            }
        });
        //SNIFFER DIGGING LOOT TABLE
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (LootTables.CAT_MORNING_GIFT_GAMEPLAY == key && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.SAUCE).weight(75))
                        .with(ItemEntry.builder(ModItems.RARE_SAUCE).weight(24))
                        .with(ItemEntry.builder(ModItems.EPIC_SAUCE).weight(1))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(pool.build());
            }
        });
        //FISHING LOOT TABLE
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (LootTables.FISHING_TREASURE_GAMEPLAY == key && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.SAUCE).weight(6))
                        .with(ItemEntry.builder(ModItems.RARE_SAUCE).weight(3))
                        .with(ItemEntry.builder(ModItems.EPIC_SAUCE).weight(1))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(pool.build());
            }
        });
    }
}
