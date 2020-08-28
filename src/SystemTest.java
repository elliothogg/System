/* This class has two purposes:
 * 
 *      Launch without args - displays a terminal menu that can be 
 *                            used to test all the methods of the System class.
 * 
 *      Launch with "runtests" as arg[0] - runs complete unit testing of System class methods.
 *
 * This project was written as a University project.
 * 
 * @author	********
 * @version 1.3  (01 August 2020)
 * 
 */

import java.util.Scanner;
import java.util.Properties;

public class SystemTest {

    //create a scanner object using reference to full path of System.in to avoid name clash
    Scanner sc = new Scanner(java.lang.System.in);
   
    //create 4 print methods that use full path reference of System.out in order to resolve "System" name clash
    public void printStringln(String input)
    {
        java.lang.System.out.println(input);
    }

    public void printString(String input)
    {
        java.lang.System.out.print(input);
    }

    public void printInt(int input)
    {
        java.lang.System.out.println(input);
    }

    public void printBool(Boolean input)
    {
        java.lang.System.out.println(input);
    }


    //method for validating integers as Scanner input
    public int returnValidInt()
    {
        //while loop that continually prompts for input until an integer is received
        while (!sc.hasNextInt())
            {
                printString("Invalid input! Please enter an integer: ");
                sc.next();
            }

        //returns the valid input
        return sc.nextInt();
    }

    //method for validating doubles as Scanner input
    public double returnValidDouble()
    {
        while (!sc.hasNextDouble())
            {
                printString("Invalid input! Please enter a double: ");
                sc.next();
            }
        return sc.nextDouble();
    }

    //recursive method that validates user menu choices
    public int returnValidMenuChoice()
    {
        printString("Please choose an option (enter 1 - 5): ");

        //while loop that continually prompts for input until an integer is recieved
        while (!sc.hasNextInt())
            {
                printString("Invalid input! Please choose an option (enter 1 - 5): ");
                sc.next();
            }

        //variable to store integer for further validation
        int menuInput = sc.nextInt();
        if (menuInput > 5 || menuInput < 1)
        {
            //error message and recursive call to revalidate input
            printString("Invalid input! ");
            return returnValidMenuChoice();
        }
        else return menuInput;
    }

    public void printMenuOptions()
    {
        printStringln("*********** MENU *********" + "\n" +
        "Choice 1 - Print System Details" + "\n" +
        "Choice 2 - Display System Properties" + "\n" +
        "Choice 3 – Diagnose System" + "\n" +
        "Choice 4 – Set Details" + "\n" +
        "Choice 5 - Quit the program");
    }

    public void runMenu()
    {
        //create a System object using the constructor
        System menuTest = new System("Apple", "Macbook", 3);
        
        int menuChoice;

        printMenuOptions();
        
        //set boolean "program running" to true. This will be changed to false when the user selects "Quit"
        boolean programRunning= true;
        
        //do-while loop that loops the program while 'programRunning' is set to 'true'
        do {
            //use 'returnValidMenuChoice' method to receive valid input (integer) and assign it to 'menuChoice' variable, used in switch menu below
            menuChoice = returnValidMenuChoice();
            //switch statement to manage menu choices
            switch(menuChoice)
            {
                //calls the method to displayDetails
                case 1:
                    printStringln(menuTest.displayDetails());
                    break;
                         
                //calls the displaySystemProperties method
                case 2:
                    printStringln(menuTest.displaySystemProperties());
                    break;
                         
                //calls the diagnoseSystem method
                case 3:
                    printStringln(menuTest.diagnoseSystem());
                    break;
                         
                //changes the memory size, hard disk size, and purchase cost using "setter" methods
                case 4:
                        
                //user input is prompted, validated, and set using "setter" methods
                    printString("Please enter the memory size: ");
                    menuTest.setMemory(returnValidInt());
                        
                    printString("Please enter the hard disk size: ");
                    menuTest.setHardDisk(returnValidDouble());
        
                    printString("Please enter the purchase cost: ");
                    menuTest.setPurchaseCost(returnValidDouble());
                    break;
         
                //displays exit message and sets programRunning to "false", ending the while loop
                case 5:
                    printStringln("************ Thanks For Using ************");
                    programRunning = false;
                    break;
                    }
        
                //do-while loop condition(mentioned above). Keeps the program looping while 'programRunning' = true
                } while (programRunning);        
    }


