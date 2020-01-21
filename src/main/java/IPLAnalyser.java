import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IPLAnalyser {

    List<CricketDataDAO> playerList = new ArrayList<>();

    public int lodeIPLRunsData(String csvFilePath) throws IPLAnalyserException {
        playerList = new DataLoader().loadIPLRunsPlayerData(BatsmanData.class, csvFilePath);
        return playerList.size();
    }

    public int lodeIPLWicketData(String csvFilePath) throws IPLAnalyserException {
        playerList = new DataLoader().loadIPLRunsPlayerData(WicketsData.class, csvFilePath);
        return playerList.size();
    }

    public ArrayList getSortedDatafieldsWise(FieldWiseSorting.fields parameter) {
        Comparator<CricketDataDAO> batsmanComparator = new FieldWiseSorting().getParameterFields(parameter);
        ArrayList batsmanList = playerList.stream().
                sorted(batsmanComparator).
                collect(Collectors.toCollection(ArrayList::new));
        return batsmanList;
    }

    public ArrayList getSortedBowlerDatafieldsWise(FieldWiseSorting.fields parameter) {
        Comparator<CricketDataDAO> batsmanComparator = new FieldWiseSorting().getParameterFields(parameter);
        ArrayList WicketList = playerList.stream().
                sorted(batsmanComparator).
                collect(Collectors.toCollection(ArrayList::new));
        return WicketList;
    }

}