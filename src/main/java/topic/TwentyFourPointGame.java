package topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @Author japing
 * @Date 2017/5/11 20:17
 * @Description:
 */
public class TwentyFourPointGame {

    private static Logger LOGGER = LoggerFactory.getLogger(TwentyFourPointGame.class);
    private OperateNumber[] inputNumbers;
    private static final int POINT_24 = 24;

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVISION = "/";
    private static Map<String, Object> resultMap = new HashMap<>();

    public TwentyFourPointGame(int[] inputNumber) {
        init(inputNumber);
    }

    private void init(int[] inputNumber) {
        if (null != inputNumber) {
            inputNumbers = new OperateNumber[inputNumber.length];
            for ( int i = 0; i < inputNumber.length; i++) {
                OperateNumber num = new OperateNumber(inputNumber[i], 0);
                inputNumbers[i] = num;
            }
        }
    }

    public void canlculate24Point(int n) {
        if (n == 1) {
            if (POINT_24 == inputNumbers[0].getAint()) {
                if (resultMap.containsKey(inputNumbers[0].getExpression())) {
                    LOGGER.info("***[formula= {}, value= {}]，重复！！！***", inputNumbers[0].getExpression(), inputNumbers[0].getAint());
                }
                resultMap.put(inputNumbers[0].getExpression(), inputNumbers[0].getAint());
                LOGGER.info("***[formula= {}, value= {}]***", inputNumbers[0].getExpression(), inputNumbers[0].getAint());
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                OperateNumber numberA = inputNumbers[i];
                OperateNumber numberB = inputNumbers[j];
                FormulaAndValue formulaAndValue = null;
                //计算 a + b
                inputNumbers[j] = inputNumbers[n-1];
                formulaAndValue= computation("+", numberA, numberB);
                inputNumbers[i] = formulaAndValue.getValue();
                canlculate24Point(n-1);
                //还原
                inputNumbers[i] = numberA;
                inputNumbers[j] = numberB;
                //计算 a -b
                inputNumbers[j] = inputNumbers[n-1];
                formulaAndValue= computation("-", numberA, numberB);
                inputNumbers[i] = formulaAndValue.getValue();
                canlculate24Point(n-1);
                //还原
                inputNumbers[i] = numberA;
                inputNumbers[j] = numberB;
                //计算 b -a
                inputNumbers[j] = inputNumbers[n-1];
                formulaAndValue= computation("-", numberB, numberA);
                inputNumbers[i] = formulaAndValue.getValue();
                canlculate24Point(n-1);
                //还原
                inputNumbers[i] = numberA;
                inputNumbers[j] = numberB;
                //计算 a * b
                inputNumbers[j] = inputNumbers[n-1];
                formulaAndValue= computation("*", numberA, numberB);
                inputNumbers[i] = formulaAndValue.getValue();
                canlculate24Point(n-1);
                //还原
                inputNumbers[i] = numberA;
                inputNumbers[j] = numberB;
                //计算 a / b
                if (0 != numberB.getAint()) {
                    inputNumbers[j] = inputNumbers[n-1];
                    formulaAndValue= computation("/", numberA, numberB);
                    inputNumbers[i] = formulaAndValue.getValue();
                    canlculate24Point(n-1);
                }
                //还原
                inputNumbers[i] = numberA;
                inputNumbers[j] = numberB;
                //计算 b / a
                if (0 != numberA.getAint()) {
                    formulaAndValue= computation("/", numberB, numberA);
                    inputNumbers[i] = formulaAndValue.getValue();
                    inputNumbers[j] = inputNumbers[n-1];
                    canlculate24Point(n-1);
                }
                //结束一次循环，还原inputNumber[i] inputNumber[j]
                inputNumbers[i] = numberA;
                inputNumbers[j] = numberB;
            }
        }
    }

    private FormulaAndValue computation(String operator, OperateNumber a, OperateNumber b) {
        int result = Integer.MIN_VALUE;
        FormulaAndValue formulaAndValue = new FormulaAndValue();
        String expression = "";
        switch (operator) {
            case PLUS : {
                result = a.getAint() + b.getAint();
                expression = constructFormula(operator, a, b);
                break;
            }
            case MINUS : {
                result = a.getAint() - b.getAint();
                expression = constructFormula(operator, a, b);
                break;
            }
            case MULTIPLY : {
                result = a.getAint() * b.getAint();
                expression = constructFormula(operator, a, b);
                break;
            }
            case DIVISION : {
                result = a.getAint() / b.getAint();
                expression = constructFormula(operator, a, b);
                break;
            }
        }
        OperateNumber operateNumber = new OperateNumber(result, OperateNumber.getINTERMEDIATE());
        operateNumber.setExpression(expression);
        formulaAndValue.setValue(operateNumber);
        return formulaAndValue;
    }

    private String constructFormula(String operator, OperateNumber a, OperateNumber b) {
        String expression = "";
        expression = (OperateNumber.getORIGIN() == a.getFlag() ? String.valueOf(a.getAint()) : a.getExpression()) + operator
                     + (OperateNumber.getORIGIN() == b.getFlag() ? String.valueOf(b.getAint()) : b.getExpression());
        expression = "(" + expression + ")";
        return expression;
    }

    public static void main(String[] args) {
        int[] numbers = new int[] {10, 14, 1, 1};
        TwentyFourPointGame twentyFourPointGame = new TwentyFourPointGame(numbers);
        twentyFourPointGame.canlculate24Point(4);
    }
}
