package com.sauceless.item.custom;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementManager;
import net.minecraft.advancement.PlacedAdvancement;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class advancementHandler {
    int progress = 0;
    public int advancementProgress(ServerPlayerEntity player){
        ServerWorld world = player.getServerWorld();
        AdvancementEntry adv = world.getServer().getAdvancementLoader().getManager().get(
                Identifier.of("story/mine_stone")).getAdvancementEntry();
        if (advancementCheck(player, adv)){
            progress+=1;
        }
        return progress;
    }
    
    //Hard Advancements create better pool of items
    //Grindy Advancements increase the chances of lootboxes
    //First thing to do: Check if Player has an Advancement, Persistent State Save
    public boolean advancementCheck(ServerPlayerEntity player, AdvancementEntry advancement) {
        return player.getAdvancementTracker().getProgress(advancement).isDone();
    }
}
