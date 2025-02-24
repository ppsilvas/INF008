package br.edu.ifba.inf008.plugins;

import java.time.LocalDate;

import br.edu.ifba.inf008.interfaces.*;
import br.edu.ifba.inf008.models.Book;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class LibraryUi implements ILibraryPluginUi{
    private ILibraryController libraryController;
    private Stage primaryStage;

    public LibraryUi(){
        this.libraryController = ICore.getInstance().getLibraryController();
    }
    
    // public LibraryUi(ILibraryController libraryController) {
    //     this.libraryController = libraryController;
    // }

    @Override
    public boolean init() {
        libraryController.loadData();
        Platform.runLater(this::showMainMenu);
        return true;
    }

    @Override
    public void showMainMenu() {
        primaryStage = new Stage();
        primaryStage.setTitle("Menu Principal");

        Button addUserButton = new Button("Adicionar Usuário");
        Button addBookButton = new Button("Adicionar Livro");
        Button borrowBookButton = new Button("Emprestar Livro");
        Button returnBookButton = new Button("Devolver Livro");
        Button loanReportButton = new Button("Livros Emprestados");
        Button delayReporButton = new Button("Livros Atrasados");
        Button exitButton = new Button("Salvar e Sair");

        addUserButton.setOnAction(e -> showAddUserMenu());
        addBookButton.setOnAction(e -> showAddBookMenu());
        borrowBookButton.setOnAction(e -> showBorrowBookMenu());
        returnBookButton.setOnAction(e -> showReturnBookMenu());
        loanReportButton.setOnAction(e -> showLoanReport());
        delayReporButton.setOnAction(e -> showDelayReport());
        exitButton.setOnAction(e -> saveLibraryData(primaryStage));

        VBox layout = new VBox(10, addUserButton, addBookButton, borrowBookButton, returnBookButton, loanReportButton, delayReporButton, exitButton);
        layout.setPadding(new javafx.geometry.Insets(20));

        Scene scene = new Scene(layout, 300, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void showAddUserMenu() {
        Stage stage = new Stage();
        stage.setTitle("Adicionar Usuário");

        TextField nameField = new TextField();
        nameField.setPromptText("Nome do Usuário");

        Button submitButton = new Button("Adicionar");
        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            if (!name.isEmpty()) {
                libraryController.newUser(name);
                showAlert("Usuário adicionado com sucesso!", Alert.AlertType.INFORMATION);
                stage.close();
            } else {
                showAlert("Nome não pode ser vazio.", Alert.AlertType.ERROR);
            }
        });

        Button cancelButton = new Button("Cancelar");
        cancelButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, nameField, submitButton, cancelButton);
        layout.setPadding(new javafx.geometry.Insets(20));

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void showAddBookMenu() {
        Stage stage = new Stage();
        stage.setTitle("Adicionar Livro");

        TextField titleField = new TextField();
        titleField.setPromptText("Título");

        TextField authorField = new TextField();
        authorField.setPromptText("Autor");

        TextField yearField = new TextField();
        yearField.setPromptText("Ano de Lançamento");

        TextField genreField = new TextField();
        genreField.setPromptText("Gênero");

        Button submitButton = new Button("Adicionar");
        submitButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String year = yearField.getText();
            String genre = genreField.getText();
            if (!title.isEmpty() && !author.isEmpty() && !year.isEmpty() && !genre.isEmpty()) {
                try {
                    libraryController.newBook(title, author, Integer.parseInt(year), genre);
                    showAlert("Livro adicionado com sucesso!", Alert.AlertType.INFORMATION);
                    stage.close();
                } catch (NumberFormatException ex) {
                    showAlert("Ano de lançamento inválido.", Alert.AlertType.ERROR);
                }
            } else {
                showAlert("Todos os campos devem ser preenchidos.", Alert.AlertType.ERROR);
            }
        });

        Button cancelButton = new Button("Cancelar");
        cancelButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, titleField, authorField, yearField, genreField, submitButton, cancelButton);
        layout.setPadding(new javafx.geometry.Insets(20));

        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void showBorrowBookMenu() {
        Stage stage = new Stage();
        stage.setTitle("Emprestar Livro");

        TextField userIdField = new TextField();
        userIdField.setPromptText("Nome do Usuário");

        TextField searchField = new TextField();
        searchField.setPromptText("Digite o título do livro...");

        ListView<Book> bookListView = new ListView<>();
        ObservableList<Book> allBooks = FXCollections.observableArrayList(libraryController.getAvailableBooks());
        FilteredList<Book> filteredBooks = new FilteredList<>(allBooks, b -> true);

        bookListView.setItems(filteredBooks);
        bookListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                setText((empty || book == null) ? null : book.getTitle());
            }
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredBooks.setPredicate(book -> 
                newValue == null || newValue.isEmpty() || book.getTitle().toLowerCase().contains(newValue.toLowerCase())
            );
        });

        final Book[] selectedBook = {null};
        bookListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedBook[0] = newSelection;
            }
        });

        DatePicker loanDatePicker = new DatePicker();
        loanDatePicker.setValue(LocalDate.now());

        Button submitButton = new Button("Emprestar");
        submitButton.setOnAction(e -> {
            try {
                LocalDate loanDate = loanDatePicker.getValue();
                String userName = userIdField.getText();

                if (selectedBook[0] == null) {
                    showAlert("Selecione um livro!", Alert.AlertType.WARNING);
                    return;
                }

                int userId = libraryController.searchUser(userName).getId();
                int isbn = selectedBook[0].getIsbn();

                if (libraryController.borrowBook(userId, isbn, loanDate)) {
                    showAlert("Livro emprestado com sucesso!", Alert.AlertType.INFORMATION);
                    stage.close();
                } else {
                    showAlert("Falha ao emprestar livro.", Alert.AlertType.ERROR);
                }
            } catch (Exception ex) {
                showAlert("Nome do usuário inválido.", Alert.AlertType.ERROR);
            }
        });

        Button cancelButton = new Button("Cancelar");
        cancelButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, userIdField, searchField, bookListView, loanDatePicker, submitButton, cancelButton);
        layout.setPadding(new javafx.geometry.Insets(20));

        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void showReturnBookMenu() {
        Stage stage = new Stage();
        stage.setTitle("Devolver Livro");

        TextField userIdField = new TextField();
        userIdField.setPromptText("Nome do Usuário");

        TextField isbnField = new TextField();
        isbnField.setPromptText("Titulo do Livro");

        TextField loanIdField = new TextField();
        loanIdField.setPromptText("ID do Empréstimo");

        Button submitButton = new Button("Devolver");
        submitButton.setOnAction(e -> {
            try {
                String userName = userIdField.getText();
                String bookTitle = isbnField.getText();
                int userId = libraryController.searchUser(userName).getId();
                int isbn = libraryController.searchBook(bookTitle).getIsbn();
                int loanId = Integer.parseInt(loanIdField.getText());
                if (libraryController.returnBook(userId, isbn, loanId)) {
                    showAlert("Livro devolvido com sucesso!", Alert.AlertType.INFORMATION);
                    stage.close();
                } else {
                    showAlert("Falha ao devolver livro.", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException ex) {
                showAlert("Campos inválidos.", Alert.AlertType.ERROR);
            }
        });

        Button cancelButton = new Button("Cancelar");
        cancelButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, userIdField, isbnField, loanIdField, submitButton, cancelButton);
        layout.setPadding(new javafx.geometry.Insets(20));

        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void showLoanReport(){
        String pluginName = "LoanReport";
        ICore.getInstance().getPluginController().executePlugin(pluginName);
    }

    @Override
    public void showDelayReport(){
        String pluginName = "DelayReport";
        ICore.getInstance().getPluginController().executePlugin(pluginName);
    }

    @Override
    public void saveLibraryData(Stage stage){
        libraryController.saveData();
        stage.close();
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
