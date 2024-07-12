package com.sauceless.item.lootbox;

import com.sauceless.PlayerData;
import com.sauceless.SaucelessStuff;
import com.sauceless.StateSaverAndLoader;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import org.apache.logging.log4j.core.jmx.Server;

public class LootTableHandler {
    // Loot Table Handler(LTH) is grabs correct loot table based on type
    // Then LTH generates an ItemStack List of Randomized Loot from the loot bag
    // Then LTH returns ItemStack List to be given to player

    //SWITCH LOOT TABLE BASED ON LOOT TYPE
    public static RegistryKey<LootTable> getLootType(ServerPlayerEntity player, LootType type){
        PlayerData playerState = StateSaverAndLoader.getPlayerState(player);
        player.sendMessage(Text.of("[LOOTBOX] Sauceless Progress: " + playerState.progress + "/39"));
        player.sendMessage(Text.of("[LOOTBOX] Advancement Tier: " + playerState.tier));
        //USE PLAYER TIER TO CHANGE LOOT TABLE (12 Loot Tables in total)
        switch (playerState.tier) {
            case 0 :
                switch (type) {
                    case LootType.COMMON -> {
                        return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/sauce"));
                    }
                    case LootType.RARE -> {
                        return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/rare_sauce"));
                    }
                    case LootType.EPIC -> {
                        return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/epic_sauce"));
                    }
                }
            case 1 :
                switch (type) {
                    case LootType.COMMON -> {
                        return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/sauce1"));
                    }
                    case LootType.RARE -> {
                        return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/rare_sauce1"));
                    }
                    case LootType.EPIC -> {
                        return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/epic_sauce1"));
                    }
                }
            case 2 :
                switch (type) {
                    case LootType.COMMON -> {
                        return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/sauce2"));
                    }
                    case LootType.RARE -> {
                        return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/rare_sauce2"));
                    }
                    case LootType.EPIC -> {
                        return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/epic_sauce2"));
                    }
                }
            case 3 :
                switch (type) {
                    case LootType.COMMON -> {
                        return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/sauce3"));
                    }
                    case LootType.RARE -> {
                        return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/rare_sauce3"));
                    }
                    case LootType.EPIC -> {
                        return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/epic_sauce3"));
                    }
                }
            default :
                return RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/sauce"));
        }
    }

    //GENERATE ITEM LOOT FROM LOOT TABLE
    public static ObjectArrayList<ItemStack> getItemLoot(ServerPlayerEntity user, LootType type){
        ServerWorld world = user.getServerWorld();
        LootTable loot = world.getServer().getReloadableRegistries().getLootTable(getLootType(user, type));
        LootContextParameterSet lootContextParameterSet = new LootContextParameterSet.Builder(world)
                .add(LootContextParameters.THIS_ENTITY, user)
                .add(LootContextParameters.ORIGIN, user.getPos())
                .build(LootContextTypes.CHEST);
        return loot.generateLoot(lootContextParameterSet);
    }

}
