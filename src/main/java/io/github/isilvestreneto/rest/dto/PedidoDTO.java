package io.github.isilvestreneto.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {
	
	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> items;
	
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<ItemPedidoDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemPedidoDTO> items) {
		this.items = items;
	}
}
