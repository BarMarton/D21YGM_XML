package hu.domparse.D21YGM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomQueryD21YGM {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("XMLD21YGM.xml");

            // Normalizálás
            document.getDocumentElement().normalize();

            // Repülőgépek gyártója és típusa
            System.out.println("1. Lekérdezés");
            NodeList airplanes = document.getElementsByTagName("Repülő");
            for (int i = 0; i < airplanes.getLength(); i++) {
                Node airplane = airplanes.item(i);
                if (airplane.getNodeType() == Node.ELEMENT_NODE) {
                    Element airplaneElement = (Element) airplane;
                    String make = airplaneElement.getElementsByTagName("Gyártó").item(0).getTextContent();
                    String model = airplaneElement.getElementsByTagName("Típus").item(0).getTextContent();
                    System.out.println("Gyártó: " + make + ", Típus: " + model);
                }
            }
            System.out.println();

            // Repterek helye és IATA kódja
            System.out.println("2. Lekérdezés");
            NodeList airports = document.getElementsByTagName("Reptér");
            for (int i = 0; i < airports.getLength(); i++) {
                Node airport = airports.item(i);
                if (airport.getNodeType() == Node.ELEMENT_NODE) {
                    Element airportElement = (Element) airport;
                    String iata = airportElement.getAttribute("IATA");
                    String country = airportElement.getElementsByTagName("Ország").item(0).getTextContent();
                    String city = airportElement.getElementsByTagName("Város").item(0).getTextContent();
                    System.out.println("IATA: " + iata + ", Ország: " + country + ", Város: " + city);
                }
            }
            System.out.println();

            // Járatok amellyek Airbus gyártmányú repülővel történnek
            System.out.println("3. Lekérdezés");
            for (int i = 0; i < airplanes.getLength(); i++) {
                Node airplane = airplanes.item(i);
                if (airplane.getNodeType() == Node.ELEMENT_NODE) {
                    Element airplaneElement = (Element) airplane;
                    String make = airplaneElement.getElementsByTagName("Gyártó").item(0).getTextContent();
                    if ("Airbus".equalsIgnoreCase(make)) {
                        String callSign = airplaneElement.getAttribute("Hívójel");
                        NodeList routes = document.getElementsByTagName("Tartalmaz");
                        for (int j = 0; j < routes.getLength(); j++) {
                            Node route = routes.item(j);
                            if (route.getNodeType() == Node.ELEMENT_NODE) {
                                Element routeElement = (Element) route;
                                if (routeElement.getAttribute("Járat").equals(callSign)) {
                                    String start = routeElement.getAttribute("Start");
                                    String destination = routeElement.getAttribute("Cél");
                                    System.out.println("Hívójel: " + callSign + ", Gyártó: " + make + ", Start: " + start + ", Cél: " + destination);
                                }
                            }
                        }
                    }
                }
            }
            System.out.println();


            // 3400 méternél hosszabb kifutópályák
            System.out.println("4. Lekérdezés");
            NodeList runways = document.getElementsByTagName("Kifutópálya");
            for (int i = 0; i < runways.getLength(); i++) {
                Node runway = runways.item(i);
                if (runway.getNodeType() == Node.ELEMENT_NODE) {
                    Element runwayElement = (Element) runway;
                    int length = Integer.parseInt(runwayElement.getElementsByTagName("Hossz").item(0).getTextContent());
                    if (length > 3400) {
                        String runwayId = runwayElement.getAttribute("id");
                        String airportIATA = runwayElement.getAttribute("fid");
                        for (int j = 0; j < airports.getLength(); j++) {
                            Node airport = airports.item(j);
                            if (airport.getNodeType() == Node.ELEMENT_NODE) {
                                Element airportElement = (Element) airport;
                                if (airportElement.getAttribute("IATA").equals(airportIATA)) {
                                    String city = airportElement.getElementsByTagName("Város").item(0).getTextContent();
                                    System.out.println("Kifutópálya: " + runwayId + ", IATA: " + airportIATA + ", Város: " + city + ", Hossz: " + length);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
