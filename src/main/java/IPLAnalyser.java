import java.util.*;
import java.util.stream.Collectors;

public class IPLAnalyser {

    List<BatsmanData> playerList = null;
    Map<fields, Comparator<BatsmanData>> mapComp = new HashMap();

    enum fields {AVERAGE, STRIKERATE, BOUNDARIES, STRIKERATE_WITH_BOUNDARIES, STRIKERATE_WITH_AVERAGE}


    public int lodeIPLData(String csvFilePath) throws IPLAnalyserException {
        playerList = new DataLoader().loadIPLMostRunsPlayerData(csvFilePath);
        return playerList.size();
    }

    public Comparator getParameterFields(IPLAnalyser.fields parameter) {
        Comparator<BatsmanData> avgComparator = Comparator.comparing(batsmanRun -> batsmanRun.average, Comparator.reverseOrder());
        Comparator<BatsmanData> strikeRateComparator = Comparator.comparing(batsmanRun -> batsmanRun.strikeRate, Comparator.reverseOrder());
        Comparator<BatsmanData> boundariesComparator = Comparator.comparing(batsman -> (batsman.sixes * 6 + batsman.fours * 4), Comparator.reverseOrder());
        Comparator<BatsmanData> strikeRateWithBoundaryComparator = Comparator.comparing(batsman -> (batsman.sixes * 6 + batsman.fours * 4), Comparator.reverseOrder());
        Comparator<BatsmanData> strikeRateWithAverageComparator = Comparator.comparing(batsman -> batsman.average, Comparator.reverseOrder());
        mapComp.put(fields.AVERAGE, avgComparator);
        mapComp.put(fields.STRIKERATE, strikeRateComparator);
        mapComp.put(fields.BOUNDARIES, boundariesComparator);
        mapComp.put(fields.STRIKERATE_WITH_BOUNDARIES, strikeRateWithBoundaryComparator.thenComparing(strikeRateComparator));
        mapComp.put(fields.STRIKERATE_WITH_AVERAGE, strikeRateWithAverageComparator.thenComparing(strikeRateComparator));
        Comparator<BatsmanData> comparator = mapComp.get(parameter);
        return comparator;
    }

    public ArrayList getSortedDatafieldsWise(IPLAnalyser.fields parameter) {
        Comparator<BatsmanData> batsmanComparator = new IPLAnalyser().getParameterFields(parameter);
        ArrayList batsmanList = playerList.stream().
                sorted(batsmanComparator).
                collect(Collectors.toCollection(ArrayList::new));
        return batsmanList;
    }

}