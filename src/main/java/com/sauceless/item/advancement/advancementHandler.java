package com.sauceless.item.advancement;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class advancementHandler {
    public int progress;

    // Write file for advancementProgress to iterate through
    public void advancementProgress(ServerPlayerEntity player){
        progress = 0;
        ServerWorld world = player.getServerWorld();
        advProgression list = new advProgression();
        for(String advancementName : list.boost) {
            AdvancementEntry adv = world.getServer().getAdvancementLoader().getManager().get(
                    Identifier.of(advancementName)).getAdvancementEntry();
            if (advancementCheck(player, adv)){
                progress+=1;
            }
        }
    }
    
    //Hard Advancements create better pool of items
    //Grindy Advancements increase the chances of lootboxes
    //First thing to do: Check if Player has an Advancement, Persistent State Save
    public boolean advancementCheck(ServerPlayerEntity player, AdvancementEntry advancement) {
        return player.getAdvancementTracker().getProgress(advancement).isDone();
    }
}
