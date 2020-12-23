package com.infosys.fsstar.eap7;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.reactivex.Single;
import lombok.*;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HelloWorld {
    @Inject
    HelloService helloService;

    @GET
    @Path("/json")
    @Produces({ "application/json" })
    public String getHelloWorldJSON() {
        return "{\"result\":\"" + helloService.createHelloMessage("World") + "\"}";
    }

    @GET
    @Path("/xml")
    @Produces({ "application/xml" })
    public String getHelloWorldXML() {
        return "<xml><result>" + helloService.createHelloMessage("World") + "</result></xml>";
    }


    @GET
    @Path("/rxjava/single")
    @Produces(MediaType.APPLICATION_JSON)
    public Single<Book> getBook() {
        return Single.just(new Book(1, "Test Book"));
    }


}
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Book {
    private int bookId;
    private String bookName;
}