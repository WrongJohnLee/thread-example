package basetype;

/**
 * @ClassName Test
 * @Description TODO
 * @Author qzli
 * @Date 2019/7/19 22:37
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        //占一个字节（8bit），2的8次方-1，因为最高位区分正负
        byte minByte = -128;
        byte maxByte = 127;
        //2个字节，16位，只能存一个字符
        char char1 = 'a';
        char char2 = '牛';
        //2个字节，16位，
        short minShort = -32768;//（-2^15）；
        short maxShort = 32767;//（2^15 - 1）;
        int minInt = -2147483648;//（-2^31）；
        int maxInt = 2147483647;//（2^31-1）；
        long minLong = 0x8000000000000000L;//（-2^63）；
        long maxLong = 0x7fffffffffffffffL;//（2^63 -1）；
        //4个字节，32位，最高位符号位，指数位占8位（只能保证10进制的6位小数运算），小数位占23位，最大值为2的32次方-1
        float minFloat = -8388608f;
        float maxFloat = 8388608f;
        //8个字节，64位，最高位符号位，指数位占11位（只能保证10进制的15位小数运算），小数位占52位，最大值为2的52次方-1
        double minDouble = Double.MIN_VALUE;
        double maxDouble =  Double.MAX_VALUE;
    
        String s1 = "啊哈哈";
        char[] chars = s1.toCharArray();
        for (char aChar : chars) {
            System.out.println(Character.toString(aChar));
        }
    }
}
