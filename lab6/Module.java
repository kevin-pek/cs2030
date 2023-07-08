class Module extends KeyableMap<Assessment> {
    Module(String key) {
        super(key);
    }

    Module(String key, ImmutableMap<String, Assessment> assessments) {
        super(key, assessments);
    }
    
    public Module put(Assessment item) {
        return new Module(getKey(), putItem(item));
    }
}
