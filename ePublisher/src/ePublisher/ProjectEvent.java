package ePublisher;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

//Project Event is used to associated comment
public class ProjectEvent{

	//add all the comments to a list
	ArrayList<Comment> allComment = new ArrayList<Comment>();
	public void addComment(String user, String comment){
	
		allComment.add(new Comment(user,comment));

	}
}
