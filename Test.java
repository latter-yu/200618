import java.util.Scanner;

public class Test {

    public static void main1(String[] args) {

        // 编写一个函数, 将两个数字相加, 不得使用 + 或其他 算数 运算符.
        // 给定两个 int A 和 B. 请返回 A ＋ B 的值.

        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        System.out.println(addAB(A, B));
    }

    private static int addAB(int A, int B) {

        // 异或性质: 0 ^ 0 = 0, 1 ^ 0 = 1, 1 ^ 1 = 0, 可以实现数字无进位相加.
        // 与运算: 0 & 0 = 0, 1 & 0 = 0, 1 & 1 = 1, 将与运算的结果左移 1 位即可变为进位.
        // 一直加到进位位为 0 结束计算.

        // 3 ^ 5 = 0011 ^ 0101 = 0110 = 6
        // 3 & 5 = 0011 & 0101 = 0001
        // 0001 << 1 = 0010 = 2

        if (B != 0 && A != 0) {
            int a = A ^ B;
            int b = (A & B) << 1;
            return addAB(a, b);
        }else if (B == 0){
            return A;
        }else {
            return B;
        }
    }

    public static int addAB1(int A, int B) {
        // 方法二:
        // 3 & 5 = 0011 & 0101 = 0001 = 1
        // 3 | 5 = 0011 | 0101 = 0111 = 7
        return (A | B) + (A & B);
    }
}

class Main {
    public static final long LIMIT = 300000;//最多搜索次数
    public static final long N = 1000000007;//求余

    public static void main(String[] args) {
        /*
        小易总是感觉饥饿，所以作为章鱼的小易经常出去寻找贝壳吃.
        最开始小易在一个初始位置 x_0. 对于小易所处的当前位置x, 他只能通过神秘的力量移动到 4 * x + 3 或者 8 * x + 7.
        因为使用神秘力量要耗费太多体力, 所以它只能使用神秘力量最多 100,000 次. 贝壳总生长在能被 1,000,000,007 整除的位置
        (比如：位置 0, 位置 1,000,000,007, 位置 2,000,000,014 等).小易需要你帮忙计算最少需要使用多少次神秘力量就能吃到贝壳。

        输入一个初始位置 x_0,范围在 1 到 1,000,000,006.
        输出小易最少需要使用神秘力量的次数，如果使用次数使用完还没找到贝壳，则输出 -1.

        例如:
        输入: 125000000
        输出: 1

        解析:
        y = 4 * x + 3, 相当于 x 的二进制左移 2 位，然后空位补 1，即原先 x 的二进制为 #####, 则 y 的二进制为 #####11.
        y = 8 * x + 3,相当于 y 的二进制左移 3 位，然后空位补 1，即原先 x 的二进制为 #####, 则 y 的二进制为 #####111.

        小易的移动，最终可以表达成 4x + 3 操作进行了 m 次，8x + 7操作进行了 n 次.
        4 * x + 3操作进行 m 次，则 x 的二进制后面增加 2m 个 1.
        8 * x + 7操作进行 n 次，则 x 的二进制后面增加了 3n 个 1.
        小易的移动，最终可以表达为：x 的二进制后面增加了（2m + 3n）个 1.

        初始位置为 0，则直接满足，需移动 0 次.
        初始位置不为 0，则记 times = （2m + 3n）, m 取 1 到 10_0000, n 取 1 到 10_0000.
        所以，times 的取值范围为 [2,30_0000]. 即：最多 30_0000 次搜索，就能获得结果.
        */

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            System.out.println(count(sc.nextLong()));
        }
    }

    // 次数判定方法
    public static long count(long x) {
        // 如果初始位置为 0，则直接可行，返回 0 次
        if (x == 0) {
            return 0L;
        }else {
            // 初始位置不为0，则开始搜索
            return find(x);
        }
    }

    // 不为 0 时的搜索
    public static long find(long x) {
        // x：初始坐标
        long temp = x;
        // 遍历，获取最小位移
        for (int i = 1; i <= LIMIT; i++) {
            // long temp = (x + 1) * (long)Math.pow(2, i) - 1;
            // 当循环较大时，幂次太高，数字超出范围，报错
            // 递推
            temp = (temp * 2 + 1 ) % N;
            if (temp % N == 0 ) {
                // i 是符合条件的最小偏移，然后对其进行分解
                for (int j =0; j <= (i / 2); j++) {
                    //j对应a值
                    if ((i - 2 * j) % 3 == 0) {
                        return ((i + j) / 3);
                    }
                }
            }
        }
        //超过最大次数还没匹配，则输出-1
        return -1L;
    }
}