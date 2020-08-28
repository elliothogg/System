/* This class can be used to create and manage computer system objects. It includes some "getter" and "setter"
 * methods, as well as some display methods.
 * 
 * This project was written as a University project.
 * 
 * @author	********
 * @version 1.3 (01 August 2020)
 * 
 */

import java.util.Properties;

public class System {
    
    //initialise variables and set to private
    private String make, model;
    private int speed, memorySize;
    private double hardDiskSize, purchaseCost;

    //constructor method to initialise the make, model, and speed
    System(String make, String model, int speed)
    {
        this.make = make;
        this.model = model;
        this.speed = speed;
    }

    //3 "setter" methods
    public void setMemory(int memorySize)
    {
        this.memorySize = memorySize;
    }
   
    public void setHardDisk(double hardDiskSize)
    {
        this.hardDiskSize = hardDiskSize;
    }

    public void setPurchaseCost(double purchaseCost)
    {
        this.purchaseCost = purchaseCost;
    }

    
    //3 "getter" methods
    public String getMake()
    {
        return make;
    }

    public String getModel()
    {
        return model;
    }

    public int getProcessorSpeed()
    {
        return speed;
    }

    //method that display all variables stored in the class
    public String displayDetails()
    {
        return "Make: " + make + "\n" +
                "Model: " + model + "\n" +
                "Speed: " + speed + "\n" +
                "Memory size: " + memorySize + "\n" +
                "Hard disk size: " + hardDiskSize + "\n" +
                "Purchase cost: " + purchaseCost;
    }

    //method that checks and validates size of system HD
    public String checkHDStatus()
    {
        //if statements to determine whether HD size is "low" or "OK"
        if (hardDiskSize < 2)
        {
            return "low";
        }
        else return "OK";
    }

    //method that checks and validates size of system memory
    public boolean goodMemorySize()
    {
        //if statements to determine whether memory size is "good"
        if (memorySize < 128)
        {
            return false;
        }
        else return true;
    }

    //method that uses checkHDstatus and goodMemorySize methods to "diagnose system"
    public String diagnoseSystem()
    {
        return "Hard Disk Size = " + checkHDStatus() + "\n" +
                "Memory Size OK = " + goodMemorySize();
    }

    //method that displays some properties of the system (os.name, os.version, java.version etc...)
    public String displaySystemProperties()
    {
        //Create new property object (reference to System using full path name to avoid name clash with Class)
        Properties p = new Properties(java.lang.System.getProperties());

        //create variables to store operating system and message
        String operatingSystem = p.getProperty("os.version");
        String message;

        //if statements to determine whether message is positive, negative, or neutral
        if (operatingSystem.equalsIgnoreCase("Windows 10"))
        {
            message = "positive";
        }
        else if (operatingSystem.equalsIgnoreCase("Linux"))
        {
            message = "negative";
        }
        else message = "neutral";

        //return all details on seperate lines
        return "Operating System Architecture: " + p.getProperty("os.arch") + "\n" +
                "Operating System Name: " + p.getProperty("os.name") + "\n" +
                "Operating System Version: " + operatingSystem + "\n" +
                "User Account Name: " + p.getProperty("user.name") + "\n" +
                "Java Version: " + p.getProperty("java.version") + "\n" +
                "Message: " + message;
    }
}