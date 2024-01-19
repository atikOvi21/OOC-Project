import java.util.Scanner;

class AdminUserManager implements UserManager
{
    AdminTrainManager trainManager ;
    @Override
    public void login()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n<****** ADMIN LOGIN ******>");
        System.out.print("User name : ");
        String adminName = scanner.nextLine();
        System.out.print("Password : ");
        String adminPassword = scanner.nextLine();

        if (adminName.equalsIgnoreCase("admin") && adminPassword.equalsIgnoreCase("admin")) {
            adminPage();
        } else {
            System.out.println("Invalid Option!, Try again");
            login();
        }
    }

    @Override
    public void createUserAccount() {

        System.out.println("Admin account creation logic");
    }

    private void adminPage()
    {
        trainManager = new AdminTrainManager() ;
        boolean isAdmin = true;
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
            int userOption = new Scanner(System.in).nextInt();
            Scanner scanner = new Scanner(System.in);

            switch (userOption)
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
                    System.out.println("Thank You For Your Work ");
                    isAdmin = false;
                    break;
            }
        }
    }
}

