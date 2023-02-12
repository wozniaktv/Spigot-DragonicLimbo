package wozniaktv.dragoniclimbo.events

import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin
import wozniaktv.dragoniclimbo.Main
import wozniaktv.dragoniclimbo.events.tasks.AllowGoingBack
import wozniaktv.dragoniclimbo.format.Format
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
            if(!plugin!!.canGoBack.contains(event.player)) return
            if(plugin!!.canGoBack[event.player]!!) {
                plugin!!.proxyAPI!!.sendPlayer(event.player,"lobby")
                plugin!!.canGoBack.remove(event.player)
            }
        }
    }

    @EventHandler
    fun chatEvent(event: AsyncPlayerChatEvent){
        if(plugin!!.afkMinutes.contains(event.player)){
            plugin!!.afkMinutes.remove(event.player)
        }
        if(plugin!!.config.getBoolean("isLimboServer")){
            event.isCancelled = true
            if(!plugin!!.canGoBack.contains(event.player)) return
            if(plugin!!.canGoBack[event.player]!!){
                plugin!!.proxyAPI!!.sendPlayer(event.player,"lobby")
                plugin!!.canGoBack.remove(event.player)
            }
        }
    }

    @EventHandler
    fun commandEvent(event: PlayerCommandPreprocessEvent){
        if(plugin!!.afkMinutes.contains(event.player)){
            plugin!!.afkMinutes.remove(event.player)
        }
        if(plugin!!.config.getBoolean("isLimboServer")){
            event.isCancelled = true
            if(!plugin!!.canGoBack.contains(event.player)) return
            if(plugin!!.canGoBack[event.player]!!){
                plugin!!.proxyAPI!!.sendPlayer(event.player,"lobby")
                plugin!!.canGoBack.remove(event.player)
            }
        }
    }

    @EventHandler
    fun inventoryClickEvent(event: InventoryClickEvent){
        if(plugin!!.afkMinutes.contains(event.whoClicked)){
            plugin!!.afkMinutes.remove(event.whoClicked)
        }
        if(plugin!!.config.getBoolean("isLimboServer")) {
            event.isCancelled = true
            if(!plugin!!.canGoBack.contains(event.whoClicked)) return
            if(plugin!!.canGoBack[event.whoClicked]!!){
                plugin!!.proxyAPI!!.sendPlayer(event.whoClicked as Player,"lobby")
                plugin!!.canGoBack.remove(event.whoClicked)
            }
        }
    }

    @EventHandler
    fun playerJoin(event: PlayerJoinEvent){
        if(plugin!!.config.getBoolean("isLimboServer")){
            event.player.isInvisible = true
            event.player.isSilent = true
            event.player.isCollidable = false
            event.player.sendTitle(Format.color("&d&lAFK"),Format.color("&fSei stato portato nel server &d&lLIMBO"),0,100,0)
            event.player.playSound(event.player, Sound.ENTITY_VILLAGER_CELEBRATE,100f,0f)
            AllowGoingBack(event.player).runTaskLater(plugin!!,100)
        }
    }
    @EventHandler
    fun playerQuit(event: PlayerJoinEvent){
        if(plugin!!.config.getBoolean("isLimboServer")){
            plugin!!.canGoBack.remove(event.player)
        }
    }

}