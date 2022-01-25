package com.example.securityTest;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    // @NotBlank(message = "Author name is mandatory")
    //private String author;
    @Column(name = "year_of_publishing")
    private int yearOfPublishing;
    @Column(name = "is_rented")
    private boolean isRented;

    //[ForeignKey("authorId")]
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "author_id")
    //@Fetch(FetchMode.JOIN)
    //  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Author author;
    //private Integer authorId;
    //@NotBlank(message = "Author id is mandatory") @Max(255)@Min(1)
    // private long authorId;

    @NotBlank(message = "Title is mandatory")
    @Max(255)
    @Min(1)
    private String title;

    @NotBlank(message = "isbn is mandatory")
    @Column(unique = true)
    @Max(13)
    @Min(13)
    private String isbn;

    public Book(long bookId, Author author, long authorId, String title, String isbn, int yearOfPublishing, boolean isRented) {
        this.bookId = bookId;
        this.title = title;
        this.isbn = isbn;
        this.yearOfPublishing = yearOfPublishing;
        this.isRented = isRented;
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    /*  public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }*/
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

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

    @Override
    public String toString() {
        return "Book{" + "bookId=" + bookId + ", yearOfPublishing=" + yearOfPublishing + ", isRented=" + isRented + ", author=" + author + ", title=" + title + ", isbn=" + isbn + '}';
    }

    public Book() {

    }

    public boolean isNew() {
        return this.bookId == 0;
    }
    
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, optional = true)
   // @JoinColumn(name = "reportId")
    private Report report;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}

    
   
    
   
    // standard constructors / setters / getters / toString
