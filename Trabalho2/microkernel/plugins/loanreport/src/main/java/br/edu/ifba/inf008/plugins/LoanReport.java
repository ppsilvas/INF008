package br.edu.ifba.inf008.plugins;

import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.ILibraryController;
import br.edu.ifba.inf008.interfaces.ILoanReport;
import br.edu.ifba.inf008.models.Book;
import br.edu.ifba.inf008.models.User;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoanReport implements ILoanReport{
    private Stage primaryStage;
    private final ILibraryController libraryController;

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
        primaryStage.setTitle("Livros Emprestados");

        TableView<Map.Entry<Book,User>> tableView = new TableView<>();

        TableColumn<Map.Entry<Book,User>,String> bookColumn = new TableColumn<>("Livro");
        bookColumn.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getKey().getTitle()));
        
        TableColumn<Map.Entry<Book,User>,String> userColumn = new TableColumn<>("Usuário");
        userColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getName()));

        tableView.getColumns().addAll(bookColumn,userColumn);

        TreeMap<User,Book> booksAndUsers = libraryController.getBorrowedBooks();

        ObservableList<Map.Entry<Book,User>> loanedBooksAndUser = FXCollections.observableArrayList(
            booksAndUsers.entrySet().stream()
            .map(entry -> new AbstractMap.SimpleEntry<>(entry.getValue(),entry.getKey()))
            .collect(Collectors.toList())
        );

        tableView.setItems(loanedBooksAndUser);
        
        tableView.setPrefSize(200, 250);
        tableView.setPadding(new Insets(2,2,2,2));

        bookColumn.setPrefWidth(150);
        userColumn.setPrefWidth(100);
        
        Button cancelButton = new Button("Voltar");
        cancelButton.setOnAction(e->primaryStage.close());

        VBox layout = new VBox(10, tableView, cancelButton);
        layout.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(layout, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
