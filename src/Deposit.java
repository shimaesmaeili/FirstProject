import java.math.BigDecimal;

/**
 * Created by $Hamid on 6/13/2017.
 */
public class Deposit {
    private int customerNumber;
    private int durationInDays;

    private BigDecimal depositBalance;
    private BigDecimal interest;

    public Deposit(int customerNumber, int durationInDays, BigDecimal depositBalance) {
        this.customerNumber = customerNumber;
        this.durationInDays = durationInDays;
        this.depositBalance = depositBalance;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }
}
