import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

public class App {

    private static Catalog.Builder ctlg;
    private static Owner.Builder owner = Owner.newBuilder();

    /**
     * Leitura do ficheiro com o data set e guarda nas respetivas estruturas
     * Cada linha que começa com um + representa um pet
     * Cada linha que começa com um - representa um owner
     * Os atributos estão separados por ;
     * */
    public static void readFile(){

        File fich = new File("/Users/ViegasMP/IS/IS_2021-main/Assignment1/proto_code/data.txt");

        if (fich.exists() && fich.isFile()){

            try{

                FileReader reader = new FileReader(fich);
                BufferedReader buffRead = new BufferedReader(reader);
                String line;
                String split[];
                System.out.println("cheguei");

                ctlg = Catalog.newBuilder();

                while((line = buffRead.readLine()) != null){
                    System.out.println("cheguei");

                    split = line.split(";");
                    Boolean check = true;

                    // pet case
                    if (split[0].compareTo("+") == 0){

                        Pet.Builder newPet = Pet.newBuilder();
                        Owner.Builder owner = Owner.newBuilder();
                        newPet.setPetId(Integer.parseInt(split[1]));

                        // verifica se já existe algum pet com aquele id
                        if ((ctlg.getAllPetsList()).isEmpty() == false){
                            for (Pet p: ctlg.getAllPetsList()){
                                if (p.getPetId() == newPet.getPetId()){
                                    check = false;
                                    break;
                                }
                            }
                        }
                        if (!check){
                            break;
                        }

                        newPet.setName(split[2]);
                        newPet.setSpecies(split[3]);
                        newPet.setGender(split[4]);
                        newPet.setWeight(Integer.parseInt(split[5]));
                        newPet.setBirth((split[6]));
                        newPet.setFormDesc(split[7]);
                        newPet.setOwnerId(Integer.parseInt(split[8]));

                        // verifica se o owner já existe, e se sim adiciona o pet
                        if ((ctlg.getAllOwnersList()).isEmpty() == false){
                            for (Owner o: ctlg.getAllOwnersList()){
                                if (o.getOwnerId() == newPet.getOwnerId()){
                                    owner.addPets(o.getOwnerId(), newPet);
                                    break;
                                }
                            }
                        }

                        ctlg.addAllPets(newPet);

                    }

                    // owner case
                    else if (split[0].compareTo("-") == 0) {

                        Owner.Builder newOwner = Owner.newBuilder();
                        newOwner.setOwnerId(Integer.parseInt(split[1]));

                        // verifica se já existe algum owner com aquele id
                        if (!(ctlg.getAllOwnersList()).isEmpty()){
                            for (Owner o: ctlg.getAllOwnersList()){
                                if (o.getOwnerId() == newOwner.getOwnerId()){
                                    check = false;
                                    break;
                                }
                            }
                        }
                        if (!check){
                            break;
                        }

                        newOwner.setName(split[2]);
                        newOwner.setBirth(split[3]);
                        newOwner.setTelephone(Integer.parseInt(split[4]));
                        newOwner.setAddress(split[5]);

                        // verifica se já existe algum pet, e se sim adiciona ao owner
                        if (!(ctlg.getAllPetsList()).isEmpty()){
                            for (Pet p: ctlg.getAllPetsList()){
                                if (p.getOwnerId() == newOwner.getOwnerId()){
                                    owner.addPets(newOwner.getOwnerId(), p);
                                }
                            }
                        }

                        ctlg.addAllOwners(newOwner);

                    }

                }

                System.out.println("Leu o ficheiro todo!");
                buffRead.close();

            } catch (Exception e) {
                System.out.println("ERRO a ler o ficheiro!");
            }

        } else {
            System.out.println("ERRO: Ficheiro não existe!");
        }

    }

    public static void main(String[] args) throws JAXBException, FileNotFoundException {

        long startTime = System.nanoTime();

        //ctlg = new Catalog();

        // Xml
        //JAXBContext obj = JAXBContext.newInstance(Catalog.class); // create the JAXB Content
        //Marshaller marsh = obj.createMarshaller();
        //marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        readFile();

        //marsh.marshal(ctlg, new FileOutputStream("data_output.xml"));

        long endTime = System.nanoTime();
        // se virmos que ns é demasiado grande fazer /1000000 (ms)
        System.out.println("Time elapsed (ns): " + (endTime-startTime));

    }

}
