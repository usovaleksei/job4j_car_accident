CREATE TABLE accident (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(2000),
    TEXT TEXT,
    ADDRESS VARCHAR(2000),



    private String text;
private String address;
private AccidentType type;
private Set<Rule> rules;
);

