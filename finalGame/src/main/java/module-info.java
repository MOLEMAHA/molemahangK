module com.example.finalgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.finalgame to javafx.fxml;
    exports com.example.finalgame;
}