package produtos.model.dto.relatorios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopUsuariosResponse {
    private UUID usuarioId;
    private String username;
    private BigDecimal valorTotalCompras;
    private Long totalPedidos;
    private Integer posicao;
}
