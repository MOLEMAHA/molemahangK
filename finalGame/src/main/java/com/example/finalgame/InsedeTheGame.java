package com.example.finalgame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.Timer;

public class InsedeTheGame implements EventHandler<ActionEvent>{
    public String [] question = {
                                "Who is the founder of Basotho Nation",
                                "Who is the king of Lesotho in 2023",
                                "what is the capital city of Lesotho",
                                "Which dam is this one",
                                "What is the name of the waterfall found in Semonkong"
                                };

public String [][] options={
                            {"Morena Moshoeshoe","Chaka Zulu","King Monada","Pakalitha Mosisili"},
                            {"Tom Thabane","King Letsie (iii)","Morena Likoche","Tansri"},
                            {"Mokhotlong","Berea","Maseru","Mafeteng"},
                            {"`Muela Day","Mohale Dam","Jorodane","Katse Dam"},
                            {"Maletsunyane Falls","TakeIt easy Falls","Himms Falls","khohlong Falls"}
                            };

public char[] answers =          {
                                    'A',
                                    'B',
                                    'C',
                                    'D'
                                    };
public String[] photo = {
                            getClass().getResource("6.jpeg").toExternalForm().toString(),
                            getClass().getResource("2.jpeg").toExternalForm().toString(),
                            getClass().getResource("3.jpeg").toExternalForm().toString(),
                            getClass().getResource("4.jpeg").toExternalForm().toString(),
                            getClass().getResource("5.jpeg").toExternalForm().toString()
                        };
char guess;
char answer;
private Button[] optionButtons;

int index;
int correctGuesses=0;
int totalQuestions = question.length;
int results;
int seconds = 10;


BorderPane borderPane = new BorderPane();

    Button buttonA = new Button("A");
    Button buttonB = new Button("B");
    Button buttonC = new Button("C");
    Button buttonD = new Button("D");

    private Label label1,label2,label3,label4,label5,label6,scoreLabel;

    VBox vBox = new VBox(10);
    private HBox hBox ,hBox1,hBox2,hBox3;
    private String audioLink  = "/home/molemahang/IdeaProjects/finalGame/src/main/resources/com/example/finalgame/sesotho.mpga";
    public  String css = getClass().getResource("app.css").toExternalForm().toString();

