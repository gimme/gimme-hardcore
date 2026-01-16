package dev.gimme.gimmehardcore;

import dev.gimme.gimmehardcore.domain.config.Config;
import dev.gimme.gimmehardcore.domain.util.Constants;
import dev.gimme.gimmehardcore.infrastructure.NeoForgeConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Constants.MOD_ID)
public class NeoForgeMod {

    public NeoForgeMod(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, NeoForgeConfig.SPEC);
        Config.INSTANCE = new NeoForgeConfig();
    }
}
