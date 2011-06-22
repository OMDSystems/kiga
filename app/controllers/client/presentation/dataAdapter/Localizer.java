package controllers.client.presentation.dataAdapter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Localizes stuff
 * @author Marvin
 */
public class Localizer {

  /** The locale wich is searched for */
  private static Locales locale = Locales.FOO;
  /** parsed xml document */
  private static Document document;

  static {
    parseXML();
  }

  public static void parseXML() {
    try {
      File xmlFile = new File("kiga/conf/localize.xml");
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      document = dBuilder.parse(xmlFile);
      document.getDocumentElement().normalize();
    } catch (SAXException ex) {
      Logger.getLogger(Localizer.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Localizer.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(Localizer.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  public static void setLocale(Locales locale) {
    Localizer.locale = locale;
  }
  
  public static Localizer.Locales getLocale(){
    return locale;
  }

  public static String localize(String text) {
    NodeList nodes = document.getElementsByTagName(text.toLowerCase().replaceAll(" ", ""));
    String result = null;
    if (nodes.getLength() != 1 || locale == Locales.ENGLISH) {
      result = text;
    } else {
      Node word = nodes.item(0);
      if (word.getNodeType() == Node.ELEMENT_NODE) {

        Element translation = (Element) word;

        result = getTagValue(locale.toString(), translation, text);
      }
    }

//      Node word = nodes.item(0);
//      NodeList translations = word.getChildNodes();
//      for (int i = 0; i < translations.getLength(); i++) {
//        if (translations.getNodeType() == Node.ELEMENT_NODE) {
//          Element translation = (Element) translations.item(i);
//          if (translation.getTextContent().equals(locale.toString())) {
//            result = translation.getTextContent();
//            break;
//          }
//        }
//      }
    return result;
  }

  private static String getTagValue(String sTag, Element eElement, String ifNotFound) {
    String result;
    Node item = eElement.getElementsByTagName(sTag).item(0);
    if (item == null) {
      result = ifNotFound;
    } else {
      NodeList childNodes = item.getChildNodes();
      Node nValue = (Node) childNodes.item(0);
      result = nValue.getNodeValue();
    }
    return result;
  }

  public enum Locales {

    GERMAN, ENGLISH, FOO;

    @Override
    public String toString() {
      return super.toString().toLowerCase();
    }
  }
}
