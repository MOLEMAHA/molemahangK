package com.example.finalgame;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.media.Media;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    private  String[] imagePaths = {getClass().getResource("images.jpeg").toExternalForm().toString(),getClass().getResource("images1.jpeg").toExternalForm().toString(),getClass().getResource("images2.jpeg").toExternalForm().toString(),getClass().getResource("images3.jpeg").toExternalForm().toString(),getClass().getResource("images4.jpeg").toExternalForm().toString(),}; // Replace with your image paths
    private final int slideTime = 3500; // Time in milliseconds between slides
    public  String css = getClass().getResource("app.css").toExternalForm().toString();
    private ImageView imageView;
    private int currentImageIndex = 0;
    private  HBox hBox,hBox1,hBox2;
    private Button button1,button2;
    private Text text;
    private final String text1 = "The SUTHO SOTHO Game is the game aiming to help both the foreigner and the citizens to gain more knowledge regarding the Basotho Cultures and everything";
    private String musicLink = "/home/molemahang/Music/Hee_ee!_-Koete_Molotsana_ft_Rm_Leeva_Sannere(128k).m4a";
    private Line line;

    @Override
    public void start(Stage stage) throws IOException {



        Pane mainRoot = new Pane();
        gettingIntoGame game = new gettingIntoGame();
        imageView = new ImageView();
        imageView.setFitWidth(700);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(false);

        StackPane root = new StackPane(imageView);
        System.out.println(root.getStyleClass());
        root.setPadding(new Insets(20));
        Task<Void> slideTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    updateMessage(imagePaths[currentImageIndex]);
                    Thread.sleep(slideTime);
                    currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
                }
            }
        };

        slideTask.messageProperty().addListener((observable, oldValue, newValue) -> {
            Image image = new Image(newValue);
            imageView.setImage(image);

            // Slide animation
            RotateTransition rotate = new RotateTransition(Duration.millis(7000));
            rotate.setNode(imageView); // Start from right
            rotate.setByAngle(360);
            rotate.setInterpolator(Interpolator.LINEAR);
            rotate.setAxis(Rotate.Y_AXIS);
            rotate.setCycleCount(TranslateTransition.INDEFINITE);
            rotate.play();
        });

        new Thread(slideTask).start();

        //background picture
        String backpath = getClass().getResource("back.jpeg").toExternalForm().toString();

        ImageView imageView1 = new ImageView();
        Image image1 = new Image(backpath);
        imageView1.setImage(image1);
        imageView1.setId("view1");
        imageView1.setOpacity(.7);
        mainRoot.getChildren().add(imageView1);
        imageView1.setFitHeight(600);
        imageView1.setFitWidth(1500);

        hBox = new HBox(30);
        VBox vBox = new VBox(30);

        button1 = new Button("Play now");
        button1.getStyleClass().add("btn1");
        button2 = new Button("Exit");
        button2.getStyleClass().add("btn2");
        String playpath = getClass().getResource("play.png").toExternalForm().toString();

        ImageView imageView2 = new ImageView();
        Image image2 = new Image(playpath);

        imageView2.setFitWidth(40);
        imageView2.setFitHeight(40);
        imageView2.setImage(image2);

        hBox.getChildren().addAll(imageView2,button1);

        String exitpath = getClass().getResource("exit.png").toExternalForm().toString();

        ImageView imageView3 = new ImageView();
        Image image3 = new Image(exitpath);
        imageView3.setImage(image3);
        imageView3.setFitWidth(40);
        imageView3.setFitHeight(40);
        hBox1 = new HBox(30);
        hBox1.getChildren().addAll(imageView3,button2);
        hBox2 = new HBox(50);

        //myText
        String str = "WELCOME TO SUTHO-SOTHO GAME OF BASOTHO ";
        Text text = new Text(20.0, 80.0, str);
        //Setting the font
        line = new Line();
        line.setTranslateX(200);
        line.setStartX(20);
        line.setEndX(850);
        line.setStrokeWidth(5);
        line.setStroke(Color.MEDIUMPURPLE);
        Font font = Font.font("Brush Script MT", FontWeight.BOLD, FontPosture.REGULAR, 34);
        text.setFont(font);
        //Setting the color of the text
        text.setFill(Color.WHITE);
        //Setting the width and color of the stroke
        text.setStrokeWidth(2);
        text.setStroke(Color.RED);
        //Setting the deep shadow effect to the text
        DropShadow shadow = new DropShadow();
        text.setEffect(shadow);
        Label label = new Label("hello test");
        hBox2.getChildren().addAll(hBox,hBox1);
        hBox2.getStyleClass().add("hbox2");
        root.setId("myroot");
        label.setId("lb");

        //animated text
        Text animatedText = new Text(text1);

        // Set initial position off-screen to the right
        animatedText.setTranslateX(vBox.getWidth());
        animatedText.setId("animText");
        TranslateTransition animation = new TranslateTransition(Duration.millis(2000));
        animation.setFromX(-150);
        animation.setToX(100); // Slide to left
        animation.setNode(animatedText);
        animation.setByX(-250);
        animatedText.setFont(font);
        animatedText.setFill(Color.PURPLE);
        animation.setCycleCount(TranslateTransition.INDEFINITE);
        animation.setAutoReverse(true);
        animation.play();

        //adding the media
        Media media = new Media(new File(musicLink).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();



        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.introduction();
                mediaPlayer.pause();
                stage.close();

            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();

            }
        });




        System.out.println(label.getStyleClass());
        vBox.getChildren().addAll(root,text,hBox2,animatedText,mediaView);
        mainRoot.getChildren().add(vBox);
        Scene scene = new Scene(mainRoot, 1500, 600);
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}