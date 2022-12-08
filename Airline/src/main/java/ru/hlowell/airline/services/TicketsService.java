package ru.hlowell.airline.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hlowell.airline.dto.TicketDto;
import ru.hlowell.airline.persistence.entity.FlightDate;
import ru.hlowell.airline.persistence.entity.Ticket;
import ru.hlowell.airline.persistence.entity.User;
import ru.hlowell.airline.persistence.repository.TicketsRepo;
import ru.hlowell.airline.security.CustomAuthProvider;

@Service
@RequiredArgsConstructor
public class TicketsService {

    private final TicketsRepo ticketsRepo;
    private final FlightDatesService flightDatesService;

    @Transactional
    public void buyTicket(TicketDto dto) {
        FlightDate flightDate = flightDatesService.reserveSeats(dto);
        String username = CustomAuthProvider.getAuthenticatedUsername();
        ticketsRepo.save(new Ticket(dto.getSeats(),flightDate, new User(username)));
    }
}
