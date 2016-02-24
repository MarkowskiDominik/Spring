package pl.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.idaware.IdAware;
import pl.spring.demo.entity.BookEntity;

@Component("bookDaoAdvisor")
@Aspect()
public class BookDaoAdvisor {

	@Autowired
	private BookDao bookDao;

	@Before("@annotation(pl.spring.demo.annotation.NullableId)")
	public void checkNotNullId(JoinPoint joinPoint) {
		Object o = joinPoint.getArgs()[0];
		if (o instanceof IdAware && ((IdAware) o).getId() != null) {
			throw new BookNotNullIdException();
		}
	}

	@Before("execution(public * pl.spring.demo.dao.BookDao.save(pl.spring.demo.entity.BookEntity))")
	public void setIdForSaveBook(JoinPoint joinPoint) {
		BookEntity book = (BookEntity) joinPoint.getArgs()[0];
		if (book.getId() == null) {
			book.setId(bookDao.getNextBookId());
		}
	}
}
