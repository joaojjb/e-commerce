package produtos.service.produtos;

import produtos.model.dto.produtos.ProdutosRequest;
import produtos.model.dto.produtos.ProdutosResponse;
import produtos.model.entity.Produtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProdutosService {
    ProdutosResponse criar(final ProdutosRequest request);

    ProdutosResponse atualizar(final UUID id, final ProdutosRequest request);

    ProdutosResponse buscarPorId(final UUID id);
    
    Produtos buscarEntidadePorId(final UUID id);

    void atualizarEstoque(final UUID produtoId, final Integer novaQuantidade);

    void deletar(final UUID id);

    List<ProdutosResponse> buscar(final String nome, final String categoria,
                                   final BigDecimal precoMin, final BigDecimal precoMax);
}

