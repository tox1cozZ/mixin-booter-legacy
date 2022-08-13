package io.github.tox1cozz.mixinextras.injector;

import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo.AnnotationType;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo.HandlerPrefix;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.libraries.org.objectweb.asm.tree.AnnotationNode;
import org.spongepowered.libraries.org.objectweb.asm.tree.MethodNode;

@AnnotationType(ModifyExpressionValue.class)
@HandlerPrefix("modifyExpressionValue")
public class ModifyExpressionValueInjectionInfo extends InjectionInfo {

    public ModifyExpressionValueInjectionInfo(MixinTargetContext mixin, MethodNode method, AnnotationNode annotation) {
        super(mixin, method, annotation);
    }

    @Override
    protected Injector parseInjector(AnnotationNode injectAnnotation) {
        return new ModifyExpressionValueInjector(this);
    }
}