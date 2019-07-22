/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package textfactoryplugin;

import application.Core;
import interfaces.ICore;
import interfaces.IDocumentEditor;
import interfaces.IDocumentFactory;
import interfaces.IDocumentSerializer;
import interfaces.IDocumentValidator;
import interfaces.IPlugin;
import interfaces.IUIController;
import java.io.File;

//import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author pedro
 */
public class TextFactoryPlugin implements IPlugin, IDocumentFactory{

    @Override
    public boolean initialize() {
        IUIController uiController = Core.getInstance().getUIController();

        return true;
    }

    @Override
    public IDocumentEditor getDocumentEditor() {
        return this.textEditor;
    }

    @Override
    public IDocumentValidator getDocumentValidator() {
        return this.textValidator;
    }

    @Override
    public IDocumentSerializer getDocumentSerializer() {
        return this.textSerializer;
    }

    @Override // return "gif|png|jpg";
    public String getSupportedExtensions() {
        return "txt|doc";
    }
    
    private TextEditor textEditor = new TextEditor();
    private TextValidator textValidator = new TextValidator();
    private TextSerializer textSerializer = new TextSerializer();
}
