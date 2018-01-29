package gui.searchobject;

import database.entity.Client;
import events.SearchClientEvent;
import gui.ABackToBasicWindow;
import gui.IStandardGUIclass;
import gui.InstancesSet;
import gui.LogInWindow;
import gui.tablesettings.TabRow;
import gui.tablesettings.TableViewSettings;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import search.client.SearchClientBO;

/**
 * Created by Kuba on 2018-01-16.
 */
public class SearchClientWindow extends ABackToBasicWindow implements Runnable, IStandardGUIclass {

    public GridPane gridPane = new GridPane();
    private TableView<TabRow> tableView = null;
    private Label namelabel;
    private TextField namefield;
    private Button searchClientButton;
    private ToggleButton backToBasicWindowButton;

    private SearchClientBO searchClientBO;

    @Override
    public void makeAllButtons() {
        makeBackToWindowButton();
        makeSearchClientButton();
    }

    @Override
    public void makeAllFields() {
        makeNameFields();
    }

    public void setup() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(3);
        gridPane.setPadding(new Insets(10, 20, 5, 20));

        makeAllButtons();
        makeAllFields();

        actionSearchClientButton();

    }

    private void makeNameFields() {
        namelabel = new Label(LogInWindow.properties.getProperty("client"));
        namelabel.setId("bold-label");
        namelabel.setPrefWidth(80);
        gridPane.add(namelabel, 1, 1);

        namefield = new TextField();
        namefield.setPromptText("pesel");
        namefield.setPrefWidth(70);
        gridPane.add(namefield, 2, 1);


    }

    private void makeBackToWindowButton() {
        backToBasicWindowButton = new ToggleButton(LogInWindow.properties.getProperty("backToMenu"));
        backToBasicWindowButton.setId("toggle-button-backToWindow");
        gridPane.add(backToBasicWindowButton, 2, 6);

        backToBasicWindowButton.setOnAction(event -> {
            SearchClientWindow searchClientWindow = InstancesSet.getInstanceSearchClientWindow();
            searchClientWindow.backToBasicWindow(gridPane);
        });
    }

    private void actionSearchClientButton() {

        searchClientButton.addEventHandler(SearchClientEvent.SEARCH_CLIEND_WINDOW_EVENT_EVENT_TYPE, event -> {
            updateTab(searchClientBO.getClientList().get(0));
        });

        searchClientButton.setOnAction(event -> {
            searchClientBO = new SearchClientBO(namefield.getText());
            tableView = TableViewSettings.newTable(gridPane, 240, 155);
            searchClientButton.fireEvent
                    (new SearchClientEvent(SearchClientEvent.SEARCH_CLIEND_WINDOW_EVENT_EVENT_TYPE, searchClientBO.getClientList().get(0)));
        });
    }

    private void makeSearchClientButton() {
        searchClientButton = new Button(LogInWindow.properties.getProperty("searchClientButton"));
        gridPane.add(searchClientButton, 2, 3);
    }

    private void updateTab(Client client) {
        tableView.getItems().add(new TabRow("PESEL", client.getPesel()));
        tableView.getItems().add(new TabRow(LogInWindow.properties.getProperty("firstname"), client.getFirstName()));
        tableView.getItems().add(new TabRow(LogInWindow.properties.getProperty("surname"), client.getSurName()));
        tableView.getItems().add(new TabRow("NIP", client.getNIP()));
        tableView.getItems().add(new TabRow(LogInWindow.properties.getProperty("companyName"), client.getCompanyName()));
    }

    @Override
    public void run() {

    }
}
