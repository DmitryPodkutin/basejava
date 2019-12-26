package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private Serialization serialization = new ObjectStreamStorage();

    protected PathStorage(String dir) {
        Objects.requireNonNull(dir, "Directory mast be not null");
        this.directory = Paths.get(dir);
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(dir + "is not directory");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not readable/writable");
        }

    }

    @Override
    protected void doUpdate(Path path, Resume resume) {
        try {
            serialization.doWrite(new BufferedOutputStream(Files.newOutputStream(path)), resume);
        } catch (IOException e) {
            throw new StorageException("Path Write Error", getFileName(path), e);
        }
    }

    @Override
    protected void doSave(Path path, Resume resume) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't creat Path", getFileName(path), e);
        }
        doUpdate(path, resume);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serialization.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read Error", getFileName(path), e);
        }

    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path not delete", getFileName(path), e);
        }
    }

    @Override
    Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected List<Resume> getListStorage() {
        List<Resume> list = new ArrayList<>();
        for (Object fl : getFilesList().toArray()) {
            list.add(doGet((Path) fl));
        }
        return list;
    }

    public void clear() {
        getFilesList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return getFilesList().toArray().length;
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", directory.getName(0).toString(), e);
        }
    }
}
