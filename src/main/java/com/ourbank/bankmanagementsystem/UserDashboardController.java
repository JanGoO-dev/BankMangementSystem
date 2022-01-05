package com.ourbank.bankmanagementsystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class UserDashboardController {


    public Label name;
    public Label FatherName;
    public Label CNIC;
    public Label Age;
    public Label DOB;
    public Label Gender;
    public Label MStatus;

    public Label Balance;
    public Label Status;
    public Label AccountID;

    public TextField deposit;
    public TextField withdraw;

    private Stage primaryStage;

    static String accID;

    public void initialize(){
        dbConnection();
    }


    @FXML
    void dbConnection(){

        try {
            AccountLogin Account = new AccountLogin();
            accID = Account.acc;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","");

            String query = "SELECT * from userpersonaldetails WHERE AccountID="+accID;
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);


            while(rs.next()) {
                int id = rs.getInt("AccountID");
                float balance = rs.getFloat("Balance");
                String status = rs.getString("Status");

                String Name = rs.getString("name");
                String fName = rs.getString("FatherName");
                String cnic = rs.getString("CNIC");
                int age = rs.getInt("Age");
                String dob = rs.getString("DOB");
                String gender = rs.getString("Gender");
                String mstatus = rs.getString("MStatus");

                AccountID.setText(String.valueOf(id));
                Balance.setText(String.valueOf(balance));
                Status.setText(status);

                name.setText(Name);
                FatherName.setText(fName);
                CNIC.setText(cnic);
                Age.setText(String.valueOf(age));
                DOB.setText(dob);
                Gender.setText(gender);
                MStatus.setText(mstatus);

            }
            st.close();

        }catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void depositMoney(ActionEvent actionEvent) throws IOException {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","");

            String query = "SELECT * from userpersonaldetails WHERE AccountID="+accID;
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            String  bal = "Nada";

            while(rs.next()) {
                float balance = rs.getFloat("Balance");
                float currentBal = Float.parseFloat(deposit.getText());
                balance += currentBal;
                bal = String.valueOf(balance);
                Balance.setText(bal);
                deposit.clear();
            }
            st.close();

            st = con.createStatement();
            String query1 = "UPDATE userpersonaldetails SET Balance =" + bal + " WHERE AccountID="+accID;
            st.executeUpdate(query1);

            st.close();
            refresh();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void withdrawMoney(ActionEvent actionEvent) throws IOException {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","");

            String query = "SELECT * from userpersonaldetails WHERE AccountID="+accID;
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            String  bal = "Nada";

            while(rs.next()) {
                float balance = rs.getFloat("Balance");
                float currentBal = Float.parseFloat(withdraw.getText());
                balance -= currentBal;
                bal = String.valueOf(balance);
                Balance.setText(bal);
                withdraw.clear();
            }
            st.close();

            st = con.createStatement();
            String query1 = "UPDATE userpersonaldetails SET Balance =" + bal + " WHERE AccountID="+accID;
            st.executeUpdate(query1);

            st.close();
            refresh();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() throws IOException{

        Stage s = UserLoginController.getPrimaryStage();
        s.close();

        primaryStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("user_dashboard_screen.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        this.primaryStage.setTitle("JanGoO Bank | User Dashboard");
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();

    }
}


