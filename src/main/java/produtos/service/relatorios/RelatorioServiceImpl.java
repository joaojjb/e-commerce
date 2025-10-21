package produtos.service.relatorios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import produtos.model.dto.relatorios.FaturamentoMensalResponse;
import produtos.model.dto.relatorios.RelatorioResponse;
import produtos.model.dto.relatorios.TicketMedioResponse;
import produtos.model.dto.relatorios.TopUsuariosResponse;
import produtos.mapper.RelatorioMapper;
import produtos.repository.RelatorioRepository;
import produtos.util.DateUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioServiceImpl implements RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final RelatorioMapper relatorioMapper;
    
    @Override
    @Transactional(readOnly = true)
    public RelatorioResponse getTop5UsuariosQueMaisCompraram() {
        final List<Object[]> resultados = relatorioRepository.findTop5UsuariosQueMaisCompraram();
        
        final List<TopUsuariosResponse> dados = resultados.stream()
            .map(relatorioMapper::mapToTopUsuariosResponse)
            .collect(Collectors.toList());
        
        return RelatorioResponse.builder()
            .titulo("Top 5 Usuários que Mais Compraram")
            .dados(dados)
            .geradoEm(LocalDateTime.now())
            .build();
    }
    
    @Override
    @Transactional(readOnly = true)
    public RelatorioResponse getTicketMedioPorUsuario() {
        final List<Object[]> resultados = relatorioRepository.findTicketMedioPorUsuario();
        
        final List<TicketMedioResponse> dados = resultados.stream()
            .map(relatorioMapper::mapToTicketMedioResponse)
            .collect(Collectors.toList());
        
        return RelatorioResponse.builder()
            .titulo("Ticket Médio por Usuário")
            .dados(dados)
            .geradoEm(LocalDateTime.now())
            .build();
    }
    
    @Override
    @Transactional(readOnly = true)
    public RelatorioResponse getFaturamentoMensal(final Integer ano, final Integer mes) {
        final List<Object[]> resultados = relatorioRepository.findFaturamentoMensal(ano, mes);
        
        final List<FaturamentoMensalResponse> dados = resultados.stream()
            .map(row -> relatorioMapper.mapToFaturamentoMensalResponse(row, mes))
            .collect(Collectors.toList());
        
        return RelatorioResponse.builder()
            .titulo(String.format("Faturamento Mensal - %s/%d", DateUtils.getNomeMes(mes), ano))
            .dados(dados)
            .geradoEm(LocalDateTime.now())
            .build();
    }
    

}
