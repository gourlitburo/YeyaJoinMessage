package yy.gourlitburo.yeyajoinmessage;

import java.util.Map;

import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

class PlayerChangedWorldEventHandler implements Listener {

  private Main plugin;

  PlayerChangedWorldEventHandler(Main instance) {
    plugin = instance;
  }

  @EventHandler
  public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
    Player player = event.getPlayer();
    Environment fromEnvironment = event.getFrom().getEnvironment();
    Environment environment = player.getWorld().getEnvironment();
    Map<String, String> values = Map.of(
      "NAME", player.getName(),
      "DISPLAYNAME", player.getDisplayName()
    );

    // TODO: dry this up
    String broadcastString = null;
    if (environment == Environment.NETHER && plugin.getMsgEnable(plugin.KEY_MSG_ENTER_NETHER_BROADCAST)) {
      broadcastString = plugin.formatter.format(plugin.getMsgText(plugin.KEY_MSG_ENTER_NETHER_BROADCAST), values);
    } else if (fromEnvironment == Environment.NETHER && plugin.getMsgEnable(plugin.KEY_MSG_LEAVE_NETHER_BROADCAST)) {
      broadcastString = plugin.formatter.format(plugin.getMsgText(plugin.KEY_MSG_LEAVE_NETHER_BROADCAST), values);
    } else if (environment == Environment.THE_END && plugin.getMsgEnable(plugin.KEY_MSG_ENTER_END_BROADCAST)) {
      broadcastString = plugin.formatter.format(plugin.getMsgText(plugin.KEY_MSG_ENTER_END_BROADCAST), values);
    } else if (fromEnvironment == Environment.THE_END && plugin.getMsgEnable(plugin.KEY_MSG_LEAVE_END_BROADCAST)) {
      broadcastString = plugin.formatter.format(plugin.getMsgText(plugin.KEY_MSG_LEAVE_END_BROADCAST), values);
    }
    if (broadcastString != null) {
      plugin.server.broadcastMessage(broadcastString);
      plugin.server.getConsoleSender().sendMessage(broadcastString);
      plugin.logger.info("Broadcasted world change message.");
    }
  }
  
}
