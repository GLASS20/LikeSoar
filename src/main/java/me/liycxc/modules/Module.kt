package me.liycxc.modules

import me.liycxc.NekoCat
import me.liycxc.utils.ClientUtils
import me.liycxc.utils.TimerUtils
import me.liycxc.utils.animation.simple.SimpleAnimation
import net.minecraft.client.Minecraft

open class Module {
    @JvmField
    var moduleName: String
    var description: String
    var moduleCategory: ModuleCategory
    var keybind: Int = 0
    var introAnimation = SimpleAnimation(0.0f)

    @JvmField
    var mc = Minecraft.getMinecraft()
    @JvmField
    var buttonAnimation = SimpleAnimation(0.0f)
    @JvmField
    var buttonOpacityAnimation = SimpleAnimation(0.0f)
    @JvmField
    var selectTimer = TimerUtils()

    var toggled = false

    constructor(moduleName: String, description: String, moduleCategory: ModuleCategory) {
        this.moduleName = moduleName
        this.description = description
        this.moduleCategory = moduleCategory
        toggled = false
    }

    constructor(moduleName: String, description: String, moduleCategory: ModuleCategory,keybind: Int) {
        this.moduleName = moduleName
        this.description = description
        this.moduleCategory = moduleCategory
        toggled = false
        this.keybind = keybind
    }

    constructor(moduleName: String, description: String, moduleCategory: ModuleCategory, toggled: Boolean) {
        this.moduleName = moduleName
        this.description = description
        this.moduleCategory = moduleCategory
        this.toggled = toggled
    }

    constructor(moduleName: String, description: String, moduleCategory: ModuleCategory, keybind: Int, toggled: Boolean) {
        this.moduleName = moduleName
        this.description = description
        this.moduleCategory = moduleCategory
        this.toggled = toggled
        this.keybind = keybind
    }


    open fun onInitialize() {}
    fun onWorld() {}
    open fun onEnable() {
    }

    open fun onDisable() {
    }

    fun toggle() {
        if (toggled) {
            toggled = false
            NekoCat.instance.eventManager.unregister(this)
            onDisable()
        } else {
            toggled = true
            NekoCat.instance.eventManager.register(this)
            onEnable()
        }
        ClientUtils.showNotification("Module","$moduleName was ${if (toggled) "Enabled" else "Disabled"}")
    }

    open val values: List<Value<*>>
        get() = javaClass.declaredFields.map { valueField ->
            valueField.isAccessible = true
            valueField[this]
        }.filterIsInstance<Value<*>>()

    open fun getValueByName(name: String): Value<*>? {
        for (value in values) {
            if (value.name == name) {
                return value
            }
        }
        return null
    }
}