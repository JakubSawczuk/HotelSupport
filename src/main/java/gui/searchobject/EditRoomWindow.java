package gui.searchobject;

import database.SupportDatabase;
import database.entity.Room;
import gui.BasicWindow;
import gui.IStandardGUIclass;
import gui.InstancesSet;
import gui.LogInWindow;
import gui.tablesettings.TabRow;
import gui.tablesettings.TableViewSettings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.List;

/**
 * Created by Kuba on 2018-01-27.
 */
public class EditRoomWindow implements Runnable, IStandardGUIclass {

    public GridPane gridPane = new GridPane();
    private TableView<TabRow> tableView = null;
    private Label numberRoomlabel;
    public TextField numberRoomfield;
    private Button backToBasicWindowButton,
            searchRoomButton;

    @Override
    public void run() {
        System.out.println("Odpalilem searchRoomWindow");
    }

    @Override
    public void makeAllButtons() {
        makeBackToWindowButton();
        makeSearchRoomButton();
    }

    @Override
    public void makeAllFields() {
        makeNumberRoomFields();
    }

    public void setup() {
        gridPane.setVgap(10);
        gridPane.setHgap(3);
        tableView = TableViewSettings.newTable(gridPane, 192, 170);
        tableView.setEditable(true);
        gridPane.setPadding(new Insets(10, 20, 5, 20));

        makeAllButtons();
        makeAllFields();
        actionSearchRoomButton();


    }

    public List<Room> queryGetRoom(){
        return SupportDatabase.entityManager.createQuery("SELECT r FROM Room r " +
                        "WHERE numberRoom = ?1", database.entity.Room.class)
                .setParameter(1, Integer.parseInt(numberRoomfield.getText())).getResultList();

    }

    public void actionSearchRoomButton(){
        searchRoomButton.setOnAction(event -> {
            updateTab(queryGetRoom().get(0));
        });
    }

    public void makeNumberRoomFields() {
        numberRoomlabel = new Label(LogInWindow.properties.getProperty("numberRoom"));
        numberRoomlabel.setId("bold-label");
        numberRoomlabel.setPrefWidth(80);
        gridPane.add(numberRoomlabel, 1, 1);


        numberRoomfield = new TextField();
        numberRoomfield.setPromptText(LogInWindow.properties.getProperty("numberRoom"));
        numberRoomfield.setPrefWidth(70);
        gridPane.add(numberRoomfield, 2, 1);
    }

    public void makeBackToWindowButton() {
        BasicWindow basicWindow = InstancesSet.getInstanceBasicWindow();
        backToBasicWindowButton = new Button(LogInWindow.properties.getProperty("backToMenu"));
        gridPane.add(backToBasicWindowButton, 2, 7);

        backToBasicWindowButton.setOnAction(event -> {
            LogInWindow.layout.getChildren().remove(gridPane);
            basicWindow.setup();
            LogInWindow.layout.setCenter(basicWindow.gridPane);
            LogInWindow.window.setWidth(260);
            LogInWindow.window.setHeight(300);
        });
    }

    public void makeSearchRoomButton() {
        searchRoomButton = new Button(LogInWindow.properties.getProperty("searchRoomButton"));
        gridPane.add(searchRoomButton, 2, 3);
    }

    private void updateTab(Room room) {
        String yesOrNoAvaialbe, yesOrNotIsClear;
        if (room.isAvailable()) {
            yesOrNoAvaialbe = "Tak";
        } else {
            yesOrNoAvaialbe = "Nie";
        }

        if (room.isClear()) {
            yesOrNotIsClear = "Tak";
        } else {
            yesOrNotIsClear = "Nie";
        }

        tableView.getItems().add(new TabRow(LogInWindow.properties.getProperty("numberRoom"), Integer.toString(room.getNumberRoom())));
        tableView.getItems().add(new TabRow(LogInWindow.properties.getProperty("available"), yesOrNoAvaialbe));
        tableView.getItems().add(new TabRow(LogInWindow.properties.getProperty("comfort"), room.getComfort()));
        tableView.getItems().add(new TabRow(LogInWindow.properties.getProperty("capacity"), Integer.toString(room.getCapacity())));
        tableView.getItems().add(new TabRow(LogInWindow.properties.getProperty("clear"), yesOrNotIsClear));
        tableView.getItems().add(new TabRow(LogInWindow.properties.getProperty("price"), Float.toString(room.getPrice())));
    }
}
