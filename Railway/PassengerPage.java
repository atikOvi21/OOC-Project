import java.util.Scanner;

public class PassengerPage
{
    private UserManager userManager;
    private Scanner scanner;

    public PassengerPage(UserManager userManager)
    {
        this.userManager = userManager;
    }

    public void passengerPage()
    {
        if (userManager instanceof PassengerUserManager)
        {
            boolean isPassenger = true;
            while (isPassenger)
            {
                System.out.println("\n******** PASSENGER ********");
                System.out.println("""
                        1 - Login
                        2 - Create Account
                        3 - Back""");
                System.out.print("Choose your option : ");
                int userOption = scanner.nextInt();
                this.scanner = new Scanner(System.in);
                scanner.nextLine();

                switch (userOption)
                {
                    case 1:
                        userManager.login();

                        break;
                    case 2:
                        userManager.createUserAccount();
                        break;
                    case 3:
                        System.out.println("\tThank You For Your Visit");
                        isPassenger = false;
                        break;
                    default:
                        System.out.println("Invalid Option!, Try again");
                }
            }
        }
        else
        {
            System.out.println("Invalid Option!, Try again");
            userManager.login();
            passengerPage();
        }
    }


}
