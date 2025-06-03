/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microconductor.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "conductor")
@NamedQueries({
    @NamedQuery(name = "Conductor.findAll", query = "SELECT c FROM Conductor c"),
    @NamedQuery(name = "Conductor.findByNombre", query = "SELECT c FROM Conductor c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Conductor.findByApellido", query = "SELECT c FROM Conductor c WHERE c.apellido = :apellido"),
    @NamedQuery(name = "Conductor.findByTipoDocumento", query = "SELECT c FROM Conductor c WHERE c.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Conductor.findByNumeroDocumento", query = "SELECT c FROM Conductor c WHERE c.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "Conductor.findByTelefono", query = "SELECT c FROM Conductor c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Conductor.findByCorreo", query = "SELECT c FROM Conductor c WHERE c.correo = :correo"),
    @NamedQuery(name = "Conductor.findByContrasena", query = "SELECT c FROM Conductor c WHERE c.contrasena = :contrasena"),
    @NamedQuery(name = "Conductor.findByCreatedat", query = "SELECT c FROM Conductor c WHERE c.createdat = :createdat")})
public class ConductorDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "id_conductor")
    private Object idConductor;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 2147483647)
    @Column(name = "tipo_documento")
     private TypesConductor.tipo_documento tipoDocumento;
    @Size(max = 50)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 100)
    @Column(name = "correo")
    private String correo;
    @Size(max = 255)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private String createdat;

    public ConductorDTO() {
    }

    public ConductorDTO(Object idConductor) {
        this.idConductor = idConductor;
    }

    public ConductorDTO(Object idConductor, String createdat) {
        this.idConductor = idConductor;
        this.createdat = createdat;
    }

    public Object getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Object idConductor) {
        this.idConductor = idConductor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TypesConductor.tipo_documento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TypesConductor.tipo_documento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConductor != null ? idConductor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConductorDTO)) {
            return false;
        }
        ConductorDTO other = (ConductorDTO) object;
        if ((this.idConductor == null && other.idConductor != null) || (this.idConductor != null && !this.idConductor.equals(other.idConductor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConductorDTO{" + "idConductor=" + idConductor + ", nombre=" + nombre + ", apellido=" + apellido + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", telefono=" + telefono + ", correo=" + correo + ", contrasena=" + contrasena + '}';
    }
}
