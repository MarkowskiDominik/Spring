package pl.spring.demo.dao;

import pl.spring.demo.entity.BookEntity;

import java.util.List;

public interface BookDao {
	
	/**
	 * returns list of all books
	 * @return list of BookEntity or empty list 
	 */
    List<BookEntity> findAll();

	/**
	 * returns list of books in witch "title" contains parameter
	 * @param title
	 * @return list of BookEntity or empty list 
	 */
    List<BookEntity> findBookByTitle(String title);

	/**
	 * returns list of books in witch "firstName lastName" contains parameter
	 * @param author
	 * @return list of BookEntity or empty list 
	 */
    List<BookEntity> findBooksByAuthor(String author);

	/**
	 * add new book to database, throw exception if struct the book does not meet the requirements
	 * @param book
	 * @exception BookNotNullIdException
	 * @return object added to the database 
	 */
    BookEntity save(BookEntity book);

	/**
	 * return consecutive unused Id
	 * @return positive long integer
	 */
	Long getNextBookId();

}
