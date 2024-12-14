package pe.edu.cibertec.DAWI_Guevara_More_Alex.dto;

public record CarUpdateDto(Integer carId,
                           String make,
                           String model,
                           Integer year,
                           String ownerName,
                           String ownerContact,
                           Integer mileage,
                           String engineType,
                           String color) {
}
