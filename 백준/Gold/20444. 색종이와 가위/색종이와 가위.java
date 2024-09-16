import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 입력: n (가위질 횟수), k (원하는 조각 수)
        long n = scanner.nextLong();
        long k = scanner.nextLong();
        
        // 계산: D = (n + 2)^2 - 4k
        long D = (n + 2) * (n + 2) - 4 * k;
        
        if (D < 0) {
            System.out.println("NO");
            scanner.close();
            return;
        }
        
        long sqrtD = sqrtLong(D);
        
        // 판별식이 완전 제곱수인지 확인
        if (sqrtD * sqrtD != D) {
            System.out.println("NO");
            scanner.close();
            return;
        }
        
        // h + 1 = (n + 2 + sqrtD) / 2
        // v + 1 = (n + 2 - sqrtD) / 2
        if ((n + 2 + sqrtD) % 2 != 0 || (n + 2 - sqrtD) % 2 != 0) {
            System.out.println("NO");
            scanner.close();
            return;
        }
        
        long x = (n + 2 + sqrtD) / 2;
        long y = (n + 2 - sqrtD) / 2;
        
        // x와 y가 각각 h + 1, v + 1 이므로 최소 1 이상이어야 함
        if (x < 1 || y < 1) {
            System.out.println("NO");
            scanner.close();
            return;
        }
        
        // (h + 1) * (v + 1) = k 인지 확인
        if (x * y == k) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        
        scanner.close();
    }
    
    public static long sqrtLong(long x) {
        if (x < 0) {
            throw new IllegalArgumentException("음수의 제곱근은 정의되지 않습니다.");
        }
        if (x == 0 || x == 1) {
            return x;
        }
        
        long start = 0;
        long end = Math.min(x, 1L << 32); 
        long ans = 0;
        
        while (start <= end) {
            long mid = start + (end - start) / 2;
            long midSquared = mid * mid;
            
            if (midSquared == x) {
                return mid;
            }
            
            if (midSquared < x) {
                start = mid + 1;
                ans = mid;
            } else {
                end = mid - 1;
            }
        }
        
        return ans;
    }
}