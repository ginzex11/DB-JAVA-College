package AvivDoron_AlexGinzburg.view;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import AvivDoron_AlexGinzburg.listeners.ModelEventListener;
import AvivDoron_AlexGinzburg.listeners.ModelUIListener;
import AvivDoron_AlexGinzburg.model.*;


public class View implements AbstractAcademyView {
    final int LABEL_WIDTH = 300;
    final int LABEL_HEIGHT = 5;
    private ArrayList<ModelUIListener> allListeners = new ArrayList<ModelUIListener>();
    private ComboBox<String> cmbAllLecturers = new ComboBox<String>(), cmbAllTempLecturers = new ComboBox<String>(),
    		cmbAllTenureLecturers = new ComboBox<String>();
    TextField tfName, tfSpecialty, tfSalary, tfYears, tfHourlySalary,tfDailyHours,tfMessage;
    Label lblName, lblSpecialty, lblSalary, lblYears,lblCommittee,lblHourlySalary,lblDailyHours,
    	  lblDegree,lblTenureFields,lblTemporaryFields, lblTopBpRoot, lblTopUnion,
    	  lblTenureUnionSizeText, lblTenureUnionSizeNum, lblTemporaryUnionSizeText, lblTemporaryUnionSizeNum,
    	  guidelineTenure,titleTenureUnion,lblChairManInfo,lblLecturerInfo,
    	  guidelineTemp,titleTempUnion,lblTempChairManInfo,lblTempLecturerInfo;
    	  
    ToggleGroup tglLecture = new ToggleGroup(),tglDegree = new ToggleGroup();
    CheckBox cbBoard,cbExceptions,cbKishut,cbCorona,cbPedagogical;
    RadioButton rdTenure, rdTemp,rdBachelor,rdMaster,rdDoctor,rdProfessor;  
    BorderPane bpRoot = new BorderPane(),bpTemporaryU = addBPane(),bpTenureU = addBPane();    
    HBox hBoxTop = addHBox(), hBoxBottom = addHBox(), hBoxMidSection = addHBox(), hbCommittee = addHBox(),
         hbRadio = addHBox(), tenureHBox = addHBox(), temporaryHBox= addHBox();
    VBox vBoxFields = addVBox(), temporaryVboxLeft = addVBox(), temporaryVboxRight = addVBox(),vbRight = addVBox(),
    	 tenureVboxLeft = new VBox(), tenureVboxRight = new VBox(),rootVBoxMidSection = addVBox();
    Button btnAddLecturer, btnViewUnion1, btnViewUnion2, btnBackToMainScreen1, btnBackToMainScreen2, btnLecturerInfo,
    	   btnLecturerInfo2, btnSelectChairMan, btnSelectChairMan2,btnPayCheck = addButton(),btnSendMessage = addButton(),
    	   btnRemoveLecturer = addButton(), btnRemoveLecturer2= addButton(), btnRemoveLecturerMain= addButton();
    Scene mainScene,tempUnionScene,tenureUnionScene;
    
  
    public View(Stage theStage) {
    	
    	theStage.setTitle("Academy");
    	setScenes(theStage);
        setLabelsAndTextFields();
        setCommitteeCheckBox();
        setDegreeRadioButton();
        setAddLecturerButton();
        setStageBtnsLblsTxts();
               
        theStage.setScene(mainScene);
        closeProgramExternal(theStage);
        theStage.show();
    }

    
    /********Main and secondary scenes*******/
    public void setScenes(Stage theStage) {
    	
        mainScene = new Scene(bpRoot,800,800); //change to default later
        tempUnionScene = new Scene(bpTemporaryU);
        tenureUnionScene = new Scene(bpTenureU);
        
        /*****Temporary Union Scene Setup *****/
        btnViewUnion1 = new Button("Show Temporary Union Participants");
        btnViewUnion1.setPrefSize(300, 20);
        btnViewUnion1.setAlignment(Pos.BOTTOM_CENTER);
        buttonStyle(btnViewUnion1);
        BtnBackToMainScreen1(theStage);
        setTenureUnionBp();
        
        /*****Tenure Union Scene Setup *****/
        btnViewUnion2 = new Button("Show Tenure Union Participants");
        btnViewUnion2.setPrefSize(300, 20);
        btnViewUnion2.setAlignment(Pos.BOTTOM_CENTER);
        buttonStyle(btnViewUnion2);
        BtnBackToMainScreen2(theStage);
        setTemporaryUnionBp();
        
        /*****Main Scene Setup *****/ 
        //hBoxTop.setAlignment(Pos.CENTER);
        bpRoot.setPrefSize(700, 650);
        bpRoot.setMaxSize(700,650);
        bpRoot.setRight(hBoxTop);
        bpRoot.setBottom(hBoxBottom);
        bpRoot.setLeft(vBoxFields);
        bpRoot.setCenter(hBoxMidSection);
        bpRoot.setStyle("-fx-background-color: #e6f7ff;");
        cmbAllLecturers.setMaxSize(465, LABEL_HEIGHT);
        cmbAllLecturers.setPromptText("Lecturer List");
        hBoxBottom.getChildren().addAll(btnViewUnion1, btnViewUnion2);
        hBoxBottom.setAlignment(Pos.BASELINE_CENTER);
    }
    
