import sys
from collections import deque
# 상자의 크기와 층수를 입력받습니다.
M, N, H = map(int, sys.stdin.readline().split())

# 3차원 배열을 초기화합니다.
tomatoes = [[[0 for _ in range(M)] for _ in range(N)] for _ in range(H)]

# 각 층에 대한 토마토 정보를 입력받아 3차원 배열에 저장합니다.
for h in range(H):
    for n in range(N):
        # 각 줄에 대한 토마토 상태를 입력받습니다.
        tomatoes[h][n] = list(map(int, sys.stdin.readline().split()))
        
tomatoes_index = []
raw_tomatoes=[]
for h in range(H):
    for n in range(N):
        for m in range(M):
            if tomatoes[h][n][m] == 1:
                count = 0
                tomatoes_index.append((h,n,m,count))
            if tomatoes[h][n][m] == 0:
                raw_tomatoes.append((h,n,m))
           
def bfs(tomatoes , tomatoes_index , raw_tomatoes):
    visit = [[[False for _ in range(M)] for _ in range(N)] for _ in range(H)]
    que = deque()
    que.extend(tomatoes_index)
    for h,n,m,count in tomatoes_index:
        visit[h][n][m] = True
        
    while que:
        cur = que.popleft()
        h,n,m,count = cur
        
        dh = [1,-1,0,0,0,0]
        dn = [0,0,1,-1,0,0]
        dm = [0,0,0,0,1,-1]
        
        for d in range(6):
            nh = h+dh[d]
            nn = n+dn[d]
            nm = m+dm[d]
            if 0<= nh < H and 0 <= nn < N and 0<= nm <M :
                if not visit[nh][nn][nm] and tomatoes[nh][nn][nm] == 0:
                    que.append((nh,nn,nm,count+1))
                    visit[nh][nn][nm] = True
    
    for h,n,m in raw_tomatoes:
        if visit[h][n][m] == False:
            return print(-1)
    
    return print(count)


bfs(tomatoes , tomatoes_index , raw_tomatoes)