package ru.hlowell.airline.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.hlowell.airline.dto.UserDto;
import ru.hlowell.airline.services.UsersService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsersService usersService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public ModelAndView registration(ModelAndView model) {
        model.setViewName("auth/registration");
        model.getModel().put("userForm", new UserDto());
        return model;
    }

    @PostMapping("/registration")
    public ModelAndView addUser(@ModelAttribute("userForm") @Validated UserDto userForm,
                                BindingResult bindingResult, ModelAndView model) {
        model.setViewName("auth/registration");

        if (bindingResult.hasErrors()) {
            return model;
        }

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.getModel().put("passwordError", "Пароли не совпадают");
            return model;
        }

        if (usersService.save(userForm).isEmpty()) {
            model.getModel().put("usernameError", "Пользователь с таким именем уже существует");
            return model;
        }
        model.clear();
        model.setView(new RedirectView("auth/login"));
        return model;
    }

}
