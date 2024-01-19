import java.util.ArrayList;
import java.util.List;

public interface TrainInfo
{
    public ArrayList<Train> train = new ArrayList<>(List.of(new Train("CHENNAI EXPRESS", new String[]{"COIMBATORE", "TIRUPPUR", "ERODE", "SALEM", "ARAKKONAM", "CHENNAI CTL"}, 3)));

}
