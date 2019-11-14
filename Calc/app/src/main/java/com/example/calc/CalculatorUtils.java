package com.example.calc;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.*;
/**
 * 功能描述: 对数学表达式进行处理的工具类
 * @Author: CLJZ
 * @Date: 2019/10/15 17:02
 * @Verson 1.0.0
 */
public class CalculatorUtils {
  /**
   * 功能描述: 对输入的字符串进行算数运算处理
   * @Param: [expression]
   * @Return: double
   * @Author: CLJZ
   * @Date: 2019/10/15 16:52
   * @Verson 1.0.0
   */
    public  static String calculate(String expression) {
        //解决10%+10%问题
        expression = expression.replaceAll("%","/100");
        StringBuilder sb = new StringBuilder();
        sb.append(expression);
        //对第一个字符是和最后一个字符是运算符的进行处理
        sb.insert(0,'0');
        //转换为String字符串进行判断处理
        String formula  = sb.toString();
        //取出所有参与运算的数字
        String[] str = formula.split("[\\+\\-\\*//%]");
        //获取所有的运算符
        Pattern p = compile("[\\+\\-\\*//%]");
        Matcher m = p.matcher(formula);
        String  character = "";
        while (m.find()) {
            character += m.group();
        }
        char[] operator = character.toCharArray();
        //把所有非加法运算转换为加法运算
        for (int i = 0; i < operator.length; i++) {
            //左表示该运算符左边的数字，右表示该运算符右边的算式
            int left = i;
            int right = i+1;
            if (operator[i] == '*') {
                BigDecimal b1 = new BigDecimal(str[left]);
                BigDecimal b2 = new BigDecimal(str[right]);
                str[right] = String.valueOf(b1.multiply(b2));
                str[left] = "0";
            } else if(operator[i] == '/') {
                BigDecimal b1 = new BigDecimal(str[left]);
                BigDecimal b2 = new BigDecimal(str[right]);
                //保留十三位，采用华为的保留位数
                str[right] = String.valueOf(b1.divide(b2,13, BigDecimal.ROUND_HALF_UP));
                str[left] = "0";
            } else if(operator[i] == '-') {
                BigDecimal b1 = new BigDecimal(0);
                BigDecimal b2 = new BigDecimal(str[right]);
                str[right] = String.valueOf(b1.subtract(b2));
            }
        }
        //转换为加法运算后全部加起来
        BigDecimal sum = new BigDecimal("0");
        for (String s : str) {
            BigDecimal b1 = new BigDecimal("0");
            BigDecimal b2 = new BigDecimal(s);
            sum = sum.add(b1.add(b2));
        }
        return subZeroAndDot(String.valueOf(sum));
    }
    /**
     * 功能描述: 去除算数结果后多余的零
     * @Param: [s]
     * @Return: java.lang.String
     * @Author: CLJZ
     * @Date: 2019/10/21 14:05
     * @Verson 1.0.0
     */
    public static String subZeroAndDot(String result){
        /**
         * point:检测算式中是否包含小数点
         * redundantZero:用于去除多余的零
         * lastPosition：用于去除末尾的零
         */
        String point = ".";
        String redundantZero = "0+?$";
        String lastPosition = "[.]$";
        if(result.indexOf(point) > 0) {
            //去掉多余的0
            result = result.replaceAll(redundantZero, "");
            //如最后一位是.则去掉
            result = result.replaceAll(lastPosition, "");
        }
        return result;
    }
}