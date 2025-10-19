package produtos.service.relatorios;

import produtos.dto.relatorios.RelatorioResponse;

public interface RelatorioService {
    RelatorioResponse getTop5UsuariosQueMaisCompraram();
    RelatorioResponse getTicketMedioPorUsuario();
    RelatorioResponse getFaturamentoMensal(Integer ano, Integer mes);
}
