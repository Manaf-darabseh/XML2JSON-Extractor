package QA;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class XmlParser {

    /**
     * Converts an XML file to a JsonNode.
     *
     * @param xmlFilePath The path to the XML file.
     * @return A JsonNode representing the XML structure.
     * @throws IOException If an error occurs while reading the XML file.
     */
    public JsonNode parseXmlFileToJson(String xmlFilePath) throws IOException {
        // Create an XmlMapper instance to read XML
        XmlMapper xmlMapper = new XmlMapper();
        // Read the XML file and convert it to a JsonNode
        return xmlMapper.readTree(new File(xmlFilePath))
                .path("Body") // Navigate to the Body node
                .path("ProcessRequestResponse") // Navigate to the ProcessRequestResponse node
                .path("Response") // Navigate to the Response node
                .path("responseData"); // Navigate to the responseData node
    }

    /**
     * Retrieves the value of a nested attribute from a JsonNode.
     *
     * @param jsonNode      The JsonNode to traverse.
     * @param parentPath    The path to the parent node of the attribute.
     * @param attributeName The name of the attribute to retrieve.
     * @return The value of the attribute, or null if the attribute is not found.
     */
    public String extractAttributeValueFromJson(JsonNode jsonNode, String parentPath, String attributeName) {
        JsonNode currentNode = jsonNode;
        // Split the parent path into individual components
        String[] pathSegments = parentPath.split("/");

        // Traverse the JSON structure based on the parent path
        for (String pathSegment : pathSegments) {
            if (pathSegment.isEmpty()) continue; // Skip empty paths

            // Handle array indices (e.g., "2")
            if (pathSegment.matches("\\d+")) {
                int arrayIndex = Integer.parseInt(pathSegment);
                if (currentNode.isArray() && currentNode.size() > arrayIndex) {
                    // Navigate to the specified index in the array
                    currentNode = currentNode.get(arrayIndex);
                } else {
                    // Handle out-of-bounds array indices
                    System.out.println("Array index out of bounds: " + pathSegment);
                    return null;
                }
            } else {
                // Navigate to the specified node
                currentNode = currentNode.path(pathSegment);
            }

            // Handle missing nodes
            if (currentNode.isMissingNode()) {
                System.out.println("Path not found: " + pathSegment);
                return null;
            }
        }

        // Get the value of the specified attribute
        JsonNode attributeValueNode = currentNode.path(attributeName);
        if (attributeValueNode.isMissingNode()) {
            // Handle missing attributes
            System.out.println("Attribute not found: " + attributeName);
            return null;
        }
        return attributeValueNode.asText();
    }
}