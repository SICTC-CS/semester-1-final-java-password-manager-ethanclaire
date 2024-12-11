package src;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


//souces:
///https://www.edureka.co/community/101325/how-do-i-check-if-a-file-exists-in-java#:~:text=To%20test%20to%20see%20if,directory%20exists%2C%20and%20false%20otherwise.
/// https://stackoverflow.com/questions/3753869/how-do-i-concatenate-two-strings-in-java 
/// 



// need these files to run this code, other files are made in code
/// accountNameList.txt
/// hintList.txt
/// passwordList.txt
/// usernameList.txt
/// 
/// javac PasswordGenerations


public class MainsPassword {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // successfulLogin(scanner,"Ethan Shrodes");
        // turn the passwordList into array list
        File passwordNamesListFile = new File("passwordList.txt");
        ArrayList<String> check = createList(passwordNamesListFile);

        boolean run=true;
        if (check.isEmpty() == true){
            System.out.println("First time login! Let's create an Account!");
            createAccount(scanner);
        }else{
        while (run) {
                System.out.println("Which would you like to do?");
                System.out.println("\t1. Create new Account\n\t2. Login\n\t3. View Account Names\n\t4. Exit");
                System.out.print("Enter your choice (1-4): ");
                int choiceFirst = scanner.nextInt();
    
                switch (choiceFirst) {
                    case 1://create account
                        createAccount(scanner);
                        break;
                    case 2://log in
                        logIn(scanner);
                        run = false;
                         break;
                    case 3:// see account names
                        File accNameLIst = new File("accountNameList.txt");
                        ArrayList<String> checkAccNameArrayList = createList(accNameLIst);
                        System.out.println(checkAccNameArrayList.toString());
                        
                        break;
                    case 4:
                        System.out.println("Now exiting...");
                        run = false;
                        break;
                    default:
                        System.out.println("no valid choice\nexiting......");
                        run =false;
                        break;
                }
            }
        }
     scanner.close();
    }

    public static void createAccount(Scanner scanner){//have the user create an account 
        newAccountName(scanner);
        newUsername(scanner);
        newPassword(scanner);
        newHint(scanner);
    }
    public static void logIn(Scanner scanner){
        
        // boolean hi = true;
        int wrongAttempt = 0;
        while (true) {
            //if users get attempt wrong 3 times then kick them out
            if (wrongAttempt == 3){
                System.out.println("shutting down");
                break;
            }

            //get the account name user index in the array then ask user for more info and compare to the same index where the account name is
            //  if right to successfulLogin() and if wrong end program
            while (true) {
                System.out.print("What is the Account name: ");
                String accName = scanner.next();
                File NamesListFile = new File("accountNameList.txt");
                
                int accNameInArrayList = checkEntriesInListForItem(NamesListFile, accName);
                if (accNameInArrayList == -1){
                    wrongAttempt++;
                    System.out.println("Attempt "+wrongAttempt+"/3");
                    break;
                }else{
                    System.out.print("What is the Username: ");
                    String userName = scanner.next();
                    File userNamesListFile = new File("usernameList.txt");
                    String correctUserName = getListNum(userNamesListFile, accNameInArrayList);
                    if (correctUserName.equals(userName)){
                        File hintListFile = new File("hintList.txt");
                        String correctHint = getListNum(hintListFile, accNameInArrayList);
                        System.out.println("\t\tHint: "+correctHint);
                        System.out.print("What is the Password: ");
                        String password = scanner.next();
                        File passwordNamesListFile = new File("passwordList.txt");
                        String correctpassword = getListNum(passwordNamesListFile, accNameInArrayList);
                        if (correctpassword.equals(password)){
                            System.out.println("You've logged in successfully!");
                            successfulLogin(scanner, accName);
                            wrongAttempt = 3;
                            break;
                        }else{
                            System.out.println("Wrong password");
                            wrongAttempt++;
                            System.out.println("Attempt "+wrongAttempt+"/3");
                            break;

                        }

                    }else{
                        System.out.println("Sorry, your Username doesn't match the account name. Goodbye!");
                        wrongAttempt++;
                        System.out.println("Attempt "+wrongAttempt+"/3");
                        break;
                    }
                }
            }
        }
    }

    public static String newHint(Scanner ui){// ask user to give hint for account
        System.out.print("Create a password hint for yourself: ");
        String hint = ui.next();
        
        File file = new File("hintList.txt");
        
        try (FileWriter fr = new FileWriter(file, true)) {
            fr.write(hint+"\n");
            fr.close(); //append code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
        } catch (IOException e) {
            System.err.println("An error with hint"+e);
        }

        return hint;
    }


    public static String newCategory(Scanner ui, String accName){////assign your account to a category
        System.out.println("what category is your account: \n\t1.Business\n\t2.School\n\t3.Personal");
        String cat = ui.next();
        while (true){
            if (cat.equals("1")||cat.equals("2")||cat.equals("3")){
                break;
            }
            System.out.println("what category is your account: \n\t1.Business\n\t2.School\n\t3.Personal");
            cat = ui.next(); 
        }
        //make the new inputs only be saved at the end 
        //remove once when nearing end 
        File file = new File(accName+"CategoryList.txt");
        
        try (FileWriter fr = new FileWriter(file, true)) {
            fr.write(cat+"\n");
            fr.close(); //append code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
        } catch (IOException e) {
            System.err.println("An error with cat");
        }
        
        return cat;
    }

    public static String newPassword(Scanner ui){ //make a new password  
        System.out.println("\n\tWould you like to \n\t\t1. Enter your own password\n\t\t2.Generate a password");
        System.out.println("\nEnter choice (1-2)");
        int choice = 0;
        try {
            choice = ui.nextInt();
        } catch (Exception e) {
            
        }
        
        String pas;
        switch (choice) {
            case 1:
                PasswordCheck check = new PasswordCheck();
                pas = check.checkpassword(ui);
                break;
            case 2:
                PasswordGeneration gen = new PasswordGeneration();
                pas = gen.generatePassword();
                System.out.println("Your password is: "+pas);
                break;
            default:
                gen = new PasswordGeneration();
                pas = gen.generatePassword();
                System.out.println("Your password is: "+pas);
                break;
        }
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
            fr.close(); //append code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
        } catch (IOException e) {
            System.err.println("An error with name");
        }
        
        return name;
    }

    public static String newAccountName(Scanner ui){//set your account name   
        System.out.print("Set your account name (this should be unique): ");
        String acc = ui.next();
        
        //make the new inputs only be saved at the end 
        //remove once when nearing end 
        File file = new File("accountNameList.txt");
        
        try (FileWriter fr = new FileWriter(file, true)) {
            fr.write(acc+"\n");
            fr.close(); //append code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
        } catch (IOException e) {
            System.err.println("An error with acc");
        }
        
        return acc;
    }

    public static ArrayList<String> createList(File file){//return  an arrayList of a given file with each line being a different array var
        //turn file into an arrayList with each line being a new index
       
        ArrayList<String> list = new ArrayList<String>();
        try (Scanner s = new Scanner(file)) { 
            while (s.hasNext()) {
                list.add(s.next()); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return list;
    }

    public static String getListNum(File file,int c){//give the number for for he arrayList i.e. list[1] is "name" so function will return "name"
        
       
        ArrayList<String> list = new ArrayList<String>();
        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                list.add(s.next()); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return list.get(c);
    }

    public static int checkEntriesInListForItem(File file,String checkFor){//give a string and will return you the location in the array where the entry is 
        ArrayList<String> list = new ArrayList<String>();
        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                list.add(s.next()); 
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

    public static void successfulLogin(Scanner scanner, String accName){//let user decide what to do with their passwords and usernames 

        //create new files for the account name
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
            System.out.println("\nWhat would you like to do?:");
            System.out.println("1. Add an Account");
            System.out.println("2. See Accounts");
            System.out.println("3. Modify an Account");
            System.out.println("4. See categories");
            System.out.println("5. Exit");
            
            System.out.print("Enter your choice (1-5): ");

            int choice = scanner.nextInt();

            // Handle the user's choice using a switch-case statement
            switch (choice) {
                case 1: // add account to the master accounts data
                    System.out.print("Choose your username: ");
                    String name = scanner.next();

                        File file2 = new File(accName+"UsernameList.txt");
                
                        try (FileWriter fr = new FileWriter(file2, true)) {
                            fr.write(name+"\n");
                            fr.close() ;//append code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
                        } catch (IOException e) {
                            System.err.println("An error with name");
                        }

                        System.out.println("\n\tWould you like to \n\t\t1. Enter your own password\n\t\t2.Generate a password");
                    System.out.println("\nEnter choice (1-2)");
                    int ychoice = 2;
                    try {
                        ychoice = scanner.nextInt();
                    } catch (Exception e) {
                        
                    }
                    String pas;
                    switch (ychoice) {
                        case 1:
                            PasswordCheck check = new PasswordCheck();
                            pas = check.checkpassword(scanner);
                            break;
                        case 2:
                            PasswordGeneration gen = new PasswordGeneration();
                            pas = gen.generatePassword();
                            System.out.println("Your password is: "+pas);
                            break;
                        default:
                            gen = new PasswordGeneration();
                            pas = gen.generatePassword();
                            System.out.println("Your password is: "+pas);
                            break;
                        }
                    //make the new inputs only be saved at the end 
                    //remove once when nearing end 
                    File file = new File(accName+"PasswordList.txt");
                    
                    try (FileWriter fr = new FileWriter(file, true)) {
                        fr.write(pas+"\n");
                        fr.close();//apend code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
                    } catch (IOException e) {
                        System.err.println("An error with pas");
                    }
                        newCategory(scanner,accName);
                        break;
                case 2: //where you display content
                    System.out.println("would you like to see \n\t1. All accounts\n\t2. Buisness\n\t3. School\n\t4. Personal");
                    System.out.print("Enter your choice (1-4): ");
                    int choice5 = scanner.nextInt();

                    switch (choice5) {
                        case 1://print out all accounts
                            File passwordNamesListFile = new File(accName+"UsernameList.txt");
                            ArrayList<String> check = createList(passwordNamesListFile);
                            File usernameListFile = new File(accName+"PasswordList.txt");
                            ArrayList<String> check2 = createList(usernameListFile);

                            for(int i=0;i<check.size();i++){
                                System.out.println("Account: "+i);
                                System.out.println("\t- Username: "+check.get(i));
                                System.out.println("\t-Password: "+check2.get(i));
                                System.out.println("--------------------------------------------------------");
                            }

                            
                            break;
                        case 2://print account if they have 1(Buisness) in the CategoryList print that index from all other lists
                            File userFile = new File(accName+"UsernameList.txt");
                            ArrayList<String> uArrayList = createList(userFile);
                            File passwordFile = new File(accName+"PasswordList.txt");
                            ArrayList<String> pArrayList = createList(passwordFile);
                            File category = new File(accName+"CategoryList.txt");
                            ArrayList<String> cateList = createList(category);
                            for (int i =0;i<cateList.size();i++){
                                if (cateList.get(i).equals("1")) {
                                    System.out.println("Account: "+i);
                                    System.out.println("Username: "+uArrayList.get(i));
                                    System.out.println("Password: "+pArrayList.get(i)+"\n");
                                    System.out.println("--------------------------------------------------------");
                                }
                            }
                            break;
                        case 3://print account if they have 2(school) in the CategoryList print that index from all other lists
                            File schoolUserFile = new File(accName+"UsernameList.txt");
                            ArrayList<String> schoolUArrayList = createList(schoolUserFile);
                            File schoolPasswordFile = new File(accName+"PasswordList.txt");
                            ArrayList<String> schoolPArrayList = createList(schoolPasswordFile);
                            File schoolCategory = new File(accName+"CategoryList.txt");
                            ArrayList<String> schoolCateList = createList(schoolCategory);
                            for (int i = 0; i < schoolCateList.size(); i++) {
                                if (schoolCateList.get(i).equals("2")) { 
                                    System.out.println("Account: "+i);
                                    System.out.println("Username: "+schoolUArrayList.get(i));
                                    System.out.println("Password: "+schoolPArrayList.get(i) + "\n");
                                    System.out.println("--------------------------------------------------------");

                                }
                            }
                            break;
                        case 4://print account if they have 3(personal) in the CategoryList print that index from all other lists
                            File personalUserFile = new File(accName+"UsernameList.txt");
                            ArrayList<String> personalUArrayList = createList(personalUserFile);
                            File personalPasswordFile = new File(accName+"PasswordList.txt");
                            ArrayList<String> personalPArrayList = createList(personalPasswordFile);
                            File personalCategory = new File(accName+"CategoryList.txt");
                            ArrayList<String> personalCateList = createList(personalCategory);
                            for (int i = 0; i<personalCateList.size(); i++) {
                                if (personalCateList.get(i).equals("3")) { 
                                    System.out.println("Account: "+i);
                                    System.out.println("Username: "+personalUArrayList.get(i));
                                    System.out.println("Password: "+personalPArrayList.get(i) + "\n");
                                    System.out.println("--------------------------------------------------------");

                                }
                            }
                            break;

                        default:
                            break;
                    }
                    break;

                case 3://modify accounts
                    System.out.println("what would you like to do?");
                    System.out.println("\t1. Remove an account\n\t2.Modify an Account");
                    System.out.print("Enter your choice (1-2): ");
                    int choice2 = scanner.nextInt();

                        switch (choice2) {
                            case 1://remove the index of the accounts from other arrayLists
                                int userInput = 0;
                                File passwordNamesListFile2 = new File(accName+"UsernameList.txt");
                                ArrayList<String> checkList2 = createList(passwordNamesListFile2);
                                File usernameListFile2 = new File(accName+"PasswordList.txt");
                                ArrayList<String> check2two = createList(usernameListFile2);
                                System.out.println("which account?(account Number)");
                                System.out.println("Usernames: "+checkList2.toString()+"\nPasswords: "+check2two.toString());

                                for(int i=0;i<checkList2.size();i++){
                                    System.out.println("Account: "+i);
                                    System.out.println("\t- Username: "+checkList2.get(i));
                                    System.out.println("\t-Password: "+check2two.get(i));
                                    System.out.println("--------------------------------------------------------");
                                }
                    
                                try {
                                    userInput = scanner.nextInt ();
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
                            case 2://convert file into arrayList and modify thee index of the arrayLists
                                System.out.println("Which account? (account number)");
                                File usernameListFile3 = new File(accName+"UsernameList.txt");
                                ArrayList<String> checkList3 = createList(usernameListFile3);
                                File passwordListFile3 = new File(accName+"PasswordList.txt");
                                ArrayList<String> passwordNamesListFile3 = createList(passwordListFile3);
                                
                                for(int i=0;i<checkList3.size();i++){
                                    System.out.println("Account: "+i);
                                    System.out.println("\t- Username: "+checkList3.get(i));
                                    System.out.println("\t-Password: "+passwordNamesListFile3.get(i));
                                    System.out.println("--------------------------------------------------------");
                                }

                                

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
                                System.out.print("Enter your choice (1-2): ");
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
                                System.out.println("not a valid choice");
                            break;  
                        }
                    break;
                    case 4:
                        System.out.println("Categories are: ");
                        System.out.println("\tBusiness");
                        System.out.println("\tSchool");
                        System.out.println("\tPersonal");
                    break;
                    case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    continueRunning = false;  // Exit the loop and end the program
                    break;

                default:
                    System.out.println("Invalid choice, please select a number between 1 and 4");
            }
        }
    }
    public static void listToFile(File file,ArrayList<String> list){//write an ArrayList to a file with each index being a new line   
                    
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


