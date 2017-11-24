package io.github.rogervirtuoso.listacompras.java.backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roger
 */
@Entity
@Table
public class Compras implements Serializable {

    public static final String DATE_PATTERN = "dd/MM/yyyy hh:mm";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 30, nullable = false)
    private String nome;

    @Column(length = 200)
    private String observacao;

    @Column(length = 8, nullable = false)
    private Long status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_cadastro", nullable = false, updatable = false)
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_alteracao", nullable = false)
    private Date dataAlteracao;

    @Transient
    private String descricaoStatus;

    @Transient
    private String dataCadastroFormatada;

    @Transient
    private String dataAlteracaoFormatada;

    @Transient
    private Double totalCompras;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Double getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(Double totalCompras) {
        this.totalCompras = totalCompras;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compras other = (Compras) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Nome da Lista: ");
        sb.append(getNome());

        return sb.toString();
    }

    public enum Status {
        NOVO(1L, "Novo"),
        ALTERADO(2L, "Alterado"),;

        private Long codigo;
        private String descricao;

        Status(Long codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public static Status valueOf(Long codigo) {
            if (codigo == null) {
                return null;
            }
            for (Status status : Status.values()) {
                if (status.getCodigo().equals(codigo)) return status;
            }
            return null;

        }

        public Long getCodigo() {
            return codigo;
        }

        public void setCodigo(Long codigo) {
            this.codigo = codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }
    }

    public String getDescricaoStatus() {
        if (this.status == null) {
            return "";
        }
        return Status.valueOf(this.status).descricao;
    }

    public String getDataCadastroFormatada() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return simpleDateFormat.format(this.getDataCadastro());
    }

    public String getDataAlteracaoFormatada() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return simpleDateFormat.format(this.getDataAlteracao());
    }
}
