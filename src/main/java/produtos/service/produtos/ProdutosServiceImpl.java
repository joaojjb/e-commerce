package produtos.service.produtos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import produtos.model.dto.produtos.ProdutosRequest;
import produtos.model.dto.produtos.ProdutosResponse;
import produtos.model.entity.Produtos;
import produtos.config.exception.DomainException;
import produtos.mapper.ProdutosMapper;
import produtos.repository.ProdutosRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutosServiceImpl implements ProdutosService {

    private final ProdutosRepository produtosRepository;
    private final ProdutosMapper produtosMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProdutosResponse criar(final ProdutosRequest request) {
        Produtos produto = produtosMapper.fromRequest(request);
        produto = produtosRepository.save(produto);
        return produtosMapper.toResponse(produto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProdutosResponse atualizar(final UUID id, final ProdutosRequest request) {
        Produtos produtoExistente = produtosRepository.findById(id)
                .orElseThrow(() -> new DomainException("Produto não encontrado com ID: " + id));

        produtoExistente.setNome(request.getNome());
        produtoExistente.setDescricao(request.getDescricao());
        produtoExistente.setPreco(request.getPreco());
        produtoExistente.setCategoria(request.getCategoria());
        produtoExistente.setQuantidadeEstoque(request.getQuantidadeEstoque());

        produtoExistente = produtosRepository.save(produtoExistente);
        return produtosMapper.toResponse(produtoExistente);
    }

    @Override
    @Transactional(readOnly = true)
    public ProdutosResponse buscarPorId(final UUID id) {
        final Produtos produto = buscarEntidadePorId(id);
        return produtosMapper.toResponse(produto);
    }

    @Override
    @Transactional(readOnly = true)
    public Produtos buscarEntidadePorId(final UUID id) {
        return produtosRepository.findById(id)
                .orElseThrow(() -> new DomainException("Produto não encontrado com ID: " + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void atualizarEstoque(final UUID produtoId, final Integer novaQuantidade) {
        if (novaQuantidade < 0) {
            throw new DomainException("Estoque não pode ser negativo");
        }

        produtosRepository.atualizarEstoque(produtoId, novaQuantidade);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutosResponse> buscar(final String nome, final String categoria,
                                         final BigDecimal precoMin, final BigDecimal precoMax) {
        final var spec = ProdutoSpecification.builder()
                .nome(nome)
                .categoria(categoria)
                .precoMinimo(precoMin)
                .precoMaximo(precoMax)
                .build();

        final List<Produtos> produtos = produtosRepository.findAll(spec);
        return produtosMapper.toListResponse(produtos);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletar(final UUID id) {
        if (!produtosRepository.existsById(id)) {
            throw new DomainException("Produto não encontrado com ID: " + id);
        }
        produtosRepository.deleteById(id);
    }

}

