package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serialization.ObjectStreamSerialization;
import ru.javawebinar.basejava.storage.serialization.Serialization;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileStorage extends AbstractStorage<File> {
    private File directory;
    private Serialization serialization = new ObjectStreamSerialization();

    protected FileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory mast be not null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writable");
        }
        this.directory = directory;
    }

    protected File[] getListFiles() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("No files in directory");
        }
        return files;
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            serialization.doWrite(new BufferedOutputStream(new FileOutputStream(file)), resume);
        } catch (IOException e) {
            throw new StorageException("File Write Error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't creat file", file.getAbsolutePath(), e);
        }
        doUpdate(file, resume);
    }

    @Override
    public Resume doGet(File file) {
        try {
            return serialization.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read Error", file.getName(), e);
        }

    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File not delete ", file.getName());
        }
    }

    @Override
    File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> getListStorage() {
       return Arrays.stream(getListFiles()).map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        for (File myFile : getListFiles()) {
            doDelete(myFile);
        }
    }

    @Override
    public int size() {
        return getListFiles().length;
    }
}
