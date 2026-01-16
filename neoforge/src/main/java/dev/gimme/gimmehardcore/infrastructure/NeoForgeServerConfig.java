package dev.gimme.gimmehardcore.infrastructure;

import dev.gimme.gimmehardcore.domain.config.ServerConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeServerConfig extends ServerConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec SPEC = BUILDER.build();
}
