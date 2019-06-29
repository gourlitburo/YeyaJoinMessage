package yy.gourlitburo.yeyajoinmessage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  Logger logger = getLogger();
  Server server = getServer();

  PluginManager manager = Bukkit.getPluginManager();

  final String KEY_MSG_JOIN = "msg_join";

  PlayerJoinEventHandler playerJoinEventHandler = new PlayerJoinEventHandler(this);

  String getMsgJoin() {
    return getConfig().getString(KEY_MSG_JOIN);
  }

  void sendColoredMessage(Player recipient, String message) {
    recipient.sendMessage(message.replaceAll("&([0-9a-f])", "\u00A7$1"));
  }

  @Override
  public void onEnable() {
    manager.registerEvents(playerJoinEventHandler, this);
    getCommand("yjm").setTabCompleter(new YJMTabCompleter());
    logger.info("YeyaJoinMessage ready.");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 0) return false;
    String action = args[0];

    Player senderPlayer = (Player) sender;

    if (action.equalsIgnoreCase("set") && args.length >= 2) {
      List<String> newMsgJoinA = new ArrayList<>();
      for (int i = 1; i < args.length; ++i) {
        newMsgJoinA.add(args[i]);
      }
      String newMsgJoin = String.join(" ", newMsgJoinA);
      getConfig().set(KEY_MSG_JOIN, newMsgJoin);
      saveConfig();
      sendColoredMessage(senderPlayer, String.format("New join message is '%s&r'.", getMsgJoin()));
      return true;
    } else if (action.equalsIgnoreCase("show")) {
      sendColoredMessage(senderPlayer, getMsgJoin());
      return true;
    } else if (action.equalsIgnoreCase("reload")) {
      reloadConfig();
      sender.sendMessage("Reloaded.");
      return true;
    }
    return false;
  }
}
