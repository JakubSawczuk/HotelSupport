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
import timewithrest.DataByRESTful;

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

    public static Properties properties;
    static public Stage window;
    static public BorderPane layout;
    private MenuBar menuBar;
    private Menu languageMenu;
    private Menu skinMenu;
    private Menu fileMenu;

    static public GridPane grid;
    static public Scene scene;

    private Label namelabel,
            passlabel,
            stringdatalabel,
            datalabel;

    private TextField namefield,
            passfield;

    private Button loginButton,
            exitButton;



    private SupportDatabase supportDatabase = new SupportDatabase();
    static BasicWindow basicWindow = InstancesSet.getInstanceBasicWindow();
    private DataByRESTful dataByRESTful = InstancesSet.getInstanceDataByRESTful();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        (new Thread(dataByRESTful)).start();
        sleep(2700);
        window = primaryStage;
        readLanguageProperties("src\\main\\resources\\pol.properties");
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

    public static void backToBasicWindow(){
        LogInWindow.layout.getChildren().remove(grid);
        basicWindow.setup();
        LogInWindow.layout.setCenter(basicWindow.gridPane);
        LogInWindow.window.setWidth(260);
        LogInWindow.window.setHeight(300);
        LogInWindow.window.setTitle(properties.getProperty("titleLogInWindow"));
    }


    public void readLanguageProperties(String path) {
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
                (new Thread(basicWindow)).start();
            } catch (Exception e) {
                e.printStackTrace();
            }

            menuBar.getMenus().remove(languageMenu);
            basicWindow.runThreadsWindow();
            layout.getChildren().remove(grid);
            layout.setCenter(BasicWindow.gridPane);
            window.setWidth(260);
            window.setHeight(300);
        });
    }

    public void actionExitButton(){
        exitButton.setOnAction(event -> {
            exit(0);
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
        actionExitButton();

        grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(3);
        grid.setPadding(new Insets(0,10,10,10));
        grid.getChildren().addAll(namelabel, namefield, passlabel, passfield,
                loginButton, exitButton, stringdatalabel, datalabel);


        layout.setCenter(grid);

        scene = new Scene(layout, 260, 220);
        scene.getStylesheets().add("skins/RedSilverSkin.css");
        window.setTitle(properties.getProperty("titleLogInWindow"));
        window.setScene(scene);
        window.show();

    }

    public void makeLoginButton() {
        loginButton = new Button(properties.getProperty("loginButtonText"));
        grid.setConstraints(loginButton, 1, 3);
    }

    public void makeExitButton() {
        exitButton = new Button(properties.getProperty("exitButtonText"));
        grid.setConstraints(exitButton, 1,5);
    }

    public void makeNameFields() {
        namelabel = new Label(properties.getProperty("user"));
        namelabel.setId("bold-label");
        grid.setConstraints(namelabel, 0, 1);

        namefield = new TextField();
        namefield.setPromptText(properties.getProperty("user"));
        grid.setConstraints(namefield, 1, 1);
    }

    public void makePassFields() {
        passlabel = new Label(properties.getProperty("password"));
        passlabel.setId("bold-label");
        grid.setConstraints(passlabel, 0, 2);

        passfield = new TextField();
        passfield.setPromptText(properties.getProperty("password"));
        grid.setConstraints(passfield, 1, 2);
    }

    public void makeDateFields(){
        stringdatalabel = new Label(properties.getProperty("date"));
        grid.setConstraints(stringdatalabel, 0, 7);

        datalabel = new Label(" " + dataByRESTful.timeClock);
        datalabel.setId("bold-label");
        grid.setConstraints(datalabel, 1, 7);
    }

    public void makeSkinMenu(){
        skinMenu = new Menu(properties.getProperty("skin"));

        MenuItem redSkin = new MenuItem(properties.getProperty("skinred"));
        redSkin.setOnAction(event -> {
            scene.getStylesheets().clear();
            scene.getStylesheets().add("skins/RedSilverSkin.css");
            window.setScene(scene);
        });

        MenuItem blueSkin = new MenuItem(properties.getProperty("skinblue"));
        blueSkin.setOnAction(event -> {
            scene.getStylesheets().clear();
            scene.getStylesheets().add("skins/BlueWhiteSkin.css");
            window.setScene(scene);
        });

        MenuItem greenSkin = new MenuItem(properties.getProperty("skingreen"));
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
            readLanguageProperties("src\\main\\resources\\pol.properties");
            layout.getChildren().remove(grid);
            layout.getChildren().remove(menuBar);
            setup();
            languagePol.setSelected(true);
        });
        RadioMenuItem languageAng = new RadioMenuItem(properties.getProperty("eng"));
        languageAng.setOnAction(event -> {
            readLanguageProperties("src\\main\\resources\\ang.properties");
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
        makeExitButton();
    }

    @Override
    public void makeAllFields() {
        makePassFields();
        makeNameFields();
        makeDateFields();
    }
}

