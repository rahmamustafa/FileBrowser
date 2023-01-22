package gov.iti.gui.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import gov.iti.gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class fileBrowseController  {
    @FXML
    Button btnFileBrowse;
    @FXML
    TextField tfFileBrowse;
    @FXML
    TreeView tvFilesContainer;
    @FXML
    ListView lvFilesContainer2;

    @FXML
    ImageView directoryIcon;
    @FXML
    ImageView fileIcon;
    TreeItem<String> rootItem;

    @FXML
    void fileBrowse(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = new DirectoryChooser().showDialog(stage);
        if (file != null) {
            tfFileBrowse.setText(file.getPath());
        }
        lvFilesContainer2.getItems().clear();
        displayFiles(tfFileBrowse.getText());

    }

    @FXML
    void onAction() {
        lvFilesContainer2.getItems().clear();
        displayFiles(tfFileBrowse.getText());
    }

    void displayFiles(String fileName) {
        File rootFile = new File(fileName);
        String[] fileList = rootFile.list();
        if (fileList == null) {
            return;
        }
        rootItem = new TreeItem<String>(rootFile.getPath());
        System.out.println(rootFile.getPath());
        rootItem.setExpanded(true);
        for (String file : fileList) {
            Image image;
            if (new File(rootFile, file).isDirectory()) {
                image = directoryIcon.getImage();
                /*
                // src\main\resources\images\directoryIcon.png
                // ImageView folderImage = new ImageView(new
                // Image(getClass().getResourceAsStream("images\\directoryIcon.png")));
                */
                TreeItem folderItem = new TreeItem<String>(file, makImageView20X16(image));
                rootItem.getChildren().add(folderItem);
                displayFiles(new File(rootFile, file).getPath(), folderItem);
            } else {
                image = fileIcon.getImage();
                rootItem.getChildren().add(new TreeItem<String>(file, makImageView20X16(image)));
            }
            ImageView imag = makImageView20X16(image);
            lvFilesContainer2.getItems().add(new Label(file, imag));

        }
        // lvFilesContainer2.setItems();
        tvFilesContainer.setRoot(rootItem);
    }

    void displayFiles(String fileName, TreeItem<String> item) {
        File parentFile = new File(fileName);
        String[] fileList = parentFile.list();
        if (fileList == null) {
            return;
        }
        List<Label> ls=new LinkedList<>();
        for (String file : fileList) {
            Image image;
             //folderItem=null;
            if (new File(parentFile, file).isDirectory()) {
                image = directoryIcon.getImage();
            
            } else {
                image = fileIcon.getImage();
                item.getChildren().add(new TreeItem<String>(file, makImageView20X16(image)));
            }
            item.expandedProperty().addListener((observable, wasExpanded, isExpanded) -> {
                lvFilesContainer2.getItems().clear();
                if (isExpanded) {
                    for (Label l :ls) {
                        lvFilesContainer2.getItems().add(l);
                    }
                    if(new File(parentFile, file).isDirectory())
                    {
                        TreeItem folderItem = new TreeItem<String>(file, makImageView20X16(image));
                        item.getChildren().add(folderItem);
                        displayFiles(new File(parentFile, file).getPath(), folderItem);
                     }   
                    
                }
            });
            ls.add(new Label(file, makImageView20X16(image)));
            
        }

    }
    ImageView makImageView20X16(Image image){
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(16);
        return imageView;

    }
}
