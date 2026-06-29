package github.com.pixayo.unimob.controller;

public class ConfiguracaoSistema {
    private static boolean acessibilidadeAtiva = false;

    public static boolean isAcessibilidadeAtiva() {
        return acessibilidadeAtiva;
    }

    public static void setAcessibilidadeAtiva(boolean ativa) {
        acessibilidadeAtiva = ativa;
    }
}