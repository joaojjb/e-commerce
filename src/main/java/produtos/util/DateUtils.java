package produtos.util;

public final class DateUtils {

    private DateUtils() {
    }

    public static String getNomeMes(final Integer mes) {
        if (mes == null || mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mês deve estar entre 1 e 12");
        }

        final String[] meses = {
                "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        };

        return meses[mes - 1];
    }

}
