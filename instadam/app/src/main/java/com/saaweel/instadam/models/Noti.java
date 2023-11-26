// Declaraciones de paquetes
package com.saaweel.instadam.models;

// Declaraciones de librerías
import java.util.Date;

/**
 * Clase Noti
 * Esta clase representa un objeto de notificación en la aplicación.
 */
public class Noti {
    private User user;
    private String image;
    private String content;
    private Date date;

    /**
     * Constructor de la clase con imagen.
     * @param user Usuario asociado a la notificación.
     * @param image Imagen asociada a la notificación.
     * @param content Contenido de la notificación.
     */
    public Noti(User user, String image, String content) {
        this.user = user;
        this.image = image;
        this.content = content;
        this.date = new Date();
    }

    /**
     * Constructor de la clase sin imagen.
     * @param user Usuario asociado a la notificación.
     * @param content Contenido de la notificación.
     */
    public Noti(User user, String content) {
        this.user = user;
        this.content = content;
        this.date = new Date();
    }

    /**
     * Método para obtener el usuario asociado a la notificación.
     * @return Usuario asociado a la notificación.
     */
    public User getUser() {
        return user;
    }

    /**
     * Método para establecer el usuario asociado a la notificación.
     * @param user Nuevo usuario asociado a la notificación.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Método para obtener la imagen asociada a la notificación.
     * @return Imagen asociada a la notificación.
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Método para establecer la imagen asociada a la notificación.
     * @param image Nueva imagen asociada a la notificación.
     */
    public void setPost(String image) {
        this.image = image;
    }

    /**
     * Método para obtener el contenido de la notificación.
     * @return Contenido de la notificación.
     */
    public String getContent() {
        return content;
    }

    /**
     * Método para establecer el contenido de la notificación.
     * @param content Nuevo contenido de la notificación.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Método para obtener la fecha de la notificación.
     * @return Fecha de la notificación.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Método para establecer la fecha de la notificación.
     * @param date Nueva fecha de la notificación.
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
