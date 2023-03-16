package com.shariqparwez.spring6webapp.bootstrap;

import com.shariqparwez.spring6webapp.domain.Author;
import com.shariqparwez.spring6webapp.domain.Book;
import com.shariqparwez.spring6webapp.domain.Publisher;
import com.shariqparwez.spring6webapp.repositories.AuthorRepository;
import com.shariqparwez.spring6webapp.repositories.BookRepository;
import com.shariqparwez.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author authorOne = new Author();
        authorOne.setFirstName("Eric");
        authorOne.setLastName("Evans");

        Book bookOne = new Book();
        bookOne.setTitle("Domain Driven Design");
        bookOne.setIsbn("123456");

        Author authorOneSaved = authorRepository.save(authorOne);
        Book bookOneSaved = bookRepository.save(bookOne);

        Author authorTwo = new Author();
        authorTwo.setFirstName("Rod");
        authorTwo.setLastName("Johnson");

        Book bookTwo = new Book();
        bookTwo.setTitle("J2EE Development without EJB");
        bookTwo.setIsbn("54757585");

        Author authorTwoSaved = authorRepository.save(authorTwo);
        Book bookTwoSaved = bookRepository.save(bookTwo);

        authorOneSaved.getBooks().add(bookOneSaved);
        authorTwoSaved.getBooks().add(bookTwoSaved);
        bookOneSaved.getAuthors().add(authorOneSaved);
        bookTwoSaved.getAuthors().add(authorTwoSaved);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("My Publisher");
        publisher.setAddress("123 Main");
        Publisher savedPublisher = publisherRepository.save(publisher);

        bookOneSaved.setPublisher(savedPublisher);
        bookTwoSaved.setPublisher(savedPublisher);

        authorRepository.save(authorOneSaved);
        authorRepository.save(authorTwoSaved);
        bookRepository.save(bookOneSaved);
        bookRepository.save(bookTwoSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
