/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfactoryplugin;

import interfaces.IDocumentEditor;


/**
 *
 * @author pedro
 */
public class TextEditor implements IDocumentEditor{

    @Override
    public boolean open() {
        System.out.println("You used the method open() from TextEditor. ");
        return true;
    }
    
}