    public InsedeTheGame()
    {
        scoreLabel = new Label();
        label1 = new Label("testing 1");
        label2 = new Label("testing 2");
        label3 = new Label("testing 3");
        label4 = new Label("testing 4");
        buttonA.getStyleClass().add("option");
        buttonB.getStyleClass().add("option");
        buttonC.getStyleClass().add("option");
        buttonD.getStyleClass().add("option");

        label5 = new Label();
        label5.getStyleClass().add("top");
        label6 = new Label();
        label1.getStyleClass().add("labling");
        label2.getStyleClass().add("labling");
        label3.getStyleClass().add("labling");
        label4.getStyleClass().add("labling");

        label6.getStyleClass().add("header");
        label5.getStyleClass().add("header");
        hBox = new HBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox3 = new HBox();

        hBox.getChildren().addAll(buttonA,label1);
        hBox1.getChildren().addAll(buttonB,label2);
        hBox2.getChildren().addAll(buttonC,label3);
        hBox3.getChildren().addAll(buttonD,label4);

        VBox vBox4 = new VBox(7);
        Label label = new Label("Timer");
        label.setFont(Font.font(30));
        Button button =new Button();
        button.setText(String.valueOf(seconds));

        scoreLabel.getStyleClass().add("something");
        button.getStyleClass().add("tfield");
        vBox4.getChildren().addAll(label,button,scoreLabel);
        borderPane.setRight(vBox4);
        borderPane.setTop(label5);

        nextQuestion();

        Button btn = new Button("next");
        btn.getStyleClass().add("nxt");
        Image[] images = new Image[photo.length];
        for (int i = 0; i < images.length; i++)
        {
            images[i]= new Image(photo[i]);

        }
        ImageView imageViewToNav = new ImageView(images[index]);
        imageViewToNav.setX(1300);
        imageViewToNav.setY(700);
        imageViewToNav.setFitWidth(300);
        imageViewToNav.setFitHeight(300);
        VBox vBox1 = new VBox(10);
        vBox1.getChildren().add(imageViewToNav);
        vBox1.getStyleClass().add("images");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                index++;
                if(index >=images.length)
                {
                    index =0;
                }
                imageViewToNav.setImage(images[index]);
                checker();
                button.setText("10");

            }
        });
        VBox vBox3 = new VBox();
        vBox3.getChildren().add(btn);
        vBox3.getStyleClass().add("btnBox");
        borderPane.setBottom(vBox3);
        borderPane.setLeft(vBox1);



        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int time = Integer.parseInt(button.getText());
            if (time>0) {

                button.setText(String.valueOf(--time));

                if (time == 0) {

                    if (index < question.length - 1) {
                        index++;
                        label5.setText(question[index]);
                        nextQuestion();
                        imageViewToNav.setImage(images[index]);

                    }else {
                        button.setText("10");



                    }
                }

            }
            else{
                button.setText("10"); // Reset the timer to 10

            }
        }));
        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline1.playFromStart();

        buttonA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                displayAnswer();
            }

        });
        buttonB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                displayAnswer();
            }

        });
        buttonC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                displayAnswer();
            }

        });
        buttonD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                displayAnswer();
            }

        });
        VBox vBox2 = new VBox();
        vBox2.setBackground(new Background(new BackgroundFill(Color.GRAY,null,null)));
        vBox2.setMaxWidth(1200);
        vBox2.setMinWidth(600);
        vBox2.setMaxHeight(400);
        vBox2.setMinHeight(150);

        //adding the media
        Media media1 =  new Media(new File(audioLink).toURI().toString());

        MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
        MediaView mediaView = new MediaView(mediaPlayer1);
        mediaPlayer1.setAutoPlay(true);
        mediaPlayer1.play();


        vBox2.getStyleClass().add("sort");
        vBox2.getChildren().addAll(label6,hBox,hBox1,hBox2,hBox3,mediaView);
        borderPane.setCenter(vBox2);
        Stage stage = new Stage();
        Scene scene = new Scene(borderPane,1500,600);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }


    public void nextQuestion() {
        if (index >= totalQuestions) {
            System.out.println("problems");
        } else {
                label1.setTextFill(Color.BLACK);
                label2.setTextFill(Color.BLACK);
                label3.setTextFill(Color.BLACK);
                label4.setTextFill(Color.BLACK);

                label5.setText("Question " +( index + 1));
                label6.setText(question[index]);
                label1.setText(options[index][0]);
                label2.setText(options[index][1]);
                label3.setText(options[index][2]);
                label4.setText(options[index][3]);
                if(index==1)
                {
                    if (buttonA.isPressed()) {
                        answer = 'A';
                        correctGuesses+=10;
                        scoreLabel.setText("Score: "+correctGuesses);
                    }
                }
                scoreLabel.setText("Score: " + (correctGuesses * 100) / question.length + "%");

        }}
    @Override
        public void handle (ActionEvent e)
        {


            if (e.getSource() == buttonA) {
                answer = 'A';

                if (answer == answers[index]) {
                    correctGuesses++;
                }
            }
            if (e.getSource() == buttonB) {
                answer = 'B';

                if (answer == answers[index]) {
                    correctGuesses++;
                }
            }
            if (e.getSource() == buttonC) {
                answer = 'C';

                if (answer == answers[index]) {
                    correctGuesses++;
                }
            }
            if (e.getSource() == buttonD) {
                answer = 'D';

                if (answer == answers[index]) {
                    correctGuesses++;
                }
            }

        }
        public void displayAnswer ()
        {

            if (answers[index] == 'A') {
                label1.setTextFill(Color.GREEN);
                correctGuesses++;
            } else if (answers[index] == 'B') {
                correctGuesses++;
                label2.setTextFill(Color.GREEN);
            } else if (answers[index] == 'D') {
                correctGuesses++;
                label3.setTextFill(Color.GREEN);
            } else if (answers[index] == 'C') {
                correctGuesses++;
                label4.setTextFill(Color.GREEN);
            }
            else if (answers[index] == 'C') {
                correctGuesses++;
                label4.setTextFill(Color.GREEN);
            }


            // Highlight incorrect answers in red:
            if (answers[index] != 'A') {
                label1.setTextFill(Color.RED);
            }
            if (answers[index] != 'B') {
                label2.setTextFill(Color.RED);
            }
            if (answers[index] != 'C') {
                label3.setTextFill(Color.RED);
            }
            if (answers[index] != 'D') {
                label4.setTextFill(Color.RED);
            }
        }

        public void checker()
        {

            nextQuestion();
        }

    }
