package produtos.mapper;

import org.mapstruct.Mapper;
import produtos.dto.relatorios.FaturamentoMensalResponse;
import produtos.dto.relatorios.TicketMedioResponse;
import produtos.dto.relatorios.TopUsuariosResponse;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RelatorioMapper {

    default TopUsuariosResponse mapToTopUsuariosResponse(Object[] row) {
        return TopUsuariosResponse.builder()
            .usuarioId(UUID.fromString((String) row[0]))
            .username((String) row[1])
            .valorTotalCompras((java.math.BigDecimal) row[2])
            .totalPedidos(((Number) row[3]).longValue())
            .posicao(((Number) row[4]).intValue())
            .build();
    }

    default TicketMedioResponse mapToTicketMedioResponse(Object[] row) {
        return TicketMedioResponse.builder()
            .usuarioId(UUID.fromString((String) row[0]))
            .username((String) row[1])
            .ticketMedio((java.math.BigDecimal) row[2])
            .totalPedidos(((Number) row[3]).longValue())
            .valorTotalCompras((java.math.BigDecimal) row[4])
            .build();
    }

    default FaturamentoMensalResponse mapToFaturamentoMensalResponse(Object[] row, Integer mes) {
        return FaturamentoMensalResponse.builder()
            .ano(((Number) row[0]).intValue())
            .mes(((Number) row[1]).intValue())
            .mesNome(produtos.util.DateUtils.getNomeMes(mes))
            .valorTotal((java.math.BigDecimal) row[2])
            .totalPedidos(((Number) row[3]).longValue())
            .totalUsuarios(((Number) row[4]).longValue())
            .build();
    }
}
