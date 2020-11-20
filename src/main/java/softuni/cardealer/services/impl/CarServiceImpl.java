package softuni.cardealer.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.cardealer.domain.dtos.exportDtos.CarExportDto;
import softuni.cardealer.domain.dtos.importDtos.CarSeedDto;
import softuni.cardealer.domain.enitities.Car;
import softuni.cardealer.domain.enitities.Part;
import softuni.cardealer.domain.repositories.CarRepository;
import softuni.cardealer.domain.repositories.PartRepository;
import softuni.cardealer.services.CarService;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class CarServiceImpl  implements CarService {
    private final static String CARS_PATH="src/main/resources/jsons/cars.json";

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private Gson gson;
    private final PartRepository partRepository;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, Gson gson, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.partRepository = partRepository;
    }

    @Override
    @Transactional
    public void seedCars() throws Exception {
        String content =
                String.join(" ", Files.readAllLines(Path.of(CARS_PATH)));

        CarSeedDto[] carSeedDtos = this.gson.fromJson(content,CarSeedDto[].class);

        for (CarSeedDto carSeedDto : carSeedDtos) {
            Car car = this.modelMapper.map(carSeedDto, Car.class);
            car.setParts(getRandomParts());
            this.carRepository.saveAndFlush(car);
        }

    }

    @Override
    public String findAllMadeByToyota() {
        List<Car> toyota = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        List<CarExportDto>  carExportDtos= new ArrayList<>();
        for (Car car : toyota) {
            CarExportDto exportDto = this.modelMapper.map(car,CarExportDto.class);
            carExportDtos.add(exportDto);

        }
        return this.gson.toJson(carExportDtos);

    }

    private Set<Part> getRandomParts() throws Exception {
        Set<Part> parts = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            Part part = this.getRandomPart();
            parts.add(part);
        }
        return parts;
    }
    private Part getRandomPart() throws Exception {
        Random random = new Random();
        long index = random.nextInt((int) this.partRepository.count())+1;
        Optional<Part> part = this.partRepository.findById(index);
        if(part.isPresent()){
            return part.get();
        }else{
            throw new Exception("Invalid part ID");
        }
    }
}
