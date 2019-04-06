import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int noteNullArraySize = 0;

    void clear() {
        Arrays.fill(storage, null);
        noteNullArraySize = 0;
    }

    void save(Resume r) {
        if (noteNullArraySize <= storage.length - 1) {
            storage[noteNullArraySize] = r;
            noteNullArraySize++;
        }
    }

    Resume get(String uuid) {
        for (Resume aStorage : storage) {
            if (aStorage != null && aStorage.uuid.equals(uuid)) {
                return aStorage;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int j;               //счетчик цикла
        for (j = 0; j < storage.length; j++) {
            if (storage[j] != null && storage[j].uuid.equals(uuid)) {
                storage[j] = null;
                break;
            }
        }
        System.arraycopy(storage, j + 1, storage, j, storage.length - 1 - j);
        noteNullArraySize--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, noteNullArraySize);
    }

    int size() {
        return noteNullArraySize;
    }
}
