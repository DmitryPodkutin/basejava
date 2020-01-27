package ru.javawebinar.basejava.storage.serialization;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serialization {

    @Override
    public void doWrite(OutputStream os, Resume r) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(),contactTypeStringEntry -> {
                dos.writeUTF(contactTypeStringEntry.getKey().name());
                dos.writeUTF(contactTypeStringEntry.getValue());
            });
            writeCollection(dos, r.getSections().entrySet(),sectionTypeSectionEntry -> {
                SectionType type = sectionTypeSectionEntry.getKey();
                Section section = sectionTypeSectionEntry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((DescriptionSection) section).getDescription());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListSection) section).getItems(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((OrganizationSection) section).getOrganizations(), organization -> {
                            dos.writeUTF(organization.getHomepage().getName());
                            dos.writeUTF(organization.getHomepage().getUrl());
                            writeCollection(dos, organization.getPositions(), position -> {
                                writeDate(dos, position.getBeginDate());
                                writeDate(dos, position.getEndDate());
                                dos.writeUTF(position.getPosition());
                                dos.writeUTF(position.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            doCycle(dis,()-> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            doCycle(dis,()-> {
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type.name()) {
                    case "OBJECTIVE":
                    case "PERSONAL":
                        resume.addSection(type, new DescriptionSection(dis.readUTF()));
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        resume.addSection(type, new ListSection(readList(dis, dis::readUTF)));
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        resume.addSection(type, new OrganizationSection(readList(dis,()->
                                new Organization(new Link(dis.readUTF(), dis.readUTF()),
                                readList(dis, () -> new Organization.Position(readDate(dis), readDate(dis), dis.readUTF(), dis.readUTF()))))));
                        break;
                }
            });
            return resume;
        }
    }
    @FunctionalInterface
    private interface CycleInterface {
        void apply() throws IOException;
    }

    private void doCycle(DataInputStream dis, CycleInterface cycleInterface ) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            cycleInterface.apply();
        }
    }
    @FunctionalInterface
    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }
    @FunctionalInterface
    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T org : collection) {
            writer.write(org);
        }
    }

    private void writeDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonthValue());
        dos.writeInt(localDate.getDayOfMonth());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }
}