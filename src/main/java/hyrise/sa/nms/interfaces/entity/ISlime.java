package hyrise.sa.nms.interfaces.entity;

import hyrise.sa.libraries.holograms.api.HologramLine;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Slime;

public interface ISlime {
  
  void setPassengerOf(Entity entity);
  
  void setLocation(double x, double y, double z);
  
  boolean isDead();
  
  void killEntity();
  
  Slime getEntity();
  
  HologramLine getLine();
}
