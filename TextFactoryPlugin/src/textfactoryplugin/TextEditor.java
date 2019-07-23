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
    
    private static TextEditor INSTANCE  = null;
    
    private TextEditor(){
        
    }
    
    public static TextEditor getInstance(){
        if(INSTANCE == null){
            INSTANCE = new TextEditor();
        }
        return INSTANCE;
    }

    @Override
    public boolean open() {
        System.out.println("You used the method open() from TextEditor. ");
        return true;
    }
    
}
