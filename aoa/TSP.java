import java.util.*;
class TSP{
  public static int tCost,path[],graph[][];public static boolean v[];
  public static void main(String[] args) {
    System.out.println("Travelling Salesman");
    Scanner ob = new Scanner(System.in);
    System.out.print("Enter no of Cities ");
    int i,j,k,n= ob.nextInt();tCost = Integer.MAX_VALUE;
    graph = new int[n][n];path = new int[n+1];
    v = new boolean[n];
    System.out.print("Enter the Cost Matrix\n");
    for(i=0;i<n;i++){
      for(j=0;j<n;j++){
        System.out.print("Cost of M["+(i+1)+"]["+(j+1)+"] = ");
        graph[i][j]=ob.nextInt();
      }
    }
    path[0]=1;path[n]=1;
    if (n<=1)
      tCost = 0;
    else{
      v[0] = true;
      tCost = tsp(graph,v,0,n,1,0,tCost);
      minCost(0,n,0);
    }
    System.out.println("Min Tour costs "+tCost);
    System.out.print("Min Path  = ");
    for(j=0;j<n;j++)
        System.out.print(path[j]+"->");
    System.out.print(path[n]+"\n\n");
  }
  static int leastPath(int C,int n){
    int i,_c=Integer.MAX_VALUE,min=Integer.MAX_VALUE,cMin=0;
    for(i=0;i<n;i++){
      if(graph[C][i]!=0&&(v[i]==false)){
        if(graph[C][i]+graph[i][C]<min){
          min=graph[i][0]+graph[C][i];
          cMin=graph[C][i];
          _c=i;
        }
      }
    }
    return _c;
  }
  static void minCost(int C,int n,int j){
    int i,_C;
    v[C]=true;
    path[j]=C+1;
    _C=leastPath(C,n);
    if(_C==Integer.MAX_VALUE){
      _C=0;
      return;
    }
    minCost(_C,n,j+1);
  }
  static int tsp(int graph[][], boolean v[],int cPos, int n,int count, int cost, int tCost){
    if (count == n && graph[cPos][0] > 0) {
      tCost = Math.min(tCost, cost + graph[cPos][0]);
      return tCost;
    }
    for (int i = 0; i < n; i++) {
      if (v[i] == false && graph[cPos][i] > 0) {
        v[i] = true;
        tCost = tsp(graph, v, i, n, count+1,cost+graph[cPos][i], tCost);
        v[i] = false;
      }
    }
    return tCost;
  }
}