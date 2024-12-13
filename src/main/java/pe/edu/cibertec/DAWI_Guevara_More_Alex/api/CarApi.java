package pe.edu.cibertec.DAWI_Guevara_More_Alex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarDto;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.response.FindCarByIdResponse;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.response.FindCarResponse;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.response.UpdateCarResponse;
import pe.edu.cibertec.DAWI_Guevara_More_Alex.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarApi {

    @Autowired
    CarService carService;

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
        return new FindCarResponse("99","Servicio no encontrado",null);
    }
}

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

    @PostMapping("/update")
    public UpdateCarResponse updateCar(@RequestBody CarDto carDto) {

        try {
            if (carService.updateCar(carDto)) {
                return new UpdateCarResponse("01", "");
            } else {
                return new UpdateCarResponse("02", "User not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarResponse("99", "Service not found");

        }

    }










}
