import java.util.*;
import java.util.stream.Collectors;

public class IPLAnalyser {

    List<BatsmanData> playerList = null;


    public int lodeIPLData(String csvFilePath) throws IPLAnalyserException {
        playerList = new DataLoader().loadIPLMostRunsPlayerData(csvFilePath);
        return playerList.size();
    }


    public ArrayList getSortedDatafieldsWise(FieldWiseSorting.fields parameter) {
        Comparator<BatsmanData> batsmanComparator = new FieldWiseSorting().getParameterFields(parameter);
        ArrayList batsmanList = playerList.stream().
                sorted(batsmanComparator).
                collect(Collectors.toCollection(ArrayList::new));
        return batsmanList;
    }

}