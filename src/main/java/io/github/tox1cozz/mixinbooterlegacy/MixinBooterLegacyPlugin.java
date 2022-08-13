package io.github.tox1cozz.mixinbooterlegacy;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.Name;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import io.github.tox1cozz.mixinextras.MixinExtrasBootstrap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Name("MixinBooterLegacy")
@MCVersion("1.7.10")
@SortingIndex(Integer.MIN_VALUE + 1)
public final class MixinBooterLegacyPlugin implements IFMLLoadingPlugin {

    public static final Logger LOGGER = LogManager.getLogger("MixinBooter");

    static {
        LOGGER.info("MixinBootstrap Initializing...");
        MixinBootstrap.init();
        // Initialize MixinExtras
        MixinExtrasBootstrap.init();
        Mixins.addConfiguration("mixin.mixinbooterlegacy.json");
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return "io.github.tox1cozz.mixinbooterlegacy.MixinBooterLegacyPlugin$Container";
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        Object coremodList = data.get("coremodList");
        if (coremodList instanceof List) {
            // noinspection rawtypes
            for (Object coremod : (List)coremodList) {
                try {
                    Field field = coremod.getClass().getField("coreModInstance");
                    field.setAccessible(true);
                    Object theMod = field.get(coremod);
                    if (theMod instanceof IEarlyMixinLoader) {
                        IEarlyMixinLoader loader = (IEarlyMixinLoader)theMod;
                        for (String mixinConfig : loader.getMixinConfigs()) {
                            if (loader.shouldMixinConfigQueue(mixinConfig)) {
                                LOGGER.info("Adding {} mixin configuration.", mixinConfig);
                                Mixins.addConfiguration(mixinConfig);
                                loader.onMixinConfigQueued(mixinConfig);
                            }
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("Unexpected error", e);
                }
            }
        }
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    public static class Container extends DummyModContainer {

        public Container() {
            super(new ModMetadata());
            ModMetadata meta = getMetadata();
            meta.modId = "mixinbooterlegacy";
            meta.name = "MixinBooterLegacy";
            meta.version = "1.0.3";
            meta.description = "A Mixin library and loader.";
            meta.logoFile = "/icon.png";
            meta.authorList.addAll(Lists.newArrayList("Rongmario", "tox1cozZ"));
            meta.credits = "Thanks Rongmario for a MixinBooter on Minecraft 1.12.2.";
            meta.url = "https://github.com/tox1cozZ/mixin-booter-legacy";
        }

        @Override
        @SuppressWarnings("UnstableApiUsage")
        public boolean registerBus(EventBus bus, LoadController controller) {
            bus.register(this);
            return true;
        }
    }
}