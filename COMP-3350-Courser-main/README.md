# Courser
[Private Repo](https://github.com/JoshuaDueck/COMP-3350-Courser)

Group members: Nicoli, Shawn, Pengyu, Nikita, Joshua

- Our log is kept in the main root folder of the repository.

- Major implemented features in iteration 1:
  - View Courses: found in GUI at WorkspaceActivity.java

- Major implemented features in iteration 2:
  - Create new course: found on workspace page.
  - Create new lecture or lab: found after "create new course" page.
  - HSQLDB: found in main.java.comp3350.courser.persistence.PersistenceAccessDB
  - Big stories implemented: I want to be able to create a new course, create a new lecture/lab section.

- Major implemented features in iteration 3:
  - Fixed HSQLDB implementation.
  - Added validation to "create course" flow. (found in Validator, as well as CreateNewCourse, CreateNewLab, CreateNewSection)
  - Increased test coverage (acceptance, unit, and integration) (in tests package).

- Testing environment:
  - Android 6.0 (Marshmallow, API level 23) emulated on Windows 10.

- Files included into delivery folder appendix deliverable
  - Structure
    - Architecture schematic in drawing: structure.pdf
    - DB and Objects structure proposal: Tentative_Object_Structure.pdf 
  - GUI
    - "GUI initial proposal".pdf
    - "GUI second proposal".pdf
    - "GUI proposal for iteration 1".pdf

- Packages:
  - comp3350.courser.business - Contains functions for working with the objects.
    - AccessService.java - Contains logic for connecting to a new DB, as well as the dependency injection.
    - CourseService.java - Contains logic for managing course objects.
    - ScheduleService.java - Contains the bulk of logic regarding schedule generation.
    - SectionService.java - Contains logic for determining if two sections overlap.
    - TimeSlotService.java - Contains logic for determining if two TimeSlots overlap.
  - comp3350.courser.objects - Contains objects which store information and provide basic mutator methods.
    - Course.java
    - Date.java - Represents a year, month, and day.
    - DayOfWeek.java - An enum to store the days of the week.
    - Lab.java
    - Lecture.java
    - Schedule.java
    - Section.java
    - TimeRange.java - A start time and an end time.
    - TimeSlot.java - A representation of a Lecture's time.
    - Workspace.java - Represents the workspace for a user's schedules.
  - comp3350.courser.persistence - Contains the interface and data required for implementing the stub database.
    - CourseDB.java - An object representation of courses in the database.
    - IPersistenceAccess - An interface for accessing the persistence layer.
    - PersistenceAccess.java - The methods for accessing the stub database.
    - PersistenceAccessDB.java - The methods for accessing the HSQLDB database.
    - SectionDB.java - An object representation of Sections in the database.
    - TimeSlotDB.java - An object representation of TimeSlots in the database.
  - comp3350.courser.presentation - Contains classes for working with front-end logic.
    - Alerts.java
    - CreateNewCourse.java
    - CreateNewLab.java
    - CreateNewSection.java
    - Validator.java
    - MainActivity.java
    - WorkspaceActivity.java
    - CourseAdapter.java
    - activity_create_new_course.XML
    - activity_create_new_lab.XML
    - activity_create_new_section.XML
    - activity_main.XML
    - activity_workspace.XML
    - content_main.XML
    - course_row.XML


- We included code that we found from other sources when it comes to our acceptance tests. These sources are found below:
    - TestUtils: https://github.com/dannyroa/espresso-samples/tree/master/RecyclerView/app/src/androidTest/java/com/dannyroa/espresso_samples/recyclerview
    - RecyclerViewMatcher: https://github.com/dannyroa/espresso-samples/tree/master/RecyclerView/app/src/androidTest/java/com/dannyroa/espresso_samples/recyclerview
    - ReclyclerClickOnView: https://stackoverflow.com/questions/28476507/using-espresso-to-click-view-inside-recyclerview-item