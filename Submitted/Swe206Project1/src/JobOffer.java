
import java.io.Serializable;
import java.util.Map;

public class JobOffer implements Serializable {
    private static Map<String, Double> jobs = Map.of("Program Manager", 14000.0, "Product Manager", 12000.0,
    "Senior Engineer", 14000.0, "Lead Engineer", 10000.0, "Engineer", 8000.0);
    private static Map<String, Double> levels = Map.of("Division", 1000.0, "Directorate", 500.0);
    private Candidate candidate;
    private String unit;
    private double base = 0, level = 0, expected = 0, min = 0, max = 0;

    public JobOffer(Candidate candidate){
        this.candidate = candidate;
        this.unit = candidate.getUnit();
    }

    public double[] getSalary(){
        jobs.forEach((n,d) -> {
            if(candidate.getPosition().equals(n))
                base = d;
        });
        levels.forEach((n,d) -> {
            if(unit.equals(n))
                level = d;
        });
        System.out.println(base + ", " + level);
        expected = (base + level) + 500 * candidate.getYearsOfExperience();
        min = Math.max((base+level), (base+level) + 500*(candidate.getYearsOfExperience()-2));
        max = (base+level) + 500*(candidate.getYearsOfExperience()+2);
        double[] array = {base, level, expected, min, max};
        return array;
    }

    public double[] getHouseTrans(double value){
        double[] array = {value * 0.25, value * 0.1};
        return array;
    }

}
