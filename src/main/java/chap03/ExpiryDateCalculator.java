package chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
        int dividedPayAmount = payAmount / 10000;

        int yearLater = 0;
        int monthLater = dividedPayAmount;
        if (dividedPayAmount >= 10) {
            yearLater = dividedPayAmount / 10;
            monthLater = dividedPayAmount % 10;
        }

        return billingDate.plusYears(yearLater).plusMonths(monthLater);
    }
}
