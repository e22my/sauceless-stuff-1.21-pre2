package com.sauceless.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.advancement.AdvancementEntry;

public class advancementHandler {
    public int advancementProgress(PlayerEntity player){
        int progress = 0;
        //USE FOR LOOP TO ACCESS JSON FILE LIST
        //OF ADVANCEMENTS WITH LOOK UP TABLE OF VARIABLE
        return progress;
    }
    public boolean advancementCheck(ServerPlayerEntity player, AdvancementEntry advancement) {
        return player.getAdvancementTracker().getProgress(advancement).isDone();
    }
}
