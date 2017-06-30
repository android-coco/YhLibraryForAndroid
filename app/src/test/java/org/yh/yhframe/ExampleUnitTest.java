package org.yh.yhframe;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
    @Test
    public void addition_isCorrect() throws Exception
    {
        //assertEquals(4, 2 + 2);
        byte[] x = new byte[]{0x32};
        switch (x[0])
        {
            case 50:
                System.out.print("adfasdfad");
                break;
        }
    }
}