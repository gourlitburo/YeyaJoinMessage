package yy.gourlitburo.yeyajoinmessage;

import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

class PlayerQuitEventHandler implements Listener {

  private Main plugin;

  public PlayerQuitEventHandler(Main instance) {
    plugin = instance;
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    if (plugin.getMsgEnable(plugin.KEY_MSG_JOIN_BROADCAST)) {
      String formatted = plugin.formatter.format(plugin.getMsgText(plugin.KEY_MSG_QUIT_BROADCAST), Map.of(
        "PLAYER", event.getPlayer().getName()
      ));
      event.setQuitMessage(formatted);
      plugin.logger.info("Broadcasted quit message.");
    }
  }

}
