package me.declipsonator.frostboater;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FrostBoater implements ModInitializer {
    public static final Logger LOG = LogManager.getLogger("FrostBoater");
    @Override
    public void onInitialize() {
        LOG.info("FrostBoater has been initialized.");
    }
}
