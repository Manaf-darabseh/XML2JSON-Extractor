package QA;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader {

    /**
     * Loads properties from a configuration file.
     *
     * @param configFilePath The path to the configuration file.
     * @return A Properties object containing the configuration.
     * @throws IOException If an error occurs while reading the file.
     */
    public Properties loadPropertiesFromFile(String configFilePath) throws IOException {
        Properties properties = new Properties();
        try (InputStream configFileStream = new FileInputStream(configFilePath)) {
            // Load the properties from the input stream
            properties.load(configFileStream);
        }
        return properties;
    }

    /**
     * Extracts attribute configurations from the properties.
     *
     * @param properties The Properties object containing the configuration.
     * @return A list of maps, where each map contains the parent path and attribute name for an attribute.
     */
    public List<Map<String, String>> extractAttributeConfigurations(Properties properties) {
        List<Map<String, String>> attributeConfigurations = new ArrayList<>();
        // Get the total number of attributes from the properties
        int totalAttributes = Integer.parseInt(properties.getProperty("totalAttributes", "0"));

        // Loop through each attribute and extract its parent path and attribute name
        for (int attributeIndex = 1; attributeIndex <= totalAttributes; attributeIndex++) {
            String parentPath = properties.getProperty("attribute" + attributeIndex + ".parentPath");
            String attributeName = properties.getProperty("attribute" + attributeIndex + ".attributeName");
            if (parentPath != null && attributeName != null) {
                // Add the attribute configuration to the list
                attributeConfigurations.add(Map.of("parentPath", parentPath, "attributeName", attributeName));
            }
        }
        return attributeConfigurations;
    }
}