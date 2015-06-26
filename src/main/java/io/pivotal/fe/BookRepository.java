package io.pivotal.fe;

public interface BookRepository {

    Book getByIsbn(String isbn);

}
