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
    player.sendMessage(plugin.formatter.format(plugin.getMsgJoin(), Map.of(
      "PLAYER", playerName
    )));
    plugin.logger.info(String.format("Sent join message to %s.", playerName));
  }
  
}
