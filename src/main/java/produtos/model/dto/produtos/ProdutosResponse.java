package produtos.model.dto.produtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private UUID criadoPorId;
    private String criadoPorUsername;
    private UUID atualizadoPorId;
    private String atualizadoPorUsername;
}

