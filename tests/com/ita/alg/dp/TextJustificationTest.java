package com.ita.alg.dp;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.Arrays;

public class TextJustificationTest {
    @Test
    public void test(){
//        String text = """
//        Although the deaths have been spread across generations, they have not been spread across the income spectrum. Life expectancy has fallen among the poorest in society but risen for the richest. A poor English girl could on average expect to live 6.8 years less than a rich girl in 2011, but 7.7 less in 2017. For boys, the gap increased from 9.1 to 9.5 years over the same period. The combined effect of the pandemic and global demographic trends can explain only some of Britain’s missing multitude. Though other rich countries have also experienced slowdowns, Britain has done the worst out of a cohort of its European peers. Working out what has gone wrong is not easy. In America, where life expectancy has fallen even more sharply in recent years, "deaths of despair" from drugs, alcohol and suicide have done the most harm. The same is true for Scotland, where drug deaths have more than doubled in a decade; Dundee is now the drug-death capital of Europe. Yet although a similar problem may be brewing in England and Wales, the rate of drug deaths is nearly four times higher in Scotland.
//        """;
        String text = "Although the deaths have been spread across generations, they have not been spread across the income spectrum.";
        TextJustification s = new TextJustification(text);
        System.out.println(Arrays.toString(s.getPartition(100)));
    }
}
