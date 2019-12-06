package petStore.models.enums;

public enum PetStatuses {
    AVAILABLE, PENDING, SOLD;

    public String nameLowerCase() {
        return name().toLowerCase();
    }

    public static PetStatuses getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

    public static PetStatuses getNewStatus(String currentStatus) {
        PetStatuses newStatus;
        do {
            newStatus = getRandom();
        } while (currentStatus.equals(newStatus.nameLowerCase()));
        return newStatus;
    }
}
