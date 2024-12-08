package domd21ygm1105;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class DomQueryD21YGMOrarend {
    public static void main(String[] args) {
        try {
            String filePath = "orarend_d21ygm.xml";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);
            document.getDocumentElement().normalize();

            NodeList oraList = document.getElementsByTagName("ora");
            List<String> targyOktatoList = new ArrayList<>();

            for (int i = 0; i < oraList.getLength(); i++) {
                Element oraElement = (Element) oraList.item(i);
                String targy = oraElement.getElementsByTagName("targy").item(0).getTextContent();
                String oktato = oraElement.getElementsByTagName("oktato").item(0).getTextContent();
                targyOktatoList.add("Targy: " + targy + ", Oktato: " + oktato);
            }

            targyOktatoList.forEach(System.out::println);

            if (oraList.getLength() > 0) {
                Element firstOra = (Element) oraList.item(0);

                String id = firstOra.getAttribute("id");
                String tipus = firstOra.getAttribute("típus");
                String targy = firstOra.getElementsByTagName("targy").item(0).getTextContent();
                String nap = firstOra.getElementsByTagName("nap").item(0).getTextContent();
                String tol = firstOra.getElementsByTagName("tol").item(0).getTextContent();
                String ig = firstOra.getElementsByTagName("ig").item(0).getTextContent();
                String helyszin = firstOra.getElementsByTagName("helyszin").item(0).getTextContent();
                String oktato = firstOra.getElementsByTagName("oktato").item(0).getTextContent();

                System.out.println("ID: " + id);
                System.out.println("Tipus: " + tipus);
                System.out.println("Targy: " + targy);
                System.out.println("Nap: " + nap);
                System.out.println("Tol: " + tol);
                System.out.println("Ig: " + ig);
                System.out.println("Helyszin: " + helyszin);
                System.out.println("Oktato: " + oktato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

