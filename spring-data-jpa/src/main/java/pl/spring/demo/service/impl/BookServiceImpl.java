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
				.map(bookEntity->bookMapper.bookEntityToBookTo(bookEntity))
				.collect(Collectors.toList());
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		return bookDao.findBookByTitle(title)
				.stream()
				.map(bookEntity->bookMapper.bookEntityToBookTo(bookEntity))
				.collect(Collectors.toList());
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		return bookDao.findBooksByAuthor(author)
				.stream()
				.map(bookEntity->bookMapper.bookEntityToBookTo(bookEntity))
				.collect(Collectors.toList());
	}

	@Override
	public BookTo saveBook(BookTo bookTo) {
		return bookMapper.bookEntityToBookTo(bookDao.save(bookMapper.bookToToBookEntity(bookTo)));
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}
