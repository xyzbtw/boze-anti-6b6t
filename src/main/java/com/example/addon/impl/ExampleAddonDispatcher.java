package com.example.addon.impl;

import com.mojang.brigadier.CommandDispatcher;
import dev.boze.api.addon.command.AddonDispatcher;
import net.minecraft.command.CommandSource;

public class ExampleAddonDispatcher implements AddonDispatcher {
    private final CommandDispatcher<CommandSource> DISPATCHER = new CommandDispatcher<>();

    @Override
    public CommandDispatcher<CommandSource> getDispatcher() {
        return DISPATCHER;
    }

    @Override
    public String getPrefix() {
        return "example";
    }
}
