package Controller;

import DAO.Table_name_DAO;
import Model.Table_Name;
import Model.Word_Model;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import project_1.Project_1;
/**
 *
 * @author hokta
 */
public class ToeicTopic_Controller implements Initializable{
    @FXML
    private VBox VboxList;
    @FXML
    private ScrollPane scrollPane;

    ArrayList<Table_Name> arrayList = new ArrayList<>();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.setFitToWidth(true); // Đảm bảo HBox vừa khít theo chiều ngang
        scrollPane.setFitToHeight(true);
        Table_name_DAO tbD = new Table_name_DAO();
        arrayList = tbD.selectAll("List_of_table");
        System.err.println(arrayList.size());
        Insets hboxMargin = new Insets(5, 10, 0, 10);
        for(Table_Name tn : arrayList){
            HBox hbox = initVBox(tn);
            VboxList.getChildren().add(hbox);
            VboxList.setMargin(hbox, hboxMargin);
        }
    }
    
    public HBox initVBox(Table_Name t) {
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        
        vbox.setPrefHeight(75);
        vbox.setPrefWidth(500);
        vbox.setSpacing(5);

        // Thêm 2 Label vào VBox
        Label wordLabel = new Label("Chủ đề: " + t.getName());
        wordLabel.setFont(new Font("Times New Roman", 24));
        
        Label meanLabel = new Label("Score: " + t.getScore());
        meanLabel.setFont(new Font(20));
        
        // CHỉnh cho thụt vào so với VBox
        wordLabel.setPadding(new Insets(0, 0, 0, 20));
        meanLabel.setPadding(new Insets(0, 0, 0, 20));

        vbox.getChildren().addAll(wordLabel, meanLabel);
        vbox.setAlignment(Pos.CENTER_LEFT);

        // Đặt màu nền cho VBox
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#ADEBAD"), new CornerRadii(20), javafx.geometry.Insets.EMPTY);        
        Background background = new Background(backgroundFill);
        vbox.setBackground(background);
        hbox.setBackground(background);
        
        // Thêm hình ảnh và imageView
        Image image = new Image(getClass().getResourceAsStream("/Image/" + t.getName() + ".jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100); // Đặt chiều rộng của ImageView
        imageView.setFitHeight(100); // Đặt chiều cao của ImageView
        
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(20); // Đặt bán kính bo tròn theo chiều rộng
        clip.setArcHeight(20); // Đặt bán kính bo tròn theo chiều cao
        imageView.setClip(clip);
        
        hbox.getChildren().addAll(vbox, imageView);
        
        // Xử lý sự kiện khi VBox được click
        hbox.setOnMouseClicked(event -> {
            try {
                SavedList_Controller controller = new SavedList_Controller(t.getName());
                project_1.Project_1.controller.setController(controller);
                project_1.Project_1.controller.changeCenterBorderPane("/FXML/SavedList.fxml");
            } catch (IOException ex) {
                Logger.getLogger(ToeicTopic_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return hbox;
    }
}