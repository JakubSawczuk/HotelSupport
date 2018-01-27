package gui;

import database.SupportDatabase;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

/**
 * Created by Kuba on 2018-01-17.
 */
public class LogInWindow extends Application implements IStandardGUIclass {

    static public Stage window;
    static public BorderPane layout;
    private MenuBar menuBar;
    private Menu languageMenu;
    private Menu skinMenu;
    private Menu fileMenu;

    static public GridPane grid;
    static public Scene scene;

    private Label namelabel,
            passlabel;
    private TextField namefield,
            passfield;

    private Button loginButton;
    public static Properties properties;


    SupportDatabase supportDatabase = new SupportDatabase();
    BasicWindow basicWindow = InstancesSet.getInstanceBasicWindow();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        readProperties("src\\main\\resources\\pol.properties");
        setup();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    SupportDatabase.entityManager.close();
                    SupportDatabase.entityManagerFactory.close();
                } catch (NullPointerException e) {
                } finally {
                    primaryStage.close();
                }
            }
        });

    }


    public void readProperties(String path) {
        FileReader reader;
        properties = new Properties();
        try {
            reader = new FileReader(path);
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionLoginButton() {
        loginButton.setOnAction(event -> {
            try {
                (new Thread(supportDatabase)).start();
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                (new Thread(basicWindow)).start();
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            menuBar.getMenus().remove(languageMenu);
            basicWindow.runThreadsWindow();
            layout.getChildren().remove(grid);
            layout.setCenter(BasicWindow.gridPane);
            window.setWidth(200);
            window.setHeight(350);
        });
    }

    @Override
    public void setup() {
        layout = new BorderPane();

        makeFileMenu();
        makeSkinMenu();
        makeLanguageMenu();

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, skinMenu, languageMenu);
        layout.setTop(menuBar);

        makeAllFields();
        makeAllButtons();
        actionLoginButton();

        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.getChildren().addAll(namelabel, namefield, passlabel, passfield, loginButton);

        layout.setBottom(grid);
        scene = new Scene(layout, 275, 150);
        scene.getStylesheets().add("skins/RedSilverSkin.css");
        window.setTitle(properties.getProperty("titleLogInWindow"));
        window.setScene(scene);
        window.show();

    }

    public void makeLoginButton() {
        loginButton = new Button(properties.getProperty("loginButtonText"));
        GridPane.setConstraints(loginButton, 1, 6);
    }

    public void makeNameFields() {
        namelabel = new Label(properties.getProperty("user"));
        namelabel.setId("bold-label");
        GridPane.setConstraints(namelabel, 0, 0);

        namefield = new TextField();
        namefield.setPromptText(properties.getProperty("user"));
        GridPane.setConstraints(namefield, 1, 0);
    }

    public void makePassFields() {
        passlabel = new Label(properties.getProperty("password"));
        passlabel.setId("bold-label");
        GridPane.setConstraints(passlabel, 0, 1);

        passfield = new TextField();
        passfield.setPromptText(properties.getProperty("password"));
        GridPane.setConstraints(passfield, 1, 1);
    }

    public void makeSkinMenu(){
        skinMenu = new Menu(properties.getProperty("skin"));

        MenuItem redSkin = new MenuItem("Czerwony");
        redSkin.setOnAction(event -> {
            scene.getStylesheets().clear();
            scene.getStylesheets().add("skins/RedSilverSkin.css");
            window.setScene(scene);
        });

        MenuItem blueSkin = new MenuItem("Niebieska");
        blueSkin.setOnAction(event -> {
            scene.getStylesheets().clear();
            scene.getStylesheets().add("skins/BlueWhiteSkin.css");
            window.setScene(scene);
        });

        MenuItem greenSkin = new MenuItem("Zielona");
        greenSkin.setOnAction(event -> {
            scene.getStylesheets().clear();
            scene.getStylesheets().add("skins/GreenWhiteSkin.css");
            window.setScene(scene);
        });
        skinMenu.getItems().addAll(redSkin, blueSkin, greenSkin);
    }

    public void makeLanguageMenu(){
        languageMenu = new Menu(properties.getProperty("language"));
        ToggleGroup difficultyToggle = new ToggleGroup();
        RadioMenuItem languagePol = new RadioMenuItem(properties.getProperty("pol"));
        languagePol.setOnAction(event -> {
            readProperties("src\\main\\resources\\pol.properties");
            layout.getChildren().remove(grid);
            layout.getChildren().remove(menuBar);
            setup();
            languagePol.setSelected(true);
        });
        RadioMenuItem languageAng = new RadioMenuItem(properties.getProperty("eng"));
        languageAng.setOnAction(event -> {
            readProperties("src\\main\\resources\\ang.properties");
            layout.getChildren().remove(grid);
            layout.getChildren().remove(menuBar);
            setup();
            languageAng.setSelected(true);


        });

        languagePol.setToggleGroup(difficultyToggle);
        languageAng.setToggleGroup(difficultyToggle);
        languageMenu.getItems().addAll(languagePol, languageAng);
    }

    public void makeFileMenu(){
        fileMenu = new Menu(properties.getProperty("fileMenu"));
        MenuItem newFile = new MenuItem(properties.getProperty("close"));
        newFile.setOnAction(e -> exit(0));
        fileMenu.getItems().add(newFile);
    }

    @Override
    public void makeAllButtons() {
        makeLoginButton();
    }

    @Override
    public void makeAllFields() {
        makePassFields();
        makeNameFields();
    }
}

