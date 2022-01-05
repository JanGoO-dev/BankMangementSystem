package com.ourbank.bankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class UserLoginController {



    @FXML
    private TextField accountNo;

    private static Stage primaryStage;

//    @FXML
//    public void initialize(){
//
//        AccountLogin Account = new AccountLogin();
//        Account.acc = accountNo.getText();
//        System.out.println(accountNo.getText());
//
//    }

    @FXML
    void openUserDashboardScreen(ActionEvent event) throws Exception {

        AccountLogin Account = new AccountLogin();
        Account.acc = accountNo.getText();
        System.out.println(accountNo.getText());

        Stage s = MainApplication.getPrimaryStage();
        s.close();

        primaryStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("user_dashboard_screen.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        this.primaryStage.setTitle("JanGoO Bank | User Dashboard");
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();

    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }

}
