package gui.addobject;


import database.SupportDatabase;
import database.entity.Client;
import database.entity.Invoice;
import database.entity.Room;
import events.InvoiceAddedEvent;
import gui.IStandardGUIclass;
import gui.LogInWindow;
import gui.NewAlert;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.time.LocalDateTime;


/**
 * Created by Kuba on 2018-01-27.
 */
public class AddInvoiceWindow implements Runnable, IStandardGUIclass {

    public GridPane gridPane = new GridPane();
    private Label pesellabel,
            numberRoomlabel,
            howManyDayslabel;

    private TextField peselfield,
            numbeRoomfield,
            howManyDaysfield;

    private Button backToBasicWindowButton,
            addInvoicetButton;

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
    public void run(){
    }

    public void makeBackToWindowButton() {
        backToBasicWindowButton = new Button(LogInWindow.properties.getProperty("backToMenu"));
        gridPane.add(backToBasicWindowButton, 2, 7);

        backToBasicWindowButton.setOnAction(event -> {
            LogInWindow.backToBasicWindow();
        });
    }

    public void makeNumberRoomFields() {
        numberRoomlabel = new Label(LogInWindow.properties.getProperty("numberRoom"));
        numberRoomlabel.setId("bold-label");
        numberRoomlabel.setPrefWidth(80);
        gridPane.add(numberRoomlabel, 1, 1);


        numbeRoomfield = new TextField();
        numbeRoomfield.setPromptText(LogInWindow.properties.getProperty("numberRoom"));
        numbeRoomfield.setPrefWidth(10);
        gridPane.add(numbeRoomfield, 2, 1);
    }

    public void makePeselFields() {

        pesellabel = new Label("PESEL: ");
        pesellabel.setId("bold-label");
        pesellabel.setPrefWidth(80);
        gridPane.add(pesellabel, 1, 2);


        peselfield = new TextField();
        peselfield.setPromptText("pesel");
        peselfield.setPrefWidth(70);
        gridPane.add(peselfield, 2, 2);

    }

    public void makeHowManyDaysFields() {

        howManyDayslabel = new Label(LogInWindow.properties.getProperty("howManyDays"));
        howManyDayslabel.setId("bold-label");
        howManyDayslabel.setPrefWidth(80);
        gridPane.add(howManyDayslabel, 1, 3);


        howManyDaysfield = new TextField();
        howManyDaysfield.setPromptText(LogInWindow.properties.getProperty("howManyDays"));
        howManyDaysfield.setPrefWidth(70);
        gridPane.add(howManyDaysfield, 2, 3);

    }

    public void makeAddInvoiceButton() {
        addInvoicetButton = new Button(LogInWindow.properties.getProperty("addInvoiceButton"));
        gridPane.add(addInvoicetButton, 2, 4);
    }

    public void actionInvoiceButton(){
        addInvoicetButton.setOnAction(event -> {
            addInvoicetButton.fireEvent(new InvoiceAddedEvent(InvoiceAddedEvent.ADD_INVOICE_EVENT_EVENT_TYPE,
                    invoice, room, client));
        });
    }

    public void addToDatabase(){

        invoice = new Invoice();
        client = new Client();
        room = new Room();

        client.setPesel(peselfield.getText());
        room.setNumberRoom(Integer.parseInt(numbeRoomfield.getText()));
        invoice.setDateInsue(java.time.LocalDateTime.now());
        invoice.setDateExpiration(LocalDateTime.now().plusDays(Long.parseLong(howManyDaysfield.getText())));
        invoice.setRoom(room);
        invoice.setClient(client);

        try {
            SupportDatabase.persistSimpleObject(invoice);
            new NewAlert("Information", "Zamowienie zostalo dodane",
                    "Zamowienie zostalo pomyslnie dodane");
        } catch (Exception e) {
            new NewAlert("Error", "Zamowienie nie zostalo dodane",
                    "Zamowienie nie zostalo dodane \n"+
                    "Sprobuj ponownie");
        }

    }

}
