import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        String binary = Integer.toBinaryString(k + 1);

        StringBuilder result = new StringBuilder();
        for (int i = 1; i < binary.length(); i++) { 
            result.append(binary.charAt(i) == '0' ? '4' : '7');
        }

        System.out.println(result.toString());
    }
}