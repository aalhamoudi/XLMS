package Toolbar;

import Glyphs.Glyph;
import Icons.Icon;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class ToolbarContent extends HBox {

    ObjectProperty<ToolbarTab> tab = new SimpleObjectProperty<>();

    public ToolbarContent(ToolbarTab tab)
    {
        setTab(tab);

        Icon collapseButton = new Icon(Glyph.FontAwesome.ARROW_UP, 20);
        collapseButton.setOnMouseClicked(e -> {
            getTab().getToolbar().collapse();
        });

        setAlignment(Pos.BOTTOM_RIGHT);
        setMargin(collapseButton, new Insets(0, 10, 10, 0));
        getChildren().add(collapseButton);
    }

    private void setTab(ToolbarTab tab) {
        this.tab.set(tab);
    }

    private ToolbarTab getTab() {
        return tab.get();
    }

}