    //Create object of System class and write testing functions for all methods
    System unitTest = new System("apple", "macbook", 3);
    
    boolean getMake_test()
    {
        return unitTest.getMake() == "apple";
    }
    
    boolean getModel_test()
    {
        return unitTest.getModel() == "macbook";
    }
  
    boolean getProcessorSpeed_test()
    {
        return unitTest.getProcessorSpeed() == 3;
    }

    boolean setterMethods_DisplayDetails_test()
    {
        unitTest.setMemory(128);
        unitTest.setHardDisk(15.5);
        unitTest.setPurchaseCost(1000.2);
        return unitTest.displayDetails().equals("Make: " + "apple" + "\n" +
                                            "Model: " + "macbook" + "\n" +
                                            "Speed: " + 3 + "\n" +
                                            "Memory size: " + "128" + "\n" +
                                            "Hard disk size: " + 15.5 + "\n" +
                                            "Purchase cost: " + 1000.2);
    }

    boolean checkHDStatus_test()
    {
        boolean case1 = false, case2 = false;
        unitTest.setHardDisk(1.9999);
        if (unitTest.checkHDStatus().equals("low"))
        {
            case1 = true;
        }
        unitTest.setHardDisk(2);
        if (unitTest.checkHDStatus().equals("OK"))
        {
            case2 = true;
        }
        if (case1 && case2)
        {
            return true;
        }
        else return false;
    }
    
    boolean goodMemorySize_test()
    {
        boolean case1 = false, case2 = false;
        unitTest.setMemory(127);
        if (!unitTest.goodMemorySize())
        {
            case1 = true;
        }
        unitTest.setMemory(128);
        if (unitTest.goodMemorySize())
        {
            case2 = true;
        }
        if (case1 && case2)
        {
            return true;
        }
        else return false;
    }

    boolean diagnoseSystem_test()
    {   
        unitTest.setHardDisk(2);
        unitTest.setMemory(128);
        return unitTest.diagnoseSystem().equals("Hard Disk Size = " + "OK" + "\n" +
                                            "Memory Size OK = " + true);
    }

    boolean displaySystemProperties_test()
    {
        Properties p = new Properties(java.lang.System.getProperties());
        String operatingSystem = p.getProperty("os.version");
        String message;
        if (operatingSystem.equalsIgnoreCase("Windows 10"))
        {
            message = "positive";
        }
        else if (operatingSystem.equalsIgnoreCase("Linux"))
        {
            message = "negative";
        }
        else message = "neutral";
        return unitTest.displaySystemProperties().equalsIgnoreCase("Operating System Architecture: " + p.getProperty("os.arch") + "\n" +
                                                                "Operating System Name: " + p.getProperty("os.name") + "\n" +
                                                                "Operating System Version: " + operatingSystem + "\n" +
                                                                "User Account Name: " + p.getProperty("user.name") + "\n" +
                                                                "Java Version: " + p.getProperty("java.version") + "\n" +
                                                                "Message: " + message);
    }

    //method that prints results of tests
    void runTest(String methodName, boolean functioningCorrectly)
    {
        printStringln(methodName + " is functioning correctly: " + functioningCorrectly);
    }
    
    public static void main(String args[])
    {
        SystemTest harness = new SystemTest();

        //arg[0] == "runtests" - complete unit testing of System methods
        //no args - program runs terminal menu
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
        }
        else harness.runMenu();
    }
}

