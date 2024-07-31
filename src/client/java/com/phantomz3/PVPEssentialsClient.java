package com.phantomz3;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class PVPEssentialsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerEvents();
    }

    private void registerEvents() {
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            PlayerEntity player = client.player;

            if (player == null) return;

            int x = ModConfig.START_X;
            int y = ModConfig.START_Y;

            EquipmentSlot Head = EquipmentSlot.HEAD;
            EquipmentSlot Chest = EquipmentSlot.CHEST;
            EquipmentSlot Legs = EquipmentSlot.LEGS;
            EquipmentSlot Feet = EquipmentSlot.FEET;

            // Armor display
            if (ModConfig.SHOW_ARMOR) {
                displayArmor(drawContext, client, player, Head, "helmet", x, y);
                y += 16;
                displayArmor(drawContext, client, player, Chest, "chestplate", x, y);
                y += 16;
                displayArmor(drawContext, client, player, Legs, "leggings", x, y);
                y += 16;
                displayArmor(drawContext, client, player, Feet, "boots", x, y);
                y += 16;
            }

            // Golden apples display
            if (ModConfig.SHOW_GOLDEN_APPLES) {
                int goldenAppleCount = getItemCount(player, "golden_apple");
                displayItemWithCount(drawContext, client, "golden_apple", goldenAppleCount, x, y);
                y += 16;
            }

            // Ender pearls display
            if (ModConfig.SHOW_ENDER_PEARLS) {
                int enderPearlCount = getItemCount(player, "ender_pearl");
                displayItemWithCount(drawContext, client, "ender_pearl", enderPearlCount, x, y);
                y += 16;
            }

            // Arrows display
            if (ModConfig.SHOW_ARROWS) {
                int arrowCount = getItemCount(player, "arrow");
                displayItemWithCount(drawContext, client, "arrow", arrowCount, x, y);
            }
        });
    }

    private void displayArmor(DrawContext drawContext, MinecraftClient client, PlayerEntity player, EquipmentSlot slot, String type, int x, int y) {
        String itemType = player.getEquippedStack(slot).getItem().getTranslationKey();
        itemType = itemType.replace("item.minecraft.", "");
        int durability = player.getEquippedStack(slot).getMaxDamage() - player.getEquippedStack(slot).getDamage();
		if (ModConfig.DISPLAY_DURABILITY_AS_PERCENTAGE) {
			durability = (int) (((float) durability / player.getEquippedStack(slot).getMaxDamage()) * 100);
		}

        if (itemType.contains(type)) {
            drawContext.drawTexture(Identifier.of("minecraft", "textures/item/" + itemType + ".png"), x, y, 0, 0, 16, 16, 16, 16);
			if (ModConfig.DISPLAY_DURABILITY_AS_PERCENTAGE) {
				drawContext.drawText(client.textRenderer, durability + "%", x + 18, y + 4, 0xFFFFFF, true);
			} else {
            	drawContext.drawText(client.textRenderer, "" + durability, x + 18, y + 4, 0xFFFFFF, true);
			}
        } else {
            drawContext.drawTexture(Identifier.of("minecraft", "textures/item/empty_armor_slot_" + type + ".png"), x, y, 0, 0, 16, 16, 16, 16);
        }
    }

    private void displayItem(DrawContext drawContext, MinecraftClient client, String type, String itemType, int x, int y) {
        itemType = itemType.replace("item.minecraft.", "");

        if (!itemType.equals("air")) {
            drawContext.drawTexture(Identifier.of("minecraft", "textures/item/" + itemType + ".png"), x, y, 0, 0, 16, 16, 16, 16);
        } else {
            drawContext.drawTexture(Identifier.of("minecraft", "textures/item/empty_slot_smithing_template_netherite_upgrade.png"), x, y, 0, 0, 16, 16, 16, 16);
        }
    }

    private void displayItemWithCount(DrawContext drawContext, MinecraftClient client, String itemType, int count, int x, int y) {
        drawContext.drawTexture(Identifier.of("minecraft", "textures/item/" + itemType + ".png"), x, y, 0, 0, 16, 16, 16, 16);
        drawContext.drawText(client.textRenderer, "" + count, x + 18, y + 4, 0xFFFFFF, true);
    }

    private int getItemCount(PlayerEntity player, String itemName) {
        int count = 0;
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().getStack(i).getItem().getTranslationKey().contains(itemName)) {
                count += player.getInventory().getStack(i).getCount();
            }
        }
        return count;
    }
}
