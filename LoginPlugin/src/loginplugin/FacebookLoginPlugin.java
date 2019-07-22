/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginplugin;

import interfaces.ILoginPlugin;

/**
 *
 * @author pedro
 */
public class FacebookLoginPlugin implements ILoginPlugin{

    @Override
    public boolean efetuarLogin() {
        System.out.println("Você efetuou login pelo Facebook.");
        return true;
    }
    
}
