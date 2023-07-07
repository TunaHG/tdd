package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    @Test
    @DisplayName("모든 조건 충족하면 '강함'")
    void meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("길이 조건만 충족하지 못하면 '중간'")
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자 조건만 충족하지 못하면 '중간'")
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("null이 들어오면 'INVALID'")
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("빈 값이 들어오면 'INVALID'")
    void emptyInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("대문자 조건만 충족되지 않으면 '중간'")
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("길이 조건만 충족되면 '약함'")
    void meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abdefghi", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("숫자 조건만 충족되면 '약함'")
    void meetsOnlyNumCriteria_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("대문자 조건만 충족되면 '약함'")
    void meetsOnlyUpperCriteria_Then_Weak() {
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("모든 조건을 충족하지 못하면 '약함'")
    void meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
}
