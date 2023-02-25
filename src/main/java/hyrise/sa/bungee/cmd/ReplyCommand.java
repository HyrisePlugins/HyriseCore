package hyrise.sa.bungee.cmd;

import hyrise.sa.player.role.Role;
import hyrise.sa.utils.StringUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ReplyCommand extends Commands {
   public ReplyCommand() {
      super("r", "reply");
   }

   public void perform(CommandSender sender, String[] args) {
      if (!(sender instanceof ProxiedPlayer)) {
         sender.sendMessage(TextComponent.fromLegacyText("§cApenas jogadores podem utilizar este comando."));
      } else {
         ProxiedPlayer player = (ProxiedPlayer)sender;
         if (args.length == 0) {
            player.sendMessage(TextComponent.fromLegacyText("§cUtilize /r [mensagem]"));
         } else if (!TellCommand.TELL_CACHE.containsKey(player.getName())) {
            player.sendMessage(TextComponent.fromLegacyText("§cVocê não tem ninguém para responder."));
         } else {
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer((String)TellCommand.TELL_CACHE.get(player.getName()));
            if (target != null && target.isConnected()) {
               String message = StringUtils.join((Object[])args, " ");
               if (player.hasPermission("kcore.tell.color")) {
                  message = StringUtils.formatColors(message);
               }

               TellCommand.TELL_CACHE.put(player.getName(), target.getName());
               TellCommand.TELL_CACHE.put(target.getName(), player.getName());
               target.sendMessage(TextComponent.fromLegacyText("§8Mensagem de: " + Role.getColored(player.getName()) + "§8: §6" + message));
               player.sendMessage(TextComponent.fromLegacyText("§8Mensagem para: " + Role.getColored(target.getName()) + "§8: §6" + message));
            } else {
               player.sendMessage(TextComponent.fromLegacyText("§cVocê não tem ninguém para responder."));
            }
         }
      }
   }
}
