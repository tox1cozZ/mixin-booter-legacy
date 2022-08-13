package org.spongepowered.asm.bridge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.libraries.org.objectweb.asm.commons.Remapper;
import org.spongepowered.asm.mixin.extensibility.IRemapper;
import org.spongepowered.asm.util.ObfuscationUtil;

public abstract class RemapperAdapter implements IRemapper, ObfuscationUtil.IClassRemapper {

    protected final Logger logger = LogManager.getLogger("mixin");
    protected final Remapper remapper;

    public RemapperAdapter(Remapper delegate) {
        remapper = delegate;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public String mapMethodName(String owner, String name, String desc) {
        logger.debug("{} is remapping method {}{} for {}", this, name, desc, owner);
        String newName = remapper.mapMethodName(owner, name, desc);
        if (!newName.equals(name)) {
            return newName;
        } else {
            String obfOwner = unmap(owner);
            String obfDesc = unmapDesc(desc);
            logger.debug("{} is remapping obfuscated method {}{} for {}", this, name, obfDesc, obfOwner);
            return remapper.mapMethodName(obfOwner, name, obfDesc);
        }
    }

    public String mapFieldName(String owner, String name, String desc) {
        logger.debug("{} is remapping field {}{} for {}", this, name, desc, owner);
        String newName = remapper.mapFieldName(owner, name, desc);
        if (!newName.equals(name)) {
            return newName;
        } else {
            String obfOwner = unmap(owner);
            String obfDesc = unmapDesc(desc);
            logger.debug("{} is remapping obfuscated field {}{} for {}", this, name, obfDesc, obfOwner);
            return remapper.mapFieldName(obfOwner, name, obfDesc);
        }
    }

    public String map(String typeName) {
        logger.debug("{} is remapping class {}", this, typeName);
        return remapper.map(typeName);
    }

    public String unmap(String typeName) {
        return typeName;
    }

    public String mapDesc(String desc) {
        return remapper.mapDesc(desc);
    }

    public String unmapDesc(String desc) {
        return ObfuscationUtil.unmapDescriptor(desc, this);
    }
}