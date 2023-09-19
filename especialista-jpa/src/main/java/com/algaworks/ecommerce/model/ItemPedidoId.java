package com.algaworks.ecommerce.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoId implements Serializable {

	private static final long serialVersionUID = 4026679135829660184L;

	@EqualsAndHashCode.Include
	private Integer pedidoId;
	
	@EqualsAndHashCode.Include
	private Integer produtoId;
	
}
