package List;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class ListPane extends ListView<ListItem>{

    public class ListPaneCell extends ListCell<ListItem> {

        public ListPaneCell()
        {
            super();


        }
        @Override
        protected void updateItem(ListItem item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getTitle());

                selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (item.getContent() == null)
                        item.setContent(new BorderPane());

                    if (newValue)
                        item.getContent().setVisible(true);
                    else
                        item.getContent().setVisible(false);
                });

            }

        }
    }

    public ListPane() {
        getStylesheets().add(getClass().getResource("list.css").toExternalForm());

        setCellFactory(list -> {
            ListPaneCell item = new ListPaneCell();
            return item;
        });
    }

    public void add(ListItem item)
    {

    }
}
