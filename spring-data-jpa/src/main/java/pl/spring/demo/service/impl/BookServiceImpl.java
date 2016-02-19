package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.stream.Collectors;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private BookMapper bookMapper;

	@Override
	@Cacheable("booksCache")
	public List<BookTo> findAllBooks() {
		return bookDao.findAll()
				.stream()
				.map(bookEntity->bookMapper.mappBookEntityToBookTo(bookEntity))
				.collect(Collectors.toList());
//		return bookMapper.mappBookEntityToBookTo(bookDao.findAll());
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		return bookDao.findBookByTitle(title)
				.stream()
				.map(bookEntity->bookMapper.mappBookEntityToBookTo(bookEntity))
				.collect(Collectors.toList());
//		return bookMapper.mappBookEntityToBookTo(bookDao.findBookByTitle(title));
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		return bookDao.findBooksByAuthor(author)
				.stream()
				.map(bookEntity->bookMapper.mappBookEntityToBookTo(bookEntity))
				.collect(Collectors.toList());
//		return bookMapper.mappBookEntityToBookTo(bookDao.findBooksByAuthor(author));
	}

	@Override
	public BookTo saveBook(BookTo bookTo) {
		bookDao.save(bookMapper.mappBookToToBookEntity(bookTo));
		return bookTo;
//		return bookMapper.mappBookEntityToBookTo(bookDao.save(bookMapper.mappBookToToBookEntity(book)));
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}
