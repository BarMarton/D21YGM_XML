package domd21ygm1105;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DomQueryD21YGMHallgato {
    public static void main(String[] args) {
        try {
            String filePath = "hallgato_d21ygm.xml";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("vezeteknev");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                System.out.println(element.getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

