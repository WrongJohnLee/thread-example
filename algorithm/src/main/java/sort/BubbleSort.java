package sort;

/**
 * 冒泡算法
 * 每次缩小1循环
 */
public class BubbleSort {
    public static void bubbleSort(int[] array) {
        // 10 89 1 -6 77 33
        // 10 89 1 -6 77 33
        // 10 1 89 -6 77 33
        //i由1开始，因为j从0开始，每次范围都缩小1，直到剩下一个没得比较停止
        for (int i = 1; i < array.length; i++) {
            //j越来越小，表示范围越来越小，第一次是循环数组的长度，依次减1
            for (int j = 0; j < (array.length - i); j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.println(String.format("第%1$s次排序后: %2$s", i, arrayToString(array)));
        }
        
    }
    
    public static String arrayToString(int[] array) {
        StringBuilder result = new StringBuilder();
        for (int value : array) {
            result.append(value).append("  ");
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
        int[] array = {10, 89, 1, -6, 77, 33};
        bubbleSort(array);
    }
}
