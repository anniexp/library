package com.example.securityTest;

import java.io.Serializable;
import java.time.Year;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

   // @NotBlank(message = "Author name is mandatory")
    //private String author;
    
 
     
     //[ForeignKey("authorId")]
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "book")
      //  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)

     private Set<Author>authors;
      //@NotBlank(message = "Author id is mandatory") @Max(255)@Min(1)
   // private long authorId;

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @NotBlank(message = "Title is mandatory") @Max(255)@Min(1)
    private String title;
    
     @NotBlank(message = "isbn is mandatory")    @Column(unique = true) @Max(13)@Min(13)
    private String isbn;

    public Book(long bookId, Set<Author> authors, long authorId, String title, String isbn, int yearOfPublishing, boolean isRented) {
        this.bookId = bookId;
        this.title = title;
        this.isbn = isbn;
        this.yearOfPublishing = yearOfPublishing;
        this.isRented = isRented;
        this.authors = authors;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    /*public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public boolean isIsRented() {
        return isRented;
    }

    public void setIsRented(boolean isRented) {
        this.isRented = isRented;
    }
     
    private int yearOfPublishing;
    
    private boolean isRented;

    public Book() {
        
    }
    }

    
   
    
   
    // standard constructors / setters / getters / toString
