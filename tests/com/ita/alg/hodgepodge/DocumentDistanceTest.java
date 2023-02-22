package com.ita.alg.hodgepodge;

import org.junit.Test;

public class DocumentDistanceTest {
    @Test
    public void TestDocumentDistance() {
        EnglishDocumentDistance distance = new EnglishDocumentDistance();
        distance.setDocument1("The dog.");
        distance.setDocument2("The cat.");
        assert distance.calculate() == 0.25;
    }

}
