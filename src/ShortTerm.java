import java.math.BigDecimal;

public class ShortTerm extends Deposit {
    double interestRate;

    public ShortTerm(int customerNumber, int durationInDays, BigDecimal depositBalance) {
        super(customerNumber, durationInDays, depositBalance);
        this.interestRate = 0.1;
    }

    public double getInterestRate() {
        return interestRate;
    }

}
