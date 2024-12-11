package src;
import java.util.Random;

public class PasswordGeneration {

    public String generatePassword() {
        String[]upper={"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"};
        String[]lower={"q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m"};
        String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
        String[] special = {"!","@","#","$","%","^","&","*","=","+","?"};
    
        Random random = new Random();
        String pas = "";
        
        String[] addThis = null;
        for (int j = 0; j < 16; j++) {  
            int num = random.nextInt(4);  
    
            if (num == 0) {
                addThis = upper;
            } else if (num == 1) {
                addThis = lower;
            } else if (num == 2) {
                addThis = numbers;
            } else {
                addThis = special;
            }
    
            String selectedChar = addThis[random.nextInt(addThis.length)];
            pas += selectedChar;
    
        }
        //to make sure it meets requirments
        pas += "-";
        pas += upper[random.nextInt(upper.length)];
        pas += lower[random.nextInt(lower.length)];
        pas += numbers[random.nextInt(numbers.length)];
        pas += special[random.nextInt(special.length)];
    
        return pas;
    }

    public static void main(String[] args) {
        PasswordGeneration generator = new PasswordGeneration();
        String password = generator.generatePassword();
        System.out.println(password);
    }
}