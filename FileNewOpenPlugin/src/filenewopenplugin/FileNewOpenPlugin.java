/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filenewopenplugin;

import application.Core;
import interfaces.ICore;
import interfaces.IDocumentFactory;
import interfaces.IPlugin;
import interfaces.IUIController;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class FileNewOpenPlugin implements IPlugin {

    @Override
    public boolean initialize() {
        IUIController uiController = Core.getInstance().getUIController();

        JMenuItem fileNewItem = uiController.addMenuItem("File", "New");
        fileNewItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFileChooser chooser = new JFileChooser();                
                int returnValue = chooser.showOpenDialog(null);
                if(returnValue == JFileChooser.APPROVE_OPTION){
                    acessedFile = chooser.getSelectedFile();
                    fileExtension = acessedFile.getName().split("\\.")[1];
                    loadedPluginsByType = Core.getInstance().getPluginController().getLoadedPluginsByType(IDocumentFactory.class);
                    findCorrectPlugin(acessedFile.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Problems with the file openning or the user cancelled the operation.", "Error", JOptionPane.ERROR_MESSAGE);
                }  
            }
        });        
        
        JMenuItem fileOpenItem = uiController.addMenuItem("File", "Open");
        fileOpenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Voce clicou no File->Open");
            }
        });        

        return true;
    }
    
    public boolean findCorrectPlugin(String filePath){
        for(IDocumentFactory p : loadedPluginsByType){
            arr = p.getSupportedExtensions().split("\\|");
            for(String s : arr){
                if(fileExtension.equals(s)){
                    JOptionPane.showMessageDialog(new JFrame(), "A plugin factory with the chosen file extension has been found.", "Alert", JOptionPane.INFORMATION_MESSAGE);
                    p.getDocumentEditor().open(filePath);
                    p.getDocumentValidator().validate(filePath);
                    p.getDocumentSerializer().load(filePath);
                    p.getDocumentSerializer().save(filePath);
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(new JFrame(), "There aren't installed plugin factories with the chosen file extension.", "Error", JOptionPane.ERROR_MESSAGE);

        return false;
    }
    
    private File acessedFile;
    private String fileExtension;
    private String[] arr = new String[100];
    private List<IDocumentFactory> loadedPluginsByType;
}

