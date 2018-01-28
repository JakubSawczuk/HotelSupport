package gui.searchobject;

import database.SupportDatabase;
import database.entity.Client;
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
 * Created by Kuba on 2018-01-16.
 */
public class SearchClientWindow implements Runnable, IStandardGUIclass {

    public GridPane gridPane = new GridPane();
    private TableView<TabRow> tableView = null;
    private Label namelabel;
    public TextField namefield;
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
        tableView = TableViewSettings.newTable(gridPane, 240, 155);
        gridPane.setPadding(new Insets(10, 20, 5, 20));


        makeAllButtons();
        makeAllFields();
        actionSearchClientButton();

    }

    public List<Client> queryGetClient() {
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

    public void makeBackToWindowButton() {
        BasicWindow basicWindow = InstancesSet.getInstanceBasicWindow();
        backToBasicWindowButton = new Button(LogInWindow.properties.getProperty("backToMenu"));
        gridPane.add(backToBasicWindowButton, 2, 6);

        backToBasicWindowButton.setOnAction(event -> {
            LogInWindow.layout.getChildren().remove(gridPane);
            basicWindow.setup();
            LogInWindow.layout.setCenter(basicWindow.gridPane);
            LogInWindow.window.setWidth(260);
            LogInWindow.window.setHeight(300);
        });
    }

    public void actionSearchClientButton() {
        searchClientButton.setOnAction(event -> {
            updateTab(queryGetClient().get(0));
        });
    }

    public void makeSearchClientButton() {
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
        System.out.println("Odpalilem showClientWindow");

    }

    // jak sie wcisnie enter
    /*LogInWindow.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case ENTER:
                    System.out.println("trololo");
                    break;
            }
        }
    });*/
}
