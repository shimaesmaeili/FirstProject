import java.math.BigDecimal;

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
