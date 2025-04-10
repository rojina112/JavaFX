module EmployeeEnrollmentSystems {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;
    requires javafx.base;
    
    opens Assignment to javafx.graphics, javafx.controls, javafx.base;
    opens application to javafx.graphics, javafx.controls, javafx.base;
    opens assignment.launcher to javafx.graphics, javafx.controls, javafx.base;
    opens DAO to javafx.base;
    opens Model to javafx.base;
    
    exports Assignment;
    exports DAO;
    exports Model;
    exports application;
    exports assignment.launcher;
} 