package petStore.responses;

public enum StatusCodes {

    CODE405(405, "error", "Invalid input"),
    CODE500(500, "unknown", "something bad happened");

    private int code;
    private String type;
    private String message;

    private StatusCodes(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
