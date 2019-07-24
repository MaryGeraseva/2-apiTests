package petStore.responses;

import java.util.Objects;

public class PetStoreResponse {

    private Integer code;
    private String type;
    private String message;

    public PetStoreResponse() {
    }

    public PetStoreResponse(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public PetStoreResponse(StatusCodes statusLine) {
        this.code = statusLine.getCode();
        this.type = statusLine.getType();
        this.message = statusLine.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("\n{code = %s, type = %s, message = %s}", code, type, message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetStoreResponse)) return false;
        PetStoreResponse that = (PetStoreResponse) o;
        return Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getType(), getMessage());
    }
}
