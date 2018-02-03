package gui;

import database.SupportDatabase;
import events.LogInEvent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import timewithrest.DataByRESTful;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.exit;

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

    private static GridPane grid;
    private static Scene scene;

    private Label namelabel,
            passlabel,
            stringdatelabel,
            datelabel;

    private TextField namefield,
            passfield;

    private ToggleButton exitButton,
            loginButton;


    private SupportDatabase supportDatabase = new SupportDatabase();
    private BasicWindow basicWindow = InstancesSet.getInstanceBasicWindow();
    private DataByRESTful dataByRESTful = InstancesSet.getInstanceDataByRESTful();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //ToDo: Odkomentowac przy testowaniu resta
        //(new Thread(dataByRESTful)).start();
        // sleep(2700);
        window = primaryStage;
        readLanguageProperties("src\\main\\resources\\pol.properties");
        setup();

        primaryStage.setOnCloseRequest(event -> {
            try {
                SupportDatabase.entityManager.close();
                SupportDatabase.entityManagerFactory.close();
            } catch (NullPointerException e) {
            } finally {
                primaryStage.close();
            }
        });

    }

    private void readLanguageProperties(String path) {
        FileReader reader;
        properties = new Properties();
        try {
            reader = new FileReader(path);
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionLoginButton() {
        loginButton.addEventHandler(LogInEvent.LOG_IN_EVENT_EVENT_TYPE, event -> {
            actionLogInEvent();
        });

        loginButton.setOnAction(event -> {
            loginButton.fireEvent
                    (new LogInEvent(LogInEvent.LOG_IN_EVENT_EVENT_TYPE));
        });
    }

    private void actionExitButton() {
        exitButton.setOnAction(event -> {
            exit(0);
        });
    }

    private void actionLogInEvent() {
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
        grid.setPadding(new Insets(0, 10, 10, 10));
        grid.getChildren().addAll(namelabel, namefield, passlabel, passfield,
                loginButton, exitButton, stringdatelabel, datelabel);


        layout.setCenter(grid);

        scene = new Scene(layout, 260, 235);
        scene.getStylesheets().add("skins/RedSilverSkin.css");
        window.setTitle(properties.getProperty("titleLogInWindow"));
        window.setScene(scene);
        window.show();

    }

    private void makeLoginButton() {
        loginButton = new ToggleButton(properties.getProperty("loginButtonText"));
        loginButton.setId("toggle-button-login");
        grid.setConstraints(loginButton, 1, 3);


    }

    private void makeExitButton() {
        exitButton = new ToggleButton(properties.getProperty("exitButtonText"));
        exitButton.setId("toggle-button-exit");
        grid.setConstraints(exitButton, 1, 5);
    }

    private void makeNameFields() {
        namelabel = new Label(properties.getProperty("user"));
        namelabel.setId("bold-label");
        grid.setConstraints(namelabel, 0, 1);

        namefield = new TextField();
        namefield.setPromptText(properties.getProperty("user"));
        grid.setConstraints(namefield, 1, 1);
    }

    private void makePassFields() {
        passlabel = new Label(properties.getProperty("password"));
        passlabel.setId("bold-label");
        grid.setConstraints(passlabel, 0, 2);

        passfield = new TextField();
        passfield.setPromptText(properties.getProperty("password"));
        grid.setConstraints(passfield, 1, 2);
    }

    private void makeDateFields() {
        stringdatelabel = new Label(properties.getProperty("date"));
        stringdatelabel.setId("date-label");
        grid.setConstraints(stringdatelabel, 0, 7);

        datelabel = new Label(" " + dataByRESTful.timeClock);
        datelabel.setId("bold-label");
        grid.setConstraints(datelabel, 1, 7);
    }

    private void makeSkinMenu() {
        skinMenu = new Menu(properties.getProperty("skin"));

        try {
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
        }catch (Exception e){
            new NewAlert("Error", properties.getProperty("errorSkinTitle"), properties.getProperty("errorSkinHeader"));
        }
    }

    private void makeLanguageMenu() {
        languageMenu = new Menu(properties.getProperty("language"));
        ToggleGroup difficultyToggle = new ToggleGroup();
        RadioMenuItem languagePol = new RadioMenuItem(properties.getProperty("pol"));
        languagePol.setOnAction(event -> {
            try {
                readLanguageProperties("src\\main\\resources\\pol.properties");
                layout.getChildren().remove(grid);
                layout.getChildren().remove(menuBar);
                setup();
                languagePol.setSelected(true);
            }catch (Exception e){
                new NewAlert("Error", "Error has occurred", "The skin did not load");
            }
        });
        RadioMenuItem languageAng = new RadioMenuItem(properties.getProperty("eng"));
        languageAng.setOnAction(event -> {
            try {
                readLanguageProperties("src\\main\\resources\\ang.properties");
                layout.getChildren().remove(grid);
                layout.getChildren().remove(menuBar);
                setup();
                languageAng.setSelected(true);
            }catch (Exception e){
                new NewAlert("Error", "Wystapil blad", "Skorka nie zostala zmieniona");
            }

        });

        languagePol.setToggleGroup(difficultyToggle);
        languageAng.setToggleGroup(difficultyToggle);
        languageMenu.getItems().addAll(languagePol, languageAng);
    }

    private void makeFileMenu() {
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

