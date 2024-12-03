import java.util.Scanner;

public class HKRankTest {

    public static void typeCounter(String sentence) {
        //String[] array = sentence.split("\\s", -1);
        String stc = sentence.replaceAll("[ ]+", " ");
        String[] array = stc.split(" ");
        //int chars = 0;
        int strings = 0;
        int ints = 0;
        int doubles = 0;
        for (String s : array) {
            String regexNormalText = "[a-zA-Z]+";
            /*if (s.matches(regexNormalText)) {
             *//*if (s.length() == 1) {
                    chars = chars + 1;
                } else {*//*
                strings = strings + 1;
                //}
            }*/
            String regexDouble = "[+-]?\\d+\\.?(\\d+)?";
            String regexNumber = "[0-9]+";
            if (!s.matches(regexNumber) && !s.matches(regexDouble)) {
                strings = strings + 1;
            }
            if (s.matches(regexNumber)) {
                ints = ints + 1;
            }
            if (s.matches(regexDouble)) {
                doubles = doubles + 1;
            }
        }
        doubles = doubles - ints;
        //System.out.println("chars: "+ chars);
        System.out.println("string " + strings);
        System.out.println("integer " + ints);
        System.out.println("double " + doubles);
    }

    public static void main(String[] args) {
        /*printTestFirst();
        printMultiple(2);
        printLoop2(2, 3, 5, 2);*/
        printDataType(123);
    }

    static void printWithNextLine() {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (scanner.hasNext()) {
            i++;
            String textLine = scanner.nextLine();
            String value = String.format("%d %s", i, textLine);
            System.out.println(value);
        }
        scanner.close();
    }

    static void printDataType(int t) {
        Scanner sc = new Scanner(System.in);
        //int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            try {
                long x = sc.nextLong();
                System.out.println(x + " can be fitted in:");
                // if one of the if blocks get executed then we will have ans
                // no need to check for each dataType again and again
                if (x >= Byte.MIN_VALUE && x <= Byte.MAX_VALUE) {
                    System.out.println("* byte");
                    System.out.println("* short");
                    System.out.println("* int");
                } else if (x >= Short.MIN_VALUE && x <= Short.MAX_VALUE) {
                    System.out.println("* short");
                    System.out.println("* int");
                } else if (x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE) {
                    System.out.println("* int");
                }
                System.out.println("* long");
            } catch (Exception e) {
                System.out.println(sc.next() + " can't be fitted anywhere.");
            }
        }
        sc.close();
    }

    static void printLoop2(int a, int b, int n, int t) {
        Scanner in = new Scanner(System.in);
        //int t = in.nextInt();
        for (int i = 0; i < t; i++) {
           /* int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();*/
            double sum = a;
            for (int j = 0; j < n; j++) {
                sum += b * Math.pow(2, j);
                System.out.printf("%.0f ", sum);
            }
            System.out.println();
        }
        in.close();
    }

    static void printMultiple(int n) {
        for (int i = 1; i <= 10; i++) {
            String formatString = String.format("%d x %d = %d", n, i, n * i);
            //System.out.println(n + " x " + i + " = " + (n * i));
            System.out.println(formatString);
        }
    }

    private static void printTestFirst() {
        //System.out.println("Hi Java");
        /*String stc = "I will         give you a 20.5 rupees instead of 20";
        String sentence = "Some tokens (like \"50\") are parse able both as integer and float";
        String sentence1 = "intention is to only count each token as one type or the other (so count as an integer, not as a float).";*/
        //typeCounter("");
        //typeCounter("Hello my 50 name 4.5 is James, I 20 years old and I have 5.7 coins");
        //typeCounter(stc);

        int mask = 0x000F;
        int value = 0x2222;
        //System.out.println(value & mask);

        /*Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        double d = scan.nextDouble();
        scan.nextLine();
        String s = scan.next();
        scan.close();

        System.out.println("Int: " + i);
        System.out.println("Double: " + d);
        System.out.println("String: " + s);*/
    }

}
