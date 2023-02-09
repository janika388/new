package com.example.vizsga_02_10_23_asztali_alkalmazas;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PancakeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.print("Indulj");
        // ######################
        // ##### TASK A #########
        ArrayList<Palacsinta> palacsintak = Palacsinta.loadPalacsintakFromCsv("palacsintak.csv");
        System.out.println("\r\n");

        // 3. - Legjobb minositesu
        ArrayList<Palacsinta> legjobbPalacsinta = new ArrayList<>();
        for (Palacsinta myPalacsinta: palacsintak) {
            if (myPalacsinta.minosites == 5) {
                legjobbPalacsinta.add(myPalacsinta);
            }
        }
        System.out.println("A legjobb minositestu palacsinta(k):");
        for (Palacsinta myPalacsinta: legjobbPalacsinta) {
            System.out.println(String.format("Palacsinta neve: %s, minositese: %d", myPalacsinta.nev, myPalacsinta.minosites));
        }
        System.out.println();

        // 4. - Sos palacsintak ara
        int sosPalacsintaAr = 0;
        for (Palacsinta myPalacsinta : palacsintak) {
            sosPalacsintaAr += myPalacsinta.ar;
        }
        System.out.println(String.format("A sos palacsintak osszesitett ara: %d", sosPalacsintaAr));
        System.out.println();

        // 6. - palacsintak 27%-al novelt arral
        System.out.println("Palacsintak 27%-al novelt arral:");
        for (Palacsinta myPalacsinta: palacsintak) {
            System.out.println(String.format("Palacsinta neve: %s, novelt ar: %d", myPalacsinta.nev, myPalacsinta.increasePrice(27)));
        }
        System.out.println();

        // ######################
        // ##### TASK B #########

        //region ######### Pastry Thickness #########
        Label pastryThicknessLabel = new Label("Milyen vastag legyen a palacsinta?");

        ToggleGroup pastryThicknessGroup = new ToggleGroup();

        RadioButton pastryThicknessThin = new RadioButton("Vekony");
        pastryThicknessThin.setToggleGroup( pastryThicknessGroup );

        RadioButton pastryThicknessNormal = new RadioButton("Normal");
        pastryThicknessNormal.setToggleGroup( pastryThicknessGroup );
        pastryThicknessNormal.setSelected(true);

        RadioButton pastryThicknessThick = new RadioButton("Vastag");
        pastryThicknessThick.setToggleGroup( pastryThicknessGroup );

        VBox pastryThickness = new VBox();
        pastryThickness.getChildren().addAll(pastryThicknessLabel, pastryThicknessThin, pastryThicknessNormal, pastryThicknessThick);
        //endregion

        //region ######### Extra Features #########
        CheckBox glutenFreeOption = new CheckBox("gluten mentes teszta");
        CheckBox lactoseFreeOption = new CheckBox("laktoz mentes teszta");

        VBox extraFeatures = new VBox(glutenFreeOption, lactoseFreeOption);
        //endregion

        //region ######### Filling type #########
        Label fillingTypeLabel = new Label("Milyen tolteléket legyen?");

        ArrayList<String> foOsszetevok = new ArrayList<>();
        for (Palacsinta myPalacsinta: palacsintak) {
            foOsszetevok.add(myPalacsinta.foOsszetevo);
        }

        ChoiceBox<String> fillingChoices = new ChoiceBox<>();
        fillingChoices.getItems().addAll(foOsszetevok);

        fillingChoices.setValue( fillingChoices.getItems().get(0) );

        VBox fillingType = new VBox(fillingTypeLabel, fillingChoices);
        //endregion

        //region ####### Comment section ########
        Label commentLabel = new Label("Megjegyzes:");

        TextArea commentArea = new TextArea();
        commentArea.setPrefSize(200, 100);

        VBox commentSection = new VBox(commentLabel, commentArea);
        //endregion

        //region ####### Review Button ########
        Button reviewButton = new Button("Elonezet");

        //endregion

        GridPane gpPancakeOrder = new GridPane();

        GridPane.setConstraints(pastryThickness, 0, 0);
        GridPane.setConstraints(extraFeatures, 0, 1);
        GridPane.setConstraints(fillingChoices, 1, 0);
        GridPane.setConstraints(commentSection, 1, 1);
        GridPane.setConstraints(reviewButton, 0, 2);

        gpPancakeOrder.setPadding(new Insets(40, 40, 40, 40));
        gpPancakeOrder.setVgap(20);
        gpPancakeOrder.setHgap(40);

        GridPane.setHalignment(reviewButton, HPos.CENTER);
        GridPane.setHgrow(reviewButton, Priority.ALWAYS);

        GridPane.setColumnSpan(reviewButton, 2);

        gpPancakeOrder.getChildren().addAll(pastryThickness, extraFeatures, fillingChoices, commentSection, reviewButton);

        Scene orderScene = new Scene(gpPancakeOrder, 800, 600);

        reviewButton.setOnAction((actionEvent) -> {
            String reviewText = "";

            reviewText += String.format("Palacsinta vastagsaga: %s \r\n", ((RadioButton) pastryThicknessGroup.getSelectedToggle()).getText());
            reviewText += String.format("Glutenmentes: %s \r\n", glutenFreeOption.isSelected() ? "Igen" : "Nem");
            reviewText += String.format("Laktozmentes: %s \r\n", lactoseFreeOption.isSelected() ? "Igen" : "Nem");
            reviewText += String.format("Toltelek: %s \r\n", fillingChoices.getValue());
            reviewText += String.format("Megjegyzes: %s \r\n", commentArea.getText());

            Label labelOrderReview = new Label(reviewText);

            Button orderButton = new Button("Megrendeles");
            Button backButton = new Button("Vissza");
            backButton.setOnAction((backActionEvent) -> {
                stage.setScene(orderScene);;
                stage.show();
            });

            HBox hboxButtons = new HBox(orderButton, backButton);

            VBox mainViewReview = new VBox(labelOrderReview, hboxButtons);
            mainViewReview.setPadding(new Insets(40, 40, 40, 40));

            Scene reviewScene = new Scene(mainViewReview, 800, 600);
            stage.setScene(reviewScene);
            stage.show();

        });

        stage.setTitle("Palacsintarendelő Program");
        stage.setScene(orderScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}