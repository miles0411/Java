package ePublisher;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public interface ProjectEventHandler{
	
    public List<ProjectEvent> events = new ArrayList<ProjectEvent>();
    
    public ProjectEvent createSubmission(String user, String title, String description, ProjectEvent relatedMilestone);
    
    public ProjectEvent createMilestone(String user, String title, String description, Date deadline);
    
    public void printEvents();
	
	public void printMilestones();
	
	public void printSubmissions();
}