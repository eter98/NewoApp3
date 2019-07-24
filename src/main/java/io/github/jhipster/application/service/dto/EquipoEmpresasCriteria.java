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
 * Criteria class for the {@link io.github.jhipster.application.domain.EquipoEmpresas} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.EquipoEmpresasResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /equipo-empresas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EquipoEmpresasCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombre;

    private StringFilter telefono;

    private StringFilter correo;

    private StringFilter direccion;

    private StringFilter paginaWeb;

    private LongFilter miembroId;

    private LongFilter empresaId;

    public EquipoEmpresasCriteria(){
    }

    public EquipoEmpresasCriteria(EquipoEmpresasCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.telefono = other.telefono == null ? null : other.telefono.copy();
        this.correo = other.correo == null ? null : other.correo.copy();
        this.direccion = other.direccion == null ? null : other.direccion.copy();
        this.paginaWeb = other.paginaWeb == null ? null : other.paginaWeb.copy();
        this.miembroId = other.miembroId == null ? null : other.miembroId.copy();
        this.empresaId = other.empresaId == null ? null : other.empresaId.copy();
    }

    @Override
    public EquipoEmpresasCriteria copy() {
        return new EquipoEmpresasCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombre() {
        return nombre;
    }

    public void setNombre(StringFilter nombre) {
        this.nombre = nombre;
    }

    public StringFilter getTelefono() {
        return telefono;
    }

    public void setTelefono(StringFilter telefono) {
        this.telefono = telefono;
    }

    public StringFilter getCorreo() {
        return correo;
    }

    public void setCorreo(StringFilter correo) {
        this.correo = correo;
    }

    public StringFilter getDireccion() {
        return direccion;
    }

    public void setDireccion(StringFilter direccion) {
        this.direccion = direccion;
    }

    public StringFilter getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(StringFilter paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public LongFilter getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(LongFilter miembroId) {
        this.miembroId = miembroId;
    }

    public LongFilter getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(LongFilter empresaId) {
        this.empresaId = empresaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EquipoEmpresasCriteria that = (EquipoEmpresasCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(telefono, that.telefono) &&
            Objects.equals(correo, that.correo) &&
            Objects.equals(direccion, that.direccion) &&
            Objects.equals(paginaWeb, that.paginaWeb) &&
            Objects.equals(miembroId, that.miembroId) &&
            Objects.equals(empresaId, that.empresaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nombre,
        telefono,
        correo,
        direccion,
        paginaWeb,
        miembroId,
        empresaId
        );
    }

    @Override
    public String toString() {
        return "EquipoEmpresasCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nombre != null ? "nombre=" + nombre + ", " : "") +
                (telefono != null ? "telefono=" + telefono + ", " : "") +
                (correo != null ? "correo=" + correo + ", " : "") +
                (direccion != null ? "direccion=" + direccion + ", " : "") +
                (paginaWeb != null ? "paginaWeb=" + paginaWeb + ", " : "") +
                (miembroId != null ? "miembroId=" + miembroId + ", " : "") +
                (empresaId != null ? "empresaId=" + empresaId + ", " : "") +
            "}";
    }

}