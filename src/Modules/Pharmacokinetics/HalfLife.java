package Modules.Pharmacokinetics;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HalfLife implements Serializable{
    public enum Administration {
        IRRELAVENT,
        ACUTE,
        CHRONIC
    }

    public enum PatientAge {
        ADULT,
        PEDIATRIC,
        GERIATRIC
    }

    double halfLife;
    Administration administration;
    PatientAge age;

    public HalfLife(double halfLife) {
        this.halfLife = halfLife;
        administration = Administration.IRRELAVENT;
        age = PatientAge.ADULT;
    }

    public HalfLife(double... halfLife) {
        this(getAverage(halfLife));
    }

    public HalfLife(Administration administration, PatientAge age, double halfLife)
    {
        this.halfLife = halfLife;
        this.administration = administration;
        this.age = age;
    }


    public HalfLife(Administration administration, PatientAge age, double... halfLife)
    {
        this(administration, age, getAverage(halfLife));
    }

    private static double getAverage(double[] halfLife) {
        double sum = 0;
        for (double hL : halfLife)
            sum += hL;
        return sum/halfLife.length;
    }

    public double getHalfLife() {
        return halfLife;
    }
    public void setHalfLife(double halfLife) {this.halfLife = halfLife;}



    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();


    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();

    }
}