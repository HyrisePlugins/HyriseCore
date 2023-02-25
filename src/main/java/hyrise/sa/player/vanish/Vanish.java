package hyrise.sa.player.vanish;

import hyrise.sa.Core;
import hyrise.sa.nms.NMS;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class Vanish {
   public static List<String> isVanish = new ArrayList<>();

   public static void setupVanish() {
      Bukkit.getScheduler().runTaskTimer(Core.getInstance(), () -> Bukkit.getOnlinePlayers().stream().filter((player) -> isVanish.contains(player.getName())).forEach((player) -> {
         NMS.sendActionBar(player, "§aVocê está invisível para os outros jogadores!");
         Bukkit.getOnlinePlayers().stream().filter((p) -> !p.hasPermission("kcore.cmd.vanish")).forEach((p) -> p.hidePlayer(player));
      }), 1L, 1L);
   }
}
