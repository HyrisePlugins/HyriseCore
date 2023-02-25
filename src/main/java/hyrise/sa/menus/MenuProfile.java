package hyrise.sa.menus;

import hyrise.sa.menus.profile.MenuAchievements;
import hyrise.sa.menus.profile.MenuBoosters;
import hyrise.sa.menus.profile.MenuPreferences;
import hyrise.sa.menus.profile.MenuStatistics;
import hyrise.sa.player.Profile;
import hyrise.sa.Core;
import hyrise.sa.libraries.menu.PlayerMenu;
import hyrise.sa.menus.profile.*;
import hyrise.sa.player.role.Role;
import hyrise.sa.utils.BukkitUtils;
import hyrise.sa.utils.StringUtils;
import hyrise.sa.utils.enums.EnumSound;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MenuProfile extends PlayerMenu {
  
  private static final SimpleDateFormat SDF = new SimpleDateFormat("d 'de' MMMM. yyyy 'às' HH:mm",
      Locale.forLanguageTag("pt-BR"));
  
  public MenuProfile(Profile profile) {
    super(profile.getPlayer(), "Meu Perfil", 6);
    
    this.setItem(49, BukkitUtils.putProfileOnSkull(this.player, BukkitUtils.deserializeItemStack(
        "SKULL_ITEM:3 : 1 : nome>&aInformações Pessoais : desc>&fRank: " + Role.getRoleByName(profile.getDataContainer("kCoreProfile", "role").getAsString())
            .getName() +"\n&fHyrise Nível: &b" + StringUtils.formatNumber(profile.getStats("kCoreSkyWars", "level")) + "\n&fFragmentos: &d" + StringUtils.formatNumber(profile.getStats("kMysteryBox", "mystery_frags")) + "\n&fCash: &6" + StringUtils.formatNumber(profile.getStats("kCoreProfile", "cash")) + "\n \n&fCadastrado: &7" + SDF.format(profile.getDataContainer("kCoreProfile", "created").getAsLong()) + "\n&fÚltimo acesso: &7" + SDF
            .format(profile.getDataContainer("kCoreProfile", "lastlogin").getAsLong()))));

    this.setItem(11, BukkitUtils.deserializeItemStack(
        "PAPER : 1 : nome>&aEstatísticas : desc>&7Visualize as suas estatísticas de\n&7cada Minigame do nosso servidor.\n \n&eClique para ver as estatísticas!"));

    this.setItem(12, BukkitUtils.deserializeItemStack(
            "SKULL_ITEM:3 : 1 : nome>&aRedes Sociais : desc>&eClique para ver as redes sociais! : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzY4NWEwYmU3NDNlOTA2N2RlOTVjZDhjNmQxYmEyMWFiMjFkMzczNzFiM2Q1OTcyMTFiYjc1ZTQzMjc5In19fQ=="));

    this.setItem(13, BukkitUtils.deserializeItemStack(
        "REDSTONE_COMPARATOR : 1 : nome>&aPreferências : desc>&7Em nosso servidor você pode personalizar\n&7sua experiência de jogo por completo.\n&7Personalize várias opções únicas como\n&7você desejar!\n \n&8As opções incluem ativar ou desativar as\n&8mensagens privadas, os jogadores e outros.\n \n&eClique para personalizar as opções!"));
    
    this.setItem(14, BukkitUtils.deserializeItemStack(
        "POTION:8232 : 1 : esconder>tudo : nome>&aMultiplicadores de Coins : desc>&7Em nosso servidor existe um sistema\n&7de &6Multiplicadores de Coins &7que afetam\n&7a quantia de &6Coins &7ganhos nas partidas.\n \n&8Os Multiplicadores podem variar de\n&8pessoais ou gerais, podendo beneficiar\n&8você e até mesmo os outros jogadores.\n \n&eClique para ver seus multiplicadores!"));
    
    this.setItem(15, BukkitUtils.deserializeItemStack(
        "GOLD_INGOT : 1 : nome>&aDesafios : desc>&7Em nosso servidor existe um sistema\n&7de &6Desafios &7que se consiste em missões\n&7de realização única que lhe garante\n&7vários prêmios vitalícios.\n \n&8Os Prêmios variam entre títulos, coins,\n&8ícones de prestígio e outros cosméticos.\n \n&eClique para ver os desafios!"));

    // VIDRO PRETO

    this.setItem(36, BukkitUtils.deserializeItemStack(
            "160:15 : 1 : nome>&8"));
    this.setItem(37, BukkitUtils.deserializeItemStack(
            "160:15 : 1 : nome>&8"));
    this.setItem(38, BukkitUtils.deserializeItemStack(
            "160:15 : 1 : nome>&8"));
    this.setItem(39, BukkitUtils.deserializeItemStack(
            "160:15 : 1 : nome>&8"));
    this.setItem(40, BukkitUtils.deserializeItemStack(
            "160:15 : 1 : nome>&8"));
    this.setItem(41, BukkitUtils.deserializeItemStack(
            "160:15 : 1 : nome>&8"));
    this.setItem(42, BukkitUtils.deserializeItemStack(
            "160:15 : 1 : nome>&8"));
    this.setItem(43, BukkitUtils.deserializeItemStack(
            "160:15 : 1 : nome>&8"));
    this.setItem(44, BukkitUtils.deserializeItemStack(
            "160:15 : 1 : nome>&8"));

    
    this.register(Core.getInstance());
    this.open();
  }
  
  @EventHandler
  public void onInventoryClick(InventoryClickEvent evt) {
    if (evt.getInventory().equals(this.getInventory())) {
      evt.setCancelled(true);
      
      if (evt.getWhoClicked().equals(this.player)) {
        Profile profile = Profile.getProfile(this.player.getName());
        if (profile == null) {
          this.player.closeInventory();
          return;
        }
        
        if (evt.getClickedInventory() != null && evt.getClickedInventory().equals(this.getInventory())) {
          ItemStack item = evt.getCurrentItem();
          
          if (item != null && item.getType() != Material.AIR) {
            if (evt.getSlot() == 49) {
              EnumSound.ITEM_PICKUP.play(this.player, 0.5F, 2.0F);
            } else if (evt.getSlot() == 11) {
              EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
              new MenuStatistics(profile);
            } else if (evt.getSlot() == 13) {
              EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
              new MenuPreferences(profile);

            } else if (evt.getSlot() == 14) {
              EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
              new MenuBoosters(profile);
            } else if (evt.getSlot() == 12) {
              EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
              new MenuSocialNetworks(profile);
            } else if (evt.getSlot() == 15) {
              EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
              new MenuAchievements(profile);
            }
          }
        }
      }
    }
  }
  
  public void cancel() {
    HandlerList.unregisterAll(this);
  }
  
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent evt) {
    if (evt.getPlayer().equals(this.player)) {
      this.cancel();
    }
  }
  
  @EventHandler
  public void onInventoryClose(InventoryCloseEvent evt) {
    if (evt.getPlayer().equals(this.player) && evt.getInventory().equals(this.getInventory())) {
      this.cancel();
    }
  }
}
