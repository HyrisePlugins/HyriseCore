package hyrise.sa.cmd;

import hyrise.sa.player.role.Role;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportHereCommand extends Commands {
   public TeleportHereCommand() {
      super("tphere", "teleporthere");
   }

   public void perform(CommandSender sender, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("§cApenas jogadores podem utilizar este comando.");
      } else {
         Player player = (Player)sender;
         if (!player.hasPermission("kcore.cmd.teleport")) {
            player.sendMessage("§cVocê não possui permissão para utilizar este comando.");
         } else if (args.length == 0) {
            player.sendMessage("§cUtilize /tphere [jogador]");
         } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
               player.sendMessage("§cJogador não encontrado.");
            } else {
               target.teleport(player);
               target.sendMessage("§aVocê foi teleportado para o jogador " + Role.getColored(player.getName()) + "§a.");
            }
         }
      }
   }
}
