import APPOO.lab1.RealPlayer
import org.junit.Test

/**
 * Created by Dennis on 29-Apr-17.
 */
class testGame extends GroovyTestCase{

    @Test
    void testAppResults() {

        RealPlayer player = new RealPlayer()
        print player.chooseLocation()
    }
}
