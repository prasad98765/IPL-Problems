import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FieldWiseSorting {

    static Map<fields, Comparator<CricketDataDAO>> playerData = new HashMap();

    enum fields {AVERAGE, STRIKERATE, BOUNDARIES, STRIKERATE_WITH_BOUNDARIES, STRIKERATE_WITH_AVERAGE, RUN_WITH_AVERAGE, BOWLING_AVERAGE,BOWLING_STRIKE_RATE}


    public Comparator<CricketDataDAO> getParameterFields(FieldWiseSorting.fields parameter) {
        Comparator<CricketDataDAO> avgComparator = Comparator.comparing(batsmanRun -> batsmanRun.average, Comparator.reverseOrder());
        Comparator<CricketDataDAO> strikeRateComparator = Comparator.comparing(batsmanRun -> batsmanRun.strikeRate, Comparator.reverseOrder());
        Comparator<CricketDataDAO> boundariesComparator = Comparator.comparing(batsman -> (batsman.sixes * 6 + batsman.fours * 4), Comparator.reverseOrder());
        Comparator<CricketDataDAO> strikeRateWithBoundaryComparator = Comparator.comparing(batsman -> (batsman.sixes * 6 + batsman.fours * 4), Comparator.reverseOrder());
        Comparator<CricketDataDAO> strikeRateWithAverageComparator = Comparator.comparing(batsman -> batsman.average, Comparator.reverseOrder());
        Comparator<CricketDataDAO> RunWithBestAverageComparator = Comparator.comparing(batsman -> batsman.runs, Comparator.reverseOrder());
        Comparator<CricketDataDAO> bowlingAverageComparator = Comparator.comparing(batsman -> batsman.average, Comparator.reverseOrder());
        Comparator<CricketDataDAO> bowlingStrikeRateComparator = Comparator.comparing(batsman -> batsman.strikeRate, Comparator.reverseOrder());
        playerData.put(FieldWiseSorting.fields.AVERAGE, avgComparator);
        playerData.put(FieldWiseSorting.fields.STRIKERATE, strikeRateComparator);
        playerData.put(FieldWiseSorting.fields.BOUNDARIES, boundariesComparator);
        playerData.put(FieldWiseSorting.fields.STRIKERATE_WITH_BOUNDARIES, strikeRateWithBoundaryComparator.thenComparing(strikeRateComparator));
        playerData.put(FieldWiseSorting.fields.STRIKERATE_WITH_AVERAGE, strikeRateWithAverageComparator.thenComparing(strikeRateComparator));
        playerData.put(FieldWiseSorting.fields.RUN_WITH_AVERAGE, RunWithBestAverageComparator.thenComparing(avgComparator));
        playerData.put(fields.BOWLING_AVERAGE, bowlingAverageComparator);
        playerData.put(fields.BOWLING_STRIKE_RATE, bowlingStrikeRateComparator);
        return playerData.get(parameter);
    }
}
