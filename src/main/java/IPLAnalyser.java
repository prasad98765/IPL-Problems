import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IPLAnalyser {

    List<CricketDataDAO> playerList = new ArrayList<>();

    public Player player;

    public enum Player {
        Batting, Bowling;
    }

    public IPLAnalyser(Player player) {
        this.player = player;
    }

    public int lodeIPLCricketData(String csvFilePath) throws IPLAnalyserException {
        playerList = new LoadDataFactory().loadCricketData(player, csvFilePath);
        return playerList.size();
    }

    public ArrayList getSortedDatafieldsWise(FieldWiseSorting.fields parameter) {
        Comparator<CricketDataDAO> batsmanComparator = null;
        if (Player.Batting.equals(player)) {
            batsmanComparator = new FieldWiseSorting().getParameterFields(parameter);
        }
        if (Player.Bowling.equals(player)) {
            batsmanComparator = new FieldWiseSorting().getParameterFields(parameter);
        }
        ArrayList batsmanList = playerList.stream().
                sorted(batsmanComparator)
                .map(batsmanDAO -> batsmanDAO.getIPLDTO(player))
                .collect(Collectors.toCollection(ArrayList::new));
        return batsmanList;
    }
}
