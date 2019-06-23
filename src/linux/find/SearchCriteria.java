package linux.find;

import java.io.File;
import java.util.List;

public interface SearchCriteria {
    List<File> filter(List<File> list);
}
