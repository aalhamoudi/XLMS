package Navigation;

import javafx.beans.DefaultProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

@DefaultProperty("content")
public class ScenePane extends BorderPane {

   SimpleObjectProperty<Node> content = new SimpleObjectProperty<>();

    public SimpleObjectProperty<Node> contentProperty() {
        return content;
    }

    public Node getContent()
    {
        return content.get();
    }

    public void setContent(Node node)
    {
        content.set(node);
    }

    public ScenePane()
    {
        centerProperty().bindBidirectional(content);
    }
}
