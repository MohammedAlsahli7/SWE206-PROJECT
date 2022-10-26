
import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TA implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Candidate> candidates = new ArrayList<>();
	private ArrayList<Interviewer> interviewers = new ArrayList<>();
	public ArrayList<Interview> interviewes = new ArrayList<>();
	private Unit xyzSoft = new Unit(0);

	public ObservableList<Candidate> getObservableCandidates() {
		ObservableList<Candidate> tmp = FXCollections.observableArrayList();
		tmp.addAll(candidates);
		return tmp;
	}

	public ObservableList<Interviewer> getObservableInterviewers() {
		ObservableList<Interviewer> tmp = FXCollections.observableArrayList();
		tmp.addAll(interviewers);
		return tmp;
	}

	public ObservableList<Interview> getObservableInterviewes() {
		ObservableList<Interview> tmp = FXCollections.observableArrayList();
		tmp.addAll(interviewes);
		return tmp;
	}

	public void linkWithUnit(Candidate candidate, Unit unit) {
		xyzSoft.addEmployee(candidate);
	}

	public void createInterview(Interview interview, Candidate candidate, Interviewer interviewer) {
		Interview newInterview = new Interview(interviewer.useDate(), interviewer, candidate);
		interviewes.add(newInterview);
		interviewer.addInterview(newInterview);
		candidate.addInterview(0, newInterview);
	}

	public JobOffer createJobOffer(Candidate candidate) {
		return new JobOffer(candidate);

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

	public boolean addCandidate(Candidate candidate) {
		if (candidates.contains(candidate))
			return false;
		candidates.add(candidate);
		return true;
	}

	public boolean removeCandidate(Candidate candidate) {
		if (!candidates.contains(candidate))
			return false;
		candidates.remove(candidate);
		return true;
	}

	public boolean addInterview(Interview interview) {

		interviewes.add(interview);
		return true;
	}

	public boolean removeInterview(Interview interview) {

		interviewes.remove(interview);

		return true;
	}

	public Unit getCompany() {
		return xyzSoft;
	}
}
