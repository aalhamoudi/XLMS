package Modules.Pharmacokinetics;

import Icons.MaterialIcon;
import Navigation.NavContent;
import Serializer.Serializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import jfxtras.scene.control.LocalDatePicker;
import jfxtras.scene.control.LocalTimePicker;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;


public class Pharmacokinetics extends SplitPane implements NavContent {

    public static Pharmacokinetics factory()
    {
        return new Pharmacokinetics();
    }


    public class DrugConverter extends StringConverter<Drug>
    {
        @Override
        public String toString(Drug drug) {
            return drug.getDrug();
        }

        @Override
        public Drug fromString(String drugName) {
            return new Drug(drugName);
        }
    }

    public class HalfLifeConverter extends StringConverter<HalfLife>
    {
        @Override
        public String toString(HalfLife halfLife) {
            return Double.toString(halfLife.getHalfLife());
        }

        @Override
        public HalfLife fromString(String halfLife) {
            return new HalfLife(Double.valueOf(halfLife));
        }
    }


    public class LocalDateConverter extends StringConverter<LocalDate>
    {
        @Override
        public String toString(LocalDate date) {
            return date.toString(DateTimeFormat.shortDate());
        }

        @Override
        public LocalDate fromString(String date)
        {
            return LocalDate.parse(date, DateTimeFormat.shortDate());
        }

    }


    public class LocalTimeConverter extends StringConverter<LocalTime>
    {
        @Override
        public String toString(LocalTime date) {
            return date.toString(DateTimeFormat.shortTime());
        }

        @Override
        public LocalTime fromString(String date)
        {
            return LocalTime.parse(date, DateTimeFormat.shortTime());
        }

    }

    public static ObservableList<Drug> drugCollection;
    ObservableList<Dose> doseCollection = null;
    Drug selectedDrug = null;
    int drugCount = 0;
    LocalDateTime day = null;

    @FXML MaterialIcon addDrug;
    @FXML MaterialIcon editDrug;
    @FXML MaterialIcon removeDrug;

    @FXML MaterialIcon addDose;
    @FXML MaterialIcon editDose;
    @FXML MaterialIcon removeDose;


    @FXML TableView<Drug> drugs;
    @FXML TableColumn<Drug, String> drugCol;
    @FXML TableColumn<Drug, HalfLife> halfLifeCol;
    @FXML TableColumn<Drug, Double> dailyDoseCol;

    @FXML TableView<Dose> doses;
    @FXML TableColumn<Dose, Double> doseCol;
    @FXML TableColumn<Dose, LocalTime> timeCol;
    @FXML TableColumn<Dose, LocalDate> dateCol;

    @FXML TextField lowerRange;
    @FXML TextField upperRange;


    @FXML LineChart<String, Number> concentrations;
    @FXML CategoryAxis timeAxis;
    @FXML NumberAxis concentrationAxis;

    @FXML ComboBox<Drug> drugSelection;
    @FXML LocalTimePicker timeField;
    @FXML TextField concentrationField;

    @FXML LocalDatePicker date;

    Drug selection = null;
    double time = 0;

    int lowerRangeValue = 0;
    int upperRangeValue = 24;

    public Pharmacokinetics() {
        fxml();

        day = new DateTime().withTimeAtStartOfDay().toLocalDateTime();


        setFactories();
        setData();
        setSelection();
        setListeners();
        setChart();
        setEditing();


    }

    private void setChart()
    {

        if (drugCollection != null) {
            updateChart();

           /* drugCollection.addListener((ListChangeListener.Change<? extends Drug> list) -> {
                updateChart();
            });*/
        }
    }

    private void updateChart() {
        concentrations.getData().clear();

        for (Drug drug : drugCollection) {
            XYChart.Series series = new XYChart.Series();
            series.setName(drug.getDrug());
            for (int i = 0; i < 24; i++)
            {
                double total = 0;
                for (Dose dose : drug.getDoses()) {


                    LocalDateTime diff = day.plusHours(i);


                    total += Concentration.calculateConcentration(drug, dose, diff);

                }
                series.getData().add(new XYChart.Data<String, Number>(Integer.toString(i), total));

            }


            concentrations.getData().add(series);
        }
    }


