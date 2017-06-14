import java.math.BigDecimal;

public class LongTerm extends Deposit {
    double interestRate;

    public LongTerm(int customerNumber, int durationInDays, BigDecimal depositBalance) {
        super(customerNumber, durationInDays, depositBalance);
        this.interestRate = 0.2;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
