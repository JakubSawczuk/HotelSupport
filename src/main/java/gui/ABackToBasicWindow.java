package gui;

import javafx.scene.layout.GridPane;

/**
 * Created by Kuba on 2018-01-28.
 */
public abstract class ABackToBasicWindow {


    public void backToBasicWindow(GridPane grid){
        BasicWindow basicWindow = InstancesSet.getInstanceBasicWindow();
        removeGrid(grid);
        setupGUIBasicWindow(basicWindow);
        setupSizeBasicWindow(basicWindow);
        setupTitleWindow();
    }

    public void removeGrid(GridPane grid){
        LogInWindow.layout.getChildren().remove(grid);
    }

    public void setupGUIBasicWindow(BasicWindow basicWindow){
        basicWindow.setup();
    }

    public void setupSizeBasicWindow(BasicWindow basicWindow){
        LogInWindow.layout.setCenter(basicWindow.gridPane);
        LogInWindow.window.setWidth(260);
        LogInWindow.window.setHeight(300);
    }

    public void setupTitleWindow(){
        LogInWindow.window.setTitle(LogInWindow.properties.getProperty("titleLogInWindow"));
    }


}
