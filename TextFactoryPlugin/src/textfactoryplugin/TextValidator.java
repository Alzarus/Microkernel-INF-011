/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfactoryplugin;

import interfaces.IDocumentValidator;

/**
 *
 * @author pedro
 */
public class TextValidator implements IDocumentValidator{

    @Override
    public boolean validate() {
        System.out.println("You used the method validate() from TextValidator. ");
        return true;        
    }
    
}
