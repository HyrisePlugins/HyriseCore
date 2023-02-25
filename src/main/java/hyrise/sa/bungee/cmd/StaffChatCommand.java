package hyrise.sa.bungee.cmd;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import hyrise.sa.player.role.Role;
import hyrise.sa.utils.StringUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

public class StaffChatCommand extends Commands {
   public static List<String> IGNORE = new ArrayList();

   public StaffChatCommand() {
      super("sc", "staffchat");
   }

   public void perform(CommandSender sender, String[] args) {
      if (!(sender instanceof ProxiedPlayer)) {
         sender.sendMessage(TextComponent.fromLegacyText("§cApenas jogadores podem utilizar este comando."));
      } else {
         ProxiedPlayer player = (ProxiedPlayer)sender;
         if (!player.hasPermission("kcore.cmd.staffchat")) {
            player.sendMessage(TextComponent.fromLegacyText("§cVocê não possui permissão para utilizar este comando."));
         } else if (args.length == 0) {
            player.sendMessage(TextComponent.fromLegacyText("§cUtilize /sc [mensagem] ou /sc [ativar/desativar]"));
         } else {
            String message = args[0];
            if (message.equalsIgnoreCase("ativar")) {
               player.sendMessage(TextComponent.fromLegacyText("§aStaffChat ativado."));
               IGNORE.remove(player.getName());
            } else if (message.equalsIgnoreCase("desativar")) {
               player.sendMessage(TextComponent.fromLegacyText("§cStaffChat desativado."));
               IGNORE.add(player.getName());
            } else {
               String format = StringUtils.formatColors(StringUtils.join((Object[])args, " "));
               BungeeCord.getInstance().getPlayers().stream().filter((pplayer) -> {
                  return pplayer.hasPermission("kcore.cmd.staffchat") && !IGNORE.contains(pplayer.getName());
               }).forEach((pplayer) -> {
                  ByteArrayDataOutput out = ByteStreams.newDataOutput();
                  out.writeUTF("STAFF_BAR");
                  out.writeUTF(pplayer.getName());
                  pplayer.getServer().sendData("kCore", out.toByteArray());
                  pplayer.sendMessage(TextComponent.fromLegacyText("§d[STAFF] §7[" + StringUtils.capitalise(player.getServer().getInfo().getName().toLowerCase()) + "] §7" + Role.getPrefixed(player.getName(), true) + "§f: " + format));
               });
            }
         }
      }
   }
}
