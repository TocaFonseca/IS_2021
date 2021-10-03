import java.io.*;

public class App {

    private static Catalog.Builder ctlg;

    /**
     * Leitura do ficheiro com o data set e guarda nas respetivas estruturas
     * Cada linha que começa com um + representa um pet
     * Cada linha que começa com um - representa um owner
     * Os atributos estão separados por ;
     * */
    public static Catalog readFile(){

        File fich = new File("data.txt");

        if (fich.exists() && fich.isFile()){

            try{

                FileReader reader = new FileReader(fich);
                BufferedReader buffRead = new BufferedReader(reader);
                String line;
                String split[];

                ctlg = Catalog.newBuilder();

                while((line = buffRead.readLine()) != null){

                    split = line.split(";");
                    Boolean check = true;

                    // pet case
                    if (split[0].compareTo("+") == 0){

                        Pet.Builder newPet = Pet.newBuilder();
                        Owner.Builder owner = Owner.newBuilder();
                        newPet.setPetId(Integer.parseInt(split[1]));

                        // verifica se já existe algum pet com aquele id
                        if (!(ctlg.getAllPetsList()).isEmpty()){
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
                        if (!(ctlg.getAllOwnersList()).isEmpty()){
                            int cont=0;
                            for (Owner o: ctlg.getAllOwnersList()){
                                if (o.getOwnerId() == newPet.getOwnerId()){
                                    Owner.Builder petOwner = o.toBuilder();
                                    petOwner.addPets(newPet);
                                    ctlg.removeAllOwners(cont);
                                    ctlg.addAllOwners(petOwner);
                                    break;
                                }
                                cont++;
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
                                    newOwner.addPets(p);
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
        return ctlg.build();

    }

    public static void main(String[] args) throws IOException {

        long startTime = System.nanoTime();

        // Google Protocol Buffer
        //gravar o catalogo no ficheiro
        FileOutputStream aOutput= new FileOutputStream("data_output.proto");
        Catalog ctlg = readFile();
        ctlg.writeTo(aOutput);
        aOutput.close();

        //ler o ficheiro de output
        Catalog backupCtlg=Catalog.parseFrom(new FileInputStream("data_output.proto"));
        System.out.println(backupCtlg.getAllOwnersList());

        long endTime = System.nanoTime();
        // se virmos que ns é demasiado grande fazer /1000000 (ms)
        System.out.println("Time elapsed (ms): " + (endTime-startTime)/1000000);

    }

}
