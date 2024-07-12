package com.sauceless.item.advancement;

import com.sauceless.PlayerData;
import com.sauceless.StateSaverAndLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class advancementHandler {
    public int progress;

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
        PlayerData playerState = StateSaverAndLoader.getPlayerState(player);
        playerState.progress = progress;
        if (progress>9){
            if (progress>20) {
                if(progress>31) {
                    playerState.tier=3;
                } else {
                    playerState.tier=2;
                }
            } else {
                playerState.tier=1;
            }
        } else {
            playerState.tier = 0;
        }
    }

    public boolean advancementCheck(ServerPlayerEntity player, AdvancementEntry advancement) {
        return player.getAdvancementTracker().getProgress(advancement).isDone();
    }
}
