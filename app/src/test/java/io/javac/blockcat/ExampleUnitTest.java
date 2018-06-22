package io.javac.blockcat;

import org.junit.Test;

import java.math.BigInteger;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        BigInteger bigInteger = new BigInteger("1159757680553507691427152451166182780670099264813521424810016825014284986887");
        String string = bigInteger.toString(16);
        System.out.println(string);

    }
}