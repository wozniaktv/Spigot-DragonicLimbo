package wozniaktv.dragoniclimbo.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import wozniaktv.dragoniclimbo.Main
import wozniaktv.dragoniclimbo.format.Format

object ConfigReload : CommandExecutor {

    private var plugin : Main ?= null

    init{
        plugin = JavaPlugin.getPlugin(Main::class.java)
    }


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {


        if(sender.hasPermission("dragonlimbo.reload")){

            plugin!!.reloadConfig()
            sender.sendMessage(Format.color("&2Il config e' stato ricaricato correttamente."))

        }

        return true
    }

}