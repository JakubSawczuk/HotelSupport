package gui.searchobject;

import database.SupportDatabase;
import database.entity.Client;
import events.SearchClientEvent;
import gui.IStandardGUIclass;
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
 * Created by Kuba on 2018-01-16.
 */
public class SearchClientWindow implements Runnable, IStandardGUIclass {

    public GridPane gridPane = new GridPane();
    private TableView<TabRow> tableView = null;
    private Label namelabel;
    private TextField namefield;
    private Button backToBasicWindowButton,
            searchClientButton;

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

    private List<Client> queryGetClient() {
        return SupportDatabase.entityManager.createQuery("SELECT e FROM Client e " +
                "WHERE Pesel = ?1", database.entity.Client.class)
                .setParameter(1, namefield.getText()).getResultList();
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
        backToBasicWindowButton = new Button(LogInWindow.properties.getProperty("backToMenu"));
        gridPane.add(backToBasicWindowButton, 2, 6);

        backToBasicWindowButton.setOnAction(event -> {
            LogInWindow.backToBasicWindow();
        });
    }

    private void actionSearchClientButton() {

        searchClientButton.addEventHandler(SearchClientEvent.SEARCH_CLIEND_WINDOW_EVENT_EVENT_TYPE, event -> {
            updateTab(queryGetClient().get(0));
        });

        searchClientButton.setOnAction(event -> {
            tableView = TableViewSettings.newTable(gridPane, 240, 155);
            searchClientButton.fireEvent
                    (new SearchClientEvent(SearchClientEvent.SEARCH_CLIEND_WINDOW_EVENT_EVENT_TYPE, queryGetClient().get(0)));
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
