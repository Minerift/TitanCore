package org.minerift.titan.modules.gens;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.minerift.titan.modules.Module;

import java.util.*;

public class GeneratorModule extends Module {

    private static final int PAY_FREQUENCY_SECONDS = 15;

    private Map<UUID, List<Generator>> generators;

    @Override
    protected void onEnable() {

        // TODO: load generators
        this.generators = new HashMap<>();

    }

    @Override
    protected void onDisable() {

    }

    public Map<UUID, List<Generator>> getGeneratorMap() {
        return generators;
    }

    public List<Generator> getGenerators(UUID uuid) {
        return generators.get(uuid);
    }

    public int getPayFrequency() {
        return PAY_FREQUENCY_SECONDS;
    }
}
