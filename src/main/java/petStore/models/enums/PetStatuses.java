package petStore.models.petModel;

public enum PetStatus {
    AVAILABLE, PENDING, SOLD;

    public String nameLowerCase(){
        return name().toLowerCase();
    }

    public static PetStatus getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
