import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private List<Pet> allPets;
    private List<Owner> allOwners;

    public Catalog(){

        allPets = new ArrayList<>();
        allOwners = new ArrayList<>();

    }

    public List<Pet> getAllPets() { return allPets; }

    public void addPet(Pet pet) { (this.allPets).add(pet); }

    public List<Owner> getAllOwners() { return allOwners; }

    public void addOwner(Owner owner) { (this.allOwners).add(owner); }
}
