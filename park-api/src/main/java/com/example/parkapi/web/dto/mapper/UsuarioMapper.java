package com.example.parkapi.web.dto.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.example.parkapi.entity.Usuario;
import com.example.parkapi.web.dto.UsuarioCreateDto;
import com.example.parkapi.web.dto.UsuarioResponseDto;

public class UsuarioMapper {

	public static Usuario toUsuario(UsuarioCreateDto createDto) {
		return new ModelMapper().map(createDto, Usuario.class);
	}
	
	public static UsuarioResponseDto toDto(Usuario usuario) {
		String role = usuario.getRole().name().substring("ROLE_".length());
		PropertyMap<Usuario, UsuarioResponseDto> porps = new PropertyMap<Usuario, UsuarioResponseDto>() {

			@Override
			protected void configure() {
				map().setRole(role);
			}
		};
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(porps);
		return mapper.map(usuario, UsuarioResponseDto.class);
	}
}
