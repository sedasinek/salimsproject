package com.yelloware.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.yelloware.entity.Book;
import com.yelloware.entity.Role;
import com.yelloware.repository.BookRepository;
import com.yelloware.repository.RoleRepository;

import javax.persistence.EntityNotFoundException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class BookRestController {

	@Autowired
    private  BookRepository repository;
    
    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Book> getAllBooks() {
       System.out.println("getAllBooks() - start");
        Collection<Book> collection = repository.findAll();
       System.out.println("getAllBooks() - end");
        return collection;
    }

    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable Long id) {
       System.out.println("getBookById() - start: id = {}"+id);
        Book receivedBook = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id = Not found"));
       System.out.println("getBookById() - end: book = {}"+receivedBook.getId());
        return receivedBook;
    }

    @GetMapping(value = "/books", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public Collection<Book> findBookByName(@RequestParam(value = "name") String name) {
       System.out.println("findBookByName() - start: name = {}"+name);
        Collection<Book> collection = repository.findByName(name);
       System.out.println("findBookByName() - end: collection = {}"+collection);
        return collection;
    }

    /*  @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book refreshBook(@PathVariable("id") long id, @RequestBody Book book) {
       System.out.println("refreshBook() - start: id = {}, book = {}" +id+" " +book);
      Book updatedBook = repository.findById(id)
                .map(entity -> {
                    entity.setName(book.getName());
                    entity.setDescription(book.getDescription());
                    entity.setTags(book.getTags());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Book with id = Not found"));
       System.out.println("refreshBook() - end: updatedBook = {}"+updatedBook);
        return updatedBook;
    }*/

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookById(@PathVariable Long id) {
       System.out.println("removeBookById() - start: id = {}" +id);
        repository.deleteById(id);
       System.out.println("removeBookById() - end: id = {}"+id);
    }

    @DeleteMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllBooks() {
       System.out.println("removeAllBooks() - start");
        repository.deleteAll();
      System.out.println("removeAllBooks() - end");
    }
}
