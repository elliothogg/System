/* This class has two purposes:
 * 
 *      Launch without args - Runs a GUI program that allows the user to test all of the methods of the System Class.
 *                            The GUI consists of two scenes - Main & setDetails. 
 * 
 *      Launch with "runtests" as args[0] - runs complete unit testing of System class methods.
 * 
 * This project was written as a University project.
 * 
 * @author	********
 * @version 1.3  (01 August 2020)
 * 
 */

import javafx.application.Application; 
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.control.TextArea; 
import javafx.scene.control.TextField; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox; 
import javafx.scene.text.Font; 
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class SystemTestGUI extends Application {

    //various "node creation" methods. These methods make node creation simpler. They also enable parameters such as font size etc to be changed globally
    Label createLabel(String labelTag)
    {
        Label newLabel = new Label(labelTag);
        newLabel.setFont(Font.font("Arial", 20));
        return newLabel;
    }

    Label createLabelRedText(String labelTag)
    {
        Label newLabel = new Label(labelTag);         
        newLabel.setFont(Font.font("Arial", 15));
        newLabel.setTextFill(Color.RED);
        return newLabel;
    }

    TextField createTextField()
    {
        TextField newTextField= new TextField();  
        newTextField.setMaxWidth(50);
        return newTextField;
    }

    Button createButton(String buttonText)
    {
        Button newButton = new Button();
        newButton.setFont(Font.font("Arial", 14));
        newButton.setText(buttonText);
        return newButton;
    }

    VBox createVbox(double spacing, Pos position, Node... children)
    {
        VBox newVbox = new VBox(spacing);
        newVbox.setAlignment(position);
        newVbox.getChildren().addAll(children);
        return newVbox;
    }

    HBox createHbox(double spacing, Pos position, Node... children)
    {
        HBox newHbox = new HBox(spacing);
        newHbox.setAlignment(position);
        newHbox.getChildren().addAll(children);
        return newHbox;
    }


    //method that validates if input is an integer
    boolean isInt(String input)
    {
        //regex expression to check if String contains integers only
        return (input.matches("\\d+"));
    }

    //method that validates if input is a double
    boolean isDouble(String input)
    {
        return (input.matches("\\d*\\.*\\d+"));
    }

    //create a System object using the constructor
    System appleMacbook = new System("Apple", "Macbook", 3);

    //create 2 scenes for the 2 scenes of the GUI
    private Scene mainScene, setDetailsScene;

    @Override
    public void start(Stage stage)
    {
        // create and configure a non-editable text area for 'main' scene
        TextArea display = new TextArea();
        display.setFont(Font.font("Arial", 20));
        display.setEditable(false);
        display.setMinSize(250,100);
        display.setMaxSize(600,200);
        display.setText("Please choose from the options below.");

       
        //CREATE ALL NODES FOR 'SET DETAILS' SCENE

        TextField memorySizeField = createTextField();
        TextField hardDiskSizeField = createTextField();
        TextField purchaseCostField = createTextField();
        
        Label memorySizeLabel = createLabel("Memory Size");
        Label hardDiskSizeLabel = createLabel("Hard Disk Size");
        Label purchaseCostLabel = createLabel("Purchase Cost");

        //error labels are initally empty
        Label memorySizeErrorLabel = createLabelRedText("");
        Label hardDiskSizeErrorLabel = createLabelRedText("");                 
        Label purchaseCostErrorLabel = createLabelRedText("");

        //back button to return user from 'set details' window to 'main' scene
        Button backButton = createButton("Back");
        backButton.setOnAction( e ->
                            {
                                stage.setScene(mainScene);
                                stage.setTitle("System");
                                stage.show();
                            }
        );

        //this button sets the details entered and returns user to the 'main' scene
        Button setButton = createButton("Set");
        setButton.setOnAction( e ->
                                {
                                    //clear any previous error messages
                                    memorySizeErrorLabel.setText("");
                                    hardDiskSizeErrorLabel.setText("");
                                    purchaseCostErrorLabel.setText("");

                                    //store text entry for validation
                                    String memorySizeInput = memorySizeField.getText();
                                    String hardDiskSizeInput = hardDiskSizeField.getText();
                                    String purchaseCostInput = purchaseCostField.getText(); 
                                    
                                    //input validation
                                    if (!isInt(memorySizeInput))
                                    {
                                        memorySizeErrorLabel.setText("Please enter a valid integer.");
                                    }
                                    if (!isDouble(hardDiskSizeInput))
                                    {
                                        hardDiskSizeErrorLabel.setText("Please enter a valid double.");
                                    }
                                    if (!isDouble(purchaseCostInput))
                                    {
                                        purchaseCostErrorLabel.setText("Please enter a valid double.");
                                    }

                                    //if all inputs are valid then set values using "setter" methods of System class
                                    if (isInt(memorySizeInput) && isDouble(hardDiskSizeInput) && isDouble(purchaseCostInput))
                                    {
                                        appleMacbook.setMemory(Integer.parseInt(memorySizeField.getText()));
                                        appleMacbook.setHardDisk(Double.parseDouble(hardDiskSizeField.getText()));
                                        appleMacbook.setPurchaseCost(Double.parseDouble(purchaseCostField.getText()));
                                        display.setText("Details sucessfully changed. \nClick the \"Print System Details\" button to view the changes.");
                                        
                                        //move back to 'main' scene
                                        stage.setScene(mainScene);
                                        stage.setTitle("System");
                                        stage.show();
                                    }
                                }
        );

        //various Box's that allign all nodes of 'set details' scene
        VBox setDetailsEntryFields = createVbox(20, Pos.CENTER_LEFT, memorySizeField, hardDiskSizeField, purchaseCostField);
        VBox setDetailsLabels = createVbox(25, Pos.CENTER_RIGHT, memorySizeLabel, hardDiskSizeLabel, purchaseCostLabel);
        VBox setDetailsInputErrorLabels = createVbox(28, Pos.CENTER_RIGHT, memorySizeErrorLabel, hardDiskSizeErrorLabel, purchaseCostErrorLabel);
        HBox setDetailsFieldsButtonsErrorLabels = createHbox(5, Pos.CENTER, setDetailsLabels, setDetailsEntryFields, setDetailsInputErrorLabels);
        HBox setDetailsPageButtons = createHbox(20, Pos.CENTER, backButton, setButton );

        //create the root note, and add it to a new scene
        VBox setDetailsWindow = createVbox(25, Pos.CENTER, setDetailsFieldsButtonsErrorLabels, setDetailsPageButtons);
        setDetailsScene = new Scene(setDetailsWindow, 650, 300);


        //CREATE NODES FOR 'MAIN' SCENE

        //create 3 buttons that display various information on the text area
        Button printSystemDetails = createButton("Print System Details");
        printSystemDetails.setOnAction( e -> display.setText(appleMacbook.displayDetails()));

        Button displaySystemProperties = createButton("Display System Properties");
        displaySystemProperties.setOnAction( e -> display.setText(appleMacbook.displaySystemProperties()));

        Button diagnoseSystem = createButton("Diagnose System");
        diagnoseSystem.setOnAction( e -> display.setText(appleMacbook.diagnoseSystem()));

        //takes the user to the 'set details' scene
        Button setDetails = createButton("Set Details");
        setDetails.setOnAction( e ->
                                {
                                    //error labels & text fields are reinitialised
                                    memorySizeErrorLabel.setText("");
                                    hardDiskSizeErrorLabel.setText("");
                                    purchaseCostErrorLabel.setText("");
                                    memorySizeField.setText("");
                                    hardDiskSizeField.setText("");
                                    purchaseCostField.setText("");

                                    //'set details' scene is added to the stage
                                    stage.setScene(setDetailsScene);
                                    stage.setTitle("Set Details");
                                    stage.show();
                                }
        );

        //create HBox to allign buttons and add all 'main' scene nodes to Vbox (root node)
        HBox buttons = createHbox(10, Pos.CENTER, printSystemDetails, displaySystemProperties, diagnoseSystem, setDetails);
        VBox mainWindow = createVbox(25, Pos.CENTER, display, buttons);
        
        // create a new scene for main scene and add it to the stage
        mainScene = new Scene(mainWindow, 650, 300);
        stage.setScene(mainScene);
        stage.setTitle("System");

        //show stage
        stage.show();
        }
    
    public static void main(String[] args)     
    {         
        SystemTest harness = new SystemTest();
        
        //arg[0] == "runtests" - complete unit testing of System methods
        //no args - program runs GUI menu.
        if (args.length != 0 && args[0].equalsIgnoreCase("runtests"))
        {
            //strings print the methods that are being tested
            harness.runTest("getMake()", harness.getMake_test());
            harness.runTest("getModel()", harness.getModel_test());
            harness.runTest("getProcessorSpeed()", harness.getProcessorSpeed_test());
            harness.runTest("checkHDStatus()", harness.checkHDStatus_test());
            harness.runTest("goodMemorySize()", harness.goodMemorySize_test());
            harness.runTest("setMemory() setHDStatus() setPurchaseCost()  displayDetails()", harness.setterMethods_DisplayDetails_test());
            harness.runTest("diagnoseSystem()", harness.diagnoseSystem_test());
            harness.runTest("displaySystemProperties()", harness.displaySystemProperties_test());
            java.lang.System.exit(0);
        }
        else launch(args);
    }
}