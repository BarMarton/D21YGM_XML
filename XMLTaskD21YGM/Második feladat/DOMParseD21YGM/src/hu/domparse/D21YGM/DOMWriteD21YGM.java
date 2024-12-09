package hu.domparse.D21YGM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class DOMWriteD21YGM {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("XMLD21YGM.xml");

            // Normalizálás
            document.getDocumentElement().normalize();

            // Gyökér elem kiválasztása
            Element root = document.getDocumentElement();
            System.out.println("Gyökér elem: " + root.getNodeName());

            // Konzolra írás
            printNode(root, 0);
            //Új fájlba mentés
            writeDocumentToFile(document, "XMLD21YGM1.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printNode(Node node, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.print("<" + node.getNodeName());

            if (node.hasAttributes()) {
                for (int i = 0; i < node.getAttributes().getLength(); i++) {
                    Node attribute = node.getAttributes().item(i);
                    System.out.print(" " + attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
                }
            }
            System.out.println(">");
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            String text = node.getNodeValue().trim();
            if (!text.isEmpty()) {
                System.out.println(text);
            }
        }

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            printNode(children.item(i), indent + 1);
        }

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            for (int i = 0; i < indent; i++) {
                System.out.print("  ");
            }
            System.out.println("</" + node.getNodeName() + ">");
        }
    }
    private static void writeDocumentToFile(Document document, String outputFilePath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(outputFilePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

