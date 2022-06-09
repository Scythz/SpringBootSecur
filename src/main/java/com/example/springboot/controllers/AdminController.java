package com.example.springboot.controllers;

import com.example.springboot.models.User;
import com.example.springboot.service.RoleService;
import com.example.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService us;
    private final RoleService usr;

    public AdminController(UserService us, RoleService usr) {
        this.us = us;
        this.usr = usr;
    }

    @GetMapping()
    public String tableUsers(Model model) {
        model.addAttribute("users", us.getAllUsers());
        return "tableUsers";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("roles", usr.getRoles());
        model.addAttribute("user", new User());
        return "new";
    }

    @PutMapping
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "selectedRoles") String[] roles) {
        us.saveUser(user, roles);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("roles", usr.getRoles());
        model.addAttribute("user", us.getUserById(id));
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String patchUser(@PathVariable("id") int id,
                            @ModelAttribute User user,
                            @RequestParam(value = "selectedRoles") String[] roles) {
        us.updateUser(user,roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        us.delete(id);
        return "redirect:/admin";
    }


}
