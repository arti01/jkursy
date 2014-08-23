package pl.eod2.managedUm;

import java.io.Serializable;

public class NamedNode implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String type;
    protected String name;
    protected Long id;
    protected String opis;

    public NamedNode(String name, Long id, String opis) {
        this.name = name;
        this.id = id;
        this.opis = opis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
