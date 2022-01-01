package com.ourbank.bankmanagementsystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("welcome_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("JanGoO Bank | Welcome");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void openUserLoginScreen(ActionEvent actionEvent) throws IOException {
        this.primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("user_login_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.primaryStage.setTitle("JanGoO Bank | User Login");
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    public void openEmployeeLoginScreen(ActionEvent actionEvent) throws IOException {
        this.primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("employee_login_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.primaryStage.setTitle("JanGoO Bank | Employee Login");
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    public void openUserDashboardScreen(ActionEvent actionEvent) throws IOException {
        this.primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("user_dashboard_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.primaryStage.setTitle("JanGoO Bank | User Dashboard");
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    public void openEmployeeDashboardScreen(ActionEvent actionEvent) throws IOException {
        this.primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("employee_dashboard_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.primaryStage.setTitle("JanGoO Bank | Employee Dashboard");
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }
}