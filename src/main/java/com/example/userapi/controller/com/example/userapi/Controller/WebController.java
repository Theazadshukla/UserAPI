package com.example.userapi.Controller;

import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    // Display Signup Page
    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    // Handle Signup Form Submission
    @PostMapping("/signup")
    public String signUpUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup";
        }
        try {
            userService.signUpUser(user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }
        model.addAttribute("user", user);
        return "redirect:/success";
    }

    // Display Success Page
    @GetMapping("/success")
    public String showSuccess(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", user);
        return "success";
    }

    // Handle Edit Request with User ID input from success page
    @GetMapping("/users/edit")
    public String handleEditRequest(@RequestParam("id") Long id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
        model.addAttribute("user", user);
        return "edit-user";
    }

    // Handle User Update and redirect to update-success
    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-user";
        }
        try {
            userService.updateUser(id, user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "edit-user";
        }
        model.addAttribute("user", user);
        return "redirect:/update-success";
    }

    // Display Update Success Page
    @GetMapping("/update-success")
    public String showUpdateSuccess(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", user);
        return "update-success";
    }

   
}
