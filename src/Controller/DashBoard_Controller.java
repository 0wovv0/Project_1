package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class DashBoard_Controller implements Initializable{
    @FXML
    private BorderPane borderPane;
    @FXML
    private ImageView home_icon, saved_icon, toeic_icon, setting_icon;
    @FXML
    public static AnchorPane contentAnchorPane;
    FXMLLoader contentLoader;
    SavedList_Controller controller;
    String currentTableName = "/FXML/Author.fxml";
    boolean check = false;
    private boolean allowChange = false;
    
    public DashBoard_Controller(){};

    @FXML
    public void savedAction() throws IOException{
        if(controller == null || check){
           check = false;
           controller = new SavedList_Controller("Saved_Table");
        }
        changeCenterBorderPane("/FXML/SavedList.fxml");
    }
    
    @FXML
    public void homeAction() throws IOException{
        changeCenterBorderPane("/FXML/Author.fxml");
    }
    
    @FXML
    public void toeicAction() throws IOException{
        changeCenterBorderPane("/FXML/ToeicTopic.fxml");
    }
    
    public void changeCenterBorderPane(String path) throws IOException {
        if(allowChange) return;
        if(currentTableName.equals(path) && check) return;
        contentAnchorPane = null;
        currentTableName = path;
        contentLoader = new FXMLLoader(getClass().getResource(path));
        if(path.equals("/FXML/SavedList.fxml")){
            contentLoader.setController(controller);
        }
        contentAnchorPane = contentLoader.load();
        borderPane.setCenter(contentAnchorPane);
    }
    
    public void setController(SavedList_Controller controller){
        this.controller = controller;
        check = true;
    }
    
    public void setAllowChange(boolean bool){
        this.allowChange = bool;
    }
    
    private void setOnMouseForButton(ImageView button){
        // Tạo Timeline để thực hiện hiệu ứng chuyển động
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(button.scaleXProperty(), 1.0), new KeyValue(button.scaleYProperty(), 1.0)),
                new KeyFrame(Duration.millis(200), new KeyValue(button.scaleXProperty(), 0.8), new KeyValue(button.scaleYProperty(), 0.8))
        );

        // Sự kiện khi con trỏ chuột vào
        button.setOnMouseEntered(event -> {
            timeline.setRate(1); // Đặt tốc độ chạy dương để phóng to
            timeline.playFromStart();
        });

        // Sự kiện khi con trỏ chuột rời đi
        button.setOnMouseExited(event -> {
            timeline.setRate(-1); // Đặt tốc độ chạy âm để thu nhỏ
            timeline.playFrom("end");
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOnMouseForButton(home_icon);
        setOnMouseForButton(saved_icon);
        setOnMouseForButton(toeic_icon);
    }
    
}