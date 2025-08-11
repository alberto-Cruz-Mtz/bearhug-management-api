package site.bearhug.management.persistence.model;

public enum Permission {

    //admin
    CREATE, READ, UPDATE, DELETE,

    //user
    CHANGE_PASSWORD, LOGOUT, DELETE_ACCOUNT, RECEIVE_NOTIFICATION,

    //invited
    VIEW_PUBLIC_CONTENT
}
