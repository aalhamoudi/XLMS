package Navigation;

import javafx.application.Platform;
import javafx.beans.DefaultProperty;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.TabPane;

@DefaultProperty("tabs")
public class NavPane extends TabPane {

    public NavTab startup = null;
    public NavTab currentTab = null;

    public NavPane()
    {

        setTabMinHeight(50);
        setTabMinWidth(50);
        setTabMaxHeight(50);
        setTabMaxWidth(50);

        setSide(Side.LEFT);
        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        getStylesheets().add(getClass().getResource("nav.css").toExternalForm());

/*
        NavTab menu = new NavTab<List>(Glyph.FontAwesome.ALIGN_JUSTIFY, new List(), true);
        NavTab separator = new NavTab(true);

        Viewer viewer = new Viewer();
        Toolbar top = new Toolbar();
        ToolbarTab homeTab = new ToolbarTab("Home");
        ToolbarTab editTab = new ToolbarTab("Edit");
        top.addAll(homeTab, editTab);
        Button collapse = new Button("Collapse");
        homeTab.setContent(new ToolbarContent(homeTab));
        collapse.setOnAction(event -> {top.collapse();});

        ViewPaneGroup bottom = new ViewPaneGroup();
        ViewPaneGroup left = new ViewPaneGroup();
        ViewPaneGroup center = new ViewPaneGroup();
        ViewPaneGroup right = new ViewPaneGroup();

        Icon ic = new Icon(Glyph.Material.REMOVE_CIRCLE);
        Icon ic2 = new Icon(Glyph.FontAwesome.ANCHOR);
        TextField text = new TextField();
        TextField g = new TextField();
        Button st = new Button("Set");
        st.setOnAction(e -> {
            ic.setGlyphFont(text.getText());
            //ic.setGlyph(g.getText());
        });
        VBox co = new VBox();
        co.setPadding(new Insets(20));
        co.setAlignment(Pos.CENTER);
        co.getChildren().addAll(ic, ic2, text,g, st);
        center.getTabs().addAll(new ViewPane("Test 1", co), new ViewPane("Test 2"));
        bottom.getTabs().addAll(new ViewPane("Test 3"), new ViewPane("Test 4"));
        right.getTabs().addAll(new ViewPane("Test 5"), new ViewPane("Test 6"));
        left.getTabs().addAll(new ViewPane("Test 7"), new ViewPane("Test 8"));

        right.setPrefWidth(200);
        left.setPrefWidth(200);
        bottom.setPrefHeight(200);

        viewer.setTop(top);
        viewer.setBottom(bottom);
        viewer.setLeft(left);
        viewer.setCenter(center);
        viewer.setRight(right);
        NavTab home = new NavTab(Glyph.FontAwesome.HOME, viewer);
        startup = currentTab = home;

        getTabs().addAll(menu, separator, home);
        getSelectionModel().select(startup);*/






        Platform.runLater(() -> {
            ScenePane parent = (ScenePane) getParent();
            if (parent != null){
                parent.setMargin(this, new Insets(-10, 0, -10, 0));
                setPadding(new Insets(10, 0, 10, 0));
            }
        });
    }

    public NavPane(NavTab... tabs)
    {
        this();
        getTabs().addAll(tabs);
    }

    public void add(NavTab tab) {
            getTabs().add(tab);

    }

    public void addAll(NavTab... tabs) {
        getTabs().addAll(tabs);

    }

    public void select(NavTab tab)
    {
            getSelectionModel().select(tab);

    }

}
