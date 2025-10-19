package produtos.service.pedidos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import produtos.dto.pedidos.PedidosRequest;
import produtos.dto.pedidos.PedidosResponse;
import produtos.dto.pedidos.ProdutosPedidosRequest;
import produtos.entity.Pedidos;
import produtos.entity.Produtos;
import produtos.entity.ProdutosPedidos;
import produtos.entity.User;
import produtos.enums.TipoPagamento;
import produtos.mapper.PedidosMapper;
import produtos.repository.PedidosRepository;
import produtos.service.auth.AuthService;
import produtos.service.produtos.ProdutosService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static produtos.enums.StatusPedido.CANCELADO;
import static produtos.enums.StatusPedido.PAGO;
import static produtos.enums.StatusPedido.PENDENTE;

@Service
@RequiredArgsConstructor
public class PedidosServiceImpl implements PedidosService {

    private final PedidosRepository pedidosRepository;
    private final ProdutosService produtosService;
    private final AuthService authService;
    private final PedidosMapper pedidosMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PedidosResponse criar(final PedidosRequest request) {
        final Pedidos pedido = Pedidos.builder()
                .statusPedido(PENDENTE)
                .valorTotal(BigDecimal.ZERO)
                .build();

        setProdutosDoPedido(request, pedido);

        pedido.calcularValorTotal();
        Pedidos pedidoSalvo = pedidosRepository.save(pedido);
        return pedidosMapper.toResponse(pedidoSalvo);
    }

    private void setProdutosDoPedido(final PedidosRequest request, final Pedidos pedido) {
        for (final ProdutosPedidosRequest itemRequest : request.getProdutos()) {
            final Produtos produto = findProdutoById(itemRequest.getProdutoId());

            validaQuantidade(itemRequest, produto);

            final ProdutosPedidos item = ProdutosPedidos.builder()
                    .produto(produto)
                    .quantidade(itemRequest.getQuantidade())
                    .precoUnitario(produto.getPreco())
                    .precoTotal(produto.getPreco().multiply(BigDecimal.valueOf(itemRequest.getQuantidade())))
                    .build();

            pedido.adicionarItem(item);
        }
    }

    private Produtos findProdutoById(final UUID id) {
        return produtosService.buscarEntidadePorId(id);
    }

    private static void validaQuantidade(final ProdutosPedidosRequest itemRequest, final Produtos produto) {
        if (produto.getQuantidadeEstoque() < itemRequest.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PedidosResponse buscarPorId(final UUID id) {
        Pedidos pedido = pedidosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));
        return pedidosMapper.toResponse(pedido);
    }

    @Override
    @Transactional
    public PedidosResponse pagar(final UUID id, final TipoPagamento tipoPagamento) {
        Pedidos pedido = pedidosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));

        validaStatusPedido(pedido);

        this.processaProdutosPagamento(pedido);

        pedido.setStatusPedido(PAGO);
        pedido.setTipoPagamento(tipoPagamento);
        pedido = pedidosRepository.save(pedido);
        return pedidosMapper.toResponse(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidosResponse> listarPedidosDoUsuario() {
        final User usuarioAtual = authService.getUsuarioAtual();
        List<Pedidos> pedidos = pedidosRepository.findByCriadoPor(usuarioAtual);
        return pedidosMapper.toListResponse(pedidos);
    }


    private void processaProdutosPagamento(final Pedidos pedido) {
        for (final ProdutosPedidos item : pedido.getProdutosPedidos()) {
            final Produtos produto = item.getProduto();

            this.validaCancelamento(item, produto, pedido);

            this.atualizaEstoqueProduto(item);
        }
    }

    private static void validaStatusPedido(final Pedidos pedido) {
        if (!PENDENTE.equals(pedido.getStatusPedido())) {
            throw new RuntimeException("Não é possível prosseguir com o pagamento do pedido, o mesmo não se encontra Pendente");
        }
    }

    private void atualizaEstoqueProduto(final ProdutosPedidos item) {
        final Integer novoEstoque = item.getProduto().getQuantidadeEstoque() - item.getQuantidade();
        produtosService.atualizarEstoque(item.getProduto().getId(), novoEstoque);
    }

    private void validaCancelamento(final ProdutosPedidos item, final Produtos produto, final Pedidos pedido) {
        if (produto.getQuantidadeEstoque() < item.getQuantidade()) {
            pedido.setStatusPedido(CANCELADO);
            pedido.setMotivoCancelamento(
                    "Estoque insuficiente no momento do pagamento para: " + produto.getNome() +
                            " (Disponível: " + produto.getQuantidadeEstoque() +
                            ", Necessário: " + item.getQuantidade() + ")"
            );
            pedidosRepository.save(pedido);

             throw new RuntimeException("Pagamento cancelado. " + pedido.getMotivoCancelamento());
         }
     }



}

