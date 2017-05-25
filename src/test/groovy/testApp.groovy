import PR.lab2.App
import org.junit.Test


/**import org.junit.Test
 * Created by Dennis on 31-Mar-17.
 **/
class testApp extends GroovyTestCase{

    App testingApp = new App()

    @Test
    void testAppResults() {

        def descriptors = ["Process1", "Process2", "Process3", "Process4", "Process5"] as String[]
        def timers = [10, 5, 3, 7, 4] as int[]
        List<String> expectedResult = ["Finished process Process1", "Finished process Process3", "Finished process Process2", "Finished process Process4", "Finished process Process5"]

        assertEquals(expectedResult, testingApp.run(descriptors, timers))
    }
}