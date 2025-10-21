package produtos.service.produtos;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import produtos.config.exception.DomainException;
import produtos.model.dto.produtos.ProdutosRequest;
import produtos.model.dto.produtos.ProdutosResponse;
import produtos.model.entity.Produtos;
import produtos.model.mapper.ProdutosMapper;
import produtos.repository.ProdutosRepository;
import produtos.service.pedidos.PedidosService;

import java.util.UUID;

@Service
public class ProdutosServiceImpl implements ProdutosService {

    private final ProdutosRepository produtosRepository;
    private final ProdutosMapper produtosMapper;
    private final PedidosService pedidosService;

    protected ProdutosServiceImpl(final ProdutosRepository produtosRepository,
                                  final ProdutosMapper produtosMapper,
                                  @Lazy final PedidosService pedidosService) {
        this.produtosRepository = produtosRepository;
        this.produtosMapper = produtosMapper;
        this.pedidosService = pedidosService;
    }

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
    public Page<ProdutosResponse> buscar(final ProdutosFiltroRequest filtros, final Pageable pageable) {
        final var spec = ProdutoSpecification.builder()
                .nome(filtros.getNome())
                .categoria(filtros.getCategoria())
                .precoMinimo(filtros.getPrecoMin())
                .precoMaximo(filtros.getPrecoMax())
                .quantidadeEstoqueMinima(filtros.getQuantidadeMinimaEstoque())
                .build();

        return produtosRepository.findAll(spec, pageable)
                .map(produtosMapper::toResponse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletar(final UUID id) {
        final Produtos produto = this.buscarEntidadePorId(id);

        this.validaProdutoPedido(produto);

        produtosRepository.delete(produto);
    }

    private void validaProdutoPedido(final Produtos produto) {
        if (pedidosService.existsByProdutosPedidos_Produto_Id(produto.getId())) {
            throw new DomainException("Este produto não pode ser excluído pois está vinculado a um pedido");
        }
    }

}

