package com.nitsenkov.http.controller;

import com.nitsenkov.dto.UserCreateEditDto;
import com.nitsenkov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") UUID uuid, Model model) {
        return userService.findById(uuid)
                .map(user -> {
                    model.addAttribute("users", user);
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute UserCreateEditDto user) {
        return "redirect:/users/" + userService.create(user);
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") UUID uuid, @ModelAttribute UserCreateEditDto user) {
        return userService.update(uuid, user)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") UUID uuid) {
        if (!userService.delete(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return "redirect:/users";
        }
    }

}
