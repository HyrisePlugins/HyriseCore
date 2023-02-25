package hyrise.sa.libraries.npclib.npc;

import hyrise.sa.libraries.npclib.api.EntityController;
import hyrise.sa.libraries.npclib.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public abstract class AbstractEntityController implements EntityController {
  
  private Entity bukkitEntity;
  
  protected abstract Entity createEntity(Location location, NPC npc);
  
  @Override
  public void spawn(Location location, NPC npc) {
    this.bukkitEntity = createEntity(location, npc);
  }
  
  @Override
  public void remove() {
    if (this.bukkitEntity != null) {
      this.bukkitEntity.remove();
      this.bukkitEntity = null;
    }
  }
  
  @Override
  public Entity getBukkitEntity() {
    return this.bukkitEntity;
  }
}
