package ru.javawebinar.basejava.exception;

/**
 * Created by Dmitry on 22.10.2019.
 */
public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exists",uuid);
    }
}
