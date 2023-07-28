package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * 목표
 * 매달 비용을 지불해야 사용할 수 있는 유료 서비스
 *
 * 규칙
 * 1. 서비스를 사용하려면 매달 1만원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
 * 2. 2개월 이상 요금을 납부할 수 있다.
 * 3. 10만원을 납부하면 서비스를 1년 제공한다.
 *
 * 납부한 금액 기준으로 서비스 만료일을 계산하는 기능 구현
 */
public class ExpiryDateCalculatorTest {
    private final ExpiryDateCalculator calculator = new ExpiryDateCalculator();

    void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        LocalDate expiryDate = calculator.calculateExpiryDate(billingDate, payAmount);
        assertEquals(expiryDate, expectedExpiryDate);
    }

    @Test
    @DisplayName("납부한 금액이 0원일 경우 서비스 만료일이 납부일과 동일")
    void payment_0_then_expiry_date_be_now() {
        LocalDate billingDate = LocalDate.now();

        assertExpiryDate(billingDate, 0, billingDate);
    }

    @Test
    @DisplayName("납부한 금액이 1만원일 경우 서비스 만료일이 납부일 한 달 뒤")
    void payment_10000_then_expiry_date_be_one_month_later() {
        LocalDate billingDate = LocalDate.now();

        assertExpiryDate(billingDate, 10000, billingDate.plusMonths(1));
    }

    @Test
    @DisplayName("납부한 금액이 2만원일 경우 서비스 만료일이 납부일 두 달 뒤")
    void payment_20000_then_expiry_date_be_two_month_later() {
        LocalDate billingDate = LocalDate.now();

        assertExpiryDate(billingDate, 20000, billingDate.plusMonths(2));
    }

    @Test
    @DisplayName("납부한 금액이 10만원일 경우 서비스 만료일이 납부일 일 년 뒤")
    void payment_100000_then_expiry_date_be_one_year_later() {
        LocalDate billingDate = LocalDate.now();

        assertExpiryDate(billingDate, 100000, billingDate.plusYears(1));
    }
}
