# XML to JSON Converter and Attribute Extractor

This project provides a Java-based solution for converting XML files to JSON and extracting specific attribute values based on a configuration file. It is designed to handle XML files with namespaces and nested structures.

---

## Table of Contents

1. [Features](#features)
2. [Prerequisites](#prerequisites)
3. [Setup](#setup)
4. [Usage](#usage)
5. [Dependencies](#Dependencies)
6. [Contributions](#Contributions)
7. [License](#license)

---

## Features

- **XML to JSON Conversion**: Converts XML files to JSON format using Jackson's `XmlMapper`.
- **Attribute Extraction**: Extracts specific attribute values from the JSON structure based on a configuration file.
- **Namespace Support**: Handles XML files with namespaces.
- **Dynamic Configuration**: Allows users to specify attribute paths and names in a `config.properties` file.

---

## Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Maven**: For building and managing dependencies.
- **Jackson Library**: Included in the `pom.xml` for XML and JSON processing.

---

## Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Manaf-darabseh/XML2JSON-Extractor.git
   cd xml-to-json-converter


2. **Build the Project:**:
   ```bash
   mvn clean install 
   ```

2. **Run the Application:**
   ```bash
   mvn exec:java
   ```


---
## Usage
**Prepare the XML File:**
- Place your XML file in the (`src/main/resources directory`).
- Example: (`XMLResponseData-LEAD.xml`)


**Configure the (`config.properties`) File:**

- Specify the attributes to extract in the (`config.properties`) file.

---
## Dependencies
This project uses the following libraries:
- Jackson for JSON processing
- javax.xml for XML parsing
---
## License
This project is licensed under the MIT License.

---
## Contributions
Contributions are welcome! Feel free to open issues or submit pull requests.