package com.example.parkapi.web.dto.mapper;

import org.modelmapper.ModelMapper;

import com.example.parkapi.entity.ClienteVaga;
import com.example.parkapi.web.dto.EstacionamentoCreateDto;
import com.example.parkapi.web.dto.EstacionamentoResponseDto;

public class ClienteVagaMapper {

	public static ClienteVaga toClienteVaga(EstacionamentoCreateDto dto) {
		return new ModelMapper().map(dto, ClienteVaga.class);
	}
	
	public static EstacionamentoResponseDto toDto(ClienteVaga clienteVaga) {
		return new ModelMapper().map(clienteVaga, EstacionamentoResponseDto.class);
	}
	
}
