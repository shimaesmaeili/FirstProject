import java.math.BigDecimal;

public class Qarz implements DepositType {
    double interestRate;

    public Qarz() {
        interestRate = 0.0;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
