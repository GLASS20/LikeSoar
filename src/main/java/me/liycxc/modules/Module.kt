package me.liycxc.modules

import me.liycxc.NekoCat
import me.liycxc.utils.TimerUtils
import me.liycxc.utils.animation.simple.SimpleAnimation
import net.minecraft.client.Minecraft

open class Module {
    @JvmField
    var moduleName: String
    var description: String
    var moduleCategory: ModuleCategory
    var introAnimation = SimpleAnimation(0.0f)
    var fontAnimation = arrayOf(
        SimpleAnimation(0.0f), SimpleAnimation(0.0f), SimpleAnimation(0.0f)
    )

    @JvmField
    var mc = Minecraft.getMinecraft()
    @JvmField
    var buttonAnimation = SimpleAnimation(0.0f)
    @JvmField
    var buttonOpacityAnimation = SimpleAnimation(0.0f)
    @JvmField
    var selectTimer = TimerUtils()
    var selectAnimation = SimpleAnimation(0.0f)
    var editOpacityAnimation = SimpleAnimation(0.0f)

    var toggled = false

    constructor(moduleName: String, description: String, moduleCategory: ModuleCategory) {
        this.moduleName = moduleName
        this.description = description
        this.moduleCategory = moduleCategory
        toggled = false
    }

    constructor(moduleName: String, description: String, moduleCategory: ModuleCategory, toggled: Boolean) {
        this.moduleName = moduleName
        this.description = description
        this.moduleCategory = moduleCategory
        this.toggled = toggled
    }

    fun onInitialize() {}
    fun onWorld() {}
    open fun onEnable() {
        NekoCat.instance.eventManager.register(this)
    }

    fun onDisable() {
        NekoCat.instance.eventManager.unregister(this)
    }

    fun toggle() {
        if (toggled) {
            toggled = false
            onDisable()
        } else {
            toggled = true
            onEnable()
        }
    }

    open val values: List<Value<*>>
        get() = javaClass.declaredFields.map { valueField ->
            valueField.isAccessible = true
            valueField[this]
        }.filterIsInstance<Value<*>>()
}