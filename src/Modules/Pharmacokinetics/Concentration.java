package Modules.Pharmacokinetics;

import org.joda.time.Interval;
import org.joda.time.LocalDateTime;



public class Concentration {


    private static double calculateFactor(Drug drug) {
        return -(Math.log(0.5) / drug.getHalfLife().getHalfLife());

    }


    public static double calculateConcentration(Drug drug, Dose dose, LocalDateTime dateTime) {
        Interval interval = null;
        if (!dose.getDateTime().isAfter(dateTime)) {
            interval = new Interval(dose.getDateTime().toDateTime(), dateTime.toDateTime());
            double duration = interval.toDuration().getStandardHours();
            double c =  (dose.getDose() * Math.exp(-1 * calculateFactor(drug) * duration));
            return c;
        }
        else
            return 0;
    }

}

/*    public static double calculateConcentrations(Drug drug, double time) {
        Calendar cal = Calendar.getInstance();

        double total = 0;
        for (Dose dose : drug.getDoses())
        {
            total += dose.getDose()*Math.exp(-1 * calculateFactor(drug) * ((time - dose.getTimeDate().toLocalTime().toSecondOfDay())/3600000));;
        }
        return total;
    }

}*/
