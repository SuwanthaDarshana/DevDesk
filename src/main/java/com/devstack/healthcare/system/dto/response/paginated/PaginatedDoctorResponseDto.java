package com.devstack.healthcare.system.dto.response.paginated;

import com.devstack.healthcare.system.dto.response.ResponseDoctorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedDoctorResponseDto {
    private long count;                                 /////Data count
    private List<ResponseDoctorDto> dataList;           ////Get Data list from Response doctorDTO (Doctor Service)
}
