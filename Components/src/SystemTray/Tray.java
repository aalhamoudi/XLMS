package SystemTray;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class Tray {

    public interface ExitFunction {
        public void run();
    }

    Stage stage;
    SystemTray tray;
    SystemIcon trayIcon;
    ExitFunction fun = null;

    public Tray(Stage stage, ExitFunction fun) {        this.fun = fun;
        this.stage = stage;
        stage.initStyle(StageStyle.UTILITY);
        stage.setOnCloseRequest(e -> stage.hide());
        stage.setAlwaysOnTop(true);

        Platform.setImplicitExit(false);



        try {
            java.awt.Toolkit.getDefaultToolkit();

            if (!java.awt.SystemTray.isSupported())
                Platform.exit();

            tray = java.awt.SystemTray.getSystemTray();
            trayIcon = new SystemIcon(stage, tray, "H:\\OneDrive\\Images\\Icons\\Tray\\Dialog.png");
            tray.add(trayIcon);

        }

        catch(AWTException e)
        {
            System.out.println("Unable to initialize system tray.");
        }
    }

    public void exit()
    {
        fun.run();
        tray.remove(trayIcon);
        Platform.exit();
    }




}
