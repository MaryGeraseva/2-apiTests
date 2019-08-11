package petStore.responses;

public enum StatusCodes {

    CODE200(200, "", "OK"),
    CODE400(400, "error", "Invalid ID supplied"),
    CODE404(404, "error", "Pet not found"),
    CODE405_POST(405, "error", "Invalid input"),
    CODE405_PUT(405, "error", "Validation exception"),
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
