package wozniaktv.dragoniclimbo.events.tasks

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import wozniaktv.dragoniclimbo.Main
import wozniaktv.dragoniclimbo.format.Format
import wozniaktv.dragoniclimbo.proxy.ProxyAPI

class MinutesCounter : BukkitRunnable() {

    private var plugin : Main = JavaPlugin.getPlugin(Main::class.java)

    override fun run() {

        if(plugin.config.getBoolean("isLimboServer")){
            this.cancel()
        }

        for(p in plugin.server.onlinePlayers){

            if(plugin.afkMinutes.contains(p)){
                plugin.afkMinutes[p] = plugin.afkMinutes[p]!!.plus(1)
            }else{
                plugin.afkMinutes[p] = 1
            }

            if(plugin.afkMinutes[p] == plugin.config.getInt("neededAFKMinutes")){
                p.sendMessage(Format.color("&dDragonicLimbo &7» &fSei stato inviato al limbo."))
                plugin.proxyAPI!!.sendPlayer(p,"limbo")
                plugin.afkMinutes.remove(p)
            }

        }

    }

}