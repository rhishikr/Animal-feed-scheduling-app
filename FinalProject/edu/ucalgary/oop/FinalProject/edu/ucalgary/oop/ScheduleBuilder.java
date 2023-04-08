package FinalProject.edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;


public class ScheduleBuilder {

    public Connection myConnect;
    public String[][] tasks;
    public String[][] animals;
    public String[][] treatments;


    public ScheduleBuilder(){
        createConnection();
        retrieveAnimals();
        retrieveTasks();
        retrieveTreatment();
    }

    public void createConnection(){
                
        try{
            myConnect = DriverManager.getConnection("jdbc:mysql://localhost/ewr", "root", "Jawad195");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int countRows(String tablename){

        try {
            Statement stmt = myConnect.createStatement();

            String query = "SELECT COUNT(*) FROM " + tablename;

            ResultSet result = stmt.executeQuery(query);

            result.next();
            int rowCount = result.getInt(1);
            return rowCount;
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        
        
    }


    public void retrieveAnimals(){
        try {
            Statement myStmt = myConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM animals");

            String[][] table = new String[countRows("animals")][3];
            int i = 0;
            while (results.next()){
                table[i][0] = results.getString("animalID");
                table[i][1] = results.getString("animalnickname");
                table[i][2] = results.getString("animalspecies");

                i = i +1;
              
            }
            myStmt.close();
            results.close();
            this.animals = table;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    } 

    public void retrieveTasks(){
        try {
            Statement myStmt = myConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM tasks");

            String[][] table = new String[countRows("tasks")][4];
            int i = 0;
            while (results.next()){
                table[i][0] = results.getString("taskID");
                table[i][1] = results.getString("description");
                table[i][2] = results.getString("Duration");
                table[i][3] = results.getString("maxwindow");
                i = i +1;
              
            }
            myStmt.close();
            results.close();
            this.tasks = table;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    } 

    public void retrieveTreatment(){
        try {
            Statement myStmt = myConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM treatments");

            String[][] table = new String[countRows("treatments")][3];
            int i = 0;
            while (results.next()){
                table[i][0] = results.getString("animalID");
                table[i][1] = results.getString("taskid");
                table[i][2] = results.getString("starthour");

                i = i +1;
              
            }
            myStmt.close();
            results.close();
            this.treatments = table;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    } 


    public String[][] getTasks() {
        return tasks;
    }

    public String[][] getAnimals() {
        return animals;
    }

    public String[][] getTreatments() {
        return treatments;
    }







public static void main(String[] args) {

    //This is where we will actually make the schedule using all classes and methods


    ScheduleBuilder schedule = new ScheduleBuilder();
    int rowsTreatment = schedule.countRows("treatments");

    MedicalTask medicalTask = new MedicalTask(schedule.getAnimals(),schedule.getTasks(),schedule.getTreatments(),schedule.countRows("treatments"));
    //get the information from medical task
  
    //find the hour that has the most tasks and save this in a variable
    //set the second half of the array list ot the most hours
    //Ahmed gives [Taskname  duration  starthour  animalnickname]

    ArrayList<String[]> medicalTasks = medicalTask.getInfo();


    for(int i = 0;i<medicalTasks.size();i++){

        System.out.println(medicalTasks.get(i)[i]);

    }
        


    }







    // I put them into hour array based on start hour




    // I give task class these Taskname,quantity, duration



   
            
}




