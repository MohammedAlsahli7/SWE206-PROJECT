
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Interview implements Serializable {
	private static final long serialVersionUID = 1L;
	private String date;
	private String status; // None, Pass, Fail, Hold
	private ArrayList<Interviewer> interviewers = new ArrayList<>();
	private Candidate candidate;
	private Interviewer interviewer;

	public Interview(String date, Interviewer interviewer, Candidate candidate) {
		this.date = date;
		this.status = "None";
		this.interviewer = interviewer;
		this.candidate = candidate;
	}
	
	public Interviewer getInterviewer() {
		return this.interviewer;
	}

	public ObservableList<Interviewer> getObservableInterviewers() {
		ObservableList<Interviewer> tmp = FXCollections.observableArrayList();
		tmp.addAll(interviewers);
		return tmp;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean addInterviewer(Interviewer interviewer) {
		if (interviewers.contains(interviewer))
			return false;
		interviewers.add(interviewer);
		return true;
	}

	public boolean removeInterviewer(Interviewer interviewer) {
		if (!interviewers.contains(interviewer))
			return false;
		interviewers.remove(interviewer);
		return true;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public String getStatus() {
		return status;
	}

	public String getDate() {
		return date;
	}

	public ArrayList<Interviewer> getInterviewers() {
		return interviewers;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void moveToOfferingStage(Candidate candidate1) {
		candidate.setOfferingStage(true);
	}

	@Override
	public boolean equals(Object interview) {
		// self check
		if (this == interview)
			return true;
		// null check
		// type check and cast
		if ((interview == null) || (getClass() != interview.getClass()))
			return false;
		Interview person = (Interview) interview;
		// field comparison
		return Objects.equals(candidate.getName(), person.candidate.getName())
				&& Objects.equals(candidate.getId(), person.candidate.getId());

	}

	@Override
	public String toString() {
		return date + ", " + candidate.getName();
	}

}