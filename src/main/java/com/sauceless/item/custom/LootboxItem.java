package com.sauceless.item.custom;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
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
            open(user);
            if (!user.isCreative()){
                itemStack.decrement(1);
            }
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    public void open(PlayerEntity player){
        ObjectArrayList<ItemStack> generatedLoot =
                LootTableHandler.getItemLoot((ServerPlayerEntity) player, type);
        // For Advancement Check, Add function to remove advancement locked items
        for (ItemStack entry : generatedLoot) {
            boolean isAdded = player.giveItemStack(entry);
            if (!isAdded){
                player.dropItem(entry, false);
            }
        }
        player.sendMessage(Text.literal(player.getName() + " opened " + type + " lootbox!"));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.sauceless_stuff.lootbox.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
