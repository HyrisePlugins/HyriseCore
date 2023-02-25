package hyrise.sa.cmd;

import hyrise.sa.game.FakeGame;
import hyrise.sa.game.Game;
import hyrise.sa.game.GameTeam;
import hyrise.sa.player.Profile;
import hyrise.sa.player.vanish.Vanish;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand extends Commands {
   public VanishCommand() {
      super("vanish", "v");
   }

   public void perform(CommandSender sender, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("§cApenas jogadores podem utilizar este comando.");
      } else {
         Player player = (Player)sender;
         Profile profile = Profile.getProfile(player.getName());
         if (!player.hasPermission("kcore.cmd.vanish")) {
            player.sendMessage("§cVocê não possui permissão para utilizar este comando.");
         } else {
            boolean contains = Vanish.isVanish.contains(player.getName());
            Game<? extends GameTeam> game = profile.getGame();
            if (contains) {
               Bukkit.getOnlinePlayers().forEach((p) -> {
                  Profile profilep = Profile.getProfile(p.getName());
                  if (!(game instanceof FakeGame)) {
                     if (game == null && !profilep.playingGame() && p.getWorld() == player.getWorld()) {
                        p.showPlayer(player);
                     }

                     if (game != null && profilep.getGame() != null && profile.getGame() == game) {
                        if (game.isSpectator(player) && game.isSpectator(p)) {
                           p.showPlayer(player);
                        }

                        if (!game.isSpectator(player) && !game.isSpectator(p)) {
                           p.showPlayer(player);
                        }
                     }

                  }
               });
               Vanish.isVanish.remove(player.getName());
               player.sendMessage("§aModo invisível desativado.");
            } else {
               Vanish.isVanish.add(player.getName());
               player.sendMessage("§aModo invisível ativado.");
            }
         }
      }
   }
}
