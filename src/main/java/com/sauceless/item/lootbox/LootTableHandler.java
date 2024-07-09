package com.sauceless.item.lootbox;

import com.sauceless.SaucelessStuff;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class LootTableHandler {
    // Loot Table Handler(LTH) is grabs correct loot table based on type
    // Then LTH generates an ItemStack List of Randomized Loot from the loot bag
    // Then LTH returns ItemStack List to be given to player

    //SWITCH LOOT TABLE BASED ON LOOT TYPE
    public static RegistryKey<LootTable> getLootType(LootType type){
        return switch (type) {
            case LootType.COMMON -> RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/sauce"));
            case LootType.RARE -> RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/rare_sauce"));
            case LootType.EPIC -> RegistryKey.of(RegistryKeys.LOOT_TABLE, SaucelessStuff.id("lootbox/epic_sauce"));
        };
    }

    //GENERATE ITEM LOOT FROM LOOT TABLE
    public static ObjectArrayList<ItemStack> getItemLoot(ServerPlayerEntity user, LootType type){
        ServerWorld world = user.getServerWorld();
        LootTable loot = world.getServer().getReloadableRegistries().getLootTable(getLootType(type));
        LootContextParameterSet lootContextParameterSet = new LootContextParameterSet.Builder(world)
                .add(LootContextParameters.THIS_ENTITY, user)
                .add(LootContextParameters.ORIGIN, user.getPos())
                .build(LootContextTypes.CHEST);
        return loot.generateLoot(lootContextParameterSet);
    }

}
