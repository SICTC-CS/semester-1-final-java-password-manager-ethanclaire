import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


//souces:
///https://www.edureka.co/community/101325/how-do-i-check-if-a-file-exists-in-java#:~:text=To%20test%20to%20see%20if,directory%20exists%2C%20and%20false%20otherwise.
/// 
/// 

/// TODO (ethan)
/// 
///Password Generation:
///

// need these files to run this code, other files are made in code
///
/// hintList.txt
/// passwordList.txt
/// usernameList.txt
/// 


public class MainsPassword {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File passwordNamesListFile = new File("passwordList.txt");
        ArrayList<String> check = createList(passwordNamesListFile);

        
        if (check.isEmpty() == true){
            System.out.println("first time login! Create an Account");
            createAccount(scanner);
        }else{
            logIn(scanner);
        }

        scanner.close();

    }

    public static void createAccount(Scanner scanner){
        newUsername(scanner);
        newPassword(scanner);
        newHint(scanner);
        newAccountName(scanner);
        



    }

    public static void logIn(Scanner scanner){
        
        // boolean hi = true;
        int wrongAttempt = 0;
        while (true) {
            
            if (wrongAttempt == 3){
                System.out.println("shutting down");
                break;
            }

            while (true) {
                
                System.out.print("What is the Account name: ");
                String accName = scanner.next();
                File NamesListFile = new File("accountNameList.txt");
                // ArrayList<String> NameList = createList(NamesListFile);
        
                int accNameInArrayList = checkEntriesInListForItem(NamesListFile, accName);
                if (accNameInArrayList == -1){
                    wrongAttempt++;
                    System.out.println("Attempt "+wrongAttempt+"/3");
                    break;
                }else{
                    System.out.print("What is the Username: ");
                    String userName = scanner.next();
                    File userNamesListFile = new File("usernameList.txt");
                    // ArrayList<String> userNameList = createList(userNamesListFile);
                    String correctUserName = getListNum(userNamesListFile, accNameInArrayList);
                    if (correctUserName.equals(userName)){
                        File hintListFile = new File("hintList.txt");
                        String correctHint = getListNum(hintListFile, accNameInArrayList);
                        System.out.println("\t\tHint: "+correctHint);
                        System.out.print("What is the Password: ");
                        String password = scanner.next();
                        File passwordNamesListFile = new File("passwordList.txt");
                        // ArrayList<String> passwordList = createList(passwordNamesListFile);
                        String correctpassword = getListNum(passwordNamesListFile, accNameInArrayList);
                        if (correctpassword.equals(password)){
                            System.out.println("Yor've logged in");
                            successfulLogin(scanner, accName);
                            wrongAttempt = 3;
                            break;
                        }else{//
                            System.out.println("Wrong password");
                            wrongAttempt++;
                            System.out.println("Attempt "+wrongAttempt+"/3");
                            break;

                        }

                    }else{
                        System.out.println("Sorry, your Username deson't match the Account nam, Goodbye!");
                        wrongAttempt++;
                        System.out.println("Attempt "+wrongAttempt+"/3");
                        break;
                    }
                }

            }


        }
          


    }

    public static String newHint(Scanner ui){
        System.out.print("Give a password hint for yourself: ");
        String hint = ui.next();

        File file = new File("hintList.txt");
        
        try (FileWriter fr = new FileWriter(file, true)) {
            fr.write(hint+"\n");
            fr.close();//apend code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
        } catch (IOException e) {
            System.err.println("An error with hint"+e);
        }

        return hint;
    }


    public static String newCategory(Scanner ui){////assign your account to a category
        System.out.println("what category is your actount: \n\t1.Bussines\n\t2.School\n\t3.Personal");
        String cat = ui.next();
        while (true){
            if (cat.equals("1")||cat.equals("2")||cat.equals("3")){
                break;
            }
            System.out.println("what category is your actount: \n\t1.Bussines\n\t2.School\n\t3.Personal");
            cat = ui.nextLine(); 
        }
        //make the new inputs only be saved at the end 
        //remove once when nearing end 
        File file = new File("categoryList.txt");
        
        try (FileWriter fr = new FileWriter(file, true)) {
            fr.write(cat+"\n");
            fr.close();//apend code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
        } catch (IOException e) {
            System.err.println("An error with cat");
        }
        
        return cat;
    }

    public static String newPassword(Scanner ui){//make a new password  
        System.out.print("create your passowrd: ");
        String pas = ui.next();
        
        //make the new inputs only be saved at the end 
        //remove once when nearing end 
        File file = new File("passwordList.txt");
        
        try (FileWriter fr = new FileWriter(file, true)) {
            fr.write(pas+"\n");
            fr.close();//apend code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
        } catch (IOException e) {
            System.err.println("An error with pas");
        }
        
        return pas;
    }

    public static String newUsername(Scanner ui){//make a new username 
        System.out.print("Choose your username: ");
        String name = ui.next();

        
        //make the new inputs only be saved at the end 
        //remove once when nearing end 
        File file = new File("usernameList.txt");
        
        try (FileWriter fr = new FileWriter(file, true)) {
            fr.write(name+"\n");
            fr.close();//apend code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
        } catch (IOException e) {
            System.err.println("An error with name");
        }
        
        return name;
    }

    public static String newAccountName(Scanner ui){//set your account name   
        System.out.print("Set your account name (this should be unique): ");
        String acc = ui.nextLine();
        
        //make the new inputs only be saved at the end 
        //remove once when nearing end 
        File file = new File("accountNameList.txt");
        
        try (FileWriter fr = new FileWriter(file, true)) {
            fr.write(acc+"\n");
            fr.close();//apend code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
        } catch (IOException e) {
            System.err.println("An error with acc");
        }
        
        return acc;
    }

    public static ArrayList<String> createList(File file){//return  an arrayList of a given file with each line being a differnt array var
        
       
        ArrayList<String> list = new ArrayList<String>();
        try (Scanner s = new Scanner(file)) { 
            while (s.hasNext()) {
                list.add(s.nextLine()); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return list;
    }

    public static String getListNum(File file,int c){//give the number for for he arrayLsit i.e. list[1] is "name" so function will return "name"
        
       
        ArrayList<String> list = new ArrayList<String>();
        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                list.add(s.nextLine()); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return list.get(c);
    }

    public static int checkEntriesInListForItem(File file,String checkFor){//give a string and will return you the loaction in the array where the entry is 
        ArrayList<String> list = new ArrayList<String>();
        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                list.add(s.nextLine()); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(checkFor)) {
                return i; 
            }
        }

        return -1;
    }

    public static void passwordHint(Scanner scanner, String correctpassword){

    }

    public static void successfulLogin(Scanner scanner, String accName){

        File f = new File(accName+"PasswordList.txt");
        if(!f.exists() && !f.isDirectory()) { 
            try {
                f.createNewFile();
            } catch (Exception e) {
                
            } 
        }

        File f2 = new File(accName+"UsernameList.txt");
        if(!f2.exists() && !f.isDirectory()) { 
            try {
                f2.createNewFile();
            } catch (Exception e) {
                
            }
        }

        File f3 = new File(accName+"CategoryList.txt");
        if(!f3.exists() && !f.isDirectory()) { 
            try {
                f3.createNewFile();
            } catch (Exception e) {
                
            }
        }

        boolean continueRunning = true;

        // Main loop for showcasing exceptions
        while (continueRunning) {
            // Display the menu of options
            System.out.println("\nSelect an exception to showcase:");
            System.out.println("1. Add an Account");
            System.out.println("2. See Acounts");
            System.out.println("3. Modify an Account");
            System.out.println("4. NumberFormatException");
            System.out.println("5. IllegalArgumentException");
            System.out.println("6. RuntimeException");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");

            int choice = scanner.nextInt();

            // Handle the user's choice using a switch-case statement
            switch (choice) {
                case 1:
                System.out.print("Choose your username: ");
                String name = scanner.next();

                File file2 = new File(accName+"UsernameList.txt");
        
                try (FileWriter fr = new FileWriter(file2, true)) {
                    fr.write(name+"\n");
                    fr.close();//apend code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
                } catch (IOException e) {
                    System.err.println("An error with name");
                }

                System.out.print("create your passowrd: ");
                String pas = scanner.next();
                
                //make the new inputs only be saved at the end 
                //remove once when nearing end 
                File file = new File(accName+"PasswordList.txt");
                
                try (FileWriter fr = new FileWriter(file, true)) {
                    fr.write(pas+"\n");
                    fr.close();//apend code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
                } catch (IOException e) {
                    System.err.println("An error with pas");
                }
                    newCategory(scanner);
                    break;
                case 2://where you display content
                    System.out.println("would you like to see \n\t1. Alls acounts\n\t2. Bussines\n\t3.School\n\t4.Personal");
                    System.out.print("Enter your choice (1-4): ");
                    int choice5 = scanner.nextInt();

                    switch (choice5) {
                        case 1:
                            File passwordNamesListFile = new File(accName+"UsernameList.txt");
                            ArrayList<String> check = createList(passwordNamesListFile);
                            File usernameListFile = new File(accName+"PasswordList.txt");
                            ArrayList<String> check2 = createList(usernameListFile);
                            System.out.println("Usernames: "+check.toString()+"\nPasswords: "+check2.toString());
                            break;
                        case 2:
                            File userFile = new File(accName+"UsernameList.txt");
                            ArrayList<String> uArrayList = createList(userFile);
                            File passwordFile = new File(accName+"PasswordList.txt");
                            ArrayList<String> pArrayList = createList(passwordFile);
                            File category = new File(accName+"CategoryList.txt");
                            ArrayList<String> cateList = createList(category);
                            for (int i =0;i<cateList.size();i++){
                                if (cateList.get(i).equals("1")) {
                                    System.out.println("Username: "+uArrayList.get(i));
                                    System.out.println("Password: "+pArrayList.get(i)+"\n");
                                }
                            }

                            break;
                        case 3:
                            File schoolUserFile = new File(accName + "UsernameList.txt");
                            ArrayList<String> schoolUArrayList = createList(schoolUserFile);
                            File schoolPasswordFile = new File(accName + "PasswordList.txt");
                            ArrayList<String> schoolPArrayList = createList(schoolPasswordFile);
                            File schoolCategory = new File(accName + "CategoryList.txt");
                            ArrayList<String> schoolCateList = createList(schoolCategory);
                            for (int i = 0; i < schoolCateList.size(); i++) {
                                if (schoolCateList.get(i).equals("2")) { 
                                    System.out.println("Username: " + schoolUArrayList.get(i));
                                    System.out.println("Password: " + schoolPArrayList.get(i) + "\n");
                                }
                            }
                            break;
                        case 4:
                            File personalUserFile = new File(accName + "UsernameList.txt");
                            ArrayList<String> personalUArrayList = createList(personalUserFile);
                            File personalPasswordFile = new File(accName + "PasswordList.txt");
                            ArrayList<String> personalPArrayList = createList(personalPasswordFile);
                            File personalCategory = new File(accName + "CategoryList.txt");
                            ArrayList<String> personalCateList = createList(personalCategory);
                            for (int i = 0; i < personalCateList.size(); i++) {
                                if (personalCateList.get(i).equals("3")) { 
                                    System.out.println("Username: " + personalUArrayList.get(i));
                                    System.out.println("Password: " + personalPArrayList.get(i) + "\n");
                                }
                            }
                            break;
                        default:
                            System.out.println("not a vaild choice");
                            break;
                    }

                    break;
                case 3://modify accounts
                    System.out.println("what would you like to do?");
                    System.out.println("\t1. Remove an account\n\t2.Modify an Account");
                    int choice2 = scanner.nextInt();

                        switch (choice2) {
                            case 1:
                                int userInput = 0;
                                File passwordNamesListFile2 = new File(accName+"UsernameList.txt");
                                ArrayList<String> checkList2 = createList(passwordNamesListFile2);
                                File usernameListFile2 = new File(accName+"PasswordList.txt");
                                ArrayList<String> check2two = createList(usernameListFile2);
                                System.out.println("which account?(account Number)");
                                System.out.println("Usernames: "+checkList2.toString()+"\nPasswords: "+check2two.toString());
                    
                                try {
                                    userInput = scanner.nextInt();
                                } catch (Exception e) {
                                    System.out.println("give an int next time, bye\n"+e);
                                    break;
                                }
                                int realInput = Math.abs(userInput) - 1;
                                if (createList(new File(accName+"PasswordList.txt")).size() <= realInput){
                                    System.out.println("No account "+userInput);
                                }

                                

                                checkList2.remove(realInput);
                                check2two.remove(realInput);

                                listToFile(passwordNamesListFile2, checkList2);
                                listToFile(usernameListFile2, check2two);
                                System.out.println("Account "+ userInput+" has been removed");
                                break;
                            case 2:
                                System.out.println("Which account? (account number)");
                                File usernameListFile3 = new File(accName+"UsernameList.txt");
                                ArrayList<String> checkList3 = createList(usernameListFile3);
                                File passwordListFile3 = new File(accName+"PasswordList.txt");
                                ArrayList<String> passwordNamesListFile3 = createList(passwordListFile3);

                                System.out.println("Usernames: "+checkList3.toString());
                                System.out.println("Passwords: "+passwordNamesListFile3.toString());

                                int userInput2 = 0;
                                try {
                                    userInput2 = scanner.nextInt();
                                } catch (Exception e) {
                                    System.out.println("give an int next time, bye\n"+e);
                                    break;
                                }
                                int realInput2 = Math.abs(userInput2) - 1;
                                if (createList(new File(accName+"PasswordList.txt")).size() <= realInput2){
                                    System.out.println("No account "+userInput2);
                                }
                                
                                System.out.println("What would you like to modify?");
                                System.out.println("\t1. Username\n\t2. Password");
                                int choice3 = scanner.nextInt();
                                switch (choice3) {
                                    case 1: 
                                        System.out.println("new username: ");
                                        String newUsername = scanner.next();
                                        checkList3.set(realInput2, newUsername); 
                                        listToFile(usernameListFile3, checkList3); 
                                        System.out.println("Account "+userInput2+"'s username has changed");
                                        break;
                                    case 2: 
                                        System.out.println("new password: ");
                                        String newPassword = scanner.next();
                                        passwordNamesListFile3.set(realInput2, newPassword); 
                                        listToFile(passwordListFile3, passwordNamesListFile3);
                                        System.out.println("Account "+userInput2+"'s password has changed");
                                        break;
                                    default:
                                        System.out.println("Not a valid choice");
                                        break;
                                }
                                break;        
                            default:
                            System.out.println("not a vaild choice");
                            break;  
                        }
                    break;
                case 4:
                    // handleNumberFormatException(scanner);
                    break;
                case 5:
                    // handleIllegalArgumentException(scanner);
                    break;
                case 6:
                    // handleRuntimeException(scanner);
                    break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    continueRunning = false;  // Exit the loop and end the program
                    break;
                default:
                    System.out.println("Invalid choice, please select a number between 1 and 7.");
            }
        }
    }


    public static void listToFile(File file,ArrayList<String> list){//set your account name   
        
        
        
            try (FileWriter fr = new FileWriter(file)) {
                for (int i =0;i<list.size();i++) {
                fr.write(list.get(i)+"\n");  // Write each string to file, followed by a newline
                }
                fr.close();
            }catch (IOException e) {
                System.err.println("An error with listToFile\n"+e);
            }
            
    }

        
    

}

