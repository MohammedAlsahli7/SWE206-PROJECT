import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Candidate implements Serializable {
	private String id = "";
	private String name = "";
	private String gender = "";
	private double yearsOfExperience = 0;
	private String educationLvl = "";
	private String position = "";
	private File cv;
	private ArrayList<Interview> interviewes = new ArrayList<>(Arrays.asList(null, null, null));
	private Boolean OfferingStage;
	private String unit = "";
	private double salary = 0;

	public ObservableList<Interview> getObservableInterviewes() {
		ObservableList<Interview> tmp = FXCollections.observableArrayList();
		tmp.addAll(interviewes);
		return tmp;
	}

	public ArrayList<Interview> getInterviews() {
		return interviewes;
	}

	public Candidate() {
		name = "";
		id = "";
		gender = "";
		yearsOfExperience = 0;
		educationLvl = "None";
		position = "None";
		unit = "None";

	}

	public Candidate(String name) {
		this();
		this.name = name;
	}

	public Candidate(String id, String name, String gender, double yearsOfExperience, String educationLvl,
			String position) {
		this(name);
		this.id = id;
		this.gender = gender;
		this.yearsOfExperience = yearsOfExperience;
		this.educationLvl = educationLvl;
		this.position = position;
	}

	// setters
	public void setId(String Id) {
		this.id = Id;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	public void setGender(String Gender) {
		this.gender = Gender;
	}

	public void setYearsOfExperience(double yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public void setEducationLvl(String EducationLvl) {
		this.educationLvl = EducationLvl;
	}

	public void setCv(File Cv) {
		this.cv = Cv;
	}

	public void setOfferingStage(boolean OfferingStage) {
		this.OfferingStage = OfferingStage;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setPosition(String positin) {
		this.position = positin;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	// getters
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public double getYearsOfExperience() {
		return yearsOfExperience;
	}

	public String getEducationLvl() {
		return educationLvl;
	}

	public File getCv() {
		return cv;
	}

	public Boolean getOfferingStage() {
		return OfferingStage;
	}
	
	public double getSalary() {
		return salary;
	}

	public String getUnit() {
		return unit;
	}

	public String getPosition() {
		return position;
	}

	// add/remove

	public boolean addInterview(int index, Interview interview) {
		if (interviewes.size() > 3)
			return false;
		interviewes.set(index, interview);
		return true;
	}

	public boolean removeInterview(Interview interview) {
		if (!interviewes.contains(interview))
			return false;
		interviewes.remove(interview);
		return true;
	}

	public Interview getInterview(int index) {
		
		if (!interviewes.isEmpty() && index < 3)
			return interviewes.get(index);
		return null;
	}

	public Interview getLastInterview() {
		if (!interviewes.isEmpty())
			return interviewes.get(interviewes.size() - 1);
		return null;
	}

	@Override
	public String toString() {
		return id + ", " + name;
	}

}
