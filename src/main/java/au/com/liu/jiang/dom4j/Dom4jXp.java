package au.com.liu.jiang.dom4j;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class Dom4jXp {

	public static void dom4jXp() throws Exception {
		String xml = "src/test/resources/xxe.xml";
		SAXReader xmlReader = new SAXReader(); // Noncompliant by default
		xmlReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); // Compliant
		Document xmlResponse = xmlReader.read(xml);
		System.out.println(xmlResponse);
	}

}
