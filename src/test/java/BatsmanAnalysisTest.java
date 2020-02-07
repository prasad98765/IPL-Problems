import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BatsmanAnalysisTest {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "/home/admin1/Desktop/Prasad/IPL-Problems-master/src/test/resources/BattingData.csv";
    private static final String WRONG_CSV_FILE = "/home/admin1/Desktop/Prasad/IPL-Problems-master/src/test/resources/WrongCSVFileData.csv";
    private static final String WRONG_CSV_FILE_DATA = "/home/admin1/Desktop/Prasad/IPL-Problems-master/src/test/resources/WrongCSVFileData.csv";

    @Test
    public void givenIPLRunsPlayerDataCSVFile_ReturnsCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Batting);
            int numOfRecords = iplAnalyser.lodeIPLCricketData(IPL_MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_shouldReturnTopBattingAverages() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Batting);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_RUNS_CSV_FILE_PATH);
            List<BatsmanData> list = iplAnalyser.getSortedDatafieldsWise(FieldWiseSorting.fields.AVERAGE);
            Assert.assertEquals(83.2, list.get(0).average, 0);
            Assert.assertEquals(0.0, list.get(99).average, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenIPLRunsPlayerDataCSVFile_shouldReturnTopBattingStrikeRates() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Batting);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_RUNS_CSV_FILE_PATH);
            List<BatsmanData> list = iplAnalyser.getSortedDatafieldsWise(FieldWiseSorting.fields.STRIKERATE);
            Assert.assertEquals(333.33, list.get(0).strikeRate, 0);
            Assert.assertEquals(63.15, list.get(99).strikeRate, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
      }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_shouldReturnTopPlayer6sAnd4s() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Batting);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_RUNS_CSV_FILE_PATH);
            List<BatsmanData> list = iplAnalyser.getSortedDatafieldsWise(FieldWiseSorting.fields.BOUNDARIES);
            Assert.assertEquals("Andre Russell", list.get(0).player);
            Assert.assertEquals("Shakib Al Hasan", list.get(99).player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_shouldReturnTopPlayer6sAnd4sWithStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Batting);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_RUNS_CSV_FILE_PATH);
            List<BatsmanData> list = iplAnalyser.getSortedDatafieldsWise(FieldWiseSorting.fields.STRIKERATE_WITH_BOUNDARIES);
            Assert.assertEquals("Andre Russell", list.get(0).player);
            Assert.assertEquals("Shakib Al Hasan", list.get(99).player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_shouldReturnTopPlayerStrikeRateWithAverage() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Batting);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_RUNS_CSV_FILE_PATH);
            List<BatsmanData> list = iplAnalyser.getSortedDatafieldsWise(FieldWiseSorting.fields.STRIKERATE_WITH_AVERAGE);
            Assert.assertEquals("MS Dhoni", list.get(0).player);
            Assert.assertEquals("Tim Southee", list.get(99).player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_shouldReturnTopRunsWithBestAverage() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Batting);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_RUNS_CSV_FILE_PATH);
            List<BatsmanData> list = iplAnalyser.getSortedDatafieldsWise(FieldWiseSorting.fields.RUN_WITH_AVERAGE);
            Assert.assertEquals("David Warner ", list.get(0).player);
            Assert.assertEquals("Tim Southee", list.get(99).player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    //Exception test
    @Test
    public void givenWrongIPLCSVFile_shouldReturnException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Batting);
            iplAnalyser.lodeIPLCricketData(WRONG_CSV_FILE);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_WithWrongType_ShouldCustomException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Batting);
            iplAnalyser.lodeIPLCricketData(WRONG_CSV_FILE_DATA);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_WithWrongDelimiter_ShouldCustomException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Batting);
            iplAnalyser.lodeIPLCricketData(WRONG_CSV_FILE_DATA);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }

    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_WithWrongHeader_ShouldCustomException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Batting);
            iplAnalyser.lodeIPLCricketData(WRONG_CSV_FILE_DATA);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }
    }

}
