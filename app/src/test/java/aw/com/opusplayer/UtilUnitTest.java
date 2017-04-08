package aw.com.opusplayer;

import org.junit.Test;

import aw.com.utils.Converters;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilUnitTest {
    @Test
    public void testNumberToTimeConverter() {
        assertEquals(Converters.convertNumberToTimeDisplay(90), "01:30");
        assertEquals(Converters.convertNumberToTimeDisplay(120), "02:00");
        assertEquals(Converters.convertNumberToTimeDisplay(61), "01:01");
        assertEquals(Converters.convertNumberToTimeDisplay(59), "00:59");
    }
}