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

public class DomModifyD21YGMOrarend {
    public static void main(String[] args){
        try{
            File inputFile = new File("orarend_d21ygm.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);

            Node orarend = doc.getFirstChild();
            NodeList ora = doc.getElementsByTagName("ora");

            Element node = doc.createElement("oraado");
            node.setTextContent("Példa Béla");

            ora.item(0).appendChild(node);

            for (int i = 0; i < ora.getLength(); i++){
                NamedNodeMap attr = ora.item(i).getAttributes();
                Node nodeAttr = attr.getNamedItem("típus");

                if(nodeAttr.getTextContent().equals("Gyakorlat")){
                    nodeAttr.setTextContent("Előadás");
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
