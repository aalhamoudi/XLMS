package Toolbar;

import View.ViewPane;
import View.ViewPaneGroup;
import View.ViewPaneTitle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TabPane;
import javafx.scene.input.TransferMode;

public class Toolbar extends ViewPaneGroup {

    DoubleProperty defaultHeightProperty = new SimpleDoubleProperty(150);
    private BooleanProperty isCollapsedProperty = new SimpleBooleanProperty(false);

    public Toolbar()
    {

        setOnDragOver(e -> {
            e.acceptTransferModes(TransferMode.NONE);
            dragStage.setX(e.getScreenX() + 5);
            dragStage.setY(e.getScreenY() + 5);
            e.consume();
        });





        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        setMinHeight(getDefaultHeight());
        setMaxHeight(getDefaultHeight());
    }

    public Toolbar(ToolbarTab... tabs)
    {
        this();
        getTabs().addAll(tabs);


    }

    private void setIsCollapsed(boolean collapsed) {
        isCollapsedProperty.set(collapsed);
    }

    private boolean getIsCollapsed() {
        return isCollapsedProperty.get();
    }

    public boolean isCollapsed() {
        return getIsCollapsed();
    }

    public void setDefaultHeightProperty(double height)
    {
        defaultHeightProperty.set(height);
    }

    public double getDefaultHeight()
    {
        return defaultHeightProperty.get();
    }

    public void add(ToolbarTab tab)
    {
        getTabs().add(tab);
    }

    public void addAll(ToolbarTab... tabs)
    {
        getTabs().addAll(tabs);
    }

    public void collapse()
    {
        if (!isCollapsed()) {
            setMinHeight(getTabMaxHeight());
            setMaxHeight(getTabMaxHeight());
            setIsCollapsed(true);
        }
    }


    public void expand()
    {
        if (isCollapsed()) {
            setMinHeight(getDefaultHeight());
            setMaxHeight(getDefaultHeight());
            setIsCollapsed(false);
        }
    }
}
