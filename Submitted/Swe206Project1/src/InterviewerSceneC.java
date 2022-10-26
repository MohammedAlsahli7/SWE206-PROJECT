import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class InterviewerSceneC {

}

// ============================================ Calendar Scene ============================================
class Calendar {
	// Main components
	protected TA ta;
	protected static ObservableList<Interview> list;
	protected ComboBox<Interview> interviewsCombo;
	private Text title = new Text("Calendar");
	private Scene viewScene;

	// Additional
	private ComboBox<String> educationLvl = new ComboBox<>();
	private ComboBox<String> position = new ComboBox<>();
	private ComboBox<String> gender = new ComboBox<>();
	private ComboBox<String> unit = new ComboBox<>();
	private Candidate temp = new Candidate("The List Is Empty, please add an interview first.");
	private Label comboLabel = new Label("Interviews (Date, Candidate):", interviewsCombo);
	private BorderPane root;

	// Text fields Candidate
	private TextField name = new TextField();
	private TextField id = new TextField();
	private TextField yearsOfExperience = new TextField();

	// Text fields Interviewer
	private TextField nameI = new TextField();
	private TextField idI = new TextField();
	private TextField date = new TextField();
	private ComboBox<String> status = new ComboBox<>();
	

	// Labels Candidate
	private Label nameLabel = new Label("Name: ", name);
	private Label idLabel = new Label("ID:", id);
	private Label genderLabel = new Label("Gender:", gender);
	private Label yearsOfExperienceLabel = new Label("Years Of Experience:", yearsOfExperience);
	private Label unitLabel = new Label("Unit:", unit);
	private Label educationLvlLabel = new Label("Education Level:", educationLvl);
	private Label positionLabel = new Label("Education Level:", position);

	// Labels Interviewer
	private Label nameLabelI = new Label("Interviewer Name: ", nameI);
	private Label idLabelI = new Label("Interviewer ID: ", idI);
	private Label dateLabel = new Label("Date: ", date);
	private Label statusLabel = new Label("Status: ", status);
	private Label lastInterviewLabel = new Label("Last Interview Info: ");

	// Buttons
	private Button back = new Button("Back");

	public Calendar(Stage primaryStage, Scene scene, TA ta) {
		this.ta = ta;
		list = ta.getObservableInterviewes();
		interviewsCombo = new ComboBox<>(list);
		interviewsCombo.setPrefSize(300, 30);

		if (list.isEmpty()) {
			interviewsCombo.setValue(new Interview("", null, temp));

		} else
			interviewsCombo.setValue(list.get(0));

		HBox buttons = new HBox(30);
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(1));
		buttons.getChildren().add(back);

		// Editing and viewing for Candidate info
		nameLabel.setContentDisplay(ContentDisplay.RIGHT);
		name.setPrefSize(300, 25);
		name.setDisable(true);
		idLabel.setContentDisplay(ContentDisplay.RIGHT);
		id.setPrefSize(250, 25);
		id.setDisable(true);
		genderLabel.setContentDisplay(ContentDisplay.RIGHT);
		gender.setPrefSize(250, 25);
		yearsOfExperienceLabel.setContentDisplay(ContentDisplay.RIGHT);
		yearsOfExperience.setPrefSize(250, 25);
		yearsOfExperience.setDisable(true);
		unitLabel.setContentDisplay(ContentDisplay.RIGHT);
		unit.setPrefSize(250, 25);
		unit.setDisable(true);
		genderLabel.setContentDisplay(ContentDisplay.RIGHT);
		gender.setDisable(true);
		unitLabel.setContentDisplay(ContentDisplay.RIGHT);
		educationLvlLabel.setContentDisplay(ContentDisplay.RIGHT);
		educationLvl.setDisable(true);
		positionLabel.setContentDisplay(ContentDisplay.RIGHT);
		position.setDisable(true);

		// Editing and viewing for Candidate info
		nameLabelI.setContentDisplay(ContentDisplay.RIGHT);
		nameI.setPrefSize(300, 25);
		nameI.setDisable(true);
		idLabelI.setContentDisplay(ContentDisplay.RIGHT);
		idI.setPrefSize(250, 25);
		idI.setDisable(true);
		dateLabel.setContentDisplay(ContentDisplay.RIGHT);
		date.setPrefSize(250, 25);
		date.setDisable(true);
		statusLabel.setContentDisplay(ContentDisplay.RIGHT);
		status.setPrefSize(250, 25);
		status.setDisable(true);

		title.setFont(new Font(30));
		title.setTextAlignment(TextAlignment.CENTER);
		title.setWrappingWidth(400);

