import java.io.*;

public class App {

    private static Catalog ctlg;

    /**
     * Leitura do ficheiro com o data set e guarda nas respetivas estruturas
     * Cada linha que começa com um + representa um pet
     * Cada linha que começa com um - representa um owner
     * Os atributos estão separados por ;
     * */
    public static void readFile(){

        File fich = new File("data.txt");

        if (fich.exists() && fich.isFile()){

            try{

                FileReader reader = new FileReader(fich);
                BufferedReader buffRead = new BufferedReader(reader);
                String line;
                String split[];

                while((line = buffRead.readLine()) != null){

                    split = line.split(";");
                    Boolean check = true;

                    // pet case
                    if (split[0].compareTo("+") == 0){

                        Pet newPet = new Pet();
                        newPet.setPet_id(Integer.parseInt(split[1]));

                        // verifica se já existe algum pet com aquele id
                        if ((ctlg.getAllPets()).isEmpty() == false){
                            for (Pet p: ctlg.getAllPets()){
                                if (p.getPet_id() == newPet.getPet_id()){
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
                        newPet.setbirth((split[6]));
                        newPet.setForm_desc(split[7]);
                        newPet.setOwner_id(Integer.parseInt(split[8]));

                        // verifica se o owner já existe, e se sim adiciona o pet
                        if ((ctlg.getAllOwners()).isEmpty() == false){
                            for (Owner o: ctlg.getAllOwners()){
                                if (o.getOwner_id() == newPet.getOwner_id()){
                                    o.addPet(newPet);
                                    break;
                                }
                            }
                        }

                        ctlg.addPet(newPet);

                    }

                    // owner case
                    else if (split[0].compareTo("-") == 0) {

                        Owner newOwner = new Owner();
                        newOwner.setOwner_id(Integer.parseInt(split[1]));

                        // verifica se já existe algum owner com aquele id
                        if ((ctlg.getAllOwners()).isEmpty() == false){
                            for (Owner o: ctlg.getAllOwners()){
                                if (o.getOwner_id() == newOwner.getOwner_id()){
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
                        if ((ctlg.getAllPets()).isEmpty() == false){
                            for (Pet p: ctlg.getAllPets()){
                                if (p.getOwner_id() == newOwner.getOwner_id()){
                                    newOwner.addPet(p);
                                }
                            }
                        }

                        ctlg.addOwner(newOwner);

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

    public static void main(String[] args) throws FileNotFoundException {

        long startTime = System.nanoTime();

        ctlg = new Catalog();

        // Google Protocol Buffers


        readFile();

        long endTime = System.nanoTime();
        // se virmos que ns é demasiado grande fazer /1000000 (ms)
        System.out.println("Time elapsed (ns): " + (endTime-startTime));

    }

}
