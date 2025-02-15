package QA;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Main {

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Define the paths to the XML file and configuration file
        String xmlFilePath = System.getProperty("user.dir") + "/src/main/resources/XMLResponseData-LEAD.xml";
        String configFilePath = System.getProperty("user.dir") + "/src/main/resources/config.properties";

        // Create instances of XmlParser and PropertiesLoader
        XmlParser xmlParser = new XmlParser();
        PropertiesLoader propertiesLoader = new PropertiesLoader();

        try {
            // Step 1: Convert the XML file to a JsonNode
            JsonNode jsonData = xmlParser.parseXmlFileToJson(xmlFilePath);

            // Step 2: Load the configuration properties
            Properties properties = propertiesLoader.loadPropertiesFromFile(configFilePath);
            // Get the list of attribute configurations from the properties
            List<Map<String, String>> attributeConfigurations = propertiesLoader.extractAttributeConfigurations(properties);

            // Step 3: Retrieve and print the values of the specified attributes
            for (Map<String, String> attributeConfig : attributeConfigurations) {
                String attributeValue = xmlParser.extractAttributeValueFromJson(
                        jsonData,
                        attributeConfig.get("parentPath"),
                        attributeConfig.get("attributeName")
                );
                // Print the attribute name and its value
                System.out.println(attributeConfig.get("attributeName") + ": " + attributeValue);
            }

        } catch (IOException e) {
            // Handle any IO errors that occur during file processing
            System.err.println("Error processing files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}