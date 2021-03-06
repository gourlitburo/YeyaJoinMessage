package yy.gourlitburo.yeyajoinmessage;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import yy.gourlitburo.stringformatter.StringFormatter;

class PlayerQuitEventHandler implements Listener {

  private Main plugin;

  public PlayerQuitEventHandler(Main instance) {
    plugin = instance;
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    if (plugin.getMsgEnable(plugin.KEY_MSG_JOIN_BROADCAST)) {
      String formatted = StringFormatter.format(plugin.getMsgText(plugin.KEY_MSG_QUIT_BROADCAST), plugin.getParameterMap(event.getPlayer()));
      event.setQuitMessage(formatted);
      plugin.logger.info("Broadcasted quit message.");
    }
  }

}
