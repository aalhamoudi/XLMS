package Modules.Pharmacokinetics;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Dose implements Serializable{


    public Drug getDrug() {
        return drug.get();
    }

    public SimpleObjectProperty<Drug> drugProperty() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug.set(drug);
    }

    public double getDose() {
        return dose.get();
    }

    public SimpleDoubleProperty doseProperty() {
        return dose;
    }

    public void setDose(double dose) {
        this.dose.set(dose);
    }

    public LocalTime getTime() {
        return time.get();
    }

    public SimpleObjectProperty<LocalTime> timeProperty() {
        return time;
    }

    public SimpleStringProperty doseNumber;

    public void setTime(String time) {
        this.time.set(LocalTime.parse(time, timeFormatter));
    }
    public void setTime(LocalTime time) {
        this.time.set(time);
    }



    public LocalDate getDate() {
        return date.get();
    }


    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(LocalDate.parse(date, dateFormatter));
    }
    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public SimpleObjectProperty<LocalDateTime> dateTimeProperty() {
        return dateTime;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime.get();
    }


    public void setDateTime(LocalDateTime dateTime)
    {
        this.dateTime.set(dateTime);
    }

    SimpleObjectProperty<Drug> drug;
    SimpleDoubleProperty dose;
    SimpleObjectProperty<LocalDate> date;
    SimpleObjectProperty<LocalTime> time;
    SimpleObjectProperty<LocalDateTime> dateTime;

    DateTimeFormatter dateFormatter = DateTimeFormat.shortDate();
    DateTimeFormatter timeFormatter = DateTimeFormat.shortTime();

    public Dose(Drug drug, double dose, LocalDateTime dateTime) {

        this.drug = new SimpleObjectProperty<Drug>(drug);
        this.dose = new SimpleDoubleProperty(dose);
        this.dateTime = new SimpleObjectProperty<>(dateTime);
        this.date = new SimpleObjectProperty<>(dateTime.toLocalDate());
        this.time = new SimpleObjectProperty<>(dateTime.toLocalTime());


    }


    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        drug = new SimpleObjectProperty<Drug>((Drug) in.readObject());
        dose = new SimpleDoubleProperty(in.readDouble());
        dateTime = new SimpleObjectProperty<>((LocalDateTime) in.readObject());
        time = new SimpleObjectProperty<>((LocalTime) in.readObject());
        date = new SimpleObjectProperty<>((LocalDate) in.readObject());

    }



    private void writeObject(ObjectOutputStream out) throws IOException{
        out.writeObject(drug.get());
        out.writeDouble(dose.get());
        out.writeObject(dateTime.get());
        out.writeObject(time.get());
        out.writeObject(date.get());

    }


}
