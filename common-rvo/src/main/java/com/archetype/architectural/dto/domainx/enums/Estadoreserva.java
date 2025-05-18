package com.archetype.architectural.dto.domainx.enums;

import lombok.Getter;

@Getter
public enum Estadoreserva {
	P("Pendiente"),D("Denegada"),A("Autorizada");
	
	private final String name;
	
	Estadoreserva(final String name) {
		this.name=name;
	}
}
