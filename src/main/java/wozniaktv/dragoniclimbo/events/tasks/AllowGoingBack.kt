package wozniaktv.dragoniclimbo.events.tasks

import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import wozniaktv.dragoniclimbo.Main

class AllowGoingBack(player: Player) : BukkitRunnable() {

    private var plugin : Main = JavaPlugin.getPlugin(Main::class.java)
    private var p : Player = player
    override fun run() {

        plugin.canGoBack[p] = true

    }

}