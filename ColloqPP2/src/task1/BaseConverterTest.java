package task1;

import org.junit.Test;

import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
public class BaseConverterTest {
    @Test
    public void testConvertToKelvin() {
        BaseConverter converter = new BaseConverter();
        assertEquals(298.15, converter.convert("K"), 0.001);
        assertEquals(298.15, converter.convert("kelvin"), 0.001);
    }

    @Test
    public void testConvertToFahrenheit() {
        BaseConverter converter = new BaseConverter();
        assertEquals(77, converter.convert("F"), 0.001);
        assertEquals(77, converter.convert("fahrenheit"), 0.001);
    }

    @Test
    public void testConvertUnsupportedUnit() {
        BaseConverter converter = new BaseConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert("unsupported"));
    }

    @Test
    public void testCreateConverter_US() {
        Locale.setDefault(new Locale("", "US"));
        BaseConverter converter = BaseConverter.createWithLocale(25);
        assertEquals(FahrenheitConverter.class, converter.getClass());
    }

    @Test
    public void testCreateConverter_RU() {
        Locale.setDefault(new Locale("", "RU"));
        BaseConverter converter = BaseConverter.createWithLocale(25);
        assertEquals(BaseConverter.class, converter.getClass());
    }

    @Test
    public void testCreateConverter_Default() {
        Locale.setDefault(new Locale("", ""));
        BaseConverter converter = BaseConverter.createWithLocale(25);
        assertEquals(BaseConverter.class, converter.getClass());
    }
}