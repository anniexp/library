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
import javax.validation.constraints.Min;
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
    @Max(255)
    @Min(1)
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

   
  
    public Author() {
        
    }

    public Author(long authorId, String authorName, String nationality, Set<Book> books) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.nationality = nationality;
        this.books = books;
        
    }
    
    
}
