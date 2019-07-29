/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfadapterplugin;

import application.Core;

import interfaces.IDocumentEditor;
import interfaces.IDocumentFactory;
import interfaces.IDocumentSerializer;
import interfaces.IDocumentValidator;
import interfaces.IPlugin;
import interfaces.IUIController;

/**
 *
 * @author pedro
 */
public class PDFAdapterPlugin implements IPlugin, IDocumentFactory{

    @Override
    public boolean initialize() {
        IUIController uiController = Core.getInstance().getUIController();

        return true;
    }

    @Override
    public IDocumentEditor getDocumentEditor() {
        return this.pdfEditor;
    }

    @Override
    public IDocumentValidator getDocumentValidator() {
        return this.pdfValidator;
    }

    @Override
    public IDocumentSerializer getDocumentSerializer() {
        return this.pdfSerializer;
    }

    @Override
    public String getSupportedExtensions() {
        return "pdf";
//        return "pdf";
    }
    
    private IDocumentEditor pdfEditor = new PDFAdapter();
    private IDocumentValidator pdfValidator = new PDFAdapter();
    private IDocumentSerializer pdfSerializer = new PDFAdapter();
    
}
