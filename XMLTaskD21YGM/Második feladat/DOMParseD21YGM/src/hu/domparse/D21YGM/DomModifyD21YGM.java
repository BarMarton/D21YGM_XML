package hu.domparse.D21YGM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyD21YGM {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("XMLD21YGM.xml");

            // Normalizálás
            document.getDocumentElement().normalize();

            // Módosítások végrehajtása
            modifyAirplaneModel(document);
            modifyCaptainNames(document);
            modifyFlightRoutes(document);
            modifyFlightDates(document);

            // Gyökér elem kiválasztása
            Element root = document.getDocumentElement();
            System.out.println("Gyökér elem: " + root.getNodeName());

            // Konzolra írás
            printNode(root, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // A400M Atlas típus cserélése A380-ra
    private static void modifyAirplaneModel(Document document) {
        NodeList airplanes = document.getElementsByTagName("Repülő");
        for (int i = 0; i < airplanes.getLength(); i++) {
            Node airplane = airplanes.item(i);
            if (airplane.getNodeType() == Node.ELEMENT_NODE) {
                Element airplaneElement = (Element) airplane;
                Node modelNode = airplaneElement.getElementsByTagName("Típus").item(0);
                if (modelNode != null && "A400M Atlas".equals(modelNode.getTextContent())) {
                    modelNode.setTextContent("A380");
                }
            }
        }
    }

    // Ha több mint 10000 órája van egy pilótának akkor kapjon doktori fokozatot
    private static void modifyCaptainNames(Document document) {
        NodeList captains = document.getElementsByTagName("Kapitány");
        for (int i = 0; i < captains.getLength(); i++) {
            Node captain = captains.item(i);
            if (captain.getNodeType() == Node.ELEMENT_NODE) {
                Element captainElement = (Element) captain;
                int flightHours = Integer.parseInt(captainElement.getElementsByTagName("Repült").item(0).getTextContent());
                if (flightHours > 10000) {
                    Node firstNameNode = captainElement.getElementsByTagName("Vezetéknév").item(0);
                    if (firstNameNode != null) {
                        firstNameNode.setTextContent("Dr. " + firstNameNode.getTextContent());
                    }
                }
            }
        }
    }

    // Az összes járat átirányítása Bécsbe
    private static void modifyFlightRoutes(Document document) {
        NodeList routes = document.getElementsByTagName("Tartalmaz");
        for (int i = 0; i < routes.getLength(); i++) {
            Node route = routes.item(i);
            if (route.getNodeType() == Node.ELEMENT_NODE) {
                Element routeElement = (Element) route;
                routeElement.setAttribute("Cél", "VIE");
            }
        }
    }

    // Jövőbe utazás
    private static void modifyFlightDates(Document document) {
        NodeList flights = document.getElementsByTagName("Járat");
        for (int i = 0; i < flights.getLength(); i++) {
            Node flight = flights.item(i);
            if (flight.getNodeType() == Node.ELEMENT_NODE) {
                Element flightElement = (Element) flight;
                Node departureNode = flightElement.getElementsByTagName("Indulás").item(0);
                Node arrivalNode = flightElement.getElementsByTagName("Érkezés").item(0);

                if (departureNode != null) {
                    departureNode.setTextContent(departureNode.getTextContent().replaceFirst("\\d{4}", "2025"));
                }
                if (arrivalNode != null) {
                    arrivalNode.setTextContent(arrivalNode.getTextContent().replaceFirst("\\d{4}", "2025"));
                }
            }
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
}

