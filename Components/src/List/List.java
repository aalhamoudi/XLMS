package List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class List extends HBox {
    ListPane sections = new ListPane();
    StackPane content = new StackPane();
    ObservableList<ListItem> itemsList = FXCollections.observableArrayList();

    {
        getChildren().addAll(sections, content);
        sections.setPrefWidth(150);
        sections.setMinWidth(ListPane.USE_PREF_SIZE);
        sections.setMaxWidth(ListPane.USE_PREF_SIZE);
    }

    public List()
    {
        addAll(new ListItem("Test 1", new BorderPane(new Button("Test 1"))), new ListItem("Test 2", new BorderPane(new Button("Test 2"))));
        sections.setItems(itemsList);
    }

    public void add(ListItem item)
    {
        itemsList.add(item);


        if (item.getContent() != null)
            content.getChildren().add(item.getContent());
    }

    public void addAll(ListItem... items)
    {
        itemsList.addAll(items);

        for (ListItem item : items)
        {
            if (item.getContent() != null)
                content.getChildren().add(item.getContent());
        }
    }
}
