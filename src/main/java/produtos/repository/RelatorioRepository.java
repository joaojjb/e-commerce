package produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import produtos.dto.relatorios.FaturamentoMensalResponse;
import produtos.dto.relatorios.TicketMedioResponse;
import produtos.entity.Pedidos;

import java.util.List;
import java.util.UUID;

@Repository
public interface RelatorioRepository extends JpaRepository<Pedidos, UUID> {

    @Query(value = """
        SELECT 
            BIN_TO_UUID(u.id) as usuarioId,
            u.username,
            COALESCE(SUM(p.valor_total), 0) as valorTotalCompras,
            COUNT(p.id) as totalPedidos,
            ROW_NUMBER() OVER (ORDER BY SUM(p.valor_total) DESC) as posicao
        FROM users u
        LEFT JOIN pedidos p ON u.id = p.criado_por 
            AND p.status_pedido = 'PAGO'
        GROUP BY u.id, u.username
        ORDER BY valorTotalCompras DESC
        LIMIT 5
        """, nativeQuery = true)
    List<Object[]> findTop5UsuariosQueMaisCompraram();

    @Query(value = """
        SELECT 
            BIN_TO_UUID(u.id) as usuarioId,
            u.username,
            COALESCE(AVG(p.valor_total), 0) as ticketMedio,
            COUNT(p.id) as totalPedidos,
            COALESCE(SUM(p.valor_total), 0) as valorTotalCompras
        FROM users u
        LEFT JOIN pedidos p ON u.id = p.criado_por 
            AND p.status_pedido = 'PAGO'
        GROUP BY u.id, u.username
        HAVING COUNT(p.id) > 0
        ORDER BY AVG(p.valor_total) DESC
        """, nativeQuery = true)
    List<Object[]> findTicketMedioPorUsuario();

    @Query(value = """
        SELECT 
            :ano as ano,
            :mes as mes,
            COALESCE(SUM(p.valor_total), 0) as valorTotal,
            COUNT(p.id) as totalPedidos,
            COUNT(DISTINCT p.criado_por) as totalUsuarios
        FROM pedidos p
        WHERE p.status_pedido = 'PAGO'
            AND YEAR(p.data_cadastro) = :ano
            AND MONTH(p.data_cadastro) = :mes
        """, nativeQuery = true)
    List<Object[]> findFaturamentoMensal(@Param("ano") final Integer ano,@Param("mes") final Integer mes);
}
