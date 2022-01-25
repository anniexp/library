/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.securityTest;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
/**
 *
 * @author Lenovo
 */
@Entity

        @Table(name = "authors") 
public class Author implements Serializable {

    
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    private long authorId;

    @NotBlank(message = "Author name is mandatory") @Max(255)
        @Column(name = "author_name")
    private String authorName;

   @Max(60)
    
       @Column(name = "nationality")
    private String nationality;

   @OneToMany(targetEntity = Book.class ,mappedBy = "author",fetch = FetchType.LAZY,orphanRemoval = false, cascade = CascadeType.ALL )
  // @JoinColumn(name = "bookId", nullable = false, updatable = false, insertable = false)
   private Set<Book> books;

   
  
    

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

   
    public Set<Book> getBook() {
        return books;
    }

    public void setBook(Set<Book> book) {
        this.books = book;    
    }
    protected Set<Book> getBooksInternal() {
		if (this.books == null) {
			this.books = new HashSet<>();
		}
		return this.books;
	}

	protected void setPetsInternal(Set<Book> books) {
		this.books = books;
	}

	public List<Book> getBooks() {
		List<Book> sortedBooks = new ArrayList<>(getBooksInternal());
		PropertyComparator.sort(sortedBooks, new MutableSortDefinition("title", true, true));
		return Collections.unmodifiableList(sortedBooks);
	}

	public void addBook(Book book) {
		if (book.isNew()) {
			getBooksInternal().add(book);
		}
		book.setAuthor(this);
	}

	
	public Book getBook(String title) {
		return getBook(title);
	}
        public boolean isNew() {
		return this.authorId == 0;
	}

	/**
	 * Return the Pet with the given name, or null if none found for this Owner.
	 * @param name to test
	 * @return true if pet name is already in use
	 */
	public Book getBook(String title, boolean ignoreNew) {
		title = title.toLowerCase();
		for (Book book : getBooksInternal()) {
			if (!ignoreNew || !book.isNew()) {
				String bookTitle = book.getTitle();
				bookTitle = bookTitle.toLowerCase();
				if (bookTitle.equals(title)) {
					return book;
				}
			}
		}
		return null;
	}

    
    
    public Author() {
        
    }

  /*  public Author(long authorId, String authorName, String nationality, Set<Book> book, long bookId) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.nationality = nationality;
        this.book = book;
        this.bookId = bookId;
    }*/

    public Author(long authorId, String authorName, String nationality, Set<Book> books) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.nationality = nationality;
        this.books = books;
        
    }
    
    
}
