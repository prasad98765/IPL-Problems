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
        playerList = new DataLoader().loadIPLRunsPlayerData(BowlingData.class, csvFilePath);
        return playerList.size();
    }

    public ArrayList getSortedDatafieldsWise(String type,FieldWiseSorting.fields parameter) {
        Comparator<CricketDataDAO> batsmanComparator = null;
        if (type.equals("Batting")) {
            batsmanComparator = new FieldWiseSorting().getParameterFields(parameter);
        }
        if (type.equals("Bowling")){
            batsmanComparator = new FieldWiseSorting().getParameterFields(parameter);
        }
        ArrayList batsmanList = playerList.stream().
                sorted(batsmanComparator).
                collect(Collectors.toCollection(ArrayList::new));
        return batsmanList;
    }
}