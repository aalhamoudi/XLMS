package View;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

import java.io.Serializable;

public class ViewPane extends Tab implements Serializable {



    public ObjectProperty<VBox> paneProperty = new SimpleObjectProperty<>();
    public ObjectProperty<ViewPaneTitle> titleProperty = new SimpleObjectProperty<>();

    public ViewPaneGroup parent;
    public int index = -1;

    public ViewPane(String title) {
        setupListeners();

        Platform.runLater(() -> {
            setTitle(new ViewPaneTitle(title, this, getPaneGroup()));
            index = getPaneGroup().getTabs().indexOf(this);
        });
        setPane(new VBox());
    }

    private void setupListeners() {
        titleProperty.addListener((observable, oldValue, newValue) -> {
            setGraphic(newValue);
        });


        paneProperty.addListener((observable, oldValue, newValue) -> {
            setContent(newValue);
        });



    }

    public ViewPaneGroup getPaneGroup()
    {
        return (ViewPaneGroup) getTabPane();
    }

    public void add(Node node)
    {
        getPane().getChildren().add(node);
    }

    public void addAll(Node... nodes)
    {
        getPane().getChildren().addAll(nodes);
    }

    public ViewPane(String title, VBox pane)
    {
        this(title);
        setPane(pane);
    }

    public void setTitle(ViewPaneTitle title) {
        titleProperty.set(title);

        getTitle().setOnDragDetected(event -> {
            Dragboard dragboard = getTitle().startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("ViewPane");
            dragboard.setContent(content);

            getPaneGroup().initDragStage();
            parent = getPaneGroup();
            parent.getTabs().remove(this);
            parent.dragGroup.add(this);


            parent.dragStage.setX(event.getSceneX() - 16);
            parent.dragStage.setY(event.getSceneY() + 14);
            parent.dragStage.show();

            event.consume();
        });

        getTitle().setOnDragDone(e -> {
            if (getPaneGroup() == parent.dragGroup)
            {
                parent.dragGroup.remove(this);
                parent.getTabs().add(index, this);
                ViewPaneGroup.dragStage.hide();
            }

            e.consume();
        });
    }

    public ViewPaneTitle getTitle() {
        return titleProperty.get();
    }
    public void setPane(VBox pane)
    {
        paneProperty.set(pane);
    }

    public VBox getPane()
    {
        return paneProperty.get();
    }


}
