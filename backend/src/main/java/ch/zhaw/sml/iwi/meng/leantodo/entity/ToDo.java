package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ToDo
 */
@Entity
public class ToDo {
    
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private boolean done = false;
    private String beschreibung;
    private Date zeitpunkt;
    private int priorisierung;
    @Lob
    private byte[] foto;

    @JsonIgnore
    private String owner;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * @param done the done to set
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    //Beschreibung
    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    //Priorisierung
    public int getPriorisierung() {
        return priorisierung;
    }

    public void setPriorisierung(int priorisierung) {
        this.priorisierung = priorisierung;
    }

    //Foto Attachement
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    //Zeitpunkt der ToDo Aufgabe
    public Date getZeitpunkt() {
        return zeitpunkt;
    }

    public void setZeitpunkt(Date zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

}