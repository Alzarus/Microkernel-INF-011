/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import interfaces.ICore;
import interfaces.IPlugin;
import interfaces.IPluginController;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class PluginController implements IPluginController {
    
    private static PluginController INSTANCE = null;
    
    private PluginController(){
        
    }
    
    public static PluginController getInstance(){
       if(INSTANCE == null){ 
           INSTANCE = new PluginController();
       }
       return INSTANCE;
    }

    @Override
    public boolean initialize() {
        File currentDir = new File("./plugins");
        String []plugins = currentDir.list();
        int i;
        URL[] jars = new URL[plugins.length];
        System.out.println("Encontrei " + plugins.length + " plugins instalados!");
        for (i = 0; i < plugins.length; i++)
            try {
                jars[i] = (new File("./plugins/" + plugins[i])).toURL();
            } catch (MalformedURLException ex) {
                Logger.getLogger(PluginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        URLClassLoader ulc = new URLClassLoader(jars);
        for (i = 0; i < plugins.length; i++) {
            String pluginName = plugins[i].split("\\.")[0];
            IPlugin plugin = null;
            try {            
                plugin = (IPlugin) Class.forName(pluginName.toLowerCase() + "." + pluginName, true, ulc).newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(PluginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (plugin != null)
                if (plugin.initialize())
                    loadedPlugins.add(plugin);
        }

        return true;
    }

    @Override
    public List<IPlugin> getLoadedPlugins() {
        return loadedPlugins;
    }
    
    public <T> List<T> getLoadedPluginsByType(Class<T> t) {
        List<T> loadedPluginsByType = new ArrayList<>();
        
        for(IPlugin eachLoadedPlugin : this.getLoadedPlugins()){
                if(t.isInstance(eachLoadedPlugin)){ 
                    loadedPluginsByType.add((T) eachLoadedPlugin);
                }
        }

        return loadedPluginsByType;
    }

    private List<IPlugin> loadedPlugins = new ArrayList<IPlugin>();
}
