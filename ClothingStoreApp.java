/**Group Project
 * CIS 210-0900
 * Osman Goni Rifat & Roger Siambe
 * Due 12/6/2023, Wednesday
*/
package com.example.fx1120demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClothingStoreApp extends Application {

    private ClothingStore clothingStore;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Step 1: Create an instance of the ClothingStore class
        clothingStore = new ClothingStore();

        // Step 2: Ask for username and password
        login(primaryStage);
    }

    private void login(Stage primaryStage) {
        // Step 3: Create UI for login
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");


        // Step 4: Create layout for the login scene
        GridPane loginGrid = new GridPane();
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.setPadding(new Insets(20, 20, 20, 20));
        loginGrid.add(usernameLabel, 0, 0);
        loginGrid.add(usernameField, 1, 0);
        loginGrid.add(passwordLabel, 0, 1);
        loginGrid.add(passwordField, 1, 1);
        loginGrid.add(loginButton, 1, 2);

        // Step 5: Create the login scene
        Scene loginScene = new Scene(loginGrid, 300, 150);

        // Step 6: Set button event handler for login
        loginButton.setOnAction(e -> {
            // Step 7: Validate login credentials
            if (validateLogin(usernameField.getText(), passwordField.getText())) {
                // Step 8: Display menu
                displayMenu(primaryStage);
            } else {
                // Step 9: Show an alert for incorrect login
                showAlert("Invalid Login", "Incorrect username or password. Try again.");
            }
        });





        // Step 6: Set button event handler for login
        loginButton.setOnAction(e -> {
            // Step 7: Validate login credentials
            if (validateLogin(usernameField.getText(), passwordField.getText())) {
                // Step 8: Display menu
                displayMenu(primaryStage);
            } else {
                // Step 9: Show an alert for incorrect login
                showAlert("Invalid Login", "Incorrect username or password. Try again.");
            }
        });

        // Step 10: Set the scene and show the stage
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Clothing Store Login");
        primaryStage.show();
    }

    private boolean validateLogin(String username, String password) {
        // Step 11: Use the ClothingStore class methods for validation
        return username.equals(clothingStore.getUsername()) && password.equals(clothingStore.getPassword());
    }

    private void displayMenu(Stage primaryStage) {
        // Step 12: Display menu
        GridPane menuGrid = new GridPane();
        menuGrid.setHgap(10);
        menuGrid.setVgap(10);
        menuGrid.setPadding(new Insets(20, 20, 20, 20));

        // Step 13: Retrieve item names and prices from ClothingStore
        String[] itemNames = clothingStore.getItemNames();
        for (int i = 0; i < itemNames.length; i++) {
            Label itemNameLabel = new Label(itemNames[i]);
            double itemPrice = clothingStore.getPrice(itemNames[i]);
            Label itemPriceLabel = new Label("$" + itemPrice);
            menuGrid.add(itemNameLabel, 0, i);
            menuGrid.add(itemPriceLabel, 1, i);
        }

        // Step 14: Create textfields and button for user input
        TextField itemTextField = new TextField();
        TextField quantityTextField = new TextField();
        Button calculateButton = new Button("Calculate Cost");

        // Step 15: Add input fields and button to the menu layout
        menuGrid.add(new Label("Select Item:"), 0, itemNames.length);
        menuGrid.add(itemTextField, 1, itemNames.length);
        menuGrid.add(new Label("Enter Quantity:"), 0, itemNames.length + 1);
        menuGrid.add(quantityTextField, 1, itemNames.length + 1);
        menuGrid.add(calculateButton, 0, itemNames.length + 2);

        // Step 16: Create the menu scene
        Scene menuScene = new Scene(menuGrid, 300, 400);

        // Step 17: Set button event handler for cost calculation
        calculateButton.setOnAction(e -> {
            // Step 18: Calculate and display cost
            calculateCost(itemTextField.getText(), quantityTextField.getText());
        });

        // Step 19: Set the scene and show the stage
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Clothing Store Menu");
        primaryStage.show();
    }

    private void calculateCost(String selectedItem, String quantity) {
        try {
            // Step 20: Validate user input
            int quantityValue = Integer.parseInt(quantity);

            double unitCost = clothingStore.getPrice(selectedItem);
            if (unitCost != -1) {
                // Step 21: Calculate and display cost details
                double preCost = clothingStore.calculateCost(selectedItem, quantityValue);
                double taxes = clothingStore.calculateTaxes(preCost);
                double totalCost = clothingStore.calculateTotalCost(preCost, taxes);

                showAlert("Cost Details", "Pre-Cost: $" + preCost + "\nTaxes: $" + taxes + "\nTotal Cost: $" + totalCost);
            } else {
                // Step 22: Show an alert for invalid item selection
                showAlert("Invalid Item", "Please select a valid item from the menu.");
            }
        } catch (NumberFormatException e) {
            // Step 23: Show an alert for invalid quantity
            showAlert("Invalid Input", "Please enter a valid quantity.");
        }
    }

    private void showAlert(String title, String content) {
        // Step 24: Show an alert dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
