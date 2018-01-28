package gui.addobject;

import addclient.Builder;
import addclient.ClientAdded;
import addclient.Director;
import database.SupportDatabase;
import database.entity.Client;
import exceptions.DuplicatePrimaryKeyException;
import gui.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by Kuba on 2018-01-27.
 */
public class AddClientWindow implements Runnable, IStandardGUIclass {

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

    private Button backToBasicWindowButton,
            addClientButton;


    public void setup() {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 5, 20));
        gridPane.setHgap(2);
        gridPane.setVgap(12);

        makeAllFields();
        makeAllButtons();

    }

    public void addToDatabase(){
        Director director = new Director();
        Builder builder = new ClientAdded();
        director.setBuilder(builder);
        director.addClientToDatabase(peselfield.getText(), companyNamefield.getText(),
                firstnamefield.getText(), surnamefield.getText(), NIPfield.getText());
        Client client = director.getClient();

        try {
            SupportDatabase.persistObject(client, peselfield.getText());
            new NewAlert("Information", "Klient zosal dodany",
                    "Klient zostal prawidlowo dodany");
        } catch (DuplicatePrimaryKeyException e) {}

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

    public void makePeselFields() {
        pesellabel = new Label("PESEL:");
        pesellabel.setId("bold-label");
        gridPane.add(pesellabel, 1, 1);

        peselfield = new TextField();
        peselfield.setPrefWidth(100);
        peselfield.setPromptText("pesel");
        gridPane.add(peselfield, 2, 1);
    }

    public void makeFirstnameFields() {
        firstnamelabel = new Label(LogInWindow.properties.getProperty("firstname"));
        firstnamelabel.setPrefWidth(80);
        firstnamelabel.setId("bold-label");
        gridPane.add(firstnamelabel, 1, 3);

        firstnamefield = new TextField();
        firstnamefield.setPrefWidth(100);
        firstnamefield.setPromptText(LogInWindow.properties.getProperty("firstname"));
        gridPane.add(firstnamefield, 2, 3);
    }

    public void makeSurnameFields() {
        surnamelabel = new Label(LogInWindow.properties.getProperty("surname"));
        surnamelabel.setPrefWidth(80);
        surnamelabel.setId("bold-label");
        gridPane.add(surnamelabel, 1, 5);

        surnamefield = new TextField();
        surnamefield.setPrefWidth(100);
        surnamefield.setPromptText(LogInWindow.properties.getProperty("surname"));
        gridPane.add(surnamefield, 2, 5);
    }

    public void makeCompanyNameFields() {
        companyNamelabel = new Label(LogInWindow.properties.getProperty("companyName"));
        companyNamelabel.setPrefWidth(80);
        companyNamelabel.setId("bold-label");
        gridPane.add(companyNamelabel, 1, 7);

        companyNamefield = new TextField();
        companyNamefield.setPrefWidth(100);
        companyNamefield.setPromptText(LogInWindow.properties.getProperty("companyName"));
        gridPane.add(companyNamefield, 2, 7);
    }

    public void makeNIPFields() {
        NIPlabel = new Label("NIP: ");
        NIPlabel.setPrefWidth(80);
        NIPlabel.setId("bold-label");
        gridPane.add(NIPlabel, 1, 9);

        NIPfield = new TextField();
        NIPfield.setPrefWidth(100);
        NIPfield.setPromptText("nip");
        gridPane.add(NIPfield, 2, 9);
    }

    public void makeAddClientButton() {
        addClientButton = new Button(LogInWindow.properties.getProperty("addClient"));
        gridPane.add(addClientButton, 2, 10);

        addClientButton.setOnAction(event -> {
            addToDatabase();
        });
    }

    public void makeBackToWindowButton() {
        BasicWindow basicWindow = InstancesSet.getInstanceBasicWindow();
        backToBasicWindowButton = new Button(LogInWindow.properties.getProperty("backToMenu"));
        gridPane.add(backToBasicWindowButton, 2, 12);

        backToBasicWindowButton.setOnAction(event -> {
            LogInWindow.layout.getChildren().remove(gridPane);
            basicWindow.setup();
            LogInWindow.layout.setCenter(basicWindow.gridPane);
            LogInWindow.window.setWidth(200);
            LogInWindow.window.setHeight(350);
        });
    }




    @Override
    public void run() {
        System.out.println("Odpalilem addClientWindow");
    }
}
