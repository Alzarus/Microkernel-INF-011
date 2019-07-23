/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfactoryplugin;

import interfaces.IDocumentSerializer;

/**
 *
 * @author pedro
 */
public class TextSerializer implements IDocumentSerializer{
    
    private static TextSerializer INSTANCE  = null;
    
    private TextSerializer(){
        
    }
    
    public static TextSerializer getInstance(){
        if(INSTANCE == null){
            INSTANCE = new TextSerializer();
        }
        return INSTANCE;
    }

    @Override
    public boolean load() {
        System.out.println("You used the method load() from TextSerializer. ");
        return true;
    }

    @Override
    public boolean save() {
        System.out.println("You used the method save() from TextSerializer. ");
        return true;
    }
    
}
