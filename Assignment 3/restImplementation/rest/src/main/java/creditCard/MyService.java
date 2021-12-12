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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

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
        String out = mapper.writeValueAsString(manageCreditCardCo.listTransactionList());
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
        System.out.println("Sending total credit to CLI...");
        return out;
    }

    @GET
    @Path("/totalPayments")
    public float totalPayments() throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException, JsonProcessingException {
        float out = Float.parseFloat(mapper.writeValueAsString(manageCreditCardCo.getTotalPayments()));
        System.out.println("Sending total payments to CLI...");
        return out;
    }

    @GET
    @Path("/totalBalance")
    public float totalBalance() throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException, JsonProcessingException {
        float out = Float.parseFloat(mapper.writeValueAsString(manageCreditCardCo.getTotalBalance()));
        System.out.println("Sending total balance to CLI...");
        return out;
    }

    @GET
    @Path("/heighestDebt")
    public String heighestDebt() throws JsonProcessingException {
        String out = manageCreditCardCo.heighestDebt();
        System.out.println("Sending client to CLI...");
        return out;
    }

    @GET
    @Path("/heighestRevenue")
    public String heighestRevenue() throws JsonProcessingException {
        String out = manageCreditCardCo.heighestRevenue();
        System.out.println("Sending manager to CLI...");
        return out;
    }

    @POST
    @Path("/addManager")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addManager(String jsonString) throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException, JsonProcessingException {
        Map<String, Object> map = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
        String out = manageCreditCardCo.addManager((String) map.get("name"));
        if (out == null){
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
        Map<String, Object> map = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
        String out = manageCreditCardCo.addClient((String) map.get("name"), (int) map.get("manager"));
        if (out == null){
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
        Map<String, Object> map = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
        String out = manageCreditCardCo.addCurrency((String) map.get("name"), Float.parseFloat((String) map.get("exchange_rate")));
        if (out == null){
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
        Map<String, Object> map = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
        String out = manageCreditCardCo.getClientCredit(Integer.parseInt((String) map.get("id")));
        if (out == null){
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
        Map<String, Object> map = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
        String out = manageCreditCardCo.getClientPayments(Integer.parseInt((String) map.get("id")));
        if (out == null){
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
        Map<String, Object> map = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
        String out = manageCreditCardCo.getClientBalance(Integer.parseInt((String) map.get("id")));
        if (out == null){
            System.out.println("Failed to get client balance...");
            return Response.serverError().entity(out).build();
        }
        System.out.println("Client balance sent...");
        return Response.ok().entity(out).build();
    }
}
