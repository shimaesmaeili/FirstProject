import java.math.BigDecimal;

public class ShortTerm implements DepositType {
    double interestRate;

    public ShortTerm() {
        interestRate = 0.1;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
