import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class PasswordManagerFunctions{

    

    public static ArrayList<String> createList(File file){//return  an arrayList of a given file with each line being a differnt array var
							  //test
        
       
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

    public static String checkEntriesInListForItem(File file,String checkFor){//give a string and will return you the loaction in the array when the entry is 
        ArrayList<String> list = new ArrayList<String>();
        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                list.add(s.nextLine()); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String entry : list) {
            if (entry.equals(checkFor)) {
                return entry; 
            }
        }

        return "no macht";
    }

    public static void main(String[] args) {
        Scanner ui = new Scanner(System.in);

        
        // String category = newCategory(ui);
        // System.out.println("Selected category: " + category);
        File file = new File("categoryList.txt");
        System.out.println(createList(file));
        
        System.out.println("\n\n\n"+getListNum(file, 3)+"\n\n\n\n");

        System.out.println(checkEntriesInListForItem(file, "6"));

        ui.close();
    }
    // && -(and) means both to the left and right are true
    // || - (or) means only one needs to be true

    public static String newCategory(Scanner ui){////assign your account to a category
        System.out.println("what category is your actount: \n\t1.Bussines\n\t2.School\n\t3.Personal");
        String cat = ui.nextLine();
        while (!cat.equals("1")||!cat.equals("2")||!cat.equals("3")){
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
        System.out.println("create your passowrd: ");
        String pas = ui.nextLine();
        
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
        System.out.println("Choose your username: ");
        String name = ui.nextLine();

        
        //make the new inputs only be saved at the end 
        //remove once when nearing end 
        File file = new File("passwordList.txt");
        
        try (FileWriter fr = new FileWriter(file, true)) {
            fr.write(name+"\n");
            fr.close();//apend code from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
        } catch (IOException e) {
            System.err.println("An error with name");
        }
        
        return name;
    }

    public static String newAccountName(Scanner ui){//set your account name   
        System.out.println("Set your account name: ");
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


}
