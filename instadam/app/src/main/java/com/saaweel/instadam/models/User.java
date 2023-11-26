// Declaraciones de paquetes
package com.saaweel.instadam.models;

/**
 * Clase User
 * Esta clase representa un objeto de usuario en la aplicación.
 */
public class User {
    private String username;
    private String avatar;
    private boolean verified;
    private int followers;
    private int follows;

    /**
     * Constructor de la clase.
     * @param username Nombre de usuario del usuario.
     */
    public User(String username) {
        this.username = username;
        this.avatar = "https://i.imgur.com/8UX9lcq.jpg";
        this.verified = false;
        this.followers = 0;
        this.follows = 0;
    }

    /**
     * Método para obtener el nombre de usuario del usuario.
     * @return Nombre de usuario del usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Método para establecer el nombre de usuario del usuario.
     * @param username Nuevo nombre de usuario del usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Método para obtener la URL del avatar del usuario.
     * @return URL del avatar del usuario.
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Método para establecer la URL del avatar del usuario.
     * @param avatar Nueva URL del avatar del usuario.
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Método para verificar si el usuario está verificado.
     * @return true si el usuario está verificado, false de lo contrario.
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * Método para establecer el estado de verificación del usuario.
     * @param verified Nuevo estado de verificación del usuario.
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    /**
     * Método para obtener la cantidad de seguidores del usuario.
     * @return Cantidad de seguidores del usuario.
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * Método para establecer la cantidad de seguidores del usuario.
     * @param followers Nueva cantidad de seguidores del usuario.
     */
    public void setFollowers(int followers) {
        this.followers = followers;
    }

    /**
     * Método para obtener la cantidad de usuarios que sigue el usuario.
     * @return Cantidad de usuarios que sigue el usuario.
     */
    public int getFollows() {
        return follows;
    }

    /**
     * Método para establecer la cantidad de usuarios que sigue el usuario.
     * @param follows Nueva cantidad de usuarios que sigue el usuario.
     */
    public void setFollows(int follows) {
        this.follows = follows;
    }

    /**
     * Método para verificar la igualdad de dos usuarios.
     * @param user Usuario con el que se compara.
     * @return true si los usuarios son iguales, false de lo contrario.
     */
    public boolean equals(User user) {
        return this.username.equals(user.getUsername());
    }
}
