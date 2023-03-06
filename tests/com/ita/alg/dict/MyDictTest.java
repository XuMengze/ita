package com.ita.alg.dict;

import org.junit.Test;

public class MyDictTest {
    @Test
    public void testMyDictBasic() {
        MyDict<String, Integer> my = new MyDict<>(1); // initial array length 4
        my.put("item1", 1);
        my.put("item2", 2);
        my.put("item3", 3);
        my.put("item4", 4);
        my.put("item5", 5);
        assert my.get("item2") == 2;
        assert my.get("item3") == 3;
        my.delete("item3");
        assert my.get("item3") == null;
    }

    @Test
    public void testMyDictExpand() {
        MyDict<String, Integer> my = new MyDict<>(1); // initial array length 4
        my.put("item1", 1);
        my.put("item2", 2);
        my.put("item3", 3);
        my.put("item4", 4);
        my.put("item5", 5);
        my.put("item6", 6);
        my.put("item7", 7);
        my.put("item8", 8);
        my.put("item9", 9);
    }
}
