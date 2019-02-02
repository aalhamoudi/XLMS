package Modules.Pharmacokinetics;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

public class Drug implements Serializable{

    SimpleStringProperty drug;
    SimpleDoubleProperty dailyDose;
    SimpleObjectProperty<HalfLife> halfLife;
    ObservableList<Dose> doses = FXCollections.observableArrayList();

    public Drug(String drugName) {
        this.drug = new SimpleStringProperty(drugName);
    }

    public Drug(String drugName, HalfLife halfLife)
    {
        this.drug = new SimpleStringProperty(drugName);
        this.halfLife = new SimpleObjectProperty<>(halfLife);


    }

    public ObservableList<Dose> getDoses() {
        return doses;
    }

    public void addDose(Dose dose) {
        doses.add(dose);
    }

    public Drug(String drugName, HalfLife halfLife, double dailyDose)
    {
        this(drugName, halfLife);
        this.dailyDose = new SimpleDoubleProperty(dailyDose);
    }

    public void setDrug(String drugName)
    {
        drug.set(drugName);
    }

    public String getDrug()
    {
        return drug.get();
    }
    public SimpleStringProperty drugProperty() {return  drug;}

    public HalfLife getHalfLife() {
        return halfLife.get();
    }

    public SimpleObjectProperty<HalfLife> halfLifeProperty() {
        return halfLife;
    }

    public void setHalfLife(HalfLife halfLife) {
        this.halfLife.set(halfLife);
    }

    public void setDailyDose(double dailyDose)
    {
        this.dailyDose.set(dailyDose);
    }

    public SimpleDoubleProperty dailyDoseProperty() {return dailyDose;}

    public double getDailyDose()
    {
        return dailyDose.get();
    }

    public String toString()
    {
        return drug.get();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        drug = new SimpleStringProperty(((String)in.readObject()));
        halfLife = new SimpleObjectProperty<>((HalfLife)in.readObject());
        dailyDose = new SimpleDoubleProperty(in.readDouble());
        LinkedList<Dose> doseList = (LinkedList<Dose>) in.readObject();
        doses = FXCollections.observableArrayList(doseList);



    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.writeObject(getDrug());
        out.writeObject(getHalfLife());
        out.writeDouble(getDailyDose());

        LinkedList<Dose> doseList = new LinkedList<>();
        ListIterator<Dose> doseIterator = getDoses().listIterator();
        while(doseIterator.hasNext())
            doseList.add(doseIterator.next());

        out.writeObject(doseList);

    }

}
