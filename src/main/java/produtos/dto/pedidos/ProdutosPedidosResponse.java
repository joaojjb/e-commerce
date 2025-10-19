package produtos.dto.pedidos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosPedidosResponse {
    private UUID id;
    private UUID produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private UUID criadoPorId;
    private String criadoPorUsername;
    private UUID atualizadoPorId;
    private String atualizadoPorUsername;
}

