package 跟钱有关的精度问题;

import java.math.BigDecimal;

public class Double比较问题 {
    public static void main(String[] args) {
        Double a = 0.10000000000000001;
        Double b = 0.10000000000000000;
        //直接比较不行
        if (a > b) {
            System.out.println("xxx");
        }
        //这样ok
        if (a.compareTo(b) >= 0) {
            System.out.println("sssssss");
        }
        //这样ok
        if (compareTo(a, b) >= 0) {
            System.out.println("ssssss");
        }
        /**
         * 因为double的精度为16位，超过16位词方法不行 了。
         * 不过一般生产不存在16精度的数据。
         */
        if (a.doubleValue() > b.doubleValue()) {
            System.out.println("11111111111111");
        }
    }

    /**
     * 比较两个double数据类型
     *
     * @param d1
     * @param d2
     * @return 2018年4月16日
     */
    public static int compareTo(Double d1, Double d2) {
        BigDecimal data1 = new BigDecimal(d1);
        BigDecimal data2 = new BigDecimal(d2);
        return data1.compareTo(data2);
    }
}
