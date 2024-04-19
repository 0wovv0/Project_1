package Controller;

import DAO.Table_name_DAO;
import DAO.Words_DAO;
import Model.Table_Name;
import Model.Word_Model;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import project_1.Project_1;


public class SavedList_Controller implements Initializable{
    ///////////////
    boolean check = false;
    Button preButton;
        
    private Label countdownLabel;
    private int secondsRemaining = 60; // Thời gian đếm ngược ban đầu
    private Label wordLabel = new Label();
    private HBox hbox = new HBox();
    private GridPane gridPane;
    
    private int sizeOfArrayList;
    private int current_index;
    private IntegerProperty score = new SimpleIntegerProperty(0);
    private Button goBackButton;
    //////////////////
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox VboxList;
    @FXML
    private Label topicLabel;
    @FXML
    private Button TEST1_BTN, TEST2_BTN, TEST3_BTN, TEST4_BTN, finalTestBTN;
    @FXML
    private AnchorPane anchorPane;
    
    private ArrayList<Word_Model> arrayList = new ArrayList();
    
    private String tableName;
    
    public SavedList_Controller(String tableName){
        this.tableName = tableName;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        countdownLabel = new Label();
        countdownLabel.setFont(new Font("Times New Roman", 20));
        Words_DAO word_dao = new Words_DAO();
        arrayList = word_dao.selectAll(tableName);
        sizeOfArrayList = arrayList.size();
        Insets hboxMargin = new Insets(10, 10, 0, 10);
        VboxList.setPrefSize(630, 555);
        for(Word_Model tv : arrayList) {
            HBox hbox = initHBox(tv);
            VboxList.getChildren().add(hbox);
            VboxList.setMargin(hbox, hboxMargin);
        }
        if(tableName.equals("Saved_Table")){
            this.topicLabel.setText("Từ đang học");
        }
        else{
            this.topicLabel.setText("Chủ đề: " + tableName);
        }
        // Tạo một GridPane
        this.gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        
        
        TEST2_BTN.setOnAction(e -> {
            try {
                project_1.Project_1.controller.setAllowChange(true);
                EchTuVung();
            } catch (IOException ex) {
                Logger.getLogger(TESTController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Khong the gan su kien cho nut echtuVung");
            }
        });
        TEST1_BTN.setOnAction(e -> {
            try {
                project_1.Project_1.controller.setAllowChange(true);
                make_Pair();
            } catch (IOException ex) {
                Logger.getLogger(TESTController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Khong the gan su kien cho nut echtuVung");
            }
        });
        TEST3_BTN.setOnAction(e -> {
            try {
                project_1.Project_1.controller.setAllowChange(true);
                Learn();
            } catch (IOException ex) {
                Logger.getLogger(TESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        TEST4_BTN.setOnAction(e -> {
            project_1.Project_1.controller.setAllowChange(true);
            tracNghiem();
        });
        
        finalTestBTN.setOnAction(e -> {
            project_1.Project_1.controller.setAllowChange(true);
            finalTest();
        });
    }
    
    public Button initButtonUpdate(Word_Model t) {
        Button buttonUpdate = new Button("Update");
        buttonUpdate.setPrefSize(90, 35);
        buttonUpdate.setFont(new Font("Times New Roman", 20));
        buttonUpdate.setStyle("-fx-background-color: #BBF7D0; -fx-background-radius: 5;");

        // Set Action
        buttonUpdate.setOnAction(event -> {
            System.out.println("Clicked Update BTN\n");

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update");

            ButtonType buttonTypeConfirm = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonTypeCancel = new ButtonType("Hủy bỏ", ButtonBar.ButtonData.CANCEL_CLOSE);

            dialog.getDialogPane().getButtonTypes().addAll(buttonTypeConfirm, buttonTypeCancel);

            TextField textWord = new TextField(t.getWord());
            TextField textMeans = new TextField(t.getMean());
            TextField textIpa = new TextField(t.getIpa());
            TextField textGenre = new TextField(t.getGenre());

            System.out.println(t.getIpa() + "\n" + t.getGenre());

            GridPane gridPane = new GridPane();
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.addRow(1, new Label("WORD: "), textWord);
            gridPane.addRow(2, new Label("MEANS:"), textMeans);
            gridPane.addRow(3, new Label("IPA:"), textIpa);
            gridPane.addRow(4, new Label("GENRE:"), textGenre);

            dialog.getDialogPane().setContent(gridPane);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == buttonTypeConfirm) {
                    // Retrieve data from text fields when "Xác nhận" is clicked
                    Word_Model wm = new Word_Model(
                            textWord.getText(),
                            textMeans.getText(),
                            textIpa.getText(),
                            textGenre.getText()
                    );

                    Words_DAO wd = new Words_DAO();
                    wd.update(t.getWord(), wm, tableName);

                }
                return null;
            });

            dialog.showAndWait();
        });

        return buttonUpdate;
    }

    public Button initButtonDelete(Word_Model t) {
        Button buttonDelete = new Button("Delete");
        buttonDelete.setFont(new Font("Times New Roman", 20));
        buttonDelete.setPrefSize(90, 35);
        buttonDelete.setStyle("-fx-background-color: #FFF2C6; -fx-background-radius: 8;");
        buttonDelete.setId("Delete_" + t.getWord());
        //Set Action
        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationDialog.setTitle("Xác nhận");
                confirmationDialog.setHeaderText("Hihii");
                confirmationDialog.setContentText("Bạn có chắc chắn muốn tiếp tục không?");

                // Thêm nút Đồng ý và Hủy bỏ
                ButtonType buttonTypeYes = new ButtonType("Đồng ý");
                ButtonType buttonTypeCancel = new ButtonType("Hủy bỏ");

                confirmationDialog.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

                // Hiển thị cửa sổ thông báo và đợi cho người dùng chọn
                confirmationDialog.showAndWait().ifPresent(response -> {
                    if (response == buttonTypeYes) {
                        System.out.println("Người dùng đã chọn Đồng ý");
                        Words_DAO wd = new Words_DAO();
                        wd.delete(t, tableName);
                        System.out.println("Deleted!\n");
                        // Thêm hành động khi người dùng chọn Đồng ý ở đây
                    } else if (response == buttonTypeCancel) {
                        System.out.println("Người dùng đã chọn Hủy bỏ");
                        // Thêm hành động khi người dùng chọn Hủy bỏ ở đây
                    }
                });
            }
        });
        return buttonDelete;
    }
    
    
    
