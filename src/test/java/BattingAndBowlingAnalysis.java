import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BattingAndBowlingAnalysis {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IPL/src/test/resources/BattingData.csv";
    private static final String IPL_MOST_WKTS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IPL/src/test/resources/FactsheetMostWkts.csv";

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_shouldReturnBestBattingAndBowlingAverage() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Batting);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_RUNS_CSV_FILE_PATH,IPL_MOST_WKTS_CSV_FILE_PATH);
            List<BatsmanData> list = iplAnalyser.getSortedDatafieldsWise(FieldWiseSorting.fields.BATTING_BOWLING_AVG);
            Assert.assertEquals("Harpreet Brar",list.get(99).player);
            Assert.assertEquals("MS Dhoni",list.get(0).player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }
}