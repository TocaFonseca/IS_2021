package creditCard;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.transaction.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import beans.*;
import data.*;


@RequestScoped
@Path("/myservice")
@Produces(MediaType.APPLICATION_JSON) public class MyService {
    @EJB
    private IManageCreditCardCo manageCreditCardCo;
    @GET
    @Path("/test")
    public String method0() {
        System.out.println("M1 executing...."); return "M1 executed...";
    }
    @GET
    @Path("/addManager")
    public String method1() throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException {
        System.out.println("M2 executing....");
        return manageCreditCardCo.addManager("Bob");
    }

    @GET
    @Path("/addClient")
    public String method2() throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException {
        System.out.println("M2 executing....");
        return manageCreditCardCo.addClient("Ana", "Bob");
    }

    @GET
    @Path("/listClients")
    public List<Client> method5() {
        System.out.println("M3 executing....");
        List<Client> list = manageCreditCardCo.listClients();
        return list;
    }
}
