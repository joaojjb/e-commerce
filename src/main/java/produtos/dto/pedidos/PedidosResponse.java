package produtos.dto.pedidos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import produtos.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidosResponse {
    private UUID id;
    private StatusPedido statusPedido;
    private String tipoPagamento;
    private BigDecimal valorTotal;
    private String motivoCancelamento;
    private List<ProdutosPedidosResponse> produtos;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private UUID criadoPorId;
    private String criadoPorUsername;
    private UUID atualizadoPorId;
    private String atualizadoPorUsername;
}

