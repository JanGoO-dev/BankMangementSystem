package com.ourbank.bankmanagementsystem;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeDashboardController {

    public Label name;
    public Label FatherName;
    public Label CNIC;
    public Label Age;
    public Label DOB;
    public Label Gender;
    public Label MStatus;

    public Label AccountID;
    public Label Status;

    static String accID;

    private Stage primaryStage;

    @FXML private TableView<user> tableView;

    @FXML private TableColumn<user, String> idColumn;
    @FXML private TableColumn<user, String> nameColumn;
    @FXML private TableColumn<user, String> accountColumn;
    @FXML private TableColumn<user, String> balanceColumn;


    public void initialize(){


        dbConnection();

        idColumn.setCellValueFactory(new PropertyValueFactory<user, String>("count"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<user, String>("Name"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<user, String>("accountNumber"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<user, String>("balance"));

        tableView.setItems(getPeople());

    }

    public ObservableList<user> getPeople(){

        ObservableList<user> people = FXCollections.observableArrayList();

        String no, userName, accountN, userBal;
        int i = 1;
        try{

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");

            String query = "SELECT * from userpersonaldetails";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);


            while (rs.next()) {

                no = String.valueOf(i);
                accountN = String.valueOf(rs.getInt("AccountID"));
                userName = rs.getString("Name");
                userBal = rs.getString("Balance");

                people.add(new user(no, userName, accountN, userBal));
                i++;
            }
            st.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return people;

    }

    @FXML
    void dbConnection() {

        try {
            AccountLogin Account = new AccountLogin();
            accID = Account.acc;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");

            String query = "SELECT * from employeepersonaldetails WHERE AccountID=" + accID;
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);


            while (rs.next()) {
                int id = rs.getInt("AccountID");
                String status = rs.getString("Status");

                String Name = rs.getString("name");
                String fName = rs.getString("FatherName");
                String cnic = rs.getString("CNIC");
                int age = rs.getInt("Age");
                String dob = rs.getString("DOB");
                String gender = rs.getString("Gender");
                String mstatus = rs.getString("MStatus");

                AccountID.setText(String.valueOf(id));
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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // CREATE NEW ACCOUNT

    public TextField newName;
    public TextField newFatherName;
    public TextField newCNIC;
    public TextField newDOB;
    public TextField newGender;
    public TextField newMStatus;


    public void createNewAccount(ActionEvent actionEvent) throws IOException {


        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","");
            String newAge;

            String year = newDOB.getText().substring(newDOB.getText().length() - 4);
            newAge = String.valueOf(2022 - (Integer.parseInt(year)));
            System.out.println(year + " "+ newAge);

            String query = "INSERT INTO `userpersonaldetails`(`Age`, `Balance`, `Status`, `Name`, `FatherName`, `CNIC` , `DOB`, `Gender`, `MStatus`) VALUES ('" + newAge + "','" + "0" + "','Active','" + newName.getText() + "','" + newFatherName.getText() + "','" + newCNIC.getText() + "','" + newDOB.getText() + "','" + newGender.getText() + "','" + newMStatus.getText() + "')";
            Statement st = con.createStatement();
            st.executeUpdate(query);

            newName.clear();
            newMStatus.clear();
            newGender.clear();
            newDOB.clear();
            newCNIC.clear();
            newFatherName.clear();

            st.close();
            refresh();

        }catch(Exception e) {
            e.printStackTrace();
        }


    }

    public TextField deleteAccount;

    public void deleteAccount(ActionEvent actionEvent) throws IOException {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","");

            String query ="DELETE FROM userpersonaldetails where AccountID=" + deleteAccount.getText();
            Statement st = con.createStatement();
            st.executeUpdate(query);

            deleteAccount.clear();

            st.close();
            refresh();

        }catch(Exception e) {
            e.printStackTrace();
        }


    }

    public TextField depositAccount;
    public TextField deposit;


    public void depositMoney(ActionEvent actionEvent) throws IOException {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","");

            String query = "SELECT * from userpersonaldetails WHERE AccountID="+depositAccount.getText();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            String  bal = "Nada";

            while(rs.next()) {
                float balance = rs.getFloat("Balance");
                float currentBal = Float.parseFloat(deposit.getText());
                balance += currentBal;
                bal = String.valueOf(balance);
                deposit.clear();
            }
            st.close();

            st = con.createStatement();
            String query1 = "UPDATE userpersonaldetails SET Balance =" + bal + " WHERE AccountID="+depositAccount.getText();
            st.executeUpdate(query1);

            depositAccount.clear();
            depositAccount.clear();

            st.close();

            refresh();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() throws IOException{

        Stage s = EmployeeLoginController.getPrimaryStage();
        s.close();

        primaryStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("employee_dashboard_screen.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        this.primaryStage.setTitle("JanGoO Bank | Employee Dashboard");
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();

    }
}
