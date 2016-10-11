package cn.lncsa.common;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by catten on 10/11/16.
 */
public class ResultObjectTest {

    @Test
    public void jsonResultPrint(){
        String jsonStrings[] = new String[]{
                JSON.toJSONString(new ResultObject(true)),
                JSON.toJSONString(new ResultObject(true,"some text")),
                JSON.toJSONString(new ResultObject(true,"some text",new String[]{"1","2","3","4"}))
        };
        String assertKeywords[] = new String[]{
                "success",
                "message",
                "content"
        };

        for (int i = 0; i < jsonStrings.length; i++){
            assertTrue(jsonStrings[i].contains(assertKeywords[i]));
            System.out.println(jsonStrings[i]);
        }
    }

}