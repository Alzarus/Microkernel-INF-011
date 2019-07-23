/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import interfaces.ICore;
import interfaces.ILoginController;
import interfaces.ILoginPlugin;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


/**
 *
 * @author pedro
 */
public class LoginController implements ILoginController{
    
    private static LoginController INSTANCE  = null;
    
    private LoginController(){
        
    }
    
    public static LoginController getInstance(){
        if(INSTANCE == null){
            INSTANCE = new LoginController();
        }
        return INSTANCE;
    }

    @Override
    public boolean initialize() {
        ILoginPlugin plugin = escolherLogin();
        plugin.efetuarLogin();
        return true;
    }

    @Override
    public ILoginPlugin escolherLogin() {
        File file = new File("./logins/login.xml");
        ILoginPlugin login = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = dbf.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            Element htmlTag = doc.getDocumentElement();
            Element headTag = (Element) htmlTag.getElementsByTagName("head").item(0);
            Element titleTag = (Element) headTag.getElementsByTagName("title").item(0);
            String className = titleTag.getTextContent();
            login = (ILoginPlugin) Class.forName("loginplugin." + className + "LoginPlugin").newInstance();
            return login;
        } catch (ParserConfigurationException | SAXException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
