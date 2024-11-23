package codelearn;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class HackerRank {

    /*public static Scanner sc = new Scanner(System.in);
    public static int B;
    public static int H;
    public static boolean flag = false;

    static {
        B = sc.nextInt();
        H = sc.nextInt();
        if (B > 0 && H > 0) {
            flag = true;
        } else {
            System.out.println("java.lang.Exception: Breadth and height must be positive");
        }
    }*/

    public static void main(String[] args) {
        System.out.println("Hacker Rank");
        //int n = 12;
        //showN(n);
        /*if (flag) {
            int area = B * H;
            System.out.print(area);
        }*/
        //convertToString();
        //Date date = new Date(2022, 01, 12);
        //System.out.println("Day of week " + findDay(12, 12, 2023));
        //printCurrency();
        //negativeCount();
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        /* Enter your code here. Print output to STDOUT. */
        System.out.println(A.length() + B.length());
        if (A.compareTo(B) > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        StringBuilder newA = new StringBuilder(A.substring(0, 1).toUpperCase() + A.substring(1) + " ");
        String newB = B.substring(0, 1).toUpperCase() + B.substring(1);
        String newString = newA.append(newB).toString();
        System.out.println(newString);
        sc.close();
    }

    private static void negativeCount() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int count = 0;
        for (int j = 0; j < n; j++) {
            if (a[j] < 0) {
                count++;
            }
            int sum = 0;
            sum += a[j];
            for (int k = j + 1; k < n; k++) {
                sum += a[k];
                if (sum < 0) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void printCurrency() {
        /*Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();*/

        /*
        Locale[] locale = {Locale.US, LocaleIN, Locale.CHINA, Locale.FRANCE};
        String[] country = {"US", "India", "China", "France"};*/

        /*Scanner sc = new Scanner(System.in);
        double value = sc.nextDouble();
        System.out.println("US: " + NumberFormat.getCurrencyInstance(Locale.US).format(value));
        System.out.println("India: " + NumberFormat.getCurrencyInstance(new Locale("EN", "in")).format(value).replace("\u20B9", "Rs."));
        System.out.println("China: " + NumberFormat.getCurrencyInstance(Locale.CHINA).format(value).replace("\u00A5", "\uFFE5"));
        System.out.println("France: " + NumberFormat.getCurrencyInstance(Locale.FRANCE).format(value).replace('\u202F', ' '));*/

        Scanner sc = new Scanner(System.in);
        double value = sc.nextDouble();
        System.out.println("US: " + NumberFormat.getCurrencyInstance(Locale.US).format(value));
        System.out.println("India: " + NumberFormat.getCurrencyInstance(new Locale("EN", "in")).format(value).replace("\u20B9", "Rs."));
        System.out.println("China: " + NumberFormat.getCurrencyInstance(Locale.CHINA).format(value).replace("\u00A5", "\uFFE5"));
        System.out.println("France: " + NumberFormat.getCurrencyInstance(Locale.FRANCE).format(value).replace('\u202F', ' '));

        /*List<Locale> list = new ArrayList<>();
        list.add(Locale.US);
        list.add(new Locale("EN", "in"));
        list.add(Locale.CHINA);
        list.add(Locale.FRANCE);
        for (Locale value : list) {
            NumberFormat format = NumberFormat.getCurrencyInstance(value);
            System.out.println(value.getDisplayName() + ": " + format.format(payment));
        }*/
    }

    public static String findDay(int month, int day, int year) {
        int january = Calendar.JANUARY;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()).toUpperCase();
    }

    static int dayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String getDayStringOld(Date date, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("EEEE", Locale.getDefault());
        return formatter.format(date);
    }

    private static void convertToString() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = String.valueOf(n);
        if (s != null) {
            System.out.println("Good job");
        } else {
            System.out.println("Wrong answer");
        }
    }

    private static void showN(int n) {
        if (n % 2 != 0 || (n >= 6 && n <= 20)) {
            System.out.println("Weird");
        } else if ((n >= 2 && n <= 5) || n > 20) {
            System.out.println("Not Weird");
        }
    }

}
