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

  private final String KEY_MSGS = "msg";
  private final String KEY_ENABLE = "enable";
  private final String KEY_TEXT = "text";
  final String KEY_MSG_JOIN = "join";
  final String KEY_MSG_JOIN_BROADCAST = "join_broadcast";
  final String KEY_MSG_QUIT_BROADCAST = "quit_broadcast";

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

  @Override
  public void onEnable() {
    manager.registerEvents(new PlayerJoinEventHandler(this), this);
    manager.registerEvents(new PlayerQuitEventHandler(this), this);

    PluginCommand command = getCommand("yjm");
    command.setExecutor(new YJMCommandExecutor(this));
    command.setTabCompleter(new YJMTabCompleter(this));

    saveDefaultConfig(); // does nothing if user's config already exists

    logger.info("YeyaJoinMessage ready.");
  }

}
