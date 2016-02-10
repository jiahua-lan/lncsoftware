package cn.lncsoftware.data.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by catten on 16/2/9.
 */
public class StringTools {
    public static List<String> splitTags(String tags){
        return (tags == null || tags.equals("")) ? null : Arrays.asList(tags.split(";"));
    }

    public static String listTags(List<String> tags){
        StringBuilder result = new StringBuilder();
        if(tags == null) return "";
        for(String s : tags) result.append(tags).append(";");
        return result.toString();
    }
}
