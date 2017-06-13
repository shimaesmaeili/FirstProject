import java.math.BigDecimal;

/**
 * Created by $Hamid on 6/13/2017.
 */
public class ShortTerm extends Deposit {
    double interestRate;

    public ShortTerm(int customerNumber, int durationInDays, BigDecimal depositBalance) {
        super(customerNumber, durationInDays, depositBalance);
        this.interestRate = 0.1;
    }

    public double getInterestRate() {
        return interestRate;
    }

    //    public double computeInterest(){
//        return interestRate*depositBalance*durationInDays/36500;
//    }
}
