/**
 * 未命名变量示例2
 */

public class Jep456_E2 {
    public static void main(String[] args) {
        System.out.println(oldGetStringValue("不能转成数字"));//0
        System.out.println(newGetStringValue("不能转成数字"));//0
    }

    private static int oldGetStringValue(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException unused) { // 这里的 unused 未使用
            return 0;
        }
    }

    private static int newGetStringValue(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException _) { // 使用 _ 占位
            return 0;
        }
    }
}