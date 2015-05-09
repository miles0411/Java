package ePublisher;

/*
 * Main.java - an example test class, showing the expected usage of the classes to be implemented.
 */
import java.util.Date;
public class Main
{
    public static void main(String [] args)
    {
        Project project = new Project();
        ProjectEvent event1 = project.createMilestone("user1", "Feature Title", "This is the first feature", new Date());
        ProjectEvent event2 = project.createSubmission("user2", "Bug Title", "This is the first bug", null);
        ProjectEvent event3 = project.createSubmission("user2", "Bug Title 2", "This is the second bug", event1);
        ProjectEvent event4 = project.createSubmission("user2", "Bug Title 3", "This is the third bug", null);
     
        event1.addComment("User 1", "This is my first comment");
        event1.addComment("User 2", "This is another comment");
        event2.addComment("User 1","I also have a comment about this");
        
		project.printMilestones();
		project.printSubmissions();
		project.printEvents();
    }
}