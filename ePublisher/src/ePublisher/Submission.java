package ePublisher;

//wrap the Submission as a class
public class Submission extends ProjectEvent{
	
	private String user, title, note;
	private Object object;
	Submission(String user, String title, String note, Object object){
		
		this.user = user;
		this.title = title;
		this.note = note;
		this.object = object;
	}
	
	//print out what we want
	@Override
	public String toString(){
		
		if(object != null){
			return "|Submission| " + user + ", " + title + ", " + note + ", " + object.toString();
		}
		else{
			if(!allComment.isEmpty()){
				return "|Submission| " + user + ", " + title + ", " + note + ", "+ allComment.toString();
			}
			else{
				return "|Submission| " + user + ", " + title + ", " + note;
			}
		}
	
	}
}
