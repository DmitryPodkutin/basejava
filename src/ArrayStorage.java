import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int noteNullArraySize = 0;

    void clear() {
        Arrays.fill(storage, 0, noteNullArraySize, null);
        noteNullArraySize = 0;
    }

    void save(Resume r) {
        if (noteNullArraySize < storage.length) {
            storage[noteNullArraySize] = r;
            noteNullArraySize++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < noteNullArraySize; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int j;               //счетчик цикла
        for (j = 0; j < noteNullArraySize; j++) {
            if (storage[j].uuid.equals(uuid)) {
                break;
            }
        }
        System.arraycopy(storage, j + 1, storage, j, noteNullArraySize - 1 - j);
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
