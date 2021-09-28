import java.util.ArrayList;
import java.util.List;

public class Owner {

    private int owner_id;
    private String name;
    private String birth;
    private int telephone;
    private String address;
    private List<Pet> pets;

    public Owner(){
        pets = new ArrayList<>();
    }

    public Owner(int owner_id, String name, String birth, int telehone, String address){
        super();
        this.owner_id = owner_id;
        this.name = name;
        this.birth = birth;
        this.telephone = telehone;
        this.address = address;
    }

    public int getOwner_id() { return owner_id; }

    public void setOwner_id(int owner_id) { this.owner_id = owner_id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBirth() { return birth; }

    public void setBirth(String birth) { this.birth = birth; }

    public int getTelephone() { return telephone; }

    public void setTelephone(int telephone) { this.telephone = telephone; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public List<Pet> getPets() { return pets; }

    public void addPet(Pet new_pet) { this.pets.add(new_pet); }
}
