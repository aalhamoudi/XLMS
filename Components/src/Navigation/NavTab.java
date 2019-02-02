package Navigation;

import Glyphs.Glyph;
import Icons.Icon;
import javafx.application.Platform;
import javafx.beans.DefaultProperty;
import javafx.beans.NamedArg;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;

import java.io.IOException;

@DefaultProperty("module")
public class NavTab extends Tab {


    public boolean isMenu = false;
    public boolean isSeparator = false;
    public boolean menuOpen = false;

    SimpleObjectProperty<Node> module = new SimpleObjectProperty<>();
    public SimpleObjectProperty<Node> moduleProperty() {
        return module;
    }

    public Node getModule()
    {
        return module.get();
    }

    public void setModule(Node node)
    {
        module.set(node);
    }

    public NavTab()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NavTab.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try
        {
            loader.load();
            contentProperty().bindBidirectional(module);
        }

        catch (IOException e)
        {

        }
    }


    public NavTab(@NamedArg("isSeperator") boolean separator)
    {
        if (separator) {
            setDisable(true);
            isSeparator = true;
        }

    }

    public NavTab(@NamedArg("glyph") Glyph glyph, boolean menu)
    {
        this(glyph);
        isMenu = menu;
    }
    public NavTab(@NamedArg("icon") Icon graphic, boolean menu)
    {
        this(graphic);
        isMenu = menu;
    }

    public NavTab(@NamedArg("icon") Icon graphic)
    {



        Platform.runLater(() -> {
            setOnSelectionChanged(e -> {
                if (!isMenu) {
                    if (this != ((NavPane) getTabPane()).currentTab) {
                        ((NavPane) getTabPane()).currentTab = this;
                    }
                }
            });
        });

        graphic.setOnMouseClicked(e -> {
            if (isMenu) {
                if (menuOpen) {
                    getTabPane().getSelectionModel().select(((NavPane) getTabPane()).currentTab);

                    menuOpen = false;
                } else
                    menuOpen = true;


            }
        });

        setGraphic(graphic);
    }



    public NavTab(@NamedArg("glyph") Glyph glyph)
    {
        this(new Icon(glyph));
    }

    public NavTab(@NamedArg("icon") Icon graphic, @NamedArg("content") SplitPane content)
    {
        this(graphic);
        setContent(content);
    }

    public NavTab(@NamedArg("glyph") Glyph glyph, @NamedArg("content")  SplitPane content)
    {
        this(glyph);
        setContent(content);
    }

    public NavTab(@NamedArg("icon") Icon graphic, @NamedArg("content")  SplitPane content, @NamedArg("isMenu") boolean menu)
    {
        this(graphic);
        setContent(content);
        menu = isMenu;
    }

    public NavTab(@NamedArg("glyph") Glyph glyph, @NamedArg("content")  SplitPane content, @NamedArg("isMenu") boolean menu)
    {
        this(glyph);
        setContent(content);
        isMenu = menu;
    }

    public static NavTab valueOf(String name)
    {
        return new NavTab(Glyph.Material.valueOf(name));
    }

    public static NavTab tabFactory()
    {
        return new NavTab(Glyph.Material.ALARM);
    }
}
