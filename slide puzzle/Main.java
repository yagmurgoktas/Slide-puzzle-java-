import java.util.*;
import java.io.*;
class node {
	int size;
	int[] table;
	String s;
	node leftc;
	node rightc;
	node upc;
	node bottomc;
	int sifir;

public node(int size) {
	this.size=size;
	table=new int[size*size];
	s="";
}
	public node moveRight() {		
	if((this.sifir+1)/this.size==this.sifir/this.size && this.sifir+1<this.size*this.size ) {
		int[] tc=Arrays.copyOf(this.table,this.table.length);
		node newnode=new node(this.size);
		newnode.table=tc;
		newnode.s=this.s+"R";
		
		int m=this.table[this.sifir+1];
		newnode.table[this.sifir]=m;
		newnode.table[this.sifir+1]=0;
		this.rightc=newnode;
		newnode.sifir=this.sifir+1;
		return newnode;
	}else {
		this.rightc=null;
	}
		return null;
	
}
	public node moveUp() {
		if(this.sifir-this.size>=0) {
			int[] tc=Arrays.copyOf(this.table,this.table.length);
			node newnode=new node(this.size);
			newnode.table=tc;
			newnode.s=this.s+"U";
			
			int m=this.table[this.sifir-this.size];
			newnode.table[this.sifir]=m;
			newnode.table[this.sifir-this.size]=0;
			this.upc= newnode;
			newnode.sifir=this.sifir-this.size;
			return newnode;
		}else {
			this.upc=null;
		}
		return null;
	}
	public node moveLeft() {
		if((this.sifir-1)/this.size==this.sifir/this.size && this.sifir-1>=0) {
			int[] tc=Arrays.copyOf(this.table,this.table.length);
			node newnode=new node(this.size);
			newnode.table=tc;
			newnode.s=this.s+"L";
			
			int m=this.table[this.sifir-1];
			newnode.table[this.sifir]=m;
			newnode.table[this.sifir-1]=0;
			newnode.sifir=this.sifir-1;
			this.leftc= newnode;
			return newnode;
		}
		else {
			this.leftc=null;
		}
		return null;
		
	}
	public node moveBottom() {
		if(this.sifir+this.size<this.table.length) {
			int[] tc=Arrays.copyOf(this.table,this.table.length);
			node newnode=new node(this.size);
			newnode.table=tc;
			newnode.s=this.s + "D";
			
			int m=this.table[this.sifir+this.size];
			newnode.table[this.sifir]=m;
			newnode.table[this.sifir+this.size]=0;
			newnode.sifir=this.sifir+this.size;
			this.bottomc= newnode;
			return newnode;
		}
		else {
			this.bottomc=null;
		}
		return null;
		
	}
	public boolean control() {
		int[] arr=Arrays.copyOf(this.table, this.table.length);
		for(int i=0;i<arr.length-1;i++) {
			if(arr[i]!=i+1) {
				return false;
			}
		}
		
		return true;
	}
	
}

class Tree {
	node root;
	public Tree(node root) {
		this.root=root;
	}
	public void game(HashSet<String> set,PrintStream pamir) {
		
		Queue<node> queue=new LinkedList<node>();
		set.add(Arrays.toString(root.table));
		queue.add(this.root);
		
		while(queue.peek()!=null) {
			node no=queue.remove();
			
			if(no.control()) {
				pamir.println(no.s);
				
				
				
				return;
			}
			node b=no.moveLeft();
			if(b!=null && set.add(Arrays.toString(b.table))) {
				queue.add(b);
				
			}
			node c=no.moveRight();
			if(c!=null && set.add(Arrays.toString(c.table))) {
				queue.add(c);
				
			}
			
			node d=no.moveUp();
			if(d!=null && set.add(Arrays.toString(d.table)) ) {
				queue.add(d);
				
			}
			
			node a=no.moveBottom();
			if(a!=null && set.add(Arrays.toString(a.table))) {
				queue.add(a);
				
			}
		
			
		}
		pamir.println("N");
	}
	
}

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scanner=new Scanner(new File("src//"+args[0]));
		String str=scanner.nextLine();
		File c = new File("src//"+args[1]);
		PrintStream ps=new PrintStream(c);

		double p=1;	
		
		String g="";
		for(int k=0;k<str.length();k++) {
			if(str.charAt(k)=='-') {
				g+=" ";
				p++;
			}else {
				g+=str.charAt(k);
			}
		}
		int m=(int)Math.sqrt(p);
		
		
		int[] t=new int[m*m];
		
		node root=new node(m);
		
		
		Scanner files=new Scanner(g);
		for(int i=0;i<m*m;i++) {
			t[i]=files.nextInt();
			if(t[i]==0) {
				root.sifir=i;
			}
		}
		root.table=t;
		Tree newtree=new Tree(root);
		HashSet<String> set =new HashSet<>();
		
		newtree.game(set,ps);
	}
	

}
