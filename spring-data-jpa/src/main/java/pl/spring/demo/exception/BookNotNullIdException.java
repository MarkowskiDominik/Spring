package pl.spring.demo.exception;

/**
 * exception when add new books to the database and it has set Id
 * (Id should be assigned by the DAO)
 */
@SuppressWarnings("serial")
public class BookNotNullIdException extends RuntimeException {
}
