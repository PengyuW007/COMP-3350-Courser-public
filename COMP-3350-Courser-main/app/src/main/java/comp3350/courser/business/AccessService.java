package comp3350.courser.business;

import comp3350.courser.persistence.IPersistenceAccess;
import comp3350.courser.persistence.PersistenceAccess;
import comp3350.courser.persistence.PersistenceAccessDB;
import comp3350.courser.presentation.MainActivity;

public class AccessService {
    private static IPersistenceAccess accessService = null;

    public static IPersistenceAccess createDataAccess(String dbName)
    {
        if (accessService == null)
        {
            accessService = new PersistenceAccessDB(dbName);
            accessService.open(MainActivity.getDBPathName());
        }
        return accessService;
    }

    public static IPersistenceAccess createDataAccess(IPersistenceAccess alternateDataAccessService)
    {
        if (accessService == null)
        {
            accessService = alternateDataAccessService;
            accessService.open(MainActivity.getDBPathName());
        }
        return accessService;
    }

    public static IPersistenceAccess getDataAccess(String dbName)
    {
        if (accessService == null)
        {
            throw new RuntimeException("Connection to data access has not been established.");
        }
        return accessService;
    }

    public static void closeDataAccess()
    {
        if (accessService != null)
        {
            accessService.close();
        }
        accessService = null;
    }
}
