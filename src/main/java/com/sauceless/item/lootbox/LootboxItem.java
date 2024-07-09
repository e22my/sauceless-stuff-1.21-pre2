package com.sauceless.item.lootbox;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.*;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class LootboxItem extends Item {
    public LootType type;

    public LootboxItem(Settings settings, LootType type) {
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
                user.getItemCooldownManager().set(this, 40);
                user.playSoundToPlayer(SoundEvent.of(Identifier.of("block.note_block.harp")), SoundCategory.NEUTRAL, 1f, 1.414214f);
            }
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    public void open(PlayerEntity player){
        ObjectArrayList<ItemStack> generatedLoot =
                LootTableHandler.getItemLoot((ServerPlayerEntity) player, type);

        for (ItemStack entry : generatedLoot) {
            boolean isAdded = player.giveItemStack(entry);
            if (!isAdded){
                player.dropItem(entry, false);
            }
        }
        if (type != LootType.COMMON){
            lootboxMsg.broadcastMessage((ServerPlayerEntity) player, type);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.sauceless_stuff.lootbox.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
