package hyrise.sa.cmd;

import hyrise.sa.game.FakeGame;
import hyrise.sa.game.Game;
import hyrise.sa.player.Profile;
import hyrise.sa.Core;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand extends Commands {
   public LobbyCommand() {
      super("lobby");
   }

   public void perform(CommandSender sender, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("§cApenas jogadores podem utilizar este comando.");
      } else {
         Player player = (Player)sender;
         Profile profile = Profile.getProfile(player.getName());
         Game<?> game = profile.getGame();
         if (game != null && !(game instanceof FakeGame)) {
            player.sendMessage("§aConectando...");
            if (Core.minigame.equals("Sky Wars")) {
               Core.sendServer(profile, "skywars");
            }

         } else {
            Core.sendServer(profile, "lobby");
         }
      }
   }
}
