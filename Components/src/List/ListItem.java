package List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class ListItem extends Label {
    public StringProperty titleProperty = new SimpleStringProperty();
    public ObjectProperty<BorderPane> contentProperty = new SimpleObjectProperty<>();

    public ListItem() {




    }

    public ListItem(String title)
    {
        this();
        setTitle(title);

    }

    public void setTitle(String title) {
        titleProperty.set(title);
    }

    public String getTitle() {
        return titleProperty.get();
    }


    public ListItem(String title, BorderPane content)
    {
        this(title);
        setContent(content);
        getContent().setVisible(false);

    }

    public void setContent(BorderPane content)
    {
        contentProperty.set(content);
    }

    public BorderPane getContent()
    {
        return contentProperty.get();
    }

    public String toString()
    {
        return getTitle();
    }
}
