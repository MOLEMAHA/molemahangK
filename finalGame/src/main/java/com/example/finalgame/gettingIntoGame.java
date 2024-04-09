package com.example.finalgame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class gettingIntoGame {
    String css = getClass().getResource("app.css").toExternalForm().toString();

    public void introduction()
    {

        String path1="/home/molemahang/Downloads/one.mp4";
        String path = "/home/molemahang/IdeaProjects/finalGame/src/main/resources/com/example/finalgame/FujiXerox Launch Gambit.mp4";
//        File file = new File(path);
//        Media media1=new Media(file.toURI().toString());
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView1 = new MediaView(mediaPlayer);
        Stage mystage = new Stage();
        mediaPlayer.play();

        Button button3 = new Button("Click Me to play");
        button3.getStyleClass().add("btnPlay");
        button3.setAlignment(Pos.BASELINE_CENTER);

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                InsedeTheGame insedeTheGame = new InsedeTheGame();
                mystage.close();
                mediaPlayer.pause();
            }
        });

        StackPane layout = new StackPane();
        layout.getChildren().addAll(mediaView1, button3);

        Scene scene = new Scene(layout, 1200, 600);
        scene.getStylesheets().add(css);
        mystage.setTitle("Hello!");
        mystage.setScene(scene);
        mystage.show();


    }

}
