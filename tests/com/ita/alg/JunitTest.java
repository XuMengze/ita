package com.ita.alg;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

public class JunitTest {
    @EnabledIfSystemProperty(named = "domain", matches = ".*gamma")
    @EnabledIfSystemProperty(named = "realm", matches = ".*cn-northwest-1")
    @Test
    public void test1(){
        System.out.println("Hello");
    }
}
