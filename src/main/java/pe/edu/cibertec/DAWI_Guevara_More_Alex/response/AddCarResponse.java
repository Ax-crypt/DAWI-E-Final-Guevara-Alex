package pe.edu.cibertec.DAWI_Guevara_More_Alex.response;

import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarDetailDto;

public record AddCarResponse(String code,
                             String error,
                             Iterable<CarDetailDto> carDetails) {
}
