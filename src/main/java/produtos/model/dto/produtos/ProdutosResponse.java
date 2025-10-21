package produtos.model.dto.produtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import produtos.enums.Categoria;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosResponse {
    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Categoria categoria;
    private Integer quantidadeEstoque;
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

