package cn.lncsa.common;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by cattenlinger on 2016/9/28.
 */
public class ListToolsTest {
    @Test
    public void listDiff() throws Exception {

        List<String> list1 = Arrays.asList(
                "Tag 1",
                "Tag 2",
                "Tag 3",
                "Tag 4"
        );

        List<String> list2 = Arrays.asList(
                "Tag 3",
                "Tag 4",
                "Tag 5",
                "Tag 6"
        );

        List<String>[] result = ListTools.listDiff(list1,list2);

        List<String> addList = result[ListTools.LIST_ADD];
        List<String> delList = result[ListTools.LIST_DELETE];

        System.out.println("List add :");
        for (String s : addList) System.out.println(s + " ");
        System.out.println("List del :");
        for (String s : delList) System.out.println(s + " ");

    }

}