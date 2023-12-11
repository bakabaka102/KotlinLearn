package codelearn;

public class HackerRank {

    public static void main(String[] args) {
        int n = 12;
        showN(n);
    }

    private static void showN(int n) {
        if (n % 2 != 0 || (n >= 6 && n <= 20)) {
            System.out.println("Weird");
        } else if ((n >= 2 && n <= 5) || n > 20) {
            System.out.println("Not Weird");
        }
    }

}
