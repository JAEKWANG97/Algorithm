import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        int[] frequency = new int[26];
        for (char c : input.toCharArray()) {
            frequency[c - 'A']++;
        }

        int oddCount = 0;
        char oddChar = '\0';
        for (int i = 0; i < 26; i++) {
            if (frequency[i] % 2 == 1) {
                oddCount++;
                oddChar = (char) (i + 'A');
            }
        }

        if (oddCount > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        char[] palindrome = new char[input.length()];
        int left = 0;
        int right = input.length() - 1;

        for (int i = 0; i < 26; i++) {
            while (frequency[i] >= 2) {
                char currentChar = (char) (i + 'A');
                palindrome[left++] = currentChar;
                palindrome[right--] = currentChar;
                frequency[i] -= 2;
            }
        }

        // 홀수 문자 처리
        if (oddCount == 1) {
            palindrome[left] = oddChar;
        }

        System.out.println(new String(palindrome));
    }
}