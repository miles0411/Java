
public class FSqeuence {
	static int array [] = new int [10];
	public static void main(String[] arg){
		array [0] =1;
		array [1] =2;
	for(int i= 2;i<=9;i++){
		array[i]=array[i-2]+array[i-1];
	}
	String result="";
	for(int i = 1;i<=10;i++){
	int x=f(i);
	result+= " "+String.valueOf(x);
	}
	System.out.println(result);
	for(int i=0;i<array.length;i++){
		System.out.print(array[i]+" ");
		
	}
	
	}
	
	private static int f(int i) {
		if(i==1){
			return 1;
		}
		else if(i==2){
			return 2;
		}
		else{	
			return f(i-1)+f(i-2);
		}
	}
}

