import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
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

public class HierarchySceneC {

	// Main components
	protected TA ta;
    protected Unit company;
	private Text title = new Text("Hierarchy");
    private Text subTitle = new Text("xyzSoft");
	private Scene viewScene;
	private Scene addScene;

	// Additional
    private int addIndicator;
	private int editIndicator;
	private int deleteIndicator;
	private Unit lastSelected;
    private ComboBox<Unit> divisionCombo = new ComboBox<>();
    private ObservableList<Unit> divisionList;
	private ComboBox<Unit> directorateCombo = new ComboBox<>();
    private ObservableList<Unit> directorateList;
	private ComboBox<Unit> departmentCombo = new ComboBox<>();
    private ObservableList<Unit> departmentList;
    private ListView<Candidate> employees = new ListView<>();
	private ObservableList<Candidate> employeesList;
	private ComboBox<Candidate> candidatesCombo;
    private ListView<String> jobBands = new ListView<>();
	private ObservableList<String> jobBandsList;
	private ComboBox<String> jobBandsCombo;
    private ListView<String> jobs = new ListView<>();
    private VBox vbox;
    private HBox divisionHBox;
    private HBox directorateHBox;
    private HBox departmentHBox;
	private HBox employeesHBox;
	private VBox employeesVBox;
	private HBox jobBandsHBox;
	private VBox jobBandsVBox;
	private VBox jobsVBox;
    private HBox lists;
	private Unit temp = new Unit(0,"The List Is Empty, please add a Unit first.");

	// Buttons
	private Button add = new Button("Add"); 
	private Button edit = new Button("Edit"); 
	private Button delete = new Button("Delete"); 
        // Division
	private Button divisionAdd = new Button("Add");
	private Button divisionEdit = new Button("Edit");
	private Button divisionDelete = new Button("Delete");
        // Directorate
    private Button directorateAdd = new Button("Add");
	private Button directorateEdit = new Button("Edit");
	private Button directorateDelete = new Button("Delete");
        // Department
    private Button departmentAdd = new Button("Add");
	private Button departmentEdit = new Button("Edit");
	private Button departmentDelete = new Button("Delete");
		// Employees
	private Button employeesAdd = new Button("Add");
	private Button employeesDelete = new Button("Delete");
		// JobBands
	private Button jobBandsAdd = new Button("Add");
	private Button jobBandsDelete = new Button("Delete");
		// Jobs
	private Button jobsGenerate = new Button("Generate Jobs");


    // Unique Buttons
    private Button save = new Button("Save");
    private Button back = new Button("Back");
	private Button yes = new Button("Yes");
	private Button no = new Button("No");

