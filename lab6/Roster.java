class Roster extends KeyableMap<Student> {
    Roster(String key) {
        super(key);
    }

    Roster(String key, ImmutableMap<String, Student> map) {
        super(key, map);
    }

    public Roster put(Student student) {
        return new Roster(getKey(), putItem(student));
    }

    public String getGrade(String name, String module, String assessment) {
        return get(name)
                .flatMap(v -> v.get(module))
                .flatMap(v -> v.get(assessment))
                .map(v -> v.getGrade())
                .orElse(String.format(
                        "No such record: %s %s %s",
                        name, module, assessment));
    }

    public Roster add(String name, String module, String assessment,
            String grade) {
        Student stu = get(name).orElse(new Student(name));
        Module mod = stu.get(module).orElse(new Module(module));
        mod = mod.put(new Assessment(assessment, grade));
        stu = stu.put(mod);
        return put(stu);
    }
}
