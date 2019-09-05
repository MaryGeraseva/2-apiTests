package petStore.responses;

public enum StatusCodes {

    CODE200(200, "", "OK", "OK"),
    CODE400(400, "error", "Invalid ID supplied", "Bad Request"),
    CODE400_GET_LIST(400, "error", "Invalid status value", "Bad Request"),
    CODE404(404, "error", "Pet not found", "Not Found"),
    CODE405_POST(405, "error", "Invalid input", "Method Not Allowed"),
    CODE405_PUT(405, "error", "Validation exception", "Method Not Allowed"),
    CODE500(500, "unknown", "something bad happened", "Internal Server Error");

    private int code;
    private String type;
    private String bodyMessage;
    private String statusMessage;

    private StatusCodes(int code, String type, String bodyMessage, String statusMessage) {
        this.code = code;
        this.type = type;
        this.bodyMessage = bodyMessage;
        this.statusMessage = statusMessage;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getBodyMessage() {
        return bodyMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
