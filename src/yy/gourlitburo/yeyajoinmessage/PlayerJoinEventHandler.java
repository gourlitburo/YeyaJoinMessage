package yy.gourlitburo.yeyajoinmessage;

import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

class PlayerJoinEventHandler implements Listener {

  private Main plugin;
  
  public PlayerJoinEventHandler(Main instance) {
    plugin = instance;
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    String playerName = player.getName();
    Map<String, String> map = Map.of(
      "PLAYER", playerName
    );
    if (plugin.getMsgEnable(plugin.KEY_MSG_JOIN)) {
      player.sendMessage(plugin.formatter.format(plugin.getMsgText(plugin.KEY_MSG_JOIN), map));
      plugin.logger.info(String.format("Sent join message to %s.", playerName));
    }
    if (plugin.getMsgEnable(plugin.KEY_MSG_JOIN_BROADCAST)) {
      String formatted = plugin.formatter.format(plugin.getMsgText(plugin.KEY_MSG_JOIN_BROADCAST), map);
      event.setJoinMessage(formatted);
      plugin.logger.info("Broadcasted join message.");
    }
  }
  
}
