package wozniaktv.dragoniclimbo

import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import wozniaktv.dragoniclimbo.commands.AFK
import wozniaktv.dragoniclimbo.commands.ConfigReload
import wozniaktv.dragoniclimbo.events.AFKChecking
import wozniaktv.dragoniclimbo.events.tasks.MinutesCounter
import wozniaktv.dragoniclimbo.proxy.ProxyAPI

class Main : JavaPlugin() {

    var afkMinutes : HashMap<Player,Int> = HashMap()
    var proxyAPI: ProxyAPI ?= null
    var canGoBack : HashMap<Player,Boolean> = HashMap()

    override fun onEnable() {
        proxyAPI = ProxyAPI()
        configSetup()
        proxyAPI!!.setupProxyConnection()
        setupCommands()
        setupListeners()
        startTasks()
    }

    override fun onDisable() {
        proxyAPI!!.closeProxyConnection()
    }

    private fun configSetup(){
        saveDefaultConfig()
        reloadConfig()
    }

    private fun setupCommands(){
        getCommand("limboreload")!!.setExecutor(ConfigReload)
        getCommand("afk")!!.setExecutor(AFK)
    }

    private fun setupListeners(){
        server.pluginManager.registerEvents(AFKChecking(), this)
    }

    private fun startTasks(){
        if(!config.getBoolean("isLimboServer")){
            MinutesCounter().runTaskTimerAsynchronously(this,1200,1200)
        }
    }


}