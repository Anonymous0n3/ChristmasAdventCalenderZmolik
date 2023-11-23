module com.example.christmasadventcalenderzmolik {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.christmasadventcalenderzmolik to javafx.fxml;
    exports com.example.christmasadventcalenderzmolik;
}