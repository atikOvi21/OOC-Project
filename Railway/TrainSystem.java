import java.util.Scanner;

public class TrainSystem
{
    private UserManager userManager;
    private TrainManager trainManager;
    public void home() {
        PassengerUserManager passengerUserManager = new PassengerUserManager();
        AdminUserManager adminUserManager = new AdminUserManager();
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (isRunning)
        {
            System.out.println("\n<*************> Welcome To Indian Railways <*************>");
            System.out.println("""
                    1 - Admin('For Officials Only')
                    2 - Passenger('For Public')
                    3 - Exit""");
            System.out.print("Choose Your Option : ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option)
            {
                case 1:
                    this.userManager = adminUserManager;
                    userManager.login();
                    adminPage();
                    break;
                case 2:
                    this.userManager = passengerUserManager;
                    userManager.login();
                    passengerPage();
                    break;
                case 3:
                    System.out.println("\tThank You For Choosing Us!");
                    isRunning = false;
                    break;
            }
        }
    }
    private void adminPage() {
            boolean isAdmin = true;
            Scanner scanner = new Scanner(System.in);

            while (isAdmin)
            {
                System.out.println("\n<******* ADMIN *******>");
                System.out.println("\tWelcome, Admin");
                System.out.println("""
                        1 - Manage train
                        2 - Add train
                        3 - Remove train
                        4 - Back""");
                System.out.print("Choose your option : ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option)
                {
                    case 1:

                        trainManager.manageTrain();
                        break;
                    case 2:
                        trainManager.addTrain();
                        break;
                    case 3:
                        trainManager.removeTrain();
                        break;
                    case 4:
                        isAdmin = false;
                        break;
                }
            }
    }
    private void passengerPage() {

            boolean isPassenger = true;
            Scanner scanner = new Scanner(System.in);

            while (isPassenger)
            {
                System.out.println("\n******** PASSENGER ********");
                System.out.println("""
                        1 - Login
                        2 - Create Account
                        3 - Back""");
                System.out.print("Choose your option : ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option)
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
                }
            }
    }

}
