package baellerry.utils;

import java.util.Map;

public class DriverProvider {

    private String browser;

    public String getDriver() {

        Map<String, String> map = PropertyResolver.getResource("browsers.properties");
        browser = map.get("browser");
        return browser;
    }
}
