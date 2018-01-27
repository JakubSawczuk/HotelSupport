package gui.tablesettings;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;

/**
 * Created by Kuba on 2018-01-18.
 */
public class TableViewSettings {

    public static javafx.scene.control.TableView<TabRow> newTable(GridPane grid, double height, double prefWidth, double prefHeight) {
        javafx.scene.control.TableView<TabRow> tableView = new javafx.scene.control.TableView<TabRow>();
        tableView.setEditable(true);
        TableColumn<TabRow, String> first = new TableColumn<TabRow, String>("Nazwa parametru");
        first.setCellValueFactory(new PropertyValueFactory<TabRow, String>("first"));
        first.setMinWidth(125);
        TableColumn<TabRow, String> second = new TableColumn<TabRow, String>("Wartosc");
        second.setCellValueFactory(new PropertyValueFactory<TabRow, String>("second"));
        tableView.getColumns().addAll(first, second);
        tableView.setPrefSize(prefWidth, prefHeight);



        first.setCellFactory(TextFieldTableCell.forTableColumn());
        first.setOnEditCommit(
                event -> ((TabRow)event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setFirst(event.getNewValue())
        );

        second.setCellFactory(TextFieldTableCell.forTableColumn());
        second.setOnEditCommit(
                event -> ((TabRow)event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setSecond(event.getNewValue())
        );


        grid.add(tableView, 0, 2, 3, 1);
        return tableView;
    }



}
