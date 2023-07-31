package models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

        @Entity
        @Table(name = "TB_ENDERECO")
        public class EnderecoModel  implements Serializable {
            private static final long serialVersionUID = 1L;

            @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            private UUID idEndereco;
            //TODO: ESTA EM COMENTARIO, AGUARDANDO NOVO COMMIT COM DADOS ATUALIZADOS DE IDCLIENT E IDFUNCIONARIO
//            @OneToOne
//            @JoinColumn(name = "clientId", referencedColumnName = "idClient")
//            private ClientModel client;       //criando conexão com clientId
//            @OneToOne
//            @JoinColumn(name = "funcionarioId", referencedColumnName = "idFuncionario")
//            private FuncionarioModel funcionario;     //criando conexão com funcionarioId

            private String logradouro;
            private int numero;
            private String complemento;
            private String cidade;
            private String estado;
            private String pais;
            private String cep;
            private int status;



            public UUID getIdEndereco() {
                return idEndereco;
            }

            public void setIdEndereco(UUID idEndereco) {
                this.idEndereco = idEndereco;
            }

            public String getLogradouro() {
                return logradouro;
            }

            public void setLogradouro(String logradouro) {
                this.logradouro = logradouro;
            }

            public int getNumero() {
                return numero;
            }

            public void setNumero(int numero) {
                this.numero = numero;
            }

            public String getComplemento() {
                return complemento;
            }

            public void setComplemento(String complemento) {
                this.complemento = complemento;
            }

            public String getCidade() {
                return cidade;
            }

            public void setCidade(String cidade) {
                this.cidade = cidade;
            }

            public String getEstado() {
                return estado;
            }

            public void setEstado(String estado) {
                this.estado = estado;
            }

            public String getPais() {
                return pais;
            }

            public void setPais(String pais) {
                this.pais = pais;
            }

            public String getCep() {
                return cep;
            }

            public void setCep(String cep) {
                this.cep = cep;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }