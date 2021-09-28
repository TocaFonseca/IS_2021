import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Catalog {

    private List<Pet> allPets;
    private List<Owner> allOwners;

    public Catalog(){

        allPets = new ArrayList<>();
        allOwners = new ArrayList<>();

    }

    public List<Pet> getAllPets() { return allPets; }

    public void addPet(Pet pet) { (this.allPets).add(pet); }

    @XmlElement
    public List<Owner> getAllOwners() { return allOwners; }
    public void addOwner(Owner owner) { (this.allOwners).add(owner); }
}
