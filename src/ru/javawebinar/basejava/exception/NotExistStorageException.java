package ru.javawebinar.basejava.exception;

/**
 * Created by Dmitry on 22.10.2019.
 */
public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " not exist", uuid);
    }
}
