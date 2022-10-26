
import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Interviewer implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<String> calendar = new ArrayList<>();
    private ArrayList<Interview> interviewes = new ArrayList<>();
    private String name;
    private String id;

    public Interviewer(String name, String id) {
    	this.name = name;
    	this.id = id;
    }


    public ObservableList<String> getObservableCalendar(){
        ObservableList<String> tmp = FXCollections.observableArrayList();
        tmp.addAll(calendar);
        return tmp;
    }

    public ObservableList<Interview> getObservableInterviewes(){
        ObservableList<Interview> tmp = FXCollections.observableArrayList();
        tmp.addAll(interviewes);
        return tmp;
    }

    public ArrayList<String> getCalendar(){
        return calendar;
    }

    public String useDate(){
        return calendar.remove(0);
    }

    public ArrayList<Interview> getInterviewes(){
        return interviewes;
    }

    public boolean addDate(String date){
        if(calendar.contains(date))
            return false;
        calendar.add(date);
        return true;
    }

    public boolean removeDate(String date){
        if(!calendar.contains(date))
            return false;
        calendar.remove(date);
        return true;
    }

    public void addInterview(Interview interview){

        interviewes.add(interview);
//        return true;
    }

    public boolean removeInterview(Interview interview){
        if(!interviewes.contains(interview))
            return false;
        interviewes.remove(interview);
        return true;
    }

    public String getName() {
    	return this.name;
    }

    public String getId() {
    	return this.id;
    }

    public void setName(String name) {
    	this.name = name;
    }

    public void setId(String id) {
    	this.id = id;
    }


    public void decide(Interview interview, String decision){
        interview.setStatus(decision);
        if(decision.equals("Pass"))
            interview.getCandidate().setOfferingStage(true);
    }

}

