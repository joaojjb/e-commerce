package produtos.service.produtos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import produtos.dto.produtos.ProdutosRequest;
import produtos.dto.produtos.ProdutosResponse;
import produtos.entity.Produtos;
import produtos.mapper.ProdutosMapper;
import produtos.repository.ProdutosRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutosServiceImpl implements ProdutosService {

    private final ProdutosRepository produtosRepository;
    private final ProdutosMapper produtosMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProdutosResponse criar(final ProdutosRequest request) {
        Produtos produto = produtosMapper.toEntity(request);
        produto = produtosRepository.save(produto);
        return produtosMapper.toResponse(produto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProdutosResponse atualizar(final UUID id, final ProdutosRequest request) {
        Produtos produto = produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        produto.setCategoria(request.getCategoria());
        produto.setQuantidadeEstoque(request.getQuantidadeEstoque());

        produto = produtosRepository.save(produto);
        return produtosMapper.toResponse(produto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProdutosResponse buscarPorId(final UUID id) {
        return produtosMapper.toResponse(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Produtos findById(final UUID id) {
        return produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void atualizarEstoque(final UUID produtoId, final Integer novaQuantidade) {
        if (novaQuantidade < 0) {
            throw new RuntimeException("Estoque não pode ser negativo");
        }

        produtosRepository.atualizarEstoque(produtoId, novaQuantidade);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutosResponse> buscar(final String nome, final String categoria,
                                         final BigDecimal precoMin, final BigDecimal precoMax) {
        var spec = ProdutoSpecification.builder()
                .nome(nome)
                .categoria(categoria)
                .precoMinimo(precoMin)
                .precoMaximo(precoMax)
                .build();

        return produtosRepository.findAll(spec)
                .stream()
                .map(produtosMapper::toResponse)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletar(final UUID id) {
        if (!produtosRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        produtosRepository.deleteById(id);
    }

}

