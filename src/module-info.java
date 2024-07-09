module sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens sample to javafx.fxml;
    exports sample;
    exports sample.db;
    opens sample.db to javafx.fxml;
    exports sample.view;
    opens sample.view to javafx.fxml;
}