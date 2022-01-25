/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.securityTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import static org.asciidoctor.jruby.cli.DocTypeEnum.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Lenovo
 */
@Controller

public class AuthorController  {
 @Autowired
    AuthorRepository authorRepository;
 @Autowired
 BookRepository bookRepository;
 
    private AuthorService authorService;

    @GetMapping("/authors")
    public String index(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }
   
    
       @GetMapping("/authors/new")
    public String showNewBookForm(Model model) {
        List<Book> books = bookRepository.findAll();
        
        model.addAttribute("author", new Author());
        model.addAttribute("books", books);
        
        return "add-author";
    }
    
    @PostMapping("/authors/addauthor")
    public String addAuthor(@Valid Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-author";
        }
        
        authorService.save(author);
        return "redirect:/";
    }

    @GetMapping("/authors/edit/{authorId}")
    public String showAuthorUpdateForm( @PathVariable("authorId") long authorId, Model model) {
        List<Author> authors = authorRepository.findAll();
        Author author = authorService.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + authorId));
        
        model.addAttribute("author", author);
        model.addAttribute("authors", authors);
        return "update-author";
    }

    @PostMapping("/authors/update/{authorId}")
    public String updateAuthor(@PathVariable("authorId") long id, @Valid Author author,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            author.setAuthorId(id);
            return "update-author";
        }

        authorService.save(author);
        return "redirect:/";
    }

    @GetMapping("/authors/delete/{authorId}")
    public String deleteAuthor(@PathVariable("authorId") long id, Model model) {
        Author author = authorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        authorService.delete(author);
        return "redirect:/";
    }
    
    //@GetMapping("/authors/")
    @RequestMapping(path = {"/authors","/authors/searchAuthors"})
    public String showAuthorsByName(@RequestParam (name = "searchAuthors", required = false)Author author, String authorName, Model model) {
        
  if(authorName!=null) {
   List<Author> authors =  authorRepository.findByKeyword(authorName);
   model.addAttribute("authors", authors);
  }else {
  List<Author> authors = authorRepository.findAll();
  model.addAttribute("authors", authors);}
 
        return "authors";
    }
    
    
    
    
}
