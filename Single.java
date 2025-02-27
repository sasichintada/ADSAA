import java.util.*;

class Single {
	static final int INF=Integer.MAX_VALUE;
	static int minDistance(int[] dist,boolean[] sptSet,int n) {
		int min=INF,minIndex=-1;
		for(int v=0;v<n;v++) {
			if(!sptSet[v] && dist[v]<=min) {
				min=dist[v];
				minIndex=v;
			}
		}
		return minIndex;
	}
	static void shortest(int[][] cost,int s,int n) {
		int[] dist=new int[n];
		boolean[] sptSet=new boolean[n];
		Arrays.fill(dist,INF);
		dist[s]=0;
		
		for(int j=0;j<n-1;j++) {
			int u=minDistance(dist,sptSet,n);
			sptSet[u]=true;
			for(int v=0;v<n;v++) {
				if(!sptSet[v] && cost[u][v]!=INF && dist[u]!=INF && dist[u]+cost[u][v]<dist[v]) {
					dist[v]=dist[u]+cost[u][v];
				}
			}
		}		
		System.out.println("vertex distance from source");
		for(int i=0;i<n;i++) {
			System.out.println(i+" \t\t "+dist[i]);
		}
	}
	
	public static void main(String args[]) {
		int INF=Integer.MAX_VALUE;
		int[][] cost={
			{0,10,INF,INF,INF,30,INF},
			{INF,0,20,INF,INF,INF,INF},
			{INF,INF,0,15,5,INF,INF},
			{INF,INF,INF,0,12,INF,20},
			{INF,INF,INF,INF,0,INF,7},
			{INF,INF,INF,INF,INF,0,35},
			{INF,INF,INF,INF,INF,INF,0}
		};
		int s=0;
		int n=cost.length;
		shortest(cost,s,n);
	}
}
