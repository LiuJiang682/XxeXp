package au.com.liu.jiang;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import au.com.liu.jiang.handler.SaxHandler;

public class XxeXp {

	public static void main(String[] args) {
		try {
//			DocumentBuilderFactoryXp();
//			SAXParserFactoryXp();
//			xmlInputFactoryXp();
//			transformerFactoryXp();
			schemaFactoryXp();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void schemaFactoryXp() throws Exception {
		String xsd = "src/test/resources/xxe.xsd";
		StreamSource xsdStreamSource = new StreamSource(xsd);

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);  // Noncompliant
		Schema schema = schemaFactory.newSchema(xsdStreamSource);
		System.out.println(schema.toString());
	}

	private static void transformerFactoryXp() throws Exception {
		String xslt = "src/test/resources/xxe.xsl";
		String xml = "src/test/resources/xxe.xml";
		TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();  // Noncompliant
		transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); // Compliant
		transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, ""); // Compliant
		Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslt));

		StringWriter writer = new StringWriter();
		transformer.transform(new StreamSource(xml), new StreamResult(writer));
		String result = writer.toString();
		System.out.println(result);
	}

	private static void xmlInputFactoryXp() throws Exception {
		XMLInputFactory factory = XMLInputFactory.newInstance();  // Noncompliant
		factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, ""); // Compliant
		factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");  // compliant
		XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("src/test/resources/xxe.xml"));
		eventReader.forEachRemaining(e -> {
			System.out.println(e);
		});
	}

	private static void SAXParserFactoryXp() throws Exception, SAXException {
		String xml = "src/test/resources/xxe.xml";
		SaxHandler handler = new SaxHandler();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();  // Noncompliant
		parser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, ""); // Compliant
		parser.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, ""); // compliant
		parser.parse(xml, handler);
		
	}

	private static void DocumentBuilderFactoryXp() throws Exception {
		String xml = "src/test/resources/xxe.xml";
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();  // Noncompliant
		//Solution
		df.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); // Compliant
		df.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, ""); // compliant
		DocumentBuilder builder = df.newDocumentBuilder();
		Document document = builder.parse(new InputSource(xml));
		DOMSource domSource = new DOMSource(document);
		System.out.println(domSource);
	}

}
