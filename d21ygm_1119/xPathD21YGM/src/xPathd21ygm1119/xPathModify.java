package xPathd21ygm1119;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import java.io.File;
import java.io.StringWriter;

public class xPathModify {
    public static void main(String[] args) {
        try {
            File inputFile = new File("orarend_d21ygm.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList szakNodes = (NodeList) xPath.evaluate("//szak", doc, XPathConstants.NODESET);
            for (int i = 0; i < szakNodes.getLength(); i++) {
                szakNodes.item(i).setTextContent("BPI");
            }

            NodeList targyNodes = (NodeList) xPath.evaluate("//ora", doc, XPathConstants.NODESET);
            for (int i = 0; i < targyNodes.getLength(); i++) {
                Element oraElement = (Element) targyNodes.item(i);
                String oktato = oraElement.getElementsByTagName("oktato").item(0).getTextContent();
                String monogram = getMonogram(oktato);
                Node targyNode = oraElement.getElementsByTagName("targy").item(0);
                targyNode.setTextContent(targyNode.getTextContent() + " (" + monogram + ")");
            }

            Node helyszinNode = (Node) xPath.evaluate("//ora[@id='03']/helyszin", doc, XPathConstants.NODE);
            if (helyszinNode != null) {
                helyszinNode.setTextContent("XXXVII");
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("orarendD21YGM1.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            StringWriter writer = new StringWriter();
            transformer.transform(source, new StreamResult(writer));
            String output = writer.getBuffer().toString();
            System.out.println(output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getMonogram(String name) {
        String[] parts = name.split(" ");
        StringBuilder monogram = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                monogram.append(part.charAt(0));
            }
        }
        return monogram.toString().toUpperCase();
    }
}
