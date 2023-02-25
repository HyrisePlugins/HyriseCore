package hyrise.sa.database.data.container;

import hyrise.sa.database.data.DataContainer;
import hyrise.sa.database.data.interfaces.AbstractContainer;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class SelectedContainer extends AbstractContainer {
  
  public SelectedContainer(DataContainer dataContainer) {
    super(dataContainer);
  }
  
  public void setIcon(String id) {
    JSONObject selected = this.dataContainer.getAsJsonObject();
    selected.put("icon", id);
    this.dataContainer.set(selected.toString());
    selected.clear();
  }
}
