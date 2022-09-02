# mixin-booter-legacy
Allows any mixins that work on mods to work effortlessly.<br>
Unofficial port of MixinBooter for Minecraft 1.7.10.<br>
MixinBooter 1.12.2 - https://github.com/LoliKingdom/MixinBooter

## For Users:
Download mod from [CurseForge](https://www.curseforge.com/minecraft/mc-mods/mixinbooterlegacy) and put jar to your mods directory.<br>
**_Important:_** If you are downloading the mod from the [Github Releases](https://github.com/tox1cozZ/mixin-booter-legacy/releases), rename the jar file by adding the prefix `!` to the beginning of the filename.

## For Developers:

```
apply from: 'https://raw.githubusercontent.com/tox1cozZ/mixin-booter-legacy/master/gradle/configurations/v1.gradle'

mixin {
    // Set custom refmap name. By default using project name
    // mixinRefMapName = 'mixin.custom.refmap.json'
}
```
