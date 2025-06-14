package luisf.dev.ProjetoBancoDeSangue.Doador;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum TipoSanguineo {
    A_POSITIVO("A+"),
    A_NEGATIVO("A-"),
    B_POSITIVO("B+"),
    B_NEGATIVO("B-"),
    AB_POSITIVO("AB+"),
    AB_NEGATIVO("AB-"),
    O_POSITIVO("O+"),
    O_NEGATIVO("O-");

    private final String descricao;

    TipoSanguineo(String descricao){
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao(){
        return descricao;
    }

    @JsonCreator
    public static TipoSanguineo fromDescricao(String descricao){
        if (descricao == null){
            return null;
        }
        return Arrays.stream(TipoSanguineo.values())
                .filter(ts -> ts.descricao.equalsIgnoreCase(descricao))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo sanguíneo inválido: " + descricao));
    }
}
