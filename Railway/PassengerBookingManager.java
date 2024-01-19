import java.util.Scanner;


class PassengerBookingManager implements BookingManager,TrainInfo
{
    public int seatNo;
    public int currentTrain;
    PassengerUserManager passengerUser = new PassengerUserManager();
    Scanner scanner = new Scanner(System.in);

    @Override
    public  void bookTicket()
    {
        System.out.println();
        for (int i = 0; i < train.size(); i++)
            System.out.println(" " + (i + 1) + ". " + train.get(i).trainName + " (" + train.get(i).destination + ")");
        System.out.print("\nSelect train : ");
        int option = (scanner.nextInt() - 1);
        scanner.nextLine();
        currentTrain = option;


        System.out.println("\nTrain Name : " + train.get(currentTrain).trainName);
        System.out.println("Destination : " + train.get(currentTrain).destination);
        System.out.println("Train Stations : ");
        for (int i = 0; i < train.get(currentTrain).route.length; i++)
            System.out.println("\t" + (i + 1) + ". " + train.get(currentTrain).route[i]);


        System.out.print("\nFROM : ");
        int sourceStation = scanner.nextInt();
        System.out.print(" TO  : ");
        int destinationStation = scanner.nextInt();
        scanner.nextLine();
        if (sourceStation >= destinationStation) {
            System.out.println("\tChoose Stations Correctly");
            bookTicket();
            return;
        }


        if (!train.get(currentTrain).passengerList.isEmpty())
            for (int i = 0; i < train.get(currentTrain).passengerList.size(); i++) {
                Passenger passengerPosition = train.get(currentTrain).passengerList.get(i);
                if (passengerPosition.passengerName.equals(PassengerUserManager.currentPassenger))
                    if (passengerPosition.sourceStation == sourceStation && passengerPosition.destinationStation == destinationStation) {
                        System.out.println("\tYou Have Already Booked Ticket For The Same Destination");
                        bookTicket();
                        return;
                    }
            }



        int ticketPrice = (destinationStation - sourceStation) * 100;


        int walletAmount = passengerUser.money.get(passengerUser.passengerNames.indexOf(PassengerUserManager.currentPassenger));
        if (walletAmount >= ticketPrice) {
            String bookingStatus = "Filled";


            if (fillSeats(sourceStation - 1, destinationStation, -1)) {
                passengerUser.money.set(passengerUser.passengerNames.indexOf(PassengerUserManager.currentPassenger), walletAmount - ticketPrice);
                train.get(currentTrain).passengerList.add(new Passenger(PassengerUserManager.currentPassenger, sourceStation, destinationStation, train.get(currentTrain).trainName, ticketPrice, (seatNo - 1), randomTime(), walletAmount, "Filled"));
                System.out.println("\tYour Booking Has Been Filled, Seat Number is " + seatNo);
            } else {
                bookingStatus = "Waiting List";
                train.get(currentTrain).waitingList.add(new Passenger(PassengerUserManager.currentPassenger, sourceStation, destinationStation, train.get(currentTrain).trainName, ticketPrice, (seatNo - 1), randomTime(), walletAmount, "Waiting list"));
                System.out.println("\tCurrently No Seats Are Available, You Are in Waiting List");
            }


            passengerUser.passengers.add(new Passenger(PassengerUserManager.currentPassenger, sourceStation, destinationStation, train.get(currentTrain).trainName, ticketPrice, (seatNo - 1), randomTime(), walletAmount, bookingStatus));
        } else System.out.println("\tInsufficient Fund in Your Wallet");
    }
    private  String randomTime()
    {
        String minutes = Integer.toString((int) ((Math.random() * 60.0) + 1));
        String hour = Integer.toString((int) ((Math.random() * 12.0) + 1));

        if (minutes.length() <= 1) minutes = "0" + minutes;
        if (hour.length() <= 1) hour = "0" + hour;

        return hour + ":" + minutes;

    }

