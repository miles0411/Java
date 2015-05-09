
public class GPTreeChildPositionException extends Exception{

	int i;
	GPTreeChildPositionException(){}
	GPTreeChildPositionException(String msg){
		super(msg);
	}
	GPTreeChildPositionException(int i){
		this.i=i;
	}
}
