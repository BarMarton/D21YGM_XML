package SaxD21YGM1203;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class XsdD21YGM {
    public static void main(String[] args) {
        String xml = "d21ygm_kurzusfelvetel.xml";
        String xsd = "d21ygm_kurzusfelvetel.xsd";
        boolean isValid = validateXMLSchema(xsd, xml);
        if (isValid) {
            System.out.println("Successful validation!");
        } else {
            System.out.println("Unsuccessful validation!");
        }
    }

    public static boolean validateXMLSchema(String xsd, String xml) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xml)));
            return true;
        } catch (SAXException | IOException e) {
            return false;
        }
    }
}