	public HierarchySceneC(Stage primaryStage, Scene scene, TA ta) {

		this.ta = ta;
        this.company = ta.getCompany();
		divisionList = ta.getCompany().getObservableSubUnits();
		divisionCombo = new ComboBox<>(divisionList);
		divisionCombo.setPrefWidth(300);
        directorateCombo.setPrefWidth(300);
        departmentCombo.setPrefWidth(300);
        divisionHBox = new HBox(10);
		divisionHBox.getChildren().addAll(divisionAdd, divisionCombo, divisionEdit, divisionDelete);
        directorateHBox = new HBox(10);
		directorateHBox.getChildren().addAll(directorateAdd, directorateCombo, directorateEdit, directorateDelete);
        departmentHBox = new HBox(10);
		departmentHBox.getChildren().addAll(departmentAdd, departmentCombo, departmentEdit, departmentDelete);
        employees.setPrefSize(125, 150);
        jobBands.setPrefSize(125, 150);
        jobs.setPrefSize(125, 150);



        // Labels 
        Label divisionLabel =    new Label("Division:      ", divisionHBox);
        Label directorateLabel = new Label("Directorate: ", directorateHBox);
        Label departmentLabel =  new Label("Department:", departmentHBox);
        Label employeesLabel = new Label("Employees:", employees);
        Label jobBandsLabel = new Label("Job Bands:", jobBands);
        Label jobsLabel = new Label("Jobs:", jobs);
        Label error = new Label();
        Label addError = new Label();


		employeesHBox = new HBox(30);
		employeesHBox.getChildren().addAll(employeesAdd,employeesDelete);
		employeesVBox = new VBox(5);
		employeesVBox.getChildren().addAll(employeesLabel, employeesHBox);
		employeesHBox.setAlignment(Pos.CENTER);

		jobBandsHBox = new HBox(30);
		jobBandsHBox.getChildren().addAll(jobBandsAdd,jobBandsDelete);
		jobBandsVBox = new VBox(5);
		jobBandsVBox.getChildren().addAll(jobBandsLabel, jobBandsHBox);
		jobBandsHBox.setAlignment(Pos.CENTER);

		jobsVBox = new VBox(5);
		jobsVBox.getChildren().addAll(jobsLabel, jobsGenerate);
		jobsVBox.setAlignment(Pos.CENTER);

        lists = new HBox(50);
        lists.getChildren().addAll(jobsVBox, jobBandsVBox, employeesVBox);
        lists.setAlignment(Pos.CENTER);

        directorateHBox.setDisable(true);
        departmentHBox.setDisable(true);
		if(divisionList.isEmpty()){
			divisionCombo.setValue(temp);
            divisionEdit.setDisable(true);
            divisionDelete.setDisable(true);
		} else divisionCombo.setValue(divisionList.get(divisionList.size()-1));
		HBox buttons = new HBox(30);
		buttons.setAlignment(Pos.CENTER_LEFT);
		buttons.setPadding(new Insets(25));
		buttons.getChildren().addAll(back);

		lastSelected = divisionCombo.getValue();
		jobBands.setItems(lastSelected.getObservableJobBands());
		jobs.setItems(lastSelected.getObservableJobs());
		employees.setItems(lastSelected.getObservableEmployees());

		// Editing and viewing
		divisionLabel.setContentDisplay(ContentDisplay.RIGHT);
		directorateLabel.setContentDisplay(ContentDisplay.RIGHT);
		departmentLabel.setContentDisplay(ContentDisplay.RIGHT);
		employeesLabel.setContentDisplay(ContentDisplay.BOTTOM);
		jobBandsLabel.setContentDisplay(ContentDisplay.BOTTOM);
        jobsLabel.setContentDisplay(ContentDisplay.BOTTOM);
        
        subTitle.setFont(new Font(16));
		subTitle.setTextAlignment(TextAlignment.CENTER);
		title.setFont(new Font(30));
		title.setTextAlignment(TextAlignment.CENTER);
		VBox editQVBox = new VBox(25);
		editQVBox.setPadding(new Insets(25));
		editQVBox.getChildren().addAll(title, subTitle, divisionLabel, directorateLabel, departmentLabel, lists);
        editQVBox.setAlignment(Pos.CENTER);
		vbox = new VBox(error, editQVBox, buttons);
		viewScene = new Scene(vbox, 600, 610);

		if(lastSelected == null || lastSelected == temp || lastSelected.getName().equals("")){
			lists.setDisable(true);
		} else lists.setDisable(false);
		if(divisionCombo.getValue() != null && !divisionCombo.getValue().equals(temp)){
			lastSelected = divisionCombo.getValue();
			employeesList = lastSelected.getObservableEmployees();
			jobBandsList = lastSelected.getObservableJobBands();
			jobs.setItems(lastSelected.getObservableJobs());
		}

		// Styling
		error.setPrefSize(600, 25);
		error.setAlignment(Pos.CENTER);
		error.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent");
		Timeline anim = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
			error.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent");
		}));

        addError.setPrefSize(275, 15);
		addError.setAlignment(Pos.CENTER);
		addError.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent");
		Timeline addAnim = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
			addError.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent");
		}));

        // Add
        Stage addS = new Stage();
		TextField addD = new TextField();
        Label unitD = new Label("Unit:", addD);
        unitD.setContentDisplay(ContentDisplay.RIGHT);
		VBox addVbox = new VBox(10);
		addVbox.setAlignment(Pos.CENTER);
        Button saveD = new Button("Save");
        saveD.setOnAction(e -> {save.fire();});
		addVbox.getChildren().addAll(addError, unitD, saveD);
		addScene = new Scene(addVbox, 275, 115);

		// Delete 
		Stage sure = new Stage();
		Text sureD = new Text("Are you sure you want to delete this unit?");
		HBox deleteHbox = new HBox();
		VBox deleteVbox = new VBox();
		deleteHbox.setSpacing(25);
		deleteVbox.setSpacing(25);
		deleteHbox.setAlignment(Pos.CENTER);
		deleteVbox.setAlignment(Pos.CENTER);
		deleteHbox.getChildren().addAll(yes, no);
		deleteVbox.getChildren().addAll(sureD, deleteHbox);
		Scene deleteScene = new Scene(deleteVbox, 275, 100);


		// Handlers
        addS.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				editIndicator = 0;
				addIndicator = 0;
				addD.setText("");
				event.consume();
				vbox.setDisable(false);
				addS.close();
			}
		});

		add.setOnAction(e -> {
			addVbox.getChildren().clear();
			addVbox.getChildren().addAll(addError, unitD, saveD);
			addS.setScene(addScene);
			addS.setResizable(false);
            vbox.setDisable(true);
			if (!addS.isShowing())
				addS.show();
			else
				addS.setScene(addScene);
		});
        divisionAdd.setOnAction(e -> {
			addS.setTitle("Add Division");
            addIndicator = 1;
			editIndicator = 0;
			add.fire();
        });
        directorateAdd.setOnAction(e -> {
			addS.setTitle("Add Directorate");
            addIndicator = 2;
			editIndicator = 0;
			add.fire();
        });
        departmentAdd.setOnAction(e -> {
			addS.setTitle("Add Department");
            addIndicator = 3;
			editIndicator = 0;
			add.fire();
        });

		edit.setOnAction(e -> {
			addD.setText(divisionCombo.getValue().getName());
			addVbox.getChildren().clear();
			addVbox.getChildren().addAll(addError, unitD, saveD);
            addS.setScene(addScene);
			addS.setResizable(false);
            vbox.setDisable(true);
			if (!addS.isShowing())
				addS.show();
			else
				addS.setScene(addScene);
		});
		divisionEdit.setOnAction(e -> {
			addS.setTitle("Edit Division");
			addIndicator = 0;
			editIndicator = 1;
			edit.fire();
        });
		directorateEdit.setOnAction(e -> {
			addS.setTitle("Edit Directorate");
			addIndicator = 0;
			editIndicator = 2;
			edit.fire();
        });
		departmentEdit.setOnAction(e -> {
			addS.setTitle("Edit Department");
			addIndicator = 0;
			editIndicator = 3;
			edit.fire();
        });
        

        saveD.setOnAction(e -> {
            if(!addD.getText().equals("") || addIndicator > 3){
				// Edit
				if(editIndicator != 0){
					if(editIndicator == 1){
						Unit tmp = divisionCombo.getValue();
						tmp.setName(addD.getText());
						divisionCombo.setItems(divisionList);
						divisionCombo.setValue(divisionList.get(0));
						divisionCombo.setValue(tmp);
					}
					else if(editIndicator == 2){
						Unit tmp = directorateCombo.getValue();
						tmp.setName(addD.getText());
						directorateCombo.setItems(directorateList);
						directorateCombo.setValue(directorateList.get(0));
						directorateCombo.setValue(tmp);
					}
					else if(editIndicator == 3){
						Unit tmp = departmentCombo.getValue();
						tmp.setName(addD.getText());
						departmentCombo.setItems(departmentList);
						departmentCombo.setValue(departmentList.get(0));
						departmentCombo.setValue(tmp);
					}
				} else {
				// Add
					if(addIndicator == 1){
						Unit tmp = new Unit(1, addD.getText());
						divisionList.add(tmp);
						ta.getCompany().addSubUnit(tmp);
						divisionCombo.setItems(divisionList);
						divisionCombo.setValue(tmp);
						directorateHBox.setDisable(false);
					} else if (addIndicator == 2){
						Unit tmp = new Unit(2, addD.getText());
						directorateList.add(tmp);
						divisionCombo.getValue().addSubUnit(tmp);
						directorateCombo.setItems(directorateList);
						directorateCombo.setValue(tmp);
						departmentHBox.setDisable(false);
					} else if (addIndicator == 3){
						Unit tmp = new Unit(3, addD.getText());
						departmentList.add(tmp);
						directorateCombo.getValue().addSubUnit(tmp);
						departmentCombo.setItems(departmentList);
						departmentCombo.setValue(tmp);
					} else if(addIndicator == 4){
						String tmp = jobBandsCombo.getValue();
						jobBandsList = lastSelected.getObservableJobBands();
						if(tmp == null || jobBandsList.contains(tmp)){
							error.setStyle("-fx-background-color: red; -fx-text-fill: white");
							error.setText("You have already added this Band!");
							anim.play();
							addS.fireEvent(new WindowEvent(addS, WindowEvent.WINDOW_CLOSE_REQUEST));
							return;
						} else {
							jobBandsList.add(tmp);
							lastSelected.addJobBand(tmp);
							jobBands.setItems(lastSelected.getObservableJobBands());
						}
					} else if(addIndicator == 5) {
						Candidate tmp = candidatesCombo.getValue();
						if(tmp == null) {
							error.setStyle("-fx-background-color: red; -fx-text-fill: white");
							error.setText("Error!");
							anim.play();
							addS.fireEvent(new WindowEvent(addS, WindowEvent.WINDOW_CLOSE_REQUEST));
							return;
						} else {
							employeesList = lastSelected.getObservableEmployees();
							employeesList.add(tmp);
							lastSelected.addEmployee(tmp);
							ta.removeCandidate(tmp);
							employees.setItems(lastSelected.getObservableEmployees());
						}
					}
				}
					editIndicator = 0;
					addIndicator = 0;
					addD.setText("");
					vbox.setDisable(false);
					addS.close();
					error.setStyle("-fx-background-color: lime; -fx-text-fill: white");
					error.setText("Done!");
					anim.play();
			} else {
                addError.setStyle("-fx-background-color: red; -fx-text-fill: white");
				addError.setText("Fill all the blanks please!");
				addAnim.play();
            }
        });
		divisionCombo.setOnMouseClicked(e -> {
			if(divisionList.size() == 1){
			divisionCombo.setValue(new Unit(0,""));
			divisionCombo.setValue(divisionCombo.getSelectionModel().getSelectedItem());
			directorateHBox.setDisable(false);
		}});
		directorateCombo.setOnMouseClicked(e -> {
			if(directorateList.size() == 1){
			directorateCombo.setValue(new Unit(""));
			directorateCombo.setValue(directorateCombo.getSelectionModel().getSelectedItem());
		}});
		departmentCombo.setOnMouseClicked(e -> {
			if(departmentList.size() == 1){
			departmentCombo.setValue(new Unit(""));
			departmentCombo.setValue(departmentCombo.getSelectionModel().getSelectedItem());
		}});

		divisionCombo.setOnAction(e -> {
			Unit unit = divisionCombo.getValue();
			if (unit == null || unit == temp || unit.getName().equals("")){
				divisionEdit.setDisable(true);
				divisionDelete.setDisable(true);
				if(lastSelected == null)
					lists.setDisable(true);
			} else {
				divisionEdit.setDisable(false);
				divisionDelete.setDisable(false);
                directorateHBox.setDisable(false);
                directorateList = unit.getObservableSubUnits();
                divisionCombo.setItems(divisionList);
                directorateCombo.setItems(directorateList);
                departmentCombo.setItems(departmentList);
                departmentCombo.setValue(temp);
                departmentHBox.setDisable(true);
				employees.setItems(unit.getObservableEmployees());
				jobBands.setItems(unit.getObservableJobBands());
				jobs.setItems(unit.getObservableJobs());
				lastSelected = unit;
				lists.setDisable(false);
			}
		});
        directorateCombo.setOnAction(e -> {
			Unit unit = directorateCombo.getValue();
			if (unit == null || unit == temp || unit.getName().equals("")){
				directorateEdit.setDisable(true);
				directorateDelete.setDisable(true);
				if(lastSelected == null)
					lists.setDisable(true);
			} else {
				directorateEdit.setDisable(false);
				directorateDelete.setDisable(false);
                departmentHBox.setDisable(false);
                departmentList = unit.getObservableSubUnits();
                divisionCombo.setItems(divisionList);
                directorateCombo.setItems(directorateList);
                departmentCombo.setItems(departmentList);
				employees.setItems(unit.getObservableEmployees());
				jobBands.setItems(unit.getObservableJobBands());
				jobs.setItems(unit.getObservableJobs());
				lastSelected = unit;
				lists.setDisable(false);
			}
		});
        departmentCombo.setOnAction(e -> {
			Unit unit = departmentCombo.getValue();
			if (unit == null || unit == temp || unit.getName().equals("")){
				departmentEdit.setDisable(true);
				departmentDelete.setDisable(true);
				if(lastSelected == null)
					lists.setDisable(true);
			} else {
				departmentEdit.setDisable(false);
				departmentDelete.setDisable(false);
                divisionCombo.setItems(divisionList);
                directorateCombo.setItems(directorateList);
                departmentCombo.setItems(departmentList);
				employees.setItems(unit.getObservableEmployees());
				jobBands.setItems(unit.getObservableJobBands());
				jobs.setItems(unit.getObservableJobs());
				lastSelected = unit;
				lists.setDisable(false);
			}
		});

		back.setOnAction(e -> {
			// return to the main scene or when editing return to view scene
			divisionCombo.setItems(divisionList);
			directorateCombo.setItems(FXCollections.observableArrayList());
			departmentCombo.setItems(FXCollections.observableArrayList());
			primaryStage.setScene(scene);
			});

		jobBandsAdd.setOnAction(e -> {
			jobBandsCombo = new ComboBox<>();
			jobBandsCombo.setPrefSize(200, 10);
			jobBandsCombo.getItems().addAll("Project Management","Engineering");
			Label jobBandD = new Label("Job Band:", jobBandsCombo);
			jobBandD.setContentDisplay(ContentDisplay.RIGHT);
			addVbox.getChildren().clear();
			addVbox.getChildren().addAll(addError, jobBandD, saveD);
            addS.setScene(addScene);
			addS.setTitle("Add Job Band");
			addS.setResizable(false);
            vbox.setDisable(true);
            addIndicator = 4;
			editIndicator = 0;
			if (!addS.isShowing())
				addS.show();
			else
				addS.setScene(addScene);
		});

		employeesAdd.setOnAction(e -> {
			candidatesCombo = new ComboBox<>();
			candidatesCombo.setPrefSize(200, 10);
			if(ta.getObservableCandidates().size()>0)
				ta.getObservableCandidates().forEach(n -> {
					if(n.getSalary() != 0)
						candidatesCombo.getItems().add(n);
				});
			else candidatesCombo.getItems().clear();
			Label candidateLabel = new Label("Candidates:", candidatesCombo);
			candidateLabel.setContentDisplay(ContentDisplay.RIGHT);
			addVbox.getChildren().clear();
			addVbox.getChildren().addAll(addError, candidateLabel, saveD);
            addS.setScene(addScene);
			addS.setTitle("Add Employee");
			addS.setResizable(false);
            vbox.setDisable(true);
            addIndicator = 5;
			editIndicator = 0;
			if (!addS.isShowing())
				addS.show();
			else
				addS.setScene(addScene);
		});

		jobsGenerate.setOnAction(e -> {
			lastSelected.generateJobs();
			jobs.setItems(lastSelected.getObservableJobs());
		});

		employeesDelete.setOnAction(e -> {
			Candidate c = employees.getSelectionModel().getSelectedItem();
			if(c != null){
				employeesList.remove(c);
				lastSelected.removeEmployee(c);
				ta.addCandidate(c);
				employees.setItems(employeesList);
				error.setStyle("-fx-background-color: lime; -fx-text-fill: white");
				error.setText("Done!");
				anim.play();
			} else {
				error.setStyle("-fx-background-color: red; -fx-text-fill: white");
				error.setText("Please choose an Employee first");
				anim.play();
			}
		});

		jobBandsDelete.setOnAction(e -> {
			String s = jobBands.getSelectionModel().getSelectedItem();
			if(s != null){
				jobBandsList.remove(s);
				lastSelected.removeJobBand(s);
				jobBands.setItems(jobBandsList);
				error.setStyle("-fx-background-color: lime; -fx-text-fill: white");
				error.setText("Done!");
				anim.play();
			} else {
				error.setStyle("-fx-background-color: red; -fx-text-fill: white");
				error.setText("Please choose a Job Band first");
				anim.play();
			}
		});



		delete.setOnAction(e -> {
			error.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent");
			sure.setScene(deleteScene);
			sure.setTitle("Are you sure?");
			sure.setResizable(false);
			vbox.setDisable(true);
			if (!sure.isShowing())
				sure.show();
			else
				sure.setScene(deleteScene);
		});
		divisionDelete.setOnAction(e -> {
			if(divisionCombo.getValue() == null){
				error.setStyle("-fx-background-color: red; -fx-text-fill: white");
				error.setText(temp.getName());
				anim.play();
			} else {
				deleteIndicator = 1;
				delete.fire();
			}
		});
		directorateDelete.setOnAction(e -> {
			if(directorateCombo.getValue() == null){
				error.setStyle("-fx-background-color: red; -fx-text-fill: white");
				error.setText(temp.getName());
				anim.play();
			} else {
				deleteIndicator = 2;
				delete.fire();
			}
		});
		departmentDelete.setOnAction(e -> {
			if(departmentCombo.getValue() == null){
				error.setStyle("-fx-background-color: red; -fx-text-fill: white");
				error.setText(temp.getName());
				anim.play();
			} else {
				deleteIndicator = 3;
				delete.fire();
			}
		});

		// ------- Delete handlers
		// making sure of the decision
		yes.setOnAction(e -> {
			Unit c = temp;
			if(deleteIndicator == 1 && divisionCombo.getValue() != null){
				c = divisionCombo.getValue();
				divisionList.remove(c);
				if(divisionList.size() == 0)
					divisionCombo.setValue(temp);
				company.deleteSubUnit(c);
				divisionCombo.setItems(divisionList);
				c.getSubUnits().forEach(cc -> {
					cc.getObservableEmployees().forEach(n -> {
						ta.addCandidate(n);
						cc.getSubUnits().forEach(ccc -> {
							ccc.getObservableEmployees().forEach(nn -> {
								ta.addCandidate(nn);
							});
						});
					});
				});
			} else if(deleteIndicator == 2 && directorateCombo.getValue() != null){
				c = directorateCombo.getValue();
				directorateList.remove(c);
				if(directorateList.size() == 0)
					directorateCombo.setValue(temp);
				divisionCombo.getValue().deleteSubUnit(c);
				directorateCombo.setItems(directorateList);
				c.getSubUnits().forEach(cc -> {
					cc.getObservableEmployees().forEach(n -> {
						ta.addCandidate(n);
					});
				});
			} else if(deleteIndicator == 3 && departmentCombo.getValue() != null){
				c = departmentCombo.getValue();
				departmentList.remove(c);
				if(departmentList.size() == 0)
					departmentCombo.setValue(temp);
				directorateCombo.getValue().deleteSubUnit(c);
				departmentCombo.setItems(departmentList);
				c.getEmployees().forEach(n -> {
					ta.addCandidate(n);
				});
			}
			error.setStyle("-fx-background-color: lime; -fx-text-fill: white");
			error.setText("Done!");
			anim.play();
			sure.close();
			vbox.setDisable(false);
			deleteIndicator = 0;
		});

		no.setOnAction(e -> {
			// do not delete the question
			sure.close();
			vbox.setDisable(false);
			deleteIndicator = 0;
		});
		
		sure.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				event.consume();
				vbox.setDisable(false);
				deleteIndicator = 0;
				sure.close();
			}
		});

	}
		public void update(){
			divisionCombo.setValue(new Unit(0, ""));
			directorateHBox.setDisable(true);
			departmentHBox.setDisable(true);
			lists.setDisable(true);
		}

		// Scene getter
		public Scene getScene() {
			return viewScene;
		}


}
