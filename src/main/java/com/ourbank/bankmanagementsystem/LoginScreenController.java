package com.ourbank.bankmanagementsystem;

import com.ourbank.bankmanagementsystem.Login;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class LoginScreenController {
    public void openLoginScreen() throws Exception {
        Login login = new Login();
        Stage stage = new Stage();
        login.start(stage);
    }
}
