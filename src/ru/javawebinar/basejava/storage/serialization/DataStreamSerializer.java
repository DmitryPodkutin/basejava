package ru.javawebinar.basejava.storage.serialization;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serialization {

    @Override
    public void doWrite(OutputStream os, Resume r) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            dos.writeInt(r.getSections().size());
            for (Map.Entry<SectionType, Section> entry : r.getSections().entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type.name()) {
                    case "OBJECTIVE":
                    case "PERSONAL":
                        dos.writeUTF(((DescriptionSection) section).getDescription());
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        writeList(dos, ((ListSection) section).getItems(), s -> dos.writeUTF(s));
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        writeList(dos, ((OrganizationSection) section).getOrganizations(), organization -> {
                            dos.writeUTF(organization.getHomepage().getName());
                            dos.writeUTF(organization.getHomepage().getUrl());
                            writeList(dos, organization.getPositions(), position -> {
                                dos.writeUTF(position.getBeginDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getPosition());
                                dos.writeUTF(position.getDescription());
                            });
                        });
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
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
                        resume.addSection(type, new OrganizationSection(new Organization(new Link(dis.readUTF(), dis.readUTF()),
                                readList(dis, () -> new Organization.Position(LocalDate.now(), LocalDate.now(), dis.readUTF(), dis.readUTF())))));
                        break;
                }

            }
            System.out.println(resume.toString());
            return resume;
        }
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    public <T> void writeList(DataOutputStream dos, List<T> list, ElementWriter<T> writer) throws IOException {
        dos.writeInt(list.size());
        for (T org : list) {
            writer.write(org);
        }
    }
}
