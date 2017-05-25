import PR.lab3.LinkValidator
import org.junit.Test

/**
 * Created by Dennis on 30-Mar-17.
 **/
class testLinkValidator extends GroovyTestCase{

    LinkValidator validator = new LinkValidator()

    @Test
    void testValidLinks() {

        List<String> urls = ["http://stackoverflow.com/questions/6467848/how-to-get-http-response-code-for-a-url-in-java", "https://www.facebook.com/"]

        urls.each {
            assertEquals(true, validator.isValidUrl(it))
        }
    }

    @Test
    void testInvalidLinks() {

        List<String> wrongUrls = ["google.com", "http://bla-bla.com/"]

        wrongUrls.each {
            assertEquals(false, validator.isValidUrl(it))
        }
    }
}
