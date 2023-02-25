package hyrise.sa.cmd;

import hyrise.sa.player.role.Role;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ClearChatCommand extends Commands {
   private static final SimpleDateFormat SDF = new SimpleDateFormat("d 'de' MMMM. yyyy 'às' HH:mm", Locale.forLanguageTag("pt-BR"));

   public ClearChatCommand() {
      super("cc", "clearchat", "chatclear");
   }

   public void perform(CommandSender sender, String label, String[] args) {
      if (!sender.hasPermission("kcore.cmd.clearchat")) {
         sender.sendMessage("§cVocê não possui permissão para utilizar este comando.");
      } else {
         sender.sendMessage("§aLimpando mensagens...");

         for(int i = 0; i < 999; ++i) {
            Bukkit.getOnlinePlayers().forEach((player) -> {
               player.sendMessage("");
            });
         }

         TextComponent component = new TextComponent("");
         BaseComponent[] var5 = TextComponent.fromLegacyText("§aChat limpo por " + (!(sender instanceof Player) ? "§6Console" : Role.getColored(sender.getName())) + "§a.");
         int var6 = var5.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            BaseComponent components = var5[var7];
            component.addExtra(components);
         }

         component.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, TextComponent.fromLegacyText("§fHorário: §7" + SDF.format(System.currentTimeMillis()))));
         Bukkit.getOnlinePlayers().forEach((player) -> {
            player.spigot().sendMessage(component);
         });
      }
   }
}
