import java.math.BigDecimal;

/**
 * Created by $Hamid on 6/13/2017.
 */
public class Qarz extends Deposit {
    double interestRate;

    public Qarz(int customerNumber, int durationInDays, BigDecimal depositBalance) {
        super(customerNumber, durationInDays, depositBalance);
        interestRate = 0.0;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
