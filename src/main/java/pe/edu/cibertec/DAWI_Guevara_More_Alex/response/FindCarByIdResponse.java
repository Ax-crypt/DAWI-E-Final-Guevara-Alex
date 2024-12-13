package pe.edu.cibertec.DAWI_Guevara_More_Alex.response;

import pe.edu.cibertec.DAWI_Guevara_More_Alex.dto.CarDetailDto;

public record FindCarByIdResponse(String code,
                                  String error,
                                  CarDetailDto carDetailDto) {
}
