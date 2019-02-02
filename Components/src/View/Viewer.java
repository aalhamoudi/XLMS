package View;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.stage.Screen;

public class Viewer extends SplitPane {

    public ObservableList<ViewPaneGroup> paneGroups = FXCollections.observableArrayList();
    public ObjectProperty<SplitPane> middleProperty = new SimpleObjectProperty<SplitPane>();
    public ObjectProperty<ViewPaneGroup> topProperty = new SimpleObjectProperty<>();
    public ObjectProperty<ViewPaneGroup> leftProperty = new SimpleObjectProperty<>();
    public ObjectProperty<ViewPaneGroup> bottomProperty = new SimpleObjectProperty<>();
    public ObjectProperty<ViewPaneGroup> rightProperty = new SimpleObjectProperty<>();
    public ObjectProperty<ViewPaneGroup> centerProperty = new SimpleObjectProperty<>();

    public Viewer() {
        setOrientation(Orientation.VERTICAL);

        getStylesheets().add(getClass().getResource("view.css").toExternalForm());


        topProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && getItems().contains(oldValue)) {
                getItems().remove(oldValue);
                paneGroups.remove(oldValue);
            }
            getItems().add(0, newValue);
            paneGroups.add(newValue);
            newValue.id = paneGroups.indexOf(newValue);
        });

        bottomProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && getItems().contains(oldValue)) {
                getItems().remove(oldValue);
                paneGroups.remove(oldValue);
            }

            if (getItems().size() == 2)
                getItems().add(2, newValue);
            else
                getItems().add(newValue);

            paneGroups.add(newValue);
            newValue.id = paneGroups.indexOf(newValue);


        });

        middleProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && getItems().contains(oldValue)) {
                getItems().remove(oldValue);
            }

            if (getItems().size() > 1)
                getItems().add(1, newValue);

            else
                getItems().add(newValue);


        });

        leftProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && middleProperty.get().getItems().contains(oldValue)) {
                middleProperty.get().getItems().remove(oldValue);
                paneGroups.remove(oldValue);
            }
            middleProperty.get().getItems().add(0, newValue);

            paneGroups.add(newValue);
            newValue.id = paneGroups.indexOf(newValue);

        });

        centerProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && middleProperty.get().getItems().contains(oldValue)) {
                middleProperty.get().getItems().remove(oldValue);
                paneGroups.remove(oldValue);
            }

            if (middleProperty.get().getItems().size() > 1)
                middleProperty.get().getItems().add(1, newValue);
            else
                middleProperty.get().getItems().add(newValue);

            paneGroups.add(newValue);
            newValue.id = paneGroups.indexOf(newValue);

        });


        rightProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && middleProperty.get().getItems().contains(oldValue)) {
                middleProperty.get().getItems().remove(oldValue);
                paneGroups.remove(oldValue);
            }

            if (middleProperty.get().getItems().size() == 2)
                middleProperty.get().getItems().add(2, newValue);
            else
                middleProperty.get().getItems().add(newValue);

            paneGroups.add(newValue);
            newValue.id = paneGroups.indexOf(newValue);

        });

        for (SplitPane.Divider divider: getDividers()){

        }
        setOnMouseReleased(event -> System.out.println("Test"));
        setOnDragDropped(event -> {
            ViewPane pane = (ViewPane) ((ViewPaneTitle) event.getGestureSource()).getViewPane();
            pane.parent.add(pane);

            ((ViewPaneTitle) event.getGestureSource()).getPaneGroup().dragStage.hide();

        });


    }

    public void setTop(ViewPaneGroup pane) {
        topProperty.set(pane);
        topProperty.get().setPrefHeight(240);

    }


    public void setBottom(ViewPaneGroup pane) {
        bottomProperty.set(pane);
        bottomProperty.get().setPrefHeight(240);

    }

    public void setMiddle(SplitPane pane) {
        middleProperty.set(pane);
        middleProperty.get().setMinHeight(Screen.getPrimary().getVisualBounds().getHeight() * 0.5);
        middleProperty.get().setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() * 0.75);
    }

    public void setLeft(ViewPaneGroup pane) {
        if (middleProperty.get() == null)
            setMiddle(new SplitPane());

        leftProperty.set(pane);
    }

    public void setCenter(ViewPaneGroup pane) {
        if (middleProperty.get() == null)
            setMiddle(new SplitPane());


        centerProperty.set(pane);
        centerProperty.get().setMinWidth(Screen.getPrimary().getVisualBounds().getWidth() * 0.5);
        centerProperty.get().setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 0.75);

    }

    public void setRight(ViewPaneGroup pane) {
        if (middleProperty.get() == null)
            setMiddle(new SplitPane());

        rightProperty.set(pane);

    }



    public ViewPaneGroup getTop() {
        return topProperty.get();
    }


    public ViewPaneGroup getBottom() {
        return  bottomProperty.get();
    }

    public SplitPane getMiddle()
    {
        return middleProperty.get();
    }

    public ViewPaneGroup getLeft() {
        return  leftProperty.get();
    }


    public ViewPaneGroup getCenter() {
        return centerProperty.get();
    }

    public ViewPaneGroup getRight() {
        return rightProperty.get();
    }

    public ObservableList<ViewPaneGroup> getPaneGroups()
    {
        return paneGroups;
    }
}
