package soap.calculator;

import calculator.wsdl2java.Calculator;
import calculator.wsdl2java.CalculatorSoap;
import common.BaseTest;
import common.ReplaceCamelCase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
@DisplayNameGeneration(ReplaceCamelCase.class)
public class CalculatorPositiveTests extends BaseTest {

    private CalculatorSoap calculator = new Calculator().getCalculatorSoap();
    private Logger log = LogManager.getLogger(CalculatorPositiveTests.class);


    @ParameterizedTest(name = "add positive test #{0}")
    @CsvSource({
            "1, -2147483648, 0",
            "2, 2147483647, 0",
            "3, 0, 0"
    })
    @Step("add positive test started")
    @Description(value = "test checks the function to add through SOAP API")
    public void addPositiveTest(int id, int A, int B) {
        int expectedResult = A + B;
        int actualResult = calculator.add(A,B);
        assertResult(expectedResult, actualResult);
    }


    @ParameterizedTest(name = "subtract positive test #{0}")
    @CsvSource({
            "1, 1, 1",
            "2, 2, 2"
    })
    @Step("subtract positive test started")
    @Description(value = "test checks the function to subtract through SOAP API")
    public void subtractPositiveTest(int id, int A, int B) {
        int expectedResult = A - B;
        int actualResult = calculator.subtract(A,B);
        assertResult(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "multiply positive test #{0}")
    @CsvSource({
            "1, 1, 1",
            "2, 2, 2"
    })
    @Step("multiply positive test started")
    @Description(value = "test checks the function to multiply through SOAP API")
    public void multiplyPositiveTest(int id, int A, int B) {
        int expectedResult = A * B;
        int actualResult = calculator.multiply(A,B);
        assertResult(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "divide positive test #{0}")
    @CsvSource({
            "1, 1, 1",
            "2, 2, 2"
    })
    @Step("divide positive test started")
    @Description(value = "test checks the function to divide through SOAP API")
    public void dividePositiveTest(int id, int A, int B) {
        int expectedResult = A / B;
        int actualResult = calculator.divide(A,B);
        assertEquals(expectedResult, actualResult, "didn't get expected result");
    }

//    @Test
//    @DisplayName("divide positive test")
//    public void dividePositiveTest() {
//        int result = calculator.divide(6,2);
//        assertResult(1, result);
//    }

    @Step("checked result of calculation")
    private void assertResult(int expectedResult, int actualResult) {
        assertEquals(expectedResult, actualResult, "didn't get expected result");
        log.info("checked result of calculation");
    }
}
