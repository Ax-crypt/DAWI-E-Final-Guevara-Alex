package pe.edu.cibertec.DAWI_Guevara_More_Alex.response;

import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarDto;

public record FindCarResponse(String code,
                              String error,
                              Iterable<CarDto> cars) {
}
