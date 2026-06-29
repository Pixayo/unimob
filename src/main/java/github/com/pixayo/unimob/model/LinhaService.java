package github.com.pixayo.unimob.model;

import java.util.ArrayList;
import java.util.List;

public class LinhaService {

    public static class LinhaOnibus {
        private String numero;
        private String nome;
        private String horarios;
        private boolean favoritado;

        public LinhaOnibus(String numero, String nome, String horarios) {
            this.numero = numero;
            this.nome = nome;
            this.horarios = horarios;
            this.favoritado = false;
        }

        public String getNumero() { return numero; }
        public String getNome() { return nome; }
        public String getHorarios() { return horarios; }
        public boolean isFavoritado() { return favoritado; }
        public void setFavoritado(boolean favoritado) { this.favoritado = favoritado; }
    }

    private static final List<LinhaOnibus> linhas = new ArrayList<>();

    static {
        linhas.add(new LinhaOnibus("0.006", "Cruzeiro / Sudoeste / W3 Sul / Octogonal", "06:00, 06:40, 07:20, 12:00, 13:15, 17:45, 18:30"));
        linhas.add(new LinhaOnibus("0.082", "Núcleo Bandeirante (Metropolitana)", "05:30, 06:15, 07:00, 07:45, 11:30, 12:15, 17:10, 18:00"));
        linhas.add(new LinhaOnibus("3213", "BRT Gama / DF-483 / Santa Maria Sul (Q.d 100/200) / Dvo", "05:00, 05:20, 05:40, 06:00, 06:20, 07:00, 16:40, 17:20"));
    }

    public static List<LinhaOnibus> getTodasAsLinhas() {
        return linhas;
    }

    public static List<LinhaOnibus> getLinhasFavoritas() {
        List<LinhaOnibus> favoritas = new ArrayList<>();
        for (LinhaOnibus l : linhas) {
            if (l.isFavoritado()) favoritas.add(l);
        }
        return favoritas;
    }
}