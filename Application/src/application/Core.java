/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import interfaces.ICore;
import interfaces.ILoginController;
import interfaces.IPluginController;
import interfaces.IUIController;

/**
 *
 * @author pedro
 */
public class Core implements ICore {
    
    private static Core INSTANCE = null;

    private Core() {
        uiController = UIController.getInstance();
        pluginController = PluginController.getInstance();
        loginController = LoginController.getInstance();
    }
    
    public boolean initialize(){
        uiController.initialize();
        pluginController.initialize();
        loginController.initialize();
        return true;
    }
    
    public static Core getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Core();
        }
        return INSTANCE;
    }
    
    
    @Override
    public IUIController getUIController() {
        return uiController;
    }

    @Override
    public IPluginController getPluginController() {
        return pluginController;
    }
    
    private IUIController uiController;
    private IPluginController pluginController;
    private ILoginController loginController;
}
