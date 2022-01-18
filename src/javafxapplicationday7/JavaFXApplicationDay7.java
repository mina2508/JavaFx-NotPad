/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationday7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.MenuElement;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;

/**
 *
 * @author minak
 */
public class JavaFXApplicationDay7 extends Application {

    FileChooser fil_chooser;
    TextArea area;
    MenuItem fileItem1;
    MenuItem fileItem2;
    MenuItem fileItem3;
    SeparatorMenuItem filesep;
    MenuItem fileItem4;
    MenuItem editItem1;
    SeparatorMenuItem editsep1;
    MenuItem editItem2;
    MenuItem editItem3;
    MenuItem editItem4;
    MenuItem editItem5;
    SeparatorMenuItem editsep2;
    MenuItem editItem6;
    MenuItem helpItem;
//creatingAndAdding menues
    Menu fileMenu;
    Menu editMenu;
    Menu helpMenu;
    MenuBar mainBar;
    BorderPane root;
    Scene scene;

    DataInputStream dataIn;
    DataOutputStream dataOut;

    @Override
    public void init() throws Exception {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        fil_chooser = new FileChooser();
        area = new TextArea();

        //file menue items
        fileItem1 = new MenuItem("New");
        fileItem2 = new MenuItem("Open...");
        fileItem3 = new MenuItem("Save");
        filesep = new SeparatorMenuItem();
        fileItem4 = new MenuItem("Exit");
        //edit menue items
        editItem1 = new MenuItem("Undo");
        editsep1 = new SeparatorMenuItem();
        editItem2 = new MenuItem("Cut");
        editItem3 = new MenuItem("Copy");
        editItem4 = new MenuItem("Paste");
        editItem5 = new MenuItem("Delete");
        editsep2 = new SeparatorMenuItem();
        editItem6 = new MenuItem("SelectAll");
        //edit menue items
        helpItem = new MenuItem("About Notepad");
//creatingAndAdding menues
        fileMenu = new Menu("File");
        editMenu = new Menu("Edit");
        helpMenu = new Menu("Help");
        mainBar = new MenuBar();
        fileMenu.getItems().addAll(fileItem1, fileItem2, fileItem3, filesep, fileItem4);
        editMenu.getItems().addAll(editItem1, editsep1, editItem2, editItem3, editItem4, editItem5, editsep2, editItem6);
        helpMenu.getItems().add(helpItem);
        mainBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        root = new BorderPane();
        root.setTop(mainBar);
        root.setCenter(area);
        scene = new Scene(root, 500, 500);

    }

    @Override
    public void start(Stage primaryStage) {

        //actions
        fileItem1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("MakeSure");
                alert.setContentText("do you really wish to open NewOne");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    area.clear();
                } else {
                    System.out.println("cancel");
                }

            }
        });
        fileItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = fil_chooser.showOpenDialog(primaryStage);
                String path = file.getPath();
                if (file != null) {

                    try {
                        dataIn = new DataInputStream(new FileInputStream(path));

                        area.setText(dataIn.readUTF());

                        dataIn.close();
                    } catch (FileNotFoundException ex) {
                        System.out.println("fILEnOTfOUND");
                    } catch (IOException ex) {
                        Logger.getLogger(JavaFXApplicationDay7.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

        fileItem3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.print("minaa");
                File file = fil_chooser.showSaveDialog(primaryStage);
                if (file != null) {
                    File newFile = new File(file.getAbsolutePath());
                    try {
                        newFile.createNewFile();
                    } catch (IOException ex) {
                        Logger.getLogger(JavaFXApplicationDay7.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        dataOut = new DataOutputStream(new FileOutputStream(newFile.getPath()));
                        dataOut.writeUTF(area.getText());
                        dataOut.close();

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(JavaFXApplicationDay7.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(JavaFXApplicationDay7.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

        fileItem4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Platform.exit();
                } catch (Exception ex) {
                    Logger.getLogger(JavaFXApplicationDay7.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //actions
        editItem1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                area.undo();
            }
        });
        editItem2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                area.cut();
            }
        });
        editItem3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                area.copy();
            }
        });
        editItem3.setAccelerator(KeyCombination.keyCombination("ctrl+n"));

        editItem4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                area.paste();
            }
        });
        editItem4.setAccelerator(KeyCombination.keyCombination("ctrl+m"));
        editItem5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                area.deleteText(area.getSelection());
            }
        });
        editItem6.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                area.selectAll();
            }
        });
        helpItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Description");
                alert.setHeaderText("Developer");
                alert.setContentText("NotPad Made By Mina Kameel");
                alert.showAndWait();

            }
        });

//        root.getChildren().add(btn);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("JavaFX Notpad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