    private void setSelection() {
        drugs.getSelectionModel().setCellSelectionEnabled(true);

            drugCol.setOnEditCommit((TableColumn.CellEditEvent<Drug, String> e) -> {
                ((Drug) e.getTableView().getItems().get(e.getTablePosition().getRow())).setDrug((String) e.getNewValue());

                TableColumn column = ((TableColumn.CellEditEvent) e).getTablePosition().getTableColumn();
                int row = ((TableColumn.CellEditEvent) e).getTablePosition().getRow();
                int col = ((TableColumn.CellEditEvent) e).getTableView().getColumns().indexOf(column);
                TableColumn nextColumn = null;
                if (col < ((TableColumn.CellEditEvent) e).getTableView().getColumns().size() - 1)
                    nextColumn = (TableColumn) ((TableColumn.CellEditEvent) e).getTableView().getColumns().get(col + 1);

                ((TableColumn.CellEditEvent) e).getTableView().requestFocus();
                ((TableColumn.CellEditEvent) e).getTableView().getFocusModel().focusRightCell();
                ((TableColumn.CellEditEvent) e).getTableView().getSelectionModel().select(row, nextColumn);
                ((TableColumn.CellEditEvent) e).getTableView().edit(row, nextColumn);

            });


        doses.getSelectionModel().setCellSelectionEnabled(true);
        for (TableColumn tc : doses.getColumns())
        {
            tc.setOnEditCommit(e -> {
                TableColumn column = ((TableColumn.CellEditEvent) e).getTablePosition().getTableColumn();
                int row = ((TableColumn.CellEditEvent) e).getTablePosition().getRow();
                int col = ((TableColumn.CellEditEvent) e).getTableView().getColumns().indexOf(column);
                TableColumn nextColumn = null;
                if (col < ((TableColumn.CellEditEvent) e).getTableView().getColumns().size() - 1)
                    nextColumn = (TableColumn) ((TableColumn.CellEditEvent) e).getTableView().getColumns().get(col + 1);

                ((TableColumn.CellEditEvent) e).getTableView().requestFocus();
                ((TableColumn.CellEditEvent) e).getTableView().getFocusModel().focusRightCell();
                ((TableColumn.CellEditEvent) e).getTableView().getSelectionModel().select(row, nextColumn);
                ((TableColumn.CellEditEvent) e).getTableView().edit(row, nextColumn);

            });
        }
    }



    private void setData() {


        try {
            LinkedList<Drug>drugList = (LinkedList<Drug>) Serializer.<LinkedList<Drug>>Deserialize("V:\\OneDrive\\Settings\\dr.ser");
            drugCollection = drugList != null? FXCollections.observableArrayList(drugList) : FXCollections.observableArrayList();
        }
        catch (Exception e) {
            System.out.println("Error reading data.\n" + e.getMessage());
        }

        drugs.setItems(drugCollection);
        drugSelection.setItems(drugCollection);
    }

    public static void saveData()
    {
        ListIterator<Drug> drugIterator = drugCollection.listIterator();
        LinkedList<Drug> drugList = new LinkedList<>();
        while (drugIterator.hasNext())
            drugList.add(drugIterator.next());

        try {
            Serializer.<LinkedList<Drug>>Serialize(drugList, "V:\\OneDrive\\Settings\\dr.ser");
        } catch (Exception ex) {
            System.out.println("Error writing data.\n" + ex.getMessage());
        }
    }

