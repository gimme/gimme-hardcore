package dev.gimme.gimmehardcore;

import dev.gimme.gimmehardcore.config.NeoForgeFragilityConfig;
import dev.gimme.gimmehardcore.config.NeoForgeHardnessConfig;
import dev.gimme.gimmehardcore.domain.config.FragilityConfig;
import dev.gimme.gimmehardcore.domain.config.HardnessConfig;
import dev.gimme.gimmehardcore.domain.util.Constants;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Constants.MOD_ID)
public class NeoForgeMod {

    public NeoForgeMod(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, NeoForgeFragilityConfig.SPEC, Constants.MOD_ID + "-fragility.toml");
        modContainer.registerConfig(ModConfig.Type.COMMON, NeoForgeHardnessConfig.SPEC, Constants.MOD_ID + "-hardness.toml");
        FragilityConfig.INSTANCE = new NeoForgeFragilityConfig();
        HardnessConfig.INSTANCE = new NeoForgeHardnessConfig();
    }
}
