package produtos.service.produtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutosFiltroRequest {
    private String nome;
    private String categoria;
    private BigDecimal precoMin;
    private BigDecimal precoMax;
    private Integer quantidadeMinimaEstoque;
}
