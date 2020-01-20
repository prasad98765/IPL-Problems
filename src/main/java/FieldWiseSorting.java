import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FieldWiseSorting {

    Map<fields, Comparator<BatsmanData>> mapComp = new HashMap();

    enum fields {AVERAGE, STRIKERATE, BOUNDARIES, STRIKERATE_WITH_BOUNDARIES, STRIKERATE_WITH_AVERAGE,MAXIMUMRUN_WITH_AVERAGE}


    public Comparator getParameterFields(FieldWiseSorting.fields parameter) {
        Comparator<BatsmanData> avgComparator = Comparator.comparing(batsmanRun -> batsmanRun.average, Comparator.reverseOrder());
        Comparator<BatsmanData> strikeRateComparator = Comparator.comparing(batsmanRun -> batsmanRun.strikeRate, Comparator.reverseOrder());
        Comparator<BatsmanData> boundariesComparator = Comparator.comparing(batsman -> (batsman.sixes * 6 + batsman.fours * 4), Comparator.reverseOrder());
        Comparator<BatsmanData> strikeRateWithBoundaryComparator = Comparator.comparing(batsman -> (batsman.sixes * 6 + batsman.fours * 4), Comparator.reverseOrder());
        Comparator<BatsmanData> strikeRateWithAverageComparator = Comparator.comparing(batsman -> batsman.average, Comparator.reverseOrder());
        Comparator<BatsmanData> maximunRunWithBestAverageRateComparator = Comparator.comparing(batsman -> batsman.runs, Comparator.reverseOrder());
        mapComp.put(FieldWiseSorting.fields.AVERAGE, avgComparator);
        mapComp.put(FieldWiseSorting.fields.STRIKERATE, strikeRateComparator);
        mapComp.put(FieldWiseSorting.fields.BOUNDARIES, boundariesComparator);
        mapComp.put(FieldWiseSorting.fields.STRIKERATE_WITH_BOUNDARIES, strikeRateWithBoundaryComparator.thenComparing(strikeRateComparator));
        mapComp.put(FieldWiseSorting.fields.STRIKERATE_WITH_AVERAGE, strikeRateWithAverageComparator.thenComparing(strikeRateComparator));
        mapComp.put(FieldWiseSorting.fields.MAXIMUMRUN_WITH_AVERAGE, maximunRunWithBestAverageRateComparator.thenComparing(avgComparator));
        Comparator<BatsmanData> comparator = mapComp.get(parameter);
        return comparator;
    }
}
