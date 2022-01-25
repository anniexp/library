package com.example.securityTest;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
//zadeistva proccess if depebdanty injection
    @Autowired
    BookRepository bookRepository;
    private BookService bookService;
    
    @Autowired
    ReportsRepository reportRepository;
    
    

    @GetMapping("/books")
    public String books(Model model) {
        List<Book>listBooks = bookRepository.findAll();
        model.addAttribute("books", listBooks);
        return "books";
    }
    
    @GetMapping("/books/signup")
    public String showSignUpForm(Book book) {
        return "add-book";
    }

    @PostMapping("/books/addbook")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }

        bookService.save(book);
        model.addAttribute("book", bookRepository.findAll());
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{bookId}")
    public String showUpdateForm(@PathVariable("bookId") long bookId, Model model) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookId));

        model.addAttribute("book", book);
        return "update-book";
    }

    @PostMapping("/books/update/{bookId}")
    public String updateBook(@PathVariable("bookId") long bookId, @Valid Book book,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setBookId(bookId);
            return "update-book";
        }

        bookRepository.save(book);
        model.addAttribute("book", bookRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/books/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") long bookId, Model model) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookId));
        bookRepository.delete(book);
        model.addAttribute("book", bookRepository.findAll());
        return "redirect:/books";
    }
    @GetMapping ("/books/search")
    public String search (@RequestParam(name = "search", required = false) String title, Model model)
    {
     model.addAttribute("book", bookRepository.findByTitleStartingWith(title));
     return "redirect:/books";
    }
    
    
    
    
     @GetMapping("/books/getBook/{bookId}")
    public String showBorrowBookForm(@PathVariable("bookId") long bookId, Model model) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookId));

        model.addAttribute("book", book);
        return "borrow-book";
    }
    
    @PostMapping ("/books/borrow/{bookId}")
    public String borrowBook 
             (@PathVariable("bookId") long bookId, @Valid Book book,
            BindingResult result, Model model, Report report) {
        if (!book.isIsRented()) {
            book.setIsRented(true);
            bookRepository.save(book);
            
            
            reportRepository.save(report);
            
          model.addAttribute("book", bookRepository.findAll());

        }
         return "redirect:/";
    
        
             }
             
             
             
             
            
    
   
}
