package com.mediadriver.atlas.converters.v2;

import com.mediadriver.atlas.api.v2.AtlasUnsupportedException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 3/7/17.
 */
public class LongConverterTest {
    private LongConverter converter = new LongConverter();

    @Test
    public void convertToBoolean() throws Exception {
        Long aLong = 0L;
        Long l = 1L;

        Boolean b = converter.convertToBoolean(l);
        assertNotNull(b);
        assertTrue(b);

        b = converter.convertToBoolean(aLong);
        assertNotNull(b);
        assertFalse(b);

    }

    @Test
    public void convertToBoolean_Null() throws Exception {
        Long l = null;
        Boolean b = converter.convertToBoolean(l);
        assertNull(b);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToBoolean_Exception() throws Exception {
        Long dt = -1L;
        Boolean b = converter.convertToBoolean(dt);
    }

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToByte() throws Exception {
        Long l = 0L;
        Byte b = converter.convertToByte(l);
    }

    @Test
    public void convertToByte_Null() throws Exception {
        assertNull(converter.convertToByte(null));
    }

    @Test
    public void convertToCharacter() throws Exception {
        Long l = 0L;
        Character c = converter.convertToCharacter(l);
        assertNotNull(c);
        assertEquals(0, c.charValue());
    }

    @Test
    public void convertToCharacter_Null() throws Exception {
        Long l = null;
        Character c = converter.convertToCharacter(l);
        assertNull(c);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToCharacter_MAX() throws Exception {
        Long l = Long.MAX_VALUE;
        Character c = converter.convertToCharacter(l);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToCharacter_MIN() throws Exception {
        Long l = -1L;
        Character c = converter.convertToCharacter(l);
    }


    @Test
    public void convertToDouble() throws Exception {
        Long l = 0L;
        Double d = converter.convertToDouble(l);
        assertNotNull(d);
        assertEquals(0.0, d, 0.0);
    }

    @Test
    public void convertToDouble_Null() throws Exception {
        Long l = null;
        Double d = converter.convertToDouble(l);
        assertNull(d);
    }

    @Test
    public void convertToDouble_MAX() throws Exception {
        Long l = Long.MAX_VALUE;
        Double d = converter.convertToDouble(l);
        assertNotNull(d);
        assertEquals(Long.MAX_VALUE, l, 0.0);
    }

    @Test
    public void convertToFloat() throws Exception {
        Long l = 0l;
        Float f = converter.convertToFloat(l);
        assertNotNull(f);
        assertEquals(0.0, f, 0.0);

        l = 1l;
        f = converter.convertToFloat(l);
        assertNotNull(f);
        assertEquals(1.0f, f, 0.0);
    }

    @Test
    public void convertToFloat_Null() throws Exception {
        assertNull(converter.convertToFloat(null));
    }

    @Test
    public void convertToFloat_MAX() throws Exception {
        Long l = Long.MAX_VALUE;
        Float f = converter.convertToFloat(l);
        assertNotNull(f);
        assertEquals(Long.MAX_VALUE, l, 0.0);
    }

    @Test
    public void convertToInteger() throws Exception {
        Long l = 0L;
        Integer i = converter.convertToInteger(l);
        assertNotNull(i);
        assertEquals(0, i, 0.0);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToInteger_MAX() throws Exception {
        Integer i = converter.convertToInteger(Long.MAX_VALUE);
        fail();
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToInteger_MIN() throws Exception {
        Integer i = converter.convertToInteger(Long.MIN_VALUE);
        fail();
    }

    @Test
    public void convertToInteger_Null() throws Exception {
        Long l = null;
        Integer i = converter.convertToInteger(l);
        assertNull(i);
    }

    @Test
    public void convertToLong() throws Exception {
        Long l = 1L;
        Long d = converter.convertToLong(l);
        assertNotNull(d);
        assertNotSame(l, d);
        assertEquals(1L, d, 0.0);
    }

    @Test
    public void convertToLong_Null() throws Exception {
        Long l = null;
        Long d = converter.convertToLong(l);
        assertNull(d);
    }


    @Test
    public void convertToShort() throws Exception {
        Long l = 0L;
        Short s = converter.convertToShort(l);
        assertNotNull(s);
        assertEquals(0, s, 0.0);
    }

    @Test
    public void convertToShort_Null() throws Exception {
        Long l = null;
        Short s = converter.convertToShort(l);
        assertNull(s);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToShort_ExceptionMAX() throws Exception {
        Long l = Long.MAX_VALUE;
        Short s = converter.convertToShort(l);
    }

    @Test
    public void convertToString() throws Exception {
        Long l = 0L;
        String s = converter.convertToString(l);
        assertNotNull(s);
        assertTrue("0".equals(s));
    }

    @Test
    public void convertToString_Null() throws Exception {
        Long l = null;
        String s = converter.convertToString(l);
        assertNull(s);
    }
}