package hu.domparse.D21YGM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DomReadD21YGM {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("XMLD21YGM.xml");

            // Normalizálás
            document.getDocumentElement().normalize();

            // Kiírás
            processKapitany(document);
            processRepulo(document);
            processJarat(document);
            processRepter(document);
            processKifutopalya(document);
            processTartalmaz(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void processKapitany(Document doc) {
        System.out.println("Kapitányok:");
        NodeList kapitanyok = doc.getElementsByTagName("Kapitány");
        for (int i = 0; i < kapitanyok.getLength(); i++) {
            Element kapitany = (Element) kapitanyok.item(i);
            String id = kapitany.getAttribute("személyi_azonosító");
            String vezeteknev = kapitany.getElementsByTagName("Vezetéknév").item(0).getTextContent();
            String utonev = kapitany.getElementsByTagName("Utónév").item(0).getTextContent();
            String repult = kapitany.getElementsByTagName("Repült").item(0).getTextContent();

            System.out.println("\nSzemélyi azonosító: " + id);
            System.out.println("Név: " + vezeteknev + " " + utonev);
            System.out.println("Repült órák: " + repult);
        }
    }

    private static void processRepulo(Document doc) {
        System.out.println("\nRerpülők:");
        NodeList repulok = doc.getElementsByTagName("Repülő");
        for (int i = 0; i < repulok.getLength(); i++) {
            Element repulo = (Element) repulok.item(i);
            String kapitanyId = repulo.getAttribute("Kapitány");
            String hivojel = repulo.getAttribute("Hívójel");
            String gyarto = repulo.getElementsByTagName("Gyártó").item(0).getTextContent();
            String tipus = repulo.getElementsByTagName("Típus").item(0).getTextContent();
            NodeList szinek = repulo.getElementsByTagName("Szín");

            System.out.println("\nHívójel: " + hivojel);
            System.out.println("Kapitány ID: " + kapitanyId);
            System.out.println("Gyártó: " + gyarto);
            System.out.println("Típus: " + tipus);
            System.out.print("Színek: ");
            for (int j = 0; j < szinek.getLength(); j++) {
                System.out.print(szinek.item(j).getTextContent());
                if (j < szinek.getLength() - 1) System.out.print(", ");
            }
            System.out.println();
        }
    }

    private static void processJarat(Document doc) {
        System.out.println("\nJáratok:");
        NodeList jaratok = doc.getElementsByTagName("Járat");
        for (int i = 0; i < jaratok.getLength(); i++) {
            Element jarat = (Element) jaratok.item(i);
            String id = jarat.getAttribute("id");
            String indul = jarat.getElementsByTagName("Indulás").item(0).getTextContent();
            String erkezik = jarat.getElementsByTagName("Érkezés").item(0).getTextContent();
            String hossz = jarat.getElementsByTagName("Hossz").item(0).getTextContent();

            System.out.println("\nJárat azonosító: " + id);
            System.out.println("Indulás: " + indul);
            System.out.println("Érkezés: " + erkezik);
            System.out.println("Hossz: " + hossz + " km");
        }
    }

    private static void processRepter(Document doc) {
        System.out.println("\nRepterek:");
        NodeList repterek = doc.getElementsByTagName("Reptér");
        for (int i = 0; i < repterek.getLength(); i++) {
            Element repter = (Element) repterek.item(i);
            String iata = repter.getAttribute("IATA");
            String orszag = repter.getElementsByTagName("Ország").item(0).getTextContent();
            String varos = repter.getElementsByTagName("Város").item(0).getTextContent();
            String legiforgalom = repter.getElementsByTagName("Légiforgalom").item(0).getTextContent();

            System.out.println("\nIATA: " + iata);
            System.out.println("Ország: " + orszag);
            System.out.println("Város: " + varos);
            System.out.println("Légiforgalom irányítás: " + legiforgalom);
        }
    }

    private static void processKifutopalya(Document doc) {
        System.out.println("\nKifutópályák:");
        NodeList kifutok = doc.getElementsByTagName("Kifutópálya");
        for (int i = 0; i < kifutok.getLength(); i++) {
            Element kifuto = (Element) kifutok.item(i);
            String id = kifuto.getAttribute("id");
            String fid = kifuto.getAttribute("fid");
            Element parameterek = (Element) kifuto.getElementsByTagName("Paraméterek").item(0);
            String meredekseg = parameterek.getElementsByTagName("Meredekség").item(0).getTextContent();
            String hossz = parameterek.getElementsByTagName("Hossz").item(0).getTextContent();
            String irany = parameterek.getElementsByTagName("Irány").item(0).getTextContent();
            String aszfalt = parameterek.getElementsByTagName("Aszfalt").item(0).getTextContent();

            System.out.println("\nID: " + id + ", Reptér: " + fid);
            System.out.println("Hossz: " + hossz + " m, Irány: " + irany + ", Aszfaltozott: " + aszfalt + ", Meredekség: " + meredekseg);

        }
    }

    private static void processTartalmaz(Document doc) {
        System.out.println("\nJáratokhoz tartozó repterek:");
        NodeList tartalmaz = doc.getElementsByTagName("Tartalmaz");
        for (int i = 0; i < tartalmaz.getLength(); i++) {
            Element kapcsolat = (Element) tartalmaz.item(i);
            String jarat = kapcsolat.getAttribute("Járat");
            String start = kapcsolat.getAttribute("Start");
            String cel = kapcsolat.getAttribute("Cél");

            System.out.println("\nJárat: " + jarat);
            System.out.println("Indul: " + start + " Érkezik: " + cel);
        }
    }
}

