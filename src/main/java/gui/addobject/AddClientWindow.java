package gui.addobject;

import add.client.BuilderClient;
import add.client.ClientAdded;
import add.client.Director;
import database.SupportDatabase;
import database.entity.Client;
import events.ClientAddedEvent;
import exceptions.DuplicatePrimaryKeyException;
import exceptions.IncorrectFormatPeselException;
import gui.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kuba on 2018-01-27.
 */
public class AddClientWindow extends ABackToBasicWindow implements IStandardGUIclass, Runnable {

    public GridPane gridPane = new GridPane();

    private Label pesellabel,
            firstnamelabel,
            surnamelabel,
            companyNamelabel,
            NIPlabel;


    private TextField peselfield,
            firstnamefield,
            surnamefield,
            companyNamefield,
            NIPfield;

    private Button addClientButton;

    private ToggleButton backToBasicWindowButton;

    private Client client;


    public void setup() {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 5, 20));
        gridPane.setHgap(2);
        gridPane.setVgap(12);

        makeAllFields();
        makeAllButtons();

        addClientButton.addEventHandler(ClientAddedEvent.CLIENT_ADDED_EVENT_EVENT_TYPE, event -> {
            addToDatabase();
        });

    }

    public void checkCorectnessClient() throws IncorrectFormatPeselException {
        Pattern patternPesel = Pattern.compile("[0-9]{11}");
        Matcher matcherPesel = patternPesel.matcher(peselfield.getText());


        if (!matcherPesel.matches()) {
            throw new IncorrectFormatPeselException();
        }
    }

    private void addToDatabase() {
        Director director = new Director();
        BuilderClient builderClient = new ClientAdded();
        director.setBuilderClient(builderClient);
        director.addClientToDatabase(peselfield.getText(), companyNamefield.getText(),
                firstnamefield.getText(), surnamefield.getText(), NIPfield.getText());
        Client client = director.getClient();


        try {
            checkCorectnessClient();
            SupportDatabase.persistObject(client, peselfield.getText());
            new NewAlert("Information", LogInWindow.properties.getProperty("addedClient"),
                    LogInWindow.properties.getProperty("addedClientOk"));
        } catch (DuplicatePrimaryKeyException e) {

        } catch (IncorrectFormatPeselException e) {

        }

    }

    public void makeAllFields() {
        makePeselFields();
        makeFirstnameFields();
        makeSurnameFields();
        makeCompanyNameFields();
        makeNIPFields();
    }

    public void makeAllButtons() {
        makeBackToWindowButton();
        makeAddClientButton();
    }

    private void makePeselFields() {
        pesellabel = new Label("PESEL:");
        pesellabel.setId("bold-label");
        gridPane.add(pesellabel, 1, 1);

        peselfield = new TextField();
        peselfield.setPrefWidth(100);
        peselfield.setPromptText("pesel");
        gridPane.add(peselfield, 2, 1);
    }

    private void makeFirstnameFields() {
        firstnamelabel = new Label(LogInWindow.properties.getProperty("firstname"));
        firstnamelabel.setPrefWidth(80);
        firstnamelabel.setId("bold-label");
        gridPane.add(firstnamelabel, 1, 3);

        firstnamefield = new TextField();
        firstnamefield.setPrefWidth(100);
        firstnamefield.setPromptText(LogInWindow.properties.getProperty("firstname"));
        gridPane.add(firstnamefield, 2, 3);
    }

    private void makeSurnameFields() {
        surnamelabel = new Label(LogInWindow.properties.getProperty("surname"));
        surnamelabel.setPrefWidth(80);
        surnamelabel.setId("bold-label");
        gridPane.add(surnamelabel, 1, 5);

        surnamefield = new TextField();
        surnamefield.setPrefWidth(100);
        surnamefield.setPromptText(LogInWindow.properties.getProperty("surname"));
        gridPane.add(surnamefield, 2, 5);
    }

    private void makeCompanyNameFields() {
        companyNamelabel = new Label(LogInWindow.properties.getProperty("companyName"));
        companyNamelabel.setPrefWidth(80);
        companyNamelabel.setId("bold-label");
        gridPane.add(companyNamelabel, 1, 7);

        companyNamefield = new TextField();
        companyNamefield.setPrefWidth(100);
        companyNamefield.setPromptText(LogInWindow.properties.getProperty("companyName"));
        gridPane.add(companyNamefield, 2, 7);
    }

    private void makeNIPFields() {
        NIPlabel = new Label("NIP: ");
        NIPlabel.setPrefWidth(80);
        NIPlabel.setId("bold-label");
        gridPane.add(NIPlabel, 1, 9);

        NIPfield = new TextField();
        NIPfield.setPrefWidth(100);
        NIPfield.setPromptText("nip");
        gridPane.add(NIPfield, 2, 9);
    }

    private void makeAddClientButton() {
        addClientButton = new Button(LogInWindow.properties.getProperty("addClient"));
        gridPane.add(addClientButton, 2, 10);

        addClientButton.setOnAction(event -> {
            addClientButton.fireEvent(new ClientAddedEvent(ClientAddedEvent.CLIENT_ADDED_EVENT_EVENT_TYPE, client));
        });

    }

    private void makeBackToWindowButton() {
        backToBasicWindowButton = new ToggleButton(LogInWindow.properties.getProperty("backToMenu"));
        backToBasicWindowButton.setId("toggle-button-backToWindow");
        gridPane.add(backToBasicWindowButton, 2, 12);

        backToBasicWindowButton.setOnAction(event -> {
            AddClientWindow addClientWindow = InstancesSet.getInstanceAddClientWindow();
            addClientWindow.backToBasicWindow(gridPane);
        });
    }


    @Override
    public void run() {
    }
}
