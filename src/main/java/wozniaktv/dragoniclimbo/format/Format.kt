package wozniaktv.dragoniclimbo.format

import org.bukkit.ChatColor

object Format {

    fun color(string: String) : String{
        return ChatColor.translateAlternateColorCodes('&',string)
    }

}