package yy.gourlitburo.yeyajoinmessage;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  Logger logger = getLogger();
  Server server = getServer();
  PluginManager manager = Bukkit.getPluginManager();
  StringFormatter formatter = new StringFormatter();

  final String KEY_MSG_JOIN = "msg_join";

  String getMsgJoin() {
    return getConfig().getString(KEY_MSG_JOIN);
  }

  @Override
  public void onEnable() {
    manager.registerEvents(new PlayerJoinEventHandler(this), this);

    PluginCommand command = getCommand("yjm");
    command.setExecutor(new YJMCommandExecutor(this));
    command.setTabCompleter(new YJMTabCompleter());

    logger.info("YeyaJoinMessage ready.");
  }

}
