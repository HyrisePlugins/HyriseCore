package hyrise.sa.nms.interfaces.entity;

import hyrise.sa.libraries.holograms.api.HologramLine;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public interface IItem {
  
  void setPassengerOf(Entity entity);
  
  void setItemStack(ItemStack item);
  
  void setLocation(double x, double y, double z);
  
  boolean isDead();
  
  void killEntity();
  
  Item getEntity();
  
  HologramLine getLine();
}
