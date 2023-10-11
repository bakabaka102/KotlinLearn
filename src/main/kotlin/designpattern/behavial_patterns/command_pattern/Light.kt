package designpattern.behavial_patterns.command_pattern

interface ICommand {
    fun execute()
}

data class Light(var status: Boolean? = null) {

    fun turnOn() {
        status = true
    }

    fun turnOff() {
        status = false
    }
}

class RemoteControl(private var iCommand: ICommand? = null) {

    fun setCommand(iCommand: ICommand) {
        this.iCommand = iCommand
    }

    fun pressButton() {
        iCommand?.execute()
    }
}

class TurnOnCommand(var light: Light) : ICommand {
    override fun execute() {
        light.turnOn()
    }
}

class TurnOffCommand(var light: Light) : ICommand {
    override fun execute() {
        light.turnOff()
    }
}

fun main() {
    val remoteControl = RemoteControl()
    val light = Light()
    remoteControl.setCommand(TurnOnCommand(light))
    remoteControl.pressButton()
    println(light)
    remoteControl.setCommand(TurnOffCommand(light))
    remoteControl.pressButton()
    println(light)
}