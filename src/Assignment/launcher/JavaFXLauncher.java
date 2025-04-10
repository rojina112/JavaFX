package assignment.launcher;

import Assignment.UserLogin;
import DAO.Mydatabase;
import java.io.File;

/**
 * This launcher class is used to start the JavaFX application properly.
 * It helps handle module-related errors and ensures proper JavaFX initialization.
 */
public class JavaFXLauncher {
    
    /**
     * Main method that delegates to the UserLogin class.
     * This avoids direct JavaFX application class loading issues.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Starting Employee Enrollment System...");
        
        // Verify database connection
        System.out.println("Testing database connection...");
        boolean dbConnected = Mydatabase.testConnection();
        
        if (!dbConnected) {
            System.err.println("WARNING: Unable to connect to the database.");
            System.err.println("The application will start, but functionality may be limited.");
            System.err.println("Please check your database configuration in DAO/Mydatabase.java");
            System.err.println("Make sure your MySQL server is running.");
            
            // Pause to let user read the message
            try {
                System.out.println("Continuing in 3 seconds...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // Ignore
            }
        } else {
            System.out.println("Database connection successful!");
        }
        
        // Set system properties for JavaFX if needed
        System.setProperty("javafx.preloader", "");
        
        // Check if the JavaFX runtime is properly accessible
        File javafxJar = new File("C:\\Program Files\\Software\\Java\\javafx-sdk-23.0.2\\lib\\javafx.controls.jar");
        if (!javafxJar.exists()) {
            System.err.println("WARNING: JavaFX libraries may not be properly configured.");
            System.err.println("If you encounter errors, check your module path settings.");
        }
        
        // Start the main application without relying on JavaFX launcher
        try {
            System.out.println("Launching UI...");
            UserLogin.main(args);
        } catch (Exception e) {
            System.err.println("Failed to launch application:");
            e.printStackTrace();
            System.exit(1);
        }
    }
} 