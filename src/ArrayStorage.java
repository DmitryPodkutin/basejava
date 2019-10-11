import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[3];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else if (size >= storage.length) {
            System.out.println("Storage is full");
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Resume " + uuid + " can't be get because it doesn't exist");
        return null;
    }

    void delete(String uuid) {
        int j;               //счетчик цикла
        for (j = 0; j < size; j++) {
            if (storage[j].uuid.equals(uuid)) {
                break;
            }
        }
        System.arraycopy(storage, j + 1, storage, j, size - 1 - j);
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
