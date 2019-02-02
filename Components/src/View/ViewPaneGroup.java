package View;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.input.DataFormat;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewPaneGroup extends TabPane {

    public int id = -1;
    public static final DataFormat format = new DataFormat("ViewPane");
    public static Stage dragStage = new Stage();
    public ViewPaneGroup dragGroup;


    public Scene dragScene;

    public ViewPaneGroup() {
        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        setStyle("-fx-padding: -5, 0, 0, 0;");


        setOnDragDetected(event -> {

        });

        setOnDragOver(event -> {
            if (event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
                dragStage.setX(event.getScreenX() + 5);
                dragStage.setY(event.getScreenY() + 5);
            }
            event.consume();

        });

        setOnDragEntered(event -> {
            if (event.getDragboard().hasString() && event.getGestureSource() != this)
                setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null)));
            event.consume();
        });

        setOnDragExited(event -> {
            if (event.getDragboard().hasString() && event.getGestureSource() != this)
                setBorder(null);
            event.consume();
        });

        setOnDragDropped(event -> {
            if (event.getDragboard().hasString()) {

                setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));

                ViewPaneTitle title = (ViewPaneTitle) event.getGestureSource();
                ViewPane pane = title.getViewPane();
                ViewPaneGroup group = title.getPaneGroup();


                group.getTabs().remove(pane);

                if (pane.parent == this)
                    getTabs().add(pane.index, pane);
                else {
                    getTabs().add(pane);
                    pane.parent = this;
                }

                dragStage.hide();
                event.setDropCompleted(true);
                event.consume();
            }

        });

        Platform.runLater(() -> {

        });
    }


    static {
        dragStage.initStyle(StageStyle.TRANSPARENT);
        dragStage.setOpacity(0.5);

    }

    public void initDragStage() {
        dragGroup = new ViewPaneGroup();
        dragScene  = new Scene(dragGroup);

        dragStage.setHeight(getHeight());
        dragStage.setWidth(getWidth());
        dragStage.setScene(dragScene);
    }

    public void add(ViewPane pane)
    {
        getTabs().add(pane);
    }

    public void remove(ViewPane pane) {
        getTabs().remove(pane);
    }
}
