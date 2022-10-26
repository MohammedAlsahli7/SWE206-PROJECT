

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class JobOfferingScene {

    // Main components
    protected TA ta;
    protected ObservableList<Candidate> list;
    protected ObservableList<Candidate> passCandidates;
    protected ComboBox<Candidate> passCandidatesCombo;
    private Text title = new Text("Job Offering");
    private Scene viewScene;

    // Additional 
    private int selected=0;
    ListView<Double> salary = new ListView<>();
    private VBox editVbox;
    
    // Text fields
    private TextField housingBenefit = new TextField();
    private TextField transBenefit = new TextField();
    
    // Labels
    private Label salaryLabel = new Label("chose salary:", salary);
    private Label done = new Label();
    private Label Housing_benefit = new Label("Housing benefit: ", housingBenefit);
    private Label Transportaion_benefit = new Label("Transportaion benefit: ", transBenefit);
    

    // Buttons
    private Button back = new Button("Back");
    private Button save = new Button("Save");
    
    
    public JobOfferingScene(Stage primaryStage, Scene scene, TA ta) {
        Housing_benefit.setVisible(false);
        Transportaion_benefit.setVisible(false);
        this.ta = ta;
        list = ta.getObservableCandidates();
        int ok = 0;
        for (int i = 0; i < list.size(); i++) { // adding pass candidates to combo box
            ok = 0;
            for (int j = 0; j < list.get(i).getInterviews().size(); j++) {
                if (list.get(i).getInterview(j) != null) {
                    if (list.get(i).getInterview(j).getStatus().equals("Pass")) {
                        ok=1;
                       break;
                    } else {
                        ok = 0;
                    }
                }
            }
            if (ok == 0) {
                list.remove(list.get(i));
                i--;
            }
        }

        passCandidatesCombo = new ComboBox<>(list);
        passCandidatesCombo.setPrefSize(450, 25);

        passCandidatesCombo.setOnAction(e -> {
            Candidate candidate = passCandidatesCombo.getValue();
            JobOffer jo = new JobOffer(candidate);
            double[] salaries = jo.getSalary();
            double min = salaries[3], max = salaries[4];
            ArrayList<Double> s = new ArrayList<>();
            salary.getItems().clear();
            for (double i = min; i <= max; i += 100) {
                s.add(i);
            }
            for (int i = 0; i < s.size(); i++) {
                salary.getItems().add(s.get(i));
            }
        });

        salary.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Double>() {
            @Override
            public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
                double selected_salary = salary.getSelectionModel().getSelectedItem();
                housingBenefit.setText(Double.toString(selected_salary * 0.25));
                housingBenefit.setEditable(false);
                transBenefit.setText(Double.toString(selected_salary * 0.1));
                transBenefit.setEditable(false);
                Housing_benefit.setVisible(true);
                Transportaion_benefit.setVisible(true);
                selected=1;
            }

        });

        HBox buttons = new HBox(30);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(25));
        buttons.getChildren().addAll(back, save);

        // Editing and viewing
        salaryLabel.setContentDisplay(ContentDisplay.RIGHT);
        salary.setPrefSize(250, 250);
        salary.getItems().addAll();
        salaryLabel.setContentDisplay(ContentDisplay.RIGHT);
        title.setFont(new Font(30));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setWrappingWidth(400);
        VBox editQVBox = new VBox(25);
        editQVBox.setPadding(new Insets(25));
        Housing_benefit.setContentDisplay(ContentDisplay.RIGHT);
        Transportaion_benefit.setContentDisplay(ContentDisplay.RIGHT);
        editQVBox.getChildren().addAll(title, passCandidatesCombo, salaryLabel, Housing_benefit, Transportaion_benefit);
        editVbox = new VBox(done, editQVBox, buttons);
        viewScene = new Scene(editVbox, 450, 600);

        done.setPrefSize(600, 25);
		done.setAlignment(Pos.CENTER);
		done.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent");
		Timeline anim = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
			done.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent");
		}));

        
        // Handler
        save.setOnAction(e -> {
            // save the selection
            if(selected==1)
            {
            	Candidate candidate = passCandidatesCombo.getValue();
            	double selected_salary = salary.getSelectionModel().getSelectedItem();
            	candidate.setSalary(selected_salary);
                done.setStyle("-fx-background-color: lime; -fx-text-fill: white");
                done.setText("Done!");
                anim.play();
            }
        });
        
        back.setOnAction(e -> {
            // go to primary scene
            primaryStage.setScene(scene);
        });
    }

    public void update(){
		list.clear();
		list.addAll(ta.getObservableCandidates());
        int ok = 0;
        for (int i = 0; i < list.size(); i++) { // adding pass candidates to combo box
            ok = 0;
            for (int j = 0; j < list.get(i).getInterviews().size(); j++) {
                if (list.get(i).getInterview(j) != null) {
                    if (list.get(i).getInterview(j).getStatus().equals("Pass")) {
                        ok=1;
                       break;
                    } else {
                        ok = 0;
                    }
                }
            }
            if (ok == 0) {
                list.remove(list.get(i));
                i--;
            }
        }
	}
    
    // Scene getter
    public Scene getScene() {
        return viewScene;
    }

}
