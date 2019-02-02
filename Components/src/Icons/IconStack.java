package Icons;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;

public class IconStack extends StackPane {

    private ObjectProperty<Icon> shownIcon = new SimpleObjectProperty<Icon>();
    private ObservableList<Icon> hiddenIcons = new SimpleListProperty<Icon>(FXCollections.observableArrayList());
    int itemsNumber = 0;

    public IconStack()
    {
        hiddenIcons.addListener((ListChangeListener<? super Icon>) c -> {

                for (int i = itemsNumber; i < hiddenIcons.size(); i++) {
                    getChildren().add(hiddenIcons.get(i));
                }
                c.next();
                itemsNumber += c.getAddedSize();


        });


        shownIcon.addListener(observable -> {


        });

    }

    public void addIcon(Icon icon) {
        hiddenIcons.add(icon);
        icon.setVisible(false);

        if (shownIcon.get() == null) {
            showIcon(icon);
        }


    }

    public void addAllIcons(Icon... icons)
    {
        if (icons != null)
        {
            hiddenIcons.addAll(icons);
            if (shownIcon == null)
                showIcon(hiddenIcons.get(0));
        }
    }

    public boolean showIcon(Icon icon)
    {
        if (hiddenIcons.contains(icon))
        {
            if (shownIcon.get() != null)
                shownIcon.get().setVisible(false);
            shownIcon.set(icon);
            icon.setVisible(true);
            return true;
        }

        else
            return false;
    }

    public boolean showIcon(String id)
    {
        for (Icon i : hiddenIcons)
        {
            if (i.id != null && i.id == id) {
                showIcon(i);
                return true;
            }
        }

        return false;
    }
}
