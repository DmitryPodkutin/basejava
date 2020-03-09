package util;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.model.DescriptionSection;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.Section;

import static ru.javawebinar.basejava.storage.ResumeTestData.*;


public class JsonParserTest {

    @Test
    public void testResume() {
        String json = JsonParser.write(RESUME_1);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(RESUME_1, resume);
    }

    @Test
    public void write()  {
        Section section1 = new DescriptionSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        Assert.assertEquals(section1, section2);
    }
}
