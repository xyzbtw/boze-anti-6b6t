package com.example.addon.mixins;

import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.option.ServerList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(ServerList.class)
public abstract class Mixinmonkey {

    @Final
    @Shadow
    private List<ServerInfo> servers;


    @Shadow public abstract void remove(ServerInfo serverInfo);

    @Shadow public abstract void saveFile();

    @Inject(method={"loadFile"}, at={@At(value="INVOKE", target="Ljava/util/List;clear()V", ordinal=0, shift=At.Shift.AFTER)})
    public void onInit(CallbackInfo ci){
        ServerInfo sixbee = new ServerInfo("6b6t", "6b6t.org", false);
        try{
            this.servers.removeIf(a-> Objects.equals(a.address, sixbee.address));
        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }
    @Inject(method={"saveFile"}, at={@At(value="HEAD")}, cancellable=true)
    public void onSaveFile(CallbackInfo ci){
        ci.cancel();
        for(ServerInfo server : servers){
            if(server.address.equals("6b6t.org")) remove(server);
        }
        saveFile();
    }

}

