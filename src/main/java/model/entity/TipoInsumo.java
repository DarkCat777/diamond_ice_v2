package model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_tipo_insumo", schema = "public", catalog = "test")
public class TipoInsumo {
    @Id
    //@NotNull(message = "El id de tipo insumo no puede ser nulo.")
    @GeneratedValue
    @Column(name = "id_tipo_insumo", nullable = false)
    private Long idTipoInsumo;
    @Basic
    @NotBlank(message = "El nombre del tipo de insumo no puede estar vacio.")
    @Column(name = "nombre_tipo_insumo", nullable = false, length = 64)
    private String nombreTipoInsumo;
    @Basic
    @NotBlank(message = "La descripcion del tipo de insumo no puede estar vacio.")
    @Column(name = "descripcion_tipo_insumo", nullable = false, length = 255)
    private String descripcionTipoInsumo;
    @Basic
    @Column(name = "estado_registro", nullable = true)
    private Boolean estadoRegistro;
    @OneToMany(mappedBy = "TipoInsumoByIdTipoInsumo", fetch = FetchType.LAZY)
    private List<Insumo> InsumosByIdTipoInsumo;

    public TipoInsumo(@NotBlank(message = "El nombre del tipo de insumo no puede estar vacio.") String nombreTipoInsumo, @NotBlank(message = "La descripcion del tipo de insumo no puede estar vacio.") String descripcionTipoInsumo, Boolean estadoRegistro) {
        this.nombreTipoInsumo = nombreTipoInsumo;
        this.descripcionTipoInsumo = descripcionTipoInsumo;
        this.estadoRegistro = estadoRegistro;
    }

    public TipoInsumo() {

    }

    public long getIdTipoInsumo() {
        return idTipoInsumo;
    }

    public void setIdTipoInsumo(long idTipoInsumo) {
        this.idTipoInsumo = idTipoInsumo;
    }

    public String getNombreTipoInsumo() {
        return nombreTipoInsumo;
    }

    public void setNombreTipoInsumo(String nombreTipoInsumo) {
        this.nombreTipoInsumo = nombreTipoInsumo;
    }

    public String getDescripcionTipoInsumo() {
        return descripcionTipoInsumo;
    }

    public void setDescripcionTipoInsumo(String descripcionTipoInsumo) {
        this.descripcionTipoInsumo = descripcionTipoInsumo;
    }

    public Boolean getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(Boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoInsumo that = (TipoInsumo) o;
        return idTipoInsumo == that.idTipoInsumo &&
                Objects.equals(nombreTipoInsumo, that.nombreTipoInsumo) &&
                Objects.equals(descripcionTipoInsumo, that.descripcionTipoInsumo) &&
                Objects.equals(estadoRegistro, that.estadoRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoInsumo, nombreTipoInsumo, descripcionTipoInsumo, estadoRegistro);
    }

    public List<Insumo> getInsumosByIdTipoInsumo() {
        return InsumosByIdTipoInsumo;
    }

    public void setInsumosByIdTipoInsumo(List<Insumo> insumosByIdTipoInsumo) {
        InsumosByIdTipoInsumo = insumosByIdTipoInsumo;
    }

    @Override
    public String toString() {
        return nombreTipoInsumo;
    }
}
