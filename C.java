package trueQuestion.tenth;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class C {
	static int[][] map;
	static boolean[][] check;
	static Node[][] road;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		map = new int[30][50];
		check = new boolean[30][50];
		road = new Node[30][50];
		String[] strmap = new String[30];
		Scanner in = new Scanner(System.in);
		for(int i = 0;i < 30;i++){
			strmap[i] = in.nextLine();
		}
		for(int i = 0;i < 30;i++){
			for(int j = 0;j < 50;j++){
				String str = ""+strmap[i].charAt(j);
				int ii = Integer.valueOf(str);
				map[i][j]=ii;
				check[i][j] = ii==1?true:false;
			}
		}
		road[29][49]=new Node(-1,-1);
//		System.out.println(map[29][49]+" "+check[29][49]);
//		List<Node> li = adj(1,0);
//		for(Node n:li){
//			System.out.println(n.x+" "+n.y);
//		}
		
		check[29][49] = true;
		bfs(29,49);
		Node pre = road[0][0];
		int px=0;int py=0;String str = "";
		while(pre.x!=-1||pre.y!=-1){
			int nx = pre.x;int ny = pre.y;
			if(ny==py){
				if(nx-px==1){
					str+="D";
				}else{
					str+="U";
				}
			}else{
				if(ny-py==1){
					str+="R";
				}else{
					str+="L";
				}
			}
//			System.out.println(pre.x+" "+pre.y);
			px=nx;py=ny;
			pre = road[pre.x][pre.y];
		}
		System.out.println(str);
		System.out.println(str.length());
	}

	private static List<Node> adj(int x,int y){
		List<Node> li = new LinkedList<Node>();
		int[] dir ={-1,1};
		for(int i:dir){
			if(x+i>=0&&x+i<30){
				if(check[x+i][y]==false){
					li.add(new Node(x+i,y));
				}
			}
			if(y+i>=0&&y+i<50){
				if(check[x][y+i]==false){
					li.add(new Node(x,y+i));
				}
			}
		}
		return li;
	}
	
	private static void bfs(int x,int y){
		Queue<Node> q = new LinkedList<Node>();
		Node nx = new Node(x,y);
		q.offer(nx);
		while(!q.isEmpty()){
			Node nn = q.poll();
			for(Node n:adj(nn.x,nn.y)){
				int i = n.x;
				int j = n.y;
				road[i][j] = nn;
				check[i][j]=true;
				q.offer(n);
//				System.out.println(1);
			}
		}
	}
}

class Node{
	int x;
	int y;
	public Node(int x,int y){
		this.x = x;
		this.y = y;
	}
}
