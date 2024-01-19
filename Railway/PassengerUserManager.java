import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class PassengerUserManager implements UserManager
{
    public ArrayList<Integer> money = new ArrayList<>(List.of(1000, 5000, 8000, 9000));
    public ArrayList<Passenger> passengers = new ArrayList<>();
    public static String currentPassenger;
    ArrayList<String> passengerNames = new ArrayList<>(List.of("A", "B", "C", "D"));
    ArrayList<String> passengerPasswords = new ArrayList<>(List.of("1111", "2222", "3333", "4444"));

    @Override
    public void login()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n******* PASSENGER LOGIN *******");
        System.out.print("UserName : ");
        String passengerName = scanner.nextLine();
        System.out.print("Password : ");
        String passengerPassword = scanner.nextLine();

        if (passengerNames.contains(passengerName)) {
            if (passengerPasswords.get(passengerNames.indexOf(passengerName)).equals(passengerPassword)) {
                this.currentPassenger = passengerName;
                passengerPage();
            } else {
                System.out.println("\tPassword Mismatch, Try Again");
                login();
            }
        } else {
            System.out.println("\tYou Don't Have Account, Create New Account");
            createUserAccount();
        }
    }

    @Override
    public void createUserAccount()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n******* CREATE PASSENGER ACCOUNT *******");
        System.out.print("Enter UserName : ");
        String newPassengerName = scanner.nextLine();
        System.out.print("Enter Password : ");
        String newPassengerPassword = scanner.nextLine();


        if (!passengerNames.contains(newPassengerName)) {
            passengerNames.add(newPassengerName);
            passengerPasswords.add(newPassengerPassword);
            System.out.println("\tAccount Created Successfully");
        } else {
            System.out.println("\tUsername already exists. Please choose another username.");
            createUserAccount();
        }
    }

    private void passengerPage()
    {

        PassengerBookingManager passengerBookingManager = new PassengerBookingManager();
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            System.out.println("\n******** Welcome Mr." + currentPassenger + " ********");
            System.out.println("""
                    1 - Book ticket
                    2 - View bookings
                    3 - Cancel ticket
                    4 - Wallet
                    5 - Back""");
            System.out.print("Choose your option : ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option)
            {
                case 1:
                    passengerBookingManager.bookTicket();
                    break;
                case 2:
                    passengerBookingManager.viewBookings();
                    break;
                case 3:
                    passengerBookingManager.cancelTicket();
                    break;
                case 4:
                    passengerBookingManager.wallet();
                    break;
                case 5:
                    System.out.println("Thank You For Visit");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }
}