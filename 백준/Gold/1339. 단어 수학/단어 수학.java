import java.util.*;

public class Main {
    private static int N;
    private static String[] words;
    private static int[] alpha = new int[26];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = sc.next();
        }

        int[] weight = new int[26]; 
        for (String word : words) {
            int pow = 1;
            for (int j = word.length() - 1; j >= 0; j--) {
                weight[word.charAt(j) - 'A'] += pow;
                pow *= 10;
            }
        }
        
        Integer[] indices = new Integer[26];
        for (int i = 0; i < 26; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> weight[b] - weight[a]);

        int num = 9;
        for (int i = 0; i < 26; i++) {
            if (weight[indices[i]] == 0) break; 
            alpha[indices[i]] = num--;
        }
        
        int answer = 0;
        for (String word : words) {
            int value = 0;
            for (int j = 0; j < word.length(); j++) {
                value = value * 10 + alpha[word.charAt(j) - 'A'];
            }
            answer += value;
        }

        System.out.println(answer);
    }
}