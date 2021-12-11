package creditCard;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.transaction.*;
import javax.transaction.NotSupportedException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.Client;

import java.util.List;

@RequestScoped
@Path("/myservice")
@Produces(MediaType.APPLICATION_JSON) public class MyService {

    @EJB
    private IManageCreditCardCo manageCreditCardCo;
    private ObjectMapper mapper = new ObjectMapper();

    @GET
    @Path("/clientsList")
    public String clientsList() throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException, JsonProcessingException {
        String out = mapper.writeValueAsString(manageCreditCardCo.listClients());
        System.out.println("Sending clients list to CLI...");
        return out;
    }

    @GET
    @Path("/managersList")
    public String managersList() throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException, JsonProcessingException {
        String out = mapper.writeValueAsString(manageCreditCardCo.listManagers());
        System.out.println("Sending managers list to CLI...");
        return out;
    }

    @GET
    @Path("/paymentsList")
    public String paymentsList() throws JsonProcessingException {
        String out = mapper.writeValueAsString(manageCreditCardCo.listTransactions());
        System.out.println("Sending transactions list to CLI...");
        return out;
    }

    @GET
    @Path("/currenciesList")
    public String currenciesList() throws JsonProcessingException {
        String out = mapper.writeValueAsString(manageCreditCardCo.listCurrencies());
        System.out.println("Sending currencies list to CLI...");
        return out;
    }

    @GET
    @Path("/totalCredit")
    public float totalCredit() throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException, JsonProcessingException {
        float out = Float.parseFloat(mapper.writeValueAsString(manageCreditCardCo.getTotalCredit()));
        System.out.println("Sending total credit to to CLI...");
        return out;
    }

    @POST
    @Path("/addManager")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addManager(String jsonString) throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException, JsonProcessingException {
        List<Object> aux = mapper.readValue(jsonString, List.class);
        String out = manageCreditCardCo.addManager((String) aux.get(0));
        if (out.isEmpty()){
            System.out.println("Failed to add manager to the database...");
            return Response.serverError().entity(out).build();
        }
        System.out.println("Manager added to the database...");
        return Response.ok().entity(out).build();
    }

    @POST
    @Path("/addClient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClient(String jsonString) throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException, JsonProcessingException {
        List<Object> aux = mapper.readValue(jsonString, List.class);
        String out = manageCreditCardCo.addClient((String) aux.get(0), (int) aux.get(1));
        if (out.isEmpty()){
            System.out.println("Failed to add client to the database...");
            return Response.serverError().entity(out).build();
        }
        System.out.println("Client added to the database...");
        return Response.ok().entity(out).build();
    }

    @POST
    @Path("/addCurrency")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCurrency(String jsonString) throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException, JsonProcessingException {
        List<Object> aux = mapper.readValue(jsonString, List.class);
        String out = manageCreditCardCo.addCurrency((String) aux.get(0), (float) aux.get(1));
        if (out.isEmpty()){
            System.out.println("Failed to add currency to the database...");
            return Response.serverError().entity(out).build();
        }
        System.out.println("Currency added to the database...");
        return Response.ok().entity(out).build();
    }

    @POST
    @Path("/clientCredit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response clientCredit(String jsonString) throws JsonProcessingException {
        List<Object> aux = mapper.readValue(jsonString, List.class);
        float out = manageCreditCardCo.getClientCredit((int) aux.get(0));
        if (out == 0){
            System.out.println("Failed to get client credit...");
            return Response.serverError().entity(out).build();
        }
        System.out.println("Client credit sent...");
        return Response.ok().entity(out).build();
    }

    @POST
    @Path("/clientPayment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response clientPayment(String jsonString) throws JsonProcessingException {
        List<Object> aux = mapper.readValue(jsonString, List.class);
        float out = manageCreditCardCo.getClientPayments((int) aux.get(0));
        if (out == 0){
            System.out.println("Failed to get client payment...");
            return Response.serverError().entity(out).build();
        }
        System.out.println("Client payments sent...");
        return Response.ok().entity(out).build();
    }

    @POST
    @Path("/clientBalance")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response clientBalance(String jsonString) throws JsonProcessingException {
        List<Object> aux = mapper.readValue(jsonString, List.class);
        float out = manageCreditCardCo.getClientBalance((int) aux.get(0));
        if (out == 0){
            System.out.println("Failed to get client balance...");
            return Response.serverError().entity(out).build();
        }
        System.out.println("Client balance sent...");
        return Response.ok().entity(out).build();
    }
}
