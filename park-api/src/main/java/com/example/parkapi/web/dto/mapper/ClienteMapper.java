package com.example.parkapi.web.dto.mapper;

import org.modelmapper.ModelMapper;

import com.example.parkapi.entity.Cliente;
import com.example.parkapi.web.dto.ClienteCreateDto;
import com.example.parkapi.web.dto.ClienteResponseDto;

public class ClienteMapper {

	public static Cliente toCliente(ClienteCreateDto dto) {
		return new ModelMapper().map(dto, Cliente.class);
	}
	
	public static ClienteResponseDto toDto(Cliente cliente) {
		return new ModelMapper().map(cliente, ClienteResponseDto.class);
	}
	
}
