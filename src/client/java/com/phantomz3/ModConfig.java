package com.phantomz3;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfig {
    public static int START_X = 0;
    public static int START_Y = 0;    
    public static boolean DISPLAY_DURABILITY_AS_PERCENTAGE = true;
    public static boolean SHOW_ARMOR = true;
    public static boolean SHOW_GOLDEN_APPLES = true;
    public static boolean SHOW_ENDER_PEARLS = true;
    public static boolean SHOW_ARROWS = true;

    public static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Text.of("PVP Essentials Config"));

        ConfigCategory general = builder.getOrCreateCategory(Text.of("General"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startIntField(Text.of("Start X"), START_X)
            .setDefaultValue(10)
            .setTooltip(Text.of("Set the X position of the HUD"))
            .setSaveConsumer(newValue -> START_X = newValue)
            .build());

        general.addEntry(entryBuilder.startIntField(Text.of("Start Y"), START_Y)
            .setDefaultValue(10)
            .setTooltip(Text.of("Set the Y position of the HUD"))
            .setSaveConsumer(newValue -> START_Y = newValue)
            .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("Display Durability as percentage"), DISPLAY_DURABILITY_AS_PERCENTAGE)
            .setDefaultValue(true)
            .setTooltip(Text.of("Display the durability of items as a percentage"))
            .setSaveConsumer(newValue -> DISPLAY_DURABILITY_AS_PERCENTAGE = newValue)
            .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("Show Armor"), SHOW_ARMOR)
            .setDefaultValue(true)
            .setTooltip(Text.of("Show the player's armor"))
            .setSaveConsumer(newValue -> SHOW_ARMOR = newValue)
            .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("Show golden apples"), SHOW_GOLDEN_APPLES)
            .setDefaultValue(true)
            .setTooltip(Text.of("Show the number of golden apples in the player's inventory"))
            .setSaveConsumer(newValue -> SHOW_GOLDEN_APPLES = newValue)
            .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("Show ender pearls"), SHOW_ENDER_PEARLS)
            .setDefaultValue(true)
            .setTooltip(Text.of("Show the number of ender pearls in the player's inventory"))
            .setSaveConsumer(newValue -> SHOW_ENDER_PEARLS = newValue)
            .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("Show arrows"), SHOW_ARROWS)
            .setDefaultValue(true)
            .setTooltip(Text.of("Show the number of arrows in the player's inventory"))
            .setSaveConsumer(newValue -> SHOW_ARROWS = newValue)
            .build());

        return builder.build();
    }
}
