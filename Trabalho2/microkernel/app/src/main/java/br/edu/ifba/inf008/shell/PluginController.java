package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.App;
import br.edu.ifba.inf008.interfaces.IPluginController;
import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
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
    private List<PluginConfig> pluginconfig = new ArrayList<>();
    private HashMap<String, Object> iPlugins = new HashMap<>();

    public boolean init() {
        configurePlugins();
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
            for (PluginConfig plugin : pluginconfig)
            {
                String pluginName = plugin.getClassName();
                Class<?> pluginClass = Class.forName("br.edu.ifba.inf008.plugins." + pluginName, true, ulc);
                Constructor<?> constructor = pluginClass.getDeclaredConstructor(plugin.getParametersType());
                Object pluginInstance = constructor.newInstance(plugin.getParameters());

                iPlugins.put(pluginName, pluginInstance);
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
        Object plugin = iPlugins.get(pluginName);
        if(plugin instanceof ILibraryPluginUi){
            ((ILibraryPluginUi) plugin).init();
            return true;
        }else if(plugin instanceof ILoanReport){
            ((ILoanReport) plugin).init();
            return true;
        }else if(plugin instanceof IPlugin){
            ((IPlugin) plugin).init();
            return true;
        }else{
            return false;
        }
    }

    private static class PluginConfig {
        private String className;
        private Class<?>[] parametersType;
        private Object[] parameters;

        protected PluginConfig(String className, Class<?>[] parametersType, Object[] parameters){
            this.className = className;
            this.parametersType = parametersType;
            this.parameters = parameters;
        }

        public String getClassName() {
            return className;
        }
        public Object[] getParameters() {
            return parameters;
        }
        public Class<?>[] getParametersType() {
            return parametersType;
        }
    }

    private void addPlugin(String className, Class<?>[] parametersType, Object[] parameters){
        pluginconfig.add(new PluginConfig(className, parametersType, parameters));
    }

    private void configurePlugins(){
        addPlugin("LibraryUi", null, null);
        addPlugin("MyPlugin", null, null);
        addPlugin("LoanReport", null, null);
        addPlugin("DelayReport", null, null);
    }
}
