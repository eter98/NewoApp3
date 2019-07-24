package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ChatsListado.
 */
@Entity
@Table(name = "chats_listado")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "chatslistado")
public class ChatsListado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("chatsListados")
    private User propietario;

    @ManyToOne
    @JsonIgnoreProperties("chatsListados")
    private User destinatario;

    @OneToMany(mappedBy = "chatsListado")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Chat> chats = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPropietario() {
        return propietario;
    }

    public ChatsListado propietario(User user) {
        this.propietario = user;
        return this;
    }

    public void setPropietario(User user) {
        this.propietario = user;
    }

    public User getDestinatario() {
        return destinatario;
    }

    public ChatsListado destinatario(User user) {
        this.destinatario = user;
        return this;
    }

    public void setDestinatario(User user) {
        this.destinatario = user;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    public ChatsListado chats(Set<Chat> chats) {
        this.chats = chats;
        return this;
    }

    public ChatsListado addChat(Chat chat) {
        this.chats.add(chat);
        chat.setChatsListado(this);
        return this;
    }

    public ChatsListado removeChat(Chat chat) {
        this.chats.remove(chat);
        chat.setChatsListado(null);
        return this;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChatsListado)) {
            return false;
        }
        return id != null && id.equals(((ChatsListado) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ChatsListado{" +
            "id=" + getId() +
            "}";
    }
}
