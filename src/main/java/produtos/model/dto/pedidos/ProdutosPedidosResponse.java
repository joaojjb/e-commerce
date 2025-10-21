package produtos.model.dto.pedidos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosPedidosResponse {
    private UUID id;
    private ProdutoResponse produto;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private Integer quantidade;
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

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProdutoResponse {
        private UUID id;
        private String nome;
        private String descricao;
    }
}

