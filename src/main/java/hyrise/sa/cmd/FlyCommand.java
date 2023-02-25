package hyrise.sa.cmd;

import hyrise.sa.player.Profile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand extends Commands {
   public FlyCommand() {
      super("fly", "voar");
   }

   public void perform(CommandSender sender, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("§cApenas jogadores podem utilizar este comando.");
      } else {
         Player player = (Player)sender;
         Profile profile = Profile.getProfile(player.getName());
         if (!player.hasPermission("kcore.fly")) {
            player.sendMessage("§cVocê não possui permissão para utilizar este comando.");
         } else if (profile.playingGame()) {
            player.sendMessage("§cVocê não pode alterar o modo voar em jogo.");
         } else {
            player.setAllowFlight(!player.getAllowFlight());
            player.sendMessage("§aModo voar " + (player.getAllowFlight() ? "ativado." : "desativado."));
         }
      }
   }
}
