package application;

import Week7day4.Person;
import Week7day4.PersonCRUDV6;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class PersonSearchForm  extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
    FlowPane flowPane=new FlowPane();
    Scene scene=new Scene(flowPane);
    primaryStage.setScene(scene);
    primaryStage.setWidth(250);
    primaryStage.setHeight(200);
    primaryStage.show();
    
    //Declare
    Label lblTitle, lblName, lblPID, lblEmail;//Prompt for User
    TextField txtPID, txtName, txtEmail; //text Box
    Button btnSearch, btnClose;//push-up Button
    
    lblTitle = new Label ("Person Search Form  ");
    lblPID = new Label("PID : ");
    lblName = new Label("NAME : ");
    lblEmail = new Label("Email : ");
    
    txtPID = new TextField();
    txtName = new TextField();
    txtEmail = new TextField();
   
    
    btnSearch = new Button("Search");//Click Event Handle
    btnSearch. setOnAction(new EventHandler<ActionEvent>() {
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		System.out.println("Button btnSearch CLicked");
    		int pid = Integer.parseInt(txtPID.getText());//String to Integer
    		Person person = new PersonCRUDV6().searchPID(pid);
            if(person.getPID()>0) {
            	txtName.setText(person.getFullName());
            	txtEmail.setText(person.getEmail());
            }

    	else {
    		txtName.setText("NA");
    		txtEmail.setText("NA");
    		System.out.println("Person not Found");
    	}
   
    	}
	});
  
    
    
    btnClose = new Button("Close "); // Event Handle
    btnClose. setOnAction(new EventHandler<ActionEvent>() {
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		System.out.println("Button btnClose CLicked");
    		primaryStage.close(); //Close the current window
    		
    	}
    
	});
  
    flowPane.getChildren().add(lblTitle);
    flowPane.getChildren().add(lblPID);
    flowPane.getChildren().add(txtPID);
    flowPane.getChildren().add(lblName);
    flowPane.getChildren().add(txtName);
    flowPane.getChildren().add(lblEmail);
    flowPane.getChildren().add(txtEmail);
    flowPane.getChildren().add(btnSearch);
    flowPane.getChildren().add(btnClose);
    
     primaryStage.show();
     
    
    	
	}
	public static void main(String[] args) {
		launch (args);
		
		
	}

}
