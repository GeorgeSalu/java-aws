package com.example.parkapi.web.dto.mapper;

import org.modelmapper.ModelMapper;

import com.example.parkapi.entity.Vaga;
import com.example.parkapi.web.dto.VagaCreateDto;
import com.example.parkapi.web.dto.VagaResponseDto;

public class VagaMapper {

	public static Vaga toVaga(VagaCreateDto dto) {
		return new ModelMapper().map(dto, Vaga.class);
	}
	
	public static VagaResponseDto toDto(Vaga vaga) {
		return new ModelMapper().map(vaga, VagaResponseDto.class);
	}


}
