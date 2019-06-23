package linux.find;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class NameCriteria extends BasicSearchCriteria {
    private String name;

    public NameCriteria(SearchCriteria base, String name) {
        super(base);
        this.name = name;
    }

    @Override
    public List<File> filter(List<File> list) {
        return s.filter(list.stream().filter(f -> f.getName().contains(name)).collect(Collectors.toList()));
    }
}
