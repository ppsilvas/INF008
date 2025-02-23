package br.edu.ifba.inf008.interfaces;

public abstract class ICore
{
    public static ICore getInstance() {
        if(instance == null){
            throw new IllegalStateException("Core not initialized");
        }
        return instance;
    }

    public abstract IUIController getUIController();
    public abstract IAuthenticationController getAuthenticationController();
    public abstract IIOController getIOController();
    public abstract IPluginController getPluginController();
    public abstract ILibraryController getLibraryController();

    protected static ICore instance = null;
}
