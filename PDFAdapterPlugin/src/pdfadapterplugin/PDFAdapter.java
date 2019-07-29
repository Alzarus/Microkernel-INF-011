/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfadapterplugin;

import interfaces.IDocumentEditor;
import interfaces.IDocumentSerializer;
import interfaces.IDocumentValidator;

import java.awt.*;
import java.lang.reflect.*;
import java.util.List;

import javax.swing.*;

import org.apache.pdfbox.PDFReader;
import org.apache.pdfbox.pdfviewer.PageWrapper;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *  
 * @author pedro
 */
public class PDFAdapter extends PDFReader implements IDocumentEditor, IDocumentValidator, IDocumentSerializer{
    
    private static final long serialVersionUID = 678451510308887925L;
    
    public PDFAdapter(){
        super();
    }
    
    @Override
    public boolean open(String filePath) {
        PDFAdapter reader = new PDFAdapter();
        
        JMenuBar menu = reader.getJMenuBar();
        menu.setVisible(false);
        
        JPanel header = new JPanel(new BorderLayout());
        
        JPanel firstLine = new JPanel();
        
        JLabel label = new JLabel("PDF Viewer");
        firstLine.add(label);
        
        JPanel secondLine = new JPanel();
        
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(100, 30));
        secondLine.add(text);
        
        JButton button = new JButton("Jump to");
        button.setPreferredSize(new Dimension(100, 30));
        secondLine.add(button);
        
        header.add(firstLine, java.awt.BorderLayout.NORTH);
        header.add(secondLine, java.awt.BorderLayout.SOUTH);
        
        reader.getContentPane().add(header, java.awt.BorderLayout.NORTH);

        // set default opened file
        reader.setCurrentFile(filePath);
        reader.showAllPages();
        reader.setVisible(true);
        
        return true;
    }

    @Override
    public boolean validate(String fileName) {
        System.out.println("You used the method validate() from PDFValidator.");
        return true;
    }

    @Override
    public boolean load(String fileName) {
        System.out.println("You used the method load() from PDFSerializer. ");
        return true;
    }

    @Override
    public boolean save(String fileName) {
        System.out.println("You used the method save() from PDFSerializer. ");
        return true;
    }
    
    public void setCurrentFile(String filePath) {
        try {
            Method m = getClass().getSuperclass().getDeclaredMethod("openPDFFile", 
                    new Class<?>[]{String.class, String.class});
            m.setAccessible(true);
            m.invoke(this, filePath, null);
        } catch (Exception e) {
            e.getCause().getMessage(); //printStackTrace();
        }
    }
    
    private void showAllPages() {
        try {
            Field pages = getClass().getSuperclass().getDeclaredField("pages");
            pages.setAccessible(true);
            List<PDPage> pagesList = (List<PDPage>) pages.get(this);

            Field documentPanel = getClass().getSuperclass().getDeclaredField("documentPanel");
            documentPanel.setAccessible(true);
            JPanel panel = (JPanel) documentPanel.get(this);
            panel.remove(0);
            GridLayout layout = new GridLayout(0, 1);
            panel.setLayout(layout);
            for(PDPage page : pagesList) {
                PageWrapper wrapper = new PageWrapper(this);
                wrapper.displayPage(page);
                panel.add(wrapper.getPanel());
            }
            pack();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
