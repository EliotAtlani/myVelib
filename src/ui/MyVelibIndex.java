package ui;

import Classes.DataStore;

/**
 * This class contains the main void and allows to launch the initialisation and the command system 
 * 
 *
 * */
public class MyVelibIndex {

    /**
     * We start by create a new DataStore to store all the data we create
     */
    public static DataStore myVelibDatabase = new DataStore();

    //Here's the main void
    public static void main(String[] args){

        //Launch CLUI
        RunCommand();


    }

    private static void RunCommand() {
//Launch command CLUI
        MyVelibCLUI myVelibCLUI = new MyVelibCLUI();
        myVelibCLUI.start();
    }
    
//


    
}
