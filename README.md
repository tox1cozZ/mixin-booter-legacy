# mixin-booter-legacy
Allows any mixins that work on mods to work effortlessly. With a single class and an annotation.<br>
Unofficial port of MixinBooter for Minecraft 1.7.10.<br>
MixinBooter 1.12.2 - https://github.com/LoliKingdom/MixinBooter

## For Developers:

```
ext {
    mixinVersion = '0.8.2'
    mixinConfigName = project.name // Set custom config name
    mixinSrg = new File(buildDir, "mixins/mixin.${mixinConfigName}.srg")
    mixinRefMapName = "mixin.${mixinConfigName}.refmap.json"
    mixinRefMap = new File(buildDir, "mixins/" + mixinRefMapName)
}

repositories {
    maven {
        name 'Spongepowered'
        url 'https://repo.spongepowered.org/maven'
    }
    maven {
        name 'Jitpack'
        url 'https://jitpack.io'
    }
}

dependencies {
    implementation 'com.github.tox1cozZ:mixin-booter-legacy:5.0.0'
    annotationProcessor "org.spongepowered:mixin:$mixinVersion:processor"
}

reobf {
    if (mixinSrg.exists()) {
        addExtraSrgFile mixinSrg
    }
}

task copySrgs(type: Copy, dependsOn: 'genSrgs') {
    from plugins.getPlugin("forge").delayedFile('{SRG_DIR}')
    include '**/*.srg'
    into 'build/srgs'
}

compileJava {
    dependsOn copySrgs
    options.compilerArgs += [
            '-Xlint:-processing',
            "-AoutSrgFile=${mixinSrg.canonicalPath}",
            "-AoutRefMapFile=${mixinRefMap.canonicalPath}",
            "-AreobfSrgFile=${file('build/srgs/mcp-srg.srg').canonicalPath}"
    ]
}

jar {
    from mixinRefMap
}
```
