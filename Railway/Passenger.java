public class Passenger
{
    String passengerName;
    int sourceStation;
    int destinationStation;
    int wallet;
    String trainName;

    int seatNo;
    String time;
    int ticketPrice;
    String bookingStatus;

    public Passenger(String name, int from, int destinationStation, String trainName, int ticketPrice, int seatNo, String time, int wallet, String bookingStatus)
    {
        this.passengerName = name;
        this.sourceStation = from;
        this.destinationStation = destinationStation;
        this.trainName = trainName;
        this.ticketPrice = ticketPrice;
        this.seatNo = seatNo;
        this.time = time;
        this.wallet = wallet;
        this.bookingStatus = bookingStatus;
    }

    public Passenger(String name, int from, int destinationStation)
    {
        this.passengerName = name;
        this.sourceStation = from;
        this.destinationStation = destinationStation;
    }

}
