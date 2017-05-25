import PR.lab3.ApiConsumer
import PR.lab3.Server
import org.junit.Test

/**
 * Created by Dennis on 21-May-17.
 **/
class testHttp extends GroovyTestCase {

    @Test
    void testHttpActions() {

        //Server server = new Server()

        ApiConsumer apiConsumer = new ApiConsumer()
        apiConsumer.get()
        apiConsumer.post()
    }
}
