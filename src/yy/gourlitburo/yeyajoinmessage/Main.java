package yy.gourlitburo.yeyajoinmessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  Logger logger = getLogger();
  Server server = getServer();
  PluginManager manager = Bukkit.getPluginManager();
  StringFormatter formatter = new StringFormatter();

  final String KEY_MSG_JOIN = "msg_join";

  private PlayerJoinEventHandler playerJoinEventHandler = new PlayerJoinEventHandler(this);

  String getMsgJoin() {
    return getConfig().getString(KEY_MSG_JOIN);
  }

  @Override
  public void onEnable() {
    manager.registerEvents(playerJoinEventHandler, this);

    PluginCommand command = getCommand("yjm");
    command.setTabCompleter(new TabComplete());
    
    logger.info("YeyaJoinMessage ready.");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 0) return false;
    String action = args[0];

    if (action.equalsIgnoreCase("set") && args.length >= 2) {
      List<String> newMsgJoinA = new ArrayList<>();
      for (int i = 1; i < args.length; ++i) {
        newMsgJoinA.add(args[i]);
      }
      String newMsgJoin = String.join(" ", newMsgJoinA);
      getConfig().set(KEY_MSG_JOIN, newMsgJoin);
      saveConfig();
      sender.sendMessage(formatter.colorize(String.format("New join message is '%s&r'.", getMsgJoin())));
      return true;
    } else if (action.equalsIgnoreCase("show")) {
      sender.sendMessage(formatter.format(getMsgJoin(), Map.of(
        "PLAYER", sender.getName()
      )));
      return true;
    } else if (action.equalsIgnoreCase("reload")) {
      reloadConfig();
      sender.sendMessage("Reloaded.");
      return true;
    }
    return false;
  }

}
