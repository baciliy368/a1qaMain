package tests.utiles;

import framework.enums.TypesOfSort;
import framework.utils.Log;
import models.posts.PostModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PostsManager {

    public static boolean isArrayOfPostsSortedById(TypesOfSort sortType, PostModel[] posts) {
        ArrayList<Integer> trueIdArray = new ArrayList<>();
        ArrayList<Integer> realIdArray = new ArrayList<>();
        final List<PostModel> postModels = Arrays.asList(posts);
        switch (sortType) {
            case DESCENDING:
                postModels.forEach(post -> realIdArray.add(post.getId()));
                realIdArray.stream().sorted(Comparator.reverseOrder()).forEach(trueIdArray::add);
                return Arrays.equals(realIdArray.toArray(), trueIdArray.toArray());
            case ASCENDING:
                postModels.forEach(post -> realIdArray.add(post.getId()));
                realIdArray.stream().sorted().forEach(trueIdArray::add);
                return Arrays.equals(realIdArray.toArray(), trueIdArray.toArray());
            default:
                EnumConstantNotPresentException enumConstantNotPresentException
                        = new EnumConstantNotPresentException(TypesOfSort.class, sortType.toString());
                Log.error(Arrays.toString(enumConstantNotPresentException.getStackTrace()));
                throw enumConstantNotPresentException;
        }
    }
}
