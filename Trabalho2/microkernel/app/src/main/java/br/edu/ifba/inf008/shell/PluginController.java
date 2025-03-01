package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.App;
import br.edu.ifba.inf008.interfaces.IPluginController;
import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ILibraryPluginUi;
import br.edu.ifba.inf008.interfaces.ILoanReport;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PluginController implements IPluginController
{
    private HashMap<String, IPlugin> iPlugins = new HashMap<>();

    public boolean init() {
        try {
            File currentDir = new File("./plugins");

            // Define a FilenameFilter to include only .jar files
            FilenameFilter jarFilter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jar");
                }
            };

            String []plugins = currentDir.list(jarFilter);
            int i;
            URL[] jars = new URL[plugins.length];
            for (i = 0; i < plugins.length; i++)
            {
                jars[i] = (new File("./plugins/" + plugins[i])).toURL();
            }
            URLClassLoader ulc = new URLClassLoader(jars, App.class.getClassLoader());
            for (i = 0; i < plugins.length; i++)
            {
                String pluginName = plugins[i].split("\\.")[0];
                IPlugin plugin = (IPlugin) Class.forName("br.edu.ifba.inf008.plugins." + pluginName, true, ulc).newInstance();
                iPlugins.put(pluginName,plugin);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());

            return false;
        }
    }

    @Override
    public boolean executePlugin(String pluginName){
        IPlugin plugin = iPlugins.get(pluginName);
        if(plugin instanceof ILibraryPluginUi){
            plugin.init();
            return true;
        }else if(plugin instanceof ILoanReport){
            plugin.init();
            return true;
        }else if(plugin != null){
            plugin.init();
            return true;
        }else{
            return false;
        }
    }
}
