package pl.spring.demo.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.to.BookTo;

@Service
public class BookMapperImpl implements BookMapper {

	@Override
	public BookTo mappBookEntityToBookTo(BookEntity bookEntity) {
		return new BookTo(bookEntity);
	}

	@Override
	public List<BookTo> mappBookEntityToBookTo(List<BookEntity> bookEntities) {
		List<BookTo> bookTos = new ArrayList<BookTo>();
		for (BookEntity bookEntity : bookEntities) {
			bookTos.add(mappBookEntityToBookTo(bookEntity));
		}
		return bookTos;
	}

	@Override
	public BookEntity mappBookToToBookEntity(BookTo bookTo) {
		return new BookEntity(bookTo);
	}

	@Override
	public List<BookEntity> mappBookToToBookEntity(List<BookTo> bookTos) {
		List<BookEntity> bookEntities = new ArrayList<BookEntity>();
		for (BookTo bookTo : bookTos) {
			bookEntities.add(mappBookToToBookEntity(bookTo));
		}
		return bookEntities;
	}
}
