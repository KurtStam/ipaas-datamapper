package com.mediadriver.atlas.converters.v2;

import com.mediadriver.atlas.api.v2.AtlasUnsupportedException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created  on 3/7/17.
 */

public class BooleanConverterTest {

    private BooleanConverter converter = new BooleanConverter();

    @Test
    public void convertToBoolean() throws Exception {
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        Boolean t2 = converter.convertToBoolean(t);
        Boolean f2 = converter.convertToBoolean(f);
        assertNotNull(t2);
        assertNotSame(t2, t);
        assertTrue(t2);
        assertNotNull(f2);
        assertNotSame(f2, f);
        assertFalse(f2);
    }

    @Test
    public void convertToBoolean_Null() throws Exception {
        Boolean t = null;
        Boolean t2 = converter.convertToBoolean(t);
        assertNull(t2);
    }

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToByte() throws Exception {
        converter.convertToByte(Boolean.TRUE);
    }

    @Test
    public void convertToByte_Null() throws Exception {
        assertNull(converter.convertToByte(null));
    }

    @Test
    public void convertToCharacter() throws Exception {
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        Character c = converter.convertToCharacter(t);
        assertNotNull(c);
        assertEquals(1, c.charValue());
        c = converter.convertToCharacter(f);
        assertNotNull(c);
        assertEquals(0, c.charValue());
    }

    @Test
    public void convertToCharacter_Null() throws Exception {
        Boolean t = null;
        Character c = converter.convertToCharacter(t);
        assertNull(c);
    }

    @Test
    public void convertToDouble() throws Exception {
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        Double d = converter.convertToDouble(t);
        assertNotNull(d);
        assertEquals(1, d, 0.0d);
        d = converter.convertToDouble(f);
        assertNotNull(d);
        assertEquals(0, d, 0.0d);

    }

    @Test
    public void convertToDouble_Null() throws Exception {
        Boolean t = null;
        Double d = converter.convertToDouble(t);
        assertNull(d);
    }

    @Test
    public void convertToFloat() throws Exception {
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        Float aFloat = converter.convertToFloat(t);
        assertNotNull(aFloat);
        assertEquals(1, aFloat, 0.0f);
        aFloat = converter.convertToFloat(f);
        assertNotNull(aFloat);
        assertEquals(0, aFloat, 0.0f);
    }

    @Test
    public void convertToFloat_Null() throws Exception {
        Boolean t = null;
        Float f = converter.convertToFloat(t);
        assertNull(f);
    }

    @Test
    public void convertToInteger() throws Exception {
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        Integer i = converter.convertToInteger(t);
        assertNotNull(i);
        assertEquals(1, i.intValue());
        i = converter.convertToInteger(f);
        assertNotNull(i);
        assertEquals(0, i.intValue());
    }

    @Test
    public void convertToInteger_Null() throws Exception {
        Boolean t = null;
        Integer i = converter.convertToInteger(t);
        assertNull(i);
    }

    @Test
    public void convertToLong() throws Exception {
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        Long l = converter.convertToLong(t);
        assertNotNull(l);
        assertEquals(1, l.longValue());
        l = converter.convertToLong(f);
        assertNotNull(l);
        assertEquals(0, l.longValue());
    }

    @Test
    public void convertToLong_Null() throws Exception {
        Boolean t = null;
        Long l = converter.convertToLong(t);
        assertNull(l);
    }

    @Test
    public void convertToShort() throws Exception {
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        Short s = converter.convertToShort(t);
        assertNotNull(s);
        assertEquals(1, s.shortValue());
        s = converter.convertToShort(f);
        assertNotNull(s);
        assertEquals(0, s.shortValue());
    }

    @Test
    public void convertToShort_Null() throws Exception {
        Boolean t = null;
        Short s = converter.convertToShort(t);
        assertNull(s);
    }

    @Test
    public void convertToString() throws Exception {
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        String s = converter.convertToString(t);
        assertNotNull(s);
        assertTrue("true".equals(s));
        s = converter.convertToString(f);
        assertNotNull(s);
        assertTrue("false".equals(s));
    }

    @Test
    public void convertToString_Null() throws Exception {
        Boolean t = null;
        String s = converter.convertToString(t);
        assertNull(s);
    }

}