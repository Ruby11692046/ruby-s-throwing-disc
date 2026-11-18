package com.ruby.throwingdisc.registry;

import com.ruby.throwingdisc.ThrowingDiscMod;
import com.ruby.throwingdisc.item.DiscTier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ThrowingDiscMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB =
            CREATIVE_TABS.register("throwing_disc",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.throwing_disc"))
                            .icon(() -> new ItemStack(ModItems.DISCS.get(DiscTier.IRON).get()))
                            .displayItems((params, output) ->
                                    ModItems.DISCS.values().forEach(ro -> output.accept(ro.get())))
                            .build());
}
