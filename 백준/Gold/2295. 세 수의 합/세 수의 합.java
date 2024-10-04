import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int arr[];


    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
    }

    private static int solution() {
        int answer = 0;
        for (int i = N - 1; i >= 0; i--) {
            int target = arr[i];
            for (int j = i; j >= 0; j--) {
                for (int k = j; k >= 0; k--) {
                    int x = arr[j];
                    int y = arr[k];
                    if (binarySearch(target - x - y) > 0) {
                        return target;
                    }
                }
            }
        }
        return answer;
    }

    private static int binarySearch(int target) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                return arr[mid];
            }

            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }
}