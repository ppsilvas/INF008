package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.ILibraryController;
import br.edu.ifba.inf008.interfaces.ILoanReport;
import br.edu.ifba.inf008.models.Book;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoanReport implements ILoanReport{
    private Stage primaryStage;
    private ILibraryController libraryController;

    public LoanReport(){
        this.libraryController = ICore.getInstance().getLibraryController();
    }

    @Override
    public boolean init() {
        Platform.runLater(this::showReport);
        return true;
    } 
    @Override
    public void showReport(){
        primaryStage = new Stage();
        primaryStage.setTitle("Relátorio de Empréstimos");

        ListView<Book> bookListView = new ListView<>();
        ObservableList<Book> allBooks = FXCollections.observableArrayList(libraryController.getBorrowedBooks());
        FilteredList<Book> filteredBooks = new FilteredList<>(allBooks, b->true);

        bookListView.setItems(filteredBooks);
        bookListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                setText((empty || book == null) ? null : book.getTitle());
            }
        });

        Button cancelButton = new Button("Voltar");
        cancelButton.setOnAction(e->primaryStage.close());

        VBox layout = new VBox(10, bookListView, cancelButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
