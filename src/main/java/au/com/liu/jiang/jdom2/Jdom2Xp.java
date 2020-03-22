package au.com.liu.jiang.jdom2;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

public class Jdom2Xp {

	public static void jdom2Xp() throws Exception {
		String xml = "src/test/resources/xxe.xml";
		SAXBuilder builder = new SAXBuilder(); // Noncompliant by default
		Document document = builder.build(new File(xml));
		System.out.println(document);
	}
}
