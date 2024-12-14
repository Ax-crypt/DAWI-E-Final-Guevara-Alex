package pe.edu.cibertec.DAWI_Guevara_More_Alex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarDto;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarUpdateDto;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.response.*;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarApi {

    @Autowired
    CarService carService;

    // Método para obtener todos los coches o un coche por id
@GetMapping("/all")
public FindCarResponse findCars(@RequestParam(value = "id", defaultValue = "0") String id){
    try {
        if (Integer.parseInt(id) > 0) {
            Optional<CarDto> optional = carService.getAllCarsById(Integer.parseInt(id));
            if (optional.isPresent()) {
                CarDto carDto = optional.get();
                return new FindCarResponse("01", "", List.of(carDto));
            } else {
                return new FindCarResponse("02", "Car not found", null);
            }

        } else {
            List<CarDto> cars = carService.getAllCars();
            if (!cars.isEmpty())
                return new FindCarResponse("01", "", cars);
            else
                return new FindCarResponse("03", "Car list not found", cars);
        }
    }catch (Exception e){
        e.printStackTrace();
        return new FindCarResponse("99","Service not found",null);
    }
}

    // Método para obtener los detalles de un coche por id
    @GetMapping("/detail")
    public FindCarByIdResponse findCarById(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            if (Integer.parseInt(id) > 0) {
                Optional<CarDetailDto> optional = carService.getCarById(Integer.parseInt(id));                if (optional.isPresent()) {
                    CarDetailDto carDetailDto = optional.get();
                    return new FindCarByIdResponse("01", "", carDetailDto);
                } else {
                    return new FindCarByIdResponse("02", "Car not found", null);
                }

            } else
                return new FindCarByIdResponse("03", "Parameter not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarByIdResponse("99", "Service not found", null);

        }

    }

    // Método para actualizar un coche
    @PostMapping("/update")
    public UpdateCarResponse updateCar(@RequestBody CarUpdateDto carUpdateDto) {

        try {
            if (carService.updateCar(carUpdateDto)) {
                return new UpdateCarResponse("01", "Successful update");
            } else {
                return new UpdateCarResponse("02", "Car not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarResponse("99", "Service not found");

        }

    }

    // Método para agregar un coche
    @PostMapping("/add")
    public AddCarResponse addCar(@RequestBody CarDetailDto carDetailDto) {
        try {
            if (carService.addCar(carDetailDto)) {
                return new AddCarResponse("01", "Successful registration", List.of(carDetailDto));  // Retorno el nuevo registro
            } else {
                return new AddCarResponse("02", "Car already exists", null);  // El coche ya existe
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AddCarResponse("99", "Service not found", null);
        }
    }

    // Método para eliminar un coche
    @DeleteMapping("/delete")
    public DeleteCarResponse deleteCar(@RequestParam(value = "id", required = true) int id) {
        try {
            if (carService.deleteCarById(id)) {
                return new DeleteCarResponse("01", "Car deleted successfully");
            } else {
                return new DeleteCarResponse("02", "Car not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarResponse("99", "Service not found");
        }
    }


}
