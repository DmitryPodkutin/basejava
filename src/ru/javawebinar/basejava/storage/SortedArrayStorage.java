package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void positionForSave(Resume resume, int index) {
        int recordIndex = index * -1 - 1;
        System.arraycopy(storage, recordIndex, storage, recordIndex + 1, recordIndex + 1);
        storage[recordIndex] = resume;
    }

    @Override
    protected void positionForDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }
}
