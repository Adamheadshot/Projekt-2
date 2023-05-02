
import java.util.Scanner; 

public class Proj2 {
    //global variabels 
    static Scanner scan = new Scanner(System.in); 
    static int []seats = new int[21]; 
    static String []name = new String[21]; 
    static String []gender = new String[21]; 
    static int sNumber = 0;

    //Det här är min main metod, alla andra metoder ligger i en while loop eftersom de måste köras tills man själv väljer menyalternativet att stänga programmet
    public static void main(String[] args) {
        while(true){
            String choiceOfMenu = menu(); 
            if(choiceOfMenu.equalsIgnoreCase("A")){
                reserveSeat();
            }
            else if (choiceOfMenu.equalsIgnoreCase("B")){
                totSeat();
            } 
            else if (choiceOfMenu.equalsIgnoreCase("C")){
                System.out.println(Profit(0));
    
            }
            else if (choiceOfMenu.equalsIgnoreCase("D")){
                break; 
            }
            else if (choiceOfMenu.equalsIgnoreCase("E")){
                int booking = 0; 
                System.out.println("Type the social security number");
                booking = scan.nextInt(); 
                scan.nextLine();
                findBooking(booking);
            }
            else if (choiceOfMenu.equalsIgnoreCase("F")){
                int cancel = 0; 
                try{
                    System.out.println("Type the social security number you want to cancel");
                    cancel = scan.nextInt(); 
                    scan.nextLine(); 
                }
                catch(Exception e){
                    System.out.println("That is not valid input type your social security number please");
                    scan.nextLine(); 
                    continue;
                }
                  cancelBooking(cancel);

            }
        }   
    }
// det här är min meny metod där alla val i programmet kan göras. Den är uppbygd av en while loop. En foor loop som kontrollerar att det som skrivs in kan vara en stor eller liten bokstav. Och den retunerar valet (Choice)
    static String menu(){
        String choice ="";
        while(true){
            System.out.println("Menu");
            System.out.println("[A] Book an available seat");
            System.out.println("[B] Print out how many available seats there is on the bus");
            System.out.println("[C] Calculate the profit of sold tickets");
            System.out.println("[D] End the program");
            System.out.println("[E] Find Booking");
            System.out.println("[F] Cancel Booking");
            choice = scan.nextLine(); 
            switch(choice){
                case "A": 
                    reserveSeat();
                    break; 

                case "B": 
                    totSeat();
                    break; 

                case "C": 
                    Profit(0); 
                    break; 

                case "D": 
                    findBooking(0);
                    break; 

                case "E": 
                    cancelBooking(sNumber);
                    break; 
                default: 



            }
            String word[] = {"A", "B", "C","D", "E", "F",};
            boolean found = false; 

            for(String element: word){
                if(element.toLowerCase().equals(choice.toLowerCase())) {
                    found = true;
                    break;
                }
            }

            if (found) break;
              System.out.println("That is not a valid input, chose between A, B, C, D, E or F");
        }

        System.out.println("Exellent choice"); 
        return choice; 
    }
    
    //här är metoden för att reservera en plats i bussen. Den är uppbygd av en while loop som tar in personnummret från användaren kontrollerar den med hjälp av en try catch för att sedan i en for loop kunna ge ut en sittplats till användaren. 
    static void reserveSeat(){
        String tempName = ""; 
        String tempGender = ""; 
        while(true){
            try{
                System.out.println("Type your social security number please:");
                sNumber = scan.nextInt(); 
                scan.nextLine();

                System.out.println("Type your name please: ");
                tempName = scan.nextLine(); 

                System.out.println("Type your Gender please: ");
                tempGender = scan.nextLine(); 
                }
                catch(Exception e){
                System.out.println("that is not valid input type your social security number please");
                scan.nextLine(); 
                continue;
            }
            if(sNumber < 1900011 || sNumber > 20231231){
                System.out.println("Type a social security number ");
                scan.nextLine();
                continue; 
            }     
            break;
        }

        
         for (int i = 0; i < seats.length; i++){
            if (seats [i] == 0){
                seats[i] = sNumber; 
                name[i] = tempName; 
                gender[i] = tempGender;
                System.out.println("The seat number you have is "+i);
                break;
            }
         }
    }

    // Detta är metoden för att kolla hur många lediga platseer det finns på bussen. Metoden är uppbyggd av en for loop som loopar igenom arrayen "seats" och om en plats är lika med 0 då plussas ett till variabeln "tot" som sedan printas ut till användaren. 
    static void totSeat(){
        int tot = 0; 
        for (int i = 0; i < seats.length; i++){
            if (seats [i] == 0){
                tot++;
            }
        }
        System.out.println("This is how many available seats there is on the bus: "+tot);
    }

    // Den är metoden räknar ut vinsten av biljetterna. Den är uppbyggd av en for loop som loopar igenom arrayen "seats" och om en plats inte är lika med 0 då plussas ett till variabeln "profit".  Variabeln profit multipliceras sedan med kostnaden av en biljett (299.90) som sedan printas ut till användaren 
    static double Profit(int index){
        if (index==21) return 0; 
        int s_Number = seats[index]; 
        if(s_Number == 0) return + Profit(index+1); 
        
         String yearIndexStr = Integer.toString(s_Number).substring(0, 4);
         int yearIndexInt = Integer.parseInt(yearIndexStr); 

         //Detta är ett mer effiktivt sätt att skriva koden på.     
        // int yearIndexInt = s_Number/ 10000; 

        double price = 0; 
        if(yearIndexInt <= 2005 && yearIndexInt > 1963)
        price = 299.90; 
        else if (yearIndexInt > 2005)
        price = 149.90; 
        else if (yearIndexInt <= 1963)
        price = 200.0; 
        return price + Profit(index + 1); 

    }
        
        

    

    // Denna metoden är till för att hitta en plats i bussen den är uppbyggd av en for loop som 
    static void findBooking(int social_nr){
                for(int i = 0; i < seats.length; i++){
                    if(seats[i] == social_nr){
                        System.out.println("The seat number is "+i);
                    }
                    else System.out.println("The social security number does not exist");
                    break; 
                    
        }
    }
    static void cancelBooking(int cancel_seat){
        for (int i = 0; i < seats.length; i++){
            if(seats[i] == cancel_seat){
                seats[i] = 0; 
                System.out.println("Your seat is canceled");
                return;
            }
        }
        System.out.println("The social security number does not match with any booking.");

    }

    
}

