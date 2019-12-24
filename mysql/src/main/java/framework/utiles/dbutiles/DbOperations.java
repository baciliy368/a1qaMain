package framework.utiles.dbutiles;

import framework.sqlconnection.DataBaseConnection;
import java.util.List;

public class DbOperations {

    public static <T> List<T> executeQuery(String query, Class<T> modelClass) {
        return DataBaseConnection.getInstance().getConnection()
                .createQuery(query).executeAndFetch(modelClass);
    }
}
