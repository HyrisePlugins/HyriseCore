package hyrise.sa.menus;

import hyrise.sa.database.data.DataContainer;
import hyrise.sa.listeners.Listeners;
import hyrise.sa.player.Profile;
import hyrise.sa.Core;
import hyrise.sa.libraries.menu.PlayerMenu;
import hyrise.sa.utils.BukkitUtils;
import hyrise.sa.utils.enums.EnumSound;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class MenuSocialNetworks extends PlayerMenu {
   public MenuSocialNetworks(Profile profile) {
      super(profile.getPlayer(), "Redes Sociais", 4);
      DataContainer data = profile.getDataContainer("kCoreProfile", "twitter");
      String name = data == null ? "" : data.getAsString();
      this.setItem(10, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&aTwitter : desc>&7Altere o link do seu twitter!\n \n&fAtual: &b" + (name.isEmpty() ? "Nenhum" : name) + "\n \n&eClique para alterar! : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzY4NWEwYmU3NDNlOTA2N2RlOTVjZDhjNmQxYmEyMWFiMjFkMzczNzFiM2Q1OTcyMTFiYjc1ZTQzMjc5In19fQ=="));
      data = profile.getDataContainer("kCoreProfile", "youtube");
      name = data == null ? "" : data.getAsString();
      this.setItem(11, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&aYouTube : desc>&7Altere o link do seu youtube!\n \n&fAtual: &c" + (name.isEmpty() ? "Nenhum" : name) + "\n \n&eClique para alterar! : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzNTNmZDBmODYzMTQzNTM4NzY1ODYwNzViOWJkZjBjNDg0YWFiMDMzMWI4NzJkZjExYmQ1NjRmY2IwMjllZCJ9fX0="));
      data = profile.getDataContainer("kCoreProfile", "instagram");
      name = data == null ? "" : data.getAsString();
      this.setItem(13, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&aInstagram : desc>&7Altere o link do seu instagram!\n \n&fAtual: &7" + (name.isEmpty() ? "Nenhum" : name) + "\n \n&eClique para alterar! : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjViM2YyY2ZhMDczOWM0ZTgyODMxNmYzOWY5MGIwNWJjMWY0ZWQyN2IxZTM1ODg4NTExZjU1OGQ0Njc1In19fQ=="));
      data = profile.getDataContainer("kCoreProfile", "discord");
      name = data == null ? "" : data.getAsString();
      this.setItem(15, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&aDiscord : desc>&7Altere o seu Discord!\n \n&fAtual: &9" + (name.isEmpty() ? "Nenhum" : name) + "\n \n&eClique para alterar! : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg3M2MxMmJmZmI1MjUxYTBiODhkNWFlNzVjNzI0N2NiMzlhNzVmZjFhODFjYmU0YzhhMzliMzExZGRlZGEifX19"));
      data = profile.getDataContainer("kCoreProfile", "twitch");
      name = data == null ? "" : data.getAsString();
      this.setItem(16, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&aTwitch : desc>&7Altere o link da sua Twitch!\n \n&fAtual: &5" + (name.isEmpty() ? "Nenhum" : name) + "\n \n&eClique para alterar! : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDZiZTY1ZjQ0Y2QyMTAxNGM4Y2RkZDAxNThiZjc1MjI3YWRjYjFmZDE3OWY0YzFhY2QxNThjODg4NzFhMTNmIn19fQ=="));
      this.setItem(31, BukkitUtils.deserializeItemStack("INK_SACK:1 : 1 : nome>&cVoltar"));
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
                  TextComponent component;
                  BaseComponent[] var5;
                  int var6;
                  int var7;
                  BaseComponent components;
                  TextComponent click;
                  BaseComponent[] var11;
                  int var12;
                  if (evt.getSlot() == 10) {
                     Listeners.SOCIAL_NETWORK.put(this.player.getName(), "twitter");
                     component = new TextComponent("");
                     var5 = TextComponent.fromLegacyText("\n§aDigite o seu novo link do seu Twitter no chat ou clique ");
                     var6 = var5.length;

                     for(var7 = 0; var7 < var6; ++var7) {
                        components = var5[var7];
                        component.addExtra(components);
                     }

                     click = new TextComponent("AQUI");
                     click.setColor(ChatColor.GREEN);
                     click.setBold(true);
                     click.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "social:cancel"));
                     click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Clique aqui para cancelar a operação.")));
                     component.addExtra(click);
                     var11 = TextComponent.fromLegacyText("§a para cancelar.\n ");
                     var7 = var11.length;

                     for(var12 = 0; var12 < var7; ++var12) {
                        components = var11[var12];
                        component.addExtra(components);
                     }

                     this.player.spigot().sendMessage(component);
                     this.player.closeInventory();
                  } else if (evt.getSlot() == 11) {
                     Listeners.SOCIAL_NETWORK.put(this.player.getName(), "youtube");
                     component = new TextComponent("");
                     var5 = TextComponent.fromLegacyText("\n§aDigite o seu novo link do seu Youtube no chat ou clique ");
                     var6 = var5.length;

                     for(var7 = 0; var7 < var6; ++var7) {
                        components = var5[var7];
                        component.addExtra(components);
                     }

                     click = new TextComponent("AQUI");
                     click.setColor(ChatColor.GREEN);
                     click.setBold(true);
                     click.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "social:cancel"));
                     click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Clique aqui para cancelar a operação.")));
                     component.addExtra(click);
                     var11 = TextComponent.fromLegacyText("§a para cancelar.\n ");
                     var7 = var11.length;

                     for(var12 = 0; var12 < var7; ++var12) {
                        components = var11[var12];
                        component.addExtra(components);
                     }

                     this.player.spigot().sendMessage(component);
                     this.player.closeInventory();
                  } else if (evt.getSlot() == 13) {
                     Listeners.SOCIAL_NETWORK.put(this.player.getName(), "instagram");
                     component = new TextComponent("");
                     var5 = TextComponent.fromLegacyText("\n§aDigite o seu novo link do seu Instagram no chat ou clique ");
                     var6 = var5.length;

                     for(var7 = 0; var7 < var6; ++var7) {
                        components = var5[var7];
                        component.addExtra(components);
                     }

                     click = new TextComponent("AQUI");
                     click.setColor(ChatColor.GREEN);
                     click.setBold(true);
                     click.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "social:cancel"));
                     click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Clique aqui para cancelar a operação.")));
                     component.addExtra(click);
                     var11 = TextComponent.fromLegacyText("§a para cancelar.\n ");
                     var7 = var11.length;

                     for(var12 = 0; var12 < var7; ++var12) {
                        components = var11[var12];
                        component.addExtra(components);
                     }

                     this.player.spigot().sendMessage(component);
                     this.player.closeInventory();
                  } else if (evt.getSlot() == 15) {
                     Listeners.SOCIAL_NETWORK.put(this.player.getName(), "discord");
                     component = new TextComponent("");
                     var5 = TextComponent.fromLegacyText("\n§aDigite o seu novo Discord no chat ou clique ");
                     var6 = var5.length;

                     for(var7 = 0; var7 < var6; ++var7) {
                        components = var5[var7];
                        component.addExtra(components);
                     }

                     click = new TextComponent("AQUI");
                     click.setColor(ChatColor.GREEN);
                     click.setBold(true);
                     click.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "social:cancel"));
                     click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Clique aqui para cancelar a operação.")));
                     component.addExtra(click);
                     var11 = TextComponent.fromLegacyText("§a para cancelar.\n ");
                     var7 = var11.length;

                     for(var12 = 0; var12 < var7; ++var12) {
                        components = var11[var12];
                        component.addExtra(components);
                     }

                     this.player.spigot().sendMessage(component);
                     this.player.closeInventory();
                  } else if (evt.getSlot() == 16) {
                     Listeners.SOCIAL_NETWORK.put(this.player.getName(), "twitch");
                     component = new TextComponent("");
                     var5 = TextComponent.fromLegacyText("\n§aDigite o seu novo link da sua Twitch no chat ou clique ");
                     var6 = var5.length;

                     for(var7 = 0; var7 < var6; ++var7) {
                        components = var5[var7];
                        component.addExtra(components);
                     }

                     click = new TextComponent("AQUI");
                     click.setColor(ChatColor.GREEN);
                     click.setBold(true);
                     click.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "social:cancel"));
                     click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Clique aqui para cancelar a operação.")));
                     component.addExtra(click);
                     var11 = TextComponent.fromLegacyText("§a para cancelar.\n ");
                     var7 = var11.length;

                     for(var12 = 0; var12 < var7; ++var12) {
                        components = var11[var12];
                        component.addExtra(components);
                     }

                     this.player.spigot().sendMessage(component);
                     this.player.closeInventory();
                  } else if (evt.getSlot() == 31) {
                     EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
                     new MenuProfile(profile);
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
