package framework.utils;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.PropertyChange;

public class ModelsComparator {

    private static <T, K> Diff getDiffFromCompare(T first, K second) {
        Javers javers = JaversBuilder.javers().build();
        return javers.compare(first, second);
    }

    public static <T, K> boolean areEquals(T first, K second) {
        Diff diff = getDiffFromCompare(first, second);
        if (diff.getChangesByType(PropertyChange.class).size() == 0) {
            return true;
        } else {
            Log.info(diff.toString());
            return false;
        }
    }
}
