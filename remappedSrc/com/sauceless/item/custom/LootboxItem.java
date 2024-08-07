package com.sauceless.item.lootbox;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
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
        ItemStack diamond = new ItemStack(Items.DIAMOND);
        boolean isAdded = player.giveItemStack(diamond);
        if (!isAdded){
            player.dropItem(diamond, false);
        }
        //TO-DO: Figure out how to generate loot from custom loot table
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.sauceless_stuff.lootbox.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
