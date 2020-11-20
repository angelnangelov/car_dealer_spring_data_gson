package softuni.cardealer.services;

import softuni.cardealer.domain.enitities.Car;

import java.io.IOException;
import java.util.List;

public interface CarService {
    void seedCars() throws Exception;
    String findAllMadeByToyota();
}
