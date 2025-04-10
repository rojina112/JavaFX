package assignment.launcher;

import Assignment.UserLogin;

/**
 * Launcher class that properly sets up the JavaFX environment before starting the application.
 * This helps work around module system issues in Java 11+ with JavaFX.
 */
public class Launcher {
    public static void main(String[] args) {
        UserLogin.main(args);
    }
} 