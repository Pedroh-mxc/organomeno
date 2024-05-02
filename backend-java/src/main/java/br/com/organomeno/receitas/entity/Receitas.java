package br.com.organomeno.receitas.entity;

import br.com.organomeno.notaFiscal.entity.NotaFiscal;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RECEITAS", schema = "dbo")
public class Receitas extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RECEITA")
    private Integer id;
    @Column(name = "RECEITA_DESCRICAO")
    private String descricao;
    @Column(name = "RECEITA_VALOR_BRUTO")
    private Double valorBruto;
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;
    @ManyToOne
    @JoinColumn(name = "NOTA_FISCAL_VINCULADA")
    private NotaFiscal notaFiscal;
    @Column(name = "TRANSACAO_FITID", unique = true)
    private String fitId;

    public String getFitId() {
        return fitId;
    }

    public void setFitId(String fitId) {
        this.fitId = fitId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(Double valorBruto) {
        this.valorBruto = valorBruto;
    }

    public Date getDataEntrada() {
        return dataCadastro;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataCadastro = dataEntrada;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
}
