package ePublisher;

import java.util.Date;

//Wrap Milestone as a class
public class Milestone extends ProjectEvent {

	private String user, title, note;
	private Date date;
	
	Milestone(String user, String title, String note, Date date){
		
		this.user = user;
		this.title = title;
		this.note = note;
		this.date = date;
		
	}
	
	@Override
	public String toString(){
		
		return "|Milestone| "+ user + ", " + title + ", " + note + ", " + date + ", " + allComment.toString();
		
	}
	
	
	
	
}
