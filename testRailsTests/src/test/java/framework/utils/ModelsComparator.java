package framework.utils;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;

public class ModelsComparator {

    public static boolean isEquals(Object first, Object second) {
        Javers javers = JaversBuilder.javers().build();
        return !javers.compare(first, second).hasChanges();
    }
}