		// Candidate VBox
		VBox editQVBox = new VBox(25);
		editQVBox.setPadding(new Insets(25));
		editQVBox.getChildren().addAll(nameLabel, idLabel, genderLabel, yearsOfExperienceLabel, unitLabel,
				educationLvlLabel, positionLabel);
		// Interviewer VBox
		VBox editQVBoxI = new VBox(25);
		editQVBoxI.setPadding(new Insets(25));
		editQVBoxI.getChildren().addAll(lastInterviewLabel, nameLabelI, idLabelI, dateLabel, statusLabel);
		VBox topVBox = new VBox();

		topVBox.getChildren().addAll(title, comboLabel, interviewsCombo);
		topVBox.setAlignment(Pos.CENTER);
		GridPane gridPane = new GridPane();
		gridPane.add(editQVBox, 0, 0);
		gridPane.add(editQVBoxI, 1, 0);
		root = new BorderPane();
		root.setTop(topVBox);
		root.setCenter(gridPane);
		root.setBottom(buttons);
		viewScene = new Scene(root, 900, 500);

		back.setOnAction(e -> {
			primaryStage.setScene(scene);
		});

		lock(true);

		interviewsCombo.setOnAction(e -> {
			// if the value of the combo box changed it automatically lock to view mode
			// and if there is no Candidates it lock all nodes except back button

			lock(true);

		});
	}

	// Locking/unlocking the text fields and buttons
	public void lock(Boolean x) {
		interviewsCombo.setItems(list);

		if (x) {
			back.setText("Back");
		} else {
			back.setText("Cancel");
		}

		nameI.setDisable(x);
		idI.setDisable(x);
		date.setDisable(x);
		status.setDisable(x);
	
		if (interviewsCombo.getValue() == null || interviewsCombo.getValue().getInterviewer() == null) {
			name.setText("None");
			nameI.setText("None");
			idI.setText("None");
			date.setText("None");
			status.setValue("None");
			id.setText("None");
			gender.setValue(null);
			yearsOfExperience.setText(null);
			unit.setValue(null);
			educationLvl.setValue(null);
			position.setValue(null);

		} else {
			name.setText(interviewsCombo.getValue().getCandidate().getName());
			id.setText(interviewsCombo.getValue().getCandidate().getId());
			gender.setValue(interviewsCombo.getValue().getCandidate().getGender());
			yearsOfExperience.setText(interviewsCombo.getValue().getCandidate().getYearsOfExperience() + "");
			unit.setValue(interviewsCombo.getValue().getCandidate().getUnit());
			educationLvl.setValue(interviewsCombo.getValue().getCandidate().getEducationLvl());
			position.setValue(interviewsCombo.getValue().getCandidate().getPosition());
			nameI.setText(interviewsCombo.getValue().getInterviewer().getName());
			idI.setText(interviewsCombo.getValue().getInterviewer().getId());
			date.setText(interviewsCombo.getValue().getDate());
			status.setValue(interviewsCombo.getValue().getStatus());

		}
	}

	// Scene getter
	public Scene getScene() {
		return viewScene;
	}
}

// ============================================ Interviews Scene ============================================
class Interviews {
	// Main components
	protected TA ta;
	protected ComboBox<Candidate> candidatesCombo;
	private ObservableList<Candidate> list;
	private Text title = new Text("Interviews");
	private Scene viewScene;

	// Additional
	private ComboBox<String> educationLvl = new ComboBox<>();
	private ComboBox<String> position = new ComboBox<>();
	private ComboBox<String> gender = new ComboBox<>();
	private ComboBox<String> unit = new ComboBox<>();
	private Candidate temp = new Candidate("The List Is Empty, please add a candidate first.");
	private BorderPane root;
	private Label comboLabel = new Label("Candidates (ID, Name):", candidatesCombo);

	// Text fields Candidate
	private TextField name = new TextField();
	private TextField id = new TextField();
	private TextField yearsOfExperience = new TextField();

	// Text fields Interviewer
	private TextField nameI = new TextField();
	private TextField idI = new TextField();
	private TextField date = new TextField();
	private ComboBox<String> status = new ComboBox<>();
	private ComboBox<String> interviewNum = new ComboBox<>();

	// Labels Candidate
	private Label nameLabel = new Label("Name: ", name);
	private Label idLabel = new Label("ID:", id);
	private Label genderLabel = new Label("Gender:", gender);
	private Label yearsOfExperienceLabel = new Label("Years Of Experience:", yearsOfExperience);
	private Label unitLabel = new Label("Unit:", unit);
	private Label educationLvlLabel = new Label("Education Level:", educationLvl);
	private Label positionLabel = new Label("Education Level:", position);

