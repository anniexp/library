/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.securityTest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Lenovo
 */
@Controller

public class AuthorController  {
 @Autowired
    AuthorRepository authorRepository;

    @GetMapping("/authors")
    public String index(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }
       @GetMapping ("/searched_authors")
    public String search (@RequestParam(name = "search", required = false) String authorName, Model model)
    {
     model.addAttribute("authorName", authorRepository.findByAuthorName(authorName));
     return "authors";
    }
}
