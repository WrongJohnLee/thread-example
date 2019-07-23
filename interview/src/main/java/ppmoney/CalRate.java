package ppmoney;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @ClassName CalRate
 * @Description 计算利息，A位本息总和，P为本金，r为利息，t为每年计算利息次数，n为投资年限
 * @Author qzli
 * @Date 2019/7/23 21:31
 * @Version 1.0
 **/
public class CalRate {
    public static void main(String[] args) {
        System.out.println("请输入本金");
        Scanner scanner = new Scanner(System.in);
        String p = scanner.next();
        validIn("^([1-9][0-9]*)+(\\.?)+([0-9][0-9]*)$", p);
        System.out.println("请输入利息");
        String r = scanner.next();
        validIn("^([1-9][0-9]*)+(\\.?)+([0-9][0-9]*)$", r);
        System.out.println("请输入每年计算利息次数");
        String t = scanner.next();
        validIn("^[1-9][0-9]*$", t);
        System.out.println("请输入投资年限");
        String n = scanner.next();
        validIn("^[1-9][0-9]*$", n);
        System.out.println(cal(new BigDecimal(p), new BigDecimal(r), Integer.valueOf(t), Integer.valueOf(n)));
    }
    
    public static void validIn(String regex, String in) {
        if(!Pattern.matches(regex, in)){
            System.out.println("输入不合法");
        }
    }
    
    public static BigDecimal cal(BigDecimal p, BigDecimal r, int t, int n) {
        int size = t * n;
        BigDecimal result = null;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                result = p.add(p.multiply(r));
            }else {
                result = result.add(result.multiply(r));
            }
        }
        return result;
    }
}
