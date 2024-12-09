package d21ygm1112;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class JSONReadD21YGM {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("d21ygm_orarend.json"));
            JSONObject orarend = (JSONObject) jsonObject.get("orarend");
            JSONArray oraArray = (JSONArray) orarend.get("ora");

            // Iterate over the array and print each element
            for (Object obj : oraArray) {
                JSONObject ora = (JSONObject) obj;
                System.out.println("Tárgy: " + ora.get("targy"));
                JSONObject idopont = (JSONObject) ora.get("idopont");
                System.out.println("  Időpont:");
                System.out.println("    Nap: " + idopont.get("nap"));
                System.out.println("    Tól: " + idopont.get("tol"));
                System.out.println("    Ig: " + idopont.get("ig"));
                System.out.println("  Helyszín: " + ora.get("helyszin"));
                System.out.println("  Oktató: " + ora.get("oktato"));
                System.out.println("  Szak: " + ora.get("szak"));
                System.out.println("  ID: " + ora.get("_id"));
                System.out.println("  Típus: " + ora.get("_típus"));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}