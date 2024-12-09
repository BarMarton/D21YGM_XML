package d21ygm1112;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class JSONWriteD21YGM {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("d21ygm_orarend.json"));
            JSONObject orarend = (JSONObject) jsonObject.get("orarend");
            JSONArray oraArray = (JSONArray) orarend.get("ora");
            JSONArray outputArray = new JSONArray();

            for (Object obj : oraArray) {
                JSONObject ora = (JSONObject) obj;

                System.out.println("**********");
                System.out.println("Tárgy: " + ora.get("targy"));

                JSONObject idopont = (JSONObject) ora.get("idopont");
                System.out.println("Időpont:");
                System.out.println("  Nap: " + idopont.get("nap"));
                System.out.println("  Tól: " + idopont.get("tol"));
                System.out.println("  Ig: " + idopont.get("ig"));

                System.out.println("Helyszín: " + ora.get("helyszin"));
                System.out.println("Oktató: " + ora.get("oktato"));
                System.out.println("Szak: " + ora.get("szak"));
                System.out.println("ID: " + ora.get("_id"));
                System.out.println("Típus: " + ora.get("_típus"));
                System.out.println("**********\n");

                outputArray.add(ora);
            }

            JSONObject newJsonObject = new JSONObject();
            newJsonObject.put("orarend", new JSONObject());
            ((JSONObject) newJsonObject.get("orarend")).put("ora", outputArray);

            try (FileWriter fileWriter = new FileWriter("d21ygm_orarend1.json")) {
                fileWriter.write(newJsonObject.toJSONString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
