package gui;

import gui.addobject.AddClientWindow;
import gui.addobject.AddInvoiceWindow;
import gui.searchobject.EditRoomWindow;
import gui.searchobject.SearchClientWindow;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import static java.lang.Thread.sleep;

/**
 * Created by Kuba on 2018-01-17.
 */
public class BasicWindow implements Runnable, IStandardGUIclass {

    public static GridPane gridPane = new GridPane();
    Button addClientButton;
    Button searchClientButton;
    Button addInvoiceButton;
    Button editRoomButton;
    SearchClientWindow searchClientWindow = InstancesSet.getInstanceSearchClientWindow();
    AddClientWindow addClientWindow = InstancesSet.getInstanceAddClientWindow();
    AddInvoiceWindow addInvoiceWindow = InstancesSet.getInstanceAddInvoiceWindow();
    EditRoomWindow editRoomWindow = InstancesSet.getInstanceEditRoomWindow();

    public void runThreadsWindow() {
        try {
            (new Thread(searchClientWindow)).start();
            (new Thread(addClientWindow)).start();
            (new Thread(addClientWindow)).start();
            (new Thread(addInvoiceWindow)).start();
            (new Thread(addInvoiceWindow)).start();
            (new Thread(editRoomWindow)).start();
            sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void configureActionWindow(int width, int height, String title) {
        LogInWindow.layout.getChildren().remove(gridPane);
        LogInWindow.window.setWidth(width);
        LogInWindow.window.setHeight(height);
        LogInWindow.window.setTitle(title);
    }

    public void buttonActions() {

        editRoomButton.setOnAction(event -> {
            editRoomWindow.setup();
            LogInWindow.layout.setCenter(editRoomWindow.gridPane);
            configureActionWindow(295, 420, LogInWindow.properties.getProperty("editRoomAction"));
        });

        searchClientButton.setOnAction(event -> {
            searchClientWindow.setup();
            LogInWindow.layout.setCenter(searchClientWindow.gridPane);
            configureActionWindow(310, 370, LogInWindow.properties.getProperty("searchClientAction"));
        });

        addClientButton.setOnAction(event -> {
            addClientWindow.setup();
            LogInWindow.layout.setCenter(addClientWindow.gridPane);
            configureActionWindow(300, 405, LogInWindow.properties.getProperty("addClientAction"));
        });

        addInvoiceButton.setOnAction(event -> {
            addInvoiceWindow.setup();
            LogInWindow.layout.setCenter(addInvoiceWindow.gridPane);
            configureActionWindow(295, 280, LogInWindow.properties.getProperty("addInvoiceAction"));
        });


    }


    @Override
    public void run() {
        setup();
    }

    @Override
    public void makeAllButtons() {
        makeAddClientButton();
        makeAddInvoiceButton();
        makeEditRoomButton();
        makeSearchClientButton();
    }

    @Override
    public void makeAllFields() {
    }

    public void setup() {
        gridPane.setPadding(new Insets(10, 10, 10, 40));
        gridPane.setVgap(10);
        gridPane.setHgap(4);

        makeAllButtons();
        makeAllFields();

        gridPane.getChildren().addAll(addClientButton, searchClientButton, addInvoiceButton, editRoomButton);

        buttonActions();
    }

    public void makeEditRoomButton() {
        editRoomButton = new Button(LogInWindow.properties.getProperty("editRoom"));
        GridPane.setConstraints(editRoomButton, 1, 10);
    }

    public void makeAddInvoiceButton() {
        addInvoiceButton = new Button(LogInWindow.properties.getProperty("addInvoice"));
        GridPane.setConstraints(addInvoiceButton, 1, 7);
    }

    public void makeSearchClientButton() {
        searchClientButton = new Button(LogInWindow.properties.getProperty("searchClient"));
        GridPane.setConstraints(searchClientButton, 1, 4);
    }

    public void makeAddClientButton() {
        addClientButton = new Button(LogInWindow.properties.getProperty("addClient"));
        GridPane.setConstraints(addClientButton, 1, 1);
    }

}



