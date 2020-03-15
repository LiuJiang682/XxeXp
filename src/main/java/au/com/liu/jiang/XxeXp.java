package au.com.liu.jiang;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XxeXp {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactoryXp();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
