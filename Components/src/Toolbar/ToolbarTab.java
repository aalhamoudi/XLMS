package Toolbar;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

public class ToolbarTab extends Tab {
    ToolbarContent content;
    Label title = new Label();

    public ToolbarTab(){
        setGraphic(title);

        title.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
                if (getToolbar().isCollapsed())
                    getToolbar().expand();
        });

        Platform.runLater(() -> {

            setOnSelectionChanged(e -> {
                if (getToolbar().isCollapsed())
                    getToolbar().expand();
            });
        });

    }

    public  ToolbarTab(ToolbarContent content)
    {
        this.content = content;
        setContent(content);
    }

    public ToolbarTab(String header)
    {
        this();
        setTitle(header);
    }

    public Toolbar getToolbar()
    {
        return (Toolbar) getTabPane();
    }

    public void collapse()
    {
        setContent(null);
    }

    public void expand()
    {
        setContent(content);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}
