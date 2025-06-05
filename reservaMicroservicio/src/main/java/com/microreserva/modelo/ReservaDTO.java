/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microreserva.modelo;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author Laura
 */
@Entity
@Table(name = "reserva")
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    @NamedQuery(name = "Reserva.findByNombreParqueadero", query = "SELECT r FROM Reserva r WHERE r.nombreParqueadero = :nombreParqueadero"),
    @NamedQuery(name = "Reserva.findByTipoVehiculo", query = "SELECT r FROM Reserva r WHERE r.tipoVehiculo = :tipoVehiculo"),
    @NamedQuery(name = "Reserva.findByFechaReserva", query = "SELECT r FROM Reserva r WHERE r.fechaReserva = :fechaReserva"),
    @NamedQuery(name = "Reserva.findByHoraInicio", query = "SELECT r FROM Reserva r WHERE r.horaInicio = :horaInicio"),
    @NamedQuery(name = "Reserva.findByHoraFin", query = "SELECT r FROM Reserva r WHERE r.horaFin = :horaFin"),
    @NamedQuery(name = "Reserva.findByEstado", query = "SELECT r FROM Reserva r WHERE r.estado = :estado"),
    @NamedQuery(name = "Reserva.findByDisponible", query = "SELECT r FROM Reserva r WHERE r.disponible = :disponible"),
    @NamedQuery(name = "Reserva.findByValorEstimado", query = "SELECT r FROM Reserva r WHERE r.valorEstimado = :valorEstimado"),
    @NamedQuery(name = "Reserva.findByCreatedAt", query = "SELECT r FROM Reserva r WHERE r.createdAt = :createdAt")})
public class ReservaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "id_reserva")
    private Object idReserva;
    @Size(max = 100)
    @Column(name = "nombre_parqueadero")
    private String nombreParqueadero;
    @Size(max = 2147483647)
    @Column(name = "tipo_vehiculo")
    private TypesReserva.tipo_vehiculos_aceptados tipoVehiculo;
    @Column(name = "fecha_reserva")
    @Temporal(TemporalType.DATE)
    private Date fechaReserva;
    @Column(name = "hora_inicio")
    private String horaInicio;
    @Column(name = "hora_fin")
    private String horaFin;
    @Size(max = 2147483647)
    @Column(name = "estado")
    private TypesReserva.estado_reserva estado;
    @Column(name = "disponible")
    private Boolean disponible;
    @Column(name = "valor_estimado")
    private BigInteger valorEstimado;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "id_conductor_fk", referencedColumnName = "id_conductor")
    @ManyToOne
    private Object idConductorFk;
    @JoinColumn(name = "id_parqueadero_fk", referencedColumnName = "id_parqueadero")
    @ManyToOne
    private Object idParqueaderoFk;

    public ReservaDTO() {
    }

    public ReservaDTO(Object idReserva, String nombreParqueadero, TypesReserva.tipo_vehiculos_aceptados tipoVehiculo, Date fechaReserva, String horaInicio, String horaFin, TypesReserva.estado_reserva estado, Boolean disponible, BigInteger valorEstimado, Date createdAt, Object idConductorFk, Object idParqueaderoFk) {
        this.idReserva = idReserva;
        this.nombreParqueadero = nombreParqueadero;
        this.tipoVehiculo = tipoVehiculo;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.disponible = disponible;
        this.valorEstimado = valorEstimado;
        this.createdAt = createdAt;
        this.idConductorFk = idConductorFk;
        this.idParqueaderoFk = idParqueaderoFk;
    }
    
    

    public ReservaDTO(Object idReserva) {
        this.idReserva = idReserva;
    }

    public Object getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Object idReserva) {
        this.idReserva = idReserva;
    }

    public String getNombreParqueadero() {
        return nombreParqueadero;
    }

    public void setNombreParqueadero(String nombreParqueadero) {
        this.nombreParqueadero = nombreParqueadero;
    }

    public TypesReserva.tipo_vehiculos_aceptados getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TypesReserva.tipo_vehiculos_aceptados tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public TypesReserva.estado_reserva getEstado() {
        return estado;
    }

    public void setEstado(TypesReserva.estado_reserva estado) {
        this.estado = estado;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public BigInteger getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(BigInteger valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Object getIdConductorFk() {
        return idConductorFk;
    }

    public void setIdConductorFk(Object idConductorFk) {
        this.idConductorFk = idConductorFk;
    }

    public Object getIdParqueaderoFk() {
        return idParqueaderoFk;
    }

    public void setIdParqueaderoFk(Object idParqueaderoFk) {
        this.idParqueaderoFk = idParqueaderoFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReserva != null ? idReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaDTO)) {
            return false;
        }
        ReservaDTO other = (ReservaDTO) object;
        if ((this.idReserva == null && other.idReserva != null) || (this.idReserva != null && !this.idReserva.equals(other.idReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.modelo.Reserva[ idReserva=" + idReserva + " ]";
    }
    
}
