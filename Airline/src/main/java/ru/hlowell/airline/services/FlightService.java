package ru.hlowell.airline.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.hlowell.airline.dto.FlightRequestDto;
import ru.hlowell.airline.persistence.entity.Flight;
import ru.hlowell.airline.persistence.repository.FlightsRepo;
import ru.hlowell.airline.dto.FlightDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightsRepo flightsRepo;
    private final ModelMapper mapper;

    public List<FlightDto> getAllFlights() {
        return flightsRepo.findAll().stream()
                .map(flight -> mapper.map(flight, FlightDto.class))
                .collect(Collectors.toList());
    }

    public List<Flight> saveAll(List<FlightDto> dtos) {
        List<Flight> flights = dtos.stream()
                .map(dto -> mapper.map(dto, Flight.class))
                .collect(Collectors.toList());
        flightsRepo.saveAll(flights);
        return flights;
    }

    public List<FlightDto> getSomeFlights(FlightRequestDto dto) {
        LocalDate date = LocalDate.parse(dto.getDate());
        String dCity = dto.getDepartureCity();
        String aCity = dto.getArrivalCity();
        Integer seats = dto.getNeededSeats();
        return flightsRepo.findSomeFlights(date, dCity, aCity, seats)
                .stream()
                .map(flight -> mapper.map(flight, FlightDto.class))
                .collect(Collectors.toList());
    }

}
