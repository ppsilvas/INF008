package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.*;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class LibraryUi implements ILibraryPluginUi{
    private ILibraryController libraryController;
    private Stage primaryStage;

    public LibraryUi(){
        
    }
    
    public LibraryUi(ILibraryController libraryController) {
        this.libraryController = libraryController;
    }

    @Override
    public boolean init() {
        Platform.runLater(this::showMainMenu);
        return true;
    }

    @Override
    public void showMainMenu() {
        primaryStage = new Stage();
        primaryStage.setTitle("Menu Principal");

        // Criação dos botões
        Button addUserButton = new Button("Adicionar Usuário");
        Button addBookButton = new Button("Adicionar Livro");
        Button borrowBookButton = new Button("Emprestar Livro");
        Button returnBookButton = new Button("Devolver Livro");
        Button exitButton = new Button("Sair");

        // Ações para os botões
        addUserButton.setOnAction(e -> showAddUserMenu());
        addBookButton.setOnAction(e -> showAddBookMenu());
        borrowBookButton.setOnAction(e -> showBorrowBookMenu());
        returnBookButton.setOnAction(e -> showReturnBookMenu());
        exitButton.setOnAction(e -> saveLibraryData(primaryStage));

        // Layout
        VBox layout = new VBox(10, addUserButton, addBookButton, borrowBookButton, returnBookButton, exitButton);
        layout.setPadding(new javafx.geometry.Insets(20));

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void showAddUserMenu() {
        Stage stage = new Stage();
        stage.setTitle("Adicionar Usuário");

        // Campos para entrada de dados
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

        // Layout
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

        // Campos para entrada de dados
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

        // Layout
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

        // Campos para entrada de dados
        TextField userIdField = new TextField();
        userIdField.setPromptText("ID do Usuário");

        TextField isbnField = new TextField();
        isbnField.setPromptText("ISBN do Livro");

        Button submitButton = new Button("Emprestar");
        submitButton.setOnAction(e -> {
            try {
                int userId = Integer.parseInt(userIdField.getText());
                int isbn = Integer.parseInt(isbnField.getText());
                if (libraryController.borrowBook(userId, isbn)) {
                    showAlert("Livro emprestado com sucesso!", Alert.AlertType.INFORMATION);
                    stage.close();
                } else {
                    showAlert("Falha ao emprestar livro.", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException ex) {
                showAlert("ID do usuário ou ISBN inválido.", Alert.AlertType.ERROR);
            }
        });

        Button cancelButton = new Button("Cancelar");
        cancelButton.setOnAction(e -> stage.close());

        // Layout
        VBox layout = new VBox(10, userIdField, isbnField, submitButton, cancelButton);
        layout.setPadding(new javafx.geometry.Insets(20));

        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void showReturnBookMenu() {
        Stage stage = new Stage();
        stage.setTitle("Devolver Livro");

        // Campos para entrada de dados
        TextField userIdField = new TextField();
        userIdField.setPromptText("ID do Usuário");

        TextField isbnField = new TextField();
        isbnField.setPromptText("ISBN do Livro");

        TextField loanIdField = new TextField();
        loanIdField.setPromptText("ID do Empréstimo");

        Button submitButton = new Button("Devolver");
        submitButton.setOnAction(e -> {
            try {
                int userId = Integer.parseInt(userIdField.getText());
                int isbn = Integer.parseInt(isbnField.getText());
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

        // Layout
        VBox layout = new VBox(10, userIdField, isbnField, loanIdField, submitButton, cancelButton);
        layout.setPadding(new javafx.geometry.Insets(20));

        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.show();
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
