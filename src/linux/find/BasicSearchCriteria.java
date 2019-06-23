package linux.find;

import java.io.File;
import java.util.List;

public class BasicSearchCriteria implements SearchCriteria {

    protected SearchCriteria s;

    public BasicSearchCriteria(SearchCriteria s) {
        this.s = s;
    }

    @Override
    public List<File> filter(List<File> list) {
        return list;
    }
}
