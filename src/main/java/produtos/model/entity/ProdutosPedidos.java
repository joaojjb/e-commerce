package produtos.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "produtos_pedidos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosPedidos extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false, referencedColumnName = "id")
    private Pedidos pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false, referencedColumnName = "id")
    private Produtos produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    @Column(name = "preco_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoTotal;

    @PrePersist
    @PreUpdate
    private void calcularPrecoTotal() {
        if (quantidade != null && precoUnitario != null) {
            this.precoTotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
        }
    }
}

