package yy.gourlitburo.yeyajoinmessage;

import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventHandler implements Listener {

  Main plugin;
  Logger logger;
  Server server = Bukkit.getServer();
  
  public PlayerJoinEventHandler(Main instance) {
    plugin = instance;
    logger = plugin.getLogger();
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    String playerName = player.getName();
    player.sendMessage(plugin.formatter.format(plugin.getMsgJoin(), Map.of(
      "PLAYER", playerName
    )));
    logger.info(String.format("Sent join message to %s.", playerName));
  }
  
}