	// Labels Interviewer
	private Label nameLabelI = new Label("Interviewer Name: ", nameI);
	private Label idLabelI = new Label("Interviewer ID: ", idI);
	private Label dateLabel = new Label("Date: ", date);
	private Label statusLabel = new Label("Status: ", status);
	private Label interviewNumLabel = new Label("Interview Number: ", interviewNum);

	// Buttons
	private Button back = new Button("Back");
	private Button save = new Button("Save");
	protected Button edit = new Button("Edit");

	public Interviews(Stage primaryStage, Scene scene, TA ta) {
		this.ta = ta;
		list = ta.getObservableCandidates();
		candidatesCombo = new ComboBox<>(list);
		candidatesCombo.setPrefSize(300, 30);
		if (list.isEmpty()) {
			candidatesCombo.setValue(temp);
			edit.setDisable(true);
		} else
			candidatesCombo.setValue(list.get(list.size() - 1));
		HBox buttons = new HBox(30);
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(25));
		buttons.getChildren().addAll(back, save, edit);

		// Editing and viewing for Candidate info
		nameLabel.setContentDisplay(ContentDisplay.RIGHT);
		name.setPrefSize(300, 25);
		name.setDisable(true);
		idLabel.setContentDisplay(ContentDisplay.RIGHT);
		id.setPrefSize(250, 25);
		id.setDisable(true);
		genderLabel.setContentDisplay(ContentDisplay.RIGHT);
		gender.setPrefSize(250, 25);
		yearsOfExperienceLabel.setContentDisplay(ContentDisplay.RIGHT);
		yearsOfExperience.setPrefSize(250, 25);
		yearsOfExperience.setDisable(true);
		genderLabel.setContentDisplay(ContentDisplay.RIGHT);
		gender.setDisable(true);
		unitLabel.setContentDisplay(ContentDisplay.RIGHT);
		unit.setPrefSize(250, 25);
		unit.setDisable(true);
		educationLvlLabel.setContentDisplay(ContentDisplay.RIGHT);
		educationLvl.setDisable(true);
		positionLabel.setContentDisplay(ContentDisplay.RIGHT);
		position.setDisable(true);

		// Editing and viewing for Candidate info
		nameLabelI.setContentDisplay(ContentDisplay.RIGHT);
		nameI.setPrefSize(300, 25);
		nameI.setDisable(true);
		idLabelI.setContentDisplay(ContentDisplay.RIGHT);
		idI.setPrefSize(250, 25);
		idI.setDisable(true);
		dateLabel.setContentDisplay(ContentDisplay.RIGHT);
		date.setPrefSize(250, 25);
		date.setDisable(true);
		statusLabel.setContentDisplay(ContentDisplay.RIGHT);
		status.setPrefSize(250, 25);
		status.setDisable(true);
		status.getItems().addAll("Pass", "Fail", "Hold");
		interviewNumLabel.setContentDisplay(ContentDisplay.RIGHT);
		interviewNum.setPrefSize(250, 25);
		interviewNum.setDisable(true);
		interviewNum.getItems().add("First Interview");
		interviewNum.setValue("First Interview");

		title.setFont(new Font(30));
		title.setTextAlignment(TextAlignment.CENTER);
		title.setWrappingWidth(400);

		// Candidate VBox
		VBox editQVBox = new VBox(25);
		editQVBox.setPadding(new Insets(25));
		editQVBox.getChildren().addAll(nameLabel, idLabel, genderLabel, yearsOfExperienceLabel, unitLabel,
				educationLvlLabel, positionLabel);
		// Interviewer VBox
		VBox editQVBoxI = new VBox(25);
		editQVBoxI.setPadding(new Insets(25));
		editQVBoxI.getChildren().addAll(nameLabelI, idLabelI, dateLabel, statusLabel, interviewNumLabel);

		VBox topVBox = new VBox();
		topVBox.setAlignment(Pos.CENTER);
		topVBox.getChildren().addAll(title, comboLabel, candidatesCombo);

		GridPane gridPane = new GridPane();
		gridPane.add(editQVBox, 0, 0);
		gridPane.add(editQVBoxI, 1, 0);
		root = new BorderPane();
		root.setTop(topVBox);
		root.setCenter(gridPane);
		root.setBottom(buttons);
		viewScene = new Scene(root, 900, 525);

		// locking to view mode
		lock(true);

