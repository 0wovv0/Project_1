package Controller;

import DAO.Table_name_DAO;
import DAO.Words_DAO;
import Model.Table_Name;
import Model.Word_Model;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;


public class TESTController implements Initializable {
    @FXML
    private Label Type;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button TEST1_BTN, finalTestBTN;
    @FXML
    private Button TEST2_BTN;
    @FXML
    private Button TEST3_BTN;
    @FXML 
    private Button TEST4_BTN;

    boolean check = false;
    Button preButton;
        
    private Label countdownLabel = new Label();
    private int secondsRemaining = 60; // Thời gian đếm ngược ban đầu
    private Label wordLabel = new Label();
    private HBox hbox = new HBox();
    private GridPane gridPane;
    
    private int max;
    private int current_index;
    private IntegerProperty score = new SimpleIntegerProperty(0);

    private String tableName;
    private ArrayList<Word_Model> arrayList = new ArrayList<>();

    public TESTController(String tableName) {
        this.tableName = tableName;
//        this.preParent = preParent;
    }
    
    // Tạo Timeline để cập nhật thời gian mỗi giây
    Timeline timelineCountdown = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(secondsRemaining >= 0)
                updateCountdown();
        }
    }));
    
    Timeline timelineCountup = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(secondsRemaining < 60)
                updateCountup();
        }
    }));
    
    Timeline timelineCountupUnlimited = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            updateCountupUnlimited();
        }
    }));
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        countdownLabel.setAlignment(Pos.CENTER);
        if(tableName == "Saved_Table"){
            Type.setText("Từ đang học");
        }else{
            Type.setText("Chủ đề: " + tableName);
        };
        
        Words_DAO wd = new Words_DAO();
        arrayList = wd.selectAll(tableName);
        countdownLabel.setFont(new Font(20.0));
        wordLabel.setFont(new Font(20.0));
        this.max = arrayList.size();
        
        // Tạo một GridPane
        this.gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        TEST2_BTN.setOnAction(e -> {
            try {
                EchTuVung();
            } catch (IOException ex) {
                Logger.getLogger(TESTController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Khong the gan su kien cho nut echtuVung");
            }
        });
        TEST1_BTN.setOnAction(e -> {
            try {
                make_Pair();
            } catch (IOException ex) {
                Logger.getLogger(TESTController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Khong the gan su kien cho nut echtuVung");
            }
        });
        TEST3_BTN.setOnAction(e -> {
            try {
                Learn();
            } catch (IOException ex) {
                Logger.getLogger(TESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        TEST4_BTN.setOnAction(e -> {
            tracNghiem();
        });
        
        finalTestBTN.setOnAction(e -> {
            finalTest();
        });
    }

    @FXML
    public void EchTuVung() throws IOException{
        score.set(0);
        secondsRemaining = 60;
        // Start the Timeline if it is not already running
        if (timelineCountdown.getStatus() != Animation.Status.RUNNING) {
            timelineCountdown.setCycleCount(Timeline.INDEFINITE);
            timelineCountdown.play();
        }
        updateWord();
    } 
    
    @FXML
    public void make_Pair() throws IOException{
        current_index = 0;
        score.set(0);
        secondsRemaining = 0;
        // Start the Timeline if it is not already running
        if (timelineCountup.getStatus() != Animation.Status.RUNNING) {
            timelineCountup.setCycleCount(Timeline.INDEFINITE);
            timelineCountup.play();
        }
        updatePair();
    }

    @FXML
    public void Learn() throws IOException{
        current_index = 0;
        score.set(0);
        secondsRemaining = 0;
        // Start the Timeline if it is not already running
        if (timelineCountupUnlimited.getStatus() != Animation.Status.RUNNING) {
            timelineCountupUnlimited.setCycleCount(Timeline.INDEFINITE);
            timelineCountupUnlimited.play();
        }
        makeCard();
    }

    private void makeCard() {
        check = true;
        current_index = 0;
        // Tạo AnchorPane chính
//        AnchorPane mainAnchorPane = new AnchorPane();       
        AnchorPane mainAnchorPane = makeAnchorPane(450.0, 300.0);
        // Tạo AnchorPane ở trên cùng
        AnchorPane topAnchorPane = new AnchorPane();
        topAnchorPane.setPrefSize(400, 250);
        Label label1 = new Label(arrayList.get(current_index).getMean());
        label1.setFont(new Font(25));
        label1.setPrefSize(450, 30);
        label1.setAlignment(Pos.CENTER);

        Label label2 = new Label(arrayList.get(current_index).getIpa());
        label2.setFont(new Font(25));
        label2.setPrefSize(450, 30);
        label2.setAlignment(Pos.CENTER);


        topAnchorPane.getChildren().addAll(label1, label2);

        // Đặt label1 ở giữa theo cả chiều ngang và chiều dọc
        topAnchorPane.setTopAnchor(label1, 90.0);

        // Đặt label2 ở trung tâm theo chiều ngang
        topAnchorPane.setTopAnchor(label2, 120.0);

        // Thêm 2 Button và một Label ở giữa
        Label midLabel = new Label("");
        if(current_index > 8)
            midLabel.setText("" + (current_index+1) + "  /  " + max);
        else midLabel.setText("0" + (current_index+1) + "  /  " + max);
        midLabel.setFont(new Font(20));

        Button preButton = new Button("Trước");
        preButton.setFont(Font.font("Bold", 18)); // Font name, size
        preButton.setPrefSize(80, 35);
        preButton.setOnAction(e -> {
            label2.setOpacity(1);
            check = true;
            if (current_index == 0) {
                current_index = max - 1;
            } else {
                    current_index--;
            }

            updateLabels(() -> {
                label1.setText(arrayList.get(current_index).getMean());
                label2.setText(arrayList.get(current_index).getIpa());
                if(current_index > 8)
                    midLabel.setText("" + (current_index+1) + "  /  " + max);
                else 
                    midLabel.setText("0" + (current_index+1) + "  /  " + max);
            });


        });

        Button afterButton = new Button("Sau");
        afterButton.setFont(Font.font("Bold", 18)); // Font name, size
        afterButton.setPrefSize(80, 35);
        afterButton.setOnAction(e -> {
            check = true;
            label2.setOpacity(1);
            if (current_index == max - 1) {
                current_index = 0;
            } else {
                    current_index++;
            }
            updateLabels(() -> {
                label1.setText(arrayList.get(current_index).getMean());
                label2.setText(arrayList.get(current_index).getIpa());
                if(current_index > 8)
                    midLabel.setText("" + (current_index+1) + "  /  " + max);
                else 
                    midLabel.setText("0" + (current_index+1) + "  /  " + max);
            });
        });
        
        Button goBackbutton = new Button("Go back");
        goBackbutton.setOnAction(event -> {
            try {
                goBack();
            } catch (IOException ex) {
                Logger.getLogger(TESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        mainAnchorPane.getChildren().add(midLabel);
        mainAnchorPane.setTopAnchor(midLabel, 250.0);
        mainAnchorPane.setLeftAnchor(midLabel, 210.0);

        // Add các thuộc tính vào AnchorPane chính
        mainAnchorPane.getChildren().add(topAnchorPane);

        mainAnchorPane.getChildren().add(preButton);
        mainAnchorPane.setTopAnchor(preButton, 245.0);
        mainAnchorPane.setLeftAnchor(preButton, 120.0);

        mainAnchorPane.getChildren().add(afterButton);
        mainAnchorPane.setTopAnchor(afterButton, 245.0);
        mainAnchorPane.setLeftAnchor(afterButton, 300.0);

        topAnchorPane.setStyle("-fx-background-color: #e0e0e0; -fx-background-radius: 10;"); // Set background color and curved border

        anchorPane.getChildren().clear();
        
        anchorPane.getChildren().add(goBackbutton);
        anchorPane.setTopAnchor(goBackbutton, 10.0);
        anchorPane.setLeftAnchor(goBackbutton, 50.0);
        
        anchorPane.getChildren().add(mainAnchorPane);
        anchorPane.setTopAnchor(mainAnchorPane, 100.0);
        anchorPane.setLeftAnchor(mainAnchorPane, 50.0);
        
        anchorPane.getChildren().add(countdownLabel);
        anchorPane.setTopAnchor(countdownLabel, 10.0);
        anchorPane.setLeftAnchor(countdownLabel, 350.0);
    
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
            randomNumber = random.nextInt(max - 1);
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
        secondsRemaining = max * 10;
        if (timelineCountdown.getStatus() != Animation.Status.RUNNING) {
            timelineCountdown.setCycleCount(Timeline.INDEFINITE);
            timelineCountdown.play();
        }

        VBox vbox = new VBox();
        vbox.setSpacing(25);

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setPrefSize(550, 400);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-width: 0;-fx-background-radius: 15");

        Collections.shuffle(arrayList);
        for (int i = 0; i < max; ++i) {
            AnchorPane childAnchorPane = makeAnchorPane(500, 300);
            childAnchorPane.setId("anchorPane" + i);
            Label label1 = new Label(arrayList.get(i).getMean());
            label1.setFont(new Font(25));
            label1.setPrefSize(450, 30);
            label1.setAlignment(Pos.CENTER);

            Label label2 = new Label(arrayList.get(i).getIpa());
            label2.setFont(new Font(25));
            label2.setPrefSize(450, 30);
            label2.setAlignment(Pos.CENTER);

            TextField textField = new TextField();
            textField.setPromptText("Điền vào đây");
            textField.setPrefSize(450, 25);
            textField.setFont(new Font(20));
            textField.setId("T" + i);
            textField.setStyle("-fx-border-radius: 15;");

            childAnchorPane.getChildren().addAll(label1, label2, textField);

            childAnchorPane.setTopAnchor(label1, 60.0);
            childAnchorPane.setTopAnchor(label2, 90.0);
            childAnchorPane.setTopAnchor(textField, 200.0);
            childAnchorPane.setLeftAnchor(textField, 20.0);

            vbox.getChildren().add(childAnchorPane);
        }

        AnchorPane finalAnchorPane = makeAnchorPane(500, 300);
        Label resultLabel = new Label("Tất cả đã xong!\nBạn đã sẵn sàng nộp bài kiểm tra?");
        resultLabel.setFont(new Font(20));
        resultLabel.setPrefSize(500, 60);
        resultLabel.setAlignment(Pos.CENTER);
        vbox.getChildren().add(finalAnchorPane);


        Button checkButton = new Button("Nộp bài!");
        finalAnchorPane.getChildren().addAll(resultLabel, checkButton);
        finalAnchorPane.setTopAnchor(resultLabel, 60.0);
        finalAnchorPane.setTopAnchor(checkButton, 175.0);
        finalAnchorPane.setLeftAnchor(checkButton, 200.0);
        checkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (showAlertFinalTest()) {
                    int trueAnswers = 0;
                    for (int i = 0; i < max; ++i) {
                        TextField textField = (TextField) vbox.lookup("#T" + i);
                        if (textField != null) {
                            String userInput = processString(textField.getText());
                            String correctAnswer = processString(arrayList.get(i).getWord());

                            if (userInput.equals(correctAnswer)) {
                                trueAnswers++;
                            } else {
                                AnchorPane anchorPane = (AnchorPane) vbox.lookup("#anchorPane" + i);
                                if (anchorPane != null) {
                                    anchorPane.setStyle("-fx-background-color: #ec8a4f; -fx-background-radius: 10;");
                                }
                            }
                        } else {
                            System.out.println("TextField with id 'T" + i + "' not found.");
                        }
                    }

                    Label resultLabel = new Label("Bạn đã làm đúng: " + trueAnswers + "  /  " + max + "\n" + "Trong thời gian: " + ((max * 10) - secondsRemaining));
                    resultLabel.setFont(new Font(20));
                    resultLabel.setPrefSize(500, 60);
                    resultLabel.setAlignment(Pos.CENTER);

                    AnchorPane resultAnchorPane = makeAnchorPane(500, 300);
                    resultAnchorPane.getChildren().add(resultLabel);
                    resultAnchorPane.setTopAnchor(resultLabel, 80.0);

                    vbox.getChildren().add(0, resultAnchorPane);
                    scrollPane.setVvalue(0.0);


                    vbox.getChildren().add(checkButton);
                    vbox.getChildren().remove(vbox.getChildren().get(vbox.getChildren().size() - 1));
                    vbox.getChildren().remove(vbox.getChildren().get(vbox.getChildren().size() - 1));
                    checkButton.setPrefSize(120, 35);
                    checkButton.setFont(new Font("Times New Roman", 20));
                    resultAnchorPane.getChildren().addAll(resultLabel, checkButton);
                    resultAnchorPane.setTopAnchor(resultLabel, 60.0);
                    resultAnchorPane.setTopAnchor(checkButton, 175.0);
                    resultAnchorPane.setLeftAnchor(checkButton, 200.0);
                    
                    timelineCountdown.stop();
                    countdownLabel.setVisible(false);
                }
            }
    });

    vbox.setAlignment(Pos.CENTER);

    anchorPane.getChildren().clear();

    anchorPane.getChildren().add(scrollPane);
    anchorPane.setTopAnchor(scrollPane, 50.0);
    anchorPane.setLeftAnchor(scrollPane, 50.0);

    
    anchorPane.getChildren().add(countdownLabel);
    anchorPane.setTopAnchor(countdownLabel, 10.0);
    anchorPane.setLeftAnchor(countdownLabel, 350.0);
}

        
    private AnchorPane makeAnchorPane(double width, double height){
        AnchorPane mainAnchorPane = new AnchorPane();
        mainAnchorPane.setPrefSize(width , height);
        mainAnchorPane.setStyle("-fx-background-color: #e0e0e0; -fx-background-radius: 10;"); // Set background color and curved bord
        
        return mainAnchorPane;
    }
    
    @FXML
    private void tracNghiem(){
        timelineCountupUnlimited.play();
        AnchorPane mainAnchorPane = makeAnchorPane(550, 380);
        
        // Tao label để hiện nghĩa từ
        Label label1 = new Label(arrayList.get(current_index).getMean());
        label1.setFont(new Font("Times New Roman", 30));
        //label.setFont(Font.font("Times New Roman", 14));
        label1.setPrefSize(550, 30);
        label1.setAlignment(Pos.CENTER);
        label1.setId("L" + current_index);

        Label midLabel = new Label("Chọn câu trả lời đúng!");
        midLabel.setFont(new Font(18));
        
        // Set vị trí cho Label1 và MidLabel
        mainAnchorPane.getChildren().addAll(label1, midLabel);
        // Đặt label1 ở giữa theo cả chiều ngang và chiều dọc
        mainAnchorPane.setTopAnchor(label1, 50.0);
        // Đặt midLabel ở tầm đoạn dưới =))
        mainAnchorPane.setTopAnchor(midLabel, 180.0);
        mainAnchorPane.setLeftAnchor(midLabel, 20.0);
        
        Button buttonA = new Button();
        buttonA.setText(arrayList.get(current_index).getWord() + "\n" + arrayList.get(current_index).getIpa());
        buttonA.setId("B" + current_index);
        buttonA.setFont(Font.font("Bold", 15)); // Font name, size
        buttonA.setPrefSize(200, 60);
        
        Button buttonB = new Button();
        buttonB.setText(arrayList.get((current_index + 1) % (max-1) ).getWord() + "\n" + arrayList.get((current_index + 1) % (max-1)).getIpa());
        buttonB.setId("B" + (current_index + 1) % (max-1));
        buttonB.setFont(Font.font("Bold", 15)); // Font name, size
        buttonB.setPrefSize(200, 60);
        
        Button buttonC = new Button();
        buttonC.setText(arrayList.get((current_index + 2) % (max-1) ).getWord() + "\n" + arrayList.get((current_index + 2) % (max-1)).getIpa());
        buttonC.setId("B" + (current_index + 2) % (max-1));
        buttonC.setFont(Font.font("Bold", 15)); // Font name, size
        buttonC.setPrefSize(200, 60);
        
        Button buttonD = new Button();
        buttonD.setText(arrayList.get((current_index + 3) % (max-1) ).getWord() + "\n" + arrayList.get((current_index + 3) % (max-1)).getIpa());
        buttonD.setId("B" + (current_index + 3) % (max-1));
        buttonD.setFont(Font.font("Bold", 15)); // Font name, size
        buttonD.setPrefSize(200, 60);
        
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
        mainAnchorPane.setLeftAnchor(buttonA, 50.0);
        
        mainAnchorPane.setTopAnchor(buttonB, 290.0);
        mainAnchorPane.setLeftAnchor(buttonB, 310.0);
        
        mainAnchorPane.setTopAnchor(buttonC, 210.0);
        mainAnchorPane.setLeftAnchor(buttonC, 50.0);
        
        mainAnchorPane.setTopAnchor(buttonD, 210.0);
        mainAnchorPane.setLeftAnchor(buttonD, 310.0);
        
        anchorPane.getChildren().clear();
        
        Button goBackbutton = new Button("Go back");
        goBackbutton.setOnAction(event -> {
            try {
                goBack();
            } catch (IOException ex) {
                Logger.getLogger(TESTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        anchorPane.getChildren().add(goBackbutton);
        anchorPane.setTopAnchor(goBackbutton, 10.0);
        anchorPane.setLeftAnchor(goBackbutton, 50.0);
        
        secondsRemaining = 0;
        anchorPane.getChildren().add(countdownLabel);
        anchorPane.setTopAnchor(countdownLabel, 10.0);
        anchorPane.setLeftAnchor(countdownLabel, 350.0);
        
        anchorPane.getChildren().add(mainAnchorPane);
        anchorPane.setTopAnchor(mainAnchorPane, 60.0);
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
        button.setMinWidth(75);
        button.setMinHeight(35);
        button.setFont(Font.font("Arial", 20));
        
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
                        timelineCountup.stop();
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
    
    private List<String> splitAndShuffle(String word) {
        List<String> characters = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            characters.add(String.valueOf(word.charAt(i)));
        }
        Collections.shuffle(characters);
        return characters;
    }
    
    private void updateCountdown() {
        secondsRemaining--;
        if (secondsRemaining >= 0) {
            countdownLabel.setText("Time remaining: " + secondsRemaining + " seconds");
        } else {
            countdownLabel.setText("Time's up!");
            // Tạo cửa sổ thông báo
            showAlertEchTuVung(score.get());
        }
    }
    
    private void updateCountup() {
        secondsRemaining++;

        if (secondsRemaining < 60) {
            countdownLabel.setText("Elapsed time: " + secondsRemaining + " seconds");
        } else {
            countdownLabel.setText("Time's up!");
            // Tạo cửa sổ thông báo
            showAlertGhepThe(false);
        }
            
    }
    
    private void updateCountupUnlimited() {
        secondsRemaining++;
        countdownLabel.setText("Elapsed time: " + secondsRemaining + " seconds");
    }
    
    private void updateHBox(HBox hbox, String c){
        int lastIndex = hbox.getChildren().size() - 1;
        Label lastLabel = (Label) hbox.getChildren().get(lastIndex);
        lastLabel.setText(c);
        Label newLabel = new Label("_");
        newLabel.setFont(new Font(20));
        hbox.getChildren().add(newLabel);
    }
    
    private void updatePair() {
        check = false;
        System.out.println("updatePair");

        // Make a set of random indices
        Set<Integer> randomSet = new HashSet<>();
        int randomNumber;
        int number_of_element = 0;
        Random random = new Random();
        while (number_of_element < 8) {
            randomNumber = random.nextInt(max - 1);
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
            Button button2 = createButton(arrayList.get(listFromSet.get(i)).getWord(), "W" + i);
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
        anchorPane.setTopAnchor(gridPane, 50.0);
        anchorPane.setLeftAnchor(gridPane, 50.0);

        anchorPane.getChildren().add(countdownLabel);
        anchorPane.setTopAnchor(countdownLabel, 10.0);
        anchorPane.setLeftAnchor(countdownLabel, 350.0);
        
        
    }   

    private void updateWord(){
        hbox.getChildren().clear();
        gridPane.getChildren().clear();
        Label newLabel = new Label("_");
        newLabel.setFont(new Font(20));
        hbox.getChildren().add(newLabel);
        
        Random random = new Random();
        int randomNumber = random.nextInt(max - 1);
        String word = arrayList.get(randomNumber).getWord();
        wordLabel.setText(arrayList.get(randomNumber).getMean());
        
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
            if (columnCount == 4) {
                columnCount = 0;
                rowCount++;
            }
        }
        
        anchorPane.getChildren().clear();
        
        Label scoreLabel = new Label("Score: " + score);
        scoreLabel.setFont(new Font(20));
        anchorPane.getChildren().add(scoreLabel);
        anchorPane.setTopAnchor(hbox, 70.0);
        anchorPane.setLeftAnchor(hbox, 400.0);
        scoreLabel.textProperty().bind(Bindings.concat("Score: ").concat(score.asString()));

        anchorPane.getChildren().add(hbox);
        anchorPane.setTopAnchor(hbox, 100.0);
        anchorPane.setLeftAnchor(hbox, 200.0);
        
        anchorPane.getChildren().add(countdownLabel);
        anchorPane.setTopAnchor(countdownLabel, 10.0);
        anchorPane.setLeftAnchor(countdownLabel, 350.0);
        
        anchorPane.getChildren().add(wordLabel);
        anchorPane.setTopAnchor(wordLabel, 50.0);
        anchorPane.setLeftAnchor(wordLabel, 150.0);
        
        anchorPane.getChildren().add(gridPane);
        anchorPane.setTopAnchor(gridPane, 150.0); // Adjust the top margin as needed
        anchorPane.setLeftAnchor(gridPane, 100.0);
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
    Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
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
                        // Xử lý khi bấm nút "Đồng ý" ở đây
                        System.out.println("Đã bấm nút Đồng ý");
                        
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
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        BorderPane borderPane =(BorderPane) stage.getScene().getRoot();
        FXMLLoader contentLoader = new FXMLLoader(getClass().getResource("/FXML/TEST.fxml"));
        TESTController controller = new TESTController(tableName);
        contentLoader.setController(controller);
        Parent preParent = contentLoader.load();
        borderPane.setCenter(preParent);
    }
    
    public static String processString(String input) {
        // Chuyển về in thường
        String lowercaseString = input.toLowerCase();

        // Loại bỏ kí tự đặc biệt và khoảng trắng
        String processedString = lowercaseString.replaceAll("[^a-zA-Z0-9]", "");

        return processedString;
    }

}