package pe.edu.cibertec.DAWI_Guevara_More_Alex.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarDto;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarUpdateDto;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.entity.Car;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.repository.CarRepository;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.service.CarService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {
        List<CarDto> cars = new ArrayList<CarDto>();
       Iterable<Car> iterable = carRepository.findAll();
       iterable.forEach(car -> {
           CarDto carDto = new CarDto(
                   car.getCarId(),
                   car.getMake(),
                   car.getModel(),
                   car.getYear(),
                   car.getColor());
           cars.add(carDto);

       });
        return cars;
    }

    @Override
    public Optional<CarDto> getAllCarsById(int id) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(
                car -> new CarDetailDto(
                        car.getCarId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getVin(),
                        car.getLicensePlate(),
                        car.getOwnerName(),
                        car.getOwnerContact(),
                        car.getPurchaseDate(),
                        car.getMileage(),
                        car.getEngineType(),
                        car.getColor(),
                        car.getInsuranceCompany(),
                        car.getInsurancePolicyNumber(),
                        car.getRegistrationExpirationDate(),
                        car.getServiceDueDate()));
    }

    @Override
    public boolean updateCar(CarUpdateDto carUpdateDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carUpdateDto.carId());
        return optional.map(car ->{
            car.setMake(carUpdateDto.make());
            car.setModel(carUpdateDto.model());
            car.setYear(carUpdateDto.year());
            car.setOwnerName(carUpdateDto.ownerName());
            car.setOwnerContact(carUpdateDto.ownerContact());
            car.setMileage(carUpdateDto.mileage());
            car.setEngineType(carUpdateDto.engineType());
            car.setColor(carUpdateDto.color());
            carRepository.save(car);
            return true;
                }).orElse(false);
    }

    @Override
    public boolean deleteCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
       return optional.map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean addCar(CarDetailDto carDetailDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDetailDto.carId());
        if (optional.isPresent()) {
            return false;
        } else {
            Car car = new Car();
            car.setMake(carDetailDto.make());
            car.setModel(carDetailDto.model());
            car.setYear(carDetailDto.year());
            car.setVin(carDetailDto.vin());
            car.setLicensePlate(carDetailDto.licensePlate());
            car.setOwnerName(carDetailDto.ownerName());
            car.setOwnerContact(carDetailDto.ownerContact());
            car.setPurchaseDate(new Date());
            car.setMileage(carDetailDto.mileage());
            car.setEngineType(carDetailDto.engineType());
            car.setColor(carDetailDto.color());
            car.setInsuranceCompany(carDetailDto.insuranceCompany());
            car.setInsurancePolicyNumber(carDetailDto.insurancePolicyNumber());
            car.setRegistrationExpirationDate(new Date());
            car.setServiceDueDate(new Date());
            carRepository.save(car);
            return true;
        }
    }
}
