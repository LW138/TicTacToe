package com.example.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
    public static int PLAYER = 1;

    @Override
    public void start(Stage stage) throws IOException {
        GridPane gridPane = new GridPane();
        TextField textField = new TextField();
        Cell cells[] = new Cell[9];

        //befülle GridPane mit Cells, Cells werden im Cell Array zwischengespeichert
        for(int i = 0; i < cells.length; i++){
            Cell cell = new Cell();
            cell.setOnMouseClicked(e -> {
                cell.setPlayer();
                int result = checkFieldState(cells);
                if(result == 1){
                    textField.setText("Kreis hat gewonnen!");
                }else if(result == 0){
                    textField.setText("Quadrat hat gewonnen!");
                }else if(result == -1){
                    textField.setText("Unentschieden!");
                }else{
                    textField.setText("Alles ist noch offen!");
                }
            });
            cells[i] = cell;
        }


        int counter = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                gridPane.add(cells[counter++], i, j);
            }
        }
        gridPane.add(textField, 0,4,3,4);


        stage.setTitle("TicTacToe");
        stage.setScene(new Scene(gridPane));
        stage.show();
    }

    public int checkFieldState(Cell[] cells){
        int result = -2;
        int sumField = 0;
        int[] state = new int[9];
        for(int i = 0; i < cells.length; i++){
            int cellState = cells[i].getPlayer();
            state[i] = cellState;
            sumField += cellState;
        }

        if(state[0] == state[3] && state[3] == state[6]) result = state[0];
        else if(state[1] == state[4] && state[4] == state[7]) result = state[1];
        else if(state[2] == state[5] && state[5] == state[8]) result = state[2];
        else if(state[0] == state[1] && state[1] == state[2]) result = state[0];
        else if(state[3] == state[4] && state[4] == state[5]) result = state[3];
        else if(state[6] == state[7] && state[7] == state[8]) result = state[6];
        else if(state[0] == state[4] && state[4] == state[8]) result = state[0];
        else if(state[2] == state[4] && state[4] == state[6]) result = state[2];
        else if(sumField == 5) result = -1;

        return result;
    }

    public class Cell extends Pane {
        private int player;
        private boolean alreadyPlayed;

        public Cell(){
            setStyle("-fx-border-color: black");
            setPrefSize(150,150);
            player = 200;
        }

        public int getPlayer() {
            return player;
        }

        public void setPlayer() {
            if(alreadyPlayed == false){
                this.player = HelloApplication.PLAYER;
                if(player == 1){
                    Ellipse ellipse = new Ellipse(75, 75, 50, 50);
                    this.getChildren().add(ellipse);
                    HelloApplication.PLAYER = 0;
                }else{
                    Rectangle rectangle = new Rectangle(25,25, 100, 100);
                    rectangle.setFill(Color.RED);
                    this.getChildren().add(rectangle);
                    HelloApplication.PLAYER = 1;
                }
                alreadyPlayed = true;
                System.out.println(player);
            }

        }


    }

    public static void main(String[] args) {
        launch();
    }
}



