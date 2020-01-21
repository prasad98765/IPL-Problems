import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IPLAnalyser {

    List<CricketDataDAO> playerList = new ArrayList<>();

    public int lodeIPLRunsData(String csvFilePath) throws IPLAnalyserException {
        playerList = new DataLoader().loadIPLRunsPlayerData(csvFilePath);
        return playerList.size();
    }

    public int lodeIPLWicketData(String csvFilePath) throws IPLAnalyserException {
        playerList = new DataLoader().loadIPLWicketsPlayerData(csvFilePath);
        return playerList.size();
    }



    public ArrayList getBatsmanSortedDatafieldsWise(FieldWiseSorting.fields parameter) {
        Comparator<CricketDataDAO> batsmanComparator = new FieldWiseSorting().getParameterFields(parameter);
        ArrayList batsmanList = playerList.stream().
                sorted(batsmanComparator).
                collect(Collectors.toCollection(ArrayList::new));
        return batsmanList;
    }

}