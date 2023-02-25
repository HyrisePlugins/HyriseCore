package hyrise.sa.cmd;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand extends Commands {
   public ShopCommand() {
      super("loja", "shop");
   }

   public void perform(CommandSender sender, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("§cApenas jogadores podem utilizar este comando.");
      } else {
         Player player = (Player)sender;
         player.closeInventory();
         TextComponent component = new TextComponent("");
         BaseComponent[] var6 = TextComponent.fromLegacyText("\n §eClique ");
         int var7 = var6.length;

         int var8;
         for(var8 = 0; var8 < var7; ++var8) {
            BaseComponent components = var6[var8];
            component.addExtra(components);
         }

         TextComponent click = new TextComponent("AQUI");
         click.setColor(ChatColor.YELLOW);
         click.setBold(true);
         click.setClickEvent(new ClickEvent(Action.OPEN_URL, "https://loja.redeseat.com"));
         click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Clique aqui para abrir a loja.")));
         component.addExtra(click);
         BaseComponent[] var12 = TextComponent.fromLegacyText(" §epara abrir a loja do servidor.\n ");
         var8 = var12.length;

         for(int var13 = 0; var13 < var8; ++var13) {
            BaseComponent components = var12[var13];
            component.addExtra(components);
         }

         player.spigot().sendMessage(component);
      }
   }
}
