package Assignment;


	import javafx.application.Application;
	import javafx.scene.Scene;
	import javafx.scene.control.*;
	import javafx.scene.layout.GridPane;
	import javafx.stage.Stage;

	public class EmployeeEnrollment extends Application {

	    @Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("Employee Enrollment");

	        // Creating Labels
	        Label lblFirstName = new Label("First name:");
	        Label lblLastName = new Label("Last name:");
	        Label lblEmail = new Label("Email:");
	        Label lblPhone = new Label("Phone:");
	        Label lblPosition = new Label("Position:");
	        Label lblDepartment = new Label("Department:");

	        // Creating Input Fields
	        TextField txtFirstName = new TextField();
	        TextField txtLastName = new TextField();
	        TextField txtEmail = new TextField();
	        TextField txtPhone = new TextField();
	        TextField txtPosition = new TextField();
	        ComboBox<String> cmbDepartment = new ComboBox<>();
	        cmbDepartment.getItems().addAll("HR", "IT", "Finance", "Marketing");

	        // Submit Button
	        Button btnSubmit = new Button("Submit");

	        // Layout Grid
	        GridPane grid = new GridPane();
	        grid.setVgap(10);
	        grid.setHgap(10);

	        // Adding Elements to Grid
	        grid.add(lblFirstName, 0, 0);
	        grid.add(txtFirstName, 1, 0);
	        grid.add(lblLastName, 0, 1);
	        grid.add(txtLastName, 1, 1);
	        grid.add(lblEmail, 0, 2);
	        grid.add(txtEmail, 1, 2);
	        grid.add(lblPhone, 0, 3);
	        grid.add(txtPhone, 1, 3);
	        grid.add(lblPosition, 0, 4);
	        grid.add(txtPosition, 1, 4);
	        grid.add(lblDepartment, 0, 5);
	        grid.add(cmbDepartment, 1, 5);
	        grid.add(btnSubmit, 1, 6);

	        // Setting Scene
	        Scene scene = new Scene(grid, 350, 300);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
	}


