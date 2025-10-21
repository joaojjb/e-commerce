package produtos.service.produtos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import produtos.model.dto.produtos.ProdutosRequest;
import produtos.model.dto.produtos.ProdutosResponse;
import produtos.model.entity.Produtos;

import java.util.UUID;

public interface ProdutosService {
    ProdutosResponse criar(final ProdutosRequest request);

    ProdutosResponse atualizar(final UUID id, final ProdutosRequest request);

    Produtos buscarEntidadePorId(final UUID id);

    void atualizarEstoque(final UUID produtoId, final Integer novaQuantidade);

    void deletar(final UUID id);

    Page<ProdutosResponse> buscar(final ProdutosFiltroRequest filtros, final Pageable pageable);
}

