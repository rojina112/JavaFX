package Assignment;


	import javafx.application.Application;
	import javafx.geometry.Insets;
	import javafx.geometry.Pos;
	import javafx.scene.Scene;
	import javafx.scene.control.Alert;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.GridPane;
	import javafx.stage.Stage;

	import javax.mail.*;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;
	import java.util.Properties;

	public class EmailConfirmation extends Application {

	    private static final Properties props = null;

		@Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("Email Confirmation");

	        // Layout
	        GridPane grid = new GridPane();
	        grid.setPadding(new Insets(20));
	        grid.setHgap(10);
	        grid.setVgap(15);
	        grid.setAlignment(Pos.CENTER);

	        // UI Elements
	        Label lblEmail = new Label("Enter Email:");
	        TextField txtEmail = new TextField();
	        Button btnSend = new Button("Send Confirmation");

	        // Add elements to layout
	        grid.add(lblEmail, 0, 0);
	        grid.add(txtEmail, 1, 0);
	        grid.add(btnSend, 1, 1);

	        // Send Email Button Action
	        btnSend.setOnAction(e -> {
	            String email = txtEmail.getText();
	            if (email.isEmpty() || !email.contains("@")) {
	                showAlert(Alert.AlertType.ERROR, "Invalid Email", "Please enter a valid email.");
	            } else {
	                boolean emailSent = sendConfirmationEmail(email);
	                if (emailSent) {
	                    showAlert(Alert.AlertType.INFORMATION, "Success", "Confirmation email sent!");
	                } else {
	                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to send email.");
	                }
	            }
	        });

	        // Scene Setup
	        Scene scene = new Scene(grid, 400, 200);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    // Send Email using JavaMail
	    private boolean sendConfirmationEmail(String recipientEmail) {
	        final String senderEmail = "your-email@gmail.com";  // Replace with your email
	        final String senderPassword = "your-password";      // Replace with app password

	        
	        // Authenticator
	        Session session = Session.getInstance(props, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(senderEmail, senderPassword);
	            }
	        });

	        try {
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(senderEmail));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
	            message.setSubject("Email Confirmation");
	            message.setText("Thank you for signing up! Your email is now confirmed.");

	            Transport.send(message);
	            return true;
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    // Show Alert Method
	    private void showAlert(Alert.AlertType alertType, String title, String message) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
	}