		candidatesCombo.setOnAction(e -> {
			// if the value of the combo box changed it automatically lock to view mode
			// and if there is no Candidates it lock all nodes except back button
			Candidate candidate = candidatesCombo.getValue();
			if (candidate == null || candidate == temp || candidate.getName().equals("")) {
				edit.setDisable(true);
			} else {
				edit.setDisable(false);
			}
			lock(true);

		});

		interviewNum.setOnAction(e -> {
			int whichInterview = interviewNum.getSelectionModel().getSelectedIndex();

			if (candidatesCombo.getValue().getInterview(whichInterview) != null
					&& candidatesCombo.getValue().getInterview(whichInterview).getStatus().equals("Hold")) {

				nameI.setText("None");
				idI.setText("None");
				date.setText("None");
				status.setValue("None");

			}
			lock(false);
		});

		back.setOnAction(e -> {
			// return to the main scene or when editing return to view scene
			if (back.getText().equals("Back"))
				primaryStage.setScene(scene);
			else {
				lock(true);
				edit.setDisable(false);
			}});

		edit.setOnAction(e -> {
			lock(false);
			edit.setDisable(true);
		});

		save.setOnAction(e -> {
			// saves the changes
			int whichInterview = interviewNum.getSelectionModel().getSelectedIndex();
			if (!(nameI.getText().equals("") || idI.getText().equals("") || date.getText().equals("")
					|| status.getValue() == null)) {
				Interviewer interviewerO = new Interviewer(nameI.getText(), idI.getText());
				Interview interview = new Interview(date.getText(), interviewerO, candidatesCombo.getValue());
				candidatesCombo.getValue().addInterview(whichInterview, interview);

				if (ta.getObservableInterviewes().size() == 0 || Calendar.list.isEmpty()) {
					ta.addInterview(interview);
					Calendar.list.add(interview);
				}

				else {
					ta.removeInterview(interview);
					ta.addInterview(interview);
					Calendar.list.remove(interview);
					Calendar.list.add(interview);
				}

				interviewerO.decide(interview, status.getValue());
				edit.setDisable(false);
				save.setDisable(true);
				nameI.setDisable(true);
				idI.setDisable(true);
				date.setDisable(true);
				status.setDisable(true);
//				interviewNum.setDisable(true);
				lock(true);
				if (list.contains(temp))
					list.remove(temp);

			} else {

			}
		});
	}

	// Locking/unlocking the text fields and buttons
	public void lock(Boolean x) {
		candidatesCombo.setItems(list);

		if (x) {
			back.setText("Back");
		} else {
			back.setText("Cancel");
		}

		save.setDisable(x);
		nameI.setDisable(x);
		idI.setDisable(x);
		date.setDisable(x);
		status.setDisable(x);

		int whichInterview = interviewNum.getSelectionModel().getSelectedIndex();
		if(candidatesCombo.getValue() != null){
			name.setText(candidatesCombo.getValue().getName());
			id.setText(candidatesCombo.getValue().getId());
			gender.setValue(candidatesCombo.getValue().getGender());
			yearsOfExperience.setText(candidatesCombo.getValue().getYearsOfExperience() + "");
			unit.setValue(candidatesCombo.getValue().getUnit());
			educationLvl.setValue(candidatesCombo.getValue().getEducationLvl());
			position.setValue(candidatesCombo.getValue().getPosition());
		} if (candidatesCombo.getValue() == null || candidatesCombo.getValue().getInterview(whichInterview) == null) {
			nameI.setText("None");
			idI.setText("None");
			date.setText("None");
			status.setValue("None");
		} else {
			nameI.setText(candidatesCombo.getValue().getInterview(whichInterview).getInterviewer().getName());
			idI.setText(candidatesCombo.getValue().getInterview(whichInterview).getInterviewer().getId());
			date.setText(candidatesCombo.getValue().getInterview(whichInterview).getDate());
			status.setValue(candidatesCombo.getValue().getInterview(whichInterview).getStatus());
			if (status.getValue().equals("Hold")) {
				if (!interviewNum.getItems().contains("Second Interview") && whichInterview == 0)
					interviewNum.getItems().add("Second Interview");
				if (!interviewNum.getItems().contains("Third Interview") && whichInterview == 1)
					interviewNum.getItems().add("Third Interview");
				interviewNum.setDisable(false);
			} else
				interviewNum.setDisable(true);
		}
	}

	public void update(){
		list.clear();
		list.addAll(ta.getObservableCandidates());
		lock(true);
		if(list.isEmpty()){
			candidatesCombo.setValue(temp);
			edit.setDisable(true);
		} else {
			candidatesCombo.setValue(list.get(list.size()-1));
			edit.setDisable(false);
		}
	}

	// Scene getter
	public Scene getScene() {
		return viewScene;
	}
}
