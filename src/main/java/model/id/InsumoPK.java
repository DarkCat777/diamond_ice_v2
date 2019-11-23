package model.id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class InsumoPK implements Serializable {
    @Id
    @GeneratedValue
    @NotNull(message = "El id de insumo no puede ser nulo.")
    @Column(name = "id_insumo", nullable = false)
    private Long idInsumo;
    @Id
    @NotNull(message = "El id de tipo insumo no puede ser nulo.")
    @Column(name = "id_tipo_insumo", nullable = false)
    private Long idTipoInsumo;

    public InsumoPK(Long idInsumo, Long idTipoInsumo) {
        this.idInsumo = idInsumo;
        this.idTipoInsumo = idTipoInsumo;
    }

    public InsumoPK() {

    }

    public long getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(long idInsumo) {
        this.idInsumo = idInsumo;
    }

    public long getIdTipoInsumo() {
        return idTipoInsumo;
    }

    public void setIdTipoInsumo(long idTipoInsumo) {
        this.idTipoInsumo = idTipoInsumo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsumoPK insumoPK = (InsumoPK) o;
        return idInsumo == insumoPK.idInsumo &&
                idTipoInsumo == insumoPK.idTipoInsumo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInsumo, idTipoInsumo);
    }
}
