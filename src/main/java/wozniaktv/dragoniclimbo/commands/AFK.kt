package wozniaktv.dragoniclimbo.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import wozniaktv.dragoniclimbo.Main
import wozniaktv.dragoniclimbo.format.Format
import wozniaktv.dragoniclimbo.proxy.ProxyAPI

object AFK : CommandExecutor {

    private var plugin : Main ?= null

    init{
        plugin = JavaPlugin.getPlugin(Main::class.java)
    }


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if(sender !is Player){
            sender.sendMessage("Console can't use this command.")
            return true
        }

        sender.sendMessage(Format.color("&dDragonicLimbo &7» &fSei andato nel limbo."))
        plugin!!.proxyAPI!!.sendPlayer(sender,"limbo")
        plugin!!.afkMinutes.remove(sender)

        return true
    }

}