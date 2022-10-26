import java.io.Serializable;
import java.util.*;

import javafx.collections.*;

public class Unit  implements Serializable, Comparable<Candidate>  {
    private static final long serialVersionUID = 1L;
    private static String[] types = {"Company", "Division", "Directorate", "Department"};
    private String name;
    private String type;
    private ArrayList<Unit> subUnits = new ArrayList<>();
    private ArrayList<String> jobBands = new ArrayList<>();
    private ArrayList<Candidate> employees = new ArrayList<>();
    private ArrayList<String> jobs = new ArrayList<String>();

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Unit> getObservableSubUnits(){
        ObservableList<Unit> tmp = FXCollections.observableArrayList();
        tmp.addAll(subUnits);
        return tmp;
    }
    
    public ObservableList<String> getObservableJobBands(){
        ObservableList<String> tmp = FXCollections.observableArrayList();
        tmp.addAll(jobBands);
        return tmp;
    }
    
    public ObservableList<Candidate> getObservableEmployees(){
        ObservableList<Candidate> tmp = FXCollections.observableArrayList();
        tmp.addAll(employees);
        return tmp;
    }
    
    public ObservableList<String> getObservableJobs(){
        ObservableList<String> tmp = FXCollections.observableArrayList();
        tmp.addAll(jobs);
        return tmp;
    }


    public Unit (int typeIndex){
        this.type = types[typeIndex];
    }

    public Unit(String type){
        this.type = type;
    }

    public Unit(int typeIndex, String name){
        this.type = types[typeIndex];
        this.name = name;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    public ArrayList<Unit> getSubUnits(){
        return subUnits;
    }
    public boolean addSubUnit(Unit u){
        if(subUnits.contains(u))
            return false;
        subUnits.add(u);
        return true;
    }
    public boolean deleteSubUnit(Unit u){
        if(!subUnits.contains(u))
            return false;
        subUnits.remove(u);
        return true;
    }

    public ArrayList<String> getJobBands(){
        return jobBands;
    }
    public boolean addJobBand(String s){
        if(jobBands.contains(s))
            return false;
        jobBands.add(s);
        return true;
    }
    public boolean removeJobBand(String s){
        if(!jobBands.contains(s))
            return false;
        jobBands.remove(s);
        return true;
    }

    public ArrayList<Candidate> getEmployees(){
        return employees;
    }
    public boolean addEmployee(Candidate c){
        if(employees.contains(c))
            return false;
        employees.add(c);
        return true;
    }
    public boolean removeEmployee(Candidate c){
        if(!employees.contains(c))
            return false;
        employees.remove(c);
        return true;
    }

    public ArrayList<String> getJobs(){
        return jobBands;
    }
    public boolean addJob(String s){
        if(jobBands.contains(s))
            return false;
        jobBands.add(s);
        return true;
    }
    public ObservableList<String> generateJobs(){
        jobs.clear();
        jobBands.forEach(n -> {
            if(n.equals("Project Management")){
                jobs.add("Program Manager");
                jobs.add("Product Manager");
            } else if(n.equals("Engineering")){
                jobs.add("Senior Engineer");
                jobs.add("Lead Engineer");
                jobs.add("Engineer");
            }
        });
        ObservableList<String> tmp = FXCollections.observableArrayList();
        tmp.addAll(jobs);
        return tmp;
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int compareTo(Candidate o) {
        return name.compareTo(o.getName());
    }

}
