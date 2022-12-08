package ru.hlowell.airline.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.hlowell.airline.dto.FlightRequestDto;
import ru.hlowell.airline.dto.TicketDto;
import ru.hlowell.airline.services.FlightService;
import ru.hlowell.airline.services.TicketsService;
import ru.hlowell.airline.security.CustomAuthProvider;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final FlightService flightService;
    private final TicketsService ticketsService;

    @GetMapping(value = "/")
    public ModelAndView userPage(ModelAndView modelAndView) {
        modelAndView.setViewName("user");
        modelAndView.addObject("flightSelection", new FlightRequestDto());
        modelAndView.getModel().put("username", CustomAuthProvider.getAuthenticatedUsername());
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView filterFlights(@ModelAttribute("flightSelection") FlightRequestDto dto, ModelAndView modelAndView) {
        modelAndView.getModel().put("username", CustomAuthProvider.getAuthenticatedUsername());
        modelAndView.getModel().put("flights", flightService.getSomeFlights(dto));
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @PostMapping("/buy")
    public ModelAndView buyTicket(@ModelAttribute("flightDate") String flightDate,
                                   @ModelAttribute("neededSeats") Integer neededSeats,
                                  @ModelAttribute("flightNumber") String flightNumber,
                                  ModelAndView modelAndView) {
        TicketDto ticket = new TicketDto(flightNumber,flightDate, neededSeats);
        ticketsService.buyTicket(ticket);
        modelAndView.getModel().put("ticket", ticket);
        modelAndView.setViewName("success");
        return modelAndView;
    }

}
