package ePublisher;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//project implements the handler methods
public class Project implements ProjectEventHandler {

	ArrayList<ProjectEvent> submissions = new ArrayList<ProjectEvent>();
	ArrayList<ProjectEvent> milestone = new ArrayList<ProjectEvent>();

	//create a Milestone and regard it as an event
	@Override
	public ProjectEvent createMilestone(String user, String title, String description, Date deadline){
		
		ProjectEvent p = new Milestone(user, title, description, deadline);
		milestone.add(p);
		events.add(p);
		return p;
	}

	//create a Submission and regard it as an event
	@Override
	public ProjectEvent createSubmission(String user, String title, String description, ProjectEvent relatedMilestone) {
		
		ProjectEvent p = new Submission(user, title, description, relatedMilestone);
		submissions.add(p);
		events.add(p);
		return p;
	}
	
	//iterate all the Milestone, and print the content
	public void printMilestones(){
		
		Iterator<ProjectEvent> i = milestone.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
	}

	//iterate all the Submission, and print the content
	public void printSubmissions() {
		
		Iterator<ProjectEvent> i = submissions.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
	}

	//iterate all the event(both type), and prin thte content
	public void printEvents() {
		
		String s ="";
		Iterator<ProjectEvent> i = events.iterator();
		while(i.hasNext()){

			s += i.next().toString()+"\n";
		}
		System.out.println(s);
	}

}
