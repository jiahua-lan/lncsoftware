package cn.lncsa.common;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by cattenlinger on 2016/9/28.
 */
public class ListTools {

    public static int LIST_ADD = 0;
    public static int LIST_DELETE = 1;

    /**
     * Check differences between two list
     *
     * @param originList   The list before modified
     * @param modifiedList The list that was modified
     * @param <T>          What type the Objects contained in two list using.
     * @return An array contains "Added Items List" and "Deleted Items List"
     * LIST_ADD : Added
     * LIST_DELETED : Deleted
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T>[] listDiff(List<T> originList, List<T> modifiedList) {
        if (originList != null && modifiedList != null) {

            Set<T> set1 = new HashSet<>();
            for (T o : originList) set1.add(o);
            Set<T> set2 = new HashSet<>();
            for (T o : modifiedList) set2.add(o);

            List<T> addList = new LinkedList<>();
            List<T> delList = new LinkedList<>();

            for (T o : set1) if (!set2.contains(o)) delList.add(o);
            for (T o : set2) if (!set1.contains(o)) addList.add(o);

            return new List[]{addList, delList};
        }
        return null;
    }
}
