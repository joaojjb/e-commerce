package produtos.dto.relatorios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FaturamentoMensalResponse {
    private Integer ano;
    private Integer mes;
    private String mesNome;
    private BigDecimal valorTotal;
    private Long totalPedidos;
    private Long totalUsuarios;
}
