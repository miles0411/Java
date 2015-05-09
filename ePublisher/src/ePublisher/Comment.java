package ePublisher;

//To wrap comment as a class
public class Comment extends ProjectEvent {
	
	String user, comment;
	
	//constructor
	Comment(String user, String comment){
		
		this.user = user;
		this.comment = comment;
		
	}
	
	@Override
	public String toString(){
		
		return user + ": " + comment;
		
	}
	
}
