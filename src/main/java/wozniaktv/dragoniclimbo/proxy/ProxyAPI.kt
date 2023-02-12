package wozniaktv.dragoniclimbo.proxy

import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import wozniaktv.dragoniclimbo.Main
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class ProxyAPI {

    private var plugin : Main = JavaPlugin.getPlugin(Main::class.java)

    fun setupProxyConnection(){
        plugin.logger.info("Starting Proxy connection...")
        try{
            plugin.server.messenger.registerOutgoingPluginChannel(plugin, "BungeeCord")
            plugin.server.logger.info("Proxy connection registered successfully.")
        }catch(ex: Exception){
            plugin.logger.severe("Error during starting Proxy connection: "+ex.message)
        }
    }

    fun closeProxyConnection(){
        plugin.logger.info("Closing Proxy connection...")
        try {
            plugin.server.messenger.unregisterOutgoingPluginChannel(plugin, "BungeeCord")
            plugin.server.logger.info("Proxy connection closed successfully.")
        }catch(ex: Exception){
            plugin.server.logger.severe("Error during closing Proxy connection: "+ex.message)
        }
    }

    fun sendPlayer(player: Player, server: String){
        val bytes = ByteArrayOutputStream()
        val out = DataOutputStream(bytes)
        out.writeUTF("ConnectOther")
        out.writeUTF(player.name)
        out.writeUTF(server)
        player.server.sendPluginMessage(plugin,"BungeeCord",bytes.toByteArray())
    }

}