package View;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;

public class ViewPaneTitle extends Label {

    public ObjectProperty<ViewPane> viewPaneProperty = new SimpleObjectProperty<>();
    public ObjectProperty<ViewPaneGroup> paneGroupProperty = new SimpleObjectProperty<>();

    public ViewPaneTitle()
    {

    }

    public ViewPaneTitle(String title, ViewPane pane, ViewPaneGroup group)
    {
        setViewPane(pane);
        setPaneGroup(group);
        setText(title);
    }

    public void setViewPane(ViewPane pane)
    {
        viewPaneProperty.set(pane);
    }

    public ViewPane getViewPane() {
        return viewPaneProperty.get();
    }

    public void setPaneGroup(ViewPaneGroup group)
    {
        paneGroupProperty.set(group);
    }

    public ViewPaneGroup getPaneGroup() {
        return paneGroupProperty.get();
    }
}
