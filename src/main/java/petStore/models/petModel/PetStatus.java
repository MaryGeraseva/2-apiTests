package petStore.models.petModel;

public enum PetStatus {
    AVAILABLE, PENDING, SOLD;

    public String nameLowerCase(){
        return name().toLowerCase();
    }
}
