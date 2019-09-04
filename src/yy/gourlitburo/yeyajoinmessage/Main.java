package yy.gourlitburo.yeyajoinmessage;

import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import yy.gourlitburo.stringformatter.StringFormatter;

public class Main extends JavaPlugin {

  Logger logger = getLogger();
  Server server = getServer();
  PluginManager manager = Bukkit.getPluginManager();
  StringFormatter formatter = new StringFormatter();

  private final String KEY_MSGS = "msg";
  private final String KEY_ENABLE = "enable";
  private final String KEY_TEXT = "text";
  
  final String KEY_MSG_JOIN = "join";
  final String KEY_MSG_JOIN_BROADCAST = "join_broadcast";
  final String KEY_MSG_QUIT_BROADCAST = "quit_broadcast";
  final String KEY_MSG_ENTER_NETHER_BROADCAST = "enter_nether_broadcast";
  final String KEY_MSG_LEAVE_NETHER_BROADCAST = "leave_nether_broadcast";
  final String KEY_MSG_ENTER_END_BROADCAST = "enter_end_broadcast";
  final String KEY_MSG_LEAVE_END_BROADCAST = "leave_end_broadcast";

  final String M_INVALID_KEY = "Invalid message key.";

  private String makeMsgPath(String key) {
    return KEY_MSGS + "." + key;
  }

  private void setConfig(String path, Object value) {
    getConfig().set(path, value);
    saveConfig();
  };

  boolean isValidMsgKey(String key) {
    return getConfig().contains(makeMsgPath(key));
  }

  void setMsgEnable(String key, Boolean enable) {
    setConfig(makeMsgPath(key) + "." + KEY_ENABLE, enable);
  };

  void setMsgText(String key, String text) {
    setConfig(makeMsgPath(key) + "." + KEY_TEXT, text);
  };

  boolean getMsgEnable(String key) {
    return getConfig().getBoolean(makeMsgPath(key) + "." + KEY_ENABLE);
  }

  String getMsgText(String key) {
    return getConfig().getString(makeMsgPath(key) + "." + KEY_TEXT);
  }

  String getDisplayName(CommandSender sender) {
    return sender instanceof Player ? ((Player) sender).getDisplayName() : sender.getName();
  }

  World getWorld(CommandSender sender) {
    if (sender instanceof Player) {
      return ((Player) sender).getWorld();
    } else if (sender instanceof BlockCommandSender) {
      return ((BlockCommandSender) sender).getBlock().getWorld();
    } else if (sender instanceof ConsoleCommandSender) {
      return server.getWorlds().get(0);
    }
    return null;
  }

  private String padLeft(String str, int n, char c) {
    int l = str.length();
    if (l >= n) return str;
    StringBuilder builder = new StringBuilder(str);
    for (int i = 0; i < n - l; ++i) {
      builder.insert(0, c);
    }
    return builder.toString();
  }

  private String padLeft(int num, int n) {
    return padLeft(Integer.toString(num), n, '0');
  }

  private String formatTime(long time) {
    float hours = time / 1000f; // relative time is analogous to hours * 1000
    int h = (int) hours;
    int m = (int) (60 * (hours % 1f));
    return String.format("%s:%s", padLeft(h, 2), padLeft(m, 2));
  }

  Map<String, String> getParameterMap(CommandSender player) {
    World world = getWorld(player);
    String timeStr = world == null ? "??:??" : formatTime(world.getTime());
    int playerCount = server.getOnlinePlayers().size();
    return Map.of(
      "NAME", player.getName(),
      "DISPLAYNAME", getDisplayName(player),
      "TIME", timeStr,
      "PLAYERCOUNT", Integer.toString(playerCount)
    );
  }

  @Override
  public void onEnable() {
    manager.registerEvents(new PlayerJoinEventHandler(this), this);
    manager.registerEvents(new PlayerQuitEventHandler(this), this);
    manager.registerEvents(new PlayerChangedWorldEventHandler(this), this);

    PluginCommand command = getCommand("yjm");
    command.setExecutor(new YJMCommandExecutor(this));
    command.setTabCompleter(new YJMTabCompleter(this));

    saveDefaultConfig(); // does nothing if user's config already exists

    logger.info("YeyaJoinMessage ready.");
  }

}
