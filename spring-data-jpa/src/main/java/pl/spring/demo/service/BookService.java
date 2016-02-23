package pl.spring.demo.service;

import pl.spring.demo.to.BookTo;

import java.util.List;

public interface BookService {

	/**
	 * returns list of all books
	 * @return list of BookTo or empty list
	 */
    List<BookTo> findAllBooks();
    
    /**
     * returns list of books in witch "title" contains parameter
     * @param title
     * @return list of BookTo or empty list
     */
    List<BookTo> findBooksByTitle(String title);
    
    /**
     * returns list of books in witch "firstName lastName" contains parameter
     * @param author
     * @return list of BookTo or empty list
     */
    List<BookTo> findBooksByAuthor(String author);

    /**
     * add new book to database, throw exception if struct the book does not meet the requirements
     * @param book
	 * @exception BookNotNullIdException
     * @return object added to the database
     */
    BookTo saveBook(BookTo book);
}
