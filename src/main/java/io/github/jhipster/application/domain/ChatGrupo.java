package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A ChatGrupo.
 */
@Entity
@Table(name = "chat_grupo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "chatgrupo")
public class ChatGrupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @NotNull
    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "fecha")
    private ZonedDateTime fecha;

    @Column(name = "leido")
    private Boolean leido;

    @ManyToOne
    @JsonIgnoreProperties("chatGrupos")
    private User de;

    @ManyToOne
    @JsonIgnoreProperties("chatGrupos")
    private User para;

    @ManyToOne
    @JsonIgnoreProperties("chatGrupos")
    private ChatListGrupo chatListGrupo;

    @ManyToOne
    @JsonIgnoreProperties("chatGrupos")
    private Grupos grupo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public ChatGrupo mensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ZonedDateTime getFecha() {
        return fecha;
    }

    public ChatGrupo fecha(ZonedDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(ZonedDateTime fecha) {
        this.fecha = fecha;
    }

    public Boolean isLeido() {
        return leido;
    }

    public ChatGrupo leido(Boolean leido) {
        this.leido = leido;
        return this;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public User getDe() {
        return de;
    }

    public ChatGrupo de(User user) {
        this.de = user;
        return this;
    }

    public void setDe(User user) {
        this.de = user;
    }

    public User getPara() {
        return para;
    }

    public ChatGrupo para(User user) {
        this.para = user;
        return this;
    }

    public void setPara(User user) {
        this.para = user;
    }

    public ChatListGrupo getChatListGrupo() {
        return chatListGrupo;
    }

    public ChatGrupo chatListGrupo(ChatListGrupo chatListGrupo) {
        this.chatListGrupo = chatListGrupo;
        return this;
    }

    public void setChatListGrupo(ChatListGrupo chatListGrupo) {
        this.chatListGrupo = chatListGrupo;
    }

    public Grupos getGrupo() {
        return grupo;
    }

    public ChatGrupo grupo(Grupos grupos) {
        this.grupo = grupos;
        return this;
    }

    public void setGrupo(Grupos grupos) {
        this.grupo = grupos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChatGrupo)) {
            return false;
        }
        return id != null && id.equals(((ChatGrupo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ChatGrupo{" +
            "id=" + getId() +
            ", mensaje='" + getMensaje() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", leido='" + isLeido() + "'" +
            "}";
    }
}