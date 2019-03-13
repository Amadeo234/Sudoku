package Windows;

import Model.PropChecker;
import Model.Sudoku;
import Model.VetoChecker;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainWindow extends Application {
    private GridPane gameRoot;
    private Scene mainSc,gameSc;
    private Button returnButt;
    private Scanner mapReader;
    private Stage noMap,noFile,wrongFile;
    private File mapFile;
    private FileChooser fileChooser;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku");

        gameRoot = new GridPane();
        gameSc = new Scene(gameRoot, 600, 500);

        returnButt = new Button("return");
        returnButt.setOnAction(event -> {
            Initializer.getNp().close();
            primaryStage.setScene(mainSc);
            gameRoot=new GridPane();
            gameSc = new Scene(gameRoot, 600, 500);
            try {
                mapReader=new Scanner(mapFile);//always works
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        noMap =new ErrorWindow("No map chosen!");
        wrongFile=new ErrorWindow("Wrong layout file!");

        Button playButt = new Button("Play");
        playButt.setOnAction(event ->
        {
            if(mapReader==null)
            {
                noMap.close();
                noMap.show();
            }
            else
            {
                Sudoku sudo;
                try {
                    sudo=Initializer.sudokuIni(gameRoot,returnButt,mapReader);
                } catch (Exception e) {
                    wrongFile.close();
                    wrongFile.show();
                    return;
                }
                PropChecker pc=new PropChecker(sudo);
                VetoChecker vc=new VetoChecker(sudo);
                sudo.setPropertyChangeListener(pc);
                sudo.setVetoableChangeListener(vc);

                primaryStage.setScene(gameSc);
            }
        });

        Button closeButt = new Button("Close :(");
        closeButt.setOnAction(event -> primaryStage.close());

        fileChooser=new FileChooser();
        noFile=new ErrorWindow("File not found!");
        Button fileButt = new Button("Choose map file...");
        fileButt.setOnAction(event->{
            mapFile = fileChooser.showOpenDialog(primaryStage);
            if (mapFile != null) {
                try{
                    mapReader=new Scanner(mapFile);
                }catch (FileNotFoundException e){
                    noFile.show();
                }
            }
        });
        VBox mainRoot = new VBox();
        mainRoot.setAlignment(Pos.CENTER);
        mainRoot.setSpacing(10);

        mainRoot.getChildren().add(playButt);
        mainRoot.getChildren().add(fileButt);
        mainRoot.getChildren().add(closeButt);

        mainSc = new Scene(mainRoot, 300, 250);
        primaryStage.setScene(mainSc);
        primaryStage.show();
    }
}
