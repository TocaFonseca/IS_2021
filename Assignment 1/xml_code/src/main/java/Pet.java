import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name="pet", propOrder = {"name", "species", "gender", "birth", "weight", "form_desc"})
public class Pet {

    private int pet_id;
    private String name;
    private String species;
    private String gender;
    private int weight;
    private String birth;
    private String form_desc;
    private int owner_id;

    public Pet(){   }

    public Pet(int pet_id, String name, String species, String gender, int weight, String birth, String form_desc, int owner_id){
        super();
        this.pet_id = pet_id;
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.weight = weight;
        this.birth = birth;
        this.form_desc = form_desc;
        this.owner_id = owner_id;
    }

    @XmlAttribute
    public int getOwner_id() { return owner_id; }
    public void setOwner_id(int owner_id) { this.owner_id = owner_id; }

    @XmlAttribute
    public int getPet_id() { return pet_id; }
    public void setPet_id(int pet_id) { this.pet_id = pet_id; }

    @XmlElement
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @XmlElement
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    @XmlElement
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    @XmlElement
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    @XmlElement
    public String getBirth() { return birth; }
    public void setbirth(String birth) { this.birth = birth; }

    @XmlElement
    public String getForm_desc() { return form_desc; }
    public void setForm_desc(String form_desc) { this.form_desc = form_desc; }
}