    public void setStageBtnsLblsTxts() {
    	//Set Tenure and Temporary Radio buttons  
        rdTenure = new RadioButton("Tenure Lecturer");
        rdTemp = new RadioButton("Temporary Lecturer");
        rdTemp.setTextFill(Color.BLACK);
        rdTenure.setToggleGroup(tglLecture);
        rdTemp.setToggleGroup(tglLecture);
        
        //adding listeners to changes
        rdTemp.selectedProperty().addListener(
        (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) ->{
        	//disable and enable valid text fields
        	tfSalary.setText("");
        	tfSalary.setDisable(true);
        	tfSalary.setStyle("-fx-background-color: red;");
        	tfYears.setText("");
        	tfYears.setDisable(true);
            tfYears.setStyle("-fx-background-color: red;");
                 
        	tfHourlySalary.setDisable(false);
        	tfDailyHours.setDisable(false);
            tfHourlySalary.setStyle("");
            tfDailyHours.setStyle("");

        	//check box
            resetCheckBox();
            disableCheckBox();
        	
        });
        
      //adding listeners to changes
        rdTenure.selectedProperty().addListener(
        (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) ->{
        	//disable and enable valid text fields
        	tfHourlySalary.setText("");
        	tfHourlySalary.setDisable(true);
            tfHourlySalary.setStyle("-fx-background-color: red;");
        	tfDailyHours.setText("");
        	tfDailyHours.setDisable(true);
            tfDailyHours.setStyle("-fx-background-color: red;");
            
        	tfSalary.setDisable(false);
        	tfYears.setDisable(false);
            tfYears.setStyle("");
            tfSalary.setStyle("");

        	//check box
            enableCheckBox();
        });
        
        btnPayCheck.setText("Pay Bonus");
        btnPayCheck.setMaxSize(200, 5);
        btnPayCheck.setPrefSize(200, 5);
        btnPayCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	try {
					for (ModelUIListener l : allListeners)
						l.paycheck();
				} catch (InputMismatchException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

            }
        });

        //set "Send Message" Button
        btnSendMessage.setText("Send Message");
        btnSendMessage.setMaxSize(200, 5);
        btnSendMessage.setPrefSize(200, 5);
        btnSendMessage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	for (ModelUIListener l : allListeners)
            		l.sendMessage(tfMessage.getText());
            	
            	tfMessage.setText("");
            }
        });
        
        //set "Remove Lecturer" Button
        btnRemoveLecturerMain.setText("Remove Lecturer");
        btnRemoveLecturerMain.setMaxSize(200, 5);
        btnRemoveLecturerMain.setPrefSize(200, 5);
        btnRemoveLecturerMain.setOnAction(new EventHandler<ActionEvent>() {
        	
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
					for(ModelUIListener l: allListeners)
					  l.removeLecturer(getIdFromLecturerString(cmbAllLecturers.getValue()));
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Please choose the lecturer you wish to remove");
				}
            }
        });
        
        
        rootVBoxMidSection.getChildren().addAll(btnAddLecturer,btnRemoveLecturerMain,rdTenure, rdTemp, btnPayCheck, btnSendMessage, tfMessage);
        rootVBoxMidSection.setSpacing(30);
        vbRight.setSpacing(25);
        vbRight.setPadding(new Insets(10));
        vbRight.setAlignment(Pos.CENTER_LEFT);
        vbRight.getChildren().addAll(rootVBoxMidSection, cmbAllLecturers);
        hBoxMidSection.getChildren().addAll(vbRight);
        
        
    }

    public void setTenureUnionBp() {
    	
    	int flag = 0;
    	btnLecturerInfo = new Button("Display Lecturer Information");
        btnLecturerInfo.setPrefSize(370, 80);
        btnLecturerInfo.setOnAction(new EventHandler<ActionEvent>() {
        	
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String str = cmbAllTenureLecturers.getValue();
                    int find = getIdFromLecturerString(str);
					for(ModelUIListener l: allListeners)
						  l.findLecturer(find,flag);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Please choose lecturer!");
				}                
            }
        });
        
        btnSelectChairMan = new Button("Select the Chairman of the Union");
        btnSelectChairMan.setPrefSize(370, 80);
        btnSelectChairMan.setOnAction(new EventHandler<ActionEvent>() {
        	
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
					for(ModelUIListener l: allListeners)
					  l.selectChairman(flag);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
            }
        });
        
        btnRemoveLecturer = new Button("Remove Lecturer");
        btnRemoveLecturer.setPrefSize(370, 80);
        btnRemoveLecturer.setOnAction(new EventHandler<ActionEvent>() {
        	
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
					for(ModelUIListener l: allListeners)
					  l.removeLecturer(getIdFromLecturerString(cmbAllTenureLecturers.getValue()));
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Please choose the lecturer you wish to remove!");
				}
            }
        });
        
        lblTenureUnionSizeText = new Label("Current Number \n of Members ->");
        lblTenureUnionSizeText.setPrefSize(120, 120);
        lblTenureUnionSizeText.setStyle("-fx-text-fill: black; -fx-font-size: 13;\n" + "-fx-font-weight: bold;\n");
        lblTenureUnionSizeNum = new Label("");
        lblTenureUnionSizeNum.setAlignment(Pos.BOTTOM_CENTER);
        lblTenureUnionSizeNum.setPrefSize(120, 20);
        lblTenureUnionSizeNum.setStyle("-fx-text-fill: black; -fx-font-size: 40; \n" + "-fx-font-weight: bold;");
        //lblTenureUnionSizeNum.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        lblTenureUnionSizeNum.setAlignment(Pos.TOP_LEFT);
           
        lblLecturerInfo = new Label("Display Lecturer Info Here!");
        lblLecturerInfo.setPrefSize(370, 200);
        lblLecturerInfo.setAlignment(Pos.TOP_LEFT);
        lblLecturerInfo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        
        lblChairManInfo = new Label("Display Chairman Info here!");
        lblChairManInfo.setPrefSize(370, 200);
        lblChairManInfo.setAlignment(Pos.TOP_LEFT);
        lblChairManInfo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        
        buttonStyle(btnLecturerInfo);
        buttonStyle(btnSelectChairMan);
        labelStyle(lblChairManInfo);
        labelStyle(lblLecturerInfo);
        
        titleTenureUnion = new Label("Tenure Union GuideLines:");
        titleTenureUnion.setStyle("-fx-text-fill: black; -fx-font-size: 15; -fx-Font-weight: bold;");
        guidelineTenure = new Label(guideLinesForTenureUnion());
        guidelineTenure.setStyle("-fx-text-fill: black; -fx-font-size: 13; -fx-Font-weight: bold;");
        
        cmbAllTenureLecturers.setPadding(new Insets(10));
        cmbAllTenureLecturers.setPromptText("Union Participants");
        cmbAllTenureLecturers.setMaxSize(370, LABEL_HEIGHT);
        cmbAllTenureLecturers.setStyle("-fx-text-fill: #0000A0;\n" +"-fx-font-weight: bold;\n" +"-fx-font-size: 13;");
        cmbAllTenureLecturers.setPadding(new Insets(10));

        tenureHBox.getChildren().addAll(cmbAllTenureLecturers, lblTenureUnionSizeText, lblTenureUnionSizeNum);
        tenureHBox.setSpacing(10);
        tenureHBox.setPadding(new Insets(10));
        tenureHBox.setAlignment(Pos.CENTER_LEFT);
        
        tenureVboxLeft.setPadding(new Insets(10));
        tenureVboxLeft.setSpacing(10);
        tenureVboxLeft.getChildren().addAll(titleTenureUnion, guidelineTenure,tenureHBox);

        tenureVboxRight.setPadding(new Insets(10));
        tenureVboxRight.setSpacing(10);
        tenureVboxRight.getChildren().addAll(btnLecturerInfo, lblLecturerInfo, btnSelectChairMan, lblChairManInfo,btnRemoveLecturer);
        
        bpTenureU.setPrefSize(1000,550);
        bpTenureU.setRight(tenureVboxRight);
        bpTenureU.setLeft(tenureVboxLeft);
        bpTenureU.setStyle("-fx-background-color: #f1dada;");

    }

    public void setTemporaryUnionBp() {
    	
    	int flag = 1;
    	btnLecturerInfo2 = new Button("Display Lecturer Information");
    	btnLecturerInfo2.setPrefSize(250, 50);
    	btnLecturerInfo2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String str = cmbAllTempLecturers.getValue();
                    int find = getIdFromLecturerString(str);
					for(ModelUIListener l: allListeners)
						  l.findLecturer(find,flag);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Please choose lecturer!");
				}                
            }
        });
    	
        btnSelectChairMan2 = new Button("Select the Chairman of the Union");      
        btnSelectChairMan2.setPrefSize(250, 50);
        btnSelectChairMan2.setOnAction(new EventHandler<ActionEvent>() {
        	
            @Override
            public void handle(ActionEvent actionEvent) {
                  try {
					for(ModelUIListener l: allListeners)
						  l.selectChairman(flag);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}              
            }
        });
        
        btnRemoveLecturer2 = new Button("Remove Lecturer");
        btnRemoveLecturer2.setPrefSize(250, 50);
        btnRemoveLecturer2.setOnAction(new EventHandler<ActionEvent>() {
        	
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
					for(ModelUIListener l: allListeners)
					  l.removeLecturer(getIdFromLecturerString(cmbAllTempLecturers.getValue()));
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Please choose the lecturer you wish to remove!");
				}            
            }
        });
        
        lblTemporaryUnionSizeText = new Label("Current Number \n of Members ->");
        lblTemporaryUnionSizeText.setPrefSize(120, 80);
        lblTemporaryUnionSizeText.setStyle("-fx-text-fill: black; -fx-font-size: 13;\n" + "-fx-font-weight: bold;\n");
        lblTemporaryUnionSizeNum = new Label("");
        lblTemporaryUnionSizeNum.setPrefSize(120, 20);
        //lblTemporaryUnionSizeNum.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        lblTemporaryUnionSizeNum.setStyle("-fx-text-fill: black; -fx-font-size: 40; \n" + "-fx-font-weight: bold;");
        lblTemporaryUnionSizeNum.setAlignment(Pos.TOP_LEFT);
        
        lblTempChairManInfo = new Label("Display Chairman Info here!");
        lblTempChairManInfo.setPrefSize(250, 130);
        lblTempChairManInfo.setAlignment(Pos.TOP_LEFT);
        lblTempChairManInfo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        
        lblTempLecturerInfo = new Label("Display Lecturer Info Here!");
        lblTempLecturerInfo.setPrefSize(250, 130);
        lblTempLecturerInfo.setAlignment(Pos.TOP_LEFT);
        lblTempLecturerInfo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        buttonStyle(btnLecturerInfo2);
        buttonStyle(btnSelectChairMan2);
        labelStyle(lblTempChairManInfo);
        labelStyle(lblTempLecturerInfo);
        
        titleTempUnion = new Label("Temporary Union GuideLines:");
        titleTempUnion.setStyle("-fx-text-fill: black; -fx-font-size: 15; -fx-Font-weight: bold;");
        guidelineTemp = new Label(guideLinesForTemporaryUnion());
        guidelineTemp.setStyle("-fx-text-fill: black ; -fx-font-size: 13; -fx-Font-weight: bold;");
       
        cmbAllTempLecturers.setPadding(new Insets(10));
        //cmbAllTempLecturers.setEditable(true);
        cmbAllTempLecturers.setPromptText("Union Participants");
        cmbAllTempLecturers.setMaxSize(450, LABEL_HEIGHT);
        cmbAllTempLecturers.setStyle("-fx-text-fill: #0000A0;\n" + "-fx-font-weight: bold;\n" +"-fx-font-size: 13;");
        
        temporaryHBox.getChildren().addAll(cmbAllTempLecturers, lblTemporaryUnionSizeText, lblTemporaryUnionSizeNum);
        temporaryHBox.setSpacing(10);
        temporaryHBox.setPadding(new Insets(5));
        temporaryHBox.setAlignment(Pos.CENTER_LEFT);

        temporaryVboxLeft.setSpacing(10);
        temporaryVboxLeft.getChildren().addAll(titleTempUnion, guidelineTemp,temporaryHBox);
        temporaryVboxRight.getChildren().addAll(btnLecturerInfo2, lblTempLecturerInfo, btnSelectChairMan2, lblTempChairManInfo,btnRemoveLecturer2);
        
        bpTemporaryU.setPrefSize(400, 600);
        bpTemporaryU.setMaxSize(400, 600);
        bpTemporaryU.setRight(temporaryVboxRight);
        bpTemporaryU.setLeft(temporaryVboxLeft);
        bpTemporaryU.setStyle("-fx-background-color:  #c6d9eb;");
    }
  
    
    /********Text fields*******/
    public void setLabelsAndTextFields() {

        lblName = new Label("Enter Lecturer`s name: ");
        lblName.setMaxSize(LABEL_WIDTH, LABEL_HEIGHT);
        tfName = new TextField();
        textSize(tfName);


        lblSpecialty = new Label("Enter specialty: ");
        lblSpecialty.setMaxSize(LABEL_WIDTH, LABEL_HEIGHT);
        tfSpecialty = new TextField();
        textSize(tfSpecialty);

        lblSalary = new Label("Enter Salary: ");
        lblSalary.setMaxSize(LABEL_WIDTH, LABEL_HEIGHT);
        tfSalary = new TextField();
        textSize(tfSalary);

        lblYears = new Label("Enter Years: ");
        lblYears.setMaxSize(LABEL_WIDTH, LABEL_HEIGHT);
        tfYears = new TextField();
        textSize(tfYears);

        lblHourlySalary = new Label("Enter Hourly Salary: ");
        lblHourlySalary.setMaxSize(LABEL_WIDTH, LABEL_HEIGHT);
        tfHourlySalary = new TextField();
        textSize(tfHourlySalary);

        lblDailyHours = new Label("Enter Daily Hours: ");
        lblDailyHours.setMaxSize(LABEL_WIDTH, LABEL_HEIGHT);
        tfDailyHours = new TextField();
        textSize(tfDailyHours);

        lblCommittee = new Label("Enter Committee: ");

        lblDegree = new Label("Enter Highest Degree: ");

        lblTenureFields = new Label("Only For Tenure Lecturers!");
        lblTenureFields.setStyle("-fx-text-fill: #a67036; -fx-font-size: 15; -fx-Font-weight: bold;");

        lblTemporaryFields = new Label("Only For Temporary Lecturers!");
        lblTemporaryFields.setStyle("-fx-text-fill: #a67036; -fx-font-size: 15; -fx-Font-weight: bold;");
        
        tfMessage = new TextField();
        textSize(tfMessage);

        bpRoot.setTop(lblTopBpRoot);
        vBoxFields.getChildren().addAll(lblName, tfName, lblSpecialty, tfSpecialty, lblTenureFields, lblSalary, tfSalary, lblYears, tfYears, lblTemporaryFields, lblHourlySalary, tfHourlySalary, lblDailyHours, tfDailyHours, lblCommittee);

    }
    
    public void resetTextFields() {
        tfName.setText("");
        tfSpecialty.setText("");
        tfSalary.setText("");
        tfYears.setText("");
        tfDailyHours.setText("");
        tfHourlySalary.setText("");
    }
    
    /********Degree Radio Buttons*******/
    public void setDegreeRadioButton() {
        rdBachelor = new RadioButton("Bachelor");
        rdMaster = new RadioButton("Masters");
        rdDoctor = new RadioButton("Doctor");
        rdProfessor = new RadioButton("Professor");
        
        rdBachelor.setToggleGroup(tglDegree);
        rdMaster.setToggleGroup(tglDegree);
        rdDoctor.setToggleGroup(tglDegree);
        rdProfessor.setToggleGroup(tglDegree);
        
        hbRadio.getChildren().addAll(rdBachelor,rdMaster,rdDoctor,rdProfessor);
        hbRadio.setSpacing(10);
        vBoxFields.getChildren().addAll(hbRadio);
    }
    
    public void resetDegreeRadioButtons() {
    	rdBachelor.setSelected(false);
    	rdDoctor.setSelected(false);
    	rdMaster.setSelected(false);
    	rdProfessor.setSelected(false);
    	rdTemp.setSelected(false);
    	rdTenure.setSelected(false);
    	
    	//temp fields to reset
    	tfHourlySalary.setDisable(false);
    	tfDailyHours.setDisable(false);
        tfHourlySalary.setStyle("");
        tfDailyHours.setStyle("");
        
        //tenure fields to reset
    	tfSalary.setDisable(false);
    	tfYears.setDisable(false);
        tfYears.setStyle("");
        tfSalary.setStyle("");
    }
    
    /********Committee*******/
    public void setCommitteeCheckBox() {
    	
    	cbBoard = new CheckBox("Board");
    	cbExceptions = new CheckBox("Exceptions");
    	cbKishut = new CheckBox("Kishut");
    	cbCorona = new CheckBox("Corona");
    	cbPedagogical = new CheckBox("Pedagogical");
    	hbCommittee.getChildren().addAll(cbBoard,cbExceptions,cbKishut,cbCorona,cbPedagogical);
    	vBoxFields.getChildren().addAll(hbCommittee);
    }
    
    public String getCommitteeString() throws InputMismatchException {
    	String str = "[ ";
    	if(checkCommittee())
    	{
        	if(cbBoard.isSelected())
        		str += cbBoard.getText();
        	if(cbExceptions.isSelected())
        		str+=" "+cbExceptions.getText();
        	if(cbKishut.isSelected())
        		str+=" "+cbKishut.getText();
        	if(cbCorona.isSelected())
        		str+=" "+cbCorona.getText();
        	if(cbPedagogical.isSelected())
        		str+=" "+cbPedagogical.getText();
    	}
    	else //need to choose at least one check box 
    		throw new InputMismatchException("Must choose at least one committee!");
	
    	str+= " ]";
    	return str;
    }
    
    public boolean checkCommittee() {
    	if(cbBoard.isSelected() || cbExceptions.isSelected() || cbKishut.isSelected() || cbCorona.isSelected() || cbPedagogical.isSelected())
    		return true;
    	return false;
    }
  
    public void setAddLecturerButton() {
    	
        btnAddLecturer = new Button("Add Lecturer");
        btnAddLecturer.setPrefSize(200, 50);
        btnAddLecturer.setMaxSize(200, 50);
        btnAddLecturer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	if(rdTenure.isSelected())
            	{
                    for (ModelUIListener l : allListeners)
    					try {
    						l.addTenureLecturerToUI(tfName.getText(),stringToInt(tfSalary.getText()),stringToInt(tfYears.getText()), tfSpecialty.getText(), getCommitteeString(),tglDegreeChoice());   						
    	                    resetTextFields();
    	                    resetCheckBox();  
    	                    resetDegreeRadioButtons();
    					}catch (InputMismatchException e) {
 							JOptionPane.showMessageDialog(null, e.getMessage());
 						}
                    	catch (Exception e) {
                    		JOptionPane.showMessageDialog(null, e.getMessage());
    					}
                 
            	}
            	else if(rdTemp.isSelected())
            	{
            		 for (ModelUIListener l : allListeners)
            			 try {
            				 l.addTempLecturerToUI(tfName.getText(), stringToInt(tfHourlySalary.getText()), stringToInt(tfDailyHours.getText()), tfSpecialty.getText(), tglDegreeChoice());
            				 resetTextFields();
            				 resetDegreeRadioButtons();
            			 }catch (InputMismatchException e) {
  							JOptionPane.showMessageDialog(null, e.getMessage());
  						}
                     	catch (Exception e) {
                     		JOptionPane.showMessageDialog(null, e.getMessage());
     					}
            		 
            	}
            	else
            		JOptionPane.showMessageDialog(null, "Lecturer must be Tenure or Temporary!");                
            }
        });
    }
    
    public void enableCheckBox() {
    	cbBoard.setDisable(false);
    	cbExceptions.setDisable(false);
    	cbCorona.setDisable(false);
    	cbKishut.setDisable(false);
    	cbPedagogical.setDisable(false);
    }
    
    public void disableCheckBox() {
    	cbBoard.setDisable(true);
    	cbExceptions.setDisable(true);
    	cbCorona.setDisable(true);
    	cbKishut.setDisable(true);
    	cbPedagogical.setDisable(true);
    }
    
    public void resetCheckBox() {
    	cbBoard.setSelected(false);
    	cbCorona.setSelected(false);
    	cbExceptions.setSelected(false);
    	cbKishut.setSelected(false);
    	cbPedagogical.setSelected(false);
    }
    
    /********General*******/
    public HBox addHBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        return hBox;
    }

    public VBox addVBox() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15, 12, 15, 12));
        vBox.setSpacing(10);
        return vBox;
    }
    
    public BorderPane addBPane() {
        BorderPane borderPane = new BorderPane();
        Label label = new Label("WELCOME TO THE UNION");
        label.getStyleClass().add("bg-6");
        BorderPane.setMargin(label, new Insets(5));
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        borderPane.setTop(label);
        return borderPane;
    }
    
    private TextField textSize(TextField textField) {
        textField.setPrefWidth(250);
        textField.setMaxWidth(250);
        return textField;
    }
    
    public Label labelStyle(Label label) {
        label.setStyle("-fx-text-fill: black;\n" +"fx-font-weight: bold;\n" +"-fx-font-size: 13;");
        return label;
    }

    public Button buttonStyle(Button btn) {
    	btn.setStyle("-fx-text-fill: black;\n" + "-fx-font-size: 12;");
        return btn;
    }
    
    private Button addButton() {
        Button btn = new Button();
        buttonStyle(btn);
        return btn;
    }
    
    public RadioButton radioBtnStyle(RadioButton rBtn) {
        rBtn.setStyle("-fx-text-fill: black;\n" + "-fx-font-weight: bold;\n" + "-fx-font-size: 12;");
        return rBtn;
    }
    
    public void BtnBackToMainScreen1(Stage theStage) {

        btnBackToMainScreen1 = new Button("Back");
        btnBackToMainScreen1.setPrefSize(100, 10);
        btnBackToMainScreen1.setAlignment(Pos.CENTER);
        btnBackToMainScreen1.setPadding(new Insets(20));
        btnViewUnion1.setOnAction(e ->
        {
            theStage.setScene(tempUnionScene);

            btnBackToMainScreen1.setOnAction(event -> theStage.setScene(mainScene));
            bpTemporaryU.setBottom(btnBackToMainScreen1);

        });
    }

    public void BtnBackToMainScreen2(Stage theStage) {

        btnBackToMainScreen2 = new Button("Back");
        btnBackToMainScreen2.setPrefSize(100, 10);
        btnBackToMainScreen2.setAlignment(Pos.CENTER);
        btnBackToMainScreen2.setPadding(new Insets(20));
        btnViewUnion2.setOnAction(e ->
        {
            theStage.setScene(tenureUnionScene);

            btnBackToMainScreen2.setOnAction(event -> theStage.setScene(mainScene));
            bpTenureU.setBottom(btnBackToMainScreen2);

        });
    }
    
    public String guideLinesForTenureUnion() {
        String GuideLinesForTenureUnion =
                "1) To lecture the material in the course and recommend reading \n" +
                        "2) To provide examples sheets for use in supervisions \n" +
                        "3) To assist examiners in producing a fair examination \n" +
                        "4) To assist in monitoring the course \n   " +
                        "___________________________________________________________________________ \n" +
                        "\nTemporary Union Benefits: \n\n" +
                        "1) The Union will provide legal protection and advice.\n    In today's lawsuit-happy society, this protection alone can be\n    worth becoming a member. \n" +
                        "2) The Union will allow you a voice in hot educational debates\n     and topics that you feel strongly about.\n" +
                        "3) Joining the Tenure Union gives you (The Tenure Lecturer)\n    power to the bargain position of the union " +
                        " for contract and labor\n negotiations regarding hourly salary and daily hours possible for work.\n";

        return GuideLinesForTenureUnion;
    }

    public String guideLinesForTemporaryUnion() {
        String GuideLinesForTemporaryUnion =
                "1) To lecture the material in the course and recommend reading \n" +
                        "2) To provide examples sheets for use in supervisions \n" +
                        "3) To assist examiners in producing a fair examination \n" +
                        "4) To assist in monitoring the course \n   " +
                        "___________________________________________________________________________ \n" +
                        "\nTemporary Union Benefits: \n\n" +
                        "1) The Union will provide legal protection and advice.\n    In today's lawsuit-happy society, this protection alone can be\n    worth becoming a member. \n" +
                        "2) The Union will allow you a voice in hot educational debates\n     and topics that you feel strongly about.\n" +
                        "3) Joining the Temporary Union gives you (The Temporary Lecturer)\n   power to the bargain position" +
                        "  of the union for contract and labor \n   negotiations regarding hourly salary and daily hours possible for work.\n ";

        return GuideLinesForTemporaryUnion;
    }
    
    public Degree tglDegreeChoice() {
    	
    	if(rdBachelor.isSelected())
    		return Degree.Bachelor;
    	else if (rdMaster.isSelected())
    		return Degree.Masters;
    	else if(rdDoctor.isSelected())
    		return Degree.Doctor;
    	else if(rdProfessor.isSelected())
    		return Degree.Professor;
    	else
    		throw new InputMismatchException("Must Choose Degree for Lecturer!");
    }
 
    public int  stringToInt(String str) {
    	// rename method
    	int num=0;
    	try {
    		num = Integer.parseInt(str);    		
    	}
    	catch (NumberFormatException  | InputMismatchException  e) {
    		JOptionPane.showMessageDialog(null, "Number must be of type Integer!");
    	}  	
    	return num;
    }
    
	private int getIdFromLecturerString(String str) {
		
		String[] arr = str.split("[()]");
		return Integer.parseInt(arr[1]);
	}
	
	public void strongerUnion(int sizeOfTenure, int sizeOfTemp) {
		
		if(sizeOfTenure > sizeOfTemp) //Tenure Union is stronger
		{
			buttonStyle(btnViewUnion1);
			btnViewUnion2.setStyle("-fx-background-color: green; -fx-text-fill: black; -fx-font-size: 15;\n" + "-fx-font-weight: bold;\n");
			
		}
		else if (sizeOfTenure < sizeOfTemp) //Temporary Union is stronger
		{
			buttonStyle(btnViewUnion2);
			btnViewUnion1.setStyle("-fx-background-color: green; -fx-text-fill: black; -fx-font-size: 15;\n" + "-fx-font-weight: bold;\n");
		}
		else // equal
		{
			buttonStyle(btnViewUnion1);
			buttonStyle(btnViewUnion2);
		}
	}

	/********Abstract Academy View *******/
    @Override
    public void registerListener(ModelUIListener listener) {
        allListeners.add(listener);
    }
    
	@Override
	public void displayTenureLecturer(String name,int salary,int years,String specialty,String committee,Degree deg,int id) {
		lblLecturerInfo.setText("");
		lblLecturerInfo.setText("Name: "+name+"\nId : "+id+"\nDegree: "+deg+"\nSpecialty: "+specialty+"\nSeniority: "+years+"\nSalary: "+salary+"$\nCommittee: "+committee);
	}
	
	@Override
	public void displayTempLecturer(String name, int salary, int hours, String specialty, Degree deg, int id) {
		lblTempLecturerInfo.setText("");
		lblTempLecturerInfo.setText("Name: "+name+"\nId : "+id+"\nDegree: "+deg+"\nSpecialty: "+specialty+"\nDaily Hours: "+hours+"\nDaily Salary: "+salary+"$\n");	
	}
    
	@Override
	public void displayTenureChairman(String name, int salary, int years, String specialty, String committee,Degree deg, int id) {
		lblChairManInfo.setText(" ");
		lblChairManInfo.setText("Chairman\nName: "+name+"\nId : "+id+"\nDegree: "+deg+"\nSpecialty: "+specialty+"\nSeniority: "+years+"\nSalary: "+salary+"$\nCommittee: "+committee);
		
	}

	@Override
	public void displayTempChairman(String name, int salary, int hours, String specialty, Degree deg, int id) {
		lblTempChairManInfo.setText("");
		lblTempChairManInfo.setText("Chairman\nName: "+name+"\nId : "+id+"\nDegree: "+deg+"\nSpecialty: "+specialty+"\nDaily Hours: "+hours+"\nDaily Salary: "+salary+"$\n");		
	}
    
    @Override
    public void addTenureLecturerToUI(String name,int salary,int years,String specialty,String committee,Degree deg, int id) {
        cmbAllLecturers.getItems().add("["+ name + ", (" + id + ") , "+ years +", " + deg+" ]");
        cmbAllTenureLecturers.getItems().add("["+ name + ", (" + id + "), "+ years +", " + deg+" ]");

    }
    
	@Override
	public void addTempLecturerToUI(String name, int salary, int hours, String specialty, Degree deg,int id) {
		cmbAllLecturers.getItems().add("["+ name + ", (" + id + ") , "+ hours +", " + deg+" ]");
		cmbAllTempLecturers.getItems().add("["+ name + ", (" + id + ") , "+ hours +", " + deg+" ]");

	}
	
	@Override
	public void updateNumOfLecturers(int sizeOfTenure,int sizeOfTemp) {
		
		lblTenureUnionSizeNum.setText(""+sizeOfTenure);
		lblTemporaryUnionSizeNum.setText(""+sizeOfTemp);
		strongerUnion(sizeOfTenure, sizeOfTemp);
		
	}
	
	@Override
	public void removeTenureFromUI(int id) {
		int i,currID;
		removeLecturerFromMainScene(id);
		
		ObservableList<String> tenureLecturers = cmbAllTenureLecturers.getItems();
		for(i=0; i < tenureLecturers.size(); i++)
		{
			currID = getIdFromLecturerString(tenureLecturers.get(i));
			if(currID == id)
				cmbAllTenureLecturers.getItems().remove(i);
		}
	}

	@Override
	public void removeTempFromUI(int id) {
		int i,currID;
		removeLecturerFromMainScene(id);
		ObservableList<String> tempLecturers = cmbAllTempLecturers.getItems();
		for(i=0; i < tempLecturers.size(); i++)
		{
			currID = getIdFromLecturerString(tempLecturers.get(i));
			if(currID == id)
				cmbAllTempLecturers.getItems().remove(i);
		}
		
	}
	
	public void removeLecturerFromMainScene(int id) {
		int i,currID;
		ObservableList<String> allLecturers = cmbAllLecturers.getItems();
		for(i=0; i < allLecturers.size(); i++)
		{
			currID = getIdFromLecturerString(allLecturers.get(i));
			if(currID == id)
				cmbAllLecturers.getItems().remove(i);
		}
	}
	
	/********Save and Load files*******/
    public void closeProgramExternal(Stage theStage) {
        theStage.setOnCloseRequest(e -> {
            e.consume();
            for(ModelUIListener l : allListeners)
            	l.closeProgram();
            System.out.println("file saved");
            theStage.close();
        });
    }


    





}