    private void setFactories() {

        drugCol.setCellValueFactory(new PropertyValueFactory<Drug, String>("drug"));
        halfLifeCol.setCellValueFactory(new PropertyValueFactory<Drug, HalfLife>("halfLife"));
        dailyDoseCol.setCellValueFactory(new PropertyValueFactory<Drug, Double>("dailyDose"));

        drugCol.setCellFactory(TextFieldTableCell.forTableColumn());
        halfLifeCol.setCellFactory(TextFieldTableCell.forTableColumn(new HalfLifeConverter()));
        dailyDoseCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));



        doseCol.setCellValueFactory(new PropertyValueFactory<Dose, Double>("dose"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Dose, LocalTime>("time"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Dose, LocalDate>("date"));

        doseCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        timeCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeConverter()));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateConverter()));



    }

    private void setListeners() {
        drugs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedDrug = (Drug) newValue;
            doseCollection = selectedDrug != null ? selectedDrug.getDoses() : null;
            doses.setItems(doseCollection);
        });


        date.localDateProperty().addListener((observable, oldVal, newVal) -> {
            day = new DateTime(newVal.getYear(), newVal.getMonthValue(), newVal.getDayOfMonth(), new DateTime().withTimeAtStartOfDay().getHourOfDay(),new DateTime().withTimeAtStartOfDay().getMinuteOfHour(), new DateTime().withTimeAtStartOfDay().getSecondOfMinute() ).toLocalDateTime();



            updateChart();
        });

        timeField.localTimeProperty().addListener((observable, oldVal, newVal) -> {

            if (selectedDrug != null) {
                double total = 0;
                for (Dose dose : selectedDrug.getDoses()) {
                    total += Concentration.calculateConcentration(selectedDrug, dose, new DateTime().withTime(new LocalTime(newVal.toString())).toLocalDateTime());
                }

                concentrationField.setText(Double.toString(total));

            }
        });

        drugSelection.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
            selectedDrug = newVal;

            double total = 0;
            for (Dose dose : selectedDrug.getDoses())
            {
                total += Concentration.calculateConcentration(selectedDrug, dose, day );
            }

            concentrationField.setText(Double.toString(total));
        });


        drugs.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if ((int)newValue != -1) {
                selectedDrug = drugs.getItems().get((int) newValue);
                doseCollection = selectedDrug != null ? selectedDrug.getDoses() : null;
                doses.setItems(doseCollection);
            }
        });


        drugSelection.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selection = newValue;
            //concentrationField.setText(Concentration.calculateConcentrations(selection, time));
        });

        addDrug.setOnMouseClicked(e -> {

            Drug drug = new Drug("Drug " + ++drugCount, new HalfLife(10), 50);
            drugCollection.add(drug);

            int row = drugCollection.size() - 1;
            TableColumn column = drugs.getColumns().get(0);

            drugs.requestFocus();
            drugs.getSelectionModel().select(row, column);
            drugs.getFocusModel().focus(row, column);
            drugs.edit(row, column);

            updateChart();


        });



        editDrug.setOnMouseClicked(e -> {
            drugs.getSelectionModel().getTableView().edit(drugs.getSelectionModel().getSelectedIndex(), drugCol);

            updateChart();

        });




        removeDrug.setOnMouseClicked(e -> {
            drugCollection.remove(drugs.getSelectionModel().getSelectedItem());

            updateChart();

        });





        addDose.setOnMouseClicked(e -> {
            if (selectedDrug != null) {
                Dose newDose = new Dose(selectedDrug, 100, LocalDateTime.now());
                doseCollection.add(newDose);


                int row = doseCollection.size() - 1;
                TableColumn column = doses.getColumns().get(0);

                doses.requestFocus();
                doses.getSelectionModel().select(row, column);
                doses.getFocusModel().focus(row, column);
                doses.edit(row, column);
                updateChart();

            }
        });



        editDose.setOnMouseClicked(e -> {
            doses.getSelectionModel().getTableView().edit(doses.getSelectionModel().getSelectedIndex(), doseCol);
            updateChart();


        });


        removeDose.setOnMouseClicked(e -> {
            doseCollection.remove(doses.getSelectionModel().getSelectedItem());
            updateChart();


        });


        for (TableColumn tc : drugs.getColumns()) {
            tc.setOnEditStart(l -> {
                if (l.getSource() instanceof TextField)
                    ((TextField) l.getSource()).selectAll();
            });

            tc.setOnEditCommit(e -> {

            });
        }

        for (TableColumn tc : doses.getColumns())
            tc.setOnEditStart(l -> {
                if (l.getSource() instanceof TextField)
                    ((TextField) l.getSource()).selectAll();
            });




    }

    private void setEditing() {
        drugCol.setOnEditCommit((TableColumn.CellEditEvent<Drug, String> e) -> {
            ((Drug) e.getTableView().getItems().get(e.getTablePosition().getRow())).setDrug(e.getNewValue());
            updateChart();
            drugCount--;
        });

        halfLifeCol.setOnEditCommit((TableColumn.CellEditEvent<Drug, HalfLife> e) -> {
            ((Drug) e.getTableView().getItems().get(e.getTablePosition().getRow())).setHalfLife(e.getNewValue());
            updateChart();

        });

        dailyDoseCol.setOnEditCommit((TableColumn.CellEditEvent<Drug, Double> e) -> {
            ((Drug) e.getTableView().getItems().get(e.getTablePosition().getRow())).setDailyDose(e.getNewValue());
            updateChart();

        });


        doseCol.setOnEditCommit((TableColumn.CellEditEvent<Dose, Double> e) -> {
            ((Dose) e.getTableView().getItems().get(e.getTablePosition().getRow())).setDose(e.getNewValue());
            updateChart();

        });

        timeCol.setOnEditCommit((TableColumn.CellEditEvent<Dose, LocalTime> e) -> {
            ((Dose) e.getTableView().getItems().get(e.getTablePosition().getRow())).setTime(e.getNewValue());
            updateChart();

        });

        dateCol.setOnEditCommit((TableColumn.CellEditEvent<Dose, LocalDate> e) -> {
            ((Dose) e.getTableView().getItems().get(e.getTablePosition().getRow())).setDate(e.getNewValue());
            updateChart();

        });
    }

    private void fxml() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pharmacokinetics.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            getStylesheets().addAll(getClass().getResource("Pharmacokinetics.css").toExternalForm());
        } catch (IOException e) {
           System.out.println("File can't be found!");
        }
    }
}
