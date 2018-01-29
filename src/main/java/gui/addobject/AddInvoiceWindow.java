package gui.addobject;


import database.SupportDatabase;
import database.entity.Client;
import database.entity.Invoice;
import database.entity.Room;
import events.InvoiceAddedEvent;
import exceptions.NumberOfDaysException;
import exceptions.SearchClientByPeselException;
import exceptions.SearchRoomByNrException;
import gui.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Created by Kuba on 2018-01-27.
 */
public class AddInvoiceWindow extends ABackToBasicWindow implements Runnable, IStandardGUIclass {

    public GridPane gridPane = new GridPane();
    private Label pesellabel,
            numberRoomlabel,
            howManyDayslabel;

    private TextField peselfield,
            numbeRoomfield,
            howManyDaysfield;

    private ToggleButton backToBasicWindowButton;
    private Button addInvoicetButton;


    private Invoice invoice;
    private Client client;
    private Room room;

    @Override
    public void makeAllFields() {
        makeNumberRoomFields();
        makePeselFields();
        makeHowManyDaysFields();
    }

    @Override
    public void makeAllButtons() {
        makeBackToWindowButton();
        makeAddInvoiceButton();
    }

    public void setup() {
        gridPane.setVgap(10);
        gridPane.setHgap(3);
        gridPane.setPadding(new Insets(10, 20, 5, 20));

        makeAllButtons();
        makeAllFields();
        actionInvoiceButton();

        addInvoicetButton.addEventHandler(InvoiceAddedEvent.ADD_INVOICE_EVENT_EVENT_TYPE, event -> {
            addToDatabase();
        });
    }

    @Override
    public void run() {
    }

    private void makeBackToWindowButton() {
        backToBasicWindowButton = new ToggleButton(LogInWindow.properties.getProperty("backToMenu"));
        backToBasicWindowButton.setId("toggle-button-backToWindow");
        gridPane.add(backToBasicWindowButton, 2, 7);

        backToBasicWindowButton.setOnAction(event -> {
            AddInvoiceWindow addInvoiceWindow = InstancesSet.getInstanceAddInvoiceWindow();
            addInvoiceWindow.backToBasicWindow(gridPane);
        });
    }

    private void makeNumberRoomFields() {
        numberRoomlabel = new Label(LogInWindow.properties.getProperty("numberRoom"));
        numberRoomlabel.setId("bold-label");
        numberRoomlabel.setPrefWidth(80);
        gridPane.add(numberRoomlabel, 1, 1);


        numbeRoomfield = new TextField();
        numbeRoomfield.setPromptText(LogInWindow.properties.getProperty("numberRoom"));
        numbeRoomfield.setPrefWidth(10);
        gridPane.add(numbeRoomfield, 2, 1);
    }

    private void makePeselFields() {

        pesellabel = new Label("PESEL: ");
        pesellabel.setId("bold-label");
        pesellabel.setPrefWidth(80);
        gridPane.add(pesellabel, 1, 2);


        peselfield = new TextField();
        peselfield.setPromptText("pesel");
        peselfield.setPrefWidth(70);
        gridPane.add(peselfield, 2, 2);

    }

    private void makeHowManyDaysFields() {

        howManyDayslabel = new Label(LogInWindow.properties.getProperty("howManyDays"));
        howManyDayslabel.setId("bold-label");
        howManyDayslabel.setPrefWidth(80);
        gridPane.add(howManyDayslabel, 1, 3);


        howManyDaysfield = new TextField();
        howManyDaysfield.setPromptText(LogInWindow.properties.getProperty("howManyDays"));
        howManyDaysfield.setPrefWidth(70);
        gridPane.add(howManyDaysfield, 2, 3);

    }

    private void makeAddInvoiceButton() {
        addInvoicetButton = new Button(LogInWindow.properties.getProperty("addInvoiceButton"));
        gridPane.add(addInvoicetButton, 2, 4);
    }

    private void actionInvoiceButton() {
        addInvoicetButton.setOnAction(event -> {
            addInvoicetButton.fireEvent(new InvoiceAddedEvent(InvoiceAddedEvent.ADD_INVOICE_EVENT_EVENT_TYPE,
                    invoice, room, client));
        });
    }

    private List<Room> queryGetRoom() {
        return SupportDatabase.entityManager.createQuery("SELECT r FROM Room r " +
                "WHERE numberRoom = ?1", database.entity.Room.class)
                .setParameter(1, Integer.parseInt(numbeRoomfield.getText())).getResultList();
    }

    private List<Client> queryGetClient() {
        return SupportDatabase.entityManager.createQuery("SELECT r FROM Client r " +
                "WHERE Pesel = ?1", database.entity.Client.class)
                .setParameter(1, peselfield.getText()).getResultList();
    }

    private void addToDatabase() {

        invoice = new Invoice();
        client = new Client();
        room = new Room();


        if (numbeRoomfield.getLength() != 0) {
            if (queryGetRoom().size() == 0) {
                throw new SearchRoomByNrException(peselfield.getText(), numbeRoomfield.getText(), howManyDaysfield.getText());
            }
        } else {
            throw new SearchRoomByNrException(peselfield.getText(), numbeRoomfield.getText(), howManyDaysfield.getText());
        }
        if (queryGetClient().size() == 0) {
            throw new SearchClientByPeselException(peselfield.getText(), numbeRoomfield.getText(), howManyDaysfield.getText());
        }
        if (Integer.parseInt(howManyDaysfield.getText()) < 1) {
            throw new NumberOfDaysException(peselfield.getText(), numbeRoomfield.getText(), howManyDaysfield.getText());
        }


        client.setPesel(peselfield.getText());
        invoice.setDateInsue(java.time.LocalDateTime.now());
        invoice.setDateExpiration(LocalDateTime.now().plusDays(Long.parseLong(howManyDaysfield.getText())));
        invoice.setRoom(room);
        invoice.setClient(client);

        try {
            SupportDatabase.persistSimpleObject(invoice);
            new NewAlert("Information", LogInWindow.properties.getProperty("titleAddInvoice"),
                    LogInWindow.properties.getProperty("headerAddInvoice"));
        } catch (Exception e) {
            new NewAlert("Error", LogInWindow.properties.getProperty("errtitleAddInvoice"),
                    LogInWindow.properties.getProperty("errheaderAddInvoice"));
        }

    }

}
