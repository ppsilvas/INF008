package br.edu.ifba.inf008.interfaces;

import javafx.stage.Stage;

public interface ILibraryPluginUi extends IPlugin{
    public abstract boolean init();
    public abstract void showMainMenu();
    public abstract void showAddUserMenu();
    public abstract void showAddBookMenu();
    public abstract void showBorrowBookMenu();
    public abstract void showReturnBookMenu();
    public abstract void showLoanReport();
    public abstract void saveLibraryData(Stage stage);
}
