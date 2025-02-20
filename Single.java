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
		Arrays.fill(sptSet,false);
		dist[s]=0;
		
		for(int j=0;j<n-1;j++) {
			int u=minDistance(dist,sptSet,n);
			if (u==-1) break;
			sptSet[u]=true;
			for(int v=0;v<n;v++) {
				if(!sptSet[v] && cost[u][v]!=INF && dist[u]!=INF && dist[u]+cost[u][v]<dist[v]) {
					dist[v]=dist[u]+cost[u][v];
				}
			}
		}		
		System.out.println("vertex distance from source");
		for(int i=0;i<n;i++) {
			System.out.println((i+1)+"\t"+(dist[i]==INF?"INF":dist[i]));
		}
	}
	
	public static void main(String args[]) {
		int INF=Integer.MAX_VALUE;
		int[][] cost={
			{0,50,45,10,INF,INF},
			{INF,0,10,15,INF,INF},
			{INF,INF,0,INF,30,INF},
			{20,INF,INF,0,15,INF},
			{INF,20,35,INF,0,INF},
			{INF,INF,INF,INF,3,0}
		};
		int s=0;
		int n=cost.length;
		shortest(cost,s,n);
	}
}
