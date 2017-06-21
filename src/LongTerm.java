import java.math.BigDecimal;

public class LongTerm implements DepositType {
    double interestRate;

    public LongTerm() {
        interestRate = 0.2;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
