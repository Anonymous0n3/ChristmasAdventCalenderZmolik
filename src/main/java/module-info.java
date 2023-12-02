module com.example.christmasadventcalenderzmolik {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.christmasadventcalenderzmolik to javafx.fxml;
    exports com.example.christmasadventcalenderzmolik;
}