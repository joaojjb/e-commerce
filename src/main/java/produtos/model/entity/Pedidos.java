package produtos.model.entity;

import jakarta.persistence.*;
import lombok.*;
import produtos.enums.StatusPedido;
import produtos.enums.TipoPagamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedidos extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido", nullable = false)
    private StatusPedido statusPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento")
    private TipoPagamento tipoPagamento;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "motivo_cancelamento", columnDefinition = "TEXT")
    private String motivoCancelamento;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProdutosPedidos> produtosPedidos = new ArrayList<>();

    public void adicionarItem(final ProdutosPedidos item) {
        produtosPedidos.add(item);
        item.setPedido(this);
    }

    public void calcularValorTotal() {
        this.valorTotal = produtosPedidos.stream()
                .map(ProdutosPedidos::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

