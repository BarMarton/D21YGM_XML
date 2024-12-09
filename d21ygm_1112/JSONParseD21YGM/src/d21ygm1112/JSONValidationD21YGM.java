package d21ygm1112;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;

public class JSONValidationD21YGM {
    public static void main(String[] args) {
        try {
            JSONObject rawSchema = new JSONObject(new JSONTokener(new FileReader("orarendD21YGMSchema.json")));
            Schema schema = SchemaLoader.load(rawSchema);
            JSONObject jsonData = new JSONObject(new JSONTokener(new FileReader("d21ygm_orarend.json")));
            schema.validate(jsonData);
            System.out.println("Megfelel");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
