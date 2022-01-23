/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.securityTest;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Lenovo
 */
@Entity
        @Table(name = "authors") 
public class Author {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorId;

    @NotBlank(message = "Author name is mandatory") @Max(255)
    private String authorName;

   @Max(60)
    private String nationality;
    
   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
   @JoinColumn(name = "bookId", nullable = false, updatable = false, insertable = false)
   private Set<Book> book;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
   private long bookId;

    

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

  

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Author(long authorId, String author, String nationality, Set<Book> book, long bookId) {
        this.authorId = authorId;
        this.authorName = author;
        this.nationality = nationality;
        this.book = book;
        this.bookId = bookId;
    }

    public Set<Book> getBook() {
        return book;
    }

    public void setBook(Set<Book> book) {
        this.book = book;
    }
    
    public Author() {
        
    }
    
}
