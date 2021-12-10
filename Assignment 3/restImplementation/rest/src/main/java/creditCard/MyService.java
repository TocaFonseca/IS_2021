package creditCard;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.transaction.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import beans.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
}
