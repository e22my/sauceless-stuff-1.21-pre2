package com.sauceless.item.lootbox;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Random;

public class lootboxMsg {
    public static String getMessage(LootType type, int index) {
        String message = "";
        if (type == LootType.RARE){
            switch (index){
                case 0 -> {
                    message = ("has found the " + type + " sauce!");
                    return message;
                }
                case 1 -> {
                    message = ("has brought the " + type + " sauce and a wooden plank to Duck Pond!");
                    return message;
                }
                case 2 -> {
                    message = ("is eating some " + type + " sauce tonight!");
                    return message;
                }
                case 3 -> {
                    message = ("isn't going to share the " + type + " sauce...");
                    return message;
                }
                case 4 -> {
                    message = ("has ordered 11 creme brulees with their " + type + " sauce");
                    return message;
                }
            }
        }
        if (type == LootType.EPIC){
            switch (index){
                case 0 -> {
                    message = ("burnt the potatoes but has the " + type + " sauce!");
                    return message;
                }
                case 1 -> {
                    message = ("says the " + type + " sauce tastes like a PENTAKILL!");
                    return message;
                }
                case 2 -> {
                    message = ("is keeping the " + type + " sauce for themself!");
                    return message;
                }
                case 3 -> {
                    message = ("has gotten the " + type + " sauce before Glenroy's beaten Hades!");
                    return message;
                }
                case 4 -> {
                    message = ("thanks Good Ethan for providing them with the " + type + " sauce!");
                    return message;
                }
            }
        }
        return message;
    }

    public static void broadcastMessage(ServerPlayerEntity player, LootType type) {
        Random rand = new Random();
        int index = rand.nextInt(5);
        String message = getMessage(type, index);
        Style style = Style.EMPTY.withColor(Formatting.GOLD);
        player.getServer().getPlayerManager()
                .broadcast(Text.of(player.getName() + " " + message).copy().setStyle(style), false);
    }
}