    public Button initButtonAdd(Word_Model t) {
        Button buttonDelete = new Button("Thêm");
        buttonDelete.setFont(new Font("Times New Roman", 20));
        buttonDelete.setPrefSize(90, 35);
        buttonDelete.setStyle("-fx-background-color: #FFF2C6; -fx-background-radius: 8;");
        buttonDelete.setId("Delete_" + t.getWord());
        //Set Action
        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationDialog.setTitle("Xác nhận");
                confirmationDialog.setHeaderText("Thêm vào từ đang học?");
                confirmationDialog.setContentText("Bạn có chắc chắn muốn tiếp tục không?");

                // Thêm nút Đồng ý và Hủy bỏ
                ButtonType buttonTypeYes = new ButtonType("Đồng ý");
                ButtonType buttonTypeCancel = new ButtonType("Hủy bỏ");

                confirmationDialog.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

                // Hiển thị cửa sổ thông báo và đợi cho người dùng chọn
                confirmationDialog.showAndWait().ifPresent(response -> {
                    if (response == buttonTypeYes) {
                        System.out.println("Người dùng đã chọn Đồng ý");
                        Words_DAO wd = new Words_DAO();
                        wd.insert(t, "Saved_Table");
                        // Thêm hành động khi người dùng chọn Đồng ý ở đây
                    }
                });
            }
        });
        return buttonDelete;
    }
    
    public VBox initVBox(Word_Model t) {
        VBox vbox = new VBox();
        vbox.setPrefSize(330, 70);
        vbox.setSpacing(5);
        
        Insets hboxMargin = new Insets(0, 0, 0, 20);
        // Thêm 3 Label vào VBox
        Label wordLabel = new Label(t.getWord());
        Label meanLabel = new Label(t.getMean());
        Label ghiChuLabel = new Label(t.getIpa());
        // Đặt cỡ chữ cho các Label
        wordLabel.setFont(new Font("Times New Roman", 22));
        meanLabel.setFont(new Font("Times New Roman", 18));
        ghiChuLabel.setFont(new Font("Times New Roman",18));

        vbox.getChildren().addAll(wordLabel, meanLabel, ghiChuLabel);
        vbox.setPadding(hboxMargin);
        vbox.setAlignment(Pos.CENTER_LEFT);
        

        return vbox;
    }
    
    public HBox initHBox(Word_Model wm) {
        HBox hbox = new HBox();
        hbox.setPrefSize(500.0, 75.0);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-background-color: #ADEBAD; -fx-background-radius: 15;");
        Insets hboxPadding = new Insets(15);
        hbox.setPadding(hboxPadding);
        Button buttonDelete1;
        Button buttonDelete2;
        Button buttonUpdate = initButtonUpdate(wm);
        buttonDelete1 = initButtonAdd(wm);
        VBox vbox = initVBox(wm);
        if(tableName.equals("Saved_Table")){
            
            buttonDelete1 = initButtonAdd(wm);
            Label genreLabel = new Label(wm.getGenre());
            genreLabel.setFont(new Font("Times New Roman", 18));
            genreLabel.setPrefSize(40, 30);
            hbox.getChildren().addAll(vbox,genreLabel, buttonUpdate, buttonDelete1);
        }
        else{
            buttonDelete2 = initButtonDelete(wm);
            hbox.getChildren().addAll(vbox, buttonUpdate, buttonDelete1, buttonDelete2);
        }
        

        hbox.setSpacing(10);
        
        hbox.setId(wm.getWord());

        return hbox;
    }
    
    @FXML
    private void handleButtonClick(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        BorderPane borderPane =(BorderPane) stage.getScene().getRoot();
        
        FXMLLoader contentLoader = new FXMLLoader(getClass().getResource("/FXML/TEST.fxml"));
        TESTController controller = new TESTController(tableName);
        contentLoader.setController(controller);
        Parent Testparent = contentLoader.load();
        borderPane.setCenter(Testparent);
    }
    
    @FXML
    public void EchTuVung() throws IOException{
        hireButton();
        score.set(0);
        secondsRemaining = 60;
        // Start the Timeline if it is not already running
        if (timelineCountdown.getStatus() != Animation.Status.RUNNING) {
            timelineCountdown.setCycleCount(Timeline.INDEFINITE);
            timelineCountdown.play();
        }
        updateWord();
    }
    
    private void updateWord(){
        hbox.getChildren().clear();
        gridPane.getChildren().clear();
        Label newLabel = new Label("_");
        newLabel.setFont(new Font(28));
        hbox.getChildren().add(newLabel);

        Random random = new Random();
        int randomNumber = random.nextInt(sizeOfArrayList - 1);
        String word = arrayList.get(randomNumber).getWord();
        wordLabel.setText(arrayList.get(randomNumber).getMean());
        wordLabel.setFont(new Font("Times New Roman", 30));
        wordLabel.setPrefSize(750, 30);
        wordLabel.setAlignment(Pos.CENTER);

        List<String> characters = splitAndShuffle(word);
        // Tạo danh sách các nút
        List<Button> buttons = new ArrayList<>();
        for (String character : characters) {
            Button button = createminiButton(String.valueOf(character));
            button.setOnAction(event -> {
                if((button.getText()).equals(String.valueOf(word.charAt(current_index)))){
                    button.setVisible(false);
                    updateHBox(hbox, String.valueOf(word.charAt(current_index)));
                    current_index++;
                    score.set(score.get() + 1);
                    if(current_index == word.length()){
                        secondsRemaining += 2;
                        current_index = 0;
                        updateWord();
                    }
                }
                else{
                    if(score.get() > 0)
                        score.set(score.get() - 1);
                }
            });
            buttons.add(button);
        }

        // Thêm các nút vào GridPane với tối đa 4 phần tử trên mỗi dòng
        int columnCount = 0;
        int rowCount = 0;
        for (Button button : buttons) {
            gridPane.add(button, columnCount, rowCount);
            columnCount++;
            if (columnCount == 5) {
                columnCount = 0;
                rowCount++;
            }
        }

        anchorPane.getChildren().clear();
        
        anchorPane.getChildren().add(hbox);
        anchorPane.setTopAnchor(hbox, 120.0);
        anchorPane.setLeftAnchor(hbox, 300.0);
        
        anchorPane.getChildren().add(wordLabel);
        anchorPane.setTopAnchor(wordLabel, 80.0);

        anchorPane.getChildren().add(gridPane);
        anchorPane.setTopAnchor(gridPane, 180.0); // Adjust the top margin as needed
        anchorPane.setLeftAnchor(gridPane, 70.0);
                
        addScoreLabel();
        addCountDownLabel();
    }
    
    private void addScoreLabel(){
        Label scoreLabel = new Label("Score: " + score);
        scoreLabel.setFont(new Font(20));
        scoreLabel.setStyle("-fx-padding: 0 0 0 50;");
        scoreLabel.textProperty().bind(Bindings.concat("Score: ").concat(score.asString()));
        anchorPane.getChildren().add(scoreLabel);
    }
  
    
    @FXML
    public void make_Pair() throws IOException{
        hireButton();
        current_index = 0;
        score.set(0);
        secondsRemaining = 0;
        // Start the Timeline if it is not already running
        if (timelineCount60.getStatus() != Animation.Status.RUNNING) {
            timelineCount60.setCycleCount(Timeline.INDEFINITE);
            timelineCount60.play();
        }
        updatePair();
    }
    
    private void updatePair() {
        check = false;
        
        // Make a set of random indices
        Set<Integer> randomSet = new HashSet<>();
        int randomNumber;
        int number_of_element = 0;
        Random random = new Random();
        while (number_of_element < 8) {
            randomNumber = random.nextInt(sizeOfArrayList - 1);
            if (!randomSet.contains(randomNumber)) {
                randomSet.add(randomNumber);
                number_of_element++;
            }
        }

        // Convert set to a list
        List<Integer> listFromSet = List.copyOf(randomSet);
        List<Button> buttons = new ArrayList<>();

        // Create buttons for each word pair
        for (int i = 0; i < 8; ++i) {
            Button button1 = createButton(arrayList.get(listFromSet.get(i)).getMean(), "M" + i);
            button1.setPrefSize(140, 75);
            button1.setWrapText(true);
            button1.setAlignment(Pos.CENTER);
            button1.setStyle("-fx-background-color: #E6FFD5; -fx-border-radius: 5px; -fx-background-radius: 5px;");
            button1.setFont(new Font("Times New Roman", 20));

            Button button2 = createButton(arrayList.get(listFromSet.get(i)).getWord() + "\n" + arrayList.get(listFromSet.get(i)).getIpa(), "W" + i);
            button2.setPrefSize(140, 75);
            button2.setWrapText(true);
            button2.setAlignment(Pos.CENTER);
            button2.setStyle("-fx-background-color: #E6FFD5; -fx-border-radius: 5px; -fx-background-radius: 5px;");
            button2.setFont(new Font("Times New Roman", 20));
            
            buttons.add(button1);
            buttons.add(button2);
        }

        // Shuffle the buttons
        Collections.shuffle(buttons);

        // Add buttons to the GridPane
        int columnCount = 0;
        int rowCount = 0;
        for (Button button : buttons) {
            gridPane.add(button, columnCount, rowCount);
            columnCount++;
            if (columnCount == 4) {
                columnCount = 0;
                rowCount++;
            }
        }

        // Clear and update the AnchorPane
        anchorPane.getChildren().clear();

        anchorPane.getChildren().add(gridPane);
        anchorPane.setTopAnchor(gridPane, 80.0);
        anchorPane.setLeftAnchor(gridPane, 50.0);

        addCountDownLabel();
    }
    
    private void addCountDownLabel(){
        anchorPane.getChildren().add(countdownLabel);
        anchorPane.setTopAnchor(countdownLabel, 25.0);
        anchorPane.setLeftAnchor(countdownLabel, 520.0);
    }
    
    private void addGoBackButton(){
        if(this.goBackButton == null){
            this.goBackButton = new Button("Go back");
            this.goBackButton.setStyle("-fx-background-color: #ADEBAD; -fx-background-radius: 10;");
            this.goBackButton.setOnAction(event -> {
                try {
                    goBack();
                } catch (IOException ex) {
                    Logger.getLogger(TESTController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        
        anchorPane.getChildren().add(goBackButton);
        anchorPane.setTopAnchor(goBackButton, 10.0);
        anchorPane.setLeftAnchor(goBackButton, 50.0);
    }

    @FXML
    public void Learn() throws IOException{
        hireButton();
        current_index = 0;
        score.set(0);
        secondsRemaining = 0;
        // Start the Timeline if it is not already running
        if (timelineCountUnlimited.getStatus() != Animation.Status.RUNNING) {
            timelineCountUnlimited.setCycleCount(Timeline.INDEFINITE);
            timelineCountUnlimited.play();
        }
        makeCard();
    }

    private void makeCard() {
        check = true;
        current_index = 0;
        // Tạo AnchorPane chính
//        AnchorPane mainAnchorPane = new AnchorPane();       
        AnchorPane mainAnchorPane = makeAnchorPane(450.0, 370.0);
        // Tạo AnchorPane ở trên cùng
        AnchorPane topAnchorPane = new AnchorPane();
        topAnchorPane.setPrefSize(330, 250);
        Label label1 = new Label(arrayList.get(current_index).getMean());
        label1.setFont(new Font(25));
        label1.setPrefSize(650, 30);
        label1.setAlignment(Pos.CENTER);

        Label label2 = new Label(arrayList.get(current_index).getIpa());
        label2.setFont(new Font(25));
        label2.setPrefSize(650, 30);
        label2.setAlignment(Pos.CENTER);

        topAnchorPane.getChildren().addAll(label1, label2);

        // Đặt label1 ở giữa theo cả chiều ngang và chiều dọc
        topAnchorPane.setTopAnchor(label1, 90.0);

        // Đặt label2 ở trung tâm theo chiều ngang
        topAnchorPane.setTopAnchor(label2, 120.0);

        // Thêm 2 Button và một Label ở giữa
        Label midLabel = new Label("");
        if(current_index > 8)
            midLabel.setText("" + (current_index+1) + "  /  " + sizeOfArrayList);
        else midLabel.setText("0" + (current_index+1) + "  /  " + sizeOfArrayList);
        midLabel.setFont(new Font(20));

        Button preButton = new Button("Trước");
        preButton.setStyle(
            "-fx-border-width: 2; " +
            "-fx-border-color: white; " +
            "-fx-background-color: #ADEBAD; " +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10"
        );

        preButton.setFont(Font.font("Bold", 18)); // Font name, size
        preButton.setPrefSize(80, 35);
        preButton.setOnAction(e -> {
            label2.setOpacity(1);
            check = true;
            if (current_index == 0) {
                current_index = sizeOfArrayList - 1;
            } else {
                    current_index--;
            }

            updateLabels(() -> {
                label1.setText(arrayList.get(current_index).getMean());
                label2.setText(arrayList.get(current_index).getIpa());
                if(current_index > 8)
                    midLabel.setText("" + (current_index+1) + "  /  " + sizeOfArrayList);
                else 
                    midLabel.setText("0" + (current_index+1) + "  /  " + sizeOfArrayList);
            });


        });

        Button afterButton = new Button("Sau");
        afterButton.setStyle("-fx-border-width: 2; -fx-border-color: white;-fx-background-color: #ADEBAD;-fx-border-radius: 10");
        afterButton.setFont(Font.font("Bold", 18)); // Font name, size
        afterButton.setPrefSize(80, 35);
        afterButton.setOnAction(e -> {
            check = true;
            label2.setOpacity(1);
            if (current_index == sizeOfArrayList - 1) {
                current_index = 0;
            } else {
                    current_index++;
            }
            updateLabels(() -> {
                label1.setText(arrayList.get(current_index).getMean());
                label2.setText(arrayList.get(current_index).getIpa());
                if(current_index > 8)
                    midLabel.setText("" + (current_index+1) + "  /  " + sizeOfArrayList);
                else 
                    midLabel.setText("0" + (current_index+1) + "  /  " + sizeOfArrayList);
            });
        });
        
        
        
        mainAnchorPane.getChildren().add(midLabel);
        mainAnchorPane.setTopAnchor(midLabel, 295.0);
        mainAnchorPane.setLeftAnchor(midLabel, 290.0);

        // Add các thuộc tính vào AnchorPane chính
        mainAnchorPane.getChildren().add(topAnchorPane);

        mainAnchorPane.getChildren().add(preButton);
        mainAnchorPane.setTopAnchor(preButton, 290.0);
        mainAnchorPane.setLeftAnchor(preButton, 200.0);

        mainAnchorPane.getChildren().add(afterButton);
        mainAnchorPane.setTopAnchor(afterButton, 290.0);
        mainAnchorPane.setLeftAnchor(afterButton, 380.0);

        topAnchorPane.setStyle("-fx-background-color: #ADEBAD; -fx-background-radius: 10;"); // Set background color and curved border

        anchorPane.getChildren().clear();
        
        anchorPane.getChildren().add(mainAnchorPane);
        anchorPane.setTopAnchor(mainAnchorPane, 100.0);
        anchorPane.setLeftAnchor(mainAnchorPane, 50.0);
        
        addCountDownLabel();
        addGoBackButton();
    
        topAnchorPane.setOnMouseClicked(event -> {
            resizeAnchorPane(topAnchorPane, label1, label2);
        });
    }
    
    void update1Labeland4Button(Label label, Button button1, Button button2, Button button3, Button button4) {
        Set<Integer> randomSet = new HashSet<>();
        int randomNumber;
        int number_of_element = 0;
        Random random = new Random();

        while (number_of_element < 4) {
            randomNumber = random.nextInt(sizeOfArrayList - 1);
            if (!randomSet.contains(randomNumber)) {
                randomSet.add(randomNumber);
                number_of_element++;
            }
        }

        // Convert set to a list
        List<Integer> listFromSet = List.copyOf(randomSet);
        randomNumber = listFromSet.get(random.nextInt(3));
        while(randomNumber == current_index){
            randomNumber = listFromSet.get(random.nextInt(3));
        }

        // Set text for the label using the provided label parameter
        label.setText(arrayList.get(randomNumber).getMean());
        label.setId("L" + randomNumber);
        current_index = randomNumber;

        // Use a Map for better readability
        Map<Button, Integer> buttonMap = Map.of(
                button1, listFromSet.get(0),
                button2, listFromSet.get(1),
                button3, listFromSet.get(2),
                button4, listFromSet.get(3)
        );

        // Set text and ID for each button
        buttonMap.forEach((btn, index) -> {
            Word_Model word = arrayList.get(index);
            btn.setText(word.getWord() + "\n" + word.getIpa());
            btn.setId("B" + index);
        });
    }
    
    @FXML
    private void finalTest() {
        hireButton();
        secondsRemaining = sizeOfArrayList * 10;
        if (timelineCountdown.getStatus() != Animation.Status.RUNNING) {
            timelineCountdown.setCycleCount(Timeline.INDEFINITE);
            timelineCountdown.play();
        }

        VBox vbox = new VBox();
        vbox.setSpacing(15);
        Insets vboxMargin = new Insets(10, 0, 0, 20); // Insets(top, right, bottom, left)
        VBox.setMargin(vbox, vboxMargin);

        ScrollPane scrollPane2 = new ScrollPane(vbox);
        scrollPane2.setPrefSize(700, 410);
        scrollPane2.setStyle("-fx-background-color: #8FDB76; -fx-border-width: 2;-fx-background-radius: 15");
        scrollPane2.setFitToWidth(true);
        scrollPane2.setFitToHeight(true);

        Collections.shuffle(arrayList);
        for (int i = 0; i < sizeOfArrayList; ++i) {
            AnchorPane childAnchorPane = makeAnchorPane(650, 400);
            childAnchorPane.setId("anchorPane" + i);
            Label label1 = new Label(arrayList.get(i).getMean());
            label1.setFont(new Font(25));
            label1.setPrefSize(600, 30);
            label1.setAlignment(Pos.CENTER);

            Label label2 = new Label(arrayList.get(i).getIpa());
            label2.setFont(new Font(25));
            label2.setPrefSize(600, 30);
            label2.setAlignment(Pos.CENTER);

            TextField textField = new TextField();
            textField.setPromptText("Điền vào đây");
            textField.setPrefSize(550, 25);
            textField.setFont(new Font(20));
            textField.setId("T" + i);
            textField.setStyle("-fx-background-radius: 15;");

            childAnchorPane.getChildren().addAll(label1, label2, textField);

            childAnchorPane.setTopAnchor(label1, 40.0);
            childAnchorPane.setTopAnchor(label2, 70.0);
            childAnchorPane.setTopAnchor(textField, 225.0);
            childAnchorPane.setLeftAnchor(textField, 20.0);
            childAnchorPane.setBottomAnchor(textField, 30.0);

            vbox.getChildren().add(childAnchorPane);
        }

        AnchorPane finalAnchorPane = makeAnchorPane(500, 300);
        Label resultLabel = new Label("Tất cả đã xong!\nBạn đã sẵn sàng nộp bài kiểm tra?");
        resultLabel.setFont(new Font("Times New Roman", 25));
        resultLabel.setPrefSize(600, 60);
        resultLabel.setAlignment(Pos.CENTER);

        Button checkButton = new Button("Nộp bài!");
        checkButton.setStyle("-fx-border-width: 2px; -fx-border-color: #8FDB76; -fx-background-color: #ADEBAD; -fx-background-radius: 15;");
        checkButton.setFont(new Font("Times New Roman", 25));
        checkButton.setPrefSize(150.0, 35.0);
        setOnMouseForButton(checkButton);
        
        
        finalAnchorPane.getChildren().addAll(resultLabel, checkButton);
        finalAnchorPane.setTopAnchor(resultLabel, 60.0);
        finalAnchorPane.setTopAnchor(checkButton, 175.0);
        finalAnchorPane.setLeftAnchor(checkButton, 200.0);
        vbox.getChildren().add(finalAnchorPane);
        
        checkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (showAlertFinalTest()) {
                    int trueAnswers = 0;
                    for (int i = 0; i < sizeOfArrayList; ++i) {
                        TextField textField = (TextField) vbox.lookup("#T" + i);
                        if (textField != null) {
                            String userInput = processString(textField.getText());
                            String correctAnswer = processString(arrayList.get(i).getWord());

                            if (userInput.equals(correctAnswer)) {
                                trueAnswers++;
                            } else {
                                AnchorPane anchorPane = (AnchorPane) vbox.lookup("#anchorPane" + i);
                                if (anchorPane != null) {
                                    anchorPane.setStyle("-fx-background-color: #f5ae91; -fx-background-radius: 10;");
                                }
                            }
                        } else {
                            System.out.println("TextField with id 'T" + i + "' not found.");
                        }
                    }
                    vbox.getChildren().remove(vbox.getChildren().get(vbox.getChildren().size() - 1));
                    
                    Label resultLabel = new Label("Bạn đã làm đúng: " + trueAnswers + "  /  " + sizeOfArrayList + "\n" + "Trong thời gian: " + ((sizeOfArrayList * 10) - secondsRemaining) + "seconds");
                    resultLabel.setFont(new Font(20));
                    resultLabel.setPrefSize(650, 60);
                    resultLabel.setAlignment(Pos.CENTER);

                    AnchorPane resultAnchorPane = makeAnchorPane(650, 300);
                    resultAnchorPane.getChildren().add(resultLabel);
                    resultAnchorPane.setTopAnchor(resultLabel, 80.0);
                    
                    vbox.getChildren().add(0, resultAnchorPane);
                    
                    scrollPane2.setVvalue(0.0);
                    
                    timelineCountdown.stop();
                    countdownLabel.setVisible(false);
                    addGoBackButton();
                }
            }
    });

        vbox.setAlignment(Pos.CENTER_RIGHT);

        anchorPane.getChildren().clear();

        anchorPane.getChildren().add(scrollPane2);
        anchorPane.setTopAnchor(scrollPane2, 50.0);
        anchorPane.setLeftAnchor(scrollPane2, 50.0);
        addGoBackButton();
        addCountDownLabel();
    }

        
    private AnchorPane makeAnchorPane(double width, double height){
        AnchorPane mainAnchorPane = new AnchorPane();
        mainAnchorPane.setPrefSize(width , height);
        mainAnchorPane.setStyle("-fx-background-color: #ADEBAD; -fx-background-radius: 10;"); // Set background color and curved bord
        
        return mainAnchorPane;
    }
    
    @FXML
    private void tracNghiem(){
        if (timelineCountUnlimited.getStatus() != Animation.Status.RUNNING) {
            timelineCountUnlimited.setCycleCount(Timeline.INDEFINITE);
            timelineCountUnlimited.play();
        }
        hireButton();
        AnchorPane mainAnchorPane = makeAnchorPane(720, 380);
        mainAnchorPane.setStyle("-fx-background-color: #ADEBAD; -fx-background-radius: 15");
        
        // Tao label để hiện nghĩa từ
        Label label1 = new Label(arrayList.get(current_index).getMean());
        label1.setFont(new Font("Times New Roman", 30));
        //label.setFont(Font.font("Times New Roman", 14));
        label1.setPrefSize(720, 30);
        label1.setAlignment(Pos.CENTER);
        label1.setId("L" + current_index);

        Label midLabel = new Label("Chọn câu trả lời đúng!");
        midLabel.setFont(new Font(20));
        
        // Set vị trí cho Label1 và MidLabel
        mainAnchorPane.getChildren().addAll(label1, midLabel);
        // Đặt label1 ở giữa theo cả chiều ngang và chiều dọc
        mainAnchorPane.setTopAnchor(label1, 50.0);
        // Đặt midLabel ở tầm đoạn dưới =))
        mainAnchorPane.setTopAnchor(midLabel, 150.0);
        mainAnchorPane.setLeftAnchor(midLabel, 50.0);
        
        Button buttonA = new Button();
        buttonA.setText(arrayList.get(current_index).getWord() + "\n" + arrayList.get(current_index).getIpa());
        buttonA.setId("B" + current_index);
        buttonA.setStyle("/CSS/Button.css");
//        buttonA.setFont(Font.font("Bold", 15)); // Font name, size
//        buttonA.setPrefSize(200, 60);
//        buttonA.setStyle("-fx-border-width: 2px; -fx-border-color:  #8FDB76; -fx-background-color: #ADEBAD; -fx-background-radius: 15;");
//        setOnMouseForButton(buttonA);
        
        Button buttonB = new Button();
        buttonB.setText(arrayList.get((current_index + 1) % (sizeOfArrayList-1) ).getWord() + "\n" + arrayList.get((current_index + 1) % (sizeOfArrayList-1)).getIpa());
        buttonB.setId("B" + (current_index + 1) % (sizeOfArrayList-1));
        buttonB.setFont(Font.font("Bold", 15)); // Font name, size
        buttonB.setPrefSize(200, 60);
        buttonB.setStyle("-fx-border-width: 2px; -fx-border-color:  #8FDB76; -fx-background-color: #ADEBAD; -fx-background-radius: 15;");
        setOnMouseForButton(buttonB);


        Button buttonC = new Button();
        buttonC.setText(arrayList.get((current_index + 2) % (sizeOfArrayList-1) ).getWord() + "\n" + arrayList.get((current_index + 2) % (sizeOfArrayList-1)).getIpa());
        buttonC.setId("B" + (current_index + 2) % (sizeOfArrayList-1));
        buttonC.setFont(Font.font("Bold", 15)); // Font name, size
        buttonC.setPrefSize(200, 60);
        buttonC.setStyle("-fx-border-width: 2px; -fx-border-color:  #8FDB76; -fx-background-color: #ADEBAD; -fx-background-radius: 15;");
        setOnMouseForButton(buttonC);
        
        Button buttonD = new Button();
        buttonD.setText(arrayList.get((current_index + 3) % (sizeOfArrayList-1) ).getWord() + "\n" + arrayList.get((current_index + 3) % (sizeOfArrayList-1)).getIpa());
        buttonD.setId("B" + (current_index + 3) % (sizeOfArrayList-1));
        buttonD.setFont(Font.font("Bold", 15)); // Font name, size
        buttonD.setPrefSize(200, 60);
        buttonD.setStyle("-fx-border-width: 2px; -fx-border-color: #8FDB76; -fx-background-color: #ADEBAD; -fx-background-radius: 15;");
        setOnMouseForButton(buttonD);
        
        buttonA.setOnAction(e -> {
            if(String.valueOf(buttonA.getId().charAt(1)).equals(String.valueOf(label1.getId().charAt(1)))){
                resizeAnchorPane(mainAnchorPane, label1, buttonA, buttonB, buttonC, buttonD);
            }
        });
        buttonB.setOnAction(e -> {
            if(String.valueOf(buttonB.getId().charAt(1)).equals(String.valueOf(label1.getId().charAt(1)))){
                resizeAnchorPane(mainAnchorPane, label1, buttonA, buttonB, buttonC, buttonD);
            }
        });
        buttonC.setOnAction(e -> {
            if(String.valueOf(buttonC.getId().charAt(1)).equals(String.valueOf(label1.getId().charAt(1)))){
                resizeAnchorPane(mainAnchorPane, label1, buttonA, buttonB, buttonC, buttonD);
            }
        });
        buttonD.setOnAction(e -> {
            if(String.valueOf(buttonD.getId().charAt(1)).equals(String.valueOf(label1.getId().charAt(1)))){
                resizeAnchorPane(mainAnchorPane, label1, buttonA, buttonB, buttonC, buttonD);
            }
        });

        mainAnchorPane.getChildren().addAll(buttonA, buttonB, buttonC, buttonD);
        
        mainAnchorPane.setTopAnchor(buttonA, 290.0);
        mainAnchorPane.setLeftAnchor(buttonA, 140.0);
        
        mainAnchorPane.setTopAnchor(buttonB, 290.0);
        mainAnchorPane.setLeftAnchor(buttonB, 380.0);
        
        mainAnchorPane.setTopAnchor(buttonC, 210.0);
        mainAnchorPane.setLeftAnchor(buttonC, 140.0);
        
        mainAnchorPane.setTopAnchor(buttonD, 210.0);
        mainAnchorPane.setLeftAnchor(buttonD, 380.0);
        
        anchorPane.getChildren().clear();
        
        addGoBackButton();
        
        secondsRemaining = 0;
        addCountDownLabel();
        
        anchorPane.getChildren().add(mainAnchorPane);
        anchorPane.setTopAnchor(mainAnchorPane, 50.0);
        anchorPane.setLeftAnchor(mainAnchorPane, 20.0);
        
    }
            
    private void resizeAnchorPane(AnchorPane anchorPane, Label label1, Label label2) {
        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(500), anchorPane);
        scaleOut.setToX(0.2);
        scaleOut.setToY(0.2);

        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(500), anchorPane);
        scaleIn.setToX(1.0);
        scaleIn.setToY(1.0);

        scaleOut.setOnFinished(event -> {
            if(check){
                label1.setText(arrayList.get(current_index).getWord());
                label2.setOpacity(0);
                check = false;
            }
            else{
                check = true;
                label1.setText(arrayList.get(current_index).getMean());
                label2.setOpacity(1);
            }
            scaleIn.play();
        });
        scaleOut.play();
    }
    
    private void resizeAnchorPane(AnchorPane anchorPane, Label label, Button button1, Button button2, Button button3, Button button4) {
        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(350), anchorPane);
        scaleOut.setToX(0.2);
        scaleOut.setToY(0.2);
        
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(350), anchorPane);
        scaleIn.setToX(1.0);
        scaleIn.setToY(1.0);

        scaleOut.setOnFinished(event -> {
            update1Labeland4Button(label, button1, button2, button3, button4);
            scaleIn.play();
        });
        scaleOut.play();
    }

    // Hàm lamba để cập nhật label1 và label2
    private void updateLabels(Runnable updateTask) {
        Platform.runLater(updateTask);
    }

    private Button createminiButton(String c){
        Button button = new Button(c);
        button.setMinWidth(100);
        button.setMinHeight(45);
        button.setFont(Font.font("Times New Roman", 25));
        button.setStyle("-fx-background-color: #BBF7D0; -fx-background-radius: 5;");
        return button;
    }
    
    private Button createButton(String c, String id){
        Button button = new Button(c);
        button.setMinWidth(100);
        button.setMinHeight(50);
        button.setFont(Font.font("Arial", 15));
        button.setId(id);
        button.setOnAction(e ->{
            if(!check){
                check = true;
                preButton = button;
                button.setOpacity(0.5);
            }
            else{
                check = false;
                if(!preButton.getId().equals(button.getId()) && String.valueOf(id.charAt(1)).equals(String.valueOf(preButton.getId().charAt(1)))){
                    current_index++;
                    if(current_index == 8){
                        timelineCount60.stop();
                        showAlertGhepThe(true);
                    }
                    button.setVisible(false);
                    preButton.setVisible(false);
                }
                else{
                    preButton.setOpacity(1);
                }
            }
        });
        
        return button;
    }
    
    private void setOnMouseForButton(Button button){
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
    
    private List<String> splitAndShuffle(String word) {
        List<String> characters = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            characters.add(String.valueOf(word.charAt(i)));
        }
        Collections.shuffle(characters);
        return characters;
    }
    
    
    
    private void updateHBox(HBox hbox, String c){
        int lastIndex = hbox.getChildren().size() - 1;
        Label lastLabel = (Label) hbox.getChildren().get(lastIndex);
        lastLabel.setText(c);
        Label newLabel = new Label("_");
        newLabel.setFont(new Font(28));
        hbox.getChildren().add(newLabel);
    }
    

    private void showAlertEchTuVung(int score) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn đã ghi được: " + score + " score!");
            alert.setContentText("Bấm vào 'Đồng ý' để tiếp tục.");

            // Tạo nút "Đồng ý"
            ButtonType buttonTypeOK = new ButtonType("Đồng ý");

            // Đặt nút vào cửa sổ thông báo
            alert.getButtonTypes().setAll(buttonTypeOK);

            // Lấy nút "Đồng ý" từ cửa sổ thông báo
            Button okButton = (Button) alert.getDialogPane().lookupButton(buttonTypeOK);

            if (okButton != null) {
                // Đặt sự kiện cho nút "Đồng ý"
                okButton.setOnAction(event -> {
                    try {
                        // Xử lý khi bấm nút "Đồng ý" ở đây
                        System.out.println("Đã bấm nút Đồng ý");
                        Table_name_DAO tbD = new Table_name_DAO();
                        Table_Name tb = tbD.selectOne(tableName);
                        tb.setScore(tb.getScore() + score);
                        tbD.update("", tb, tableName);
                        
                        goBack();
                        // Sau khi xử lý, đóng cửa sổ thông báo
                        alert.close();
                    } catch (IOException ex) {
                        Logger.getLogger(TESTController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            } else {
                // Handle the case where okButton is null, log or display an error message.
                System.err.println("Error: Ok button not found!");
            }
            // Hiển thị cửa sổ thông báo và đợi người dùng bấm nút "Đồng ý"
            alert.showAndWait();
        });
    }
    
    private boolean showAlertFinalTest() {
        // Tạo một thông báo kiểu CONFIRMATION
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận");
        confirmationAlert.setHeaderText("Nộp bài?");
        confirmationAlert.setContentText("Bạn chắc chắn muốn nộp bài?");

        // Tùy chỉnh nút trên alert
        confirmationAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        // Hiển thị alert và xử lý kết quả
        AtomicBoolean checkTT = new AtomicBoolean(false);
        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                checkTT.set(true);
                // Thêm mã xử lý cho hành động được xác nhận ở đây
            }
        });
        return checkTT.get();
    }

    private void showAlertGhepThe(boolean timeLimited) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bấm vào 'Đồng ý' để tiếp tục.");
        
        if(timeLimited){
            alert.setHeaderText("Chúc mừng bạn đã hoàn thành trong thời gian: " + secondsRemaining);
        }
        else{
            alert.setHeaderText("Rất tiếc bạn chưa hoàn thành trò chơi!");
        }
        Platform.runLater(() -> {
            // Tạo nút "Đồng ý"
            ButtonType buttonTypeOK = new ButtonType("Đồng ý");

            // Đặt nút vào cửa sổ thông báo
            alert.getButtonTypes().setAll(buttonTypeOK);

            // Lấy nút "Đồng ý" từ cửa sổ thông báo
            Button okButton = (Button) alert.getDialogPane().lookupButton(buttonTypeOK);

            if (okButton != null) {
                // Đặt sự kiện cho nút "Đồng ý"
                okButton.setOnAction(event -> {
                    try {
                        project_1.Project_1.controller.setAllowChange(false);
                        if(timeLimited){
                            Table_name_DAO tbD = new Table_name_DAO();
                            Table_Name tb = tbD.selectOne(tableName);
                            tb.setScore(tb.getScore() + 15);
                            tbD.update("", tb, tableName);
                        }
                        goBack();
                        // Sau khi xử lý, đóng cửa sổ thông báo
                        alert.close();
                    } catch (IOException ex) {
                        Logger.getLogger(TESTController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            } else {
                // Handle the case where okButton is null, log or display an error message.
                System.err.println("Error: Ok button not found!");
            }

            // Hiển thị cửa sổ thông báo và đợi người dùng bấm nút "Đồng ý"
            alert.showAndWait();
        });
    }
    
    private void goBack() throws IOException{
        project_1.Project_1.controller.setAllowChange(false);
        timelineCountUnlimited.stop();
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(scrollPane);
        appearButton();
    }
    
    public static String processString(String input) {
        // Chuyển về in thường
        String lowercaseString = input.toLowerCase();

        // Loại bỏ kí tự đặc biệt và khoảng trắng
        String processedString = lowercaseString.replaceAll("[^a-zA-Z0-9]", "");

        return processedString;
    }
    
    Timeline timelineCountdown = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(secondsRemaining >= 0){
                secondsRemaining--;
                countdownLabel.setText("Time remaining: " + secondsRemaining + " seconds");
            } else {
                timelineCountdown.stop();
                countdownLabel.setText("Time's up!");
                project_1.Project_1.controller.setAllowChange(false);
                // Tạo cửa sổ thông báo
                showAlertEchTuVung(score.get());
                }
            }
    }));
    
    Timeline timelineCount60 = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(secondsRemaining < 60){
                secondsRemaining++;
                countdownLabel.setText("Elapsed time: " + secondsRemaining + " seconds");
            }
             else {
                countdownLabel.setText("Time's up!");
                timelineCount60.stop();
                project_1.Project_1.controller.setAllowChange(false);
                showAlertGhepThe(false);
            }
        }
    }));
    
    Timeline timelineCountUnlimited = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            secondsRemaining++;
            countdownLabel.setText("Elapsed time: " + secondsRemaining + " seconds");
        }
    }));
    
    private void hireButton(){
        TEST1_BTN.setVisible(false);
        TEST2_BTN.setVisible(false);
        TEST3_BTN.setVisible(false);
        TEST4_BTN.setVisible(false);
        finalTestBTN.setVisible(false);
    }
    
    private void appearButton(){
        anchorPane.getChildren().addAll(TEST1_BTN, TEST2_BTN, TEST3_BTN, TEST4_BTN);
        anchorPane.toBack();
        TEST1_BTN.setVisible(true);
        TEST2_BTN.setVisible(true);
        TEST3_BTN.setVisible(true);
        TEST4_BTN.setVisible(true);
        finalTestBTN.setVisible(true);
    }

}