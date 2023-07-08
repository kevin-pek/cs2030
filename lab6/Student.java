class Student extends KeyableMap<Module> {
    Student(String key) {
        super(key);
    }

    Student(String key, ImmutableMap<String, Module> modules) {
        super(key, modules);
    }

    public Student put(Module item) {
        return new Student(getKey(), putItem(item));
    }
}
