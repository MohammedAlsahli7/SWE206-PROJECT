import java.io.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ProjectMain extends Application {
	TA ta;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		ReadObjectFromFile();

		// General scene
		HBox genOptions = new HBox();
		Button tAButton = new Button("Talent Acquisition");
		Button interviewerButton = new Button("Interviewer");
		genOptions.getChildren().addAll(tAButton, interviewerButton);
		genOptions.setPadding(new Insets(15));
		genOptions.setSpacing(25);
		genOptions.setAlignment(Pos.CENTER);
		Text genTitle = new Text("XYZSoft Recruiting Program");
		genTitle.setFont(new Font(30));
		genTitle.setTextAlignment(TextAlignment.CENTER);
		genTitle.setWrappingWidth(400);

		genTitle.setOnMouseEntered(n ->{
			genTitle.setFill(Color.RED);
		});
		genTitle.setOnMouseExited(n ->{
			
			genTitle.setFill(Color.BLACK);
		});
		
		// Main stage
		BorderPane genBorder = new BorderPane();
		VBox genOptionsVbox = new VBox(genTitle, genOptions);
		genOptionsVbox.setAlignment(Pos.BASELINE_CENTER);
		genBorder.setPadding(new Insets(10));
		genBorder.setCenter(genOptionsVbox);
		Scene genScene = new Scene(genBorder, 450, 125);
		primaryStage.setScene(genScene);
		primaryStage.setTitle("XYZSoft Recruiting Program");
		primaryStage.setResizable(false);
		primaryStage.show();

		// TA scene
		HBox taOptions = new HBox();
		HBox backHBox = new HBox();
		Button candidateButton = new Button("Candidates");
		Button unitButton = new Button("Hierarchy");
		Button jobofferButton = new Button("Job Offers");
		Button back = new Button("Back");

		taOptions.getChildren().addAll(candidateButton, unitButton, jobofferButton);
		taOptions.setPadding(new Insets(15));
		taOptions.setSpacing(25);
		taOptions.setAlignment(Pos.CENTER);
		backHBox.getChildren().add(back);
		back.setAlignment(Pos.CENTER_LEFT);
		backHBox.setPadding(new Insets(15));
		backHBox.setSpacing(25);
		Text taTitle = new Text("Talent Acquisition");
		taTitle.setFont(new Font(30));
		taTitle.setTextAlignment(TextAlignment.CENTER);
		taTitle.setWrappingWidth(400);

		BorderPane taBorder = new BorderPane();
		VBox taOptionsVbox = new VBox(taTitle, taOptions, backHBox);
		taOptionsVbox.setAlignment(Pos.BASELINE_CENTER);
		taBorder.setPadding(new Insets(10));
		taBorder.setCenter(taOptionsVbox);
		Scene taScene = new Scene(taBorder, 450, 170);

		// Interviewer scene
		HBox interviewerOptions = new HBox();
		HBox backHBox2 = new HBox();
		Button calendarButton = new Button("Calendar");
		Button InterviewsButton = new Button("Interviews");
		Button back2 = new Button("Back");

		interviewerOptions.getChildren().addAll(calendarButton, InterviewsButton);
		interviewerOptions.setPadding(new Insets(15));
		interviewerOptions.setSpacing(25);
		interviewerOptions.setAlignment(Pos.CENTER);
		backHBox2.getChildren().add(back2);
		back2.setAlignment(Pos.CENTER_LEFT);
		backHBox2.setPadding(new Insets(15));
		backHBox2.setSpacing(25);
		Text InterviewerTitle = new Text("Interviewer");
		InterviewerTitle.setFont(new Font(30));
		InterviewerTitle.setTextAlignment(TextAlignment.CENTER);
		InterviewerTitle.setWrappingWidth(400);

		BorderPane interviewerBorder = new BorderPane();
		VBox interviewerOptionsVbox = new VBox(InterviewerTitle, interviewerOptions, backHBox2);
		interviewerOptionsVbox.setAlignment(Pos.BASELINE_CENTER);
		interviewerBorder.setPadding(new Insets(10));
		interviewerBorder.setCenter(interviewerOptionsVbox);
		Scene interviewerScene = new Scene(interviewerBorder, 450, 170);

		

		taTitle.setOnMouseEntered(n ->{
			taTitle.setFill(Color.RED);
		});
		taTitle.setOnMouseExited(n ->{
			
			taTitle.setFill(Color.BLACK);
		});


		// Candidate scene
		CandidateSceneC candidateSceneO = new CandidateSceneC(primaryStage, taScene, ta);
		Scene candidateScene = candidateSceneO.getScene(); 

		// Hierarchy Scene
		HierarchySceneC HierarchySceneO = new HierarchySceneC(primaryStage, taScene, ta);
		Scene HierarchyScene = HierarchySceneO.getScene();

		// jobOffering scene
		JobOfferingScene jobOfferingSceneO = new JobOfferingScene(primaryStage, taScene, ta);
		Scene jobOfferingScene = jobOfferingSceneO.getScene();

		// Calendar scene
		Calendar calendarSceneO = new Calendar(primaryStage, interviewerScene, ta);
		Scene calendarScene = calendarSceneO.getScene();

		// Interviews scene
		Interviews interviewsSceneO = new Interviews(primaryStage, interviewerScene, ta);
		Scene interviewsScene = interviewsSceneO.getScene();

		// Main handlers
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				event.consume();
				WriteObjectToFile(ta);
				primaryStage.close();
			}
		});

		back.setOnAction(e -> {
			// go to primary scene
			primaryStage.setScene(genScene);
		});
		back2.setOnAction(e -> {
			// go to primary scene
			back.fire();
		});
		tAButton.setOnAction(e -> {
			// go to Talent Acquisition scene
			primaryStage.setScene(taScene);
		});

		candidateButton.setOnAction(e -> {
			// go to candidate scene
			candidateSceneO.update();
			primaryStage.setScene(candidateScene);
		});

		jobofferButton.setOnAction(e -> {
			// go to jobOffer scene
			jobOfferingSceneO.update();
			primaryStage.setScene(jobOfferingScene);
		});

		unitButton.setOnAction(e -> {
			// go to hierarchy scene
			HierarchySceneO.update();
			primaryStage.setScene(HierarchyScene);
		});
		interviewerButton.setOnAction(e -> {
			interviewsSceneO.update();
			primaryStage.setScene(interviewerScene);
		});

		calendarButton.setOnAction(e -> {
			primaryStage.setScene(calendarScene);
		});

		InterviewsButton.setOnAction(e -> {
			interviewsSceneO.update();
			primaryStage.setScene(interviewsScene);
		});
		

		
	}


	// reading out of file method
	public void ReadObjectFromFile() {
		try {
			FileInputStream fileIn = new FileInputStream("Swe206Project1//ProjectFile.dat");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			TA ta = (TA) objectIn.readObject();
			this.ta = ta;
			objectIn.close();
		} catch (Exception e) {
			this.ta = new TA();
		}
	}

	// writing into file method
	public void WriteObjectToFile(TA Obj) {
		try {
			FileOutputStream fileOut = new FileOutputStream("Swe206Project1//ProjectFile.dat");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(Obj);
			objectOut.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}