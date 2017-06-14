import java.math.BigDecimal;
import java.util.Comparator;

public class Deposit implements Comparable<Deposit> {
    public int customerNumber;
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

    @Override
    public int compareTo(Deposit o1) {
        return -1*this.getInterest().compareTo(o1.getInterest());
    }
}
