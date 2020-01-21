public class IPLAnalyserException extends Exception {

    public IPLAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

    enum ExceptionType {
        INVALID_FILE_DATA, INVALID_FILE_DATA_PROBLEM,INCORRECT_TYPE
    }

    ExceptionType type;

    public IPLAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
