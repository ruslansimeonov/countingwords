import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class CountingWordsResource {

    @GET
    public String sayHello(){
        return "Hello world";
    }
}
