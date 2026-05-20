module github.com.pixayo.unimob {
    requires javafx.controls;
    requires javafx.fxml;


    opens github.com.pixayo.unimob to javafx.fxml;
    exports github.com.pixayo.unimob;
    exports github.com.pixayo.unimob.controller;
}