# java-path-spring-sgdigital-assignment

Project assignment for spring boot's training
---------------------------------------------
Implementation of a sample movie database system.

 - Entities
    - Title: This is the center piece of the application defining a title for the database system
    - Movie: Extends the title with movie specific information
    - Serie: Extends the title with serie specific information
    - Season: defines information about a serie's season.
    - Episode: defines information about a serie's episode.
    - Genre: defines the genres of a title, multiple genres for each title are possible
    - Crew: Defines a person withing the system that can have an association with a title
    - CrewRole: Defines the roles Crew can have for each title (e.g. Director/Actor/etc)
    - TitleCrew: Provides an assosiation between Title/Crew and Role

 - Api is available through swagger UI
   http://localhost:8080/swagger-ui.html

 - Available profiles
    - test-data : populates all entity classes with test data
    - sanity : performs all CRUD service actions as a form of self test

 - Search
    - Searchable entities Title/Season/Episode/Crew
    - Search available for each entity individually as well as a system wide search
