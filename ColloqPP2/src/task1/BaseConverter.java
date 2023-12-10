package task1;

import java.util.Locale;

public class BaseConverter {
    private  double celsius;

    public BaseConverter() {
        this.celsius = celsius;
    }

    public double convert(String unit) {
        return switch (unit.toUpperCase()) {
            case "K", "KELVIN" -> celsius + 273.15;
            case "F", "FAHRENHEIT" -> celsius * 9 / 5 + 32;
            default -> throw new IllegalArgumentException("Unsupported unit: " + unit);
        };
    }

    public static BaseConverter createWithLocale(double celsius) {
        Locale locale = Locale.getDefault();
        String countryCode = locale.getCountry().toUpperCase();
        String[] fahrenheitCountries = {"BS", "US", "BZ", "KY", "PW"};

        for (String country : fahrenheitCountries) {
            if (countryCode.equals(country)) {
                return new FahrenheitConverter(celsius);
            }
        }

        return new BaseConverter();
    }
}
