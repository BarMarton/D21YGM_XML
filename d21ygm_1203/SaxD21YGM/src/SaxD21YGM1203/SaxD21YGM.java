package SaxD21YGM1203;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxD21YGM {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SaxHandler handler = new SaxHandler();
            parser.parse("d21ygm_kurzusfelvetel.xml", handler);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
class SaxHandler extends DefaultHandler {
    private int indent = 0;

    private void indent(Integer indentOffset) {
        int actualIndent =  indent + indentOffset;
        for (int i = 0; i < actualIndent; i++) {
            System.out.print("  ");
        }
    }

    private String format(Attributes attributes) {
        int attributeLength = attributes.getLength();
        if (attributeLength == 0){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(" {");
        for (int i = 0; i < attributeLength; i++) {
            stringBuilder.append(attributes.getLocalName(i)).append(":").append(attributes.getValue(i));
            if ( i < attributeLength - 1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        indent++;
        indent(0);
        System.out.println(qName + format(attributes) + " start");
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        indent(0);
        indent--;
        System.out.println(qName + " end");
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String chars = new String(ch, start, length).trim();
        if (!chars.isEmpty()) {
            indent(1);
            System.out.println(chars);
        }
    }
}