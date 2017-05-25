package PR.lab3

import groovyx.net.http.FromServer
import groovyx.net.http.HttpBuilder

/**
 * Created by Dennis on 21-May-17.
 **/
class ApiConsumer {

    HttpBuilder http

    ApiConsumer(){
        http = HttpBuilder.configure {
            request.uri = 'http://localhost:4567'
            response.success { FromServer from, Object body ->
                body.name
            }
        }
    }

    void get() {

        print "Server content: " + http.get(String) {
            request.uri.path = '/message'
        }
    }

    void post() {

        http.post(String) {
            request.uri.path = '/message'
            request.contentType = 'application/json'
            request.body = { name : 'HttpBuilder is alive!' }
        }
    }

    void delete()
    {
        http.delete(String) {
            request.uri.path = '/message'
        }
    }
}
