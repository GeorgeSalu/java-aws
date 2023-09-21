package com.algaworks.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@EqualsAndHashCode
public abstract class Pagamento  {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
	
}
