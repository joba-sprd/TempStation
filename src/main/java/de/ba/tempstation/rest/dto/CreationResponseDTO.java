package de.ba.tempstation.rest.dto;

import java.net.URI;

public class CreationResponseDTO {
    private int id;
    private URI href;

    public CreationResponseDTO(int id, URI href) {
        this.id = id;
        this.href = href;
    }

    //region Getter/Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public URI getHref() {
        return href;
    }

    public void setHref(URI href) {
        this.href = href;
    }
    //endregion
}