    private boolean fillSeats(int sourceStation, int destination, int cancel)
    {

        if (cancel != -1)
            for (int i = 0; i < train.size(); i++)
                if ( passengerUser.passengers.get(cancel).trainName.equals(train.get(i).trainName))
                    currentTrain = i;

        for (int i = 0; i < train.get(currentTrain).seat.length; i++)
        {
            int isFree = 0;
            for (int j = sourceStation; j < destination && j < train.get(currentTrain).seat[i].length; j++)
                if (train.get(currentTrain).seat[i][j] != 0)
                    isFree++;
            if (isFree <= 1)
            {
                for (int k = sourceStation; k < destination && k < train.get(currentTrain).seat[i].length; k++)
                    train.get(currentTrain).seat[i][k] = 1;
                seatNo = i + 1;
                train.get(currentTrain).seatManage.add(new Passenger(passengerUser.currentPassenger, sourceStation, destination));
                return true;
            }
            else if (isFree == 2) for (int j = 0; j < train.get(currentTrain).seatManage.size(); j++) {
                boolean isSameBoarding = false;
                for (int k = 0; k < train.get(currentTrain).seatManage.size(); k++)
                    if (train.get(currentTrain).seatManage.get(k).sourceStation == sourceStation) {
                        isSameBoarding = true;
                        break;
                    }

                if (!isSameBoarding && train.get(currentTrain).seatManage.get(i).destinationStation == sourceStation + 1) {
                    train.get(currentTrain).seat[i][sourceStation] = 0;
                    fillSeats(sourceStation, destination, -1);
                    return true;
                }
            }
        }
        return false;
    }
    public void viewBookings()
    {
        if (!passengerUser.passengers.isEmpty())
        {
            System.out.println("*************************************************************************************************************************");
            System.out.printf("%6s %19s %24s %19s %17s %11s %15s", "S.NO", "PASSENGER NAME", "DESTINATION", "TIME", "SEAT NO", "PRICE", "STATUS");
            System.out.println();
            System.out.println("*************************************************************************************************************************");

            for (int i = 0, j = 1; i < passengerUser.passengers.size(); i++)
            {
                Passenger curPassenger = passengerUser.passengers.get(i);
                if (curPassenger.trainName.equals(train.get(currentTrain).trainName))
                {
                    System.out.format("%4s %13s %38s %14s %12s %14s %16ss", j, curPassenger.passengerName, (train.get(currentTrain).route[curPassenger.sourceStation - 1] + " - " + train.get(currentTrain).route[curPassenger.destinationStation - 1]), curPassenger.time, (curPassenger.seatNo + 1), ("₹" + curPassenger.ticketPrice), curPassenger.bookingStatus);
                    System.out.println();
                    j++;
                }
            }
            System.out.println("*************************************************************************************************************************");
        }
        else
            System.out.println("\tNo Passenger List Available");

    }
    @Override
    public void cancelTicket()
    {
        //PassengerUserManager passengerUser = new PassengerUserManager();
        scanner = new Scanner(System.in);

        if (passengerUser.passengers.isEmpty())
            System.out.println("\tYou don't have any bookings");
        else
        {
            viewBookings();

            System.out.print("Cancel Ticket : ");
            int cancel = (scanner.nextInt() - 1);

            int current_Train = 0;
            for (int i = 0; i < train.size(); i++)
                if ( passengerUser.passengers.get(cancel).trainName.equals(train.get(i).trainName))
                    current_Train = i;


            String nameTODelete =  passengerUser.passengers.get(cancel).passengerName;
            int sourceToDelete =  passengerUser.passengers.get(cancel).sourceStation;
            int destinationToDelete =  passengerUser.passengers.get(cancel).destinationStation;
            int seatTODelete =  passengerUser.passengers.get(cancel).seatNo;
            int ticketAmount =  passengerUser.passengers.get(cancel).ticketPrice;

            for (Train obj : train)
            {
                for (int i = 0; i < obj.passengerList.size(); i++)
                {
                    Passenger psList = obj.passengerList.get(i);
                    if (psList.passengerName.equals(nameTODelete) && psList.destinationStation == destinationToDelete && psList.sourceStation == sourceToDelete)
                        obj.passengerList.remove(i);
                }
            }


           passengerUser.passengers.remove(cancel);


            for (Train obj : train) {
                for (int i = 0; i < obj.seatManage.size(); i++) {
                    Passenger psList = obj.seatManage.get(i);
                    if (psList.passengerName.equals(nameTODelete) && psList.destinationStation == destinationToDelete && (psList.sourceStation + 1) == sourceToDelete)
                        obj.seatManage.remove(i);
                }
            }

            for (int j = sourceToDelete - 1; j < destinationToDelete; j++)
                train.get(current_Train).seat[seatTODelete][j] = 0;


            int userInd = passengerUser.passengerNames.indexOf(passengerUser.currentPassenger);
            passengerUser.money.set(userInd,passengerUser.money.get(userInd) + ticketAmount);
            System.out.println("\tTicket canceled, amount refunded to your wallet");


            if (!train.get(current_Train).waitingList.isEmpty()) {
                for (int i = 0; i < train.get(current_Train).waitingList.size(); i++) {
                    Passenger waitingListPassenger = train.get(current_Train).waitingList.get(i);
                    fillSeats((waitingListPassenger.sourceStation - 1), waitingListPassenger.destinationStation, cancel);

                    passengerUser.money.set(passengerUser.passengerNames.indexOf(waitingListPassenger.passengerName), waitingListPassenger.wallet - (waitingListPassenger.destinationStation - waitingListPassenger.sourceStation) * 100);
                    train.get(current_Train).passengerList.add(new Passenger(waitingListPassenger.passengerName, waitingListPassenger.sourceStation, waitingListPassenger.destinationStation, waitingListPassenger.trainName, waitingListPassenger.ticketPrice, (seatNo - 1), waitingListPassenger.time, waitingListPassenger.wallet, "Filled"));
                    //Change passenger
                    for (Passenger passenger :  passengerUser.passengers)
                        if (passenger.passengerName.equals(waitingListPassenger.passengerName)) passenger.bookingStatus = "Filled";
                }
            }
        }
    }
    public void wallet()
    {

        boolean isWalletMenuActive = true;
        while (isWalletMenuActive) {
            System.out.println("\n******* Mr." + passengerUser.currentPassenger + " Wallet *******");
            System.out.println("""
                    1 - Show Balance
                    2 - Add Balance
                    3 - Back""");
            System.out.print("Choose Your Option : ");
            int userOption = scanner.nextInt();
            scanner.nextLine();

            switch (userOption)
            {

                case 1:
                    System.out.println("\n\tYour Wallet Balance is : ₹" + passengerUser.money.get(passengerUser.passengerNames.indexOf(passengerUser.currentPassenger)));
                    break;



                case 2:
                    System.out.print("\nEnter Amount : ");
                    int amount = scanner.nextInt();
                    scanner.nextLine();
                    passengerUser.money.set(passengerUser.passengerNames.indexOf(passengerUser.currentPassenger), passengerUser.money.get(passengerUser.passengerNames.indexOf(passengerUser.currentPassenger)) + amount);
                    System.out.println("\tAmount Added Successfully");
                    break;
                case 3:
                    isWalletMenuActive = false;
                    break;
            }
        }
    }
}
