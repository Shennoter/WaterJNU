package pes.shennoter

import com.google.gson.Gson
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin


object Waterjnu : KotlinPlugin(
    JvmPluginDescription(
        id = "pes.shennoter.waterjnu",
        name = "WaterJNU",
        version = "1.0.0",
    ) {
        author("Shennoter")
    }
) {
    override fun onEnable() {
        logger.info("WaterJNU已加载")
        CommandManager.registerCommand(Water)
    }

    override fun onDisable(){
        CommandManager.unregisterCommand(Water)
        logger.info("WaterJNU已卸载")
    }

    object Water : SimpleCommand(
        Waterjnu,"water","水电费" ,
        description = "查询水电费"
    ) {
        @Handler
        suspend fun CommandSender.water(id:String) {
            val url = "https://api.hengy1.top/IBSJnuWeb/balance?dormitory=$id"
            val requestStr = getRes(url)
            if(requestStr?.contains("\"errno\":0") == true) {
                val res = Gson().fromJson(requestStr, Info::class.java)
                var text = "宿舍：${res.data.dormitory}\n"
                text += "余额：${res.data.balance}\n"
                text += "电费补贴：${res.data.electricitySubsidy}\n"
                text += "冷水补贴：${res.data.coldSubsidy}\n"
                text += "热水补贴：${res.data.hotSubsidy}\n"
                text += "提示：${res.data.notice}"
                subject?.sendMessage(text)
            }
            else{
                val res = Gson().fromJson(requestStr, ErrInfo::class.java)
                subject?.sendMessage(res.errmsg)
            }
        }
    }

}