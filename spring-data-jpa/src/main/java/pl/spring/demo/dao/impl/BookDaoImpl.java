package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("bookDao")
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	@Autowired
	private Sequence sequence;

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		return null;
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {
		return null;
	}

	@Override
	@NullableId
	public BookEntity save(BookEntity book) {
		ALL_BOOKS.add(book);
		return book;
	}

	public Long getNextBookId() {
		return sequence.nextValue(ALL_BOOKS);
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", Arrays.asList(new AuthorEntity(1L, "Wiliam", "Szekspir"))));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", Arrays.asList(new AuthorEntity(2L, "Hanna", "OÅ¼ogowska"))));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", Arrays.asList(new AuthorEntity(3L, "Jan", "Parandowski"))));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w NiekÅ‚aju", Arrays.asList(new AuthorEntity(4L, "Edmund", "Niziurski"))));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", Arrays.asList(new AuthorEntity(5L, "Zbigniew", "Nienacki"))));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", Arrays.asList(new AuthorEntity(6L, "Aleksander", "Fredro"))));
	}
}
