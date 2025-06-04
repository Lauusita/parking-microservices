/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microparqueadero.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author rojas
 */
@Entity
@Table(name = "parqueadero")
@NamedQueries({
    @NamedQuery(name = "Parqueadero.findAll", query = "SELECT p FROM Parqueadero p"),
    @NamedQuery(name = "Parqueadero.findByNombre", query = "SELECT p FROM Parqueadero p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Parqueadero.findByDireccion", query = "SELECT p FROM Parqueadero p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Parqueadero.findByCiudad", query = "SELECT p FROM Parqueadero p WHERE p.ciudad = :ciudad"),
    @NamedQuery(name = "Parqueadero.findByNumeroCeldas", query = "SELECT p FROM Parqueadero p WHERE p.numeroCeldas = :numeroCeldas"),
    @NamedQuery(name = "Parqueadero.findByTipoCeldas", query = "SELECT p FROM Parqueadero p WHERE p.tipoCeldas = :tipoCeldas"),
    @NamedQuery(name = "Parqueadero.findByTarifaHora", query = "SELECT p FROM Parqueadero p WHERE p.tarifaHora = :tarifaHora"),
    @NamedQuery(name = "Parqueadero.findByEstado", query = "SELECT p FROM Parqueadero p WHERE p.estado = :estado"),
    @NamedQuery(name = "Parqueadero.findByCalificacion", query = "SELECT p FROM Parqueadero p WHERE p.calificacion = :calificacion"),
    @NamedQuery(name = "Parqueadero.findByDepartamento", query = "SELECT p FROM Parqueadero p WHERE p.departamento = :departamento"),
    @NamedQuery(name = "Parqueadero.findByTiposVehiculosAceptados", query = "SELECT p FROM Parqueadero p WHERE p.tiposVehiculosAceptados = :tiposVehiculosAceptados"),
    @NamedQuery(name = "Parqueadero.findByCreatedAt", query = "SELECT p FROM Parqueadero p WHERE p.createdAt = :createdAt")})
public class ParqueaderoDTO implements Serializable {

    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 200)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 100)
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "calificacion")
    private Integer calificacion;
    @Size(max = 100)
    @Column(name = "departamento")
    private String departamento;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "id_parqueadero")
    private Object idParqueadero;
    @Column(name = "numero_celdas")
    private Integer numeroCeldas;
    @Column(name = "tarifa_hora")
    private Integer tarifaHora;
    @Lob
    @Column(name = "horario_atencion")
    private Object horarioAtencion;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "tipos_vehiculos_aceptados")
    private TypesParqueadero.tipo_vehiculo_aceptado tiposVehiculosAceptados;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @JoinColumn(name = "id_propietario_fk", referencedColumnName = "id_propietario")
    @ManyToOne
    private Object idPropietarioFk;

    public ParqueaderoDTO() {
    }

    public ParqueaderoDTO(Object idParqueadero) {
        this.idParqueadero = idParqueadero;
    }

    public Object getIdParqueadero() {
        return idParqueadero;
    }

    public void setIdParqueadero(Object idParqueadero) {
        this.idParqueadero = idParqueadero;
    }


    public Integer getNumeroCeldas() {
        return numeroCeldas;
    }

    public void setNumeroCeldas(Integer numeroCeldas) {
        this.numeroCeldas = numeroCeldas;
    }

    public Integer getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(Integer tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public Object getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(Object horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


    public TypesParqueadero.tipo_vehiculo_aceptado getTiposVehiculosAceptados() {
        return tiposVehiculosAceptados;
    }

    public void setTiposVehiculosAceptados(TypesParqueadero.tipo_vehiculo_aceptado tiposVehiculosAceptados) {
        this.tiposVehiculosAceptados = tiposVehiculosAceptados;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Object getIdPropietarioFk() {
        return idPropietarioFk;
    }

    public void setIdPropietarioFk(Object idPropietarioFk) {
        this.idPropietarioFk = idPropietarioFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParqueadero != null ? idParqueadero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParqueaderoDTO)) {
            return false;
        }
        ParqueaderoDTO other = (ParqueaderoDTO) object;
        if ((this.idParqueadero == null && other.idParqueadero != null) || (this.idParqueadero != null && !this.idParqueadero.equals(other.idParqueadero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ParqueaderoDTO{" + "idParqueadero=" + idParqueadero + ", nombre=" + nombre + ", direccion=" + direccion + ", ciudad=" + ciudad + ", numeroCeldas=" + numeroCeldas + ", tarifaHora=" + tarifaHora + ", horarioAtencion=" + horarioAtencion + ", estado=" + estado + ", calificacion=" + calificacion + ", departamento=" + departamento + ", tiposVehiculosAceptados=" + tiposVehiculosAceptados + ", createdAt=" + createdAt + ", idPropietarioFk=" + idPropietarioFk + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    
    
}
