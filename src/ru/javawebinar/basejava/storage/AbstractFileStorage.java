package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory mast be not null");
        if (directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writable");
        }
        this.directory = directory;
    }

    protected abstract Resume doRead(File file);

    protected abstract void doWrite(File file, Resume resume) throws IOException;

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            doWrite(file, resume);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
            doWrite(file, resume);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
    }

    @Override
    public Resume doGet(File file) {
        return doRead(file);
    }

    @Override
    protected void doDelete(File file) {
        if (file.delete()) {
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
        File[] files = directory.listFiles();
        List<Resume> list = new ArrayList<>();
        for (File fl : Objects.requireNonNull(files)) {
            list.add(doGet(fl));
        }
        return list;
    }

    @Override
    public void clear() {
        for (File myFile : Objects.requireNonNull(directory.listFiles())) {
            if (myFile.isFile()) {
                if (myFile.delete()) {
                    throw new StorageException("File not delete ", myFile.getName());
                }
            }
        }
    }

    @Override
    public int size() {
        int i = 0;
        for (File fl : Objects.requireNonNull(directory.listFiles())) {
            if (fl.isFile()) {
                i++;
            }
        }
        return i;
    }
}
