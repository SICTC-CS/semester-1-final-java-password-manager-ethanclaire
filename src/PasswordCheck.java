package src;
import java.util.Scanner;

public class PasswordCheck {
    public String checkpassword(Scanner ui){

        System.out.println("Please create your password");
        String pass = ui.next();


        char[] special = {'!','@','#','$','%','^','&','*','=','+','?'};

        char[] password = pass.toCharArray();
        
        boolean upper = false;
        boolean num = false;
        boolean specchar = false;
        boolean leng = false;
        while(!upper && !num && !specchar && !leng){
            for(char e : password){
                if(Character.isUpperCase(e)){
                    upper = true;
                    
                }
                else if(Character.isDigit(e)){
                    num = true;
                }
                else if(pass.length()>=8){
                    leng = true;
                }
                for( char a : special){
                    if(e==a){
                        specchar = true;
                    }
                }
            }

            if(!upper || !num || !specchar || !leng) {
                System.out.println("Password does not meet requirements");
                System.out.println("Please enter a new password");
                pass = ui.next();
            }
        }
        return pass;
    }
    
}
