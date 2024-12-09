package xPathd21ygm1119;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.File;

public class xPathD21YGM {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("studentD21YGM.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            String[] expressions = {
                    "/class/student", // 1
                    "/class/student[@id='02']", // 2
                    "//student", // 3
                    "/class/student[2]", // 4
                    "/class/student[last()]", // 5
                    "/class/student[position()<3]", // 6
                    "/class/student[last()-1]", // 7
                    "/class/*", // 8
                    "/class/student[@*]", // 9
                    "//*", // 10
                    "/class/student[kor > 20]" // 11
            };

            for (int i = 0; i < expressions.length; i++) {
                System.out.println("XPath " + (i + 1) + ": " + expressions[i]);
                NodeList nodeList = (NodeList) xpath.evaluate(expressions[i], doc, XPathConstants.NODESET);

                for (int j = 0; j < nodeList.getLength(); j++) {
                    Node node = nodeList.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println(node.getNodeName() + node.getTextContent().trim());
                    }
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
