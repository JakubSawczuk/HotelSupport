package gui.searchobject;

import database.SupportDatabase;
import database.entity.Room;
import events.EditRoomEvent;
import gui.*;
import gui.tablesettings.TabRow;
import gui.tablesettings.TableViewSettings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;

/**
 * Created by Kuba on 2018-01-27.
 */
public class EditRoomWindow extends ABackToBasicWindow implements Runnable, IStandardGUIclass {

    public GridPane gridPane = new GridPane();
    private TableView<TabRow> tableView = null;
    private Label numberRoomlabel;
    private TextField numberRoomfield;
    private ToggleButton backToBasicWindowButton;
    private Button searchRoomButton;
    private ImageView imageView;
    DoubleProperty zoomProperty = new SimpleDoubleProperty(200);


    @Override
    public void run() {
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
        gridPane.setHgap(4);
        gridPane.setPadding(new Insets(10, 20, 5, 20));

        makeAllButtons();
        makeAllFields();
        actionSearchRoomButton();
    }

    private List<Room> queryGetRoom() {
        return SupportDatabase.entityManager.createQuery("SELECT r FROM Room r " +
                "WHERE numberRoom = ?1", database.entity.Room.class)
                .setParameter(1, Integer.parseInt(numberRoomfield.getText())).getResultList();

    }

    private void actionSearchRoomButton() {
        searchRoomButton.addEventHandler(EditRoomEvent.EDIT_ROOM_EVENT_EVENT_TYPE, event -> {
            tableView = TableViewSettings.newTable(gridPane, 241, 170);
            tableView.setEditable(true);
            updateTab(queryGetRoom().get(0));

            LogInWindow.window.setWidth(LogInWindow.window.getWidth()+250);
            LogInWindow.window.setHeight(LogInWindow.window.getHeight()+20);


            imageView = new ImageView(queryGetRoom().get(0).getImg());
            imageView.setFitHeight(200);
            imageView.setFitWidth(250);
            imageView.setId("picture");

            gridPane.add(imageView, 4, 2);
        });

        searchRoomButton.setOnAction(event -> {
            try {
                searchRoomButton.fireEvent
                        (new EditRoomEvent(EditRoomEvent.EDIT_ROOM_EVENT_EVENT_TYPE, queryGetRoom().get(0)));
            }catch (Exception e){
                new NewAlert(
                        "Error", LogInWindow.properties.getProperty("header"), LogInWindow.properties.getProperty("reasonRoom")
                );
            }
        });

    }

    private void makeNumberRoomFields() {
        numberRoomlabel = new Label(LogInWindow.properties.getProperty("numberRoom"));
        numberRoomlabel.setId("bold-label");
        numberRoomlabel.setPrefWidth(80);
        gridPane.add(numberRoomlabel, 1, 1);

        numberRoomfield = new TextField();
        numberRoomfield.setPromptText(LogInWindow.properties.getProperty("numberRoom"));
        numberRoomfield.setPrefWidth(70);
        gridPane.add(numberRoomfield, 2, 1);
    }

    private void makeBackToWindowButton() {
        backToBasicWindowButton = new ToggleButton(LogInWindow.properties.getProperty("backToMenu"));
        backToBasicWindowButton.setId("toggle-button-backToWindow");
        gridPane.add(backToBasicWindowButton, 2, 7);

        backToBasicWindowButton.setOnAction(event -> {
            gridPane.getChildren().remove(imageView);
            EditRoomWindow editRoomWindow = InstancesSet.getInstanceEditRoomWindow();
            editRoomWindow.backToBasicWindow(gridPane);
        });
    }

    private void makeSearchRoomButton() {
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
