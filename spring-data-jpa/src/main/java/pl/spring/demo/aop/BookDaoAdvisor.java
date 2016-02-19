package pl.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.IdAware;

@Component("bookDaoAdvisor")
@Aspect()
public class BookDaoAdvisor {

	@Pointcut("@annotation(pl.spring.demo.annotation.NullableId)")
	public void pointCutForNullableId() {};

	@Before("pointCutForNullableId()")
	public void beforeCheckNotNullId(JoinPoint joinPoint) {
		checkNotNullId(joinPoint.getArgs()[0]);
	}

	private void checkNotNullId(Object o) {
		if (o instanceof IdAware && ((IdAware) o).getId() != null) {
			throw new BookNotNullIdException();
		}
	}
}
