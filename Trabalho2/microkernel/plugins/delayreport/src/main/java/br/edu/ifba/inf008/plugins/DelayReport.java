package br.edu.ifba.inf008.plugins;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.stream.IntStream;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IDelayReport;
import br.edu.ifba.inf008.interfaces.ILibraryController;
import br.edu.ifba.inf008.models.Book;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DelayReport implements IDelayReport{
    private Stage primaryStage;
    private ILibraryController libraryController;

    public DelayReport(){
        this.libraryController = ICore.getInstance().getLibraryController();
    }

    @Override
    public boolean init() {
        Platform.runLater(this::showDelayReport);
        return true;
    }

    @Override
    public void showDelayReport(){
        primaryStage = new Stage();
        TableView<Map.Entry<Book, Double>> tableView = new TableView<>();

        TableColumn<Map.Entry<Book, Double>,String> titleColumn = new TableColumn<>("Livro");
        titleColumn.setCellValueFactory(cellData->
            new SimpleStringProperty(cellData.getValue().getKey().getTitle()));
        TableColumn<Map.Entry<Book, Double>,String> fineColumn = new TableColumn<>("Multa (R$)");
        fineColumn.setCellValueFactory(cellData->
            new SimpleStringProperty(String.format("%.2f", cellData.getValue().getValue())));

        tableView.getColumns().addAll(titleColumn,fineColumn);

        List<Book> lateBooks = libraryController.getLateBooks();
        List<Double> fines = libraryController.getFine();

        ObservableList<Map.Entry<Book,Double>> lateBooksWithFine = FXCollections.observableArrayList(
            IntStream.range(0, lateBooks.size())
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(lateBooks.get(i), fines.get(i)))
                .toList()
        );

        tableView.setItems(lateBooksWithFine);

        Button cancelButton = new Button("Voltar");
        cancelButton.setOnAction(e->primaryStage.close());

        VBox vbox = new VBox(10, tableView, cancelButton);
        Scene scene = new Scene(vbox, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Impressão de Tabela");
        primaryStage.show();
    }
}
