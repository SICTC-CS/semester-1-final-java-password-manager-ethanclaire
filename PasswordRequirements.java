


public class PasswordRequirements {

    public String meetRequirements(String password){

        boolean hi = true;
        char[]upper={'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};
        char[]lower={'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
        char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};
        char[] special = {'!','@','#','$','%','^','&','*','=','+','?'};



if (password.length() >= 8 && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") ){
     hi = false;

    
    for (int i = 0; i < password.length(); i++) {
        char c = password.charAt(i);
        for (char specialChar : special) {
            if (c == specialChar) {
                hi = true;
                break;
            }
        }
        if (hi) break;
    }

    if (!hi) {
        System.out.println("Your password must contain at least one special character.");
        password = null;
    } else {
        System.out.println(password);
    }
} else {
    System.out.println("Your password doesn't meet the basic requirements.");
    password = null;
}

        
    
    
        
        
        
        return password;
    }

    

    public static void main(String[] args) {

        
        // meetRequirements(hi);

        // System.out.println(meetRequirements(hi));
    }

}
