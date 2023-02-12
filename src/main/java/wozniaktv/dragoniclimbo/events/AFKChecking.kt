package wozniaktv.dragoniclimbo.events

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin
import wozniaktv.dragoniclimbo.Main
import wozniaktv.dragoniclimbo.proxy.ProxyAPI

class AFKChecking : Listener {

    private var plugin : Main?= null

    init{
        plugin = JavaPlugin.getPlugin(Main::class.java)
    }

    @EventHandler
    fun moveEvent(event: PlayerMoveEvent){
        if(plugin!!.afkMinutes.contains(event.player)){
            plugin!!.afkMinutes.remove(event.player)
        }
        if(plugin!!.config.getBoolean("isLimboServer")){
            event.isCancelled = true
            plugin!!.proxyAPI!!.sendPlayer(event.player,"lobby")
        }
    }

    @EventHandler
    fun chatEvent(event: AsyncPlayerChatEvent){
        if(plugin!!.afkMinutes.contains(event.player)){
            plugin!!.afkMinutes.remove(event.player)
        }
        if(plugin!!.config.getBoolean("isLimboServer")){
            event.isCancelled = true
            plugin!!.proxyAPI!!.sendPlayer(event.player,"lobby")
        }
    }

    @EventHandler
    fun commandEvent(event: PlayerCommandPreprocessEvent){
        if(plugin!!.afkMinutes.contains(event.player)){
            plugin!!.afkMinutes.remove(event.player)
        }
        if(plugin!!.config.getBoolean("isLimboServer")){
            event.isCancelled = true
            plugin!!.proxyAPI!!.sendPlayer(event.player,"lobby")
        }
    }

    @EventHandler
    fun inventoryClickEvent(event: InventoryClickEvent){
        if(plugin!!.afkMinutes.contains(event.whoClicked)){
            plugin!!.afkMinutes.remove(event.whoClicked)
        }
        if(plugin!!.config.getBoolean("isLimboServer")) {
            event.isCancelled = true
            plugin!!.proxyAPI!!.sendPlayer(event.whoClicked as Player,"lobby")
        }
    }

}