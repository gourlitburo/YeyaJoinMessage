package yy.gourlitburo.yeyajoinmessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

class YJMTabCompleter implements TabCompleter {

  private Main plugin;
  private static final List<String> ACTIONS = Arrays.asList("set", "enable", "disable", "show", "show-raw", "reload");
  private static final List<String> ARGUABLE_ACTIONS = Arrays.asList("set", "enable", "disable", "show", "show-raw");

  public YJMTabCompleter(Main instance) {
    plugin = instance;
  }
  
  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    if (args.length == 1) {
      return StringUtil.copyPartialMatches(args[0], ACTIONS, new ArrayList<String>());
    } else if (args.length == 2 && ARGUABLE_ACTIONS.indexOf(args[0]) != -1) {
      Set<String> keys = plugin.getConfig().getDefaults().getConfigurationSection("msg").getKeys(false);
      return StringUtil.copyPartialMatches(args[1], keys, new ArrayList<String>());
    } else {
      return new ArrayList<String>();
    }
  }

}
