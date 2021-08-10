public class test
{
    public static void main(String[] args) {
        int a = 15;
        int b = 9;
        System.out.println(ggd(a, b));
    }

    static int ggd(int a, int b)
    {
        return(a % b == 0 ? a : ggd(a % b, b));
    }
}