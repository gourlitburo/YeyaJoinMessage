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

    if (action.equalsIgnoreCase("reload")) {
      plugin.reloadConfig();
      sender.sendMessage("Reloaded.");
      return true;
    } else {
      if (args.length <= 1) return false;
      String msgKey = args[1];
      if (!plugin.isValidMsgKey(msgKey)) {
        sender.sendMessage(plugin.M_INVALID_KEY);
        return true;
      }
      if (action.equalsIgnoreCase("set") && args.length >= 2) {
        List<String> newMsgA = new ArrayList<>();
        for (int i = 2; i < args.length; ++i) {
          newMsgA.add(args[i]);
        }
        String newMsg = String.join(" ", newMsgA).replaceAll("\\\\n", "\n");
        plugin.setMsgText(msgKey, newMsg);
        sender.sendMessage(plugin.formatter.colorize(String.format("New join message is '%s&r'.", plugin.getMsgText(msgKey))));
      } else if (action.equalsIgnoreCase("enable")) {
        plugin.setMsgEnable(msgKey, true);
        sender.sendMessage("Message '" + msgKey + "' is now enabled.");
      } else if (action.equalsIgnoreCase("disable")) {
        plugin.setMsgEnable(msgKey, false);
        sender.sendMessage("Message '" + msgKey + "' is now disabled.");
      } else if (action.equalsIgnoreCase("show") || action.equalsIgnoreCase("show-raw")) {
        String message = plugin.getMsgText(msgKey);
        if (action.equalsIgnoreCase("show")) {
          String senderName = sender.getName();
          message = plugin.formatter.format(message, Map.of(
            "PLAYER", senderName,
            "NAME", senderName,
            "DISPLAYNAME", plugin.getDisplayName(sender)
          ));
        }
        sender.sendMessage(message);
      }
      return true;
    }
  }

}
