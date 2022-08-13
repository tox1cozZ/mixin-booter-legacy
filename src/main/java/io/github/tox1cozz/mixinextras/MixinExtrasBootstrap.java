package io.github.tox1cozz.mixinextras;

import io.github.tox1cozz.mixinextras.injector.ModifyExpressionValueInjectionInfo;
import io.github.tox1cozz.mixinextras.injector.ModifyReceiverInjectionInfo;
import io.github.tox1cozz.mixinextras.injector.ModifyReturnValueInjectionInfo;
import io.github.tox1cozz.mixinextras.injector.WrapWithConditionInjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;

public class MixinExtrasBootstrap {

    public static void init() {
        InjectionInfo.register(ModifyExpressionValueInjectionInfo.class);
        InjectionInfo.register(ModifyReceiverInjectionInfo.class);
        InjectionInfo.register(ModifyReturnValueInjectionInfo.class);
        InjectionInfo.register(WrapWithConditionInjectionInfo.class);
    }
}