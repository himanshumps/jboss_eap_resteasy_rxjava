package com.infosys.fsstar.eap7;

import io.reactivex.Completable;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/")
public class AppController {
    @GET
    @Path("/rxjava/single")
    @Produces({MediaType.APPLICATION_JSON})
    public Single<Book> getBook() {
        Book book = new Book();
        book.setBookId(1);
        book.setBookName("Test Book");
        return Single.just(book);
    }

    @GET
    @Path("/suspended")
    @Produces({MediaType.APPLICATION_JSON})
    public void getBookSuspended(@Suspended final AsyncResponse async) {
        Book book = new Book();
        book.setBookId(1);
        book.setBookName("Test Book");
        Single.just(book).subscribe(async::resume, async::resume);
    }

    @GET
    @Path("/completable")
    @Produces({MediaType.APPLICATION_JSON})
    public CompletionStage<Book> getBookCompletionStage() {
        CompletableFuture<Book> completableFuture = new CompletableFuture();
        Book book = new Book();
        book.setBookId(1);
        book.setBookName("Test Book");
        Single.just(book).subscribe(completableFuture::complete, completableFuture::completeExceptionally);
        return completableFuture;
    }
}
