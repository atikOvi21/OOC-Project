import java.util.ArrayList;
import java.util.Scanner;

class AdminTrainManager implements TrainManager,TrainInfo
{

    public ArrayList<Passenger> passengers = new ArrayList<>();
    public int currentTrain;
    public Scanner scanner;

    @Override
    public void manageTrain() {
        System.out.println();
        for (int i = 0; i < train.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + train.get(i).trainName + " (" + train.get(i).destination + ")");
        }
        System.out.print("\nSelect The Train : ");
        scanner = new Scanner(System.in);
        int option = (scanner.nextInt() - 1);
        scanner.nextLine();
        currentTrain = option;

        //Update train
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n<****** " + train.get(currentTrain).trainName + " *******>");
            System.out.println("""
                    1 - View bookings
                    2 - View route
                    3 - Update route
                    4 - Update number of seats
                    5 - Back""");
            System.out.print("Choose your option : ");
            int userOption = scanner.nextInt();
            scanner.nextLine();

            switch (userOption)
            {
                case 1:
                    viewBookings();
                    break;
                case 2:
                    viewRoute();
                    break;
                case 3:
                    updateRoute();
                    break;
                case 4:
                    updateSeat();
                    break;
                case 5:
                    isRunning = false;
                    break;
            }
        }
    }
    @Override
    public void addTrain() {
        System.out.print("\nTrain name : ");
        scanner = new Scanner(System.in);
        String trainName = scanner.nextLine();

        System.out.print("Number of station : ");
        int numOfStations = scanner.nextInt();
        scanner.nextLine();
        String[] station = new String[numOfStations];
        for (int i = 0; i < station.length; i++) {
            System.out.print("Station " + (i + 1) + " - ");
            station[i] = scanner.nextLine();
        }

        System.out.print("Number of seats in The Train : ");
        int numOfSeats = scanner.nextInt();

        train.add(new Train(trainName, station, numOfSeats));
        System.out.println("\t" + trainName + " Train successfully added");
    }
    @Override
    public void removeTrain() {
        scanner = new Scanner(System.in);
        System.out.println();
        for (int i = 0; i <  train.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + train.get(i).trainName + " (" +  train.get(i).destination + ")");
        }
        System.out.print("\nSelect The Train : ");
        int option = (scanner.nextInt() - 1);
        scanner.nextLine();
        System.out.println("\t" +  train.get(option).trainName + " successfully removed");
        train.remove(option);
    }
    private  void viewBookings() {
        if (!passengers.isEmpty())
        {
            System.out.println("*************************************************************************************************************************");
            System.out.printf("%6s %19s %24s %19s %17s %11s %15s", "S.NO", "PASSENGER NAME", "DESTINATION", "TIME", "SEAT NO", "PRICE", "STATUS");
            System.out.println();
            System.out.println("*************************************************************************************************************************");

            for (int i = 0, j = 1; i < passengers.size(); i++) {
                Passenger curPassenger = passengers.get(i);
                if (curPassenger.trainName.equals(train.get(currentTrain).trainName)) {
                    System.out.format("%4s %13s %38s %14s %12s %14s %16ss", j, curPassenger.passengerName, (train.get(currentTrain).route[curPassenger.sourceStation - 1] + " - " + train.get(currentTrain).route[curPassenger.destinationStation - 1]), curPassenger.time, (curPassenger.seatNo + 1), ("₹" + curPassenger.ticketPrice), curPassenger.bookingStatus);
                    System.out.println();
                    j++;
                }
            }
            System.out.println("*************************************************************************************************************************");
        } else System.out.println("\tNo Passenger List Available");
    }
    private  void viewRoute() {
        System.out.println("\nTrain name : " + train.get(currentTrain).trainName);
        System.out.println("Destination : " + train.get(currentTrain).destination);
        System.out.println("Train routes : ");

        for (int i = 0; i < train.get(currentTrain).route.length; i++)
        {
            System.out.println("\t" + (i + 1) + ". " + train.get(currentTrain).route[i]);
        }
    }
    private  void updateRoute() {
        scanner = new Scanner(System.in);
        System.out.print("\nNumber of stations : ");
        int numOfStation = scanner.nextInt();
        scanner.nextLine();
        train.get(currentTrain).route = new String[numOfStation];

        for (int i = 0; i < train.get(currentTrain).route.length; i++) {
            System.out.print("Station " + (i + 1) + " - ");
            String station = scanner.nextLine();
            train.get(currentTrain).route[i] = station;
        }
        System.out.println("\tStations Successfully Updated");
    }
    private void updateSeat() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nUpdate Number of Seats for a Train");
        displayAvailableTrains();

        System.out.print("Select Train (Enter Train Index): ");
        int trainIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (trainIndex >= 0 && trainIndex < train.size())
        {
            Train selectedTrain = train.get(trainIndex);
            System.out.print("Enter the new number of seats: ");
            int newNumOfSeats = scanner.nextInt();
            scanner.nextLine();

            selectedTrain.setNumOfSeats(newNumOfSeats);
            System.out.println("Number of seats for Train " + selectedTrain.getTrainName() + " updated successfully");
        }
        else
        {
            System.out.println("Invalid Train Index");
        }
    }
    private void displayAvailableTrains() {
        System.out.println("Available Trains:");
        for (int i = 0; i < train.size(); i++)
        {
            System.out.println((i + 1) + ". " + train.get(i).getTrainName());
        }
    }

}
