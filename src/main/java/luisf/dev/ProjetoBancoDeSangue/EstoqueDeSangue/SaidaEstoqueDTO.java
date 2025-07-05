package luisf.dev.ProjetoBancoDeSangue.EstoqueDeSangue;

import luisf.dev.ProjetoBancoDeSangue.Doador.TipoSanguineo;

public class SaidaEstoqueDTO {

    private TipoSanguineo tipoSanguineo;
    private int quantidade;

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
