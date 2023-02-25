package hyrise.sa.database.data;

import hyrise.sa.database.tables.*;
import hyrise.sa.database.Database;
import hyrise.sa.database.data.interfaces.DataTableInfo;
import hyrise.sa.database.tables.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class DataTable {

  private static final List<DataTable> TABLES = new ArrayList<>();
  
  static {
    TABLES.add(new CoreTable());
    TABLES.add(new SkyWarsTable());
    TABLES.add(new BedWarsTable());
  }

  public static void registerTable(DataTable table) {
    TABLES.add(table);
  }

  public static Collection<DataTable> listTables() {
    return TABLES;
  }

  public abstract void init(Database database);

  public abstract Map<String, DataContainer> getDefaultValues();

  public DataTableInfo getInfo() {
    return this.getClass().getAnnotation(DataTableInfo.class);
  }
}
