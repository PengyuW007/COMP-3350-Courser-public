package comp3350.courser.tests.persistence;

class SectionDB {
    protected String sectionId;
    protected int crn;
    protected String section; // A01 for lectures and B01 for labs for example
    protected String instructor; // might be a list of instructors
    protected String campus; // use codes?
    protected String courseFK; // CourseID
    protected String lectureFK; // CRN - -1 if lecture section and section id if Lab

    protected SectionDB(String sectionId, int crn, String section, String instructor, String campus, String courseFK, String lectureFK) {
        this.sectionId = sectionId;
        this.crn = crn;
        this.section = section;
        this.instructor = instructor;
        this.campus = campus;
        this.courseFK = courseFK;
        this.lectureFK = lectureFK;
    }
}
