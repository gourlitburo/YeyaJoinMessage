package yy.gourlitburo.yeyajoinmessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

class YJMCommandExecutor implements CommandExecutor {

  private Main plugin;

  public YJMCommandExecutor(Main instance) {
    plugin = instance;
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
      plugin.getConfig().set(plugin.KEY_MSG_JOIN, newMsgJoin);
      plugin.saveConfig();
      sender.sendMessage(plugin.formatter.colorize(String.format("New join message is '%s&r'.", plugin.getMsgJoin())));
      return true;
    } else if (action.equalsIgnoreCase("show") || action.equalsIgnoreCase("show-raw")) {
      String message = plugin.getMsgJoin();
      if (action.equalsIgnoreCase("show")) {
        message = plugin.formatter.format(message, Map.of(
          "PLAYER", sender.getName()
        ));
      }
      sender.sendMessage(message);
      return true;
    } else if (action.equalsIgnoreCase("reload")) {
      plugin.reloadConfig();
      sender.sendMessage("Reloaded.");
      return true;
    }
    return false;
  }

}
