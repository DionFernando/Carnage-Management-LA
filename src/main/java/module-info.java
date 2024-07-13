module lk.carnage.carnagemanagementla {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires AnimateFX;
    requires java.datatransfer;
    requires java.desktop;
    requires javafx.media;
    requires jasperreports;

    opens lk.carnage.carnagemanagementla to javafx.fxml;
    opens lk.carnage.carnagemanagementla.controller to javafx.fxml;
    //opens lk.carnage.carnagemanagementla.view.tdm to java.base;
    opens lk.carnage.carnagemanagementla.view.tdm to javafx.base;

    exports lk.carnage.carnagemanagementla;
    exports lk.carnage.carnagemanagementla.controller;
}