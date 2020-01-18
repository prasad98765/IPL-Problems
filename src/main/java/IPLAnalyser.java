import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IPLAnalyser {
    List playerList = new ArrayList<>();

    public int lodeIPLData(String csvFilePath) throws IPLAnalyserException {
        playerList = new DataLoader().loadIPLMostRunsPlayerData(csvFilePath);
        return playerList.size();
    }

    public List<MostRunsCSV> getSortedBattingAverage() {
        Comparator<MostRunsCSV> mostRunsCSVComparable = (o1, o2) -> ((o1.average - (o2.average)) > 0) ? -1 : 1;
        Collections.sort(playerList, mostRunsCSVComparable);
        return playerList;
    }

    public List<MostRunsCSV> getSortedBattingStrikeRates() {
        Comparator<MostRunsCSV> mostRunsCSVComparable = (o1, o2) -> ((o1.strikeRate - (o2.strikeRate)) > 0) ? -1 : 1;
        Collections.sort(playerList, mostRunsCSVComparable);
        return playerList;
    }

    public List<MostRunsCSV> getSortedMostPlayer6sand4s() {
        Comparator<MostRunsCSV> mostRunsCSVComparable = Comparator.comparingInt(o -> (o.sixes * 6 + (o.fours * 4)));
        Collections.sort(playerList, mostRunsCSVComparable);
        Collections.reverse(playerList);
        return playerList;
    }

    public List<MostRunsCSV> getSortedMostPlayer6sand4sWithStrikeRate() {
        Comparator<MostRunsCSV> mostRunsCSVComparable = Comparator.comparingInt(o -> (o.sixes * 6 + (o.fours * 4)));
        mostRunsCSVComparable = mostRunsCSVComparable.thenComparing((o1, o2) -> ((o1.strikeRate - (o2.strikeRate)) < 0) ? -1 : 1);
        Collections.sort(playerList, mostRunsCSVComparable);
        Collections.reverse(playerList);
        return playerList;
    }
    public List<MostRunsCSV> getSortedMostPlayerStrikeRateWithAverage() {
        Comparator<MostRunsCSV> mostRunsCSVComparable = (o1, o2) -> (int) (o1.average - (o2.average));
        mostRunsCSVComparable = mostRunsCSVComparable.thenComparing((o1, o2) -> ((o1.strikeRate - (o2.strikeRate)) < 0) ? -1 : 1);
        Collections.sort(playerList, mostRunsCSVComparable);
        Collections.reverse(playerList);
        System.out.print(playerList);
        return playerList;
    }

}
