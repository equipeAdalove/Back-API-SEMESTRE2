package com.adalove.api.model.services;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImagePicker {
    private File selectedFile;


    public String getImagePath() {


        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.tiff")
        );
        selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            return selectedFile.getAbsolutePath();
        }
        else{
            return null;
        }
    }
}

