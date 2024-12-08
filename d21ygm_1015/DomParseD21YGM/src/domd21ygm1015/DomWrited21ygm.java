package domd21ygm1022;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


public class DomWrited21ygm {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("orarend_d21ygm.xml");
            document.getDocumentElement().normalize();

            printNode(document.getDocumentElement(), 0);

            writeToFile(document, "orarend1_d21ygm.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printNode(Node node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.print("<" + node.getNodeName());

            NamedNodeMap attributes = node.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                System.out.print(" " + attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
            }
            System.out.println(">");

            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                printNode(children.item(i), level + 1);
            }

            for (int i = 0; i < level; i++) {
                System.out.print("  ");
            }
            System.out.println("</" + node.getNodeName() + ">");
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            String content = node.getTextContent().trim();
            if (!content.isEmpty()) {
                System.out.println(content);
            }
        }
    }
    private static void writeToFile(Document document, String filePath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath));

            transformer.transform(source, result);

            System.out.println("XML contents written to " + filePath);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}