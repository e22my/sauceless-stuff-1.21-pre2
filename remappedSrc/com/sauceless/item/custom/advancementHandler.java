package com.sauceless.item.lootbox;

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
    public void advancementCheck(ServerPlayerEntity player, AdvancementEntry advancement) {
        player.getAdvancementTracker().getProgress(advancement).isDone();
    }
}
