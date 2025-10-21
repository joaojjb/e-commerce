package produtos.model.dto.pedidos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import produtos.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidosResponse {
    private UUID id;
    private StatusPedido statusPedido;
    private String tipoPagamento;
    private BigDecimal valorTotal;
    private String motivoCancelamento;
    private List<ProdutosPedidosResponse> produtosPedidos;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private UserResponse criadoPor;
    private UserResponse atualizadoPor;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserResponse {
        private UUID id;
        private String username;
    }
}

