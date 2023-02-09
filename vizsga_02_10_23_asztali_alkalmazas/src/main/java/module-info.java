module com.example.vizsga_02_10_23_asztali_alkalmazas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vizsga_02_10_23_asztali_alkalmazas to javafx.fxml;
    exports com.example.vizsga_02_10_23_asztali_alkalmazas;
}