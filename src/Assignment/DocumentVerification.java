package Assignment;


	import javafx.application.Application;
	import javafx.scene.Scene;
	import javafx.scene.control.*;
	import javafx.scene.layout.GridPane;
	import javafx.stage.Stage;

	public class DocumentVerification extends Application {

	    @Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("Document Verification");

	        // Creating Labels
	        Label lblIdentification = new Label("Identification Documents:");
	        Label lblEducation = new Label("Educational Certificates:");
	        Label lblLegal = new Label("Legal Documents:");
	        Label lblBank = new Label("Bank Details:");
	        Label lblOther = new Label("Other Documents:");

	        // Creating ComboBoxes
	        ComboBox<String> cmbIdentification = new ComboBox<>();
	        cmbIdentification.getItems().addAll("Passport", "Driver's License", "National ID");

	        ComboBox<String> cmbEducation = new ComboBox<>();
	        cmbEducation.getItems().addAll("Degree Certificate", "Diploma", "Transcript");

	        ComboBox<String> cmbLegal = new ComboBox<>();
	        cmbLegal.getItems().addAll("Work Permit", "Tax Documents", "Contract");

	        // TextFields for Bank Details and Other Documents
	        TextField txtBank = new TextField();
	        TextField txtOther = new TextField();

	        // Verify Button
	        Button btnVerify = new Button("Verify");

	        // Layout Grid
	        GridPane grid = new GridPane();
	        grid.setVgap(10);
	        grid.setHgap(10);

	        // Adding Elements to Grid
	        grid.add(lblIdentification, 0, 0);
	        grid.add(cmbIdentification, 1, 0);
	        grid.add(lblEducation, 0, 1);
	        grid.add(cmbEducation, 1, 1);
	        grid.add(lblLegal, 0, 2);
	        grid.add(cmbLegal, 1, 2);
	        grid.add(lblBank, 0, 3);
	        grid.add(txtBank, 1, 3);
	        grid.add(lblOther, 0, 4);
	        grid.add(txtOther, 1, 4);
	        grid.add(btnVerify, 1, 5);

	        // Setting Scene
	        Scene scene = new Scene(grid, 400, 300);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
	}


