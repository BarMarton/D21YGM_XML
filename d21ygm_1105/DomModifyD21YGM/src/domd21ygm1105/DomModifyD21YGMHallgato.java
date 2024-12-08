package domd21ygm1105;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DomModifyD21YGMHallgato {
    public static void main(String[] args){
        try{
            File inputFile = new File("hallgato_d21ygm.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(inputFile);
            Node hallgatok = doc.getFirstChild();
            Node hallgat = doc.getElementsByTagName("hallgato").item(0);

            NamedNodeMap attr = hallgat.getAttributes();
            Node nodeAttr = attr.getNamedItem("id");

            nodeAttr.setTextContent("01");
            NodeList list = hallgat.getChildNodes();

            for (int temp = 0; temp < list.getLength(); temp++){
                Node node = list.item(temp);

                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) node;

                    if("keresztnev".equals(eElement.getNodeName())) {
                        if("Pál".equals(eElement.getTextContent())){
                            eElement.setTextContent("Olivia");
                        }
                    }

                    if("vezeteknev".equals(eElement.getNodeName())) {
                        if("Kiss".equals(eElement.getTextContent())) {
                            eElement.setTextContent("Vigh");
                        }
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            System.out.println("--- Módosított file ---");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
