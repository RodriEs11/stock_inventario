package com.unla.grupo1.dtos;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotaPedidoDTO {

	private int id;
	private String producto;
	private int cantidad;
	private boolean activa;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime createdAt;
}
