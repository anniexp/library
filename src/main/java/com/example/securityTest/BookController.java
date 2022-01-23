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

    @GetMapping("/books")
    public String index(Model model) {
        List<Book>listBooks = bookRepository.findAll();
        model.addAttribute("books", listBooks);
        return "books";
    }
    
    @GetMapping("/signup")
    public String showSignUpForm(Book book) {
        return "add-book";
    }

    @PostMapping("/addbook")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }

        bookRepository.save(book);
        model.addAttribute("books", bookRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/edit/{bookId}")
    public String showUpdateForm(@PathVariable("bookId") long bookId, Model model) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookId));

        model.addAttribute("book", book);
        return "update-book";
    }

    @PostMapping("/update/{bookId}")
    public String updateBook(@PathVariable("bookId") long bookId, @Valid Book book,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setBookId(bookId);
            return "update-book";
        }

        bookRepository.save(book);
        model.addAttribute("books", bookRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") long bookId, Model model) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookId));
        bookRepository.delete(book);
        model.addAttribute("books", bookRepository.findAll());
        return "redirect:/";
    }
    @GetMapping ("/search")
    public String search (@RequestParam(name = "search", required = false) String title, Model model)
    {
     model.addAttribute("books", bookRepository.findByTitleStartingWith(title));
     return "books";
    }
    
    @GetMapping ("/borrow/{bookId}")
    public String borrowBook 
             (@PathVariable("bookId") long bookId, @Valid Book book,
            BindingResult result, Model model) {
        if (!book.isIsRented()) {
            book.setIsRented(true);
            
        }
         return "redirect:/";
    
        
             }
}
