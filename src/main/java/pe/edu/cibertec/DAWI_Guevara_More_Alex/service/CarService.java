package pe.edu.cibertec.DAWI_Guevara_More_Alex.service;

import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarDto;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarUpdateDto;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<CarDto> getAllCars() throws Exception;

    Optional<CarDto> getAllCarsById(int id) throws Exception;

    Optional<CarDetailDto> getCarById(int id) throws Exception;

    boolean updateCar(CarUpdateDto carUpdateDto) throws Exception;

    boolean deleteCarById(int id) throws Exception;

    boolean addCar(CarDetailDto carDetailDto) throws Exception;

}
