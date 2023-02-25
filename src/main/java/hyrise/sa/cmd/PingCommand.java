package hyrise.sa.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand extends Commands {
   public PingCommand() {
      super("ping");
   }

   public void perform(CommandSender sender, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("§cApenas jogadores podem utilizar este comando.");
      } else {
         Player player;
         if (args.length == 0) {
            player = (Player)sender;
            player.sendMessage("§aSeu ping é " + ((CraftPlayer)player).getHandle().ping + "ms.");
         } else {
            player = Bukkit.getPlayerExact(args[0]);
            if (player == null) {
               sender.sendMessage("§cJogador não encontrado.");
            } else {
               sender.sendMessage("§aO ping de " + player.getName() + " é " + ((CraftPlayer)player).getHandle().ping + "ms.");
            }
         }
      }
   }
}
