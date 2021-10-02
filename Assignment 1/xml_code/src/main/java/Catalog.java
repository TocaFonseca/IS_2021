import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Catalog {

    private List<Pet> pet;
    private List<Owner> owner;

    public Catalog(){

        pet = new ArrayList<>();
        owner = new ArrayList<>();

    }

    public List<Pet> getAllPets() { return pet; }

    public void addPet(Pet pet) { (this.pet).add(pet); }

    @XmlElement
    public List<Owner> getOwners() { return owner; }
    public void addOwner(Owner owner) { (this.owner).add(owner); }
}
