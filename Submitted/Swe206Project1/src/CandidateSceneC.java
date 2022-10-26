import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class CandidateSceneC {

	// Main components
	protected TA ta;
	protected ObservableList<Candidate> list;
	protected ComboBox<Candidate> candidatesCombo;
	private HashMap<String,Candidate> listHash = new HashMap<>();
	private Text title = new Text("Candidate");
	private Scene viewScene;

	// Additional
	private ComboBox<String> educationLvl = new ComboBox<>();
	private ComboBox<String> position = new ComboBox<>();
	private ComboBox<String> gender = new ComboBox<>();
	private ComboBox<String> unit = new ComboBox<>();
	private VBox editVbox;
	private Candidate temp = new Candidate("The List Is Empty, please add a candidate first.");

	// Text fields
	private TextField name = new TextField();
	private TextField id = new TextField();
	private TextField yearsOfExperience = new TextField();
	private TextField offering = new TextField();
	private TextField salary = new TextField();

	// Labels
	private Label nameLabel = new Label("Name", name);
	private Label idLabel = new Label("ID:", id);
	private Label genderLabel = new Label("Gender:", gender);
	private Label yearsOfExperienceLabel = new Label("Years Of Experience:", yearsOfExperience);
	private Label unitLabel = new Label("Unit:", unit);
	private Label educationLvlLabel = new Label("Education Level:", educationLvl);
	private Label positionLabel = new Label("Education Level:", position);
	private Label salaryLabel = new Label("Salary:", salary);
	private Label offeringLabel = new Label("Offering Stage:", offering);
	private Label error = new Label();

	// Buttons
	private Button back = new Button("Back");
	private Button add = new Button("Add");
	private Button save = new Button("Save");
	protected Button edit = new Button("Edit");
	protected Button delete = new Button("Delete");
	private Button yes = new Button("Yes");
	private Button no = new Button("No");

	public CandidateSceneC(Stage primaryStage, Scene scene, TA ta) {

		this.ta = ta;
		list = ta.getObservableCandidates();
		list.forEach(n -> {
			listHash.put(n.getId(), n);
		});
		candidatesCombo = new ComboBox<>(list);
		candidatesCombo.setPrefSize(500, 450);
		if(list.isEmpty()){
			candidatesCombo.setValue(temp);
			edit.setDisable(true);
			delete.setDisable(true);
		} else candidatesCombo.setValue(list.get(list.size()-1));
		HBox buttons = new HBox(30);
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(25));
		buttons.getChildren().addAll(back, add, save, edit, delete);

		// Editing and viewing
		salaryLabel.setContentDisplay(ContentDisplay.RIGHT);
		salary.setPrefSize(250, 25);
		salary.setDisable(true);
		offeringLabel.setContentDisplay(ContentDisplay.RIGHT);
		offering.setPrefSize(250, 25);
		offering.setDisable(true);
		nameLabel.setContentDisplay(ContentDisplay.RIGHT);
		name.setPrefSize(300, 25);
		idLabel.setContentDisplay(ContentDisplay.RIGHT);
		id.setPrefSize(250, 25);
		genderLabel.setContentDisplay(ContentDisplay.RIGHT);
		gender.setPrefSize(250, 25);
		yearsOfExperienceLabel.setContentDisplay(ContentDisplay.RIGHT);
		yearsOfExperience.setPrefSize(250, 25);
		unitLabel.setContentDisplay(ContentDisplay.RIGHT);
		unit.setPrefSize(250, 25);
		gender.getItems().addAll("Male", "Female");
		genderLabel.setContentDisplay(ContentDisplay.RIGHT);
		unit.getItems().addAll("Division", "Directorate", "Department");
		unitLabel.setContentDisplay(ContentDisplay.RIGHT);
		educationLvl.getItems().addAll("Elementry School", "High School", "Diploma", "Bechelor", "Master", "Doctorate");
		educationLvlLabel.setContentDisplay(ContentDisplay.RIGHT);
		position.getItems().addAll("Program Manager", "Product Manager", "Senior Engineer", "Lead Engineer", "Engineer");
		positionLabel.setContentDisplay(ContentDisplay.RIGHT);
		title.setFont(new Font(30));
		title.setTextAlignment(TextAlignment.CENTER);
		title.setWrappingWidth(400);
		VBox editQVBox = new VBox(25);
		editQVBox.setPadding(new Insets(25));
		editQVBox.getChildren().addAll(title, candidatesCombo, nameLabel, idLabel, genderLabel,
				yearsOfExperienceLabel, unitLabel, educationLvlLabel, positionLabel, offeringLabel, salaryLabel);
		editVbox = new VBox(error, editQVBox, buttons);
		viewScene = new Scene(editVbox, 450, 675);

		// Styling
		error.setPrefSize(450, 25);
		error.setAlignment(Pos.CENTER);
		error.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent");
		Timeline anim = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
			error.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent");
		}));

		// Delete
		Stage sure = new Stage();
		Text sureD = new Text("Are you sure you want to delete this candidate?");
		HBox deleteHbox = new HBox();
		VBox deleteVbox = new VBox();
		deleteHbox.setSpacing(25);
		deleteVbox.setSpacing(25);
		deleteHbox.setAlignment(Pos.CENTER);
		deleteVbox.setAlignment(Pos.CENTER);
		deleteHbox.getChildren().addAll(yes, no);
		deleteVbox.getChildren().addAll(sureD, deleteHbox);
		Scene deleteScene = new Scene(deleteVbox, 275, 100);

		// locking to view mode
		lock(true);

		// Handlers

		candidatesCombo.setOnAction(e -> {
			// if the value of the combo box changed it automatically lock to view mode
			// and if there is no Candidates it lock all nodes except back button
			Candidate candidate = candidatesCombo.getValue();
			if (candidate == null || candidate ==  temp || candidate.getName().equals("")){
				edit.setDisable(true);
				delete.setDisable(true);
			} else {
				edit.setDisable(false);
				delete.setDisable(false);
			}
			lock(true);

		});

		back.setOnAction(e -> {
			// return to the main scene or when editing return to view scene
			if (back.getText().equals("Back"))
				primaryStage.setScene(scene);
			else {
				lock(true);
				edit.setDisable(false);
				add.setDisable(false);
			}});

		edit.setOnAction(e -> {
			lock(false);
			edit.setDisable(true);
		});

		add.setOnAction(e -> {
			// adds a new Candidate
			list.add(new Candidate());
			candidatesCombo.setValue(list.get(list.size()-1));
			lock(false);
			edit.setDisable(true);
			delete.setDisable(true);
			add.setDisable(true);

			
		});

		save.setOnAction(e -> {
			// saves the changes
			error.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent");
			if (!(name.getText().equals("") || id.getText().equals("")
					|| gender.getValue().equals("None") || yearsOfExperience.getText().equals("")
					|| unit.getValue().equals("None") || educationLvl.getValue().equals("None") 
					|| position.getValue().equals("None"))) {
				if(id.getText().length() != 9 || !isDigit(id.getText())){
					error.setStyle("-fx-background-color: red; -fx-text-fill: white");
					error.setText("ID must be exactly 9 digits");
					anim.play();
					return;
				}
				if(listHash.containsKey(id.getText()) && !candidatesCombo.getValue().equals(listHash.get(id.getText()))){
					error.setStyle("-fx-background-color: red; -fx-text-fill: white");
					error.setText("ID must be unique");
					anim.play();
					return;
				}
				Candidate c = candidatesCombo.getValue();
				String oldId = c.getId();
				c.setId(id.getText());
				c.setName(name.getText());
				c.setGender(gender.getValue());
				c.setPosition(position.getValue());
				c.setYearsOfExperience(Double.parseDouble("0"+yearsOfExperience.getText()));
				c.setEducationLvl(educationLvl.getValue());
				c.setUnit(unit.getValue());
				if(candidatesCombo.getValue().getName().equals("")){
					list.add(c);
					listHash.put(c.getId(), c);
				} else {
					listHash.remove(oldId);
					listHash.put(id.getText(), c);
				}
				error.setStyle("-fx-background-color: lime; -fx-text-fill: white");
				error.setText("Done!");
				anim.play();
				edit.setDisable(false);
				save.setDisable(true);
				delete.setDisable(false);
				add.setDisable(false);
				ta.addCandidate(c);
				name.clear();
				id.clear();
				gender.setValue("None");
				educationLvl.setValue("None");
				position.setValue("None");
				unit.setValue("None");
				yearsOfExperience.clear();
				candidatesCombo.setItems(list);
				lock(true);
				if(list.contains(temp))
					list.remove(temp);
				

			} else {
				error.setStyle("-fx-background-color: red; -fx-text-fill: white");
				error.setText("Fill all the blanks please!");
				anim.play();

			}
		});

		delete.setOnAction(e -> {
			// deletes a question
			error.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent");
			lock(true);
			candidatesCombo.setDisable(true);
			back.setDisable(true);
			delete.setDisable(true);
			edit.setDisable(true);
			sure.setScene(deleteScene);
			sure.setTitle("Are you sure?");
			sure.setResizable(false);
			if (!sure.isShowing())
				sure.show();
			else
				sure.setScene(deleteScene);
		});

		// ------- Delete handlers
		// making sure of the decision
		yes.setOnAction(e -> {
			// delete the question
			Candidate c = candidatesCombo.getValue();
			if ((list.size() <= 1 && !list.contains(temp)))
				list.add(0, temp);
			list.remove(c);
			listHash.remove(c.getId());
			ta.removeCandidate(c);
			candidatesCombo.setItems(list);
			error.setStyle("-fx-background-color: lime; -fx-text-fill: white");
			error.setText("Done!");
			anim.play();
			sure.close();
			candidatesCombo.setDisable(false);
			back.setDisable(false);
			delete.setDisable(false);
			edit.setDisable(false);
		});

		no.setOnAction(e -> {
			// do not delete the question
			sure.close();
			candidatesCombo.setDisable(false);
			back.setDisable(false);
			delete.setDisable(false);
			edit.setDisable(false);
		});
		
		sure.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				event.consume();
				candidatesCombo.setDisable(false);
				back.setDisable(false);
				delete.setDisable(false);
				edit.setDisable(false);
				sure.close();
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
		name.setDisable(x);
		id.setDisable(x);
		gender.setDisable(x);
		yearsOfExperience.setDisable(x);
		unit.setDisable(x);
		educationLvl.setDisable(x);
		position.setDisable(x);

		if (list.size() == 0 || candidatesCombo.getValue() == null) {
			name.setText("");
			id.setText("");
			gender.setValue("None");
			yearsOfExperience.setText("");
			unit.setValue("None");
			educationLvl.setValue("None");
			position.setValue("None");
			salary.setText("");
			offering.setText("");
		} else {
			Candidate c = candidatesCombo.getValue();
			name.setText(c.getName());
			id.setText(c.getId());
			gender.setValue(c.getGender());
			yearsOfExperience.setText(c.getYearsOfExperience()+"");
			unit.setValue(c.getUnit());
			educationLvl.setValue(c.getEducationLvl());
			position.setValue(c.getPosition());
			salary.setText("" + (c.getSalary() != 0? c.getSalary() : "None"));
			offering.setText("" + c.getOfferingStage());
		}
	}
		public boolean isDigit(String s){
			for(int i = 0; i < s.length(); i++)
				if(!Character.isDigit((s.charAt(i))))
					return false;
			return true;
		}

		public void update(){
			list.clear();
			list.addAll(ta.getObservableCandidates());
			if(list.isEmpty() || list.get(list.size()-1).getName().equals("")){
				candidatesCombo.setValue(temp);
				edit.setDisable(true);
				delete.setDisable(true);
			} else candidatesCombo.setValue(list.get(list.size()-1));
		}

		// Scene getter
		public Scene getScene() {
			return viewScene;
		}


}
