import java.util.*;

class Solution {
    static int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] dp = new int[n+1][m+1];
        
        
        
        
        for(int[] i : puddles){
            dp[i[1]][i[0]] = -1;
        }
        dp[1][1] = 1;
        
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if((i==1 && j==1) || dp[i][j] == - 1) continue;
                
                if(dp[i-1][j] != -1){
                    dp[i][j] = (dp[i][j] + dp[i-1][j]) % MOD;
                }
                if(dp[i][j-1] != -1){
                    dp[i][j] = (dp[i][j] + dp[i][j-1]) % MOD;
                }
                
            }
        }
        // for(int i = 1 ; i <= n ; i++){
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        return dp[n][m];
    }
}