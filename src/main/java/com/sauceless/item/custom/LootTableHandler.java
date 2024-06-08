package com.sauceless.item.custom;

public class LootTableHandler {
    // Loot Table Handler(LTH) is supposed to grab correct loot table based on type
    // Then LTH generates an ItemStack List of Randomized Loot from the loot bag
    // Then LTH returns ItemStack List to be given to player

    // To be implemented(?), LTH uses Advancement Handler(AH)
    // to exclude certain items until a specific advancement
    // or advancement score is achieved.
    // Eg. Can't receive Elytra until [The End?]
    // Possible implement - generates loot but excludes items
    // If empty, generates and excludes until not empty
    // Separate method to go through what items are excluded
    // based on incomplete advancements


}
