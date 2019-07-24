package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.ChatsListado} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.ChatsListadoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /chats-listados?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ChatsListadoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter propietarioId;

    private LongFilter destinatarioId;

    private LongFilter chatId;

    public ChatsListadoCriteria(){
    }

    public ChatsListadoCriteria(ChatsListadoCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.propietarioId = other.propietarioId == null ? null : other.propietarioId.copy();
        this.destinatarioId = other.destinatarioId == null ? null : other.destinatarioId.copy();
        this.chatId = other.chatId == null ? null : other.chatId.copy();
    }

    @Override
    public ChatsListadoCriteria copy() {
        return new ChatsListadoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(LongFilter propietarioId) {
        this.propietarioId = propietarioId;
    }

    public LongFilter getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(LongFilter destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public LongFilter getChatId() {
        return chatId;
    }

    public void setChatId(LongFilter chatId) {
        this.chatId = chatId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ChatsListadoCriteria that = (ChatsListadoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(propietarioId, that.propietarioId) &&
            Objects.equals(destinatarioId, that.destinatarioId) &&
            Objects.equals(chatId, that.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        propietarioId,
        destinatarioId,
        chatId
        );
    }

    @Override
    public String toString() {
        return "ChatsListadoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (propietarioId != null ? "propietarioId=" + propietarioId + ", " : "") +
                (destinatarioId != null ? "destinatarioId=" + destinatarioId + ", " : "") +
                (chatId != null ? "chatId=" + chatId + ", " : "") +
            "}";
    }

}
