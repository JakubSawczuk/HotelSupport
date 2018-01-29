package gui.tablesettings;

import database.SupportDatabase;
import database.entity.Room;
import gui.LogInWindow;
import gui.NewAlert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;

/**
 * Created by Kuba on 2018-01-18.
 */
public class TableViewSettings {

    public static javafx.scene.control.TableView<TabRow> newTable(GridPane grid, double prefWidth, double prefHeight) {
        javafx.scene.control.TableView<TabRow> tableView = new javafx.scene.control.TableView<>();
        tableView.setEditable(true);
        TableColumn<TabRow, String> first = new TableColumn<>();
        first.setCellValueFactory(new PropertyValueFactory<>("first"));
        first.setPrefWidth((prefWidth - 4) / 2);
        TableColumn<TabRow, String> second = new TableColumn<>();
        second.setCellValueFactory(new PropertyValueFactory<>("second"));
        second.setPrefWidth((prefWidth - 4) / 2);
        tableView.getColumns().addAll(first, second);
        tableView.setPrefSize(prefWidth, prefHeight);


        if (LogInWindow.window.getTitle().equals("Edytowanie pokoju") || LogInWindow.window.getTitle().equals("Edit room")) {
            second.setCellFactory(TextFieldTableCell.forTableColumn());
            second.setOnEditCommit(
                    event -> {
                        event.getTableView().getItems().get(event.getTablePosition().getRow()).setSecond(event.getNewValue());

                        try {
                            Room room = SupportDatabase.entityManager.find(Room.class, Integer.parseInt(tableView.getItems().get(0).getSecond()));
                            if (event.getTablePosition().getRow() == 1) {
                                if (event.getNewValue().equals("tak") || event.getNewValue().equals("Tak")) {
                                    room.setAvailable(true);
                                } else if (event.getNewValue().equals("nie") || event.getNewValue().equals("Nie")) {
                                    room.setAvailable(false);
                                }
                            } else if (event.getTablePosition().getRow() == 2) {
                                room.setComfort(event.getNewValue());
                            } else if (event.getTablePosition().getRow() == 3) {
                                room.setCapacity(Integer.parseInt(event.getNewValue()));
                            } else if (event.getTablePosition().getRow() == 4) {
                                if (event.getNewValue().equals("tak") || event.getNewValue().equals("Tak")) {
                                    room.setClear(true);
                                } else if (event.getNewValue().equals("nie") || event.getNewValue().equals("Nie")) {
                                    room.setClear(false);
                                }
                            }
                            SupportDatabase.persistSimpleObject(room);
                            new NewAlert("Information", null, LogInWindow.properties.getProperty("editSucces"));
                        } catch (Exception e) {
                            new NewAlert("Error", LogInWindow.properties.getProperty("titleErrorAddRoom"),
                                    LogInWindow.properties.getProperty("headerErrorAddRoom"));
                        }


                    });
        }
        grid.add(tableView, 0, 2, 3, 1);
        return tableView;
    }
}
