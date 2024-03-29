package baellerry.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PropertyResolver {

    public static Map<String, String> getResource(String resource) {
        URL systemResource = ClassLoader.getSystemResource(resource);
        String path = systemResource.getPath();
        try {
            Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
            return lines
                    .map(line -> line.split("="))
                    .collect(Collectors.toMap(arg -> arg[0], arg -> arg[1]));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not found");
        }
    }
}
