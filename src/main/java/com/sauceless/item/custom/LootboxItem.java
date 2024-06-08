package com.sauceless.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.message.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class LootboxItem extends Item {
    public LootType type;

    public LootboxItem(Item.Settings settings, LootType type) {
        super(settings);
        this.type = type;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient() && user instanceof ServerPlayerEntity){
            open(user, world);
            if (!user.isCreative()){
                itemStack.decrement(1);
            }
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    public void open(PlayerEntity player, World world){
        //CHANGE: Following line is temporary
        //        Needs to call Loot Table Handler,
        //        pass args+type and return List<ItemStack>
        ItemStack loot = new ItemStack(Items.DIAMOND);

        //Here -> Iterate through List and give Items to player

        //Do not touch for now, gives player item and drops item
        //if inventory full
        boolean isAdded = player.giveItemStack(loot);
        if (!isAdded){
            player.dropItem(loot, false);
        }
        player.sendMessage(Text.of(player.getName() + " opened " + type + " lootbox!"));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.sauceless_stuff.lootbox.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
