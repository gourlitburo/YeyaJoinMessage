package yy.gourlitburo.yeyajoinmessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

public class YJMTabCompleter implements TabCompleter {
  private static final List<String> ACTIONS = Arrays.asList("set", "show", "reload");
  
  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    if (args.length <= 1) {
      return StringUtil.copyPartialMatches(args[0], ACTIONS, new ArrayList<String>());
    } else {
      return new ArrayList<String>();
    }
  }
}
