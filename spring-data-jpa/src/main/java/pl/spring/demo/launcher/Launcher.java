package pl.spring.demo.launcher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.spring.demo.service.BookService;

public class Launcher {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
		BookService bookService = (BookService) context.getBean("bookService");
		System.out.println(bookService.findAllBooks());
	}
}
