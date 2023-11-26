// Declaraciones de paquetes
package com.saaweel.instadam.models;

// Declaraciones de librerías
import java.util.Random;

/**
 * Clase Post
 * Esta clase representa un objeto de publicación en la aplicación.
 */
public class Post {
    private User user;
    private String image;
    private String description;
    private int likes;

    /**
     * Constructor de la clase.
     * @param user Usuario asociado a la publicación.
     * @param image Imagen asociada a la publicación.
     */
    public Post(User user, String image) {
        this.user = user;
        this.image = image;
        this.description = "";
        this.likes = new Random().nextInt(20000);
    }

    /**
     * Método para obtener el usuario asociado a la publicación.
     * @return Usuario asociado a la publicación.
     */
    public User getUser() {
        return user;
    }

    /**
     * Método para establecer el usuario asociado a la publicación.
     * @param user Nuevo usuario asociado a la publicación.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Método para obtener la imagen asociada a la publicación.
     * @return Imagen asociada a la publicación.
     */
    public String getImage() {
        return image;
    }

    /**
     * Método para establecer la imagen asociada a la publicación.
     * @param image Nueva imagen asociada a la publicación.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Método para obtener la descripción de la publicación.
     * @return Descripción de la publicación.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Método para establecer la descripción de la publicación.
     * @param description Nueva descripción de la publicación.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Método para obtener la cantidad de likes de la publicación.
     * @return Cantidad de likes de la publicación.
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Método para establecer la cantidad de likes de la publicación.
     * @param likes Nueva cantidad de likes de la publicación.
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }
}
