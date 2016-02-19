package pl.spring.demo.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.to.BookTo;

@Service("bookMapper")
public class BookMapperImpl implements BookMapper {
	
	@Override
	public BookTo mappBookEntityToBookTo(BookEntity bookEntity) {
		Long id = bookEntity.getId();
		String title = bookEntity.getTitle();
		String authors = null;
		for (AuthorEntity authorEntity : bookEntity.getAuthors()) {
			authors = authors + authorEntity.getFirstName() + " " + authorEntity.getLastName() + ", ";
		}
		return new BookTo(id, title, authors.substring(0, authors.length()-2));
	}

//	@Override
//	public List<BookTo> mappBookEntityToBookTo(List<BookEntity> bookEntities) {
//		List<BookTo> bookTos = new ArrayList<BookTo>();
//		for (BookEntity bookEntity : bookEntities) {
//			bookTos.add(mappBookEntityToBookTo(bookEntity));
//		}
//		return bookTos;
//	}

	@Override
	public BookEntity mappBookToToBookEntity(BookTo bookTo) {
		Long id = bookTo.getId();
    	String title = bookTo.getTitle();
    	List<AuthorEntity> authors = new ArrayList<AuthorEntity>();
		System.out.println("");
    	for (String authorTo : bookTo.getAuthors().split(",")) {
			authors.add(new AuthorEntity(1l, authorTo.split(" ")[0], authorTo.split(" ")[1]));
		}
		return new BookEntity(id, title, authors);
	}

//	@Override
//	public List<BookEntity> mappBookToToBookEntity(List<BookTo> bookTos) {
//		List<BookEntity> bookEntities = new ArrayList<BookEntity>();
//		for (BookTo bookTo : bookTos) {
//			bookEntities.add(mappBookToToBookEntity(bookTo));
//		}
//		return bookEntities;
//	}
}
