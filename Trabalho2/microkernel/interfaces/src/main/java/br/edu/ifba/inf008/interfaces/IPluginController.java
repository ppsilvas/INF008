package br.edu.ifba.inf008.interfaces;

public interface IPluginController
{
    public abstract boolean init();
    public abstract boolean executePlugin(String pluginName);
}
