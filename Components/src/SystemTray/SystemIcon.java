package SystemTray;

import javafx.application.Platform;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Timer;

public class SystemIcon extends TrayIcon{


    PopupMenu menu = new PopupMenu();
    MenuItem open = new MenuItem("Open");
    MenuItem exit = new MenuItem("Exit");

    Font font = Font.decode(null);
    Font boldFont = font.deriveFont(Font.BOLD);

    Timer timer = new Timer();

    Stage stage;
    SystemTray tray;

    public SystemIcon(Stage stage, SystemTray tray, String iconPath)
    {
        super(IconReader.readIcon(iconPath));

        this.stage = stage;
        this.tray = tray;

        open.addActionListener(e -> Platform.runLater(this::showApplication));
        open.setFont(boldFont);

        addActionListener(e -> Platform.runLater(this::showApplication));


        exit.addActionListener(e -> {
            timer.cancel();
            tray.remove(this);
            Platform.exit();
        });

        menu.add(open);
        menu.addSeparator();
        menu.add(exit);

        setPopupMenu(menu);


    }

    private void showApplication() {
        if (stage != null)
        {
            stage.show();
            stage.toFront();
        }
    }


}
