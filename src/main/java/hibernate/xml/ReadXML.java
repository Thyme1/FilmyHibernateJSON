package hibernate.xml;


import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.*;


public class ReadXML {

    public static String deserializeXML() {
        XmlMapper xmlMapper=new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String xml=null;
        try {
            xml=inputStreamToString(new FileInputStream("actors.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;

    }


    public static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb=new StringBuilder();
        String line;
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        while ((line=br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public static String getPrettyString(String xmlData, int indent) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", indent);

        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StringWriter stringWriter = new StringWriter();
        StreamResult xmlOutput = new StreamResult(stringWriter);

        Source xmlInput = new StreamSource(new StringReader(xmlData));
        transformer.transform(xmlInput, xmlOutput);

        return xmlOutput.getWriter().toString();
    }


    public static void main(String[] args){
        String xml = deserializeXML();
        try {
            String str = getPrettyString(xml, 2);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}





