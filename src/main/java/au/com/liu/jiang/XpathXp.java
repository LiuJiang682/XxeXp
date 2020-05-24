package au.com.liu.jiang;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XpathXp {

	public static void main(String[] args) {
		try {
			xpathDangerCode();
			xpathSecuredCode();
			xpathFieldMatchXp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void xpathFieldMatchXp() throws Exception {
		String xml = "src/test/resources/multilevel.xml";
		String expression = "//squad";
		final XPath xpath = XPathFactory.newInstance().newXPath();
		final String squadName = xpath.evaluate(expression, new InputSource(xml));
		System.out.println(squadName);
	};

	private static void xpathSecuredCode() throws Exception {
		final XPath xpath = XPathFactory.newInstance().newXPath();
		final String user = "' or 1=1 or ''='";
		final String pass = "abc";
		org.w3c.dom.Document doc = getDocument();
		String expression = "/users/user[@name=$user and @pass=$pass]";

		  xpath.setXPathVariableResolver(v -> {
		    switch (v.getLocalPart()) {
		      case "user":
		        return user;
		      case "pass":
		        return pass;
		      default:
		        throw new IllegalArgumentException();
		    }
		  });
		  boolean flag = (boolean) xpath.evaluate(expression, doc, XPathConstants.BOOLEAN);
		  System.out.println(flag);
	}

	private static void xpathDangerCode() throws Exception {
		final XPath xpath = XPathFactory.newInstance().newXPath();
		final String user = "' or 1=1 or ''='";
		final String pass = "abc";
		org.w3c.dom.Document doc = getDocument();
		String expression = "/users/user[@name='" + user 
				+ "' and @pass='" + pass + "']"; // Unsafe

		// An attacker can bypass authentication by setting user 
		// to this special value
	
		boolean flag = (boolean) xpath.evaluate(expression, 
				doc, XPathConstants.BOOLEAN);
		System.out.println(flag);
	}

	private static Document getDocument() throws Exception {
		String xml = "src/test/resources/users.xml";
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();  // Noncompliant
		//Solution
		df.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); // Compliant
		df.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, ""); // compliant
		DocumentBuilder builder = df.newDocumentBuilder();
		Document document = builder.parse(new InputSource(xml));
		return document;
	}
}
