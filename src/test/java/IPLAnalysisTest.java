import org.junit.Assert;
import org.junit.Test;

public class IPLAnalysisTest {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019MostRunsPlayer.csv";

    @Test
    public void givenIPLMostRunPlayerDataCSVFile_ReturnsCorrectRecords() {
         IPLAnalyser iplAnalyser = new IPLAnalyser();
        int numOfRecords = iplAnalyser.loadIPLMostRunsPlayerData(IPL_MOST_RUNS_CSV_FILE_PATH);
        Assert.assertEquals(100, numOfRecords);
    }
}
